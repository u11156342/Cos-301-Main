/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class ConverterTest extends TestCase {

    public ConverterTest(String testName) {
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
     * Test of ArrFromUrl method, of class Converter.
     */
    public void testArrFromUrl() {
        System.out.println("ArrFromUrl");
        String link = "t1@t2@t3" + '\n' + "r1@r2@r3" + '\n';
        Converter instance = new Converter();
        ArrayList<String[]> expResult = new ArrayList();

        String[] temp = new String[3];
        temp[0] = "t1";
        temp[1] = "t2";
        temp[2] = "t3";

        expResult.add(temp);
        String[] temp2 = new String[3];
        temp2[0] = "r1";
        temp2[1] = "r2";
        temp2[2] = "r3";
        expResult.add(temp2);

        ArrayList<String[]> result = instance.ArrFromUrl(link);
        assertEquals(expResult.get(0)[0], result.get(0)[0]);
        assertEquals(expResult.get(0)[1], result.get(0)[1]);
        assertEquals(expResult.get(0)[2], result.get(0)[2]);
        assertEquals(expResult.get(1)[0], result.get(1)[0]);
        assertEquals(expResult.get(1)[1], result.get(1)[1]);
        assertEquals(expResult.get(1)[2], result.get(1)[2]);
    }

    /**
     * Test of FromUrl method, of class Converter.
     */
    public void testFromUrl() {
        System.out.println("FromUrl");
        String link = "t1" + '\n' + "t2" + '\n' + "t3";
        Converter instance = new Converter();

        ArrayList<String> expResult = new ArrayList();

        expResult.add("t1");
        expResult.add("t2");
        expResult.add("t3");

        ArrayList result = instance.FromUrl(link);
        assertEquals(expResult.get(0), result.get(0));
        assertEquals(expResult.get(1), result.get(1));
        assertEquals(expResult.get(2), result.get(2));
    }

    /**
     * Test of ArrToUrl method, of class Converter.
     */
    public void testArrToUrl() {
        System.out.println("ArrToUrl");
        ArrayList<String[]> lis = new ArrayList();

        String[] temp = new String[3];
        temp[0] = "t1";
        temp[1] = "t2";
        temp[2] = "t3";

        lis.add(temp);
        String[] temp2 = new String[3];
        temp2[0] = "r1";
        temp2[1] = "r2";
        temp2[2] = "r3";
        lis.add(temp2);

        Converter instance = new Converter();
        String expResult = "t1@t2@t3" + '\n' + "r1@r2@r3" + '\n';
        String result = instance.ArrToUrl(lis);
        assertEquals(expResult, result);
    }

    /**
     * Test of ToUrl method, of class Converter.
     */
    public void testToUrl() {
        System.out.println("ToUrl");
        ArrayList<String> list = new ArrayList();

        list.add("t1");
        list.add("t2");
        list.add("t3");
        Converter instance = new Converter();
        String expResult = "t1" + '\n' + "t2" + '\n' + "t3";
        String result = instance.ToUrl(list);
        assertEquals(expResult, result);
    }
}
