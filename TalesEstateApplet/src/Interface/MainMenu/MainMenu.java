package Interface.MainMenu;

import Interface.ManageInterface.ManageInterface;
import Interface.admin.AdminMain;
import talesestateapplet.BasePanel;
import talesestateapplet.UserCharacter;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import talesestateapplet.TalesEstateApplet;

/**
 *
 * @author NightFiyah
 */
public class MainMenu extends BasePanel {

    Button btn = new Button("Buy new Property");
    Button btn2 = new Button("View All my Properties");
    Button btn3 = new Button("Search For a Property");
    Button btn4 = new Button("Admin interface");
    JMenuBar menu = new JMenuBar();

    public MainMenu(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT) {
        super(name);
        init(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT);
    }

    public void init(int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT) {

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        Font f = new Font("Dialog", Font.HANGING_BASELINE, 24);


        JScrollPane MainMenuScrollPane = new JScrollPane(container);
        MainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        btn.setFont(f);

        c.gridy = 1;
        c.insets = new Insets(20, 0, 0, 0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide

        container.add(btn, c);
        btn2.setFont(f);

        c.gridy = 2;
        c.insets = new Insets(20, 0, 0, 0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide

        container.add(btn2, c);
        btn3.setFont(f);

        c.gridy = 3;
        c.insets = new Insets(20, 0, 0, 0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        container.add(btn3, c);

        btn4.setFont(f);

        c.gridy = 4;
        c.insets = new Insets(20, 0, 0, 0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        container.add(btn4, c);

        add(MainMenuScrollPane, BorderLayout.CENTER);
    }
    TalesEstateApplet a;

    public void addNextActionListener(ActionListener buttonListener, final UserCharacter Pchar, final TalesEstateApplet applet, final CardLayout cardlayout, final Container contentPane) {

        a = applet;
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardlayout.show(contentPane, "Buy");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                ManageInterface ManageI;
                ManageI = new ManageInterface("Manage", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, Pchar, applet, cardlayout, contentPane);
                applet.add(ManageI, ManageI.getName());
                cardlayout.show(contentPane, "Manage");
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardlayout.show(contentPane, "Search");
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                AdminMain admin;
                admin = new AdminMain("Admin", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, Pchar, applet, cardlayout, contentPane);
                applet.add(admin, admin.getName());
                cardlayout.show(contentPane, "Admin");
            }
        });

        JMenu Properties = new JMenu("Properties");
        JMenuItem newItem = new JMenuItem("Buy New Property");
        Properties.add(newItem);
        JMenuItem newItem1 = new JMenuItem("View All My Properties");
        Properties.add(newItem1);
        JMenuItem newItem2 = new JMenuItem("Search for a property");
        Properties.add(newItem2);
        JMenuItem newItem3 = new JMenuItem("Admin Interface");
        Properties.add(newItem3);

        menu.add(Properties);

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardlayout.show(contentPane, "Buy");
            }
        });

        newItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ManageInterface ManageI;
                ManageI = new ManageInterface("Manage", applet.JFXPANEL_WIDTH_INT, applet.JFXPANEL_HEIGHT_INT, Pchar, applet, cardlayout, contentPane);
                applet.add(ManageI, ManageI.getName());
                cardlayout.show(contentPane, "Manage");
            }
        });
        newItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardlayout.show(contentPane, "Search");
            }
        });
        newItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cardlayout.show(contentPane, "Admin");
            }
        });

    }

    public void showMenu() {
        a.setJMenuBar(menu);
        //frame.setJMenuBar(menu);
    }
}
