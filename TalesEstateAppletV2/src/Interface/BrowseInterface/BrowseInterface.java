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

    public BrowseInterface(String play) {
        super(play);
    }

    public void init(final TransferContainer tc, int propertyIDz, int sizes, int[][] tilesz, int[][] buildingsz) {

      //  System.out.println(tilesz.length + " " + buildingsz.length);

        size = tilesz.length;

        propertyID = propertyIDz;

        GameBrowseGrid playIn = null;
        try {
            playIn = new GameBrowseGrid(size, tc,propertyID);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        playIn.gridstates = buildingsz;
        playIn.tileStates = tilesz;

        playIn.setPreferredSize(new Dimension(360 * size + 100, 180 * size + 100));
        JScrollPane playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT, tc.JFXPANEL_HEIGHT_INT * 3 / 4));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((GameBrowseGrid) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tc.lastAdminBrowse) {
                    tc.cardlayout.show(tc.contentpane, tc.Cmanager.AdminSearchInterfaces[tc.Cmanager.currentAdminSearchInterfaceCard].getName());
                } else {
                    tc.cardlayout.show(tc.contentpane, tc.Cmanager.SearchInterfaces[tc.Cmanager.currentSearchInterfaceCard].getName());
                }
                // tc.cardlayout.show(tc.contentpane, "MainMenu");
                // 
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
