package Interface.Search;

import Connections.RestFullAdapter;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class SearchInterface extends BasePanel {

    public ArrayList<String[]> properties;
    JButton title;

    public SearchInterface(String name, ArrayList<String[]> prop,TransferContainer tc) {
        super(name);
        properties = prop;
        RestFullAdapter picAdapter = new RestFullAdapter();
        title = new JButton(new ImageIcon(picAdapter.ImageAdapter(18)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        init(tc);
    }

    public void init(TransferContainer tc) {
        ResultProperties mmenu = new ResultProperties(tc.JFXPANEL_WIDTH_INT - 500, properties.size() * 100, properties, tc);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
    }
}