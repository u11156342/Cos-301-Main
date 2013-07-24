/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageServer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Stateless
@Path("/Image")
public class ImageHost {

    public BufferedImage defaultIm;
    public BufferedImage[] tiles;

    public ImageHost() {
        super();
        tiles = new BufferedImage[100];              
            
        String folderlocation;
        folderlocation = "E:\\301\\EstateSystemDBServer\\src\\java\\Images\\";
            
        try {

            
            //tile layout

            //0 Dirt
            tiles[0] = ImageIO.read(new File(folderlocation+"Dirt.gif"));
            //1 semi_fertile
            tiles[1] = ImageIO.read(new File(folderlocation+"semi_fertile.gif"));
            //2 fertile
            tiles[2] = ImageIO.read(new File(folderlocation+"fertile.gif"));
            //3 water
            tiles[3] = ImageIO.read(new File(folderlocation+"water.gif"));
            //4 reserved for future ground tiles down till 9
            //5
            //6
            //7
            //8
            //9
            //10 Arnhelm wold map
            tiles[10] = ImageIO.read(new File(folderlocation+"Arnhelm.jpg"));

            //rest buildings
            //11
            tiles[11] = ImageIO.read(new File(folderlocation+"Village 5-a.png"));
            // 12                        
            tiles[12] = ImageIO.read(new File(folderlocation+"Town 5-c.png"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces("image/gif")
    public Response getImageByID(@PathParam("id") int imageID) throws IOException {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();


        for (int a = 0; a < tiles.length; a++) {
            if (a == imageID) {
                ImageIO.write(tiles[a], "gif", baos);
            }
        }



        byte[] imageData = baos.toByteArray();
        return Response.ok(imageData).build();

    }
}
