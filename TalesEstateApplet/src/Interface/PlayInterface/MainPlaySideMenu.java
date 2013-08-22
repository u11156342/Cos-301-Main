/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import Interface.BuyInterface.Generator;
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

    public JButton Report, Deposite, Withdraw, exspand, listBuildings, addBuildings, VisualInterface;
    final MainPlaySideMenu ref = this;
    int size;
    int[][] tiles;
    int[][] buildings;

    public MainPlaySideMenu(final JTextPane textZone, final int PropertId, final TalesEstateApplet aThis, final CardLayout cardlayout, final Container contentPane, final String duchy, final UserCharacter uchar, final int PlotID, int sizes, int[][] tilesz, int[][] buildingsz) {
        size=sizes;
        tiles=tilesz;
        buildings=buildingsz;
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

                try {
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("How much do you wish to deposit"));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        Withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(JOptionPane.showInputDialog("How much do you wish to Withdraw"));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        exspand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestFullDBAdapter wrapper = new RestFullDBAdapter();
                Generator gen = new Generator(3);

                ArrayList<String> retrievePlotDetails = wrapper.retrievePlotDetails(PlotID);

  
                String[] choices = {"Poor", "Fine", "Exquisite"};


                String picked = (String) JOptionPane.showInputDialog(ref, "Choose what quality : ", "", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
                ArrayList<String[]> result;

                result = wrapper.queryPlotPrice(duchy, picked);
                int stat;

                stat = JOptionPane.showConfirmDialog(ref, "Will cost: Platinum:" + result.get(0)[0] + " Gold:" + result.get(0)[1] + " Silver:" + result.get(0)[2], "Purchase Confirmation", JOptionPane.YES_NO_OPTION);
                int quality = 0;

                picked = picked.toLowerCase();
                if ("poor".equals(picked)) {
                    quality = 1;
                } else if ("fine".equals(picked)) {
                    quality = 2;
                } else {
                    quality = 3;
                }


                int old = size;
                tiles = gen.ExspandGenerate(duchy, quality, size, tiles);
                size = tiles.length;

                if (old != size) {
                    System.out.println(buildings.length);
                    buildings = gen.ArrayCopy(buildings, old, size);
                    System.out.println(buildings.length);
                }




                wrapper.modifyPlot(PlotID, uchar.CharacterName, duchy, size, picked, tiles, buildings, Double.parseDouble(retrievePlotDetails.get(7)), Double.parseDouble(retrievePlotDetails.get(8)), Integer.parseInt(retrievePlotDetails.get(11)), Integer.parseInt(retrievePlotDetails.get(12)), Integer.parseInt(retrievePlotDetails.get(9)), Double.parseDouble(retrievePlotDetails.get(10)));

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
                html.append("<th>Building ID</th>");
                html.append("<th>Building name </th>");
                html.append("<th>Income</th>");
                html.append("<th>Happiness</th>");

                ArrayList<String[]> tempresult;
                for (int a = 0; a < retrieveAllBuildingsOwnedByCharacter.size(); a++) {
                    tempresult = wrapper.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)));
                    html.append("	<tr>");
                    html.append(" <td>").append(retrieveAllBuildingsOwnedByCharacter.get(a)).append("</td>");
                    html.append(" <td>").append(tempresult.get(0)[1]).append("</td>");
                    html.append(" <td>").append(tempresult.get(0)[6]).append("</td>");
                    html.append(" <td>").append(tempresult.get(0)[10]).append("</td>");
                    html.append("	</tr>");

                }

                html.append("</table>");

                textZone.setText(html.toString());
            }
        });


        addBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuildtabPanel Build = new BuildtabPanel("Build", duchy, PropertId, cardlayout, contentPane, uchar, PlotID, ref);
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
