/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
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
}
