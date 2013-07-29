/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.BuyInterface;

import Connections.RestFullDBAdapter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author NightFiyah
 */
public class BuyInterfaceTest extends TestCase {

    public BuyInterfaceTest(String testName) {
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
     * Test of init method, of class BuyInterface.
     */
    public void testInit() {
        System.out.println("init");


        String duchyName = "Thegnheim";
        int sizeValue = 3;
        String quality = "Fine";
        int[][] groundArray = {{0, 0, 0}, {0, 0, 0}, {-1, -1, -1}};
        int[][] buildingArray = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        double acresUsed = 0.0;
        double acreMax = 0.0;
        int workersUsed = 0;
        int workerMax = 0;
        int happiness = 0;
        double monthlyIncome = 0.0;
        RestFullDBAdapter wrapper = new RestFullDBAdapter();
        long ti=System.currentTimeMillis();
        wrapper.registerEstateCharacter(ti+"");
        wrapper.addPlotToCharacter(ti+"", duchyName, 3, "Fine", groundArray, buildingArray, 1, 1, 0, workerMax, 0, monthlyIncome);

        ArrayList<String[]> results = null;
        int so = 0;
        while (so == 0) {
            try {
                Thread.sleep(3000);
                results = wrapper.retrievePlotsOwnedByCharacter(wrapper.retrieveCharacterID(ti+""));
                so = results.size();
            } catch (InterruptedException ex) {
                Logger.getLogger(BuyInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
