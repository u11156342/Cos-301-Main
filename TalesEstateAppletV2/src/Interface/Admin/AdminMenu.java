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
 * @author NightFiyah
 */
public class AdminMenu extends BasePanel {

    public AdminMenu(String name, final TransferContainer tc) {
        super(name, tc);


        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(48)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);

        add(Title, BorderLayout.NORTH);

        AdminMenuInterface ami = new AdminMenuInterface(tc);
        add(ami, BorderLayout.CENTER);


        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

        back.setPreferredSize(new Dimension(250, 60));
        add(back, BorderLayout.SOUTH);
    }
}
