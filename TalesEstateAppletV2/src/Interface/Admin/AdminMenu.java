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


        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                MainAdminSearch admin;
                admin = new MainAdminSearch("Admin", tc);
                tc.mainapplet.add(admin, admin.getName());
                tc.cardlayout.show(tc.contentpane, "Admin");
            }
        });

        ManageCharacterGold.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ManageGold mg = new ManageGold("CGold", tc);
                tc.mainapplet.add(mg, mg.getName());
                tc.cardlayout.show(tc.contentpane, "CGold");
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

    }
}
