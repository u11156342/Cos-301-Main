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

    public RightsSideMenu(JTextPane text, final TransferContainer tc) {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        addCharacter=new JButton("Add Character");
        addCharacter.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AddPlayer pfr=new AddPlayer("pla");
                pfr.init(tc);
                tc.mainapplet.add(pfr,pfr.getName());
                tc.cardlayout.show(tc.contentpane,"pla");
                
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        addCharacter.setPreferredSize(new Dimension(150, 60));
        add(addCharacter, c);

    }
}
