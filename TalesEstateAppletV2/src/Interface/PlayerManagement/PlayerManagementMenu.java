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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Fiyah
 */
public class PlayerManagementMenu extends JPanel {

    JButton donateGoldToChar = new JButton("Give gold to character");
    JButton donateGoldToPlayer = new JButton("Give gold to estate");

    public PlayerManagementMenu() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        donateGoldToChar.setPreferredSize(new Dimension(250, 60));

        donateGoldToChar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                String[] characters = {"piet", "ben", "jave"};
                String picked = (String) JOptionPane.showInputDialog(donateGoldToChar, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);

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
        add(donateGoldToPlayer, c);





    }
}
