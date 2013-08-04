package Interface.SearchInterface;

import Connections.RestFullAdapter;
import talesestateapplet.BasePanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateapplet.TalesEstateApplet;

public class SearchInterface extends BasePanel {

    public ArrayList<String[]> properties;
    JButton title;

    public SearchInterface(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, ArrayList<String[]> prop, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {
        super(name);
        properties = prop;
        RestFullAdapter picAdapter = new RestFullAdapter();
        title = new JButton(new ImageIcon(picAdapter.ImageAdapter(18)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT, aThis, cardlayout, contentPane);
    }

    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {
        ResultProperties mmenu = new ResultProperties(JFXPANEL_WIDTH_INT - 500, properties.size() * 100, properties, aThis, cardlayout, contentPane);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
    }
}
