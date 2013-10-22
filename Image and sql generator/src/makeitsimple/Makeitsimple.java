/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package makeitsimple;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Makeitsimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here 
        BufferedReader br = null;
        
        br = new BufferedReader(new FileReader("building list.txt"));
 String sCurrentLine = "";
 File file = new File("newlist.txt");
 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write(content);
		
 
 
			while ((sCurrentLine = br.readLine()) != null)
                        {
                            
                            System.out.println(sCurrentLine);
                            sCurrentLine = sCurrentLine.replaceAll("\\/", " " );
                            sCurrentLine = sCurrentLine.replaceAll("\"", " " );
                            sCurrentLine = sCurrentLine.replaceAll("\t", " ")+".png";
                            bw.write(sCurrentLine);
                            bw.newLine();
                            
 
                                  
                        }
                     //   
   
    br = new BufferedReader(new FileReader("newlist.txt"));
   sCurrentLine = "";
        int h = 0;bw.close();/*/
        while ((sCurrentLine = br.readLine()) != null) {

            BufferedImage image = new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);
            //Graphics g = image.getGraphics();
           // g.drawString("temp image", 10, 20);
            try {
                ImageIO.write(image, "png", new File(sCurrentLine));
            } catch (IOException e) {
                e.printStackTrace();
            }




                        }/*/
    
    	
    }
        
    }

