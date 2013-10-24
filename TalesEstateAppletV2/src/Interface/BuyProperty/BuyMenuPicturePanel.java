package Interface.BuyProperty;

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
        g.drawImage(background,0, 0, w, h, null);
    }
}
