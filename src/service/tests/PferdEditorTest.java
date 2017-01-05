package service.tests;

import dao.PferdDAO;
import dao.impl.PferdDAOJdbc;
import domain.PferdData;
import org.junit.Test;
import service.PferdEditor;
import service.Suchen;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PferdEditorTest {

    @Test
    public void testCreate() throws Exception {
        PferdData testPferd = new PferdData(null, "test12345852314", 50, 60, "nothing");
        PferdEditor.create("test12345852314", 50, 60, "nothing");
        ArrayList<PferdData> daten = Suchen.findHorse(testPferd.getName());
        PferdData created = daten.get(0);
        assertTrue(testPferd.getName().equals(created.getName()));
        assertTrue(testPferd.getSpeedMin() == created.getSpeedMin());
        assertTrue(testPferd.getFoto().equals(created.getFoto()));
        assertTrue(testPferd.getSpeedMax() == created.getSpeedMax());

    }

    @Test
    public void testUpdate() throws Exception {
        PferdDAO pDAO = new PferdDAOJdbc();
        ArrayList<PferdData> pferde = pDAO.empfangen();
        PferdData pferd = pferde.get(0);
        int oldMinSpeed = pferd.getSpeedMin();
        int oldMaxSpeed = pferd.getSpeedMax();
        int minSpeed = oldMinSpeed++;
        int maxSpeed = oldMaxSpeed++;
        assertTrue(PferdEditor.update(pferd.getID(), pferd.getName(), minSpeed, maxSpeed, pferd.getFoto()));
        PferdData updated = Suchen.findHorseID(pferd.getID());
        assertTrue(updated.getSpeedMax() == maxSpeed);
        assertTrue(updated.getSpeedMin() == minSpeed);
    }

    @Test
    public void testDelete() throws Exception {
        PferdDAO pDAO = new PferdDAOJdbc();
        ArrayList<PferdData> pferde = pDAO.empfangen();
        PferdData pferd = pferde.get(0);
        assertTrue(PferdEditor.delete(pferd.getID()));
        assertTrue(Suchen.findHorseID(pferd.getID()) == null);
    }
}