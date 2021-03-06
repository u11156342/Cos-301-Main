package Interface.MainMenu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import talesestateappletv2.BasePanel;
import talesestateappletv2.CookieReader;
import talesestateappletv2.TransferContainer;

public class CharSelectMenu extends BasePanel {

    JComboBox chars;
    String[] CharList = null;
    JButton btn;
    String UserId = "";

    public CharSelectMenu(String name, TransferContainer tc) {
        super(name, tc);
        CookieReader cr = new CookieReader(tc);
        while (cr.userIDs.isEmpty()) {
            JOptionPane.showMessageDialog(btn, "You need to log into the site first, or restart browser");
            return;
        }
        float[] c1 = java.awt.Color.RGBtoHSB(142, 117, 92, null);
        setBackground(java.awt.Color.getHSBColor(c1[0], c1[1], c1[2]));
        UserId = cr.userIDs.get(cr.userIDs.size() - 1);
        JPanel container = new JPanel();
        container.setBackground(java.awt.Color.getHSBColor(c1[0], c1[1], c1[2]));
        BufferedImage tobeIcon = tc.ad.ImageAdapter(6);
        ImageIcon ic = new ImageIcon(tobeIcon);
        btn = new JButton(ic);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        repaint();
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setContentAreaFilled(true);
                btn.setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setContentAreaFilled(false);
                btn.setBorderPainted(false);
            }
        });

        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ArrayList<String> ch = tc.rdb.retrieveCharactersOwnedByUser(UserId);
        CharList = new String[ch.size()];
        if (tc.rdb.checkHasCharacter(UserId)) {
            for (int a = 0; a < ch.size(); a++) {
                CharList[a] = ch.get(a);
            }
        }
        c.gridy = 0;
        c.gridx = 1;
        JButton temp = new JButton(new ImageIcon(tc.ad.ImageAdapter(11)));
        temp.setContentAreaFilled(false);
        temp.setBorderPainted(false);
        container.add(temp, c);
        chars = new JComboBox(CharList);
        chars.setFont(new Font("Dialog", Font.HANGING_BASELINE, 24));
        c.gridy = 3;
        c.gridx = 1;
        c.insets = new Insets(70, 0, 0, 0);
        container.add(chars, c);
        c.insets = new Insets(30, 0, 0, 0);
        c.gridy = 4;
        c.gridx = 1;
        container.add(btn, c);
        add(container, BorderLayout.CENTER);
    }

    public void addNextActionListener(final TransferContainer tc, final MainMenu ref) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                tc.CharacterName = CharList[chars.getSelectedIndex()];
                tc.rdb.registerEstateCharacter(tc.CharacterName, UserId);
                tc.CharacterID = tc.rdb.retrieveCharacterID(tc.CharacterName);
                tc.CharacterName = tc.rdb.retrieveCharacterName(tc.CharacterID);
                System.out.println(tc.CharacterID);
                tc.MainMenu.init(tc);
                tc.MainMenu.addNextActionListener(tc);
                tc.cardlayout.show(tc.contentpane, "MainMenu");
            }
        });

    }
}
