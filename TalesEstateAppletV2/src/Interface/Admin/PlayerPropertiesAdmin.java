package Interface.Admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;

public class PlayerPropertiesAdmin extends JPanel {

    int w;
    int h;
    PlayerCurrentPropertiesAdmin[] playersCurrentProperties;
    public int amount;

    PlayerPropertiesAdmin(int i, int i0, final TransferContainer tc, ArrayList<String[]> result) {

        w = i;
        h = i0;
        playersCurrentProperties = new PlayerCurrentPropertiesAdmin[result.size()];
        amount = result.size();
        setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));

        h = amount * 350;
        for (int a = 0; a < playersCurrentProperties.length; a++) {
            playersCurrentProperties[a] = new PlayerCurrentPropertiesAdmin(w, this, tc);
            // playersCurrentProperties[a].setPreferredSize(new Dimension(450, 300));
            playersCurrentProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);

            playersCurrentProperties[a].init(tc);
        }

        JComponent panel1 = makePanel("Panel #1", 1);


        panel1.setBackground(java.awt.Color.getHSBColor(tc.c1[0], tc.c1[1], tc.c1[2]));
        //  panel1.setPreferredSize(new Dimension(1000, h));
        add(panel1, BorderLayout.CENTER);



    }

    protected JComponent makePanel(String text, int type) {

        if (type == 1) {
            JPanel panel = new JPanel(false);
            GridLayout experimentLayout = new GridLayout(0, 1);
            panel.setLayout(experimentLayout);
            for (int a = 0; a < playersCurrentProperties.length; a++) {
                //   playersCurrentProperties[a].setBorder(BorderFactory.createLineBorder(Color.black));
                panel.add(playersCurrentProperties[a]);
            }
            return panel;
        }
        return null;

    }
}
