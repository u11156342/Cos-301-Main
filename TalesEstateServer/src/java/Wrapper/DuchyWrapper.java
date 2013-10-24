package Wrapper;

import QueryHandlers.QueryHandler;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
@Path("/DuchyWrapper")
public class DuchyWrapper {

    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("retrieveDuchyList")
    @Produces("text/plain")
    public String retrieveDuchyList() throws IOException {
        return converter.ToUrl(handler.getDuchyQH().retrieveDuchyList());
    }
}
