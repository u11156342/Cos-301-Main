package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import talesestateapplet.BasePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateapplet.UserCharacter;

public class PlayInterface extends BasePanel {

    int size;
    int propertyID;
    int[][] tiles;
    int[][] buildings;
    JScrollPane playMapScrollPane;

    public PlayInterface(String play, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, int propertyIDz, UserCharacter uchar) {
        super(play);
        propertyID = propertyIDz;

        RestFullDBAdapter wrapper = new RestFullDBAdapter();

        ArrayList results = wrapper.retrievePlotDetails(propertyID);

        size = Integer.parseInt("" + results.get(3));
        tiles = wrapper.convertFromArray("" + results.get(5));
        buildings = wrapper.convertFromArray("" + results.get(6));
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT, uchar);
    }

    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, UserCharacter uchar) {

        VisualMap playIn = null;
        try {
            playIn = new VisualMap(size);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        playIn.gridstates = buildings;
        playIn.tileStates = tiles;
        playIn.setPreferredSize(new Dimension(playIn.wdOfcell * size + 100, playIn.htOfcell * size + 100));

        playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT / 5 * 4, JFXPANEL_HEIGHT_INT));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((VisualMap) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);

        VisualSideMenu menu = new VisualSideMenu(propertyID, uchar);
        menu.setPreferredSize(new Dimension(180, JFXPANEL_HEIGHT_INT - 100));
        JScrollPane menuscroll = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menuscroll.setPreferredSize(new Dimension(200, JFXPANEL_HEIGHT_INT - 100));
        menuscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(menuscroll, BorderLayout.EAST);
    }
}
