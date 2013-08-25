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
@Path("/BuildingWrapper")
public class BuildingWrapper {

    QueryHandler handler = handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("retrieveCompleteBuildingList")
    @Produces("text/plain")
    public String retrieveCompleteBuildingList() {
        return converter.ToUrl(handler.getBuildingQH().retrieveCompleteBuildingList());
    }

    @GET
    @Path("listBuildingBy/{duc}/{ind}")
    @Produces("text/plain")
    public String listBuildingBy(@PathParam("duc") String duchy, @PathParam("ind") String industry) {
        return converter.ArrToUrl(handler.getBuildingQH().listBuildingBy(duchy, industry));
    }

    @GET
    @Path("retrieveBuildingDetailsById/{id}")
    @Produces("text/plain")
    public String retrieveBuildingDetailsById(@PathParam("id") int buildingID) {
        return converter.ArrToUrl(handler.getBuildingQH().retrieveBuildingDetailsById(buildingID));
    }

    @GET
    @Path("retrieveAllBuildingsOwnedByCharacter/{characterID}/{plotID}")
    @Produces("text/html")
    public String retrieveAllBuildingsOwnedByCharacter(@PathParam("characterID") int characterID,
            @PathParam("plotID") int plotID) {

        return converter.ToUrl(handler.getBuildingQH().retrieveAllBuildingsOwnedByCharacter(characterID, plotID));

    }
}
