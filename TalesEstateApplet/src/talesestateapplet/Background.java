/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package talesestateapplet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author NightFiyah
 */
public class Background extends JPanel {

    BufferedImage im;

    public Background(BufferedImage ims) {
        im = ims;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(im, 0, 0, 1024, 800, null);
    }
}
