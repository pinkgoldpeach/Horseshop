package dao.impl;

import dao.Connector;
import dao.DaoException;
import dao.ReiterDAO;
import domain.Data;
import domain.ReiterData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rebeka on 2015.03.16..
 */
public class ReiterDAOJdbc implements ReiterDAO {

    private static final Logger log = LogManager.getLogger(ReiterDAOJdbc.class);

    @Override
    public ArrayList<ReiterData> empfangen() throws DaoException {
        ArrayList<ReiterData> result = new ArrayList<ReiterData>();
        try {
            ResultSet tabelle = Connector.getConnection().createStatement().executeQuery("SELECT * FROM reiter WHERE deleted = false");
            while (tabelle.next()) {
                result.add(new ReiterData(tabelle.getInt("id"), tabelle.getString("name"), tabelle.getDouble("skill")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean senden(ArrayList<ReiterData> daten) throws DaoException {
        try {
            PreparedStatement ps = Connector.getConnection().prepareStatement("INSERT INTO reiter(skill, deleted, name ) VALUES (?,?,?)");
            for (ReiterData d : daten) {
                if (d.istReiter()) {
                    ReiterData r = (ReiterData) d;
                    if (r.getID() == null) {
                        ps.setDouble(1, r.getSkill());
                        ps.setBoolean(2, false);
                        ps.setString(3, r.getName());
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean update(ArrayList<ReiterData> daten) throws DaoException {
        ArrayList<ReiterData> reiter = empfangen();
        ArrayList<Integer> reiterID = new ArrayList<Integer>();
        for (ReiterData d : reiter) {
            reiterID.add(d.getID());
        }
        try {
            PreparedStatement ps = Connector.getConnection().prepareStatement("UPDATE  reiter SET skill = ?, deleted = ?, name = ? WHERE  id = ?");
            for (ReiterData d : daten) {
                if (d.istReiter()) {
                    if (reiterID.contains(d.getID())) {
                        ReiterData r = (ReiterData) d;
                        ps.setDouble(1, r.getSkill());
                        ps.setBoolean(2, r.isDeleted());
                        ps.setString(3, r.getName());
                        ps.setInt(4, r.getID());
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean delete(ArrayList<ReiterData> daten) throws DaoException {
        for (ReiterData d : daten) {
            if (d.istReiter()) {
                ReiterData r = (ReiterData) d;
                r.delete();
            }
        }
        try {
            update(daten);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return true;
    }
}
