package dao.impl;

import dao.Connector;
import dao.DaoException;
import dao.RennenDAO;
import domain.Data;
import domain.RennenData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Rebeka on 2015.03.16..
 */
public class RennenDAOJdbc implements RennenDAO {

    private static final Logger log = LogManager.getLogger(RennenDAOJdbc.class);

    @Override
    public ArrayList<RennenData> empfangen() throws DaoException {
        ArrayList<RennenData> result = new ArrayList<RennenData>();
        try {
            ResultSet tabelle = Connector.getConnection().createStatement().executeQuery("SELECT *FROM rennen");
            while (tabelle.next()) {
                result.add(new RennenData(tabelle.getInt("id"), tabelle.getInt("pID"), tabelle.getInt("rID"), tabelle.getDouble("ergebnis"), tabelle.getDouble("luck"), tabelle.getDouble("skill"), tabelle.getInt("rank"), tabelle.getDouble("tempo") ));

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public boolean senden(ArrayList<RennenData> daten) throws DaoException {
        try {
            PreparedStatement ps = Connector.getConnection().prepareStatement("INSERT INTO rennen(id, pID, rID, ergebnis, luck, skill, rank, tempo) VALUES (?,?,?,?,?,?,?,?)");
            ResultSet rsID = Connector.getConnection().createStatement().executeQuery("SELECT max(id) FROM rennen");
            rsID.next();
            int id = rsID.getInt(1) + 1;
            ArrayList<Integer> pID = new ArrayList<Integer>();
            ArrayList<Integer> rID = new ArrayList<Integer>();
            for (RennenData d : daten) {
                if (d.istRennen()) {
                    RennenData r = (RennenData) d;
                    if (!pID.contains(r.getPID()) && !rID.contains(r.getRID())) {
                        if (r.getID() == null) {
                            rID.add(r.getRID());
                            pID.add(r.getPID());
                            ps.setInt(1, id);
                            ps.setInt(2, r.getPID());
                            ps.setInt(3, r.getRID());
                            ps.setDouble(4, r.getErgebnis());
                            ps.setDouble(5, r.getLuck());
                            ps.setDouble(6, r.getSkill());
                            ps.setInt(7, r.getRank());
                            ps.setDouble(8, r.getTempo());
                            ps.execute();
                        }
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
    public boolean update(ArrayList<RennenData> daten) {
       /* ArrayList<Data> rennen = empfangen();
        ArrayList<Integer> rennenID = new ArrayList<Integer>();
        ArrayList<Integer> pferdID = new ArrayList<Integer>();
        ArrayList<Integer> reiterID = new ArrayList<Integer>();
        for (Data d : rennen) {
            if (d.istRennen()) {
                RennenData r = (RennenData) d;
                rennenID.add(r.getID());
                pferdID.add(r.getPID());
                reiterID.add(r.getRID());
            }
        }
        try {
            PreparedStatement ps = Connector.getConnection().prepareStatement("UPDATE  rennen SET ergebnis = ? WHERE  id = ? AND rID = ? AND pID = ?");
            for (Data d : daten) {
                if (d.istReiter()) {
                    if (reiterID.contains(d.getID())) {
                        RennenData r = (RennenData) d;
                        for (int i = 0; i < rennenID.size(); i++) {
                            if (r.getID() == rennenID.get(i)) {
                                if (r.getPID() == pferdID.get(i) && r.getRID() == reiterID.get(i)) {
                                    ps.setDouble(1, r.getErgebnis());
                                    ps.setInt(2, r.getID());
                                    ps.setInt(3, r.getRID());
                                    ps.setInt(4, r.getPID());
                                    ps.execute();
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error getConnection");
            return false;
        }*/
        return false;
    }

    @Override
    public boolean delete(ArrayList<RennenData> daten) {
        return false;
    }
}
