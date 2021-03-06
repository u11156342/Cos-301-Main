package Interface.BrowseInterface;

import Interface.PlayInterface.PlayInterface;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

public class BrowseInterface extends BasePanel {

    int size;
    int propertyID;

    public BrowseInterface(String play, TransferContainer tc) {
        super(play, tc);
    }

    public void init(final TransferContainer tc, int propertyIDz, int sizes, int[][] tilesz, int[][] buildingsz) {
        size = tilesz.length;
        propertyID = propertyIDz;
        GameBrowseGrid playIn = null;
        try {
            playIn = new GameBrowseGrid(size, tc, propertyID);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        playIn.init(size, tc, propertyID);
        playIn.gridstates = buildingsz;
        playIn.tileStates = tilesz;
        playIn.setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        playIn.setPreferredSize(new Dimension(360 * size + 100, 180 * size + 100));
        JScrollPane playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT, tc.JFXPANEL_HEIGHT_INT * 3 / 4));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((GameBrowseGrid) playIn).setScrollP(playMapScrollPane);
        add(playMapScrollPane, BorderLayout.CENTER);
        JButton back = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tc.lastAdminBrowse) {
                    tc.cardlayout.show(tc.contentpane, tc.Cmanager.AdminSearchInterfaces[tc.Cmanager.currentAdminSearchInterfaceCard].getName());
                } else {
                    tc.cardlayout.show(tc.contentpane, tc.Cmanager.SearchInterfaces[tc.Cmanager.currentSearchInterfaceCard].getName());
                }
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
