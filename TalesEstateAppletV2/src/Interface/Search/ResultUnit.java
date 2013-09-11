package Interface.Search;

import Interface.BrowseInterface.BrowseInterface;
import Interface.BrowseInterface.GameBrowseGrid;
import Interface.MyProperties.PlayerOwnPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import talesestateappletv2.TransferContainer;

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

    public void init(final TransferContainer tc) {
        
        final ArrayList<String> retrievePlotDetails = tc.rdb.retrievePlotDetails(propertyID);               
        
        statusArea.setPreferredSize(new Dimension(160,100));
        statusArea.append("Owner : " + retrievePlotDetails.get(1)+ "\n");
        statusArea.append("Plot Duchy : " + retrievePlotDetails.get(3)+ "\n");
        statusArea.append("Plot Size : " + retrievePlotDetails.get(4)+ "\n");

        Browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowseInterface bi=new BrowseInterface("Browse",tc,propertyID,size,tiles,buildings);
                tc.mainapplet.add(bi,bi.getName());
                tc.cardlayout.show(tc.contentpane,"Browse");
                
            }
        });
    }
}
