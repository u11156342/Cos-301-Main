package QueryHandlers;


import Connection.DatabaseConnection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
    private ResultSetMetaData rsmd = null;
    private String sql = "";
    public ArrayList<String[]> pictures;
    public BufferedImage[] LoadedPictures;
    String folderlocation;

    public PictureQueryHandler(Connection c){
        super();
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("ServerConfig.txt"));
            folderlocation=reader.readLine();
            System.out.println(folderlocation);
        } catch (Exception ex) {
            Logger.getLogger(PictureQueryHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        con = c;
        pictures = new ArrayList();

        String[] temp;
        try {
            sql = "SELECT * FROM Upictures";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                temp = new String[6];
                temp[0] = rs.getString("PictureId");
                temp[1] = rs.getString("DisplayHeight");
                temp[2] = rs.getString("DisplayWidth");
                temp[3] = rs.getString("xOffset");
                temp[4] = rs.getString("yOffset");
                temp[5] = rs.getString("StoredImage");
                System.out.println(temp[5]);
                pictures.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoadedPictures = new BufferedImage[pictures.size()];
        
        for(int a=0;a<LoadedPictures.length;a++)
        {
            try {
                System.out.println("loading "+folderlocation+""+pictures.get(a)[5]);
                LoadedPictures[a]=ImageIO.read(new File(folderlocation+pictures.get(a)[5]));
            } catch (IOException ex) {
                System.out.println("cant load "+folderlocation+pictures.get(a)[5]);
            }
        }

    }

    public void closeConnection() {
        db.closeConnection();
    }
}
