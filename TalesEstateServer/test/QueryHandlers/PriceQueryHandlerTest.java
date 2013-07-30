/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class PriceQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    public PriceQueryHandlerTest(String testName) {
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
     * Test of retrieveMonthlyUpkeep method, of class PriceQueryHandler.
     */
    public void testRetrieveMonthlyUpkeep() {
        System.out.println("Testing retrieveMonthlyUpkeep()");
        PriceQueryHandler instance = new PriceQueryHandler(con);
        String duchy = "Sarkland";
        String quality = "Exquisite";
        ArrayList<String> result = instance.retrieveMonthlyUpkeep(duchy, quality);
        
        ArrayList<String> expResult = new ArrayList();
        expResult.add("0");
        expResult.add("7");
        expResult.add("5");
        
        int correct = 0;
        for(int a = 0; a < result.size(); a++)
        {
            if(result.get(a).equals(expResult.get(a)))
                ++correct;
        }
        
        assertEquals(3, correct);
    }
}
