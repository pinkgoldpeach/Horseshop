package service;

import dao.DaoException;
import dao.ReiterDAO;
import dao.impl.ReiterDAOJdbc;
import domain.Data;
import domain.ReiterData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Rebeka on 2015.03.19..
 */
public class ReiterEditor implements Editor {

    private static final Logger log = LogManager.getLogger(ReiterEditor.class);

    @Override
    public ReiterData create() throws ServiceException {
        ReiterDAO rDAO = new ReiterDAOJdbc();
        ReiterData reiter = new ReiterData(null, "no name :(", 0.0);
        ArrayList<ReiterData> r = new ArrayList<ReiterData>();
        r.add(reiter);
        try {
            rDAO.senden(r);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return reiter;
    }

    public ReiterData create(String name) throws ServiceException {
        ReiterDAO rDAO = new ReiterDAOJdbc();
        ReiterData reiter = new ReiterData(null, name, 0.0);
        ArrayList<ReiterData> r = new ArrayList<ReiterData>();
        r.add(reiter);
        try {
            rDAO.senden(r);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return reiter;
    }

    public ReiterData create(double skill) throws ServiceException {
        ReiterDAO rDAO = new ReiterDAOJdbc();
        ReiterData reiter = new ReiterData(null, "no name :(", skill);
        ArrayList<ReiterData> r = new ArrayList<ReiterData>();
        r.add(reiter);
        try {
            rDAO.senden(r);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return reiter;
    }

    public static ReiterData create(String name, double skill) throws ServiceException {
        ReiterDAO rDAO = new ReiterDAOJdbc();
        ReiterData reiter = new ReiterData(null, name, skill);
        ArrayList<ReiterData> r = new ArrayList<ReiterData>();
        r.add(reiter);
        try {
            rDAO.senden(r);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return reiter;
    }

    public static boolean update(int id, String name, double skill) throws ServiceException {
        ReiterDAO r = new ReiterDAOJdbc();
        ArrayList<ReiterData> daten = null;
        try {
            daten = r.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        ArrayList<ReiterData> toUpdate = new ArrayList<>();
        for (Data d : daten) {
            if (d.istReiter() && d.getID() == id) {
                ReiterData reiter = new ReiterData(id, name, skill);
                toUpdate.add(reiter);
            }
        }
        try {
            if (r.update(toUpdate) ) {
                return true;
            }
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return false;
    }

    public static boolean delete(int id) throws ServiceException {
        ReiterDAO r = new ReiterDAOJdbc();
        ArrayList<ReiterData> daten = null;
        try {
            daten = r.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        for (ReiterData d : daten) {
            if (d.istReiter()) {
                if (d.getID() == id) {
                    ArrayList<ReiterData> toDelete = new ArrayList<ReiterData>();
                    toDelete.add(d);
                    try {
                        r.delete(toDelete);
                    } catch (DaoException e) {
                        log.error(e.getMessage());
                        throw new ServiceException(e);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
