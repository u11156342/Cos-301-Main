/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package makeitsimple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static makeitsimple.NewClass.start;

/**
 *
 * @author User
 */
public class makesql {
     public static void main(String[] args) throws FileNotFoundException, IOException {start();};
    public static void start() throws FileNotFoundException, IOException{
    
    String sCurrentLine = "";
 BufferedReader br = null;
 
     br = new BufferedReader(new FileReader("newlist.txt"));
   
     
      File file = new File("sqllist.txt");
 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
     
     
     
     while ((sCurrentLine = br.readLine()) != null) {
     String picname = "";
     String pool = "INSERT INTO [TalesEstate].[dbo].[Picture]\n" +
"           ([PictureDisplayHeight]\n" +
"           ,[PictureDisplayWidth]\n" +
"           ,[PictureXOffset]\n" +
"           ,[PictureYOffset]\n" +
"           ,[PictureImageName])\n" +
"     VALUES\n" +
"           (0\n" +
"           ,0\n" +
"           ,0\n" +
"           ,0\n" +
"           ,"+"'"+sCurrentLine+"'"+")\n" +
"GO";
     
     bw.write(pool);
     bw.newLine();
     }
    bw.close();}
    
}
