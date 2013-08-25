/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author NightFiyah
 */
@Stateless
@Path("/DuchyWrapper")
public class DuchyWrapper {

    QueryHandler handler = handler = new QueryHandler();
    Converter converter = new Converter();

    @GET
    @Path("retrieveDuchyList")
    @Produces("text/plain")
    public String retrieveDuchyList() throws IOException {
        return converter.ToUrl(handler.getDuchyQH().retrieveDuchyList());
    }
}
