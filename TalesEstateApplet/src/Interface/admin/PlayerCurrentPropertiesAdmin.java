/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.admin;

import Connections.RestFullDBAdapter;
import Interface.BuyInterface.Generator;
import Interface.ManageInterface.PlayerProperties;
import Interface.PlayInterface.PlayInterface;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        setPreferredSize(new Dimension(w - 100, 100));
        setBorder(BorderFactory.createLineBorder(Color.black));
        statusArea.setPreferredSize(new Dimension(500, 90));
        statusArea.setEditable(false);
        add(statusArea, BorderLayout.WEST);
        add(commands);
        add(bt);
    }

    public void init(final TalesEstateApplet applet, final UserCharacter uchar, final CardLayout cardlayout, final Container contentPane) {
        statusArea.append("Property id : " + propertyID + " located in " + duchy);
        statusArea.append("" + '\n');
        if (quality
                == 1) {
            statusArea.append("Quality is : poor, Current size is : " + size);
        } else if (quality
                == 2) {
            statusArea.append("Quality is : fine, Current size is : " + size);
        } else if (quality
                == 3) {
            statusArea.append("Quality is : exquisite, Current size is : " + size);
        }

    }
}
