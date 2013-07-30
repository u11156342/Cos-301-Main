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
public class BuildingQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    public BuildingQueryHandlerTest(String testName) {
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
     * Test of capitalizeFirst method, of class BuildingQueryHandler.
     */
    public void testCapitalizeFirst() {
        System.out.println("Testing capitalizeFirst()");
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        String result = instance.capitalizeFirst("hello");
        
        String expResult = "Hello";
        
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveCompleteBuildingList method, of class BuildingQueryHandler.
     */
    public void testRetrieveCompleteBuildingList() {
        System.out.println("Testing retrieveCompleteBuildingList()");
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        ArrayList result = instance.retrieveCompleteBuildingList();
        
        
        //The results should contain some of these (random) values:
        String[] expectedValues = new String[3];
        expectedValues[0] = "Farm (Sheep)";
        expectedValues[1] = "Artisan (Specify)";
        expectedValues[2] = "Summer House";
        
        assertTrue(result.contains(expectedValues[0]));
        assertTrue(result.contains(expectedValues[1]));
        assertTrue(result.contains(expectedValues[2]));
    }

    /**
     * Test of listBuildingBy method, of class BuildingQueryHandler.
     */
    public void testListBuildingBy() {
        System.out.println("Testing listBuildingBy()");
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        String duchy = "Ragonvaldr";
        String industry = "Manufacturing";
        ArrayList<String[]> result = instance.listBuildingBy(duchy, industry);

        //The results should contain some of these (random) values:
        String[] expectedValues = new String[3];
        expectedValues[0] = "Blacksmith";
        expectedValues[1] = "Brewery";
        expectedValues[2] = "Laboratory (Traps)";
        
        boolean correct1 = false,
                correct2 = false,
                correct3 = false;
        
        for(int a = 0; a < result.size(); a++)
        {
            if(result.get(a)[1].equals(expectedValues[0]))
            {
                correct1 = true;
            }
            if(result.get(a)[1].equals(expectedValues[1]))
            {
                correct2 = true;
            }
            if(result.get(a)[1].equals(expectedValues[2]))
            {
                correct3 = true;
            }            
        }
        
        assertTrue(correct1);
        assertTrue(correct2);
        assertTrue(correct3);
    }

    /**
     * Test of retrieveBuildingDetailsById method, of class BuildingQueryHandler.
     */
    public void testRetrieveBuildingDetailsById() {
        System.out.println("Testing retrieveBuildingDetailsById()");
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        int id = 7;
        ArrayList<String[]> result = instance.retrieveBuildingDetailsById(id);
        
        ArrayList<String[]> expResult = new ArrayList();
        String[] line = new String[11];
        line[0] = "Agricultural";
        line[1] = "Fishing Operation";
        line[2] = "Thegnheim,Sarkland,Ragonvaldr,Svaerstein,Rotheim,Langzerund";
        line[3] = "Suitable water";
        line[4] = "50";
        line[5] = "50";
        line[6] = "5.0";
        line[7] = "20";
        line[8] = "2";
        line[9] = "1.0";
        line[10] = "0";
        expResult.add(line);
        
        boolean correct = true;
        for(int b = 0; b < result.get(0).length; b++)
        {
            if(!expResult.get(0)[b].equals(result.get(0)[b]))
                correct = false;
        }
        
        assertTrue(correct);
    }
}
