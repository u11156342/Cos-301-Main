package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author NightFiyah
 */
public class VisualSideMenu extends JPanel {

    int PropertyID;
    ArrayList buildings;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    JList buildingTokens;
    JButton title = new JButton("Buildings");
    String[] build;
    int[] buildID;
    int[] PicID;

    public VisualSideMenu(final int PropertyId, final TransferContainer tc) {
        PropertyID = PropertyId;

        ArrayList<String[]> retrieveAllBuildingsOwnedByCharacter = wrapper.retrieveAllBuildingsOwnedByCharacter(tc.CharacterID, PropertyId);

        build = new String[retrieveAllBuildingsOwnedByCharacter.size()];
        buildID = new int[retrieveAllBuildingsOwnedByCharacter.size()];
        PicID = new int[retrieveAllBuildingsOwnedByCharacter.size()];

        ArrayList<String[]> tempresult;

        for (int a = 0; a < build.length; a++) {
            tempresult = wrapper.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[0]));
            build[a] = tempresult.get(0)[1];
            buildID[a] = Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[0]);
            PicID[a] = Integer.parseInt(tempresult.get(0)[12]);
        }


        buildingTokens = new JList(build);
        buildingTokens.setFixedCellWidth(170);

        //  buildingTokens.setPreferredSize(new Dimension(200, tc.JFXPANEL_HEIGHT_INT - 100));
        JScrollPane tokenscroll = new JScrollPane(buildingTokens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tokenscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        //  tokenscroll.setPreferredSize(new Dimension(210, tc.JFXPANEL_HEIGHT_INT-90));
        // setLayout(new GridBagLayout());

        //  GridBagConstraints c = new GridBagConstraints();

        // c.gridy = 0;
        //  add(title, c);
        //  c.insets = new Insets(30, 0, 0, 0);
        // c.gridy = 1;
        add(tokenscroll, BorderLayout.CENTER);
        buildingTokens.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tc.BuildingRef = PicID[buildingTokens.getSelectedIndex()];
                //JOptionPane.showMessageDialog(buildingTokens, tc.BuildingRef);
            }
        });
    }
}
