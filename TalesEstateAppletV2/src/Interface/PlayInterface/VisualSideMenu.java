package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import talesestateappletv2.TransferContainer;

public class VisualSideMenu extends JPanel {

    int PropertyID;
    ArrayList buildings;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    JList buildingTokens;
    JButton title = new JButton("Buildings");
    String[] build;
    int[] buildID;
    int[] PicID;
    int[] LogIDs;

    public VisualSideMenu(final int PropertyId, final TransferContainer tc) {
        PropertyID = PropertyId;
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
    }

    public void init(final TransferContainer tc) {

        ArrayList<String[]> retrieveAllBuildingsOwnedByCharacter = wrapper.retrieveAllBuildingsOwnedByCharacter(tc.CharacterID, PropertyID);

        build = new String[retrieveAllBuildingsOwnedByCharacter.size()];
        buildID = new int[retrieveAllBuildingsOwnedByCharacter.size()];
        PicID = new int[retrieveAllBuildingsOwnedByCharacter.size()];
        LogIDs = new int[retrieveAllBuildingsOwnedByCharacter.size()];
        ArrayList<String[]> tempresult;

        for (int a = 0; a < build.length; a++) {

            if ("0".equals(retrieveAllBuildingsOwnedByCharacter.get(a)[4])) {
                tempresult = wrapper.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[0]));
                build[a] = tempresult.get(0)[1];
                buildID[a] = Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[0]);
                PicID[a] = Integer.parseInt(tempresult.get(0)[12]);
                LogIDs[a] = Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[3]);
            }
        }


        if (build.length == 0) {
            JOptionPane.showMessageDialog(buildingTokens, "Buying a building will allow you to place tokens that will appear in the panel on the right hand side");
        }
        
        buildingTokens = new JList(build);
        buildingTokens.setFixedCellHeight(30);
        JScrollPane tokenscroll = new JScrollPane(buildingTokens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tokenscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        this.setLayout(new GridLayout(1, 1));
        add(tokenscroll);

        buildingTokens.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    tc.BuildingRef = PicID[buildingTokens.getSelectedIndex()];
                    tc.BuildingLogReference = LogIDs[buildingTokens.getSelectedIndex()];
                } catch (Exception ex) {
                }
            }
        });
    }
}
