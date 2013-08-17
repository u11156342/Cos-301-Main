/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *
 * @author NightFiyah
 */
public class VisualSideMenu extends JPanel {

    int PropertyID;
    ArrayList buildings;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    JList buildingTokens = new JList();
    JButton title = new JButton("Title");
    JButton buildb = new JButton("build");

    public VisualSideMenu(int PropertyId) {
        PropertyID = PropertyId;

        String[] build = {"aasdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd", "basdasdasdasd"};
        buildingTokens = new JList(build);

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

    }
}
