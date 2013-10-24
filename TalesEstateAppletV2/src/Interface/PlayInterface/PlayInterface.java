package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class PlayInterface extends BasePanel {

    int size;
    int propertyID;
    int[][] tiles;
    int[][] buildings;
    JScrollPane playMapScrollPane;
    TransferContainer t;

    public PlayInterface(String play, TransferContainer tc) {
        super(play, tc);
        t = tc;
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

    }

    public void init(final TransferContainer t, int propertyIDz) {

        propertyID = propertyIDz;
        RestFullDBAdapter wrapper = new RestFullDBAdapter();
        ArrayList results = wrapper.retrievePlotDetails(propertyID);
        size = Integer.parseInt("" + results.get(4));
        tiles = wrapper.convertFromArray("" + results.get(5));
        buildings = wrapper.convertFromArray("" + results.get(6));

        VisualMap playIn = null;
        try {
            playIn = new VisualMap(size, t);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        playIn.gridstates = buildings;
        playIn.tileStates = tiles;
        playIn.PlotID = propertyID;
        playIn.setPreferredSize(new Dimension(360 * size + 100, 180 * size + 100));
        playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension((t.JFXPANEL_WIDTH_INT / 5) * 4, t.JFXPANEL_HEIGHT_INT));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((VisualMap) playIn).setScrollP(playMapScrollPane);
        add(playMapScrollPane, BorderLayout.CENTER);
        VisualSideMenu menu = new VisualSideMenu(propertyID, t);
        t.reference = menu;
        menu.init(t);
        JScrollPane menuscroll = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menuscroll.setPreferredSize(new Dimension(t.JFXPANEL_WIDTH_INT / 5 + 100, t.JFXPANEL_HEIGHT_INT - 300));
        menuscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(menuscroll, BorderLayout.EAST);
        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.cardlayout.show(t.contentpane, t.Cmanager.MainPlayInterfaces[t.Cmanager.currentMainPlayInterfaceCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
