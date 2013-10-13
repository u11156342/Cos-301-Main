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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author NightFiyah
 */
public class ManageGold extends BasePanel {

    public ManageGold(String name, final TransferContainer tc) {
        super(name);
        
        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(47)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);
        add(Title,BorderLayout.NORTH);
        
        ManageGoldPanel mgp = new ManageGoldPanel(tc);

        add(mgp, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "AMain");
            }
        });
        
        add(back,BorderLayout.SOUTH);
    }
}
