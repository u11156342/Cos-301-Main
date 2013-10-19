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
    public String logBuildingBuilt(@PathParam("plotID") int plotID, @PathParam("buildingID") int buildingID) {

        handler.getLogQH().logBuildingBuilt(plotID, buildingID, new Date());
        return "";

    }

    @GET
    @Path("PlotLog/{PlotID}/{description}/{Userid}")
    @Produces("text/html")
    public String PlotLog(@PathParam("PlotID") int PlotID, @PathParam("description") String description) {

        handler.getLogQH().PlotLog(PlotID, description);
        return "";
    }

    @GET
    @Path("CharacterLog/{CharID}/{description}")
    @Produces("text/html")
    public String CharacterLog(@PathParam("CharID") int CharID, @PathParam("description") String description) {
        handler.getLogQH().CharacterLog(CharID, description);
        return "";
    }

    @GET
    @Path("getPlotLog/{num}/{plot}")
    @Produces("text/html")
    public String getPlotLog(@PathParam("num") int number, @PathParam("plot") int plot) {
        ArrayList<String[]> temp = handler.getLogQH().getPlotLog(number, plot);

        StringBuilder html = new StringBuilder();

        html.append("<h1>Estate log</h1>");
        
        for (int i = 0; i < temp.size(); i++) {
            html.append("<table>");
            html.append("<tr><td>").append(temp.get(i)[2]).append("</td><td>")
                    .append(temp.get(i)[3].replaceAll("\\*", " ")).append(
                    "</td></tr>");
            html.append("</table>");
            html.append("<hr/>");
        }
        
        return html.toString();
    }

    @GET
    @Path("getCharacterLog/{num}/{chars}")
    @Produces("text/html")
    public String getCharacterLog(@PathParam("num") int number, @PathParam("chars") int chars) {
        ArrayList<String[]> temp = handler.getLogQH().getCharacterLog(number, chars);

        StringBuilder html = new StringBuilder();

        html.append("<h1 style=\"text-align: center;\">Character gold log</h1>");
        
        for (int i = 0; i < temp.size(); i++) {
            html.append("<table style=\"width: 90%;\">");
            html.append("<tr style=\"font-size: 12px;\"><td style=\"width: 50%;\">")
                    .append(temp.get(i)[1]).append("</td><td style=\"width: 50%;\">")
                    .append(temp.get(i)[2].replaceAll("\\*", " ")).append("</td></tr>");
            html.append("</table>");
            html.append("<hr/>");
        }

        return html.toString();
    }
}
