/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateapplet.UserCharacter;

/**
 *
 * @author NightFiyah
 */
public class VisualSideMenu extends JPanel {

    int PropertyID;
    ArrayList buildings;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    JList buildingTokens;
    JButton title = new JButton("Title");
    JButton buildb = new JButton("build");

    public VisualSideMenu(int PropertyId, UserCharacter uchar) {
        PropertyID = PropertyId;
        String[] build;
        int[] buildID;

        ArrayList<String> retrieveAllBuildingsOwnedByCharacter = wrapper.retrieveAllBuildingsOwnedByCharacter(uchar.CharacterID, PropertyId);

        build = new String[retrieveAllBuildingsOwnedByCharacter.size()];
        buildID = new int[retrieveAllBuildingsOwnedByCharacter.size()];

        ArrayList<String[]> tempresult;
        for (int a = 0; a < build.length; a++) {
            tempresult = wrapper.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)));
            build[a]=tempresult.get(0)[1];
            buildID[a]=Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a));
        }

        
        buildingTokens = new JList(build);
        buildingTokens.setPreferredSize(new Dimension(150,build.length*30));
        JScrollPane tokenscroll = new JScrollPane(buildingTokens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tokenscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        add(title, c);
        c.insets = new Insets(30, 0, 0, 0);
        c.gridy = 1;

        add(tokenscroll, c);

        c.gridy = 2;
        add(buildb, c);

        buildb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wrapper.logBuildingBuilt(1, 2);
            }
        });

    }
}
