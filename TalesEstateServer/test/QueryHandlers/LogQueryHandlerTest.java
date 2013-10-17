/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class LogQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    public LogQueryHandlerTest(String testName) {
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
     * Test of logBuildingBuilt method, of class LogQueryHandler.
     */
    public void testLogBuildingBuilt() {
        System.out.println("Testing logBuildingBuilt()");
        int characterID = 1;
        int buildingID = 1;
        int plotID = 1;
        Date date = new Date();
        
        LogQueryHandler instance = new LogQueryHandler(con);
        
        instance.logBuildingBuilt(characterID, plotID, buildingID, date);
    }

    /**
     * Test of getPlotLog method, of class LogQueryHandler.
     */
    public void testGetPlotLog() {
        System.out.println("getPlotLog");
        int number = 0;
        int PlotID = 0;
        LogQueryHandler instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getPlotLog(number, PlotID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCharacterLog method, of class LogQueryHandler.
     */
    public void testGetCharacterLog() {
        System.out.println("getCharacterLog");
        int number = 0;
        int CharaterID = 0;
        LogQueryHandler instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getCharacterLog(number, CharaterID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CharacterLog method, of class LogQueryHandler.
     */
    public void testCharacterLog() {
        System.out.println("CharacterLog");
        int characterID = 0;
        String description = "";
        LogQueryHandler instance = null;
        instance.CharacterLog(characterID, description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of PlotLog method, of class LogQueryHandler.
     */
    public void testPlotLog() {
        System.out.println("PlotLog");
        int plotID = 0;
        String description = "";
        int UserID = 0;
        LogQueryHandler instance = null;
        instance.PlotLog(plotID, description, UserID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
