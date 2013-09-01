/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateapplet.BasePanel;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

/**
 *
 * @author NightFiyah
 */
public class MainPlayInterface extends BasePanel {

    JTextPane textZone = new JTextPane();
    int size;
    String duchy;
    int quality;
    int propertyID;
    int[][] tiles;
    int[][] buildings;

    public MainPlayInterface(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, int propertyIDz, String duchys, int qual, int sizes, int[][] tilesz, int[][] buildingsz, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane,UserCharacter uchar) {
        super(name);
        
        textZone.setEditable(false);    
        size = sizes;
        duchy = duchys;
        quality = qual;
        propertyID = propertyIDz;
        tiles = tilesz;
        buildings = buildingsz;

        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(scrollText, BorderLayout.CENTER);

        MainPlaySideMenu menu = new MainPlaySideMenu(textZone,propertyID,aThis,cardlayout,contentPane,duchy,uchar,propertyIDz,sizes,tilesz,buildingsz);
        menu.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT / 5, JFXPANEL_HEIGHT_INT));
        add(menu, BorderLayout.EAST);

        textZone.setContentType("text/html"); 
        RestFullDBAdapter wrapper=new RestFullDBAdapter();

        textZone.setText(wrapper.getStatus(propertyIDz));


    }
}
