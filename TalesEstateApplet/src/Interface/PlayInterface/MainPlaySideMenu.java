/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import Connections.RestFullDBAdapter;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import talesestateapplet.TalesEstateApplet;
import talesestateapplet.UserCharacter;

/**
 *
 * @author NightFiyah
 */
public class MainPlaySideMenu extends JPanel {

    JButton Report, Deposite, Withdraw, exspand, listBuildings, addBuildings, VisualInterface;

    public MainPlaySideMenu(final JTextPane textZone, final int PropertId, final TalesEstateApplet aThis, final CardLayout cardlayout, final Container contentPane,final String duchy,final UserCharacter uchar) {
        Report = new JButton("Status Report");
        Deposite = new JButton("Deposit gold");
        Withdraw = new JButton("Withdraw gold");
        exspand = new JButton("Exspand");
        listBuildings = new JButton("List Buildings");
        addBuildings = new JButton("Add Building");
        VisualInterface = new JButton("Visual Interface");

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.gridx = 2;
        c.gridy = 0;
        add(Report, c);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(30, 0, 0, 0);
        add(Deposite, c);
        c.gridx = 2;
        c.gridy = 2;
        c.insets = new Insets(30, 0, 0, 0);
        add(Withdraw, c);
        c.gridx = 2;
        c.gridy = 3;
        c.insets = new Insets(30, 0, 0, 0);
        add(exspand, c);
        c.gridy = 4;
        c.insets = new Insets(30, 0, 0, 0);
        add(listBuildings, c);
        c.gridy = 5;
        c.insets = new Insets(30, 0, 0, 0);
        add(addBuildings, c);
        c.gridy = 6;
        c.insets = new Insets(30, 0, 0, 0);
        add(VisualInterface, c);


        Report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestFullDBAdapter wrapper = new RestFullDBAdapter();
                textZone.setText(wrapper.getStatus(PropertId));
            }
        });
        Deposite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                double amount=Double.parseDouble(JOptionPane.showInputDialog("How much do you wish to deposit"));
            }
        });
        Withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount=Double.parseDouble(JOptionPane.showInputDialog("How much do you wish to Withdraw"));
            }
        });

        exspand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestFullDBAdapter wrapper = new RestFullDBAdapter();
                textZone.setText(wrapper.getStatus(PropertId));
            }
        });

        //use the property to get the info,then list all the buildins that are build onit
        listBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textZone.setText("");
            }
        });

        addBuildings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuildtabPanel Build = new BuildtabPanel("Build",duchy,PropertId,cardlayout,contentPane,uchar);
                aThis.add(Build, Build.getName());
                cardlayout.show(contentPane, "Build");
            }
        });
        VisualInterface.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayInterface visual=new PlayInterface("visual",aThis.getWidth(),aThis.getHeight(),PropertId,uchar);
                aThis.add(visual, visual.getName());
                cardlayout.show(contentPane, "visual");
            }
        });

    }
}
