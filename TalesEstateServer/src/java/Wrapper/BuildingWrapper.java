package Wrapper;

import QueryHandlers.QueryHandler;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/BuildingWrapper")
public class BuildingWrapper {

    QueryHandler handler = new QueryHandler(0);
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
    public String retrieveAllBuildingsOwnedByCharacter(@PathParam("plotID") int plotID) {

        return converter.ArrToUrl(handler.getBuildingQH().retrieveAllBuildingsOwnedByCharacter(plotID));

    }
    
    @GET
    @Path("checkBuildingPrerequisites/{plotID}/{buildingID}")
    @Produces("text/plain")
    public String checkBuildingPrerequisites(@PathParam("plotID") int plotID, @PathParam("buildingID") int buildingID) {
        return handler.getBuildingQH().checkBuildingPrerequisites(plotID, buildingID);
    }
    
    @GET
    @Path("getBuildingTTB/{buildingID}")
    @Produces("text/plain")
    public String getBuildingTTB(@PathParam("buildingID") int buildingID) {
        return handler.getBuildingQH().getBuildingTTB(buildingID);
    }
}
