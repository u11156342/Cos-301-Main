/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.Admin;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author Fiyah
 */
public class AddEventInterface extends JPanel {

    JTextPane description = new JTextPane();
    JScrollPane descriptionPane;
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
    JLabel l2 = new JLabel("Income Modifier %");
    JLabel l3 = new JLabel("Platinum Modifier");
    JLabel l4 = new JLabel("Gold Modifier");
    JLabel l5 = new JLabel("Silver Modifier");
    JLabel l6 = new JLabel("Defense Modifier");

    public void init(final TransferContainer tc, final int PID) {
        incomemod.setText("0");
        platmod.setText("0");
        goldmod.setText("0");
        silmod.setText("0");
        defmod.setText("0");
        happinessmod.setSelectedIndex(5);


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        descriptionPane = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setPreferredSize(new Dimension(400, 200));
        descriptionPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        incomemod.setPreferredSize(new Dimension(100, 40));
        platmod.setPreferredSize(new Dimension(100, 40));
        goldmod.setPreferredSize(new Dimension(100, 40));
        silmod.setPreferredSize(new Dimension(100, 40));
        defmod.setPreferredSize(new Dimension(100, 40));
        add.setPreferredSize(new Dimension(100, 40));
        cansel.setPreferredSize(new Dimension(100, 40));

        

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.insets = new Insets(10, 10, 10, 10);
        add(des, c);

        c.gridy = 2;

        c.gridheight = 4;
        c.gridwidth = 5;

        // description.setPreferredSize(new Dimension(500, 300));
        add(descriptionPane, c);

        c.gridwidth = 1;
        c.gridheight = 1;

        c.gridy = 7;
        //  add(exsplain, c);

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(l1, c);
        c.gridx = 1;
        add(happinessmod, c);


        c.gridy = 9;
        c.gridx = 0;
        add(l2, c);
        c.gridx = 1;
        c.gridy = 9;
        add(incomemod, c);

        c.gridy = 10;
        c.gridx = 0;
        add(l3, c);
        c.gridx = 1;
        c.gridy = 10;
        add(platmod, c);

        c.gridy = 11;
        c.gridx = 0;
        add(l4, c);
        c.gridx = 1;
        c.gridy = 11;
        add(goldmod, c);

        c.gridy = 12;
        c.gridx = 0;
        add(l5, c);
        c.gridx = 1;
        c.gridy = 12;
        add(silmod, c);

        c.gridy = 13;
        c.gridx = 0;
        //add(l6, c);
        c.gridx = 1;
        c.gridy = 13;
        //add(defmod, c);

        c.gridx = 0;
        c.gridy = 14;

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ename = JOptionPane.showInputDialog("What is the events name? ");

                try {
                    String desu = description.getText();

                    String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~:[]@!$&()*+,;=";

                    String validurl = "";
                    for (int i = 0; i < desu.length(); i++) {

                        if (valid.contains("" + desu.charAt(i))) {
                            validurl = validurl + desu.charAt(i);
                        } else {
                            validurl = validurl + "~";
                        }
                    }

                    if ("".equals(ename)) {
                        ename = "-";
                    }

                    if ("".equals(validurl)) {
                        validurl = "-";
                    }
                    int pm = 0;
                    int gm = 0;
                    int sm = 0;
                    int h = 0;
                    int i = 0;


                    boolean paramCheck = true;
                    try {
                        pm = Integer.parseInt(platmod.getText());
                        gm = Integer.parseInt(goldmod.getText());
                        sm = Integer.parseInt(silmod.getText());

                    } catch (Exception ex) {
                        paramCheck = false;
                        JOptionPane.showMessageDialog(platmod, "Please only enter valid numbers in the modifications");
                    }

                    try {
                        h = Integer.parseInt(haps[happinessmod.getSelectedIndex()]);

                    } catch (Exception ex) {
                        paramCheck = false;
                        JOptionPane.showMessageDialog(platmod, "Please only enter valid numbers for hapiness");
                    }

                    try {
                        i = Integer.parseInt(incomemod.getText());

                    } catch (Exception ex) {
                        paramCheck = false;
                        JOptionPane.showMessageDialog(platmod, "Please only enter valid numbers for income percentage");
                    }



                    if (paramCheck) {
                        if (tc.rdb.addEvent(PID, ename, validurl, pm, gm, sm, h, i)) {
                            tc.cardlayout.show(tc.contentpane, tc.Cmanager.AdminSearchInterfaces[tc.Cmanager.currentAdminSearchInterfaceCard].getName());
                        } else {
                            JOptionPane.showMessageDialog(platmod, "Event adding failed,please make sure not to use any special characters in the description");
                        }
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(platmod, "An error has occured, please make sure all data types are correct");
                }

            }
        });


        add.setPreferredSize(new Dimension(150, 60));
        add(add, c);




    }
}
