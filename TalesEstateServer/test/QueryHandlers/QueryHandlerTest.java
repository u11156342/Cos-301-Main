/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class QueryHandlerTest extends TestCase {
    
    public QueryHandlerTest(String testName) {
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
     * Test of closeConnection method, of class QueryHandler.
     */
    public void testCloseConnection() {
        System.out.println("closeConnection");
        QueryHandler instance = null;
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogQH method, of class QueryHandler.
     */
    public void testGetLogQH() {
        System.out.println("getLogQH");
        QueryHandler instance = null;
        LogQueryHandler expResult = null;
        LogQueryHandler result = instance.getLogQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBuildingQH method, of class QueryHandler.
     */
    public void testGetBuildingQH() {
        System.out.println("getBuildingQH");
        QueryHandler instance = null;
        BuildingQueryHandler expResult = null;
        BuildingQueryHandler result = instance.getBuildingQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCharacterQH method, of class QueryHandler.
     */
    public void testGetCharacterQH() {
        System.out.println("getCharacterQH");
        QueryHandler instance = null;
        CharacterQueryHandler expResult = null;
        CharacterQueryHandler result = instance.getCharacterQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDuchyQH method, of class QueryHandler.
     */
    public void testGetDuchyQH() {
        System.out.println("getDuchyQH");
        QueryHandler instance = null;
        DuchyQueryHandler expResult = null;
        DuchyQueryHandler result = instance.getDuchyQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlotQH method, of class QueryHandler.
     */
    public void testGetPlotQH() {
        System.out.println("getPlotQH");
        QueryHandler instance = null;
        PlotQueryHandler expResult = null;
        PlotQueryHandler result = instance.getPlotQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriceQH method, of class QueryHandler.
     */
    public void testGetPriceQH() {
        System.out.println("getPriceQH");
        QueryHandler instance = null;
        PriceQueryHandler expResult = null;
        PriceQueryHandler result = instance.getPriceQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPictureQH method, of class QueryHandler.
     */
    public void testGetPictureQH() {
        System.out.println("getPictureQH");
        QueryHandler instance = null;
        PictureQueryHandler expResult = null;
        PictureQueryHandler result = instance.getPictureQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserQH method, of class QueryHandler.
     */
    public void testGetUserQH() {
        System.out.println("getUserQH");
        QueryHandler instance = null;
        UserQueryHandler expResult = null;
        UserQueryHandler result = instance.getUserQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventQH method, of class QueryHandler.
     */
    public void testGetEventQH() {
        System.out.println("getEventQH");
        QueryHandler instance = null;
        EventQueryHandler expResult = null;
        EventQueryHandler result = instance.getEventQH();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
