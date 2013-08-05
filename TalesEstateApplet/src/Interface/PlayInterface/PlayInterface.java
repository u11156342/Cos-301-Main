package Interface.PlayInterface;

import talesestateapplet.BasePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class PlayInterface extends BasePanel {

    int size;
    String duchy;
    int quality;
    int propertyID;
    int[][] tiles;
    int[][] buildings;

    public PlayInterface(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT) {
        super(name);
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);
    }

    public PlayInterface(String play, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, int propertyIDz, String duchys, int qual, int sizes, int[][] tilesz, int[][] buildingsz) {
        super(play);

        size = sizes;
        duchy = duchys;
        quality = qual;
        propertyID = propertyIDz;
        tiles = tilesz;
        buildings = buildingsz;
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);

    }

    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT) {
        
        gridPanel playIn = null;
        try {
            playIn = new gridPanel(size);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        BuildtabPanel menu = new BuildtabPanel(duchy,propertyID);
      //  menu.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT - 100, JFXPANEL_HEIGHT_INT / 4 - 50));
        JScrollPane playmenuScrollPane = new JScrollPane(menu);
        playmenuScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT / 4+20));
        playmenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(playmenuScrollPane, BorderLayout.SOUTH);
        
        playIn.gridstates = buildings;
        playIn.tileStates = tiles;
        playIn.setPreferredSize(new Dimension(playIn.wdOfcell * size + 100, playIn.htOfcell * size + 100));
        JScrollPane playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT * 4/5-20));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((gridPanel) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);
    }
}
