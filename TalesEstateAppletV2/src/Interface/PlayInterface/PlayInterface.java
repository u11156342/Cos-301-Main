package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        super(play);
        t = tc;

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
        playIn.PlotID=propertyID;
        playIn.setPreferredSize(new Dimension(360 * size + 100, 180 * size + 100));

        playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension((t.JFXPANEL_WIDTH_INT / 5) * 4, t.JFXPANEL_HEIGHT_INT));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((VisualMap) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);

        VisualSideMenu menu = new VisualSideMenu(propertyID, t);
        t.reference=menu;
        menu.init(t);
        menu.setPreferredSize(new Dimension(t.JFXPANEL_WIDTH_INT / 5-100, t.JFXPANEL_HEIGHT_INT - 100));
        JScrollPane menuscroll = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menuscroll.setPreferredSize(new Dimension(t.JFXPANEL_WIDTH_INT / 5, t.JFXPANEL_HEIGHT_INT - 100));
        menuscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(menuscroll, BorderLayout.EAST);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.cardlayout.show(t.contentpane, t.Cmanager.MainPlayInterfaces[t.Cmanager.currentMainPlayInterfaceCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
