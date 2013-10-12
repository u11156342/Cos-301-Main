/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    JButton search = new JButton("Search");
    JButton ManageCharacterGold = new JButton("Manage Character Gold");
    JButton GlobalStatus = new JButton("Global Status");

    public AdminMenu(String name, final TransferContainer tc) {
        super(name);



        search.setPreferredSize(new Dimension(150, 60));
        ManageCharacterGold.setPreferredSize(new Dimension(150, 60));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainAdminSearch admin = tc.Cmanager.getMainAdminSearchesCard();
                admin.init(tc);
                tc.cardlayout.show(tc.contentpane, admin.getName());
            }
        });

        ManageCharacterGold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ManageGold mg = tc.Cmanager.getManageGoldIntersCard();
                tc.cardlayout.show(tc.contentpane, mg.getName());
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        search.setPreferredSize(new Dimension(250, 60));
        ManageCharacterGold.setPreferredSize(new Dimension(250, 60));


        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(13)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);

        c.gridy = 0;
        c.insets = new Insets(50, 0, 0, 0);
        add(Title, c);
        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 1;
        add(search, c);
        c.gridy = 2;
        add(ManageCharacterGold, c);

        GlobalStatus.setPreferredSize(new Dimension(250, 60));
        GlobalStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.gstatus.refresh(tc);
                tc.cardlayout.show(tc.contentpane, "gStatus");
            }
        });
        c.gridy = 3;
        add(GlobalStatus, c);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

        back.setPreferredSize(new Dimension(250, 60));
        c.gridy = 4;
        add(back, c);
    }
}
