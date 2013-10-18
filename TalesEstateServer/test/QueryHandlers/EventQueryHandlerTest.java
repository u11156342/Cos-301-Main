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
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class EventQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    private int testPlotID = 0;
    
    public EventQueryHandlerTest(String testName) {
        super(testName);
        
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
     * Test of addEvent method, of class EventQueryHandler.
     */
    public void testAddEvent() {
        System.out.println("Testing addEvent()");
        
        int plotID = testPlotID;
        String eventName = "test event";
        String eventDescription = "test event in the unit test. testing...";
        int platinumMod = 1;
        int goldMod = 2;
        int silverMod = 3;
        int happinessMod = -1;
        int incomeMod = -10;
        EventQueryHandler instance = new EventQueryHandler(con);
        
        boolean added = false;
        boolean result = instance.addEvent(plotID, eventName, eventDescription, platinumMod, goldMod, silverMod, happinessMod, incomeMod);
        String resName, resDesc;
        
        if(result) {
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM EventLog WHERE "
                        + "PlotID = " + testPlotID);
                rs.next();
                resName = rs.getString("EventLogName");
                resDesc = rs.getString("EventLogDescription");
                
                if(resName.equals(eventName) && resDesc.equals(eventDescription))
                    added = true;
            }
            catch(Exception e) {
                System.out.println("Error in testAddEvent()");
                System.out.println(e.getMessage());
            }
        }
        
        assertTrue(added);
    }

    /**
     * Test of getEvent method, of class EventQueryHandler.
     */
    public void testGetEvent() {
        System.out.println("Testing getEvent()");
        
        int month = Calendar.getInstance().get(Calendar.MONTH);
        month++; //Calendar class index starts at 0
        int PlotID = testPlotID;
        
        EventQueryHandler instance = new EventQueryHandler(con);
        boolean match = false;
        
        ArrayList<String[]> result = instance.getEvent(month, PlotID);
        if(result.get(0)[2].equals("test event") && 
                result.get(0)[3].equals("test event in the unit test. testing..."))
            match = true;
        
        assertTrue(match);
    }
}
