package Connections;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class RestFullAdapter {

    String serverURL = "localhost";
    int serverPort = 8080;

    public RestFullAdapter() {

        /*
         BufferedReader br;
         InputStream istream = getClass().getResourceAsStream("resources/config.txt");

         if (istream != null) {
         try {
         String line;

         byte[] b = new byte[istream.available()];
         istream.read(b);

         String text = new String(b);

         StringTokenizer tokens = new StringTokenizer(text, ":" + '\r');

         String prob = tokens.nextToken();
         if ("server-url".equals(prob)) {
         serverURL = tokens.nextToken();
         }
         String prob2 = tokens.nextToken();
         if ("server-port".equals(prob2)) {
         serverPort = Integer.parseInt(tokens.nextToken());
         }
         } catch (IOException ex) {
         Logger.getLogger(RestFullAdapter.class.getName()).log(Level.SEVERE, null, ex);
         }

         } else {
         System.out.println("null");
         }
         * */
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
