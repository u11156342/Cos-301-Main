/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class LogQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    private int testPlotID = 0;
    
    public LogQueryHandlerTest(String testName) {
        super(testName);
        
        //Initialize test variables
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM UserCharacter WHERE "
                    + "UserCharacterName LIKE 'test character%'");
            rs.next();
            testCharID = Integer.parseInt(rs.getString("UserCharacterID"));
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotOwnedBy = "
                    + testCharID);
            rs.next();
            testPlotID = Integer.parseInt(rs.getString("PlotID"));
        }
        catch(Exception e) {
            System.out.println("Error in BuildingQueryHandlerTEST constructor");
            System.out.println(e.getMessage());
        }
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
        
        int characterID = testCharID;
        int buildingID = 1;
        int plotID = testPlotID;
        Date date = new Date();
        boolean added = false;
        
        LogQueryHandler instance = new LogQueryHandler(con);
        instance.logBuildingBuilt(characterID, plotID, buildingID, date);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM BuildLog WHERE BuildLogCharacterID = " +
                    characterID + " AND BuildLogPlotID = " + plotID);
            
            if(rs.next())
                added = true;
            
        }
        catch(Exception e) {
            System.out.println("Error in testLogBuildingBuilt()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(added);
    }

    /**
     * Test of getPlotLog method, of class LogQueryHandler.
     */
    public void testGetPlotLog() {
        System.out.println("Testing getPlotLog()");
        
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        ++month;  //Calendar month-index starts at 0
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String sDay, sMonth;
        
        if(day < 10)
            sDay = "0" + day;
        else
            sDay = Integer.toString(day);
        
        if(month < 10)
            sMonth = "0" + month;
        else
            sMonth = Integer.toString(month);
        
        String date = year + "-" + sMonth + "-" + sDay + " 00:00:00";
        int plotID = testPlotID;
        int charID = testCharID;
        LogQueryHandler instance = new LogQueryHandler(con);
        
        try {
            stmt = con.createStatement();
            stmt.execute("INSERT INTO PlotLog VALUES ("
                    + plotID + ", " + charID + ", '" + date + "', 'test plot'");
        }
        catch(Exception e) {
            System.out.println("Error in testGetPlotLog()");
            System.out.println(e.getMessage());
        }
        
        boolean added = false;
        ArrayList<String[]> result = instance.getPlotLog(month, plotID);
        String[] one = result.get(0);
        System.out.println("ONE STARTS HERE");
        System.out.println(one[0]);
        System.out.println(one[1]);
        System.out.println(one[2]);
        System.out.println(one[3]);
        
        if(one[0].equals(Integer.toString(plotID)) && one[1].equals(Integer.toString(charID)) &&
                one[2].equals(date) && one[3].equals("test plot"))
            added = true;
        
        assertTrue(added);
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
