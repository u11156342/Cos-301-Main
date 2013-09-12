package Interface.MyProperties;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

public class PlayerProperties extends JPanel {

    int w;
    int h;
    PlayerOwnPanel[] playersCurrentProperties;

    PlayerProperties(TransferContainer tc) {
        w = tc.JFXPANEL_WIDTH_INT-300;

        ArrayList<String[]> result = tc.rdb.retrievePlotsOwnedByCharacter(tc.CharacterID);

        playersCurrentProperties = new PlayerOwnPanel[result.size()];
        int amount = result.size();
        h = amount * 200;

        for (int a = 0; a < playersCurrentProperties.length; a++) {
            playersCurrentProperties[a] = new PlayerOwnPanel(tc);
            playersCurrentProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);
            playersCurrentProperties[a].duchy = result.get(a)[3];
            playersCurrentProperties[a].amount = tc.rdb.getCurrentAmount(playersCurrentProperties[a].propertyID);
            ArrayList<String[]> list = new ArrayList();

            String[] t1 = {"poor", result.get(a)[15], result.get(a)[16]};
            list.add(t1);
            String[] t2 = {"fine", result.get(a)[13], result.get(a)[14]};
            list.add(t2);
            String[] t3 = {"Exquisite", result.get(a)[11], result.get(a)[12]};
            list.add(t3);

            playersCurrentProperties[a].quality = list;
            System.out.println(playersCurrentProperties[a].amount);
            playersCurrentProperties[a].size = Integer.parseInt(result.get(a)[4]);
            playersCurrentProperties[a].tiles = tc.rdb.convertFromArray(result.get(a)[5]);
            playersCurrentProperties[a].buildings = tc.rdb.convertFromArray(result.get(a)[6]);
            playersCurrentProperties[a].income = Double.parseDouble(result.get(a)[8]);
            playersCurrentProperties[a].hap = Integer.parseInt(result.get(a)[7]);
            playersCurrentProperties[a].wc = Integer.parseInt(result.get(a)[9]);
            playersCurrentProperties[a].wm = Integer.parseInt(result.get(a)[10]);
            playersCurrentProperties[a].init(tc);
        }




        JComponent panel1 = makePanel("Panel #1", 1);
        panel1.setPreferredSize(new Dimension(w, h));
        add(panel1);
    }

    protected JComponent makePanel(String text, int type) {

        if (type == 1) {
            JPanel panel = new JPanel(false);

            for (int a = 0; a < playersCurrentProperties.length; a++) {
                playersCurrentProperties[a].setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(playersCurrentProperties[a]);
            }
            return panel;
        }
        return null;

    }
}
