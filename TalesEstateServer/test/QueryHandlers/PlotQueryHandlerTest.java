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
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class PlotQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    private int testPlotID = 0;
    private int testAmountID = 0;
    private int testAddedPlot = 0;
    
    public PlotQueryHandlerTest(String testName) {
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
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT PlotAmount FROM Plot WHERE PlotOwnedBy = " + testCharID);
            rs.next();
            testAmountID = Integer.parseInt(rs.getString("PlotAmount"));
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
     * Test of capitalizeFirst method, of class PlotQueryHandler.
     */
    public void testCapitalizeFirst() {
        System.out.println("Testing capitalizeFirst()");
        PlotQueryHandler instance = new PlotQueryHandler(con);
        String name = "hello";
        String result = instance.capitalizeFirst(name);
        
        String expResult = "Hello";
        assertEquals(expResult, result);
    }

    /**
     * Test of queryPlotPrice method, of class PlotQueryHandler.
     */
    public void testQueryPlotPrice() {
        System.out.println("Testing queryPlotPrice()");
        PlotQueryHandler instance = new PlotQueryHandler(con);
        String duchy = "Svaerstein";
        String quality = "Poor";
        ArrayList<String[]> result = instance.queryPlotPrice(duchy, quality);
        
        ArrayList<String[]> expResult = new ArrayList();
        String[] line = new String[3];
        line[0] = "0";
        line[1] = "15";
        line[2] = "0";
        expResult.add(line);
        line = new String[3];
        line[0] = "0";
        line[1] = "0";
        line[2] = "8";
        expResult.add(line);
        
        int correct = 0;
        for(int a = 0; a < result.size(); a++)
        {
            if(result.get(a)[0].equals(expResult.get(a)[0]) &&
                result.get(a)[1].equals(expResult.get(a)[1]) &&
                result.get(a)[2].equals(expResult.get(a)[2]))
                ++correct;
        }
        
        assertEquals(correct, 2);
    }

    /**
     * Test of convertToArray method, of class PlotQueryHandler.
     */
    public void testConvertToArray() {
        System.out.println("Testing convertToArray()");
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int[][] inArray = new int[3][3];
        for(int a = 1; a < 4; a++)
        {
            for(int b = 1; b < 4; b++)
            {
                inArray[a - 1][b - 1] = b;
            }
        }
        String result = instance.convertToArray(inArray);
        
        String expResult = "1,2,3;1,2,3;1,2,3;";
        
        assertEquals(expResult, result);
    }

    /**
     * Test of convertFromArray method, of class PlotQueryHandler.
     */
    public void testConvertFromArray() {
        System.out.println("Testing convertFromArray()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        String inArray = "1,2,3;1,2,3;1,2,3;";
        int[][] result = instance.convertFromArray(inArray);
        
        int[][] expResult = new int[3][3];
        for(int a = 1; a < 4; a++)
        {
            for(int b = 1; b < 4; b++)
            {
                expResult[a - 1][b - 1] = b;
            }
        }
        
        int correct = 0;
        
        for(int a = 1; a < 4; a++)
        {
            for(int b = 1; b < 4; b++)
            {
                if(expResult[a - 1][b - 1] == result[a - 1][b - 1])
                    ++correct;
            }
        }
        
        assertEquals(9, correct);
    }

    /**
     * Test of addPlotToCharacter method, of class PlotQueryHandler.
     * 
     * **Note: This test remains commented because previous test data already
     * confirms adding functionality
     */
    public void testAddPlotToCharacter() {
        /*System.out.println("Testing addPlotToCharacter()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int[][] ar1 = new int[3][3];
        for(int a = 0; a < 3; a++)
            for(int b = 0; b < 3; b++)
                ar1[a][b] = 0;
        int[][] ar2 = new int[3][3];
        for(int a = 0; a < 3; a++)
            for(int b = 0; b < 3; b++)
                ar2[a][b] = -1;
        
        String charName = "";
        boolean added = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM UserCharacter WHERE "
                    + "UserCharacterID = " + testCharID);
            rs.next();
            charName = rs.getString("UserCharacterName");
        }
        catch(Exception e) {
            System.out.println("Error in function testAddPlotToCharacter()");
            System.out.println(e.getMessage());
        }
                
        instance.addPlotToCharacter(charName, "Sarkland", "Poor", 3, ar1, ar2, 0, 0, 0, 0, "Aiber", "test plot");
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotOwnedBy = "
                    + testCharID + " AND PlotEstateName = 'test plot'");
            rs.next();
            
            if(rs.getString("PlotOwnedBy").equals(Integer.toString(testCharID)) &&
                    rs.getString("PlotEstateName").equals("test plot"))
                added = true;
            
            testAddedPlot = Integer.parseInt(rs.getString("PlotID"));
        }
        catch(Exception e) {
            System.out.println("Error in function testAddPlotToCharacter()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(added);
        */
    }

    /**
     * Test of retrievePlotsOwnedByCharacter method, of class PlotQueryHandler.
     */
    public void testRetrievePlotsOwnedByCharacter() {
        System.out.println("Testing retrievePlotsOwnedByCharacter()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int characterID = testCharID;
        ArrayList<String[]> result = instance.retrievePlotsOwnedByCharacter(characterID);
        boolean stop = false;
        
        //This is one of the results that should be present.
        String[] expResult = null;
        for(int a = 0; a < result.size() && stop == false; a++) {
            if(result.get(a)[18].equals("test estate")) {
                stop = true;
                expResult = result.get(a);
            }
        }
        
        String[] line = new String[20];
        line[0] = Integer.toString(testCharID);
        line[1] = "test character&*&" + testCharID;
        line[2] = "50-50-50";
        line[3] = "Thegnheim";
        line[4] = "1";
        line[5] = "0,0,0;0,0,0;0,0,0;";
        line[6] = "-1,-1,-1;-1,-1,-1;-1,-1,-1;";
        line[7] = "10";
        line[8] = "10.1";
        line[9] = "10";
        line[10] = "20";
        line[11] = "1.0";
        line[12] = "1";
        line[13] = "1.0";
        line[14] = "1";
        line[15] = "1.0";
        line[16] = "1";
        line[17] = "10.0";
        line[18] = "test estate";
        line[19] = "Aiber";
        
        boolean correct = true;
        for(int a = 1; a < expResult.length; a++)
        {
            if(!line[a].equals(expResult[a]))
                correct = false;
        }
        
        assertTrue(correct);
    }

    /**
     * Test of retrievePlotDetails method, of class PlotQueryHandler.
     */
    public void testRetrievePlotDetails() {
        System.out.println("Testing retrievePlotDetails()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int plotID = testPlotID;
        ArrayList<String> result = instance.retrievePlotDetails(plotID);
        
        ArrayList<String> expResult = new ArrayList();
        expResult.add(Integer.toString(testCharID));
        expResult.add("test character&*&" + testCharID);
        expResult.add("50-50-50");
        expResult.add("Thegnheim");
        expResult.add("1");
        expResult.add("0,0,0;0,0,0;0,0,0;");
        expResult.add("-1,-1,-1;-1,-1,-1;-1,-1,-1;");
        expResult.add("10");
        expResult.add("10.1");
        expResult.add("10");
        expResult.add("20");
        expResult.add("1.0");
        expResult.add("1");
        expResult.add("1.0");
        expResult.add("1");
        expResult.add("1.0");
        expResult.add("1");
        expResult.add("10.0");
        expResult.add("test estate");
        
        boolean correct = false;
        if(result.get(0).equals(expResult.get(0)) &&
            result.get(1).equals(expResult.get(1)) &&
            result.get(2).equals(expResult.get(2)) &&
            result.get(3).equals(expResult.get(3)) &&
            result.get(4).equals(expResult.get(4)) &&
            result.get(5).equals(expResult.get(5)) &&
            result.get(6).equals(expResult.get(6)) &&
            result.get(7).equals(expResult.get(7)) &&
            result.get(8).equals(expResult.get(8)) &&
            result.get(9).equals(expResult.get(9)) &&
            result.get(10).equals(expResult.get(10)) &&
            result.get(11).equals(expResult.get(11)) &&
            result.get(12).equals(expResult.get(12)) &&
            result.get(13).equals(expResult.get(13)) &&
            result.get(14).equals(expResult.get(14)) &&
            result.get(15).equals(expResult.get(15)) &&
            result.get(16).equals(expResult.get(16)) &&
            result.get(17).equals(expResult.get(17)) &&
            result.get(18).equals(expResult.get(18)))
            correct = true;
            
        assertTrue(correct);
    }

    /**
     * Test of modifyPlot method, of class PlotQueryHandler.
     * 
     * *Note: this function will remain unimplemented as we do not want to
     * unnecessarily modify plot details.
     */
    public void testModifyPlot() {
        System.out.println("Testing modifyPlot()");
        int amountID = 0;
        int[][] groundArray, buildingArray;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT PlotAmount FROM Plot WHERE PlotOwnedBy = "
                    + testCharID);
            rs.next();
            amountID = Integer.parseInt(rs.getString("PlotAmount"));
        }
        catch(Exception e) {
            System.out.println("Error in function testModifyPlot()");
            System.out.println(e.getMessage());
        }
        
        //Change duchy to rotheim
        PlotQueryHandler instance = new PlotQueryHandler(con);
        
        groundArray = instance.convertFromArray("0,0,0;0,0,0;0,0,0;");
        buildingArray = instance.convertFromArray("-1,-1,-1;-1,-1,-1;-1,-1,-1;");
                
        instance.modifyPlot(testPlotID, "test character&*&" + testCharID, "50-50-50",
                "Rotheim", 1, groundArray, buildingArray, 10, 10.1, 10, 20, 1.0, 1,
                1.0, 1, 1.0, 1, 10, "test estate", "Aiber");
        
        boolean correct = false;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotOwnedBy = "
                    + testCharID);
            rs.next();
            if(rs.getString("PlotDuchy").equals("5"))
                correct = true;
        }
        catch(Exception e) {
            System.out.println("Error in function testModifyPlot()");
            System.out.println(e.getMessage());
        }
        
        instance.modifyPlot(testPlotID, "test character&*&" + testCharID, "50-50-50",
                "Thegnheim", 1, groundArray, buildingArray, 10, 10.1, 10, 20, 1.0, 1,
                1.0, 1, 1.0, 1, 10, "test estate", "Aiber");
        
        assertTrue(correct);
    }

    /**
     * Test of searchPlotBy method, of class PlotQueryHandler.
     */
    public void testSearchPlotBy() {
        System.out.println("Testing searchPlotBy()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        String characterName = "test character&*&" + testCharID;
        String duchy = "Thegnheim";
        int size = 1;
        String quality = "Fine";
        String amountID = "";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotOwnedBy = " + testCharID);
            rs.next();
            amountID = rs.getString("PlotAmount");
        }
        catch(Exception e) {
            System.out.println("Error in function testSearchPlotBy()");
            System.out.println(e.getMessage());
        }
        ArrayList<String[]> result = instance.searchPlotBy(characterName, duchy, size, quality);
        String[] expResult = result.get(0);
        
        String[] line = new String[20];
        line[0] = "";
        line[1] = Integer.toString(testCharID);
        line[2] = amountID;
        line[3] = "1";
        line[4] = "1";
        line[5] = "0,0,0;0,0,0;0,0,0;";
        line[6] = "-1,-1,-1;-1,-1,-1;-1,-1,-1;";
        line[7] = "10";
        line[8] = "10.1";
        line[9] = "10";
        line[10] = "20";
        line[11] = "1.0";
        line[12] = "1";
        line[13] = "1.0";
        line[14] = "1";
        line[15] = "1.0";
        line[16] = "1";
        line[17] = "10.0";
        line[18] = "test estate";
        line[19] = "1";
        
        boolean correct = false;
        if(expResult[1].equals(line[1]) &&
                expResult[2].equals(line[2]) &&
                expResult[3].equals(line[3]) &&
                expResult[4].equals(line[4]) &&
                expResult[5].equals(line[5]) &&
                expResult[6].equals(line[6]) &&
                expResult[7].equals(line[7]) &&
                expResult[8].equals(line[8]) &&
                expResult[9].equals(line[9]) &&
                expResult[10].equals(line[10]) &&
                expResult[11].equals(line[11]) &&
                expResult[12].equals(line[12]) &&
                expResult[13].equals(line[13]) &&
                expResult[14].equals(line[14]) &&
                expResult[15].equals(line[15]) &&
                expResult[16].equals(line[16]) &&
                expResult[17].equals(line[17]) &&
                expResult[18].equals(line[18]) &&
                expResult[19].equals(line[19]))
            correct = true;

        assertTrue(correct);
    }

    /**
     * Test of deletePlot method, of class PlotQueryHandler.
     * 
     * *Note: This function will remain unimplemented because we do not want
     * to delete a plot every time we test.
     */
    public void testDeletePlot() {
        /*System.out.println("Testing deletePlot()");
        
        String amountID;
        boolean deleted = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT PlotAmount FROM Plot WHERE PlotID = "
                    + testAddedPlot);
            rs.next();
            amountID = rs.getString("PlotAmount");
            
            stmt = con.createStatement();
            stmt.execute("DELETE FROM Amount WHERE AmountID = " + amountID);
            
            stmt = con.createStatement();
            stmt.execute("DELETE FROM Plot WHERE PlotID = " + testAddedPlot);
            deleted = true;
        }
        catch(Exception e) {
            System.out.println("Error in function testDeletePlot()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(deleted);
        */ 
    }

    /**
     * Test of retrieveAllPlots method, of class PlotQueryHandler.
     */
    public void testRetrieveAllPlots() {
        System.out.println("Testing retrieveAllPlots()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        ArrayList<String[]> results = instance.retrieveAllPlots();
        int remem = -1;
        
        //Find test case
        for(int a = 0; a < results.size(); a++) {
            if(results.get(a)[1].equals("test character&*&" + testCharID)) {
                remem = a;
            }
        }
        
        boolean found = false;
        String[] one = null;
        String[] expResult = new String[20];
        
        expResult[0] = "";  //Will always differ. Don't use
        expResult[1] = "test character&*&" + testCharID;
        expResult[2] = "50-50-50";
        expResult[3] = "Thegnheim";
        expResult[4] = "1";
        expResult[5] = "0,0,0;0,0,0;0,0,0;";
        expResult[6] = "-1,-1,-1;-1,-1,-1;-1,-1,-1;";
        expResult[7] = "10";
        expResult[8] = "10.1";
        expResult[9] = "10";
        expResult[10] = "20";
        expResult[11] = "1.0";
        expResult[12] = "1";
        expResult[13] = "1.0";
        expResult[14] = "1";
        expResult[15] = "1.0";
        expResult[16] = "1";
        expResult[17] = "10.0";
        expResult[18] = "test estate";
        expResult[19] = "1";
                

        if(remem != -1) {
            one = results.get(remem);
        
            if(one[1].equals(expResult[1]) &&
                    one[2].equals(expResult[2]) &&
                    one[3].equals(expResult[3]) &&
                    one[4].equals(expResult[4]) &&
                    one[5].equals(expResult[5]) &&
                    one[6].equals(expResult[6]) &&
                    one[7].equals(expResult[7]) &&
                    one[8].equals(expResult[8]) &&
                    one[9].equals(expResult[9]) &&
                    one[10].equals(expResult[10]) &&
                    one[11].equals(expResult[11]) &&
                    one[12].equals(expResult[12]) &&
                    one[13].equals(expResult[13]) &&
                    one[14].equals(expResult[14]) &&
                    one[15].equals(expResult[15]) &&
                    one[16].equals(expResult[16]) &&
                    one[17].equals(expResult[17]) &&
                    one[18].equals(expResult[18]) &&
                    one[19].equals(expResult[19]))
                found = true;
        }
        
        assertTrue(found);
    }

    /**
     * Test of getCurrentAmount method, of class PlotQueryHandler.
     */
    public void testGetCurrentAmount() {
        System.out.println("Testomg getCurrentAmount()");
        
        int plotID = testPlotID;
        PlotQueryHandler instance = new PlotQueryHandler(con);
        
        ArrayList expResult = new ArrayList();
        expResult.add("50");
        expResult.add("50");
        expResult.add("50");
        
        ArrayList result = instance.getCurrentAmount(plotID);
        boolean correct = false;
        
        if(result.get(0).equals(expResult.get(0)) &&
                result.get(1).equals(expResult.get(1)) &&
                result.get(2).equals(expResult.get(2)))
            correct = true;
        
        assertTrue(correct);
    }

    /**
     * Test of modifyAmount method, of class PlotQueryHandler.
     */
    public void testModifyAmount() {
        System.out.println("Testing modifyAmount()");
        
        int inPlotID = testPlotID;
        int amountPlatinum = 11;
        int amountGold = 22;
        int amountSilver = 33;
        PlotQueryHandler instance = new PlotQueryHandler(con);
        
        boolean correct = false;
        String amountID;
        
        instance.modifyAmount(inPlotID, amountPlatinum, amountGold, amountSilver);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT PlotAmount FROM Plot WHERE PlotOwnedBy = " + testCharID);
            rs.next();
            amountID = rs.getString("PlotAmount");
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Amount WHERE AmountID = " + amountID);
            rs.next();
            if(rs.getString("AmountPlatinum").equals("11") &&
                    rs.getString("AmountGold").equals("22") &&
                    rs.getString("AmountSilver").equals("33"))
                correct = true;
            
            instance.modifyAmount(inPlotID, 50, 50, 50);
        }
        catch(Exception e) {
            System.out.println("Error in function testModifyAmount()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }

    /**
     * Test of depositAmount method, of class PlotQueryHandler.
     */
    public void testDepositAmount() {
        System.out.println("Testing depositAmount()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        instance.depositAmount(testPlotID, 5, 5, 5);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Amount WHERE AmountID = " +
                    testAmountID);
            rs.next();
            
            if(rs.getString("AmountPlatinum").equals("55") &&
                    rs.getString("AmountGold").equals("55") &&
                    rs.getString("AmountSilver").equals("55"))
                correct = true;
            
            instance.modifyAmount(testPlotID, 50, 50, 50);
        }
        catch(Exception e) {
            System.out.println("Error in function testDepositAmount()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }

    /**
     * Test of withdrawAmount method, of class PlotQueryHandler.
     */
    public void testWithdrawAmount() {
        System.out.println("Testing withdrawAmount()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        instance.withdrawAmount(testPlotID, 5, 5, 5);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Amount WHERE AmountID = " +
                    testAmountID);
            rs.next();
            
            if(rs.getString("AmountPlatinum").equals("45") &&
                    rs.getString("AmountGold").equals("45") &&
                    rs.getString("AmountSilver").equals("45"))
                correct = true;
            
            instance.modifyAmount(testPlotID, 50, 50, 50);
        }
        catch(Exception e) {
            System.out.println("Error in function testDepositAmount()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }

    /**
     * Test of expandPlot method, of class PlotQueryHandler.
     */
    public void testExpandPlot() {
        System.out.println("Testing expandPlot()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int plotID = testPlotID;
        String quality = "Exquisite";
        int[][] groundArray = instance.convertFromArray("0,0,0;0,0,0;0,0,0;");
        int[][] buildingArray = instance.convertFromArray("-1,-1,-1;-1,-1,-1;-1,-1,-1;");
        boolean correct = false;
        
        instance.expandPlot(plotID, quality, groundArray, buildingArray);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotID = " + testPlotID);
            rs.next();
            
            if(rs.getString("PlotAcreExquisiteMax").equals("2"))
                correct = true;
            
            stmt = con.createStatement();
            stmt.execute("UPDATE Plot SET PlotSize = 1, PlotGroundArray = '0,0,0;0,0,0;0,0,0;', "
                    + "PlotBuildingArray = '-1,-1,-1;-1,-1,-1;-1,-1,-1;', PlotAcreExquisiteMax = 1 "
                    + "WHERE PlotID = " + testPlotID);
        }
        catch(Exception e) {
            System.out.println("Error in function testExpandPlot()");
            System.out.println(e.getMessage());
        }

        assertTrue(correct);
    }

    /**
     * Test of getAcreQualityAmounts method, of class PlotQueryHandler.
     */
    public void testGetAcreQualityAmounts() {
        System.out.println("Testing getAcreQualityAmounts()");
        
        int plotID = testPlotID;
        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        
        ArrayList<String> result = instance.getAcreQualityAmounts(plotID);
        
        if(result.get(0).equals("1") &&
                result.get(1).equals("1") &&
                result.get(2).equals("1"))
            correct = true;
        
        assertTrue(correct);
    }

    /**
     * Test of useAcresOnPlot method, of class PlotQueryHandler.
     */
    public void testUseAcresOnPlot() {
        System.out.println("Testing useAcresOnPlot()");
        
        int plotID = testPlotID;
        double acreExquisite = -0.5;
        double acreFine = -0.4;
        double acrePoor = -0.3;
        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        
        instance.useAcresOnPlot(plotID, acreExquisite, acreFine, acrePoor);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotID = " + testPlotID);
            rs.next();
            
            if(rs.getString("PlotAcreExquisite").equals("0.5") &&
                    rs.getString("PlotAcreFine").equals("0.6") &&
                    rs.getString("PlotAcrePoor").equals("0.7"))
                correct = true;
            
            instance.useAcresOnPlot(plotID, 0.5, 0.4, 0.3);
        }
        catch(Exception e) {
            System.out.println("Error in function testUserAcresOnPlot()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }

    /**
     * Test of addPlotAccess method, of class PlotQueryHandler.
     * **Note: The following 3 test must follow each other in succession.
     * 
     * Test 1st of 3
     */
    public void testAddPlotAccess() {
        System.out.println("Testing addPlotAccess()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM PlotAccess WHERE PlotID = " + testPlotID);
            if(!rs.next())
                instance.addPlotAccess(testPlotID, testCharID, true, false, false, false, false, true);
            else
                stmt.execute("UPDATE PlotAccess SET "
                        + "PlotAccessDeposit = 1, "
                        + "PlotAccessWithdraw = 0, "
                        + "PlotAccessBuy = 0, "
                        + "PlotAccessPlace = 0, "
                        + "PlotAccessExpand = 0, "
                        + "PlotAccessStatus = 1 "
                        + "WHERE PlotID = " + testPlotID);
        }
        catch(Exception e) {
            System.out.println("Error in function testAddPlotAccess()");
            System.out.println(e.getMessage());
        }
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM PlotAccess WHERE PlotID = " + testPlotID);
            rs.next();
            
            if(rs.getString("PlotAccessDeposit").equals("1") &&
                    rs.getString("PlotAccessWithdraw").equals("0") &&
                    rs.getString("PlotAccessBuy").equals("0") &&
                    rs.getString("PlotAccessPlace").equals("0") &&
                    rs.getString("PlotAccessExpand").equals("0") &&
                    rs.getString("PlotAccessStatus").equals("1"))
                correct = true;
        }
        catch(Exception e) {
            System.out.println("Error in function testAddPlotAccess()");
            System.out.println(e.getMessage());
        }
        assertTrue(correct);
    }

    /**
     * Test of modifyPlotAccess method, of class PlotQueryHandler.
     * 
     * Test 2nd of 3
     */
    public void testModifyPlotAccess() {
        System.out.println("Testing modifyPlotAccess()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        
        instance.modifyPlotAccess(testPlotID, testCharID, false, true, true, true, true, false);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM PlotAccess WHERE PlotID = " + testPlotID);
            rs.next();
            
            if(rs.getString("PlotAccessDeposit").equals("0") &&
                    rs.getString("PlotAccessWithdraw").equals("1") &&
                    rs.getString("PlotAccessBuy").equals("1") &&
                    rs.getString("PlotAccessPlace").equals("1") &&
                    rs.getString("PlotAccessExpand").equals("1") &&
                    rs.getString("PlotAccessStatus").equals("0"))
                correct = true;
        }
        catch(Exception e) {
            System.out.println("Error in function testAddPlotAccess()");
            System.out.println(e.getMessage());
        }
        assertTrue(correct);
    }

    /**
     * Test of getPlotAccess method, of class PlotQueryHandler.
     * 
     * Test 3rd of 3
     */
    public void testGetPlotAccess() {
        System.out.println("Testing getPlotAccess()");

        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        ArrayList<String> result = instance.getPlotAccess(testPlotID, testCharID);
        
        if(result.get(0).equals("0") &&
                result.get(1).equals("1") &&
                result.get(2).equals("1") &&
                result.get(3).equals("1") &&
                result.get(4).equals("1") &&
                result.get(5).equals("0"))
            correct = true;
        
        assertTrue(correct);
    }

    /**
     * Test of getAllAccess method, of class PlotQueryHandler.
     */
    public void testGetAllAccess() {
        System.out.println("Testing getAllAccess()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        
        ArrayList<String[]> result = instance.getAllAccess(testPlotID);
        String[] one = result.get(0);
        
        if(one[0].equals("0") &&
                one[1].equals("1") &&
                one[2].equals("1") &&
                one[3].equals("1") &&
                one[4].equals("1") &&
                one[5].equals("0"))
            correct = true;
            
        assertTrue(correct);
    }

    /**
     * Test of removeAccess method, of class PlotQueryHandler.
     */
    public void testRemoveAccess() {
        System.out.println("Testing removeAccess()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        instance.removeAccess(testPlotID, testCharID);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM PlotAccess WHERE PlotID = " + testPlotID
                     + " AND UserCharacterID = " + testCharID);
            if(!rs.next())
                correct = true;
        }
        catch(Exception e) {
            System.out.println("Error in function testRemoveAccess()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }

    /**
     * Test of AllPlotsIHaveAccess method, of class PlotQueryHandler.
     */
    public void testAllPlotsIHaveAccess() {
        System.out.println("Testing AllPlotsIHaveAccess()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        instance.addPlotAccess(testPlotID, testCharID, true, false, false, false, false, true);
        
        ArrayList<String[]> result = instance.AllPlotsIHaveAccess(testCharID);
        String[] one = result.get(0);
        boolean correct = false;
        
        if(one[0].equals(Integer.toString(testCharID)))
            correct = true;
        
        assertTrue(correct);
    }

    /**
     * Test of DoExspand method, of class PlotQueryHandler.
     */
    public void testDoExspand() {
        System.out.println("Testing DoExspand()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        instance.DoExspand(testPlotID, 5.0, 100);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotID = " + testPlotID);
            rs.next();
            
            if(rs.getString("PlotMonthlyIncome").equals("5.0") &&
                    rs.getString("PlotWorkerMax").equals("100"))
                correct = true;
            
            stmt = con.createStatement();
            stmt.executeQuery("UPDATE Plot SET PlotMonthlyIncome = 10.1, "
                    + "PlotWorkerMax = 20 WHERE PlotID = " + testPlotID);
        }
        catch(Exception e) {
            System.out.println("Error in function testDoExpand()");
            System.out.println(e.getMessage());
        }

        assertTrue(correct);
    }

    /**
     * Test of PlaceBuilding method, of class PlotQueryHandler.
     */
    public void testPlaceBuilding() {
        System.out.println("Testing PlaceBuilding()");
        
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int[][] buildings = instance.convertFromArray("2,-1,-1;-1,3,-1;-1,-1,4;");
        
        instance.PlaceBuilding(testPlotID, buildings);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotID = " + testPlotID);
            rs.next();
            
            if(rs.getString("PlotBuildingArray").equals("2,-1,-1;-1,3,-1;-1,-1,4;"))
                correct = true;
            
            stmt = con.createStatement();
            stmt.execute("UPDATE Plot SET PlotBuildingArray = '-1,-1,-1;-1,-1,-1;-1,-1,-1;' "
                    + "WHERE PlotID = " + testPlotID);
        }
        catch(Exception e) {
            System.out.println("Error in function testPlaceBuilding()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }

    /**
     * Test of MarkBuildingAsPlaced method, of class PlotQueryHandler.
     */
    public void testMarkBuildingAsPlaced() {
        System.out.println("Testing MarkBuildingAsPlaced()");
        
        int blogID = -1;
        PlotQueryHandler instance = new PlotQueryHandler(con);
        boolean correct = false;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM BuildLog WHERE BuildLogPlotID = " + testPlotID);
            rs.next();
            
            blogID = Integer.parseInt(rs.getString("BuildLogID"));
        }
        catch(Exception e) {
            System.out.println("Error in function testMarkBuildingAsPlaced()");
            System.out.println(e.getMessage());
        }
        
        if(blogID != -1) {
            instance.MarkBuildingAsPlaced(blogID);
        }
        else
            System.out.println("Error in function testMarkBuildingAsPlaced():"
                    + "Could not find a BuildLogID.");
        
        try { 
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM BuildLog WHERE BuildLogID = " + blogID);
           rs.next();
           
           if(rs.getString("BuildLogPlaced").equals("1"))
               correct = true;
           
           stmt = con.createStatement();
           stmt.execute("UPDATE BuildLog SET BuildLogPlaced = '0' WHERE BuildLogID = " + blogID);
        }
        catch(Exception e) {
            System.out.println("Error in function testMarkBuildingAsPlaced()");
            System.out.println(e.getMessage());
        }
        
        assertTrue(correct);
    }
}
