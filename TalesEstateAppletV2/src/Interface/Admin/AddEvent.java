/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
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

    public AddEvent(String name) {
        super(name);
    }

    public void init(final TransferContainer tc, final int PID) {
        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(45)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);

        add(Title, BorderLayout.NORTH);

        AddEventInterface aei = new AddEventInterface();
        aei.init(tc, PID);

        add(aei, BorderLayout.CENTER);

        JButton back = new JButton("Back");
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
