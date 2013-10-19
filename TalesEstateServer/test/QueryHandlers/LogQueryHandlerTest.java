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
        
        int buildingID = 1;
        int plotID = testPlotID;
        Date date = new Date();
        boolean added = false;
        
        LogQueryHandler instance = new LogQueryHandler(con);
        instance.logBuildingBuilt(plotID, buildingID, date);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM BuildLog WHERE "
                    + "BuildLogPlotID = " + plotID);
            
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
        
        String date = year + "-" + sMonth + "-" + sDay;
        int plotID = testPlotID;
        LogQueryHandler instance = new LogQueryHandler(con);
        
        boolean added = false;
        ArrayList<String[]> result = instance.getPlotLog(month, plotID);
        String[] one = result.get(0);

        if(one[0].equals(Integer.toString(plotID)) &&
                one[1].substring(0, one[1].indexOf(" ")).equals(date) && one[2].equals("test plot"))
            added = true;
        
        assertTrue(added);
    }

    /**
     * Test of getCharacterLog method, of class LogQueryHandler.
     */
    public void testGetCharacterLog() {
        System.out.println("Testing getCharacterLog()");
        
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
        
        String date = year + "-" + sMonth + "-" + sDay;

        int characterID = testCharID;
        LogQueryHandler instance = new LogQueryHandler(con);
        boolean added = false;
        
        ArrayList<String[]> result = instance.getCharacterLog(month, characterID);
        String[] one = result.get(0);
        
        if(one[0].equals(Integer.toString(characterID)) && one[1].substring(0, one[1].indexOf(" ")).equals(date) &&
                one[2].equals("test character log"))
            added = true;
        
        assertTrue(added);
    }

    /**
     * Test of CharacterLog method, of class LogQueryHandler.
     * 
     * ***Note: this function is not tested as the function above - testGetCharacterLog
     * already tests the insert functionality of the CharacterLog table
     */
    public void testCharacterLog() {
        /*System.out.println("Testing CharacterLog()");
        */
    }

    /**
     * Test of PlotLog method, of class LogQueryHandler.
     * 
     * ***Note: this function is not tested as the function above - testGetPlotLog()
     * already tests the insert functionality of the PlotLog table
     */
    public void testPlotLog() {
        /*System.out.println("Testing PlotLog()");
        */
    }
}
