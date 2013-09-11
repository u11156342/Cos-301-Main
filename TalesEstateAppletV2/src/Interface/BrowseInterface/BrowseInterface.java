package Interface.BrowseInterface;

import Interface.PlayInterface.PlayInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class BrowseInterface extends BasePanel {

    int size;
    int propertyID;
    int[][] tiles;
    int[][] buildings;

    public BrowseInterface(String play, TransferContainer tc, int propertyIDz, int sizes, int[][] tilesz, int[][] buildingsz) {
        super(play);

        size = sizes;

        propertyID = propertyIDz;
        tiles = tilesz;
        buildings = buildingsz;

        init(tc);

    }

    public void init(final TransferContainer t) {

        GameBrowseGrid playIn = null;
        try {
            playIn = new GameBrowseGrid(size);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        playIn.gridstates = buildings;
        playIn.tileStates = tiles;

        playIn.setPreferredSize(new Dimension(playIn.wdOfcell * size + 100, playIn.htOfcell * size + 100));
        JScrollPane playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(t.JFXPANEL_WIDTH_INT, t.JFXPANEL_HEIGHT_INT * 3 / 4));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((GameBrowseGrid) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.cardlayout.show(t.contentpane, "AdminS");
                t.cardlayout.show(t.contentpane, "MPlay");
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
