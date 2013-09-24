/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.RightsManagement;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class RightsSideMenu extends JPanel {

    public JButton addCharacter;
    public JButton removeCharacter;

    public RightsSideMenu(JTextPane text, final TransferContainer tc, final int PlotID) {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        addCharacter = new JButton("Add Character");
        addCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                ArrayList<String[]> retrieveCharacterIDExtra = tc.rdb.retrieveCharacterIDExtra(attempt);

                String[] characters;

                characters = new String[retrieveCharacterIDExtra.size()];
                for (int i = 0; i < retrieveCharacterIDExtra.size(); i++) {
                    characters[i] = retrieveCharacterIDExtra.get(i)[1];
                }

                String picked = "";
                if (characters.length > 0) {
                    picked = (String) JOptionPane.showInputDialog(addCharacter, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
                } else {
                    JOptionPane.showMessageDialog(addCharacter, "No characters found");
                }

                if (!"".equals(picked) && picked != null) {
                    AddPlayer pfr = new AddPlayer("pla");
                    pfr.init(tc, picked, PlotID);
                    tc.mainapplet.add(pfr, pfr.getName());
                    tc.cardlayout.show(tc.contentpane, "pla");
                }

            }
        });
        c.gridx = 2;
        c.gridy = 0;
        addCharacter.setPreferredSize(new Dimension(150, 60));
        add(addCharacter, c);

        removeCharacter = new JButton("Remove Character");
        removeCharacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String attempt = JOptionPane.showInputDialog("What is the characers name ? ");

                ArrayList<String[]> retrieveCharacterIDExtra = tc.rdb.retrieveCharacterIDExtra(attempt);

                String[] characters;

                characters = new String[retrieveCharacterIDExtra.size()];
                for (int i = 0; i < retrieveCharacterIDExtra.size(); i++) {
                    characters[i] = retrieveCharacterIDExtra.get(i)[1];
                }

                String picked = "";
                if (characters.length > 0) {
                    picked = (String) JOptionPane.showInputDialog(addCharacter, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
                } else {
                    JOptionPane.showMessageDialog(addCharacter, "No characters found");
                }

                if (!"".equals(picked) && picked != null) {

                    tc.rdb.removeAccess(PlotID, tc.rdb.retrieveCharacterID(picked));
                    tc.ri = new RightsInterface("right");
                    tc.mainapplet.add(tc.ri, tc.ri.getName());
                    tc.ri.init(tc, PlotID);
                    tc.cardlayout.show(tc.contentpane, "right");
                }

            }
        });
        c.gridx = 2;
        c.gridy = 1;
        removeCharacter.setPreferredSize(new Dimension(150, 60));
        add(removeCharacter, c);




    }
}
