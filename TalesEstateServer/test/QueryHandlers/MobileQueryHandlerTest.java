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
public class MobileQueryHandlerTest extends TestCase {
    
    public MobileQueryHandlerTest(String testName) {
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
     * Test of getid method, of class MobileQueryHandler.
     */
    public void testGetid() throws Exception {
        System.out.println("getid");
        String name = "";
        MobileQueryHandler instance = new MobileQueryHandler();
        String expResult = "";
        String result = instance.getdetails(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
