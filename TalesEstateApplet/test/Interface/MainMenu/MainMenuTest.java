/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.MainMenu;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import junit.framework.TestCase;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

/**
 *
 * @author NightFiyah
 */
public class MainMenuTest extends TestCase {

    public MainMenuTest(String testName) {
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
     * Test of init method, of class MainMenu.
     */
    public void testInit() {
        System.out.println("init");
        int JFXPANEL_WIDTH_INT = 1024;
        int JFXPANEL_HEIGHT_INT = 800;
        MainMenu instance = new MainMenu("Main Menu", JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);
        instance.init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);
    }

    /**
     * Test of addNextActionListener method, of class MainMenu.
     */
    public void testAddNextActionListener() {
        System.out.println("addNextActionListener");
    }

    /**
     * Test of showMenu method, of class MainMenu.
     */
    public void testShowMenu() {
    }
}
