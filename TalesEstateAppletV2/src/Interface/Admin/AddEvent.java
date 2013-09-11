/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class AddEvent extends BasePanel {

    JTextPane description = new JTextPane();
    String[] haps = {"-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3", "4", "5"};
    JComboBox happinessmod = new JComboBox(haps);
    JTextField incomemod = new JTextField();
    JTextField platmod = new JTextField();
    JTextField goldmod = new JTextField();
    JTextField silmod = new JTextField();
    JTextField defmod = new JTextField();
    JButton add = new JButton("add");
    JButton cansel = new JButton("cancel");
    JLabel des = new JLabel("Event Description");
    JLabel exsplain = new JLabel("These values will modify current values, 0 is nothing happens, -4 is 4 less and so on");
    JLabel l1 = new JLabel("Happiness Modifier");
    JLabel l2 = new JLabel("Income Modifier");
    JLabel l3 = new JLabel("Platinum Modifier");
    JLabel l4 = new JLabel("Gold Modifier");
    JLabel l5 = new JLabel("Silver Modifier");
    JLabel l6 = new JLabel("Defense Modifier");

    public AddEvent(String name, final TransferContainer tc) {
        super(name);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        incomemod.setPreferredSize(new Dimension(100, 40));
        platmod.setPreferredSize(new Dimension(100, 40));
        goldmod.setPreferredSize(new Dimension(100, 40));
        silmod.setPreferredSize(new Dimension(100, 40));
        defmod.setPreferredSize(new Dimension(100, 40));







        c.gridx = 0;
        c.gridy = 0;



        add(des, c);

        c.gridy = 1;

        c.gridheight = 4;
        c.gridwidth = 5;

        description.setPreferredSize(new Dimension(500, 300));
        add(description, c);

        c.gridwidth = 1;
        c.gridheight = 1;

        c.gridy = 6;
        add(exsplain, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(l1, c);
        c.gridx = 1;
        add(happinessmod, c);


        c.gridy = 8;
        c.gridx = 0;
        add(l2, c);
        c.gridx = 1;
        c.gridy = 8;
        add(incomemod, c);

        c.gridy = 9;
        c.gridx = 0;
        add(l3, c);
        c.gridx = 1;
        c.gridy = 9;
        add(platmod, c);

        c.gridy = 10;
        c.gridx = 0;
        add(l4, c);
        c.gridx = 1;
        c.gridy = 10;
        add(goldmod, c);

        c.gridy = 11;
        c.gridx = 0;
        add(l5, c);
        c.gridx = 1;
        c.gridy = 11;
        add(silmod, c);

        c.gridy = 12;
        c.gridx = 0;
        add(l6, c);
        c.gridx = 1;
        c.gridy = 12;
        add(defmod, c);

        c.gridx = 0;
        c.gridy = 13;
        add(add, c);

        c.gridx = 1;
        c.gridy = 13;
        add(cansel, c);

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "AdminS");
            }
        });
        c.gridy = 14;
        add(back, c);

    }
}
