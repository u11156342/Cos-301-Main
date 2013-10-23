/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class RequestUnit extends JPanel {

    public JTextPane statusArea = new JTextPane();
    String[] coms = {"Resolve", "Delete"};
    JComboBox commands = new JComboBox(coms);
    JButton next = new JButton("next");
    public int RequestID;
    public String RequestMessage;

    public RequestUnit(final TransferContainer tc,final ArrayList<String[]> ar) {
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        statusArea.setEditable(false);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = commands.getSelectedIndex();

                if (selectedIndex == 0) {
                    tc.rdb.approveRequest(RequestID);
                } else {
                    tc.rdb.deleteRequest(RequestID);
                }
                RequestInterface card = tc.Cmanager.getRequestInterfaceCard();
                card.init(tc,ar);
                tc.cardlayout.show(tc.contentpane, card.getName());
            }
        });


    }

    public void init(TransferContainer tc) {

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
                // + "border-top-color: #FF0000;"
                // + "border-right-color: #FF0000;"
                // + "border-bottom-color: #FF0000;"
                // + "border-left-color: #FF0000;"
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
        html.append("<tr><td class=\"ssheading\">RequestMessage</td><td> ").append(RequestMessage.replace(".", " ")).append("</td></tr>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        statusArea.setText(html.toString());

        JPanel temp = new JPanel();
        temp.setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        temp.setLayout(new GridLayout(0, 1));
        temp.add(commands);
        temp.add(next);

        add(statusArea, BorderLayout.CENTER);
        add(temp, BorderLayout.EAST);
    }
}
