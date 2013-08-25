/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.admin;

import Connections.RestFullDBAdapter;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

public class PlayerPropertiesAdmin extends JPanel {

    int w;
    int h;
    PlayerCurrentPropertiesAdmin[] playersCurrentProperties;
    public int amount;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();

    PlayerPropertiesAdmin(int i, UserCharacter user, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane,ArrayList<String[]> result) {

        w = i;


       // ArrayList<String[]> result = wrapper.retrieveAllPlots();
        playersCurrentProperties = new PlayerCurrentPropertiesAdmin[result.size()];
        amount = result.size();

        if (amount == 0) {
            h = 300;
            JOptionPane.showMessageDialog(contentPane, "There are currently no  properties");

        }

        h = amount * 70;
        for (int a = 0; a < playersCurrentProperties.length; a++) {
            playersCurrentProperties[a] = new PlayerCurrentPropertiesAdmin(w, this);
            playersCurrentProperties[a].setPreferredSize(new Dimension(257,100));
            playersCurrentProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);
            
            playersCurrentProperties[a].duchy = result.get(a)[2];
            playersCurrentProperties[a].owner = result.get(a)[1];
            playersCurrentProperties[a].size = Integer.parseInt(result.get(a)[3]);
            playersCurrentProperties[a].tiles = wrapper.convertFromArray(result.get(a)[5]);
            playersCurrentProperties[a].buildings = wrapper.convertFromArray(result.get(a)[6]);

            playersCurrentProperties[a].init(aThis, user, cardlayout, contentPane);
        }




        JComponent panel1 = makePanel("Panel #1", 1);
        panel1.setPreferredSize(new Dimension(w, h));
        add(panel1);
    }

    protected JComponent makePanel(String text, int type) {

        if (type == 1) {
            JPanel panel = new JPanel(false);

            for (int a = 0; a < playersCurrentProperties.length; a++) {
                playersCurrentProperties[a].setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(playersCurrentProperties[a]);
            }
            return panel;
        }
        return null;

    }
}
