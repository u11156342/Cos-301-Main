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

public class RightsSideMenu extends JPanel {

    public JButton addCharacter;
    public JButton removeCharacter;

    public RightsSideMenu(JTextPane text, final TransferContainer tc, final int PlotID) {

        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
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
                    characters[i] = retrieveCharacterIDExtra.get(i)[1].substring(0, retrieveCharacterIDExtra.get(i)[1].indexOf("&*&"));
                }
                String picked = "";
                if (characters.length > 0) {
                    picked = (String) JOptionPane.showInputDialog(addCharacter, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
                } else {
                    JOptionPane.showMessageDialog(addCharacter, "No characters found");
                }
                if (!"".equals(picked) && picked != null) {
                    AddPlayer pfr = tc.Cmanager.getAddPlayereCard();
                    pfr.init(tc, picked, PlotID);
                    tc.cardlayout.show(tc.contentpane, pfr.getName());
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
                    characters[i] = retrieveCharacterIDExtra.get(i)[1].substring(0, retrieveCharacterIDExtra.get(i)[1].indexOf("&*&"));
                }
                String picked = "";
                if (characters.length > 0) {
                    picked = (String) JOptionPane.showInputDialog(addCharacter, "Choose the character you are looking for : ", "", JOptionPane.QUESTION_MESSAGE, null, characters, characters[0]);
                } else {
                    JOptionPane.showMessageDialog(addCharacter, "No characters found");
                }

                if (!"".equals(picked) && picked != null) {

                    RightsInterface card = tc.Cmanager.getRightsInterfacesCard();
                    tc.rdb.removeAccess(PlotID, tc.rdb.retrieveCharacterID(picked));
                    card.init(tc, PlotID);
                    tc.cardlayout.show(tc.contentpane, card.getName());
                }
            }
        });
        c.gridx = 2;
        c.gridy = 1;
        removeCharacter.setPreferredSize(new Dimension(150, 60));
        add(removeCharacter, c);




    }
}
