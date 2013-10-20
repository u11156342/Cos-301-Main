package Connections;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import talesestateappletv2.TransferContainer;

public class RestFullAdapter {

     //String serverURL = "216.172.99.153";
    String serverURL = "localhost";
    int serverPort = 8080;
    ArrayList<BufferedImage> pics = new ArrayList();
    ArrayList donePics = new ArrayList();
    TransferContainer tain;

    public RestFullAdapter(TransferContainer tc) {
        // JOptionPane.showMessageDialog(tc.mainapplet, "inited");
        tain = tc;
    }

    public BufferedImage ImageAdapter(int id) {



        try {

            System.out.println("getting " + id);
            donePics.add(id);
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/TalesEstateServer/resources/ImageWrapper/getImageByID/" + id);
            //  System.out.println("http://" + serverURL + ":" + serverPort + server + "SecurityWrapper/" + "ServerRequest" + "/" + enc);
            //  System.out.println(new String(ciphertext));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String name = in.readLine();
            name=name.replaceAll(" ","%20");
            System.out.println("http://" + "216.172.99.153" + "/estatesystem/pictures/" + name);
            BufferedImage t = ImageIO.read(new URL("http://" + "216.172.99.153" + "/estatesystem/pictures/" + name));
            return t;


        } catch (MalformedURLException ex) {
            Logger.getLogger(RestFullAdapter.class.getName()).log(Level.WARNING, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestFullAdapter.class.getName()).log(Level.WARNING, null, ex);
        }
//        JOptionPane.showMessageDialog(tain.mainapplet,"null ");
        return null;
    }
}
