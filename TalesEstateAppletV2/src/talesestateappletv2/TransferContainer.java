/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package talesestateappletv2;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import java.awt.CardLayout;
import java.awt.Container;
import javafx.embed.swing.JFXPanel;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * @author NightFiyah
 */
public class TransferContainer {

    public int JFXPANEL_WIDTH_INT = 1024;
    public int JFXPANEL_HEIGHT_INT = 768;
    public JFXPanel fxContainer;
    public CardLayout cardlayout;
    public int CharacterID;
    public String CharacterName;
    public JFrame mainframe;
    public JApplet mainapplet;
    public RestFullAdapter ad=new RestFullAdapter();
    public RestFullDBAdapter rdb=new RestFullDBAdapter();
    public Container contentpane;

    public TransferContainer() {
        cardlayout = new CardLayout();
    }
}
