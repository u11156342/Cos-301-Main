package Interface.Search;

import Interface.BrowseInterface.BrowseInterface;
import Interface.BrowseInterface.GameBrowseGrid;
import Interface.MyProperties.PlayerOwnPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import talesestateappletv2.TransferContainer;

public class ResultUnit extends JPanel {

    public JTextPane statusArea = new JTextPane();
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
        statusArea.setContentType("text/html");
        StringBuilder html = new StringBuilder();

        statusArea.setPreferredSize(new Dimension(150, 100));
        StringTokenizer token = new StringTokenizer(retrievePlotDetails.get(1), "&*&");
        html.append("Owner : ").append(token.nextToken());
        html.append("<br>");
        html.append("Plot Name : ").append(retrievePlotDetails.get(18));
        html.append("<br>");
        html.append("Plot Duchy : ").append(retrievePlotDetails.get(3));
        html.append("<br>");






        // statusArea.append("Plot Size : " + (Integer.parseInt(retrievePlotDetails.get(4)))+ "\n");

        statusArea.setText(html.toString());

        Browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowseInterface bi = tc.Cmanager.getBrowseInterfacesCard();
                //public void init(final TransferContainer tc, int propertyIDz, int sizes, int[][] tilesz, int[][] buildingsz)
                bi.init(tc, propertyID, size, tiles, buildings);
                tc.cardlayout.show(tc.contentpane, bi.getName());

            }
        });
    }
}
