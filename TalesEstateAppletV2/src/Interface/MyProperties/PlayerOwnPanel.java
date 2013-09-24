package Interface.MyProperties;

import Interface.TextManage.MainPlayInterface;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import talesestateappletv2.TransferContainer;

public class PlayerOwnPanel extends JPanel {

    public JTextArea statusArea = new JTextArea();
    JButton exspand = new JButton("Exspand plot");
    JButton play = new JButton("play");
    public int propertyID;
    public String duchy;
    ArrayList<String[]> quality;
    public int size;
    public double income;
    public int[][] tiles;
    public int[][] buildings;
    ArrayList<String> amount;
    public int hap;
    public int wc;
    public int wm;
    TransferContainer tain;

    PlayerOwnPanel(TransferContainer tc) {
        tain = tc;
        exspand = new JButton(new ImageIcon(tc.ad.ImageAdapter(16)));
        exspand.setCursor(new Cursor(Cursor.HAND_CURSOR));
        play = new JButton(new ImageIcon(tc.ad.ImageAdapter(17)));
        play.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        statusArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusArea.setColumns(4);
        statusArea.setEditable(false);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 2;

        add(statusArea, c);
        c.gridheight = 1;
        c.gridwidth = 1;

        c.gridx = 2;
        c.gridy = 0;
        c.gridx = 2;
        c.gridy = 1;
        add(play, c);
    }

    public void init(final TransferContainer tc) {

        statusArea.append("Located in " + duchy);
        statusArea.append("" + '\n');
        statusArea.append("Funds => Platinum : " + amount.get(0) + " Gold : " + amount.get(1) + " Silver : " + amount.get(2));
        statusArea.append("" + '\n');
        statusArea.append("Income => Gold : " + income);
        statusArea.append("" + '\n');
        statusArea.append("Happiness => " + hap);
        statusArea.append("" + '\n');
        statusArea.append("Workers =>" + wc + "/" + wm);
        statusArea.append("" + '\n');
        statusArea.append("Poor acres : " + quality.get(0)[1] + "/" + quality.get(0)[2]);
        statusArea.append("" + '\n');
        statusArea.append("Fine acres : " + quality.get(1)[1] + "/" + quality.get(1)[2]);
        statusArea.append("" + '\n');
        statusArea.append("Exquisite acres : " + quality.get(2)[1] + "/" + quality.get(2)[2]);
        statusArea.append("" + '\n');

        play.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                tain.mp = new MainPlayInterface("MPlay");
                tain.mainapplet.add(tain.mp, tain.mp.getName());
                
                tc.mp.buildings = buildings;
                tc.mp.duchy = duchy;
                tc.mp.propertyID = propertyID;
                tc.mp.tiles = tiles;
                tc.mp.amount = amount;
                tc.mp.quality = quality;
                tc.mp.init(tain, propertyID);


                tain.cardlayout.show(tain.contentpane, "MPlay");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}
