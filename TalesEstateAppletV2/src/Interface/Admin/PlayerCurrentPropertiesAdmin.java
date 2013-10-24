package Interface.Admin;

import Interface.BrowseInterface.BrowseInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import talesestateappletv2.TransferContainer;

//A single property that is returned by admins search
public class PlayerCurrentPropertiesAdmin extends JPanel {

    public JTextPane statusArea = new JTextPane();
    String[] coms = {"Status", "Add event", "View Plot", "Set Description"};
    JComboBox commands = new JComboBox(coms);
    JButton bt = new JButton("next");
    String owner;
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    int[][] tiles;
    int[][] buildings;

    PlayerCurrentPropertiesAdmin(int w, PlayerPropertiesAdmin aThis, final TransferContainer t) {
        statusArea.setEditable(false);
        statusArea.setPreferredSize(new Dimension(300, 250));
        setBackground(java.awt.Color.getHSBColor(t.c1[0], t.c1[1], t.c1[2]));
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = commands.getSelectedIndex();

                if (selectedIndex == 0) {
                    DetailedStatus ds = t.Cmanager.getDetailedStatusCard();
                    ds.init(propertyID, t);
                    t.cardlayout.show(t.contentpane, ds.getName());
                } else if (selectedIndex == 1) {
                    AddEvent ev = t.Cmanager.getAddEventCard();
                    final ArrayList<String> retrievePlotDetails = t.rdb.retrievePlotDetails(propertyID);
                    ev.init(t, propertyID, (int) Double.parseDouble(retrievePlotDetails.get(17)));
                    t.cardlayout.show(t.contentpane, ev.getName());
                } else if (selectedIndex == 2) {
                    System.out.println(propertyID);
                    final ArrayList<String> retrievePlotDetails = t.rdb.retrievePlotDetails(propertyID);
                    size = Integer.parseInt("" + retrievePlotDetails.get(4));
                    tiles = t.rdb.convertFromArray("" + retrievePlotDetails.get(5));
                    buildings = t.rdb.convertFromArray("" + retrievePlotDetails.get(6));
                    t.lastAdminBrowse = true;
                    BrowseInterface bi = t.Cmanager.getBrowseInterfacesCard();
                    bi.init(t, propertyID, size, tiles, buildings);
                    t.cardlayout.show(t.contentpane, bi.getName());
                } else if (selectedIndex == 3) {
                    String showInputDialog = JOptionPane.showInputDialog("What is the new description");

                    if ("".equals(showInputDialog)) {
                        JOptionPane.showMessageDialog(commands, "Please enter a description");
                    } else {
                        String desu = showInputDialog;
                        String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~:[]@!$&()*+,;=";
                        String validurl = "";
                        for (int i = 0; i < desu.length(); i++) {

                            if (valid.contains("" + desu.charAt(i))) {
                                validurl = validurl + desu.charAt(i);
                            } else {
                                validurl = validurl + "~";
                            }
                        }
                        try {
                            t.rdb.setDescription(validurl, propertyID);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(commands, "Something went wrong with set the description, please avoid using any special characters");
                        }
                    }
                }


            }
        });
    }

    public void init(TransferContainer t) {

        ArrayList<String> retrievePlotDetails = t.rdb.retrievePlotDetails(propertyID);


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
        html.append("<tr><td class=\"ssheading\">Owner</td><td>").append(retrievePlotDetails.get(1).substring(0, retrievePlotDetails.get(1).indexOf("&*&"))).append("</td></tr>");
        html.append("<tr><td class=\"ssheading\">Estate Number</td><td> ").append(retrievePlotDetails.get(0)).append("</td></tr>");
        html.append("<tr><td class=\"ssheading\">Estate Name</td><td> ").append(retrievePlotDetails.get(18).replace(".", " ")).append("</td></tr>");
        html.append("<tr><td class=\"ssheading\">Duchy</td><td> ").append(retrievePlotDetails.get(3)).append("</td></tr>");
        try {
            html.append("<tr><td class=\"ssheading\">County</td><td> ").append(retrievePlotDetails.get(19)).append("</td></tr>");
        } catch (Exception e) {
        }
        html.append("<tr><td class=\"ssheading\">Happiness</td><td>").append(retrievePlotDetails.get(7)).append("</td></tr>");
        html.append("<tr><td class=\"ssheading\">Income</td><td>").append(retrievePlotDetails.get(8)).append("</td></tr>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        statusArea.setText(html.toString());
        JScrollPane scrollText = new JScrollPane(statusArea);
        add(scrollText, BorderLayout.NORTH);
        JPanel temp = new JPanel();
        GridLayout experimentLayout = new GridLayout(0, 1);
        temp.setLayout(experimentLayout);
        temp.add(commands);
        temp.add(bt);
        temp.setBackground(java.awt.Color.getHSBColor(t.c1[0], t.c1[1], t.c1[2]));
        add(temp, BorderLayout.SOUTH);
    }
}