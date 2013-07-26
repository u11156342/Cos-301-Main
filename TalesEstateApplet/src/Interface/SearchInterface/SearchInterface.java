package Interface.SearchInterface;

import talesestateapplet.BasePanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateapplet.TalesEstateApplet;


public class SearchInterface extends BasePanel{
    
    public ArrayList<String[]> properties;
    
    public SearchInterface(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, ArrayList<String[]> prop, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {
        super(name);
        properties=prop;
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT,aThis,cardlayout,contentPane);
    }
    
        public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {        
        ResultProperties mmenu = new ResultProperties(JFXPANEL_WIDTH_INT - 500,properties.size()*140,properties,aThis, cardlayout, contentPane);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
    }
    
}
