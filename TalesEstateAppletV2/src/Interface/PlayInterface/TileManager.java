package Interface.PlayInterface;

import Connections.RestFullAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import talesestateappletv2.TransferContainer;

public class TileManager {
    Tile[] tiles = new Tile[200];
    public BufferedImage defaultIm;
    TransferContainer t;

    public TileManager(TransferContainer tc) throws MalformedURLException, IOException {
        t=tc;
        for (int a = 0; a < tiles.length; a++) {
            tiles[a] = new Tile(a, null);
        }
    }
    public BufferedImage get(int i) throws IOException {
        for (int a = 0; a < tiles.length; a++) {

            if (tiles[a].ID == i) {
                if (tiles[a].picture == null) {
                    tiles[a].picture = t.ad.ImageAdapter(i);
                }
                return tiles[a].picture;
            }
        }
        return null;
    }
}
