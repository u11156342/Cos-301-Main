package Presentation.Search;


import Presentation.PlayerManagement.PlayerOwnPanel;
import guiv3.GuiV3;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ResultUnit extends JPanel {

    public JTextArea statusArea = new JTextArea();
    Button Browse = new Button("Browse plot");
    PlayerOwnPanel me;
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    public int[][] tiles;
    public int[][] buildings;

    ResultUnit(int w, ResultProperties aThis) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setPreferredSize(new Dimension(w - 100, 100));
        setBorder(BorderFactory.createLineBorder(Color.black));
        statusArea.setPreferredSize(new Dimension(500, 90));
        statusArea.setEditable(false);
        add(statusArea, BorderLayout.WEST);
        add(Browse);
    }

    public void init(final GuiV3 applet, final CardLayout cardlayout, final Container contentPane) {
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
