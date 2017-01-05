package service;

import dao.DaoException;
import dao.PferdDAO;
import dao.ReiterDAO;
import dao.RennenDAO;
import dao.impl.PferdDAOJdbc;
import dao.impl.ReiterDAOJdbc;
import dao.impl.RennenDAOJdbc;
import domain.Data;
import domain.PferdData;
import domain.ReiterData;
import domain.RennenData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Rebeka on 2015.03.19..
 */
public class Suchen {

    private static final Logger log = LogManager.getLogger(Suchen.class);

    /**
     * Sucht Pferd nach Name
     * @param name
     * @return
     */
    public static ArrayList<PferdData> findHorse(String name) throws ServiceException {
        PferdDAO p = new PferdDAOJdbc();
        ArrayList<PferdData> daten = null;
        try {
            daten = p.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<PferdData> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PferdData pferd = (PferdData) daten.get(i);
            if (pferd.getName().equals(name)) {
                result.add(pferd);
            }
        }
        return result;
    }

    /**
     * Sucht Pferd nach Foto (Pfad)
     * @param foto
     * @return
     */
    public static ArrayList<PferdData> findHorsePhoto(String foto) throws ServiceException {
        PferdDAO p = new PferdDAOJdbc();
        ArrayList<PferdData> daten = null;
        try {
            daten = p.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<PferdData> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PferdData pferd = (PferdData) daten.get(i);
            if (pferd.getFoto() != null && pferd.getFoto().equals(foto)) {
                result.add(pferd);
            }
        }
        return result;
    }

    /**
     * Sucht schnellstes/langsamstes Pferd
     * @param fastest : schnellste?
     * @return
     */
    public static PferdData findFastestHorse(boolean fastest) throws ServiceException {
        try {
            return Statistik.bestHorse(fastest);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Sucht Pferd nach ID
     * @param id
     * @return
     */
    public static  PferdData findHorseID(int id) throws ServiceException {
        PferdDAO p = new PferdDAOJdbc();
        ArrayList<PferdData> daten = null;
        try {
            daten = p.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        for (PferdData d : daten) {
            if (d.istPferd()) {
                PferdData pferd = (PferdData) d;
                if (pferd.getID() == id) {
                    return pferd;
                }
            }
        }
        return null;
    }

    /**
     * Gibt alle Pferde zurueck
     * @return
     */
    public static ArrayList<PferdData> allHorses() throws ServiceException {
        PferdDAO p = new PferdDAOJdbc();
        ArrayList<PferdData> daten = null;
        try {
            daten = p.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<PferdData> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PferdData pferd = (PferdData) daten.get(i);
            result.add(pferd);
        }
        return result;
    }

    /**
     * Gibt alle Pferde ausser den gegebenen zurueck
     * @param pferde : nicht verfuegbare Pferde
     * @return
     */
    public static ArrayList<PferdData> availableHorses(ArrayList<PferdData> pferde) throws ServiceException {
        ArrayList<PferdData> result = null;
        try {
            result = allHorses();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        if (pferde == null) {
            return result;
        }
        int size = result.size();
        for (int i = 0; i < size; i++ ) {
            int resultID = result.get(i).getID();
            for (PferdData p : pferde) {
                int rID = p.getID();
                if (rID == resultID) {
                    result.remove(i);
                    size--;
                    i--;
                    continue;
                }
            }
        }
        return result;
    }

    /**
     * Sucht Reiter nach Name
     * @param name
     * @return
     */
    public static ArrayList<ReiterData> findJockey(String name) throws ServiceException {
        ReiterDAO r = new ReiterDAOJdbc();
        ArrayList<ReiterData> daten = null;
        try {
            daten = r.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<ReiterData> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ReiterData reiter = (ReiterData) daten.get(i);
            if (reiter.getName().equals(name)) {
                result.add(reiter);
            }
        }
        return result;
    }

    /**
     * Sucht faehigsten/unfaehigsten Reiter
     * @param best : faehigste?
     * @return
     */
    public static ReiterData findBestJockey(boolean best) throws ServiceException {
        try {
            return Statistik.bestJockey(best);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Gibt alle Reiter zurueck
     * @return
     */
    public static ArrayList<ReiterData> allJockeys() throws ServiceException {
        ReiterDAO p = new ReiterDAOJdbc();
        ArrayList<ReiterData> daten = null;
        try {
            daten = p.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<ReiterData> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ReiterData r = (ReiterData) daten.get(i);
            result.add(r);
        }
        return result;
    }

    /**
     * Gibt alle Reiter ausser den gegebenen zurueck
     * @param reiter : nicht verfuegbare Reiter
     * @return
     */
    public static ArrayList<ReiterData> availableJockey(ArrayList<ReiterData> reiter) throws ServiceException {
        ArrayList<ReiterData> result = null;
        try {
            result = allJockeys();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        if (reiter == null) {
            return result;
        }
        int size = result.size();
        for (int i = 0; i < size; i++ ) {
            int resultID = result.get(i).getID();
            for (ReiterData r : reiter) {
                int rID = r.getID();
                if (rID == resultID) {
                    result.remove(i);
                    size--;
                    i--;
                    continue;
                }
            }
        }
        return result;
    }

    /**
     * Sucht Reiter nach ID
     * @param id
     * @return
     */
    public static ReiterData findJockeyID(int id) throws ServiceException {
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
                ReiterData reiter = (ReiterData) d;
                if (reiter.getID() == id) {
                    return reiter;
                }
            }
        }
        return null;
    }

    /**
     * Sucht die Rennen, wo der gegebene Reiter mit dem gegebenen Pferd teilgenommen hat
     * @param jockey
     * @param horse
     * @return
     */
    public static ArrayList<RennenData> findRennen(int jockey, int horse) throws ServiceException {
        RennenDAO r = new RennenDAOJdbc();
        ArrayList<RennenData> daten = null;
        try {
            daten = r.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        ArrayList<RennenData> result = new ArrayList<RennenData>();
        for (RennenData d : daten) {
            if (d.istRennen()) {
                RennenData rennen = (RennenData) d;
                if (rennen.getRID() == jockey && rennen.getPID() == horse) {
                    result.add(rennen);
                }
            }
        }
        return result;
    }

    /**
     * Sucht ein Rennen nach ID
     * @param id
     * @return
     */
    public static  ArrayList<RennenData> findRaceID(int id) throws ServiceException {
        RennenDAO r = new RennenDAOJdbc();
        ArrayList<RennenData> daten = null;
        try {
            daten = r.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        ArrayList<RennenData> result = new ArrayList<>();
        for (RennenData d : daten) {
            if (d.istRennen()) {
                RennenData rennen = (RennenData) d;
                if (rennen.getID() == id) {
                    result.add(rennen);
                }
            }
        }
        return result;
    }

    /**
     * Gibt alle Rennen zurueck
     * @return
     */
    public static ArrayList<RennenData> allRaces() throws ServiceException {
        RennenDAO r = new RennenDAOJdbc();
        ArrayList<RennenData> daten = null;
        try {
            daten = r.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        ArrayList<RennenData> result = new ArrayList<>();
        for (RennenData d : daten) {
            if (d.istRennen()) {
                RennenData rennen = (RennenData) d;
                result.add(rennen);
            }
        }
        return result;
    }
}
