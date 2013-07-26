package Interface.PlayInterface;

import Connections.RestFullAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

public class TileManager {
    Tile[] tiles = new Tile[100];
    RestFullAdapter adapter = new RestFullAdapter();
    public BufferedImage defaultIm;

    public TileManager() throws MalformedURLException, IOException {
        for (int a = 0; a < tiles.length; a++) {
            tiles[a] = new Tile(a, null);
        }
    }
    public BufferedImage get(int i) throws IOException {
        for (int a = 0; a < tiles.length; a++) {

            if (tiles[a].ID == i) {
                if (tiles[a].picture == null) {
                    tiles[a].picture = adapter.ImageAdapter(i);
                }
                return tiles[a].picture;
            }
        }
        return null;
    }
}
