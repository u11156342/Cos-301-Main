package Interface.RightsManagement;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class RightsInterface extends BasePanel {

    public JTextPane textZone = new JTextPane();

    public RightsInterface(String name,TransferContainer tc) {
        super(name,tc);
    }

    public void init(final TransferContainer tc, int PlotID) {

        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ArrayList<String[]> allAccess = tc.rdb.getAllAccess(PlotID);
        StringBuilder html = new StringBuilder();
        textZone.setContentType("text/html");
        textZone.setEditable(false);
        html.append("<html>");
        html.append("<head>");

        /* CSS for page
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
        html.append("hr{"
                + ""
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
        html.append("p{"
                + "text-align: center;"
                + "}");
        html.append("th{"
                + "text-align: left;"
                + "}");
                
        html.append("</style>");
        html.append("</head>");
        
        html.append("<body>");
                
        html.append("<h1>Estate property rights</h1>");
        
        if(allAccess.size() == 0) {
            html.append("<p>There are currently no access rights set for this estate.</p>");
        }
        else {
        
            html.append("<table>");
            html.append("<th>User Name </th>");
            html.append("<th>Deposit rights</th>");
            html.append("<th>Withdraw rights</th>");
            html.append("<th>Building purchase rights</th>");
            html.append("<th>Plot exspansion rights</th>");
            html.append("<th>Plot status view rights</th>");
            html.append("<th>Plot visual token placing rights</th>");

            for (int i = 0; i < allAccess.size(); i++) {
                html.append("<tr>");
                String t=tc.rdb.retrieveCharacterName(Integer.parseInt(allAccess.get(i)[6]));
                html.append("<td>").append(t.substring(0,t.indexOf("&*&"))).append("</td>");
                html.append("<td>").append(allAccess.get(i)[0]).append("</td>");
                html.append("<td>").append(allAccess.get(i)[1]).append("</td>");
                html.append("<td>").append(allAccess.get(i)[2]).append("</td>");
                html.append("<td>").append(allAccess.get(i)[4]).append("</td>");
                html.append("<td>").append(allAccess.get(i)[5]).append("</td>");
                html.append("<td>").append(allAccess.get(i)[3]).append("</td>");
                html.append("</tr>");
            }

            html.append("</table>");
        }
        textZone.setText(html.toString());       

        RightsSideMenu menu = new RightsSideMenu(textZone, tc,PlotID);
        menu.setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        add(menu, BorderLayout.EAST);

        add(scrollText, BorderLayout.CENTER);
        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, tc.Cmanager.MainPlayInterfaces[tc.Cmanager.currentMainPlayInterfaceCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
