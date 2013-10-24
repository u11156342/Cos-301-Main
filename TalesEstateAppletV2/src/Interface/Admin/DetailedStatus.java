package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
//Detailed status panel
public class DetailedStatus extends BasePanel {

    public JTextPane textZone = new JTextPane();
    JButton back = new JButton("back");

    public DetailedStatus(String name, TransferContainer tc) {
        super(name, tc);
    }

    public void init(int Pid, final TransferContainer tc) {

        JScrollPane stats = new JScrollPane(textZone);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        stats.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        textZone.setContentType("text/html");
        String t = tc.rdb.getSuperStatusReport(Pid);
        textZone.setText(t);
        textZone.setEditable(false);

        this.add(stats, BorderLayout.CENTER);
        back = new JButton(new ImageIcon(tc.ad.ImageAdapter(119)));
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, tc.Cmanager.AdminSearchInterfaces[tc.Cmanager.currentAdminSearchInterfaceCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);

    }
}
