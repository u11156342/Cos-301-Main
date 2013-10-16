package Connections;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class RestFullAdapter {

    String serverURL = "216.172.99.153";
    int serverPort = 8080;

    public RestFullAdapter() {    
    }

    public BufferedImage ImageAdapter(int id) {
        try {
            return ImageIO.read(new URL("http://" + serverURL + ":" + serverPort +"/TalesEstateServer/resources/ImageWrapper/getImageByID/" + id));


        } catch (MalformedURLException ex) {
            Logger.getLogger(RestFullAdapter.class
                    .getName()).log(Level.WARNING, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestFullAdapter.class
                    .getName()).log(Level.WARNING, null, ex);
        }

        return null;
    }
}
