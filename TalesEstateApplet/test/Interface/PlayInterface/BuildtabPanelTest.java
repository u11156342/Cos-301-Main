/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import java.util.ArrayList;
import javax.swing.JComponent;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class BuildtabPanelTest extends TestCase {
    
    public BuildtabPanelTest(String testName) {
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
     * Test of makeTextPanel method, of class BuildtabPanel.
     */
    public void testMakeTextPanel() {
        System.out.println("makeTextPanel");
        String text = "Agricultural";
        int type = 0;
        ArrayList<String[]> arr = null;
        BuildtabPanel instance = new BuildtabPanel(1024,800,"Thegnheim");
        JComponent expResult = null;
        JComponent result = instance.makeTextPanel(text, type, arr);
        assertFalse(expResult==result);

    }
}
