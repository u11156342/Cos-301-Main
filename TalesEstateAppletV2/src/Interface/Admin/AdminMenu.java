/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public AdminMenu(String name, final TransferContainer tc) {
        super(name);


        search.setPreferredSize(new Dimension(150, 60));
        ManageCharacterGold.setPreferredSize(new Dimension(150, 60));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainAdminSearch admin=tc.Cmanager.getMainAdminSearchesCard();
                admin.init(tc);
                tc.cardlayout.show(tc.contentpane,admin.getName());
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

        c.gridy = 0;
        add(search, c);
        c.gridy = 1;
        add(ManageCharacterGold, c);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

        back.setPreferredSize(new Dimension(250, 60));
        c.gridy = 2;
        add(back, c);
    }
}
