/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

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
 * @author Fiyah
 */
public class DetailedStatus extends BasePanel {

    public JTextPane textZone = new JTextPane();
    JButton back = new JButton("back");

    public DetailedStatus(String name) {
        super(name);
    }

    public void init(int Pid, final TransferContainer tc) {

        JScrollPane stats = new JScrollPane(textZone);

        stats.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        textZone.setContentType("text/html");
        String t = tc.rdb.getSuperStatusReport(Pid);

        textZone.setText(t);
        textZone.setEditable(false);

        this.add(stats, BorderLayout.CENTER);
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, tc.Cmanager.AdminSearchInterfaces[tc.Cmanager.currentAdminSearchInterfaceCard].getName());
            }
        });
        add(back, BorderLayout.SOUTH);




    }
}
