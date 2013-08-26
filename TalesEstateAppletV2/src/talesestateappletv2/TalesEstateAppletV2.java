/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package talesestateappletv2;

import Interface.BuyProperty.BuyInterface;
import Interface.MainMenu.CharSelectMenu;
import Interface.MainMenu.MainMenu;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    BuyInterface Buy;
    MainMenu MainMenu;
    CharSelectMenu CSelect;

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

                tContain.JFXPANEL_WIDTH_INT=JFXPANEL_WIDTH_INT;
                tContain.JFXPANEL_HEIGHT_INT=JFXPANEL_HEIGHT_INT;
                tContain.mainframe = new JFrame("ToT Estate System");
                tContain.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                tContain.mainapplet = new TalesEstateAppletV2();
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

        CSelect = new CharSelectMenu("Char", tContain);

        MainMenu = new MainMenu("MainMenu", tContain);
        MainMenu.addNextActionListener(buttonListener, tContain);

        CSelect.addNextActionListener(tContain, MainMenu);


        Buy = new BuyInterface("Buy", tContain);
        Buy.addNextActionListener(buttonListener);


        add(CSelect, CSelect.getName());
        add(MainMenu, MainMenu.getName());
        add(Buy, Buy.getName());
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
