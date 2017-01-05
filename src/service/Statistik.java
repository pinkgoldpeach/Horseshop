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
 * Berechnet Statistiken
 */
public class Statistik {

    private static final Logger log = LogManager.getLogger(Statistik.class);

    /**
     * schnellste/langsamste Pferd
     * @param max : schnellste?
     * @return
     */
    public static PferdData bestHorse(boolean max) throws ServiceException {
        PferdDAO pDAO = new PferdDAOJdbc();
        ArrayList<PferdData> daten = null;
        try {
            daten = pDAO.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<Integer> speed = new ArrayList<Integer>();
        int result = 101;
        if (max) {
            result = 0;
        }
        for (int i = 0; i < size; i++) {
            if (daten.get(i).istPferd()) {
                PferdData pferd = (PferdData) daten.get(i);
                int x = (pferd.getSpeedMax() + pferd.getSpeedMin()) / 2;
                speed.add(x);
                if (max) {
                    if (x > result) {
                        result = x;
                    }
                } else {
                    if (x < result) {
                        result = x;
                    }
                }
            } else {
                speed.add(-1);
            }
        }
        if (result == 0) {
            return null;
        }
       int pferdIndex = speed.indexOf(result);
        Data dataResult = daten.get(pferdIndex);
        PferdData pferdResult = (PferdData) dataResult;
        return pferdResult;
    }

    /**
     * Reiter mit hoehste/niedrigsten 'skill'
     * @param max : hoehste?
     * @return
     */
    public static ReiterData bestJockey(boolean max) throws ServiceException {
        ReiterDAO rDAO = new ReiterDAOJdbc();
        ArrayList<ReiterData> daten = null;
        try {
            daten = rDAO.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<Double> skill = new ArrayList<Double>();
        Double result = null;
        for (int i = 0; i < size; i++) {
            if (daten.get(i).istReiter()) {
                ReiterData reiter = (ReiterData) daten.get(i);
                skill.add(reiter.getSkill());
                if (result == null) {
                    result = reiter.getSkill();
                }
                if (max) {
                    if (reiter.getSkill() > result) {
                        result = reiter.getSkill();
                    }
                }else {
                    if (reiter.getSkill() < result) {
                        result = reiter.getSkill();
                    }
                }
            } else {
                skill.add(null);
            }
        }
        int reiterIndex = skill.indexOf(result);
        Data dataResult = daten.get(reiterIndex);
        ReiterData reiterResult = (ReiterData) dataResult;
        return reiterResult;
    }

    /**
     * Schnellste/Langsamste Zeitergebnis von den Rennnen
     * @param max :  schnellste?
     * @return
     */
    public static RennenData fastestTurn(boolean max) throws ServiceException {
        RennenDAO rDAO = new RennenDAOJdbc();
        ArrayList<RennenData> daten = null;
        try {
            daten = rDAO.empfangen();
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        int size = daten.size();
        ArrayList<Double> ergebnisse = new ArrayList<Double>();
        Double result = null;
        for (int i = 0; i < size; i++) {
            if (daten.get(i).istRennen()) {
                RennenData rennen = (RennenData) daten.get(i);
                ergebnisse.add(rennen.getErgebnis());
                if (result == null) {
                    result = rennen.getErgebnis();
                }
                if (max) {
                    if (rennen.getErgebnis() > result) {
                        result = rennen.getErgebnis();
                    }
                }else {
                    if (rennen.getErgebnis() < result) {
                        result = rennen.getErgebnis();
                    }
                }
                } else {
                    ergebnisse.add(-1.0);
                }
            }
            int rennenIndex = ergebnisse.indexOf(result);
            Data dataResult = daten.get(rennenIndex);
            RennenData rennenResult = (RennenData) dataResult;
            return rennenResult;
    }

    /**
     * Erreichte Positionen einer Pferd-Reiter Gruppe
     * @param pferd
     * @param reiter
     * @return
     */
    public static ArrayList<String> ranking(PferdData pferd, ReiterData reiter) throws ServiceException {
        ArrayList<RennenData> r = null;
        try {
            r = Suchen.findRennen(reiter.getID(), pferd.getID());
        } catch (ServiceException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        if (r.isEmpty()) {
            ArrayList<String> res = new ArrayList<>();
            res.add("There is no Turn with such Pony and Jockey ID :(");
            return res;
        }
        ArrayList<RennenData> turn = new ArrayList<>();
        ArrayList<Integer> ranking = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for (RennenData rennen : r) {
           turn = Suchen.findRaceID(rennen.getID());
           int better = 0;
            for (RennenData current: turn) {
                if (current.getErgebnis() < rennen.getErgebnis()) {
                    better++;
                }
            }
            ranking.add(better+1);
        }
        int position = 1;
        while (!ranking.isEmpty()) {
            int count = 0;
            for (int i = 0; i < ranking.size(); i++) {
                if (ranking.get(i) == position) {
                    count++;
                    ranking.remove(i);
                    i--;
                }
            }
            result.add(position + ". position: "  + count + "X");
            position++;
        }
        return result;
    }
}