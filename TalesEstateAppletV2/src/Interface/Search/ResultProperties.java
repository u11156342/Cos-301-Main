package Interface.Search;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import talesestateappletv2.TransferContainer;


public class ResultProperties extends JPanel {

    int w;
    int h;
    ResultUnit[] searchedProperties;
    public int amount;

    public ResultProperties(int i, int i0,  ArrayList<String[]> result,TransferContainer tc) {

        w = i;
        h = i0;
        
        searchedProperties = new ResultUnit[result.size()];
        amount = result.size();

        for (int a = 0; a < searchedProperties.length; a++) {
            searchedProperties[a] = new ResultUnit(w, this);
            searchedProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);
            searchedProperties[a].duchy = result.get(a)[2];

            switch (result.get(a)[4]) {
                case "Poor":
                    searchedProperties[a].quality = 1;
                    break;
                case "Fine":
                    searchedProperties[a].quality = 2;
                    break;
                default:
                    searchedProperties[a].quality = 3;
                    break;
            }

            searchedProperties[a].size = Integer.parseInt(result.get(a)[3]);
            searchedProperties[a].tiles = tc.rdb.convertFromArray(result.get(a)[5]);
            searchedProperties[a].buildings = tc.rdb.convertFromArray(result.get(a)[6]);

            searchedProperties[a].init(tc);
        }

        JComponent panel1 = makePanel("Panel #1", 1);
        panel1.setPreferredSize(new Dimension(w, h));
        add(panel1);
    }

    protected JComponent makePanel(String text, int type) {

        if (type == 1) {
            JPanel panel = new JPanel(false);

            for (int a = 0; a < searchedProperties.length; a++) {
                panel.add(searchedProperties[a]);
               // searchedProperties[a].setBorder(BorderFactory.createLineBorder(Color.black));
            }
            return panel;
        }
        return null;

    }
}
