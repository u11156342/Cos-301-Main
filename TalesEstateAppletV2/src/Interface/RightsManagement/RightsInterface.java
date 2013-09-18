/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.RightsManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class RightsInterface extends BasePanel {

    public JTextPane textZone = new JTextPane();

    public RightsInterface(String name, final TransferContainer tc) {
        super(name);


        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        
        RightsSideMenu menu = new RightsSideMenu(textZone, tc);
        menu.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT / 5, tc.JFXPANEL_HEIGHT_INT));
        add(menu, BorderLayout.EAST);


        add(scrollText,BorderLayout.CENTER);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
