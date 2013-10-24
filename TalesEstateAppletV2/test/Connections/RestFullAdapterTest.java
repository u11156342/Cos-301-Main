package Connections;

import java.awt.image.BufferedImage;
import static junit.framework.Assert.assertFalse;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import talesestateappletv2.TransferContainer;

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
        TransferContainer tc = new TransferContainer();
        RestFullAdapter instance = tc.ad;
        BufferedImage expResult = null;
        BufferedImage result = instance.ImageAdapter(id);
        assertFalse(expResult == result);
    }
}
