/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class DatabaseConnectionTest extends TestCase {

    public DatabaseConnectionTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of openConnectionProd method, of class DatabaseConnection.
     */
    public void testOpenConnectionProd() {
        System.out.println("openConnectionProd");
        DatabaseConnection instance = new DatabaseConnection();
        Connection expResult = null;
        Connection result = instance.openConnectionProd();
        assertFalse(expResult == result);

    }

    /**
     * Test of openConnectionEstate method, of class DatabaseConnection.
     */
    public void testOpenConnectionEstate() {
        System.out.println("openConnectionEstate");
        DatabaseConnection instance = new DatabaseConnection();
        Connection expResult = null;
        Connection result = instance.openConnectionEstate();
        assertFalse(expResult == result);
    }

    /**
     * Test of closeConnection method, of class DatabaseConnection.
     */
    public void testCloseConnection() {
        System.out.println("closeConnection");
        DatabaseConnection instance = new DatabaseConnection();
        instance.closeConnection();
    }

    /**
     * Test of executeQuery method, of class DatabaseConnection.
     */
    public void testExecuteQuery() {
        System.out.println("executeQuery");
        String inSQL = "Select DuchyName from Duchy";
        DatabaseConnection instance = new DatabaseConnection();
        instance.openConnectionEstate();
        ArrayList<String[]> result = instance.executeQuery(inSQL);

        assertEquals("Thegnheim", result.get(0)[0]);
        assertEquals("Sarkland", result.get(1)[0]);
        assertEquals("Langzerund", result.get(5)[0]);
    }
}
