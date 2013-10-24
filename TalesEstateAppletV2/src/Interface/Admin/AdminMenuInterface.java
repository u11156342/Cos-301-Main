package Interface.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
// Admin menus interface
public class AdminMenuInterface extends JPanel {

    JButton search = new JButton("Search");
    JButton ManageCharacterGold = new JButton("Manage Character Gold");
    JButton GlobalStatus = new JButton("Global Status");
    JButton Requests = new JButton("Requests");

    public AdminMenuInterface(final TransferContainer tc) {


        search = new JButton(new ImageIcon(tc.ad.ImageAdapter(130)));
        search.setContentAreaFilled(false);
        search.setBorderPainted(false);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ManageCharacterGold = new JButton(new ImageIcon(tc.ad.ImageAdapter(126)));
        ManageCharacterGold.setContentAreaFilled(false);
        ManageCharacterGold.setBorderPainted(false);
        ManageCharacterGold.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GlobalStatus = new JButton(new ImageIcon(tc.ad.ImageAdapter(124)));
        GlobalStatus.setContentAreaFilled(false);
        GlobalStatus.setBorderPainted(false);
        GlobalStatus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Requests = new JButton(new ImageIcon(tc.ad.ImageAdapter(118)));
        Requests.setContentAreaFilled(false);
        Requests.setBorderPainted(false);
        Requests.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 1;
        add(search, c);
        c.gridy = 2;
        add(ManageCharacterGold, c);
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
