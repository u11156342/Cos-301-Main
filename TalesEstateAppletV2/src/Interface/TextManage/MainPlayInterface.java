/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.TextManage;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;


/**
 *
 * @author NightFiyah
 */
public class MainPlayInterface extends BasePanel {

    public JTextPane textZone = new JTextPane();
    public int size;
    public String duchy;
    public int propertyID;
    public int[][] tiles;
    public int[][] buildings;
    public ArrayList<String[]> quality;
    public ArrayList<String> amount;

    public MainPlayInterface(String name,TransferContainer tc,int Pid) {
        super(name);
        
        textZone.setEditable(false); 

        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(scrollText, BorderLayout.CENTER);

        MainPlaySideMenu menu = new MainPlaySideMenu(textZone,tc,Pid);
        menu.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT / 5, tc.JFXPANEL_HEIGHT_INT));
        add(menu, BorderLayout.EAST);

        textZone.setContentType("text/html"); 

    }
}
