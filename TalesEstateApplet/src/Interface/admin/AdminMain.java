/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.admin;

import Connections.RestFullAdapter;
import Interface.ManageInterface.PlayerProperties;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateapplet.BasePanel;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

public class AdminMain extends BasePanel {

    UserCharacter user;
    JButton title;

    public AdminMain(String manage, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, UserCharacter Pchar, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {
        super(manage);
        user = Pchar;
        
        RestFullAdapter wrapper = new RestFullAdapter();
        title = new JButton(new ImageIcon(wrapper.ImageAdapter(14)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT, aThis, cardlayout, contentPane);
    }

    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {
        PlayerPropertiesAdmin ad = new PlayerPropertiesAdmin(JFXPANEL_WIDTH_INT - 500, user, aThis, cardlayout, contentPane);
        JScrollPane mainMenuScrollPane = new JScrollPane(ad, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT - 50, JFXPANEL_HEIGHT_INT - 50));
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title,BorderLayout.NORTH);
    }
}
