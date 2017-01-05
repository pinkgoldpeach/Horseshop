package dao.tests;

import dao.Connector;
import dao.DaoException;
import dao.PferdDAO;
import dao.impl.PferdDAOJdbc;
import domain.Data;
import domain.PferdData;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PferdDAOJdbcTest {

   static  Connection c;

    @BeforeClass
    public static void begin() {
        try {
            c = Connector.getConnection();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    /**
     * empfangen()
     * @throws Exception
     */
    @Test
    public void testEmpfangen() throws Exception {
        PferdDAO p = new PferdDAOJdbc();
        assertNotEquals(null, p.empfangen());
    }

    /**
     * senden()
     * @throws Exception
     */
    @Test
    public void testSenden() throws Exception {
        PferdDAO p = new PferdDAOJdbc();
        PferdData pferd = new PferdData(null, "Rainbow Dash", 50, 100, "irgendwo.jpeg");
        ArrayList<PferdData> pferdListe = new ArrayList<PferdData>();
         pferdListe.add(pferd);
        pferdListe.add(pferd);
        pferdListe.add(pferd);
        pferdListe.add(pferd);
        pferdListe.add(pferd);
        pferdListe.add(pferd);
        pferdListe.add(pferd);
        assertTrue( p.senden(pferdListe));
    }

    /**
     * update()
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
       PferdDAO p = new PferdDAOJdbc();
        PferdData pferdTwilight = new PferdData(null, "Twilight", 50, 100, "irgendwo.jpeg");
        PferdData pferdSparkle = new PferdData(33, "Sparkle", 50, 100, "irgendwo.jpeg");
        PferdData pferdPinkiePie = new PferdData(34, "Pinkie Pie", 50, 100, "irgendwo.jpeg");
        ArrayList<PferdData> pferdListe = new ArrayList<PferdData>();
        pferdListe.add(pferdTwilight);
        pferdListe.add(pferdSparkle);
        pferdListe.add(pferdPinkiePie);
        assertTrue(p.update(pferdListe));
    }

    /**
     * delete()
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        PferdDAO p = new PferdDAOJdbc();
        PferdData pferdTwilight = new PferdData(null, "Twilight", 50, 100, "irgendwo.jpeg");
        PferdData pferdRaimnowDash = new PferdData(35, "RainbowDash", 50, 100, "irgendwo.jpeg");
        PferdData pferdSparkle = new PferdData(36, "Sparkle", 50, 100, "irgendwo.jpeg");
        ArrayList<PferdData> pferdListe = new ArrayList<PferdData>();
        pferdListe.add(pferdTwilight);
        pferdListe.add(pferdRaimnowDash);
        pferdListe.add(pferdSparkle);
        assertTrue(p.delete(pferdListe));
    }
}