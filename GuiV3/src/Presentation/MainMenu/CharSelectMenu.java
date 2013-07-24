package Presentation.MainMenu;

import Data.Communication.RestFullDBAdapter;
import guiv3.BasePanel;
import guiv3.UserCharacter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class CharSelectMenu extends BasePanel {

    JComboBox chars;
    String[] CharList = null;
    RestFullDBAdapter ad = new RestFullDBAdapter();
    Button btn = new Button("Select character");
    String UserId = "474C675A-EFE9-47B8-9AF5-01CEB4E2B98A";
    public String UserChar;
    public String UserCharId;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();

    public CharSelectMenu(String name) {
        super(name);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ArrayList<String> ch = ad.retrieveCharactersOwnedByUser(UserId);
        CharList = new String[ch.size()];
        if (ad.checkHasCharacter(UserId)) {
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
