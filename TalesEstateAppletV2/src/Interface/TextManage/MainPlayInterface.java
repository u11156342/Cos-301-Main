/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.TextManage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author NightFiyah
 */
public class MainPlayInterface extends BasePanel {

    public JTextPane textZone = new JTextPane();
    public int size;
    public String duchy;
    public int propertyID;
    public int[][] tiles;
    public int[][] buildings;
    public ArrayList<String[]> quality;
    public ArrayList<String> amount;
    
    public boolean IsOwner;

    public MainPlayInterface(String name) {
        super(name);
    }

    public void init(final TransferContainer tc, int Pid) {
        textZone.setEditable(false);

        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(scrollText, BorderLayout.CENTER);

        MainPlaySideMenu menu = new MainPlaySideMenu(textZone, tc, Pid,IsOwner);
        menu.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT / 5, tc.JFXPANEL_HEIGHT_INT));
        add(menu, BorderLayout.EAST);

        textZone.setContentType("text/html");
        menu.Report.doClick();

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, tc.Cmanager.MyProperties[tc.Cmanager.currentMyPropertiesCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
