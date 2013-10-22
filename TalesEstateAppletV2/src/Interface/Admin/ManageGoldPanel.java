/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class ManageGoldPanel extends Panel {

    TransferContainer tain;
    JTextField playername = new JTextField();
    JButton findPlayer = new JButton("Find Player");
    JTextField platmod = new JTextField();
    JTextField goldmod = new JTextField();
    JTextField silmod = new JTextField();
    JLabel l4 = new JLabel("Platinum thrones : ");
    JLabel l5 = new JLabel("Gold crowns : ");
    JLabel l6 = new JLabel("Silver shields : ");
    JLabel character = new JLabel("");
    String username;
    boolean found = false;

    public ManageGoldPanel(final TransferContainer tc) {
        tain = tc;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        platmod.setPreferredSize(new Dimension(100, 25));
        goldmod.setPreferredSize(new Dimension(100, 25));
        silmod.setPreferredSize(new Dimension(100, 25));
        final ManageGoldPanel ref = this;
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        playername.setPreferredSize(new Dimension(100, 40));

        findPlayer.setPreferredSize(new Dimension(150, 60));
        findPlayer.addActionListener(new ActionListener() {
            ArrayList<String> characterAmounts;
            int platAmount = 0;
            int goldAmount = 0;
            int silverAmount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                found = false;

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                ArrayList<String[]> retrieveCharacterIDExtra = tc.rdb.retrieveCharacterIDExtra(attempt);

                String[] characters;

                characters = new String[retrieveCharacterIDExtra.size()];
                for (int i = 0; i < retrieveCharacterIDExtra.size(); i++) {
                    characters[i] = retrieveCharacterIDExtra.get(i)[1].substring(0, retrieveCharacterIDExtra.get(i)[1].indexOf("&*&"));
                }

                String picked = "";
                if (characters.length > 0) {
                    picked = (String) JOptionPane.showInputDialog(findPlayer, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
                } else {
                    JOptionPane.showMessageDialog(findPlayer, "No characters found");
                }

                if (!"".equals(picked) && picked != null) {
                    username = picked;

                    try {
                        characterAmounts = tc.rdb.getCharacterAmounts(picked);
                    } catch (Exception ex) {
                    }
                    if (characterAmounts != null && !characterAmounts.isEmpty()) {

                        //  JOptionPane.showMessageDialog(ref, "Player found");
                        character.setText("Editing funds for : " + picked);
                        try {
                            platAmount = Integer.parseInt(characterAmounts.get(0));
                            goldAmount = Integer.parseInt(characterAmounts.get(1));
                            silverAmount = Integer.parseInt(characterAmounts.get(2));
                        } catch (Exception ex) {
                        }

                        platmod.setText("" + platAmount);
                        goldmod.setText("" + goldAmount);
                        silmod.setText("" + silverAmount);
                        found = true;
                    } else {
                        JOptionPane.showMessageDialog(ref, "Player not found");

                    }
                }



            }
        });

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        c.insets = new Insets(30, 0, 0, 0);
        add(findPlayer, c);

        c.gridy = 1;
        add(character, c);

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
        modify.setPreferredSize(new Dimension(150, 60));
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!found) {
                    return;
                }
                try {
                    tc.rdb.modifyAmount(username, Integer.parseInt(platmod.getText()), Integer.parseInt(goldmod.getText()), Integer.parseInt(silmod.getText()));
                    JOptionPane.showMessageDialog(playername, "Funds Updated");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(playername, "Only enter valid integers");
                }
            }
        });

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(modify, c);
    }
}
