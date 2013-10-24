package Wrapper;

import QueryHandlers.QueryHandler;
import java.io.ByteArrayOutputStream;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/ImageWrapper")
public class ImageWrapper {

    QueryHandler handler = new QueryHandler(1);
    Converter converter = new Converter();

    @GET
    @Path("getImageByID/{id}")
    @Produces("text/plain")
    public String getImageByID(@PathParam("id") int imageID) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int a = 0; a < handler.getPictureQH().loadedPictures.length; a++) {
                if (a == imageID) {
                    return handler.getPictureQH().pictures.get(a);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }
}
