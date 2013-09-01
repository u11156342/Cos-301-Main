package Interface.MainMenu;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import Interface.MyProperties.MyPropertiesInterface;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import talesestateappletv2.BasePanel;
import talesestateappletv2.TransferContainer;

/**
 *
 * @author NightFiyah
 */
public class MainMenu extends BasePanel {

    JButton btn = new JButton("Buy new Property");
    JButton btn2 = new JButton("View All my Properties");
    JButton btn3 = new JButton("Search For a Property");
    JButton btn4 = new JButton("Admin interface");
    JMenuBar menu = new JMenuBar();

    public MainMenu(String name, TransferContainer tc) {
        super(name);
        init(tc);
    }

    public void init(TransferContainer tc) {


        JButton Title = new JButton(new ImageIcon(tc.ad.ImageAdapter(13)));

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
        GridBagConstraints c = new GridBagConstraints();

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

        container.add(btn, c);
        btn2.setFont(f);

        c.gridy = 2;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;

        container.add(btn2, c);
        btn3.setFont(f);

        c.gridy = 3;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;
        container.add(btn3, c);

        btn4.setFont(f);

        c.gridy = 4;
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridwidth = 2;
        container.add(btn4, c);
        add(MainMenuScrollPane, BorderLayout.CENTER);
    }

    public void addNextActionListener(ActionListener buttonListener, final TransferContainer tc) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                tc.cardlayout.show(tc.contentpane, "Buy");
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                MyPropertiesInterface mProp;
                mProp = new MyPropertiesInterface("MyProp", tc);
                tc.mainapplet.add(mProp, mProp.getName());
                tc.cardlayout.show(tc.contentpane, "MyProp");
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
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
                tc.cardlayout.show(tc.contentpane, "Buy");
            }
        });

        newItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
            }
        });
        newItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
            }
        });
        newItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
            }
        });

    }

    public void showMenu(TransferContainer tc) {
        // tc.mainapplet.setJMenuBar(menu);
        tc.mainframe.setJMenuBar(menu);
    }
}
