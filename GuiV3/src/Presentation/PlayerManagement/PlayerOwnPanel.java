package Presentation.PlayerManagement;

import Data.Communication.RestFullDBAdapter;
import GridGenerator.Generator;
import Presentation.PlayMap.PlayInterface;
import guiv3.GuiV3;
import guiv3.UserCharacter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerOwnPanel extends JPanel {

    public JTextArea statusArea = new JTextArea();
    Button exspand = new Button("Exspand plot");
    PlayerOwnPanel me;
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    public int[][] tiles;
    public int[][] buildings;
    
    RestFullDBAdapter wrapper=new RestFullDBAdapter();

    PlayerOwnPanel(int w, PlayerProperties aThis) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        setPreferredSize(new Dimension(w - 100, 100));
        setBorder(BorderFactory.createLineBorder(Color.black));
        statusArea.setPreferredSize(new Dimension(500, 90));
        statusArea.setEditable(false);
        add(statusArea, BorderLayout.WEST);
        add(exspand);
    }

    public void init(final GuiV3 applet, final UserCharacter uchar, final CardLayout cardlayout, final Container contentPane) {
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

        statusArea.addMouseListener(
                new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PlayInterface play = new PlayInterface("Play", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, propertyID, duchy, quality, size, tiles, buildings);
                applet.add(play, play.getName());
                cardlayout.show(contentPane, "Play");
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
                
                wrapper.modifyPlot(propertyID, uchar.CharacterName, duchy, size, q, tiles, buildings,Double.parseDouble(retrievePlotDetails.get(7)) , Double.parseDouble(retrievePlotDetails.get(8)), Integer.parseInt(retrievePlotDetails.get(11)), Integer.parseInt(retrievePlotDetails.get(12)), Integer.parseInt(retrievePlotDetails.get(9)), Double.parseDouble(retrievePlotDetails.get(10)));

                PlayInterface play = new PlayInterface("Play", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, propertyID, duchy, quality, size, tiles, buildings);
                applet.add(play, play.getName());
                cardlayout.show(contentPane, "Play");
            }
        });
    }
}
