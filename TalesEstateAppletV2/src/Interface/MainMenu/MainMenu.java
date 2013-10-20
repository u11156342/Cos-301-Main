package Interface.MainMenu;

import Interface.MyProperties.MyPropertiesInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TalesEstateAppletV2;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author NightFiyah
 */
public class MainMenu extends BasePanel {

    JButton btn = new JButton("Buy new Property");
    JButton pman = new JButton("Player Management");
    JButton btn2 = new JButton("View All my Properties");
    JButton btn3 = new JButton("Search For a Property");
    JButton btn4 = new JButton("Admin interface");
    TalesEstateAppletV2 applet;
    GridBagConstraints c = new GridBagConstraints();

    public MainMenu(String name, TransferContainer tc, TalesEstateAppletV2 me) {
        super(name, tc);
        applet = me;
       
        setBackground(java.awt.Color.getHSBColor(tc.c1[0],tc. c1[1],tc. c1[2]));

    }

    public void init(TransferContainer tc) {


        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(13)));

        pman = new JButton(new ImageIcon(tc.ad.ImageAdapter(44)));
        pman.setContentAreaFilled(false);
        pman.setBorderPainted(false);
        pman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn = new JButton(new ImageIcon(tc.ad.ImageAdapter(7)));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn2 = new JButton(new ImageIcon(tc.ad.ImageAdapter(8)));
        btn2.setContentAreaFilled(false);
        btn2.setBorderPainted(false);
        btn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn3 = new JButton(new ImageIcon(tc.ad.ImageAdapter(10)));
        btn3.setContentAreaFilled(false);
        btn3.setBorderPainted(false);
        btn3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn4 = new JButton(new ImageIcon(tc.ad.ImageAdapter(9)));
        btn4.setContentAreaFilled(false);
        btn4.setBorderPainted(false);
        btn4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());

        
        container.setBackground(java.awt.Color.getHSBColor(tc.c1[0],tc.c1[1],tc.c1[2]));
        Font f = new Font("Dialog", Font.HANGING_BASELINE, 24);


        JScrollPane MainMenuScrollPane = new JScrollPane(container);
        MainMenuScrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        c.gridy = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 4;

        Title.setContentAreaFilled(false);
        Title.setBorderPainted(false);
        container.add(Title, c);



        btn.setFont(f);

        c.gridy = 1;
        c.insets = new Insets(70, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;

        container.add(pman, c);

        c.gridy = 2;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;

        container.add(btn, c);
        btn2.setFont(f);

        c.gridy = 3;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;

        container.add(btn2, c);
        btn3.setFont(f);

        c.gridy = 4;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;
        container.add(btn3, c);

        btn4.setFont(f);

        c.gridy = 5;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;
        if (tc.rdb.isAdmin(tc.CharacterID)) {
            container.add(btn4, c);
        }
        add(MainMenuScrollPane, BorderLayout.CENTER);
    }

    public void addNextActionListener(final TransferContainer tc) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "Buy");
            }
        });
        pman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tc.pmI.init(tc);
                tc.cardlayout.show(tc.contentpane, "PlayerMan");

            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {


                MyPropertiesInterface card = tc.Cmanager.getMyPropertiesCard();
                card.init();
                tc.cardlayout.show(tc.contentpane, card.getName());
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "Search");
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "AMain");
            }
        });
    }
}
