/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package talesestateappletv2;

import Interface.Admin.AdminMenu;
import Interface.BuyBuilding.BuildtabPanel;
import Interface.BuyProperty.BuyInterface;
import Interface.MainMenu.CharSelectMenu;
import Interface.MainMenu.MainMenu;
import Interface.MyProperties.MyPropertiesInterface;
import Interface.PlayInterface.PlayInterface;
import Interface.PlayerManagement.PlayerManagementInterface;
import Interface.RightsManagement.RightsInterface;
import Interface.Search.MainSearch;
import Interface.TextManage.MainPlayInterface;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author NightFiyah
 */
public class TalesEstateAppletV2 extends JApplet {

    public static final int JFXPANEL_WIDTH_INT = 1024;
    public static final int JFXPANEL_HEIGHT_INT = 768;
    private static JFXPanel fxContainer;
    private static TransferContainer tContain = new TransferContainer();
    private static TalesEstateAppletV2 me;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {
                }

                tContain.JFXPANEL_WIDTH_INT = JFXPANEL_WIDTH_INT;
                tContain.JFXPANEL_HEIGHT_INT = JFXPANEL_HEIGHT_INT;
                tContain.mainframe = new JFrame("ToT Estate System");
                tContain.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                me= new TalesEstateAppletV2();
                tContain.mainapplet = me;
                
                tContain.mainapplet.init();
                tContain.mainframe.setContentPane(tContain.mainapplet.getContentPane());
                tContain.mainframe.pack();
                tContain.mainframe.setLocationRelativeTo(null);
                tContain.mainframe.setVisible(true);
                tContain.mainapplet.start();
            }
        });
    }

    @Override
    public void init() {
        setLayout(tContain.cardlayout);
        tContain.contentpane = this.getContentPane();
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                tContain.cardlayout.next(getContentPane());
            }
        };

        tContain.CSelect = new CharSelectMenu("Char", tContain);

        tContain.MainMenu = new MainMenu("MainMenu", tContain,me);
        tContain.MainMenu.addNextActionListener(buttonListener, tContain);

        tContain.CSelect.addNextActionListener(tContain, tContain.MainMenu);


        tContain.Buy = new BuyInterface("Buy", tContain);
        tContain.Buy.addNextActionListener(buttonListener);

        tContain.search = new MainSearch("Search", tContain);


        tContain.mProp = new MyPropertiesInterface("MyProp", tContain);


        tContain.am = new AdminMenu("AMain", tContain);

        tContain.mp = new MainPlayInterface("MPlay");

        tContain.visual = new PlayInterface("visual", tContain);
        
        tContain.Build = new BuildtabPanel("Build", tContain);


        tContain.ri = new RightsInterface("right", tContain);

        tContain.pmI=new PlayerManagementInterface("PlayerMan");

        add(tContain.CSelect, tContain.CSelect.getName());
        add(tContain.MainMenu, tContain.MainMenu.getName());
        add(tContain.Buy, tContain.Buy.getName());
        add(tContain.search, tContain.search.getName());
       // add(tContain.mProp, tContain.mProp.getName());
        add(tContain.am, tContain.am.getName());
      //  add(tContain.mp, tContain.mp.getName());
        add(tContain.Build, tContain.Build.getName());
        add(tContain.visual, tContain.visual.getName());
        add(tContain.ri, tContain.ri.getName());
        add(tContain.pmI, tContain.pmI.getName());

        revalidate();
        repaint();

        fxContainer = new JFXPanel();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createScene();
            }
        });
    }

    private void createScene() {
        StackPane root = new StackPane();
        fxContainer.setScene(new Scene(root));
    }
}
