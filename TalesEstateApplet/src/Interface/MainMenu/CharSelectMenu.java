package Interface.MainMenu;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import talesestateapplet.BasePanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import talesestateapplet.Background;
import talesestateapplet.UserCharacter;

public class CharSelectMenu extends BasePanel {

    JComboBox chars;
    String[] CharList = null;
    RestFullAdapter ad = new RestFullAdapter();
    JButton btn;
    String UserId = "474C675A-EFE9-47B8-9AF5-01CEB4E2B98A";
    public String UserChar;
    public String UserCharId;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();
    Background bground;

    public CharSelectMenu(String name) {
        super(name);
        
        bground=new Background(ad.ImageAdapter(15));
        
        
        JPanel container=new JPanel();
        
        BufferedImage tobeIcon = ad.ImageAdapter(6);
        ImageIcon ic = new ImageIcon(tobeIcon);

        btn = new JButton(ic);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        
        
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ArrayList<String> ch = wrapper.retrieveCharactersOwnedByUser(UserId);
        CharList = new String[ch.size()];
        if (wrapper.checkHasCharacter(UserId)) {
            for (int a = 0; a < ch.size(); a++) {
                CharList[a] = ch.get(a);
            }
        }       

        c.gridy = 0;
        c.gridx = 1;
        JButton temp = new JButton(new ImageIcon(ad.ImageAdapter(11)));
        temp.setContentAreaFilled(false);
        temp.setBorderPainted(false);
        container.add(temp, c);

        chars = new JComboBox(CharList);
        c.gridy = 3;
        c.gridx = 1;
        c.insets = new Insets(100, 0, 0, 0);  //top padding
        container.add(chars, c);
        c.insets = new Insets(10, 0, 0, 0);  //top padding
        c.gridy = 4;
        c.gridx = 1;
        container.add(btn, c);

        
       //add(container,BorderLayout.CENTER);
        bground.add(container,BorderLayout.CENTER);
        add(bground);        
        
    }

    public void addNextActionListener(final UserCharacter c, final CardLayout cardlayout, final Container contentPane, final MainMenu ref) {

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                UserChar = CharList[chars.getSelectedIndex()];
                c.CharacterID = wrapper.retrieveCharacterID(UserChar);
                c.CharacterName = UserChar;
                wrapper.registerEstateCharacter(UserChar);
                ref.showMenu();
                cardlayout.show(contentPane, "MainMenu");
            }
        });

    }
}
