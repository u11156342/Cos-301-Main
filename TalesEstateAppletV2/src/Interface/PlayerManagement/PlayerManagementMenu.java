/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayerManagement;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
public class PlayerManagementMenu extends JPanel {

    JButton donateGoldToChar = new JButton("Give gold to character");
    JButton donateGoldToPlayer = new JButton("Give gold to estate");

    public PlayerManagementMenu(final TransferContainer tc) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        donateGoldToChar.setPreferredSize(new Dimension(250, 60));

        donateGoldToChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                ArrayList<String[]> retrieveCharacterIDExtra = tc.rdb.retrieveCharacterIDExtra(attempt);

                System.out.println("a o " + retrieveCharacterIDExtra.size());
                String[] characters;

                characters = new String[retrieveCharacterIDExtra.size()];
                for (int i = 0; i < retrieveCharacterIDExtra.size(); i++) {
                    characters[i] = retrieveCharacterIDExtra.get(i)[1];
                }

                String picked = "";
                if (characters.length > 0) {
                    picked = (String) JOptionPane.showInputDialog(donateGoldToChar, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
                } else {
                    JOptionPane.showMessageDialog(donateGoldToChar, "No characters found");
                }


                if (!"".equals(picked) && picked != null) {
                    int giver = tc.CharacterID;
                    int reciever = 0;
                    for (int i = 0; i < characters.length; i++) {
                        if (characters[i].equals(retrieveCharacterIDExtra.get(i)[1])) {
                            reciever = Integer.parseInt(retrieveCharacterIDExtra.get(i)[0]);
                        }

                    }
                    ArrayList<String> amount1 = tc.rdb.getCharacterAmounts(tc.CharacterName);
                    double gold = Integer.parseInt(amount1.get(0)) * 10.0 + Integer.parseInt(amount1.get(1)) + (Integer.parseInt(amount1.get(2)) * 1.0 / 10);
                    String mes = "How much gold do you wish to give, available gold " + gold;

                    System.out.println("g " + giver + " r " + reciever);

                    try {
                        double amountz = 0;
                        amountz = Double.parseDouble(JOptionPane.showInputDialog(mes));
                        if (amountz == 0) {
                            return;
                        }
                        if (gold >= amountz) {

                            //make my gold less
                            int tempa = Integer.parseInt(amount1.get(0)) * 100 + Integer.parseInt(amount1.get(1)) * 10 + Integer.parseInt(amount1.get(2));
                            tempa = tempa - (int) (amountz * 10);
                            int nplat = tempa / 100;
                            tempa = tempa - nplat * 100;
                            int ngold = tempa / 10;
                            tempa = tempa - ngold * 10;
                            int nsilver = tempa;
                            tc.rdb.modifyAmount(tc.CharacterName, nplat, ngold, nsilver);

                            //ok now make other persons more

                            ArrayList<String> amount2 = tc.rdb.getCharacterAmounts(picked);

                            tempa = Integer.parseInt(amount2.get(0)) * 100 + Integer.parseInt(amount2.get(1)) * 10 + Integer.parseInt(amount2.get(2));
                            tempa = tempa + (int) (amountz * 10);
                            nplat = tempa / 100;
                            tempa = tempa - nplat * 100;
                            ngold = tempa / 10;
                            tempa = tempa - ngold * 10;
                            nsilver = tempa;
                            tc.rdb.modifyAmount(picked, nplat, ngold, nsilver);


                        }
                    } catch (Exception ex) {
                    }

                }


            }
        });
        add(donateGoldToChar, c);
        c.gridx = 0;
        c.gridy = 1;
        donateGoldToPlayer.setPreferredSize(new Dimension(250, 60));
        donateGoldToPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        //maby later
       // add(donateGoldToPlayer, c);





    }
}
