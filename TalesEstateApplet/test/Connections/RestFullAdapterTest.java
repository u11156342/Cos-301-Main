/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.awt.image.BufferedImage;
import static junit.framework.Assert.assertFalse;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class RestFullAdapterTest extends TestCase {
    
    public RestFullAdapterTest(String testName) {
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
     * Test of ImageAdapter method, of class RestFullAdapter.
     */
   /**
     * Test of ImageAdapter method, of class RestFullAdapter.
     */
    public void testImageAdapter() {
        System.out.println("ImageAdapter");
        int id = 0;
        RestFullAdapter instance = new RestFullAdapter();
        BufferedImage expResult = null;
        BufferedImage result = instance.ImageAdapter(id);
        assertFalse(expResult==result);
    }
}
