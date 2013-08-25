/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import java.util.Date;
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
@Path("/LogWrapper")
public class LogWrapper {

    QueryHandler handler = handler = new QueryHandler();
    Converter converter = new Converter();

    @GET
    @Path("logBuildingBuilt/{characterID}/{plotID}/{buildingID}")
    @Produces("text/html")
    public String logBuildingBuilt(@PathParam("characterID") int characterID,
            @PathParam("plotID") int plotID, @PathParam("buildingID") int buildingID) {

        handler.getLogQH().logBuildingBuilt(characterID, plotID, buildingID, new Date());
        return "";

    }
}
