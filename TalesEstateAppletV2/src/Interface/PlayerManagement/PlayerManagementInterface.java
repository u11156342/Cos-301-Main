/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayerManagement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class PlayerManagementInterface extends BasePanel {

    public PlayerManagementInterface(String name) {
        super(name);
    }

    public void init(final TransferContainer tc) {

        PlayerManagementMenu pmm = new PlayerManagementMenu(tc);

        add(pmm, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

        add(back, BorderLayout.SOUTH);

    }
}
