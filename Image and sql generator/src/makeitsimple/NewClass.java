/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package makeitsimple;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class NewClass { 
     public static void main(String[] args) throws FileNotFoundException, IOException {start();};
    public static void start() throws FileNotFoundException, IOException{
   
 String sCurrentLine = "";
 BufferedReader br = null;
 
     br = new BufferedReader(new FileReader("newlist.txt"));
   sCurrentLine = "";
        int h = 0;
        while ((sCurrentLine = br.readLine()) != null) {

            BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);
            //Graphics g = image.getGraphics();
           // g.drawString("temp image", 10, 20);
            try {
                ImageIO.write(image, "png", new File(sCurrentLine));
            } catch (IOException e) {
                e.printStackTrace();
            }




                        }}
}
