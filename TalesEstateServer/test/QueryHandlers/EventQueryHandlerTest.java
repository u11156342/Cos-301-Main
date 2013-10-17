/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class EventQueryHandlerTest extends TestCase {
    
    public EventQueryHandlerTest(String testName) {
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
     * Test of addEvent method, of class EventQueryHandler.
     */
    public void testAddEvent() {
        System.out.println("addEvent");
        int plotID = 0;
        String eventName = "";
        String eventDescription = "";
        int platinumMod = 0;
        int goldMod = 0;
        int silverMod = 0;
        int happinessMod = 0;
        int incomeMod = 0;
        EventQueryHandler instance = null;
        boolean expResult = false;
        boolean result = instance.addEvent(plotID, eventName, eventDescription, platinumMod, goldMod, silverMod, happinessMod, incomeMod);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvent method, of class EventQueryHandler.
     */
    public void testGetEvent() {
        System.out.println("getEvent");
        int month = 0;
        int PlotID = 0;
        EventQueryHandler instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getEvent(month, PlotID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
