/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.admin;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

public class PlayerCurrentPropertiesAdmin extends JPanel {

    public JTextArea statusArea = new JTextArea();
    String[] coms = {"Add event", "View Property"};
    JComboBox commands = new JComboBox(coms);
    JButton bt = new JButton("next");
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    int[][] tiles;
    int[][] buildings;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();

    PlayerCurrentPropertiesAdmin(int w, PlayerPropertiesAdmin aThis) {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setColumns(4);
        statusArea.setEditable(false);
        
        c.gridx = 0;
        c.gridy=0;
        c.gridheight = 2;
        c.gridwidth = 2;

        add(statusArea, c);
        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx = 2;
        c.gridy = 0;
        add(commands, c);
        c.gridx = 2;
        c.gridy = 1;
        add(bt, c);
    }

    public void init(final TalesEstateApplet applet, final UserCharacter uchar, final CardLayout cardlayout, final Container contentPane) {
        statusArea.append("Property id : " + propertyID+"\n" + "Located in " + duchy);
        statusArea.append("" + '\n');
        if (quality
                == 1) {
            statusArea.append("Quality is : poor"+"\n"+"Current size is : " + size);
        } else if (quality
                == 2) {
            statusArea.append("Quality is : fine"+"\n"+"Current size is : " + size);
        } else if (quality
                == 3) {
            statusArea.append("Quality is : exquisite"+"\n"+"Current size is : " + size);
        }

    }
}
