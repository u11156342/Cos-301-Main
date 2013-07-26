package Interface.ManageInterface;

import Connections.RestFullDBAdapter;
import talesestateapplet.UserCharacter;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import talesestateapplet.TalesEstateApplet;

public class PlayerProperties extends JPanel {

    int w;
    int h;
    PlayerOwnPanel[] playersCurrentProperties;
    public int amount;
    RestFullDBAdapter wrapper = new RestFullDBAdapter();

    PlayerProperties(int i, UserCharacter user, TalesEstateApplet aThis, CardLayout cardlayout, Container contentPane) {

        w = i;


        ArrayList<String[]> result = wrapper.retrievePlotsOwnedByCharacter(user.CharacterID);
        playersCurrentProperties = new PlayerOwnPanel[result.size()];
        amount = result.size();

        if (amount == 0) {
            h = 300;
            JOptionPane.showMessageDialog(contentPane, "You Currently own no properties");
        }

        h = amount * 140;
        for (int a = 0; a < playersCurrentProperties.length; a++) {
            playersCurrentProperties[a] = new PlayerOwnPanel(w, this);
            playersCurrentProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);
            playersCurrentProperties[a].duchy = result.get(a)[2];

            switch (result.get(a)[4]) {
                case "Poor":
                    playersCurrentProperties[a].quality = 1;
                    break;
                case "Fine":
                    playersCurrentProperties[a].quality = 2;
                    break;
                default:
                    playersCurrentProperties[a].quality = 3;
                    break;
            }

            playersCurrentProperties[a].size = Integer.parseInt(result.get(a)[3]);
            playersCurrentProperties[a].tiles = wrapper.convertFromArray(result.get(a)[5]);
            playersCurrentProperties[a].buildings = wrapper.convertFromArray(result.get(a)[6]);

            playersCurrentProperties[a].init(aThis, user, cardlayout, contentPane);
        }




        JComponent panel1 = makePanel("Panel #1", 1);
        panel1.setPreferredSize(new Dimension(w, h));
        add(panel1);
    }

    protected JComponent makePanel(String text, int type) {

        if (type == 1) {
            JPanel panel = new JPanel(false);
            panel.setBorder(BorderFactory.createLineBorder(Color.black));

            for (int a = 0; a < playersCurrentProperties.length; a++) {
                panel.add(playersCurrentProperties[a]);
            }
            return panel;
        }
        return null;

    }
}
