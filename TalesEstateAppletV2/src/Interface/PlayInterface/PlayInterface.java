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
        System.out.println("play interface");
        t=tc;

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
            playIn = new VisualMap(size,t);
        } catch (IOException ex) {
            Logger.getLogger(PlayInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        playIn.gridstates = buildings;
        playIn.tileStates = tiles;
        playIn.setPreferredSize(new Dimension(160 * size + 100, 80 * size + 100));

        playMapScrollPane = new JScrollPane(playIn, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playMapScrollPane.setPreferredSize(new Dimension(t.JFXPANEL_WIDTH_INT / 5 * 4, t.JFXPANEL_HEIGHT_INT));
        playMapScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        ((VisualMap) playIn).setScrollP(playMapScrollPane);

        add(playMapScrollPane, BorderLayout.CENTER);

        VisualSideMenu menu = new VisualSideMenu(propertyID, t);
        menu.setPreferredSize(new Dimension(180, t.JFXPANEL_HEIGHT_INT - 100));
        JScrollPane menuscroll = new JScrollPane(menu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        menuscroll.setPreferredSize(new Dimension(200, t.JFXPANEL_HEIGHT_INT - 100));
        menuscroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(menuscroll, BorderLayout.EAST);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.cardlayout.show(t.contentpane, "MPlay");
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
