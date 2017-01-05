package service;

import dao.DaoException;
import dao.RennenDAO;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Rebeka on 2015.03.19..
 * Fuehrt eine Rennsimulation aus
 */
public class Simulation {

    private static final Logger log = LogManager.getLogger(Simulation.class);

    public static ArrayList<RennenData> simulation(ArrayList<PferdData> pferde, ArrayList<ReiterData> reiter) throws ServiceException {
       RennenDAO rDAO = new RennenDAOJdbc();
        int size = Math.min(pferde.size(), reiter.size());
        ObservableList<RennenData> r = FXCollections.observableArrayList();
        ArrayList<RennenData> result = new ArrayList<RennenData>();
        ArrayList<PferdData> pferdeRennen = new ArrayList<>();
        ArrayList<ReiterData> reiterRennen = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < size; i++) {
            if (!pferdeRennen.contains(pferde.get(i)) && !reiterRennen.contains(reiter.get(i))) {
                double koennen = 1 + (0.15 * (1 / Math.PI) * Math.atan(0.2 * reiter.get(i).getSkill()));
                double speed = (rd.nextInt(Math.abs(pferde.get(i).getSpeedMin() - pferde.get(i).getSpeedMax()))) + Math.min(pferde.get(i).getSpeedMin(), pferde.get(i).getSpeedMax());
                double luck = (rd.nextDouble() * 0.1) + 0.95;
                double ergebnis = koennen * luck * speed;
                RennenData rennen = new RennenData(null, pferde.get(i).getID(), reiter.get(i).getID(), ergebnis);
                rennen.setLuck(luck);
                rennen.setSkill(koennen);
                rennen.setTempo(speed);
                pferdeRennen.add(pferde.get(i));
                reiterRennen.add(reiter.get(i));
                result.add(rennen);
                r.add(rennen);
            }
        }
        ranking(result);
        try {
            rDAO.senden(result);
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
        return result;
    }

    private static void ranking(List<RennenData> rennen) {
        double rank = -1;
        Collections.sort(rennen);
        for (int i = rennen.size(); i >0; i--) {
            rennen.get(i-1).setRank(i);
        }
    }
}