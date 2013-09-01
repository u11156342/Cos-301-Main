/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.TextManage;

import java.awt.*;
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
 * @author NightFiyah
 */
public class MainPlaySideMenu extends JPanel {

    public JButton Report, Deposite, Withdraw, exspand, listBuildings, addBuildings, VisualInterface;
    final MainPlaySideMenu ref = this;
    int size;
    int[][] tiles;
    int[][] buildings;
    public ArrayList<String> amount1;
    public ArrayList<String> amount2;
    public int pId;

    public MainPlaySideMenu(final JTextPane textZone, TransferContainer tc,int p) {
        pId=p;
        Report = new JButton("Status Report");
        Deposite = new JButton("Deposit gold");
        Withdraw = new JButton("Withdraw gold");
        exspand = new JButton("Exspand");
        listBuildings = new JButton("List Buildings");
        addBuildings = new JButton("Add Building");
        VisualInterface = new JButton("Visual Interface");

        amount1 = tc.rdb.getCharacterAmounts(tc.CharacterName);
        amount2 = tc.rdb.getCurrentAmount(pId);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 2;
        c.gridy = 0;
        Report.setPreferredSize(new Dimension(150, 60));
        add(Report, c);
        c.gridx = 2;
        c.gridy = 1;
        // c.insets = new Insets(30, 0, 0, 0);
        Deposite.setPreferredSize(new Dimension(150, 60));
        add(Deposite, c);
        c.gridx = 2;
        c.gridy = 2;
        //  c.insets = new Insets(30, 0, 0, 0);
        Withdraw.setPreferredSize(new Dimension(150, 60));
        add(Withdraw, c);
        c.gridx = 2;
        c.gridy = 3;
        // c.insets = new Insets(30, 0, 0, 0);
        exspand.setPreferredSize(new Dimension(150, 60));
        add(exspand, c);
        c.gridy = 4;
        //  c.insets = new Insets(30, 0, 0, 0);
        listBuildings.setPreferredSize(new Dimension(150, 60));
        add(listBuildings, c);
        c.gridy = 5;
        //  c.insets = new Insets(30, 0, 0, 0);
        addBuildings.setPreferredSize(new Dimension(150, 60));
        add(addBuildings, c);
        c.gridy = 6;
        //  c.insets = new Insets(30, 0, 0, 0);
        VisualInterface.setPreferredSize(new Dimension(150, 60));
        add(VisualInterface, c);


        Report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        Deposite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double gold=Integer.parseInt(amount1.get(0))*10.0 + Integer.parseInt(amount1.get(1)) + Integer.parseInt(amount1.get(2))/10;
                String mes = "How much gold do you wish to deposit, available gold "+gold;
                try {
                    double amountz = Double.parseDouble(JOptionPane.showInputDialog(mes));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        Withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 double gold=Integer.parseInt(amount2.get(0))*10.0 + Integer.parseInt(amount2.get(1)) + Integer.parseInt(amount2.get(2))/10;
                String mes = "How much gold do you wish to Withdraw, available gold " +gold;
                try {
                    double amountz = Double.parseDouble(JOptionPane.showInputDialog(mes));
                    if(gold>=amountz)
                    {
                        tc.rdb.modifyAmount(mes, PROPERTIES, HEIGHT, ALLBITS);
                        tc.rdb.
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        exspand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        //use the property to get the info,then list all the buildins that are build onit
        listBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        addBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        VisualInterface.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

    }
}
