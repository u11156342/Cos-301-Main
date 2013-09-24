package Interface.BuyBuilding;

import Connections.RestFullDBAdapter;
import Interface.TextManage.MainPlaySideMenu;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class BuildtabPanel extends BasePanel {

    String duc;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    ArrayList<String[]> results1 = null;
    ArrayList<String[]> result2 = null;
    CardLayout Cardlayout;
    Container ContentPane;
    int PlotID;
    TransferContainer tain;

    public BuildtabPanel(String build, TransferContainer tc) {
        super(build);
        tain = tc;
    }

    public void init(int pId, String duchy_, MainPlaySideMenu tr) {

        PlotID = pId;
        duc = duchy_;
        JTabbedPane tabbedPane = new JTabbedPane();

        ArrayList<String[]> Agricultural = null;
        ArrayList<String[]> Mining = null;
        ArrayList<String[]> Manufacturing = null;
        ArrayList<String[]> Services = null;
        ArrayList<String[]> Improvements = null;

        JComponent panel1 = makeTextPanel("Agricultural", 0, Agricultural, tr);
        tabbedPane.addTab("Agricultural", null, panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Mining", 1, Mining, tr);
        tabbedPane.addTab("Mining", null, panel2);

        JComponent panel3 = makeTextPanel("Manufacturing", 2, Manufacturing, tr);
        tabbedPane.addTab("Manufacturing", null, panel3);

        JComponent panel4 = makeTextPanel("Services", 3, Services, tr);
        tabbedPane.addTab("Services", null, panel4);

        JComponent panel5 = makeTextPanel("Improvements", 4, Improvements, tr);
        tabbedPane.addTab("Improvements", null, panel5);
        add(tabbedPane, BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tain.cardlayout.show(tain.contentpane, "MainMenu");
            }
        });

        add(back, BorderLayout.SOUTH);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text, int type, ArrayList<String[]> arr, final MainPlaySideMenu tr) {
        JPanel panel = new JPanel(false);


        final JTextArea buildingIn = new JTextArea();

        String[] buildingsList;
        final int[] buildingsID;
        if (arr == null) {
            arr = tain.rdb.listBuildingBy(duc, text);
        }
        results1 = arr;
        buildingsList = new String[results1.size()];
        buildingsID = new int[results1.size()];

        for (int a = 0; a < results1.size(); a++) {
            buildingsID[a] = Integer.parseInt(results1.get(a)[0]);
            buildingsList[a] = results1.get(a)[1];
        }

        JPanel buildingsPanel = new JPanel();
        buildingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        final JComboBox buildings = new JComboBox(buildingsList);
        result2 = tain.rdb.retrieveBuildingDetailsById(buildingsID[0]);
        String temp = "";
        temp = temp + "Building Cost : " + result2.get(0)[4] + '\n';
        temp = temp + "Building Setup Cost : " + result2.get(0)[5] + '\n';
        temp = temp + "Building Monthly Income : " + result2.get(0)[6] + '\n';
        temp = temp + "Building Workers Needed : " + result2.get(0)[7] + '\n';
        temp = temp + "Building Time To Build : " + result2.get(0)[8] + '\n';
        temp = temp + "Building Size Required : " + result2.get(0)[9] + '\n';
        temp = temp + "Building Happiness : " + result2.get(0)[10] + '\n';

        buildingIn.append(temp);
        buildingIn.setEditable(false);
        buildings.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                buildingIn.setText("");
                int id = buildingsID[buildings.getSelectedIndex()];
                ArrayList<String[]> r2 = tain.rdb.retrieveBuildingDetailsById(id);
                String temp = "";
                temp = temp + "Building Cost : " + r2.get(0)[4] + '\n';
                temp = temp + "Building Setup Cost : " + r2.get(0)[5] + '\n';
                temp = temp + "Building Monthly Income : " + r2.get(0)[6] + '\n';
                temp = temp + "Building Workers Needed : " + r2.get(0)[7] + '\n';
                temp = temp + "Building Time To Build : " + r2.get(0)[8] + '\n';
                temp = temp + "Building Size Required : " + r2.get(0)[9] + '\n';
                temp = temp + "Building Happiness : " + r2.get(0)[10];
                buildingIn.append(temp);
            }
        });
        Object comp = buildings.getUI().getAccessibleChild(buildings, 0);
        JPopupMenu popup = (JPopupMenu) comp;
        JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
        scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        buildings.setPreferredSize(new Dimension(200, 50));
        Button bbutton = new Button("Build");
        bbutton.setPreferredSize(new Dimension(50, 50));
        c.gridx = 1;

        bbutton.setPreferredSize(new Dimension(150, 60));
        bbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = buildingsID[buildings.getSelectedIndex()];

                String checkBuildingPrerequisites = tain.rdb.checkBuildingPrerequisites(PlotID, id);

                if (!"".equals(checkBuildingPrerequisites)) {
                    JOptionPane.showMessageDialog(ContentPane, "Prerequisites not met, requires " + checkBuildingPrerequisites);
                } else {

                    boolean buySucess = true;

                    //do the building need the following values
                    //1 character iD
                    int charsId = tain.CharacterID;
                    //2 Building id

                    ArrayList<String[]> r2s = tain.rdb.retrieveBuildingDetailsById(id);
                    //3 Time Build (Prop gonna have to make the final value when the building will be completed
                    int WeeksToBuild = Integer.parseInt(r2s.get(0)[8]);

                    //now we need to do checks to see if the person can indeed build on the property;
                    // All the reqs the building has
                    double acresNeeded = Double.parseDouble(r2s.get(0)[9]);

                    int WorkersNeeded = Integer.parseInt(r2s.get(0)[7]);

                    double BuildingCost = Double.parseDouble(r2s.get(0)[4]);

                    double BuildingSetupCost = Double.parseDouble(r2s.get(0)[5]);

                    //the users current plot specs


                    ArrayList<String> retrievePlotDetails = wrapper.retrievePlotDetails(PlotID);

                    int PlotWorkerMax = Integer.parseInt(retrievePlotDetails.get(10));
                    double PlotWorkersUsed = Double.parseDouble(retrievePlotDetails.get(9));


                    int poormax = Integer.parseInt(retrievePlotDetails.get(16));
                    double poorused = Double.parseDouble(retrievePlotDetails.get(15));

                    int finemax = Integer.parseInt(retrievePlotDetails.get(14));
                    double fineused = Double.parseDouble(retrievePlotDetails.get(13));

                    int exmax = Integer.parseInt(retrievePlotDetails.get(12));
                    double exused = Double.parseDouble(retrievePlotDetails.get(11));

                    int checkCounter = 0;

                    int preReq = Integer.parseInt(r2s.get(0)[3]);

                    if (preReq == 2) {

                        if (poorused == poormax) {
                            JOptionPane.showMessageDialog(ContentPane, "This building requires more Poor acre space to build");
                            buySucess = false;
                        } else if ((poorused + acresNeeded) > poormax) {
                            JOptionPane.showMessageDialog(ContentPane, "This building requires more Poor acre space to build");
                            buySucess = false;
                        } else {
                            checkCounter++;
                        }
                    } else if (preReq == 3) {
                        if (fineused == finemax) {
                            JOptionPane.showMessageDialog(ContentPane, "This building requires more Fine acre space to build");
                            buySucess = false;
                        } else if ((fineused + acresNeeded) > finemax) {
                            JOptionPane.showMessageDialog(ContentPane, "This building requires more Fine space to build");
                            buySucess = false;
                        } else {
                            checkCounter++;
                        }
                    } else if (preReq == 4) {
                        if (exused == exmax) {
                            JOptionPane.showMessageDialog(ContentPane, "This building requires more Exquisite space to build");
                            buySucess = false;
                        } else if ((exused + acresNeeded) > exmax) {
                            JOptionPane.showMessageDialog(ContentPane, "This building requires more Exquisite space to build");
                            buySucess = false;
                        } else {
                        }
                    } else {
                        checkCounter++;
                    }

                    //worker check
                    if (PlotWorkerMax == PlotWorkersUsed) {
                        buySucess = false;
                        JOptionPane.showMessageDialog(ContentPane, "Your plot has no more space for workers,purchase more acres to keep on building");
                    } else if ((PlotWorkersUsed + WorkersNeeded) > PlotWorkerMax) {
                        buySucess = false;
                        JOptionPane.showMessageDialog(ContentPane, "Your plot has no more space for workers,purchase more acres to keep on building");
                    } else {
                        checkCounter++;
                    }

                    //money check
                    ArrayList<String> currentAmount = tain.rdb.getCurrentAmount(PlotID);

                    // amount if money in the plot
                    int silver = Integer.parseInt(currentAmount.get(0)) * 100 + Integer.parseInt(currentAmount.get(1)) * 10 + Integer.parseInt(currentAmount.get(2));

                    // need to look at setup cost tho
                    if ((BuildingCost * 10) > silver) {
                        JOptionPane.showMessageDialog(ContentPane, "You need more money to buy this building");
                    } else {
                        checkCounter++;
                    }
                    //time check,need to use log see what is build and how much time is left

                    // all his current buildings in the log
                    ArrayList<String> retrieveAllBuildingsOwnedByCharacter = tain.rdb.retrieveAllBuildingsOwnedByCharacter(tain.CharacterID, PlotID);

                    //







                    if (buySucess && checkCounter == 3) {
                        //if this is reached then the person has all the req to build,need to update all the values
                        //log the building
                        tain.rdb.logBuildingBuilt(charsId, buildingsID[buildings.getSelectedIndex()], PlotID);
                        // now we need to consume the acres
                        
                        // consume workers
                        
                        
                        
                        
                        //consume funds
                        ArrayList<String> amount2 = tain.rdb.getCurrentAmount(PlotID);
                        int tempa = Integer.parseInt(amount2.get(0)) * 100 + Integer.parseInt(amount2.get(1)) * 10 + Integer.parseInt(amount2.get(2));
                        //plots gold so it becomes less
                        tempa = tempa - (int) (BuildingCost * 10);
                        int nplat = tempa / 100;
                        tempa = tempa - nplat * 100;
                        int ngold = tempa / 10;
                        tempa = tempa - ngold * 10;
                        int nsilver = tempa;
                        tain.rdb.modifyAmount(PlotID, nplat, ngold, nsilver);

                    }
                    tr.listBuildings.doClick();
                    tain.cardlayout.show(tain.contentpane, "MPlay");
                }
            }
        });
        buildingsPanel.add(bbutton, c);
        c.gridx = 2;
        buildingsPanel.add(buildings, c);
        JPanel buildingInfo = new JPanel();
        buildingIn.setPreferredSize(new Dimension(200, 130));
        buildingInfo.add(buildingIn);
        panel.add(buildingsPanel);
        panel.add(buildingInfo);

        return panel;
    }
}
