package dao.tests;

import dao.ReiterDAO;
import dao.impl.ReiterDAOJdbc;
import domain.Data;
import domain.ReiterData;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReiterDAOTest {

    @Test
    public void testEmpfangen() throws Exception {
        ReiterDAO r = new ReiterDAOJdbc();
        assertNotEquals(null, r.empfangen());
    }

    @Test
    public void testSenden() throws Exception {
        ReiterDAO r = new ReiterDAOJdbc();
        ArrayList<ReiterData> reiterListe = new ArrayList<ReiterData>();
        //soll geschrieben werden
        ReiterData reiter = new ReiterData(null, "Fanny", 1.2d);
        //soll nicht geschrieben werden
        ReiterData reiter2 = new ReiterData(1, "Lucy", 0.0d);
        reiterListe.add(reiter);
        reiterListe.add(reiter2);
        assertTrue(r.senden(reiterListe));
    }

    @Test
    public void testUpdate() throws Exception {
        ReiterDAO r = new ReiterDAOJdbc();
        ArrayList<ReiterData> reiterListe = new ArrayList<ReiterData>();
        //soll nicht upgedatet werden
        ReiterData reiter = new ReiterData(null, "Bob", 1.2d);
        //update, 1 - OliveSnook
        ReiterData reiter2 = new ReiterData(1, "Bobby", 1.1d);
        reiterListe.add(reiter);
        reiterListe.add(reiter2);
        assertTrue(r.update(reiterListe));
    }

    @Test
    public void testDelete() throws Exception {
        ReiterDAO r = new ReiterDAOJdbc();
        ArrayList<ReiterData> reiterListe = new ArrayList<ReiterData>();
        //loescht 2 . Doctor Who
        ReiterData reiter = new ReiterData(2, "Doctor Who", 0.0d);
        //loesct && update id - 34
        ReiterData reiter2 = new ReiterData(34, "Liv", 1.2d);
        //nichts
        ReiterData reiter3 = new ReiterData(null, "X", 0.0d);
        //nichts
        ReiterData reiter4 = new ReiterData(10000, "Tom", 10.2d);
        reiterListe.add(reiter);
        reiterListe.add(reiter2);
        reiterListe.add(reiter3);
        reiterListe.add(reiter4);
        assertTrue(r.delete(reiterListe));
    }
}