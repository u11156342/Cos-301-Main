package Interface.MyProperties;

import Interface.TextManage.MainPlayInterface;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JTextPane;
import talesestateappletv2.TransferContainer;

public class PlayerOwnPanel extends JPanel {
    
    public JTextPane statusArea = new JTextPane();
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
    
    public void init(final TransferContainer tc, final boolean own) {
        
        StringBuilder text = new StringBuilder();
        
        text.append("Located in ").append(duchy).append("<br>");
        text.append("" + '\n');
        text.append("Funds => Platinum : ").append(amount.get(0)).append(" Gold : ").append(amount.get(1)).append(" Silver : ").append(amount.get(2)).append("<br>");
        text.append("Income => Gold : ").append(income).append("<br>");
        text.append("Happiness => ").append(hap).append("<br>");
        text.append("Workers =>").append(wc).append("/").append(wm).append("<br>");
        text.append("Poor acres : ").append(quality.get(0)[1]).append("/").append(quality.get(0)[2]).append("<br>");
        text.append("Fine acres : ").append(quality.get(1)[1]).append("/").append(quality.get(1)[2]).append("<br>");
        text.append("Exquisite acres : ").append(quality.get(2)[1]).append("/").append(quality.get(2)[2]).append("<br>");
        
        statusArea.setContentType("text/html");
        statusArea.setPreferredSize(new Dimension(200, 280));
        statusArea.setText(text.toString());
        
        
        
        play.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                MainPlayInterface card = tain.Cmanager.getMainPlayInterfaceCard();
                card.buildings = buildings;
                card.duchy = duchy;
                card.propertyID = propertyID;
                card.tiles = tiles;
                card.amount = amount;
                card.quality = quality;
                card.IsOwner = own;
                card.init(tain, propertyID);
                
                
                
                tain.cardlayout.show(tain.contentpane, card.getName());
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
