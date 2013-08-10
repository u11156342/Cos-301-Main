package Interface.ManageInterface;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import Interface.BuyInterface.Generator;
import Interface.PlayInterface.MainPlayInterface;
import Interface.PlayInterface.PlayInterface;
import talesestateapplet.UserCharacter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import talesestateapplet.TalesEstateApplet;

public class PlayerOwnPanel extends JPanel {

    public JTextArea statusArea = new JTextArea();
    JButton exspand = new JButton("Exspand plot");
    JButton play = new JButton("play");
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    public int[][] tiles;
    public int[][] buildings;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    RestFullAdapter picadapter = new RestFullAdapter();

    PlayerOwnPanel(int w, PlayerProperties aThis) {

        exspand = new JButton(new ImageIcon(picadapter.ImageAdapter(16)));
        exspand.setCursor(new Cursor(Cursor.HAND_CURSOR));
        play = new JButton(new ImageIcon(picadapter.ImageAdapter(17)));
        play.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setColumns(4);
        statusArea.setEditable(false);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;

        add(statusArea, c);
        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx = 2;
        c.gridy = 0;
        add(exspand, c);
        c.gridx = 2;
        c.gridy = 1;
        add(play, c);
    }

    public void init(final TalesEstateApplet applet, final UserCharacter uchar, final CardLayout cardlayout, final Container contentPane) {

        statusArea.append("Property id : " + propertyID + "\n" + "Located in " + duchy);
        statusArea.append("" + '\n');
        if (quality
                == 1) {
            statusArea.append("Quality is : poor" + "\n" + "Current size is : " + size);
        } else if (quality
                == 2) {
            statusArea.append("Quality is : fine" + "\n" + "Current size is : " + size);
        } else if (quality
                == 3) {
            statusArea.append("Quality is : exquisite" + "\n" + "Current size is : " + size);
        }

        play.addMouseListener(
                new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainPlayInterface play = new MainPlayInterface("MainPlay", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, propertyID, duchy, quality, size, tiles, buildings,applet,cardlayout,contentPane);
                applet.add(play, play.getName());
                cardlayout.show(contentPane, "MainPlay");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        exspand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Generator gen = new Generator(3);
                int old = size;
                tiles = gen.ExspandGenerate(duchy, quality, size, tiles);
                size = tiles.length;

                if (old != size) {
                    buildings = gen.ArrayCopy(buildings, old, size);
                }

                String q = "";
                if (quality == 1) {
                    q = "poor";
                } else if (quality == 2) {
                    q = "fine";
                } else {
                    q = "Exquisite";
                }
                ArrayList<String> retrievePlotDetails = wrapper.retrievePlotDetails(propertyID);

                wrapper.modifyPlot(propertyID, uchar.CharacterName, duchy, size, q, tiles, buildings, Double.parseDouble(retrievePlotDetails.get(7)), Double.parseDouble(retrievePlotDetails.get(8)), Integer.parseInt(retrievePlotDetails.get(11)), Integer.parseInt(retrievePlotDetails.get(12)), Integer.parseInt(retrievePlotDetails.get(9)), Double.parseDouble(retrievePlotDetails.get(10)));

                PlayInterface play = new PlayInterface("Play", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, propertyID, duchy, quality, size, tiles, buildings);
                applet.add(play, play.getName());
                cardlayout.show(contentPane, "Play");
            }
        });
    }
}
