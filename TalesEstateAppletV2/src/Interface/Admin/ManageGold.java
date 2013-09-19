/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    TransferContainer tain;
    JTextField playername = new JTextField();
    JButton findPlayer = new JButton("Find Player");
    JTextField platmod = new JTextField();
    JTextField goldmod = new JTextField();
    JTextField silmod = new JTextField();
    JLabel l4 = new JLabel("Platinum : ");
    JLabel l5 = new JLabel("Gold : ");
    JLabel l6 = new JLabel("Silver : ");

    public ManageGold(String name, final TransferContainer tc) {
        super(name);
        tain = tc;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        platmod.setPreferredSize(new Dimension(100, 40));
        goldmod.setPreferredSize(new Dimension(100, 40));
        silmod.setPreferredSize(new Dimension(100, 40));


        playername.setPreferredSize(new Dimension(100, 40));
        final ManageGold ref = this;
        findPlayer.addActionListener(new ActionListener() {
            ArrayList<String> characterAmounts;
            int platAmount = 0;
            int goldAmount = 0;
            int silverAmount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                String[] characters = {"piet", "ben", "jave"};
                String picked = (String) JOptionPane.showInputDialog(findPlayer, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);


                try {
                    characterAmounts = tc.rdb.getCharacterAmounts(playername.getText());
                } catch (Exception ex) {
                }

                if (characterAmounts != null && !characterAmounts.isEmpty()) {
                    JOptionPane.showMessageDialog(ref, "Player found");
                    platAmount = Integer.parseInt(characterAmounts.get(0));
                    goldAmount = Integer.parseInt(characterAmounts.get(1));
                    silverAmount = Integer.parseInt(characterAmounts.get(2));

                    platmod.setText("" + platAmount);
                    goldmod.setText("" + goldAmount);
                    silmod.setText("" + silverAmount);
                } else {
                    JOptionPane.showMessageDialog(ref, "Player not found");
                }
            }
        });

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        add(findPlayer, c);
        c.gridwidth = 1;

        //c.gridwidth = 0;

        c.gridx = 0;
        c.gridy = 2;

        add(l4, c);

        c.gridx = 1;

        add(platmod, c);

        c.gridy = 3;
        c.gridx = 0;

        add(l5, c);

        c.gridx = 1;

        add(goldmod, c);


        c.gridy = 4;
        c.gridx = 0;

        add(l6, c);

        c.gridx = 1;

        add(silmod, c);

        JButton modify = new JButton("Modify");

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(modify, c);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tain.cardlayout.show(tain.contentpane, "AMain");
            }
        });
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(back, c);
    }
}
