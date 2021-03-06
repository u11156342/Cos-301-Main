/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class RestFullDBAdapterTest extends TestCase {

    public RestFullDBAdapterTest(String testName) {
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
     * Test of retrieveCompleteBuildingList method, of class RestFullDBAdapter.
     */
    public void testRetrieveCompleteBuildingList() {
        System.out.println("retrieveCompleteBuildingList");
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList result = instance.retrieveCompleteBuildingList();
        assertEquals("Farm (Cattle)(Dairy and Meat)", result.get(0));
        assertEquals("Walls (10\" Stone)", result.get(75));
    }

    /**
     * Test of listBuildingBy method, of class RestFullDBAdapter.
     */
    public void testListBuildingBy() {
        System.out.println("listBuildingBy");
        String duchy = "Thegnheim";
        String industry = "Mining";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList<String[]> result = instance.listBuildingBy(duchy, industry);
        assertEquals("Oil Refinery (Pitch, Lamp oil, Trade with Hoblings)", result.get(0)[1]);
        assertEquals("Mining Operation (Semi Precious)", result.get(2)[1]);
    }

    /**
     * Test of retrieveBuildingDetailsById method, of class RestFullDBAdapter.
     */
    public void testRetrieveBuildingDetailsById() {
        System.out.println("retrieveBuildingDetailsById");
        int buildingID = 1;
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList<String[]> result = instance.retrieveBuildingDetailsById(buildingID);
        assertEquals("Agricultural", result.get(0)[0]);
        assertEquals("Farm (Cattle)(Dairy and Meat)", result.get(0)[1]);

    }

    /**
     * Test of retrieveDuchyList method, of class RestFullDBAdapter.
     */
    public void testRetrieveDuchyList() {
        System.out.println("retrieveDuchyList");
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList result = instance.retrieveDuchyList();
        assertEquals("Thegnheim", result.get(0));
        assertEquals("Langzerund", result.get(5));
    }

    /**
     * Test of queryPlotPrice method, of class RestFullDBAdapter.
     */
    public void testQueryPlotPrice() {
        System.out.println("queryPlotPrice");
        String duchy = "Thegnheim";
        String quality = "Fine";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList<String[]> result = instance.queryPlotPrice(duchy, quality);
        assertEquals("100", result.get(0)[1]);
        assertEquals("5", result.get(1)[1]);
    }

    /**
     * Test of addPlotToCharacter method, of class RestFullDBAdapter.
     */
    public void testAddPlotToCharacter() {
        System.out.println("addPlotToCharacter");
        String characterName = "QR Character";
        String duchyName = "Thegnheim";
        int sizeValue = 3;
        String quality = "Fine";
        int[][] groundArray = {{0, 0, 0}, {0, 0, 0}, {-1, -1, -1}};
        int[][] buildingArray = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double acresUsed = 0.0;
        double acreMax = 0.0;
        int workersUsed = 0;
        int workerMax = 0;
        int happiness = 0;
        double monthlyIncome = 0.0;
        RestFullDBAdapter instance = new RestFullDBAdapter();
        boolean expResult = true;
        boolean result = instance.addPlotToCharacter(characterName, duchyName, sizeValue, quality, groundArray, buildingArray, acresUsed, acreMax, workersUsed, workerMax, happiness, monthlyIncome);
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyPlot method, of class RestFullDBAdapter.
     */
    public void testModifyPlot() {
        System.out.println("modifyPlot");
        int plotId = 12;
        String characterName = "QR Character";
        String duchyName = "Thegnheim";
        int sizeValue = 3;
        String quality = "Fine";
        int[][] groundArray = {{0, 0, 0}, {0, 0, 0}, {-1, -1, -1}};
        int[][] buildingArray = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double acresUsed = 0.0;
        double acreMax = 0.0;
        int workersUsed = 0;
        int workerMax = 0;
        int happiness = 5;
        double monthlyIncome = 0.0;
        RestFullDBAdapter instance = new RestFullDBAdapter();
        boolean expResult = true;
        boolean result = instance.modifyPlot(plotId, characterName, duchyName, sizeValue, quality, groundArray, buildingArray, acresUsed, acreMax, workersUsed, workerMax, happiness, monthlyIncome);
        assertEquals(expResult, result);
    }

    /**
     * Test of retrievePlotsOwnedByCharacter method, of class RestFullDBAdapter.
     */
    public void testRetrievePlotsOwnedByCharacter() {
        System.out.println("retrievePlotsOwnedByCharacter");
        int characterID = 3;
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList<String[]> expResult = new ArrayList();

        String[] temp = new String[13];
        temp[0] = "1";

        expResult.add(temp);
        String[] temp2 = new String[13];
        temp2[0] = "2";

        expResult.add(temp2);

        ArrayList<String[]> result = instance.retrievePlotsOwnedByCharacter(characterID);
        assertEquals(expResult.get(0)[0], result.get(0)[0]);

        assertEquals(expResult.get(1)[0], result.get(1)[0]);

    }

    /**
     * Test of retrievePlotDetails method, of class RestFullDBAdapter.
     */
    public void testRetrievePlotDetails() {
        System.out.println("retrievePlotDetails");
        int plotID = 12;
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList expResult = new ArrayList();
        expResult.add("12");
        expResult.add("QR Character");

        ArrayList result = instance.retrievePlotDetails(plotID);
        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));

    }

    /**
     * Test of searchPlotBy method, of class RestFullDBAdapter.
     */
    public void testSearchPlotBy() {
        System.out.println("searchPlotBy");
        String characterName = "QR Character";
        String duchy = "Thegnheim";
        int size = 3;
        String quality = "Fine";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList<String[]> result = instance.searchPlotBy(characterName, duchy, size, quality);
        assertEquals("12", result.get(0)[0]);

    }

    /**
     * Test of deletePlot method, of class RestFullDBAdapter.
     */
    public void testDeletePlot() {
        System.out.println("deletePlot");
        int plotID = 0;
        RestFullDBAdapter instance = new RestFullDBAdapter();
        boolean expResult = true;
        boolean result = instance.deletePlot(plotID);
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveMonthlyUpkeep method, of class RestFullDBAdapter.
     */
    public void testRetrieveMonthlyUpkeep() {
        System.out.println("retrieveMonthlyUpkeep");
        String duchy = "Thegnheim";
        String quality = "Fine";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList result = instance.retrieveMonthlyUpkeep(duchy, quality);
        assertEquals("0", result.get(0));
        assertEquals("5", result.get(1));
        assertEquals("0", result.get(2));

    }

    /**
     * Test of registerEstateCharacter method, of class RestFullDBAdapter.
     */
    public void testRegisterEstateCharacter() {
        System.out.println("registerEstateCharacter");
        String characterName = "Test 123";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        boolean result = instance.registerEstateCharacter(characterName);
        assertEquals(true, result);
    }

    /**
     * Test of retrieveCharacterID method, of class RestFullDBAdapter.
     */
    public void testRetrieveCharacterID() {
        System.out.println("retrieveCharacterID");
        String userID = "QR Character";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        int expResult = 1;
        int result = instance.retrieveCharacterID(userID);
        assertEquals(expResult,1);
    }

    /**
     * Test of retrieveAllCharacters method, of class RestFullDBAdapter.
     */
    public void testRetrieveAllCharacters() {
        System.out.println("retrieveAllCharacters");
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList<String[]> result = instance.retrieveAllCharacters();
        assertEquals("1", result.get(0)[0]);
        assertEquals("Gilthana", result.get(0)[1]);
    }

    /**
     * Test of convertToArray method, of class RestFullDBAdapter.
     */
    public void testConvertToArray() {
        System.out.println("convertToArray");
        int[][] inArray = {{-1,-1,-1},{0,-1,2},{0,-1,0}};
        RestFullDBAdapter instance = new RestFullDBAdapter();
        String expResult = "-1,-1,-1_0,-1,2_0,-1,0_";
        String result = instance.convertToArray(inArray);
        assertEquals(expResult, result);
    }

    /**
     * Test of convertFromArray method, of class RestFullDBAdapter.
     */
    public void testConvertFromArray() {
        String inArray = "-1,-1,-1;0,-1,2;0,-1,0;";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        int[][] expResult = {{-1,-1,-1},{0,-1,2},{0,-1,0}};
        int[][] result = instance.convertFromArray(inArray);
        assertEquals(expResult[0][0], result[0][0]);
        assertEquals(expResult[2][2], result[2][2]);
    }

    /**
     * Test of checkLogin method, of class RestFullDBAdapter.
     */
    public void testCheckLogin() {
        System.out.println("checkLogin");
        String userID = "474C675A-EFE9-47B8-9AF5-01CEB4E2B98A";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        boolean expResult = true;
        boolean result = instance.checkLogin(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkHasCharacter method, of class RestFullDBAdapter.
     */
    public void testCheckHasCharacter() {
        System.out.println("checkHasCharacter");
        String userID = "474C675A-EFE9-47B8-9AF5-01CEB4E2B98A";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        boolean expResult = true;
        boolean result = instance.checkHasCharacter(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveCharactersOwnedByUser method, of class RestFullDBAdapter.
     */
    public void testRetrieveCharactersOwnedByUser() {
        System.out.println("retrieveCharactersOwnedByUser");
        String userID = "474C675A-EFE9-47B8-9AF5-01CEB4E2B98A";
        RestFullDBAdapter instance = new RestFullDBAdapter();
        ArrayList result = instance.retrieveCharactersOwnedByUser(userID);
        assertEquals("Fiorella de Luca", result.get(0));
        assertEquals("Gilthana", result.get(1));
        assertEquals("Saachi Namasri", result.get(2));
    }
}
