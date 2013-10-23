/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class AdminMenuInterface extends JPanel {

    JButton search = new JButton("Search");
    JButton ManageCharacterGold = new JButton("Manage Character Gold");
    JButton GlobalStatus = new JButton("Global Status");
    JButton Requests = new JButton("Requests");

    public AdminMenuInterface(final TransferContainer tc) {
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainAdminSearch admin = tc.Cmanager.getMainAdminSearchesCard();
                admin.init(tc);
                tc.cardlayout.show(tc.contentpane, admin.getName());
            }
        });
        Requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RequestInterface card = tc.Cmanager.getRequestInterfaceCard();


                ArrayList<String[]> allRequests = tc.rdb.getAllOutstandingRequests();


                if (allRequests.isEmpty()) {
                    JOptionPane.showMessageDialog(Requests, "There are currently no requests.");
                    return;
                } else {
                    card.init(tc, allRequests);
                    tc.cardlayout.show(tc.contentpane, card.getName());
                }
            }
        });

        ManageCharacterGold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ManageGold mg = tc.Cmanager.getManageGoldIntersCard();
                mg.init(tc);
                tc.cardlayout.show(tc.contentpane, mg.getName());
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        search.setPreferredSize(new Dimension(250, 60));
        ManageCharacterGold.setPreferredSize(new Dimension(250, 60));
        Requests.setPreferredSize(new Dimension(250, 60));



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

        c.gridy = 4;
        add(Requests, c);
    }
}
