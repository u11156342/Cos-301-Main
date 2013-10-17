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
        w = tc.JFXPANEL_WIDTH_INT-260;


        ArrayList<String[]> result = tc.rdb.retrievePlotsOwnedByCharacter(tc.CharacterID);
        ArrayList<String[]> AllPlotsIHaveAccess = tc.rdb.AllPlotsIHaveAccess(tc.CharacterID);

        // this.setPreferredSize(new Dimension(tc.JFXPANEL_WIDTH_INT,300*result.size()));

        System.out.println("retrieving plots " + (result.size() + AllPlotsIHaveAccess.size()));
        playersCurrentProperties = new PlayerOwnPanel[(result.size() + AllPlotsIHaveAccess.size())];
        int amount = result.size() + AllPlotsIHaveAccess.size();
        h = amount * 600;

        for (int a = 0; a < result.size(); a++) {
            playersCurrentProperties[a] = new PlayerOwnPanel(tc);
            playersCurrentProperties[a].setPreferredSize(new Dimension(300, 500));
            playersCurrentProperties[a].propertyID = Integer.parseInt(result.get(a)[0]);
            playersCurrentProperties[a].duchy = result.get(a)[3];
            playersCurrentProperties[a].amount = tc.rdb.getCurrentAmount(playersCurrentProperties[a].propertyID);
            try
            {
            playersCurrentProperties[a].county = result.get(a)[19];
            }catch(Exception e)
            {
                
            }
            playersCurrentProperties[a].plotname = result.get(a)[18].replaceAll("\\.", " ");
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
            playersCurrentProperties[a].init(tc, true);
        }


        for (int i = result.size(); i < (result.size() + AllPlotsIHaveAccess.size()); i++) {
            System.out.println("adding right prop");
            playersCurrentProperties[i] = new PlayerOwnPanel(tc);

            //  playersCurrentProperties[i].setPreferredSize(new Dimension(300, 510));

            ArrayList<String> retrievePlotDetails = tc.rdb.retrievePlotDetails(Integer.parseInt(AllPlotsIHaveAccess.get(i - result.size())[0]));

            playersCurrentProperties[i].propertyID = Integer.parseInt(AllPlotsIHaveAccess.get(i - result.size())[0]);
            System.out.println(Integer.parseInt(AllPlotsIHaveAccess.get(i - result.size())[0]));
            playersCurrentProperties[i].duchy = retrievePlotDetails.get(3);
            playersCurrentProperties[i].amount = tc.rdb.getCurrentAmount(playersCurrentProperties[i].propertyID);
            ArrayList<String[]> list = new ArrayList();

            String[] t1 = {"poor", retrievePlotDetails.get(15), retrievePlotDetails.get(16)};
            list.add(t1);
            String[] t2 = {"fine", retrievePlotDetails.get(13), retrievePlotDetails.get(14)};
            list.add(t2);
            String[] t3 = {"Exquisite", retrievePlotDetails.get(11), retrievePlotDetails.get(12)};
            list.add(t3);

            playersCurrentProperties[i].quality = list;
            System.out.println(playersCurrentProperties[i].amount);
            playersCurrentProperties[i].size = Integer.parseInt(retrievePlotDetails.get(4));
            playersCurrentProperties[i].tiles = tc.rdb.convertFromArray(retrievePlotDetails.get(5));
            playersCurrentProperties[i].buildings = tc.rdb.convertFromArray(retrievePlotDetails.get(6));
            playersCurrentProperties[i].income = Double.parseDouble(retrievePlotDetails.get(8));
            playersCurrentProperties[i].hap = Integer.parseInt(retrievePlotDetails.get(7));
            playersCurrentProperties[i].wc = Integer.parseInt(retrievePlotDetails.get(9));
            playersCurrentProperties[i].wm = Integer.parseInt(retrievePlotDetails.get(10));
            playersCurrentProperties[i].county = retrievePlotDetails.get(19);
            playersCurrentProperties[i].plotname = retrievePlotDetails.get(18).replaceAll("\\.", " ");
            playersCurrentProperties[i].init(tc, false);
        }




        JComponent panel1 = makePanel("Panel #1", 1, result.size());
        panel1.setPreferredSize(new Dimension(w, h));
        add(panel1);
    }

    protected JComponent makePanel(String text, int type, int bound) {

        if (type == 1) {
            JPanel panel = new JPanel(false);

            for (int a = 0; a < playersCurrentProperties.length; a++) {
                if (a >= bound) {
                    playersCurrentProperties[a].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    panel.add(playersCurrentProperties[a]);
                } else {
                    //     playersCurrentProperties[a].setBorder(BorderFactory.createLineBorder(Color.black));
                    panel.add(playersCurrentProperties[a]);
                }
            }
            return panel;
        }
        return null;

    }
}
