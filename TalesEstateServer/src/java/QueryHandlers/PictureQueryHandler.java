package QueryHandlers;

import Connection.DatabaseConnection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PictureQueryHandler {
    private DatabaseConnection db = null;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    public ArrayList<String> pictures;
    public BufferedImage[] loadedPictures;
    String folderLocation;

    public PictureQueryHandler(Connection c){
        super();
        
        BufferedReader reader;
        
        try 
        {
            reader = new BufferedReader(new FileReader("ServerConfig.txt"));
            folderLocation = reader.readLine();
            //System.out.println(folderLocation);
        } catch (Exception ex) {
            Logger.getLogger(PictureQueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        con = c;
        pictures = new ArrayList();

        String temp;
        
        try {
            sql = "SELECT PictureImageName FROM Picture";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) 
            {
                pictures.add(rs.getString("PictureImageName"));
            }
        } catch (Exception e) {
            System.out.println("Error in PictureQueryHandler constructor");
            System.out.println(e.getMessage());
        }

        loadedPictures = new BufferedImage[pictures.size()];
        
        for(int a = 0; a < loadedPictures.length; a++)
        {
            try 
            {
                System.out.println("Loading picture " + folderLocation + "\\" + pictures.get(a));
                loadedPictures[a] = ImageIO.read(new File(folderLocation + pictures.get(a)));
            } catch (IOException ex) {
                System.out.println("Error when loading pictures in PictureQueryHandler constructor");
                System.out.println("Error loading " + folderLocation + "\\" + pictures.get(a));
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnection() {
        db.closeConnection();
    }
}
