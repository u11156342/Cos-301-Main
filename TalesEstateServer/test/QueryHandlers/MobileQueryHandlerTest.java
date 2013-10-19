/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class MobileQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    private int testPlotID = 0;
    
    public MobileQueryHandlerTest(String testName) {
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
     * Test of getid method, of class MobileQueryHandler.
     */
    public void testGetid() throws Exception {
        System.out.println("Testing getid()");
        
        String name = "";
        MobileQueryHandler instance = new MobileQueryHandler();
        String expResult = "";
        String result = instance.getdetails(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getdetails method, of class MobileQueryHandler.
     */
    public void testGetdetails() {
        System.out.println("Testing getdetails()");
        String name = "";
        MobileQueryHandler instance = null;
        String expResult = "";
        String result = "";
        
        try {
            instance = new MobileQueryHandler();
            result = instance.getdetails(name);
        }
        catch(Exception e) {
            System.out.println("Error in function testGetdetails()");
            System.out.println(e.getMessage());
        }

        assertEquals(expResult, result);
    }

    /**
     * Test of getnames method, of class MobileQueryHandler.
     */
    public void testGetnames() {
        System.out.println("Testing getnames()");
        
        String id = "";
        MobileQueryHandler instance = null;
        String expResult = "";
        String result = "";
        
        try {
            instance = new MobileQueryHandler();
            result = instance.getnames(id);
        }
        catch(Exception e) {
            System.out.println("Error in function testGetdetails()");
            System.out.println(e.getMessage());
        }

        assertEquals(expResult, result);
    }
}
