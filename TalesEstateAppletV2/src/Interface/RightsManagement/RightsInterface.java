/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.RightsManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class RightsInterface extends BasePanel {

    public JTextPane textZone = new JTextPane();

    public RightsInterface(String name) {
        super(name);
    }

    public void init(final TransferContainer tc, int PlotID) {

        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        ArrayList<String[]> allAccess = tc.rdb.getAllAccess(PlotID);

        StringBuilder html = new StringBuilder();
        textZone.setContentType("text/html");
        textZone.setEditable(false);

        html.append("<h1> <font color=\"blue\">Property rights</font></h1>");
        
        html.append("<table border=\"1\">");
        html.append("<th>User Name </th>");
        html.append("<th>Deposit rights</th>");
        html.append("<th>Withdraw rights</th>");
        html.append("<th>Building purchase rights</th>");
        html.append("<th>Plot exspansion rights</th>");
        html.append("<th>Plot status view rights</th>");
        html.append("<th>Plot visual token placing rights</th>");

        for (int i = 0; i < allAccess.size(); i++) {
            html.append("<tr>");
            html.append("<td>").append(tc.rdb.retrieveCharacterName(Integer.parseInt(allAccess.get(i)[6]))).append("</td>");
            html.append("<td>").append(allAccess.get(i)[0]).append("</td>");
            html.append("<td>").append(allAccess.get(i)[1]).append("</td>");
            html.append("<td>").append(allAccess.get(i)[2]).append("</td>");
            html.append("<td>").append(allAccess.get(i)[4]).append("</td>");
            html.append("<td>").append(allAccess.get(i)[5]).append("</td>");
            html.append("<td>").append(allAccess.get(i)[3]).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        textZone.setText(html.toString());
        

        RightsSideMenu menu = new RightsSideMenu(textZone, tc,PlotID);
        menu.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT / 5, tc.JFXPANEL_HEIGHT_INT));
        add(menu, BorderLayout.EAST);


        add(scrollText, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MPlay");
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
