/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import java.io.ByteArrayOutputStream;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author NightFiyah
 */
@Stateless
@Path("/ImageWrapper")
public class ImageWrapper {

    QueryHandler handler = handler = new QueryHandler(1);
    Converter converter = new Converter();

    @GET
    @Path("getImageByID/{id}")
    @Produces("image/gif")
    public Response getImageByID(@PathParam("id") int imageID) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int a = 0; a < handler.getPictureQH().loadedPictures.length; a++) {
                if (a == imageID) {
                    ImageIO.write(handler.getPictureQH().loadedPictures[a], "gif", baos);
                }
            }
            byte[] imageData = baos.toByteArray();
            return Response.ok(imageData).build();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }
}
