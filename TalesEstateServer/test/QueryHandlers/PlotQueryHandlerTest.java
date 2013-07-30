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
public class PlotQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    public PlotQueryHandlerTest(String testName) {
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
     * *Note: this function will remain unimplemented as to not add unwanted
     * characters to the database every time the program is tested.
     */
    public void testAddPlotToCharacter() {
        //System.out.println("Testing addPlotToCharacter()");
    }

    /**
     * Test of retrievePlotsOwnedByCharacter method, of class PlotQueryHandler.
     */
    public void testRetrievePlotsOwnedByCharacter() {
        System.out.println("Testing retrievePlotsOwnedByCharacter()");
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int characterID = 3;
        ArrayList<String[]> result = instance.retrievePlotsOwnedByCharacter(characterID);
        
        //This is one of the results that should be present.
        ArrayList<String[]> expResult = new ArrayList();
        String[] line = new String[13];
        line[0] = "6";
        line[1] = "Gilthana";
        line[2] = "Svaerstein";
        line[3] = "3";
        line[4] = "Exquisite";
        line[5] = "2,3,2;2,2,3;3,2,3;";
        line[6] = "0,0,0;0,0,0;0,0,0;";
        line[7] = "1.0";
        line[8] = "1";
        line[9] = "0";
        line[10] = "-25.0";
        line[11] = "0";
        line[12] = "80";
        expResult.add(line);
        
        boolean correct = false;
        for(int a = 0; a < result.size(); a++)
        {
            if(result.get(a)[0].equals(expResult.get(0)[0]) &&
                result.get(a)[1].equals(expResult.get(0)[1]) &&
                result.get(a)[2].equals(expResult.get(0)[2]) &&
                result.get(a)[3].equals(expResult.get(0)[3]) &&
                result.get(a)[4].equals(expResult.get(0)[4]) &&
                result.get(a)[5].equals(expResult.get(0)[5]) &&
                result.get(a)[6].equals(expResult.get(0)[6]) &&
                result.get(a)[7].equals(expResult.get(0)[7]) &&
                result.get(a)[8].equals(expResult.get(0)[8]) &&
                result.get(a)[9].equals(expResult.get(0)[9]) &&
                result.get(a)[10].equals(expResult.get(0)[10]) &&
                result.get(a)[11].equals(expResult.get(0)[11]) &&
                result.get(a)[12].equals(expResult.get(0)[12]))
                correct = true;
        }
        
        assertEquals(true, correct);
    }

    /**
     * Test of retrievePlotDetails method, of class PlotQueryHandler.
     */
    public void testRetrievePlotDetails() {
        System.out.println("Testing retrievePlotDetails()");
        PlotQueryHandler instance = new PlotQueryHandler(con);
        int plotID = 6;
        ArrayList<String> result = instance.retrievePlotDetails(plotID);
        
        ArrayList<String> expResult = new ArrayList();
        expResult.add("6");
        expResult.add("Gilthana");
        expResult.add("Svaerstein");
        expResult.add("3");
        expResult.add("Exquisite");
        expResult.add("2,3,2;2,2,3;3,2,3;");
        expResult.add("0,0,0;0,0,0;0,0,0;");
        expResult.add("1.0");
        expResult.add("1");
        expResult.add("0");
        expResult.add("-25.0");
        expResult.add("0");
        expResult.add("80");
        
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
            result.get(12).equals(expResult.get(12)))
            correct = true;
            
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyPlot method, of class PlotQueryHandler.
     * 
     * *Note: this function will remain unimplemented as we do not want to
     * unnecessarily modify plot details.
     */
    public void testModifyPlot() {
        //System.out.println("Testing modifyPlot()");
    }

    /**
     * Test of searchPlotBy method, of class PlotQueryHandler.
     */
    public void testSearchPlotBy() {
        System.out.println("Testing searchPlotBy()");
        PlotQueryHandler instance = new PlotQueryHandler(con);
        String characterName = "Fiorella de Luca";
        String duchy = "Svaerstein";
        int size = 9;
        String quality = "Exquisite";
        ArrayList<String[]> result = instance.searchPlotBy(characterName, duchy, size, quality);
        
        ArrayList<String[]> expResult = new ArrayList();
        String[] line = new String[13];
        line[0] = "10";
        line[1] = "4";
        line[2] = "4";
        line[3] = "9";
        line[4] = "3";
        line[5] = "2,2,2,2,2,2,2,3,2;2,2,3,2,2,2,2,2,2;3,2,2,2,2,2,2,2,2;2,2,3,3,2,2,2,2,2;2,2,3,2,2,2,3,3,2;2,3,2,2,2,2,2,2,3;2,3,2,2,2,2,2,3,2;2,2,2,2,2,3,2,3,2;2,2,2,3,2,2,2,2,2;";
        line[6] = "0,0,0,-1,-1,-1,-1,-1,-1;0,0,0,-1,-1,-1,-1,-1,-1;0,0,0,-1,-1,-1,-1,-1,-1;-1,-1,-1,-1,-1,-1,-1,-1,-1;-1,-1,-1,-1,-1,-1,-1,-1,-1;-1,-1,-1,-1,-1,-1,-1,-1,-1;-1,-1,-1,-1,-1,-1,-1,-1,-1;-1,-1,-1,-1,-1,-1,-1,-1,-1;-1,-1,-1,-1,-1,-1,-1,-1,-1;";
        line[7] = "1.0";
        line[8] = "1";
        line[9] = "0";
        line[10] = "-25.0";
        line[11] = "0";
        line[12] = "80";
        expResult.add(line);
        
        boolean correct = false;
        for(int a = 0; a < result.size(); a++)
        {
            if(result.get(a)[0].equals(expResult.get(0)[0]) &&
                    result.get(a)[1].equals(expResult.get(0)[1]) &&
                    result.get(a)[2].equals(expResult.get(0)[2]) &&
                    result.get(a)[3].equals(expResult.get(0)[3]) &&
                    result.get(a)[4].equals(expResult.get(0)[4]) &&
                    result.get(a)[5].equals(expResult.get(0)[5]) &&
                    result.get(a)[6].equals(expResult.get(0)[6]) &&
                    result.get(a)[7].equals(expResult.get(0)[7]) &&
                    result.get(a)[8].equals(expResult.get(0)[8]) &&
                    result.get(a)[9].equals(expResult.get(0)[9]) &&
                    result.get(a)[10].equals(expResult.get(0)[10]) &&
                    result.get(a)[11].equals(expResult.get(0)[11]) &&
                    result.get(a)[12].equals(expResult.get(0)[12]))
                correct = true;
        }

        assertEquals(true, correct);
    }

    /**
     * Test of deletePlot method, of class PlotQueryHandler.
     * 
     * *Note: This function will remain unimplemented because we do not want
     * to delete a plot every time we test.
     */
    public void testDeletePlot() {
        //System.out.println("Testing deletePlot()");
    }
}
