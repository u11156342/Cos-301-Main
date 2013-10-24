package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    // Search interface for admins
    public AdminSearchInterface(String name, TransferContainer tc) {
        super(name, tc);
    }

    public void init(final TransferContainer t, ArrayList<String[]> prop) {
        setBackground(java.awt.Color.getHSBColor(t.c1[0], t.c1[1], t.c1[2]));
        properties = prop;
        title = new JButton(new ImageIcon(t.ad.ImageAdapter(18)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);
        PlayerPropertiesAdmin mmenu = new PlayerPropertiesAdmin(t.JFXPANEL_WIDTH_INT - 500, properties.size() * 100, t, properties);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        mainMenuScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.cardlayout.show(t.contentpane, t.Cmanager.MainAdminSearches[t.Cmanager.currentMainAdminSearchCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
