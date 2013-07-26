package Interface.SearchInterface;

import Interface.PlayInterface.PlayInterface;
import talesestateapplet.BasePanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class BrowseInterface extends BasePanel {

    int size;
    String duchy;
    int quality;
    int propertyID;
    int[][] tiles;
    int[][] buildings;

    public BrowseInterface(String play, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, int propertyIDz, String duchys, int qual, int sizes, int[][] tilesz, int[][] buildingsz) {
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



        GameBrowseGrid playIn = null;
        try {
            playIn = new GameBrowseGrid(size);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        playIn.gridstates = buildings;
        playIn.tileStates = tiles;

        playIn.setPreferredSize(new Dimension(playIn.wdOfcell * size + 100, playIn.htOfcell * size + 100));
        JScrollPane playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        playMapScrollPane.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT * 3 / 4));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((GameBrowseGrid) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);
    }
}
