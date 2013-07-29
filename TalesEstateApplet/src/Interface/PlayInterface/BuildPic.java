
package Interface.PlayInterface;

import Connections.RestFullAdapter;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class BuildPic extends JPanel {

    BufferedImage builpic = null;
    RestFullAdapter adapter = new RestFullAdapter();

    public void set(int a) {
        builpic = adapter.ImageAdapter(5);
    }

    @Override
    public void paint(final Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(builpic, 0, 0, 100, 100, this);
    }
}
