package service;

import dao.DaoException;
import dao.PferdDAO;
import dao.impl.PferdDAOJdbc;
import domain.Data;
import domain.PferdData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
/**
 * Created by Rebeka on 2015.03.19..
 */
public class PferdEditor implements Editor {

    private static final Logger log = LogManager.getLogger(PferdEditor.class);

    @Override
    public PferdData create() throws ServiceException {
        PferdDAO pDAO = new PferdDAOJdbc();
        PferdData pferd = new PferdData(null, "no name :( ", 50, 100, "kein Foto vorhanden");
        ArrayList<PferdData> p = new ArrayList<PferdData>();
        p.add(pferd);
        try {
            pDAO.senden(p);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return pferd;
    }

    @Override
    public PferdData create(String name) throws ServiceException {
        PferdDAO pDAO = new PferdDAOJdbc();
       PferdData pferd = new PferdData(null, name, 50, 100, "kein Foto vorhanden");
        ArrayList<PferdData> p = new ArrayList<PferdData>();
        p.add(pferd);
        try {
            pDAO.senden(p);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return pferd;
    }

    public static PferdData create(String name, int speedMin, int speedMax) throws ServiceException {
        PferdDAO pDAO = new PferdDAOJdbc();
        if (speedMin < 50 ) {
            speedMin = 50;
        }
        if (speedMin > 100) {
            speedMin = 100;
        }
        if (speedMax < 50 ) {
            speedMax = 50;
        }
        if (speedMax > 100) {
            speedMax = 100;
        }
        PferdData pferd = new PferdData(null, name, Math.min(speedMin, speedMax), Math.max(speedMin, speedMax), "kein Foto vorhanden");
        ArrayList<PferdData> p = new ArrayList<PferdData>();
        p.add(pferd);
        try {
            pDAO.senden(p);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return pferd;
    }

    public static PferdData create(String name, int speedMin, int speedMax, String foto) throws ServiceException {
        PferdDAO pDAO = new PferdDAOJdbc();
        if (speedMin < 50 ) {
            speedMin = 50;
        }
        if (speedMin > 100) {
            speedMin = 100;
        }
        if (speedMax < 50 ) {
            speedMax = 50;
        }
        if (speedMax > 100) {
            speedMax = 100;
        }
        PferdData pferd = new PferdData(null, name, Math.min(speedMin, speedMax), Math.max(speedMin, speedMax), foto);
        ArrayList<PferdData> p = new ArrayList<PferdData>();
        p.add(pferd);
        try {
            pDAO.senden(p);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return pferd;
    }

     public static boolean update(int id, String name, int minSpeed, int maxSpeed, String photo) throws ServiceException {
        PferdDAO p = new PferdDAOJdbc();
         ArrayList<PferdData> daten = null;
         try {
             daten = p.empfangen();
         } catch (DaoException e) {
             log.error(e.getMessage());
             throw new ServiceException(e);
         }
         ArrayList<PferdData> toUpdate = new ArrayList<>();
         for (PferdData d : daten ) {
             if (d.istPferd() && d.getID() == id) {
                 PferdData pferd = new PferdData(id, name, minSpeed, maxSpeed, photo);
                 toUpdate.add(pferd);
             }
         }
         try {
             if ( p.update(toUpdate)) {
                 return true;
             }
         } catch (DaoException e) {
             log.error(e.getMessage());
             throw new ServiceException(e);
         }
         return false;
    }


    public static boolean delete(int id) throws ServiceException {
        PferdDAO p = new PferdDAOJdbc();
        PferdData pferd = Suchen.findHorseID(id);
        if (pferd != null) {
            ArrayList<PferdData> toDelete = new ArrayList<>();
            toDelete.add(pferd);
            try {
                p.delete(toDelete);
            } catch (DaoException e) {
                log.error(e.getMessage());
                throw new ServiceException(e);
            }
            return true;
        }
        return false;
    }
}
