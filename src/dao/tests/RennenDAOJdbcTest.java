package dao.tests;

import dao.RennenDAO;
import dao.impl.RennenDAOJdbc;
import domain.Data;
import domain.RennenData;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RennenDAOJdbcTest {

    @Test
    public void testEmpfangen() throws Exception {
        RennenDAO r = new RennenDAOJdbc();
        assertNotEquals(null, r.empfangen());
    }

    @Test
    public void testSenden() throws Exception {
        RennenDAO r = new RennenDAOJdbc();
        ArrayList<RennenData> rennenList = new ArrayList<RennenData>();
        RennenData rennen = new RennenData(null, 1, 1, 0.0 );
        RennenData rennen2 = new RennenData(null, 2,2, 1.1);
        RennenData rennen3 = new RennenData(null, 3,3, 1.1);
        RennenData rennen4 = new RennenData(null, 4,4, 1.1);
        RennenData rennen5 = new RennenData(null, 5,5, 1.1);
        rennenList.add(rennen);
        rennenList.add(rennen2);
        rennenList.add(rennen3);
        rennenList.add(rennen4);
        rennenList.add(rennen5);
        assertTrue(r.senden(rennenList));
        RennenData rennenFalse = new RennenData(null, 1, 1, 10000.0);
        rennenList.add(rennenFalse);
        assertTrue(r.senden(rennenList));
    }

    @Test
    public void testUpdate() throws Exception {
        RennenDAO r = new RennenDAOJdbc();
        ArrayList<RennenData> rennen = new ArrayList<RennenData>();
        assertFalse(r.update(rennen));
    }

    @Test
    public void testDelete() throws Exception {
        RennenDAO r = new RennenDAOJdbc();
        ArrayList<RennenData> rennen = new ArrayList<RennenData>();
        assertFalse(r.delete(rennen));
    }
}