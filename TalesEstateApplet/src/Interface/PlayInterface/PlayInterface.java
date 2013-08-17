package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import talesestateapplet.BasePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class PlayInterface extends BasePanel {

    int size;
    int propertyID;
    int[][] tiles;
    int[][] buildings;

    public PlayInterface(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT) {
        super(name);
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);
    }

    public PlayInterface(String play, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, int propertyIDz) {
        super(play);
        propertyID = propertyIDz;

        RestFullDBAdapter wrapper = new RestFullDBAdapter();

        ArrayList results = wrapper.retrievePlotDetails(propertyID);

        size = Integer.parseInt("" + results.get(3));
        tiles = wrapper.convertFromArray("" + results.get(5));
        buildings = wrapper.convertFromArray("" + results.get(6));
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);



    }

    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT) {

        VisualMap playIn = null;
        try {
            playIn = new VisualMap(size);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        playIn.gridstates = buildings;
        playIn.tileStates = tiles;
        playIn.setPreferredSize(new Dimension(playIn.wdOfcell * size + 100, playIn.htOfcell * size + 100));
        
        JScrollPane playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT/5*4, JFXPANEL_HEIGHT_INT));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((VisualMap) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);
        
        VisualSideMenu menu=new VisualSideMenu(propertyID);
        menu.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT/5*1,JFXPANEL_HEIGHT_INT));
        JScrollPane menuscroll = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menuscroll.setPreferredSize(new Dimension(200, JFXPANEL_HEIGHT_INT));
        menuscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        
        add(menuscroll, BorderLayout.EAST);
    }
}
