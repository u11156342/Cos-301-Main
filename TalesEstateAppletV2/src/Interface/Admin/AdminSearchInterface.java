package Interface.Admin;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class AdminSearchInterface extends BasePanel {

    public ArrayList<String[]> properties;
    JButton title;

    public AdminSearchInterface(String name,TransferContainer tc,ArrayList<String[]> prop) {
        super(name);
        properties = prop;
        title = new JButton(new ImageIcon(tc.ad.ImageAdapter(18)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        init(tc);
    }

    public void init(TransferContainer t) {
        PlayerPropertiesAdmin mmenu = new PlayerPropertiesAdmin(t.JFXPANEL_WIDTH_INT - 500, properties.size() * 100,t, properties);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
    }
}
