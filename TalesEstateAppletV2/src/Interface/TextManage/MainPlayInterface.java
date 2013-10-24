package Interface.TextManage;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

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

    public MainPlayInterface(String name, TransferContainer tc) {
        super(name, tc);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
    }

    public void init(final TransferContainer tc, int Pid) {
        textZone.setEditable(false);
        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(scrollText, BorderLayout.CENTER);
        MainPlaySideMenu menu = new MainPlaySideMenu(textZone, tc, Pid, IsOwner);
        add(menu, BorderLayout.EAST);
        textZone.setContentType("text/html");
        menu.Report.doClick();
        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, tc.Cmanager.MyProperties[tc.Cmanager.currentMyPropertiesCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);
    }
}
