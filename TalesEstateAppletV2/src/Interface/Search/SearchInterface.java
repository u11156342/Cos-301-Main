package Interface.Search;

import Connections.RestFullAdapter;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public SearchInterface(String name, TransferContainer tc) {
        super(name, tc);
    }

    public void init(final TransferContainer tc, ArrayList<String[]> prop) {
        properties = prop;
        title = new JButton(new ImageIcon(tc.ad.ImageAdapter(18)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        ResultProperties mmenu = new ResultProperties(tc.JFXPANEL_WIDTH_INT - 400, properties.size() * 260, properties, tc);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        JButton back = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
