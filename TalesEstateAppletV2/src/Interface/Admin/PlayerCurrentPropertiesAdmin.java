package Interface.Admin;

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
    String[] coms = {"Status","Add event", "View Property"};
    JComboBox commands = new JComboBox(coms);
    JButton bt = new JButton("next");
    String owner;
    public int propertyID;
    public String duchy;
    public int quality;
    public int size;
    int[][] tiles;
    int[][] buildings;

    PlayerCurrentPropertiesAdmin(int w, PlayerPropertiesAdmin aThis,final TransferContainer t) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setColumns(4);
        statusArea.setEditable(false);
        statusArea.setPreferredSize(new Dimension(150,50));
        
        //init();
        
        bt.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = commands.getSelectedIndex();
                
                if(selectedIndex==0)
                {
                    DetailedStatus ds=new DetailedStatus("stats",propertyID,t);
                    t.mainapplet.add(ds,ds.getName());
                    t.cardlayout.show(t.contentpane,"stats");
                }
                else if(selectedIndex==1)
                {
                    AddEvent ev=new AddEvent("event",t);
                    t.mainapplet.add(ev,ev.getName());
                    t.cardlayout.show(t.contentpane,"event");
                }
            }
        });
        
                
        c.gridx = 0;
        c.gridy=0;
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
        
        
        statusArea.append("Owner : "+retrievePlotDetails.get(1)+"\n");
        statusArea.append("Located in " + retrievePlotDetails.get(3)+"\n");
        statusArea.append("Happiness : " + retrievePlotDetails.get(7)+"\n");
        statusArea.append("Income : " + retrievePlotDetails.get(8)+"\n");


    }
}
