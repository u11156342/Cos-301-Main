package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;
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

    public VisualSideMenu(final int PropertyId, TransferContainer tc) {
        PropertyID = PropertyId;


        ArrayList<String> retrieveAllBuildingsOwnedByCharacter = wrapper.retrieveAllBuildingsOwnedByCharacter(tc.CharacterID, PropertyId);

        build = new String[retrieveAllBuildingsOwnedByCharacter.size()];
        buildID = new int[retrieveAllBuildingsOwnedByCharacter.size()];

        
        ArrayList<String[]> tempresult;        

        for (int a = 0; a < build.length; a++) {
            tempresult = wrapper.retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)));
            build[a] = tempresult.get(0)[1];
            buildID[a] = Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a));
        }


        buildingTokens = new JList(build);
        buildingTokens.setPreferredSize(new Dimension(150, build.length * 30));
        JScrollPane tokenscroll = new JScrollPane(buildingTokens, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        tokenscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        tokenscroll.setPreferredSize(new Dimension(150, 200));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        add(title, c);
        c.insets = new Insets(30, 0, 0, 0);
        c.gridy = 1;
        add(tokenscroll, c);

    }
}
