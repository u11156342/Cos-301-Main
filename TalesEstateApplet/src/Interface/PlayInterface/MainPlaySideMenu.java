/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

/**
 *
 * @author NightFiyah
 */
public class MainPlaySideMenu extends JPanel {

    JButton Report, Deposite, Withdraw, exspand, listBuildings, addBuildings, VisualInterface;

    public MainPlaySideMenu(final JTextPane textZone, final int PropertId, final TalesEstateApplet aThis, final CardLayout cardlayout, final Container contentPane, final String duchy, final UserCharacter uchar,final int PlotID) {
        Report = new JButton("Status Report");
        Deposite = new JButton("Deposit gold");
        Withdraw = new JButton("Withdraw gold");
        exspand = new JButton("Exspand");
        listBuildings = new JButton("List Buildings");
        addBuildings = new JButton("Add Building");
        VisualInterface = new JButton("Visual Interface");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 2;
        c.gridy = 0;
        Report.setPreferredSize(new Dimension(150, 60));
        add(Report, c);
        c.gridx = 2;
        c.gridy = 1;
        // c.insets = new Insets(30, 0, 0, 0);
        Deposite.setPreferredSize(new Dimension(150, 60));
        add(Deposite, c);
        c.gridx = 2;
        c.gridy = 2;
        //  c.insets = new Insets(30, 0, 0, 0);
        Withdraw.setPreferredSize(new Dimension(150, 60));
        add(Withdraw, c);
        c.gridx = 2;
        c.gridy = 3;
        // c.insets = new Insets(30, 0, 0, 0);
        exspand.setPreferredSize(new Dimension(150, 60));
        add(exspand, c);
        c.gridy = 4;
        //  c.insets = new Insets(30, 0, 0, 0);
        listBuildings.setPreferredSize(new Dimension(150, 60));
        add(listBuildings, c);
        c.gridy = 5;
        //  c.insets = new Insets(30, 0, 0, 0);
        addBuildings.setPreferredSize(new Dimension(150, 60));
        add(addBuildings, c);
        c.gridy = 6;
        //  c.insets = new Insets(30, 0, 0, 0);
        VisualInterface.setPreferredSize(new Dimension(150, 60));
        add(VisualInterface, c);


        Report.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RestFullDBAdapter wrapper = new RestFullDBAdapter();
                textZone.setText(wrapper.getStatus(PropertId));
            }
        });
        Deposite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                double amount = Double.parseDouble(JOptionPane.showInputDialog("How much do you wish to deposit"));
            }
        });
        Withdraw.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(JOptionPane.showInputDialog("How much do you wish to Withdraw"));
            }
        });

        exspand.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RestFullDBAdapter wrapper = new RestFullDBAdapter();
                textZone.setText(wrapper.getStatus(PropertId));
            }
        });

        //use the property to get the info,then list all the buildins that are build onit
        listBuildings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                RestFullDBAdapter wrapper = new RestFullDBAdapter();
                ArrayList<String> retrieveAllBuildingsOwnedByCharacter = wrapper.retrieveAllBuildingsOwnedByCharacter(uchar.CharacterID, PropertId);

                textZone.setText("");


                StringBuilder html = new StringBuilder();
                html.append("<h1> <font color=\"blue\">Current Plot Buildings</font></h1>");
                html.append("<table border=\"1\">");

                ArrayList<String[]> tempresult;
                for (int a = 0; a < retrieveAllBuildingsOwnedByCharacter.size(); a++) {
                    tempresult = wrapper.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)));
                    html.append("	<tr>");
                    html.append(" <td>").append(tempresult.get(0)[1]).append("</td>");
                    html.append("	</tr>");

                }

                html.append("</table>");

                textZone.setText(html.toString());
            }
        });

        addBuildings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BuildtabPanel Build = new BuildtabPanel("Build", duchy, PropertId, cardlayout, contentPane, uchar,PlotID);
                aThis.add(Build, Build.getName());
                cardlayout.show(contentPane, "Build");
            }
        });
        VisualInterface.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PlayInterface visual = new PlayInterface("visual", aThis.getWidth(), aThis.getHeight(), PropertId, uchar);
                aThis.add(visual, visual.getName());
                cardlayout.show(contentPane, "visual");
            }
        });

    }
}
