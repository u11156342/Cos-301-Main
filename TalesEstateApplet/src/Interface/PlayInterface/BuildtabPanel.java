package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.*;

public class BuildtabPanel extends JPanel {

    int w;
    int h;
    String duc;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();

    public BuildtabPanel(int with, int height, String duchy) {
        w = with;
        h = height;
        duc = duchy;
        JTabbedPane tabbedPane = new JTabbedPane();

        ArrayList<String[]> Agricultural = null;
        ArrayList<String[]> Mining = null;
        ArrayList<String[]> Manufacturing = null;
        ArrayList<String[]> Services = null;
        ArrayList<String[]> Improvements = null;

        JComponent panel1 = makeTextPanel("Agricultural", 0, Agricultural);
        panel1.setPreferredSize(new Dimension(with, height));
        tabbedPane.addTab("Agricultural", null, panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Mining", 1, Mining);
        panel2.setPreferredSize(new Dimension(with, height));
        tabbedPane.addTab("Mining", null, panel2);

        JComponent panel3 = makeTextPanel("Manufacturing", 2, Manufacturing);
        panel3.setPreferredSize(new Dimension(with, height));
        tabbedPane.addTab("Manufacturing", null, panel3);

        JComponent panel4 = makeTextPanel("Services", 3, Services);
        panel3.setPreferredSize(new Dimension(with, height));
        tabbedPane.addTab("Services", null, panel4);

        JComponent panel5 = makeTextPanel("Improvements", 4, Improvements);
        panel3.setPreferredSize(new Dimension(with, height));
        tabbedPane.addTab("Improvements", null, panel5);
        add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    ArrayList<String[]> results1 = null;
    ArrayList<String[]> result2 = null;

    protected JComponent makeTextPanel(String text, int type, ArrayList<String[]> arr) {
        JPanel pic = new BuildPic();
        JPanel panel = new JPanel(false);
        final JTextArea buildingIn = new JTextArea();
        String[] buildingsList;
        final int[] buildingsID;
        if (arr == null) {
            arr = wrapper.listBuildingBy(duc, text);
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
        result2 = wrapper.retrieveBuildingDetailsById(buildingsID[0]);
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
                ArrayList<String[]> r2 = wrapper.retrieveBuildingDetailsById(id);
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
        buildingsPanel.add(bbutton, c);
        c.gridx = 2;
        buildingsPanel.add(buildings, c);
        JPanel buildingInfo = new JPanel();
        pic.setPreferredSize(new Dimension(100, 100));
        ((BuildPic) pic).set(12);
        buildingInfo.add(pic);
        buildingInfo.repaint();
        buildingInfo.revalidate();
        buildingIn.setPreferredSize(new Dimension(200, 140));
        buildingInfo.add(buildingIn);
        panel.add(buildingsPanel);
        panel.add(buildingInfo);
        return panel;
    }
}