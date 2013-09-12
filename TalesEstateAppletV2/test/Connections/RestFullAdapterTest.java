/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import java.awt.image.BufferedImage;
import static junit.framework.Assert.assertFalse;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author NightFiyah
 */
public class RestFullAdapterTest extends TestCase {
    
    public RestFullAdapterTest(String testName) {
        super(testName);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
   /**
     * Test of ImageAdapter method, of class RestFullAdapter.
     */
    public void testImageAdapter() {
        System.out.println("ImageAdapter");
        int id = 1;
        RestFullAdapter instance = new RestFullAdapter();
        BufferedImage expResult = null;
        BufferedImage result = instance.ImageAdapter(id);
        assertFalse(expResult==result);
    }
}
