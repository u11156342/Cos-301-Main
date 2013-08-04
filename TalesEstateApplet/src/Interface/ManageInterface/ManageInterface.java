
package Interface.ManageInterface;

import Connections.RestFullAdapter;
import talesestateapplet.BasePanel;
import talesestateapplet.UserCharacter;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateapplet.TalesEstateApplet;

public final class ManageInterface extends BasePanel {
    UserCharacter user;
    JButton title;
    
    public ManageInterface(String manage, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, UserCharacter Pchar, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane,ArrayList<String[]> Properties) {
        super(manage);
        user=Pchar;
        
        RestFullAdapter wrapper=new RestFullAdapter();
        title=new JButton(new ImageIcon(wrapper.ImageAdapter(12)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT,aThis,cardlayout,contentPane,Properties);
    }


    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane,ArrayList<String[]> properties) {        
        PlayerProperties mmenu = new PlayerProperties(JFXPANEL_WIDTH_INT - 500,user,aThis, cardlayout, contentPane,properties);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT -50, JFXPANEL_HEIGHT_INT - 50));
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title,BorderLayout.NORTH);
    }
}
