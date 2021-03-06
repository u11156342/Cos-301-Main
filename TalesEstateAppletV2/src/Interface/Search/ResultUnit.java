package Interface.Search;

import Interface.BrowseInterface.BrowseInterface;
import Interface.MyProperties.PlayerOwnPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setEditable(false);
        c.gridx = 1;
        statusArea.setContentType("text/html");
        statusArea.setPreferredSize(new Dimension(300, 250));
        JScrollPane scrollText = new JScrollPane(statusArea);
        add(scrollText, c);
        c.gridx = 2;
        Browse.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(Browse, c);
    }

    public void init(final TransferContainer tc) {

        final ArrayList<String> retrievePlotDetails = tc.rdb.retrievePlotDetails(propertyID);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        StringBuilder html = new StringBuilder();
        statusArea.setContentType("text/html");
        html.append("<html>");
        html.append("<head>");

        /*
         * CSS for page
         */
        html.append("<style type=\"text/css\">");
        html.append("body{"
                + "font-family: \"century gothic\";"
                + "background-color: white;"
                + "border-right-width: 2px;"
                + "border-bottom-width: 2px;"
                + "border-left-width: 2px;"
                + "border-top-width: 2px;"
                + "border-top-style: solid;"
                + "border-right-style: solid;"
                + "border-bottom-style: solid;"
                + "border-left-style: solid;"
                + "border-top-color: #FF0000;"
                + "border-right-color: #FF0000;"
                + "border-bottom-color: #FF0000;"
                + "border-left-color: #FF0000;"
                + "}");
        html.append("h1{"
                + "text-align: center;"
                + "}");
        html.append("table{"
                + "width: 90%;"
                + "}");
        html.append("td{"
                + "width: 50%;"
                + "}");
        html.append("th{"
                + "text-align: left;"
                + "}");
        html.append(".hilight{"
                + "font-size: 12px;"
                + "}");
        html.append(".sheading{"
                + "font-size: 14px;"
                + "font-weight: bold;"
                + "}");
        html.append(".ssheading{"
                + "font-size: 10px;"
                + "font-weight: bold;"
                + "}");
        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<table>");
        try {
            html.append("<tr><td class=\"ssheading\">Owner</td><td>").append(retrievePlotDetails.get(1).substring(0, retrievePlotDetails.get(1).indexOf("&*&"))).append("</td></tr>");
        } catch (Exception e) {
            html.append("<tr><td class=\"ssheading\">Owner</td><td>").append(retrievePlotDetails.get(1)).append("</td></tr>");
        }
        html.append("<tr><td class=\"ssheading\">Estate Number</td><td> ").append(retrievePlotDetails.get(0)).append("</td></tr>");
        html.append("<tr><td class=\"ssheading\">Estate Name</td><td> ").append(retrievePlotDetails.get(18)).append("</td></tr>");
        html.append("<tr><td class=\"ssheading\">Duchy</td><td> ").append(retrievePlotDetails.get(3)).append("</td></tr>");
        try {
            html.append("<tr><td class=\"ssheading\">County</td><td> ").append(retrievePlotDetails.get(19)).append("</td></tr>");
        } catch (Exception e) {
        }
        html.append("<tr><td class=\"ssheading\">Description</td><td> ").append(tc.rdb.getDescription(propertyID)).append("</td></tr>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        statusArea.setText(html.toString());
        Browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowseInterface bi = tc.Cmanager.getBrowseInterfacesCard();
                tc.lastAdminBrowse = false;
                bi.init(tc, propertyID, size, tiles, buildings);
                tc.cardlayout.show(tc.contentpane, bi.getName());

            }
        });
    }
}
