/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import java.util.ArrayList;
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

    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("logBuildingBuilt/{characterID}/{plotID}/{buildingID}")
    @Produces("text/html")
    public String logBuildingBuilt(@PathParam("characterID") int characterID,
            @PathParam("plotID") int plotID, @PathParam("buildingID") int buildingID) {

        handler.getLogQH().logBuildingBuilt(characterID, plotID, buildingID, new Date());
        return "";

    }

    @GET
    @Path("PlotLog/{description}")
    @Produces("text/html")
    public String PlotLog(@PathParam("description") String description) {

        handler.getLogQH().PlotLog(description);
        return "";
    }

    @GET
    @Path("CharacterLog/{description}")
    @Produces("text/html")
    public String CharacterLog(@PathParam("description") String description) {
        handler.getLogQH().CharacterLog(description);
        return "";
    }

    @GET
    @Path("getPlotLog/{num}")
    @Produces("text/html")
    public String getPlotLog(@PathParam("num") int number) {
        ArrayList<String[]> temp = handler.getLogQH().getPlotLog(number);

        StringBuilder html = new StringBuilder();

        for (int i = 0; i < temp.size(); i++) {
            html.append(temp.get(i)[0]).append(temp.get(i)[1]).append(temp.get(i)[2]);
            html.append("<br>");
        }

        return html.toString();
    }

    @GET
    @Path("getCharacterLog/{num}")
    @Produces("text/html")
    public String getCharacterLog(@PathParam("num") int number) {
        ArrayList<String[]> temp = handler.getLogQH().getCharacterLog(number);

        StringBuilder html = new StringBuilder();

        for (int i = 0; i < temp.size(); i++) {
            html.append(temp.get(i)[0]).append(temp.get(i)[1]).append(temp.get(i)[2]);
            html.append("<br>");
        }

        return html.toString();
    }
}
