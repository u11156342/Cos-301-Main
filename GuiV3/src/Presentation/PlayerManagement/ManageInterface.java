
package Presentation.PlayerManagement;

import guiv3.BasePanel;
import guiv3.GuiV3;
import guiv3.UserCharacter;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public final class ManageInterface extends BasePanel {
    UserCharacter user;
    public ManageInterface(String manage, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, UserCharacter Pchar, GuiV3 aThis, CardLayout cardlayout, Container contentPane) {
        super(manage);
        user=Pchar;
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT,aThis,cardlayout,contentPane);
    }


    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, GuiV3 aThis, CardLayout cardlayout, Container contentPane) {        
        PlayerProperties mmenu = new PlayerProperties(JFXPANEL_WIDTH_INT - 500,user,aThis, cardlayout, contentPane);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainMenuScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT -50, JFXPANEL_HEIGHT_INT - 50));
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
    }
}
