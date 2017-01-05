package service.tests;

import dao.DaoException;
import dao.PferdDAO;
import dao.ReiterDAO;
import dao.impl.PferdDAOJdbc;
import dao.impl.ReiterDAOJdbc;
import domain.PferdData;
import domain.ReiterData;
import domain.RennenData;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.Simulation;

import javax.xml.ws.Service;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SimulationTest {

    ArrayList<ReiterData> reiter;
    ArrayList<PferdData> pferde;

    @Before
    public void begin() {
        PferdDAO pDAO = new PferdDAOJdbc();
        ReiterDAO rDAO = new ReiterDAOJdbc();
        /*PferdData pferd1 = new PferdData(null, "Lou", 56, 86, "pictures\twilight.png");
        PferdData pferd2 = new PferdData(null, "May", 66, 76, "pictures\twilight.png");
        ReiterData reiter1 = new ReiterData(null, "Olive", 45.0);
        ReiterData reiter2 = new ReiterData(null, "Tom", -0.2564);
       reiter  = new ArrayList<>();
        reiter.add(reiter1);
        reiter.add(reiter2);
        try {
            rDAO.senden(reiter);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        pferde = new ArrayList<>();
        pferde.add(pferd1);
        pferde.add(pferd2);
        try {
            pDAO.senden(pferde);
        } catch (DaoException e) {
            e.printStackTrace();
        }*/
        try {
            reiter = rDAO.empfangen();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        try {
            pferde = pDAO.empfangen();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSimulation() throws Exception {
        ArrayList<RennenData> result = Simulation.simulation(pferde, reiter);
        assertTrue(result.size() == (Math.min(reiter.size(), pferde.size())));

        int ranking = 0;
        boolean tempo = true;
        boolean luck = true;
        for (RennenData rennen : result) {
            if (rennen.getTempo() < 50 || rennen.getTempo() > 100) {
                tempo = false;
            }
            if (rennen.getLuck() < 0.95 || rennen.getLuck() > 1.05) {
                luck = false;
            }
            for (RennenData current : result) {
                if (current.getRank() == rennen.getRank()) {
                    ranking++;
                }
            }
            }
        assertEquals(ranking, result.size());
        assertTrue(tempo);
        assertTrue(luck);
    }
}