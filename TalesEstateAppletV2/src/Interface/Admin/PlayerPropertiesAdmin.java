package Interface.Admin;

import Connections.RestFullDBAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
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

        if (amount == 0) {
            h = 300;
            
        }
        if(amount==1)
        {
            h=300;
        }
        else
        h = amount * 75;
        for (int a = 0; a < playersCurrentProperties.length; a++) {
            playersCurrentProperties[a] = new PlayerCurrentPropertiesAdmin(w, this, tc);
            playersCurrentProperties[a].setPreferredSize(new Dimension(257, 120));
            playersCurrentProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);

            playersCurrentProperties[a].init(tc);
        }

        JComponent panel1 = makePanel("Panel #1", 1);
        panel1.setPreferredSize(new Dimension(w, h));
        add(panel1,BorderLayout.CENTER);


        
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
