/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.MyProperties;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class MyPropertiesInterface extends BasePanel {

    TransferContainer tain;
    JButton title;

    public MyPropertiesInterface(String name, TransferContainer tc) {
        super(name,tc);
        tain = tc;
        setBackground(java.awt.Color.getHSBColor(tc.c1[0],tc. c1[1],tc. c1[2]));
    }

    public void init() {

        title = new JButton(new ImageIcon(tain.ad.ImageAdapter(12)));
        title.setContentAreaFilled(false);
        title.setBorderPainted(false);

        PlayerProperties mmenu = new PlayerProperties(tain);
        JScrollPane mainMenuScrollPane = new JScrollPane(mmenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //mainMenuScrollPane.setPreferredSize(new Dimension(tain.JFXPANEL_WIDTH_INT - 50, tain.JFXPANEL_HEIGHT_INT - 50));
        mainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(mainMenuScrollPane, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tain.cardlayout.show(tain.contentpane, "MainMenu");
            }
        });
        add(back, BorderLayout.SOUTH);

        // tain.cardlayout.show(tain.contentpane, "MainMenu");
    }
}
