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
public class DuchyQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    public DuchyQueryHandlerTest(String testName) {
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
     * Test of retrieveDuchyList method, of class DuchyQueryHandler.
     */
    public void testRetrieveDuchyList() {
        System.out.println("Testing retrieveDuchyList()");
        DuchyQueryHandler instance = new DuchyQueryHandler(con);
        ArrayList<String> result = instance.retrieveDuchyList();
        
        //Some results that should be present
        ArrayList<String> expResult = new ArrayList();
        expResult.add("Sarkland");
        expResult.add("Langzerund");
        
        int correct = 0;
        for(int a = 0; a < result.size(); a++)
        {
            if(result.get(a).equals(expResult.get(0)))
                ++correct;
            if(result.get(a).equals(expResult.get(1)))
                ++correct;
        }
        
        assertEquals(correct, 2);
    }
}
