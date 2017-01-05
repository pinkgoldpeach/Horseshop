package dao.impl;

import dao.Connector;
import dao.DaoException;
import dao.PferdDAO;
import domain.Data;
import domain.PferdData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rebeka on 2015.03.16..
 */
public class PferdDAOJdbc implements PferdDAO {

    private static final Logger log = LogManager.getLogger(PferdDAOJdbc.class);

    @Override
    public ArrayList<PferdData> empfangen() throws DaoException {
        ArrayList<PferdData> result = new ArrayList<PferdData>();
        try {
            ResultSet tabelle = Connector.getConnection().createStatement().executeQuery("SELECT * FROM pferd WHERE deleted=false ");
            while (tabelle.next()) {
                result.add(new PferdData(tabelle.getInt("id"), tabelle.getString("name"), tabelle.getInt("speedMin"), tabelle.getInt("speedMax"), tabelle.getString("foto")));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean senden(ArrayList<PferdData> daten) throws DaoException {
        try {
            PreparedStatement ps = Connector.getConnection().prepareStatement("INSERT INTO pferd (speedMin, speedMax, deleted, foto, name ) VALUES (?,?,?,?,?)");
            for (PferdData d : daten) {
                if (d.istPferd()) {
                    PferdData p = (PferdData)d;
                    if (p.getID() == null) {
                        ps.setInt(1, p.getSpeedMin());
                        ps.setInt(2, p.getSpeedMax());
                        ps.setBoolean(3, p.isDeleted());
                        ps.setString(4, p.getFoto());
                        ps.setString(5, p.getName());
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
    public boolean update(ArrayList<PferdData> daten) throws DaoException {
        ArrayList<PferdData> pferde = empfangen();
        ArrayList<Integer> pferdID = new ArrayList<Integer>();
        for (PferdData d : pferde) {
            pferdID.add(d.getID());
        }
        try {
            PreparedStatement ps = Connector.getConnection().prepareStatement("UPDATE pferd SET speedMin = ?, speedMax = ?, deleted = ?, foto = ?, name = ? WHERE id = ?");
            for(PferdData d : daten) {
                if (d.istPferd()) {
                    if (pferdID.contains(d.getID())) {
                        PferdData p = (PferdData) d;
                        ps.setInt(1, p.getSpeedMin() );
                        ps.setInt(2,p.getSpeedMax());
                        ps.setBoolean(3, p.isDeleted());
                        ps.setString(4, p.getFoto());
                        ps.setString(5, p.getName());
                        ps.setInt(6, p.getID());
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
    public boolean delete(ArrayList<PferdData> daten) throws DaoException {
        for (PferdData d : daten) {
            if (d.istPferd()) {
                PferdData p = (PferdData) d;
                p.delete();
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
