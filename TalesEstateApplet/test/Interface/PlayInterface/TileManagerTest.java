/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import java.awt.image.BufferedImage;
import static junit.framework.Assert.assertFalse;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class TileManagerTest extends TestCase {
    
    public TileManagerTest(String testName) {
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
     * Test of get method, of class TileManager.
     */
    public void testGet() throws Exception {
        System.out.println("get");
        TileManager instance = new TileManager();
        assertFalse(null==instance.get(1));
        assertFalse(null==instance.get(2));
        assertFalse(null==instance.get(3));
        assertFalse(null==instance.get(4));
        assertFalse(null==instance.get(5));
    }
}
