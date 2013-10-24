/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/** 
 *
 * @author Fiyah
 */
public class AddEvent extends BasePanel {

    public AddEvent(String name,TransferContainer tc) {
        super(name,tc);
    }

    public void init(final TransferContainer tc, final int PID,int def) {
        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(45)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);

        add(Title, BorderLayout.NORTH);

        AddEventInterface aei = new AddEventInterface();
        aei.init(tc, PID,def);

        add(aei, BorderLayout.CENTER);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

        JButton back = new JButton(new ImageIcon(tain.ad.ImageAdapter(119)));
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
