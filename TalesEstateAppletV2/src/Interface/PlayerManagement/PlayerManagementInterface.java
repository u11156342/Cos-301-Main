/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayerManagement;

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
public class PlayerManagementInterface extends BasePanel {

    public PlayerManagementInterface(String name,TransferContainer tc) {
        super(name,tc);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0],tc. c1[1],tc. c1[2]));
    }

    public void init(final TransferContainer tc) {

        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(49)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);
        add(Title, BorderLayout.NORTH);
        PlayerManagementMenu pmm = new PlayerManagementMenu(tc);

        add(pmm, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

        add(back, BorderLayout.SOUTH);

    }
}
