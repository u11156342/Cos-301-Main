package Interface.Admin;

import Interface.BrowseInterface.BrowseInterface;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import talesestateappletv2.TransferContainer;

public class PlayerCurrentPropertiesAdmin extends JPanel {

    public JTextArea statusArea = new JTextArea();
    String[] coms = {"Status", "Add event", "View Plot"};
    JComboBox commands = new JComboBox(coms);
    JButton bt = new JButton("next");
    String owner;
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    int[][] tiles;
    int[][] buildings;

    PlayerCurrentPropertiesAdmin(int w, PlayerPropertiesAdmin aThis, final TransferContainer t) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setColumns(4);
        statusArea.setEditable(false);
        statusArea.setPreferredSize(new Dimension(170, 95));

        //init();

        bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = commands.getSelectedIndex();

                if (selectedIndex == 0) {
                    DetailedStatus ds = t.Cmanager.getDetailedStatusCard();
                    ds.init(propertyID, t);   
                    t.cardlayout.show(t.contentpane, ds.getName());
                } else if (selectedIndex == 1) {
                    AddEvent ev = t.Cmanager.getAddEventCard();
                    ev.init(t, propertyID);
                    t.cardlayout.show(t.contentpane, ev.getName());
                } else if (selectedIndex == 2) {
                    System.out.println(propertyID);
                    ArrayList<String> retrievePlotDetails = t.rdb.retrievePlotDetails(propertyID);

                    size = Integer.parseInt("" + retrievePlotDetails.get(4));
                    tiles = t.rdb.convertFromArray("" + retrievePlotDetails.get(5));
                    buildings = t.rdb.convertFromArray("" + retrievePlotDetails.get(6));

                    BrowseInterface bi = t.Cmanager.getBrowseInterfacesCard();
                    bi.init(t, propertyID, size, tiles, buildings);
                    t.cardlayout.show(t.contentpane, bi.getName());
                }
            }
        });


        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;

        add(statusArea, c);
        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx = 2;
        c.gridy = 0;
        add(commands, c);
        c.gridx = 2;
        c.gridy = 1;
        add(bt, c);


    }

    public void init(TransferContainer t) {

        ArrayList<String> retrievePlotDetails = t.rdb.retrievePlotDetails(propertyID);


        statusArea.append("Owner : " + retrievePlotDetails.get(1) + "\n");
        statusArea.append("Located in " + retrievePlotDetails.get(3) + "\n");
        statusArea.append("Happiness : " + retrievePlotDetails.get(7) + "\n");
        statusArea.append("Income : " + retrievePlotDetails.get(8) + "\n");


    }
}
