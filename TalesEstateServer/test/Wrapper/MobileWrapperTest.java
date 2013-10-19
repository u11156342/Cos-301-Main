/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import javax.ejb.embeddable.EJBContainer;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class MobileWrapperTest extends TestCase {
    
    public MobileWrapperTest(String testName) {
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
     * Test of getnames method, of class MobileWrapper.
     */
    public void testGetnames() throws Exception {
        System.out.println("getnames");
        String userName = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MobileWrapper instance = (MobileWrapper)container.getContext().lookup("java:global/classes/MobileWrapper");
        String expResult = "";
        String result = instance.getnames(userName);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getid method, of class MobileWrapper.
     */
    public void testGetid() throws Exception {
        System.out.println("getid");
        String userid = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MobileWrapper instance = (MobileWrapper)container.getContext().lookup("java:global/classes/MobileWrapper");
        String expResult = "";
        String result = instance.getdetails(userid);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
