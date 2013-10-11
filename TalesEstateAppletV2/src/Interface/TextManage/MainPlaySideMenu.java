/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.TextManage;

import Interface.BuyBuilding.BuildtabPanel;
import Interface.BuyProperty.Generator;
import Interface.PlayInterface.PlayInterface;
import Interface.RightsManagement.RightsInterface;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author NightFiyah
 */
public class MainPlaySideMenu extends JPanel {

    public JButton Report, Deposite, Withdraw, exspand, listBuildings, addBuildings, VisualInterface, RightsManagement, PropertyLog;
    final MainPlaySideMenu ref = this;
    int size;
    int[][] tiles;
    int[][] buildings;
    public ArrayList<String> amount1;
    public ArrayList<String> amount2;
    public int pId;
    String duchy_;

    public MainPlaySideMenu(final JTextPane textZone, final TransferContainer tc, int p, boolean isowner) {
        pId = p;
        Report = new JButton("Status Report");
        Deposite = new JButton("Deposit gold");
        Withdraw = new JButton("Withdraw gold");
        exspand = new JButton("Exspand");
        listBuildings = new JButton("List Buildings");
        addBuildings = new JButton("Add Building");
        VisualInterface = new JButton("Visual Interface");
        RightsManagement = new JButton("Rights Management");
        PropertyLog = new JButton("Estate Log");

        ArrayList<String> retrievePlotDetails = tc.rdb.retrievePlotDetails(pId);
        tiles = tc.rdb.convertFromArray(retrievePlotDetails.get(5));
        buildings = tc.rdb.convertFromArray(retrievePlotDetails.get(6));
        size = tiles.length;
        duchy_ = retrievePlotDetails.get(3);




        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (isowner) {
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

            c.gridy = 7;
            RightsManagement.setPreferredSize(new Dimension(150, 60));
            add(RightsManagement, c);

            c.gridy = 8;
            PropertyLog.setPreferredSize(new Dimension(150, 60));
            add(PropertyLog, c);

        } else {
            ArrayList<String> plotAccess = tc.rdb.getPlotAccess(pId, tc.CharacterID);
            System.out.println(plotAccess);

            boolean dep = false;
            boolean with = false;
            boolean buy = false;
            boolean pla = false;
            boolean ex = false;
            boolean st = false;

            if ("1".equals(plotAccess.get(0))) {
                dep = true;
            }

            if ("1".equals(plotAccess.get(1))) {
                with = true;
            }

            if ("1".equals(plotAccess.get(2))) {
                buy = true;
            }

            if ("1".equals(plotAccess.get(3))) {
                pla = true;
            }
            if ("1".equals(plotAccess.get(4))) {
                ex = true;
            }
            if ("1".equals(plotAccess.get(5))) {
                st = true;
            }

            System.out.println(dep + " " + with + " " + buy + " " + pla + " " + ex + " " + st);

            c.gridx = 2;
            c.gridy = 0;
            Report.setPreferredSize(new Dimension(150, 60));
            if (st) {
                add(Report, c);
            }
            c.gridx = 2;
            c.gridy = 1;
            // c.insets = new Insets(30, 0, 0, 0);
            Deposite.setPreferredSize(new Dimension(150, 60));
            if (dep) {
                add(Deposite, c);
            }
            c.gridx = 2;
            c.gridy = 2;
            //  c.insets = new Insets(30, 0, 0, 0);
            Withdraw.setPreferredSize(new Dimension(150, 60));
            if (with) {
                add(Withdraw, c);
            }
            c.gridx = 2;
            c.gridy = 3;
            // c.insets = new Insets(30, 0, 0, 0);
            exspand.setPreferredSize(new Dimension(150, 60));
            if (ex) {
                add(exspand, c);
            }
            c.gridy = 4;
            //  c.insets = new Insets(30, 0, 0, 0);

            listBuildings.setPreferredSize(new Dimension(150, 60));
            if (st || buy) {
                add(listBuildings, c);
            }
            c.gridy = 5;
            //  c.insets = new Insets(30, 0, 0, 0);

            addBuildings.setPreferredSize(new Dimension(150, 60));
            if (buy) {
                add(addBuildings, c);
            }
            c.gridy = 6;
            //  c.insets = new Insets(30, 0, 0, 0);

            VisualInterface.setPreferredSize(new Dimension(150, 60));
            if (pla) {
                add(VisualInterface, c);
            }

        }

        Report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textZone.setText(tc.rdb.getStatus(pId));
            }
        });
        Report.doClick();
        Deposite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount1 = tc.rdb.getCharacterAmounts(tc.CharacterName);
                amount2 = tc.rdb.getCurrentAmount(pId);
                double gold = Integer.parseInt(amount1.get(0)) * 10.0 + Integer.parseInt(amount1.get(1)) + (Integer.parseInt(amount1.get(2)) * 1.0 / 10);
                String mes = "How much gold do you wish to deposit, available gold " + gold;
                try {
                    double amountz = 0;
                    amountz = Double.parseDouble(JOptionPane.showInputDialog(mes));
                    if (amountz == 0) {
                        return;
                    }
                    if (gold >= amountz) {

                        int tempa = Integer.parseInt(amount2.get(0)) * 100 + Integer.parseInt(amount2.get(1)) * 10 + Integer.parseInt(amount2.get(2));
                        System.out.println(Integer.parseInt(amount2.get(0)) + " " + Integer.parseInt(amount2.get(1)) + " " + Integer.parseInt(amount2.get(2)));
                        //plots gold so it becomes more
                        // System.out.println(amountz);
                        tempa = tempa + (int) (amountz * 10);
                        int nplat = tempa / 100;
                        tempa = tempa - nplat * 100;
                        int ngold = tempa / 10;
                        tempa = tempa - ngold * 10;
                        int nsilver = tempa;
                        //plot 

                        tc.rdb.modifyAmount(pId, nplat, ngold, nsilver);
                        amount2 = tc.rdb.getCurrentAmount(pId);
                        System.out.println(Integer.parseInt(amount2.get(0)) + " " + Integer.parseInt(amount2.get(1)) + " " + Integer.parseInt(amount2.get(2)));

                        // users gold so it becomes less
                        tempa = Integer.parseInt(amount1.get(0)) * 100 + Integer.parseInt(amount1.get(1)) * 10 + Integer.parseInt(amount1.get(2));
                        System.out.println(Integer.parseInt(amount1.get(0)) + " " + Integer.parseInt(amount1.get(1)) + " " + Integer.parseInt(amount1.get(2)));
                        tempa = tempa - (int) amountz * 10;
                        nplat = tempa / 100;
                        tempa = tempa - nplat * 100;
                        ngold = tempa / 10;
                        tempa = tempa - ngold * 10;
                        nsilver = tempa;

                        //user 
                        tc.rdb.modifyAmount(tc.CharacterName, nplat, ngold, nsilver);
                        System.out.println(Integer.parseInt(amount1.get(0)) + " " + Integer.parseInt(amount1.get(1)) + " " + Integer.parseInt(amount1.get(2)));
                    } else {
                        JOptionPane.showMessageDialog(textZone, "You do not have enough gold");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                Report.doClick();
            }
        });
        Withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                amount1 = tc.rdb.getCharacterAmounts(tc.CharacterName);
                amount2 = tc.rdb.getCurrentAmount(pId);
                double gold = Integer.parseInt(amount2.get(0)) * 10.0 + Integer.parseInt(amount2.get(1)) + (Integer.parseInt(amount2.get(2)) * 1.0 / 10);
                String mes = "How much gold do you wish to Withdraw, available gold " + gold;
                try {
                    double amountz = 0;
                    amountz = Double.parseDouble(JOptionPane.showInputDialog(mes));
                    if (amountz == 0) {
                        return;
                    }
                    if (gold >= amountz) {

                        int tempa = Integer.parseInt(amount2.get(0)) * 100 + Integer.parseInt(amount2.get(1)) * 10 + Integer.parseInt(amount2.get(2));
                        System.out.println(Integer.parseInt(amount2.get(0)) + " " + Integer.parseInt(amount2.get(1)) + " " + Integer.parseInt(amount2.get(2)));
                        //plots gold so it becomes less
                        tempa = tempa - (int) (amountz * 10);
                        int nplat = tempa / 100;
                        tempa = tempa - nplat * 100;
                        int ngold = tempa / 10;
                        tempa = tempa - ngold * 10;
                        int nsilver = tempa;
                        //plot 

                        tc.rdb.modifyAmount(pId, nplat, ngold, nsilver);
                        amount2 = tc.rdb.getCurrentAmount(pId);
                        System.out.println(Integer.parseInt(amount2.get(0)) + " " + Integer.parseInt(amount2.get(1)) + " " + Integer.parseInt(amount2.get(2)));

                        // users gold so it becomes more
                        tempa = Integer.parseInt(amount1.get(0)) * 100 + Integer.parseInt(amount1.get(1)) * 10 + Integer.parseInt(amount1.get(2));
                        System.out.println(Integer.parseInt(amount1.get(0)) + " " + Integer.parseInt(amount1.get(1)) + " " + Integer.parseInt(amount1.get(2)));
                        tempa = tempa + (int) amountz * 10;
                        nplat = tempa / 100;
                        tempa = tempa - nplat * 100;
                        ngold = tempa / 10;
                        tempa = tempa - ngold * 10;
                        nsilver = tempa;

                        //user 
                        tc.rdb.modifyAmount(tc.CharacterName, nplat, ngold, nsilver);
                        System.out.println(Integer.parseInt(amount1.get(0)) + " " + Integer.parseInt(amount1.get(1)) + " " + Integer.parseInt(amount1.get(2)));
                    } else {
                        JOptionPane.showMessageDialog(textZone, "You do not have enough gold");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                Report.doClick();
            }
        });

        exspand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> retrievePlotDetails = tc.rdb.retrievePlotDetails(pId);

                String[] choices = {"Poor", "Fine", "Exquisite"};
                String picked = (String) JOptionPane.showInputDialog(ref, "Choose what quality : ", "", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

                ArrayList<String[]> cost;
                if (picked == null) {
                    return;
                }
                cost = tc.rdb.queryPlotPrice(retrievePlotDetails.get(3), picked);
                int stat;

                stat = JOptionPane.showConfirmDialog(ref, "Will cost: Platinum:" + cost.get(0)[0] + " Gold:" + cost.get(0)[1] + " Silver:" + cost.get(0)[2], "Purchase Confirmation", JOptionPane.YES_NO_OPTION);

                if (stat == 0) {

                    amount2 = tc.rdb.getCurrentAmount(pId);
                    //how much gold the plot has
                    int tempa = Integer.parseInt(amount2.get(0)) * 100 + Integer.parseInt(amount2.get(1)) * 10 + Integer.parseInt(amount2.get(2));

                    // the amoun the plot will cost
                    int costr = 100 * Integer.parseInt(cost.get(0)[0]) + 10 * Integer.parseInt(cost.get(0)[1]) + Integer.parseInt(cost.get(0)[2]);
                    System.out.println(tempa + " " + costr);
                    if (tempa >= costr) {

                        tempa = tempa - costr;

                        int nplat = tempa / 100;
                        tempa = tempa - nplat * 100;
                        int ngold = tempa / 10;
                        tempa = tempa - ngold * 10;
                        int nsilver = tempa;



                        Generator gen = new Generator(3);

                        tiles = tc.rdb.convertFromArray(retrievePlotDetails.get(5));
                        buildings = tc.rdb.convertFromArray(retrievePlotDetails.get(6));

                        int old = tiles.length;
                        System.out.println("SIZE BEFORE EXSPAND " + old);
                        int[][] tilesz = gen.ExspandGenerate(retrievePlotDetails.get(3), picked, size, tiles);
                        size = tilesz.length;
                        System.out.println("SIZE AFTER EXSPAND " + size);

                        //   if (old != size) {
                        //      buildings = gen.ArrayCopy(buildings, old, size);
                        //   }

                        int[][] temp = new int[buildings.length][buildings.length];
                        for (int a = 0; a < buildings.length; a++) {
                            System.arraycopy(buildings[a], 0, temp[a], 0, buildings.length);
                        }

                        buildings = new int[tilesz.length][tilesz.length];
                        for (int a = 0; a < temp.length; a++) {
                            System.arraycopy(temp[a], 0, buildings[a], 0, temp.length);
                        }



                        tc.rdb.expandPlot(pId, picked, tilesz, buildings);

                        //ok so now the property is exspanded in terms of acres and stuff but I still need to edit max workers and income

                        int workerMax;
                        int quality;
                        switch (picked) {
                            case "Poor":
                                quality = 1;
                                workerMax = 20;
                                break;
                            case "Fine":
                                quality = 2;
                                workerMax = 40;
                                break;
                            default:
                                quality = 3;
                                workerMax = 80;
                                break;
                        }
                        double Upkeep;
                        String pc;
                        duchy_ = retrievePlotDetails.get(3);
                        ArrayList<String> retrieveMonthlyUpkeep = tc.rdb.retrieveMonthlyUpkeep(retrievePlotDetails.get(3), picked);
                        pc = retrieveMonthlyUpkeep.get(0);
                        String gc = retrieveMonthlyUpkeep.get(1);
                        String sc = retrieveMonthlyUpkeep.get(2);

                        Upkeep = Double.parseDouble(retrievePlotDetails.get(8)) - (10 * Double.parseDouble(pc) + Double.parseDouble(gc) + Double.parseDouble(sc) / 10);

                        workerMax = workerMax + Integer.parseInt(retrievePlotDetails.get(10));
                        System.out.println(pc + " " + gc + " " + sc + " " + Upkeep);
                        // modifyPlot(int plotId, String characterName,int plotAmount, String duchyName, int sizeValue,int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome,int workersUsed, int workerMax, double exquisiteUsed,int exquisiteMax,double fineUsed,int fineMax,double poorUsed,int poorMax
                        //tc.rdb.modifyPlot(pId, retrievePlotDetails.get(1), retrievePlotDetails.get(2), retrievePlotDetails.get(3), tilesz.length, tilesz, buildings, Integer.parseInt(retrievePlotDetails.get(7)), Upkeep, Integer.parseInt(retrievePlotDetails.get(9)), workerMax, Double.parseDouble(retrievePlotDetails.get(11)), Integer.parseInt(retrievePlotDetails.get(12)), Double.parseDouble(retrievePlotDetails.get(13)), Integer.parseInt(retrievePlotDetails.get(14)), Double.parseDouble(retrievePlotDetails.get(15)), Integer.parseInt(retrievePlotDetails.get(16)), Double.parseDouble(retrievePlotDetails.get(17)));
                        tc.rdb.DoExspand(pId, Upkeep, workerMax);

                        tc.rdb.modifyAmount(pId, nplat, ngold, nsilver);

                        Report.doClick();

                    } else {
                        JOptionPane.showMessageDialog(textZone, "There is not enough funds in the property");
                    }




                }

            }
        });

        //use the property to get the info,then list all the buildins that are build on it

        listBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> retrievePlotDetails1 = tc.rdb.retrievePlotDetails(pId);

                ArrayList<String[]> retrieveAllBuildingsOwnedByCharacter = tc.rdb.retrieveAllBuildingsOwnedByCharacter(tc.rdb.retrieveCharacterID(retrievePlotDetails1.get(1)), pId);
                textZone.setText("");
                StringBuilder html = new StringBuilder();

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
                html.append("<h1>Estate buildings</h1>");

                if (retrieveAllBuildingsOwnedByCharacter.isEmpty()) {
                    html.append("<p>There are currently no buildings bought for this estate.</p>");
                } else {
                    html.append("<table>");
                    html.append("<th>Building ID</th>");
                    html.append("<th>Building name </th>");
                    html.append("<th>Income</th>");
                    html.append("<th>Happiness</th>");
                    html.append("<th>Status</th>");

                    ArrayList<String[]> tempresult;

                    for (int a = 0; a < retrieveAllBuildingsOwnedByCharacter.size(); a++) {
                        tempresult = tc.rdb.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[0]));
                        html.append("<tr>");
                        html.append("<td>").append(retrieveAllBuildingsOwnedByCharacter.get(a)[0]).append("</td>");
                        html.append("<td>").append(tempresult.get(0)[1]).append("</td>");
                        html.append("<td>").append(tempresult.get(0)[6]).append("</td>");
                        html.append("<td>").append(tempresult.get(0)[10]).append("</td>");
                        if ("0".equals(retrieveAllBuildingsOwnedByCharacter.get(a)[2])) {
                            html.append("<td>").append("Uncompleted").append("</td>");
                        } else {
                            html.append("<td>").append("Completed").append("</td>");
                        }

                        html.append("</tr>");
                    }
                }

                html.append("</table>");
                textZone.setText(html.toString());
            }
        });


        final MainPlaySideMenu tr = this;
        addBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tc.Build.init(pId, duchy_, tr);
                tc.cardlayout.show(tc.contentpane, "Build");
            }
        });
        VisualInterface.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PlayInterface card = tc.Cmanager.getPlayInterfacesCard();
                card.init(tc, pId);
                tc.cardlayout.show(tc.contentpane, card.getName());
            }
        });

        RightsManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RightsInterface card = tc.Cmanager.getRightsInterfacesCard();
                card.init(tc, pId);
                tc.cardlayout.show(tc.contentpane, card.getName());
            }
        });

        PropertyLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
}
