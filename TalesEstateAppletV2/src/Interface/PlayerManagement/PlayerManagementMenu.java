package Interface.PlayerManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

public class PlayerManagementMenu extends JPanel {

    JButton donateGoldToChar;
    JButton donateGoldToPlayer;

    public PlayerManagementMenu(final TransferContainer tc) {

        donateGoldToChar = new JButton(new ImageIcon(tc.ad.ImageAdapter(134)));
        donateGoldToChar.setContentAreaFilled(false);
        donateGoldToChar.setBorderPainted(false);
        donateGoldToChar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        donateGoldToPlayer = new JButton("Give gold to estate");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        c.gridx = 0;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridy = 0;
        donateGoldToChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                if ("".equals(attempt) || attempt == null) {
                    return;
                }
                ArrayList<String[]> retrieveCharacterIDExtra = tc.rdb.retrieveCharacterIDExtra(attempt);

                if (retrieveCharacterIDExtra == null) {
                    JOptionPane.showMessageDialog(donateGoldToChar, "No characters found");
                    return;
                }
                if (retrieveCharacterIDExtra.isEmpty()) {
                    JOptionPane.showMessageDialog(donateGoldToChar, "No characters found");
                    return;
                }
                String[] characters;
                String[] correctname;
                characters = new String[retrieveCharacterIDExtra.size()];
                correctname = new String[retrieveCharacterIDExtra.size()];
                for (int i = 0; i < retrieveCharacterIDExtra.size(); i++) {
                    correctname[i] = retrieveCharacterIDExtra.get(i)[1];
                    StringTokenizer tokens = new StringTokenizer(retrieveCharacterIDExtra.get(i)[1], "&*&");
                    characters[i] = tokens.nextToken();
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
                    ArrayList<String> amount1 = tc.rdb.getCharacterAmounts(tc.rdb.retrieveCharacterName(tc.CharacterID));

                    double gold = Integer.parseInt(amount1.get(0)) * 10.0 + Integer.parseInt(amount1.get(1)) + (Integer.parseInt(amount1.get(2)) * 1.0 / 10);
                    String mes = "How much Gold crowns do you wish to give, available Gold crowns " + gold;

                    try {
                        double amountz = 0;
                        try {
                            amountz = Double.parseDouble(JOptionPane.showInputDialog(mes));
                        } catch (Exception ee) {
                            JOptionPane.showMessageDialog(donateGoldToChar, "Please enter a valid number");
                            return;
                        }
                        if (amountz == 0) {
                            return;
                        }
                        if (gold >= amountz && amountz > 0) {

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

                            String corectUserName = "";
                            for (int i = 0; i < characters.length; i++) {
                                if (characters[i].contains(picked)) {
                                    corectUserName = correctname[i];
                                }
                            }
                            ArrayList<String> amount2 = tc.rdb.getCharacterAmounts(corectUserName);

                            tempa = Integer.parseInt(amount2.get(0)) * 100 + Integer.parseInt(amount2.get(1)) * 10 + Integer.parseInt(amount2.get(2));
                            tempa = tempa + (int) (amountz * 10);
                            nplat = tempa / 100;
                            tempa = tempa - nplat * 100;
                            ngold = tempa / 10;
                            tempa = tempa - ngold * 10;
                            nsilver = tempa;
                            tc.rdb.modifyAmount(corectUserName, nplat, ngold, nsilver);


                            StringTokenizer token = new StringTokenizer(tc.CharacterName, "&*&");
                            String firstName = token.nextToken();

                            tc.rdb.LogChar(tc.CharacterID, firstName + "*gave*" + amountz + "*Gold*crowns*to*" + picked);
                            tc.rdb.LogChar(tc.rdb.retrieveCharacterID(picked), "Player*" + firstName + "*gave*you*" + amountz + "*Gold crowns");

                        } else {
                            JOptionPane.showMessageDialog(donateGoldToChar, "Amount is invalid,please try again");
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

        JButton CharacterLog = new JButton(new ImageIcon(tc.ad.ImageAdapter(121)));
        CharacterLog.setBorderPainted(false);
        CharacterLog.setContentAreaFilled(false);
        CharacterLog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CharacterLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.clog.refres(tc);
                tc.cardlayout.show(tc.contentpane, "cLog");
            }
        });
        c.gridx = 0;
        c.gridy = 1;

        add(CharacterLog, c);
    }
}
