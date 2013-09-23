/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author NightFiyah
 */
@Stateless
@Path("/PriceWrapper")
public class PriceWrapper {

    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("retrieveMonthlyUpkeep/{duchy}/{quality}")
    @Produces("text/plain")
    public String retrieveMonthlyUpkeep(@PathParam("duchy") String duchy, @PathParam("quality") String quality) {
        return converter.ToUrl(handler.getPriceQH().retrieveMonthlyUpkeep(duchy, quality));
    }
}
