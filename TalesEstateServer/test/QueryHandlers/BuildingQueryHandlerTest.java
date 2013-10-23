package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

public class BuildingQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    private int testPlotID = 0;

    public BuildingQueryHandlerTest(String testName) {
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
        ArrayList<String> result = instance.retrieveCompleteBuildingList();
        
        //The results should contain some of these (random) values:
        String[] expectedValues = new String[5];
        expectedValues[0] = "Farm (Cattle)(Dairy and Meat)";
        expectedValues[1] = "Mining Operation (Iron)";
        expectedValues[2] = "Artisan (Specify)";
        expectedValues[3] = "Merchant (Specify)";
        expectedValues[4] = "Barracks (Sleeps 20)";
        
        assertTrue(result.contains(expectedValues[0]));
        assertTrue(result.contains(expectedValues[1]));
        assertTrue(result.contains(expectedValues[2]));
        assertTrue(result.contains(expectedValues[3]));
        assertTrue(result.contains(expectedValues[4]));
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
        String[] oneLine = result.get(0);
        
        String[] line = new String[13];
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
        line[11] = "0.0";
        line[12] = "58";
        
        boolean correct = true;
        for(int b = 0; b < oneLine.length; b++)
        {
            if(!oneLine[b].equals(line[b]))
                correct = false;
        }
        
        assertTrue(correct);
    }

    /**
     * Test of retrieveAllBuildingsOwnedByCharacter method, of class BuildingQueryHandler.
     */
    public void testRetrieveAllBuildingsOwnedByCharacter() {
        System.out.println("Testing retrieveAllBuildingsOwnedByCharacter()");

        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        ArrayList<String[]> result = instance.retrieveAllBuildingsOwnedByCharacter(testPlotID);
        String[] oneResult;
        Boolean correct = false;
         
        oneResult = result.get(0);
        if(oneResult[0].equals("7") &&
                oneResult[1].equals("4") &&
                oneResult[2].equals("1") &&
                oneResult[4].equals("0"))
            correct = true;
        else
            correct = false;
        
        oneResult = result.get(1);
        if(oneResult[0].equals("9") &&
                oneResult[1].equals("4") &&
                oneResult[2].equals("1") &&
                oneResult[4].equals("0"))
            correct = true;
        else
            correct = false;
                
        assertTrue(correct);
    }

    /**
     * Test of checkBuildingPrerequisites method, of class BuildingQueryHandler.
     */
    public void testCheckBuildingPrerequisites() {
        System.out.println("Testing checkBuildingPrerequisites()");
        
        int plotID = testPlotID;
        int buildingID = 33;
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        String expResult = "iron mine";
        
        String result = instance.checkBuildingPrerequisites(plotID, buildingID);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of countOccurrences method, of class BuildingQueryHandler.
     */
    public void testCountOccurrences() {
        System.out.println("Testing countOccurrences()");
        String whole = "asdfasdfasdfa";
        String part = "f";
        
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        int expResult = 3;
        
        int result = instance.countOccurrences(whole, part);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getBuildingTTB method, of class BuildingQueryHandler.
     */
    public void testGetBuildingTTB() {
        System.out.println("Testing getBuildingTTB()");
        
        int buildingID = 47;
        BuildingQueryHandler instance = new BuildingQueryHandler(con);
        
        String expResult = "1";
        String result = instance.getBuildingTTB(buildingID);
        
        assertEquals(expResult, result);
    }
}
