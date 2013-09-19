/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.RightsManagement;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Fiyah
 */
public class AddPlayerForm extends JPanel {

    JLabel pname = new JLabel("Players name : ");
    JTextArea name = new JTextArea();
    JLabel withdraw = new JLabel("Withdraw/Deposit rights : ");
    JCheckBox withdaw_j = new JCheckBox();
    // withdraw/deposit
    JLabel building = new JLabel("Building Rights : ");
    JCheckBox building_j = new JCheckBox();
    // building
    JLabel status = new JLabel("Status View Rights : ");
    JCheckBox status_j = new JCheckBox();
    // status rights
    JLabel token = new JLabel("Visual Token Placing Rights : ");
    JCheckBox token_j = new JCheckBox();
    // visual token rights

    public AddPlayerForm() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;

        c.insets=new Insets(10,10,10,10);
        c.gridx = 0;
        add(pname, c);
        c.gridx = 1;
        name.setPreferredSize(new Dimension(100,40));
        add(name, c);

        c.gridy = 1;

        c.insets=new Insets(10,10,10,10);
        c.gridx = 0;
        add(withdraw, c);
        c.gridx = 1;
        add(withdaw_j, c);


        c.gridy = 2;

        c.insets=new Insets(10,10,10,10);
        c.gridx = 0;
        add(building, c);
        c.gridx = 1;
        add(building_j, c);

        c.gridy = 3;

        c.insets=new Insets(10,10,10,10);
        c.gridx = 0;
        add(status, c);
        c.gridx = 1;
        add(status_j, c);

        c.gridy = 4;

        c.insets=new Insets(10,10,10,10);
        c.gridx = 0;
        add(token, c);
        c.gridx = 1;
        add(token_j, c);



    }
}
