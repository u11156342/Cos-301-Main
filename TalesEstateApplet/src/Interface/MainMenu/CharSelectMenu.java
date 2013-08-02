package Interface.MainMenu;

import Connections.RestFullAdapter;
import Connections.RestFullDBAdapter;
import com.sun.javafx.css.Stylesheet;
import com.sun.javafx.css.parser.CSSParser;
import talesestateapplet.BasePanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import talesestateapplet.UserCharacter;

public class CharSelectMenu extends BasePanel {

    JComboBox chars;
    String[] CharList = null;
    RestFullAdapter ad = new RestFullAdapter();
    JButton btn ;
    String UserId = "474C675A-EFE9-47B8-9AF5-01CEB4E2B98A";
    public String UserChar;
    public String UserCharId;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();

    public CharSelectMenu(String name) {
        super(name);
        
        
        
       BufferedImage tobeIcon=ad.ImageAdapter(6);       
       ImageIcon water = new ImageIcon(tobeIcon); 
       
        btn=new JButton(water);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
      
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ArrayList<String> ch = wrapper.retrieveCharactersOwnedByUser(UserId);
        CharList = new String[ch.size()];
        if (wrapper.checkHasCharacter(UserId)) {
            for (int a = 0; a < ch.size(); a++) {
                CharList[a] = ch.get(a);
            }
        }
        chars = new JComboBox(CharList);
        c.gridy = 1;
        add(chars, c);
        c.gridy = 2;
        add(btn, c);
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
