package talesestateappletv2;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BasePanel extends JPanel {

    private static final float FONT_SIZE = 24f;
    private JButton next = new JButton();
    public TransferContainer tain;

    public BasePanel(String name, TransferContainer tc) {
        setName(name);
        tain = tc;

//        JLabel label = null;
//        try {
//            label = new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Fiyah\\Documents\\Cos-301-Main\\TalesEstateServer\\Images\\background.jpg"))));
//        } catch (IOException ex) {
//            Logger.getLogger(BasePanel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
        setLayout(new BorderLayout());
//        add(label, BorderLayout.CENTER);
    }

    public void addNextActionListener(ActionListener listener) {
        next.addActionListener(listener);
    }
//    @Override
//    public void paint(Graphics g) {
//        super.paintComponent(g); 
//        g.drawImage(tain.background, 0, 0, null); // see javadoc for more info on the parameters            
//    }
}
