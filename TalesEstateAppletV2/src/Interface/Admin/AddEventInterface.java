package Interface.Admin;

import java.awt.*;
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

//Interface used for adding events
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
    JLabel l3 = new JLabel("Platinum thrones Modifier");
    JLabel l4 = new JLabel("Gold crowns Modifier");
    JLabel l5 = new JLabel("Silver shields Modifier");
    JLabel l6 = new JLabel("Defense Modifier");

    public void init(final TransferContainer tc, final int PID, int CurrentDefence) {

        add = new JButton(new ImageIcon(tc.ad.ImageAdapter(116)));
        add.setContentAreaFilled(false);
        add.setBorderPainted(false);
        add.setCursor(new Cursor(Cursor.HAND_CURSOR));

        incomemod.setText("0          ");
        platmod.setText("0          ");
        goldmod.setText("0          ");
        silmod.setText("0          ");
        defmod.setText("0          ");
        happinessmod.setSelectedIndex(5);
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        descriptionPane = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        descriptionPane.setPreferredSize(new Dimension(400, 200));
        descriptionPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        JLabel def = new JLabel("Current defence : " + CurrentDefence);

        c.gridy = 0;
        c.gridwidth = 2;
        add(def, c);
        c.gridwidth = 2;
        c.gridy = 1;
        add(des, c);
        c.gridwidth = 6;
        add(descriptionPane, c);
        c.gridy = 2;
        add(l1, c);
        add(happinessmod, c);
        c.gridy = 3;
        add(l2, c);
        add(incomemod, c);
        c.gridy = 4;
        add(l3, c);
        add(platmod, c);
        c.gridy = 5;
        add(l4, c);
        add(goldmod, c);
        c.gridy = 6;
        add(l5, c);
        add(silmod, c);
        c.gridy = 7;
        add(l6, c);
        add(defmod, c);
        c.gridy = 8;
        c.gridwidth = 2;
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
                    int d = 0;

                    boolean paramCheck = true;
                    try {
                        pm = Integer.parseInt(platmod.getText().replaceAll(" ", ""));
                        gm = Integer.parseInt(goldmod.getText().replaceAll(" ", ""));
                        sm = Integer.parseInt(silmod.getText().replaceAll(" ", ""));

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
                        i = Integer.parseInt(incomemod.getText().replaceAll(" ", ""));

                    } catch (Exception ex) {
                        paramCheck = false;
                        JOptionPane.showMessageDialog(platmod, "Please only enter valid numbers for income percentage");
                    }

                    try {
                        d = Integer.parseInt(defmod.getText().replaceAll(" ", ""));

                    } catch (Exception ex) {
                        paramCheck = false;
                        JOptionPane.showMessageDialog(platmod, "Please only enter valid numbers for defence percentage");
                    }



                    if (paramCheck) {
                        if (tc.rdb.addEvent(PID, ename, validurl, pm, gm, sm, h, i, d)) {
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
        c.gridwidth = 2;
        add(add, c);
    }
}
