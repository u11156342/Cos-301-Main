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
import javax.swing.JTextPane;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class CharacterLog extends BasePanel {

    public JTextPane textZone = new JTextPane();

    public CharacterLog(String name) {
        super(name);
    }

    public void init(final TransferContainer tc) {

        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(13)));
        Title.setBorderPainted(false);
        Title.setContentAreaFilled(false);

        add(Title, BorderLayout.NORTH);

        textZone.setEditable(false);
        add(textZone, BorderLayout.CENTER);

        JButton back = new JButton("Back");
        back.setPreferredSize(new Dimension(150, 60));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "PlayerMan");
            }
        });

        add(back, BorderLayout.SOUTH);
    }
}
