package Interface.SearchInterface;

import Connections.RestFullAdapter;
import Interface.ManageInterface.PlayerOwnPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import talesestateapplet.TalesEstateApplet;

public class ResultUnit extends JPanel {

    public JTextArea statusArea = new JTextArea();
    JButton Browse = new JButton("Browse plot");
    PlayerOwnPanel me;
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    public int[][] tiles;
    public int[][] buildings;

    ResultUnit(int w, ResultProperties aThis) {

        setBorder(BorderFactory.createLineBorder(Color.black));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setEditable(false);
        c.gridx = 1;
        add(statusArea, c);
        c.gridx = 2;
        
       // RestFullAdapter PicAdapter = new RestFullAdapter();
      //  Browse = new JButton(new ImageIcon(PicAdapter.ImageAdapter(20)));
       // Browse.setContentAreaFilled(false);
        Browse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(Browse, c);
    }

    public void init(final TalesEstateApplet applet, final CardLayout cardlayout, final Container contentPane) {
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


        Browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BrowseInterface browse = new BrowseInterface("Browse", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, propertyID, duchy, quality, size, tiles, buildings);
                applet.add(browse, browse.getName());
                cardlayout.show(contentPane, "Browse");
            }
        });
    }
}
