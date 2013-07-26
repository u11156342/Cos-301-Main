package Interface.BuyInterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class BuyMenuPicturePanel extends JPanel {

    BufferedImage background;
    public int w;
    public int h;

    public BuyMenuPicturePanel(BufferedImage im, int we, int he) {
        background = im;
        w = im.getWidth();
        h = im.getHeight();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawRect(10, 10, 20, 20);
        g.drawImage(background, 10, 10, w, h, null);
    }
}
