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
@Path("/PlotWrapper")
public class PlotWrapper {

    QueryHandler handler = handler = new QueryHandler();
    Converter converter = new Converter();

    @GET
    @Path("queryPlotPrice/{duc}/{qual}")
    @Produces("text/plain")
    public String queryPlotPrice(@PathParam("duc") String duchy, @PathParam("qual") String quality) {
        return converter.ArrToUrl(handler.getPlotQH().queryPlotPrice(duchy, quality));
    }

    @GET
    @Path("addPlotToCharacter/{characterName}/{duchyName}/{quality}/{sizeValue}/{groundArray}/{buildingArray}/{happiness}/{monthlyIncome}/{workersUsed}/{workerMax}")
    @Produces("text/plain")
    public String addPlotToCharacter(@PathParam("characterName") String characterName, @PathParam("duchyName") String duchyName, @PathParam("quality") String quality,
            @PathParam("sizeValue") int sizeValue, @PathParam("groundArray") String groundArray, @PathParam("buildingArray") String buildingArray, @PathParam("happiness") int happiness,
            @PathParam("monthlyIncome") double monthlyIncome, @PathParam("workersUsed") int workersUsed, @PathParam("workerMax") int workerMax) {

        characterName = characterName.replace('.', ' ');
        groundArray = groundArray.replace('_', ';');
        buildingArray = buildingArray.replace('_', ';');
        System.out.println("groundArray " + groundArray);
        System.out.println("buildingArray " + buildingArray);
        if (handler.getPlotQH().addPlotToCharacter(characterName, duchyName, quality, sizeValue, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(buildingArray), happiness, monthlyIncome, workersUsed, workerMax)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("modifyPlot/{plotId}/{characterName}/{plotAmount}/{duchyName}/{sizeValue}/{groundArray}/{buildingArray}/{happiness}/{monthlyIncome}{workersUsed}/{workerMax}/{exquisiteUsed}/{exquisiteMax}/{fineUsed}/{fineMax}/{poorUsed}/{poorMax}")
    @Produces("text/plain")
    public String modifyPlot(@PathParam("plotId") int plotId, @PathParam("characterName") String characterName, @PathParam("plotAmount") String plotAmount,
            @PathParam("duchyName") String duchyName, @PathParam("sizeValue") int sizeValue, @PathParam("groundArray") String groundArray,
            @PathParam("buildingArray") String buildingArray, @PathParam("happiness") int happiness, @PathParam("monthlyIncome") double monthlyIncome,
            @PathParam("workersUsed") int workersUsed, @PathParam("workerMax") int workerMax, @PathParam("exquisiteUsed") double exquisiteUsed, @PathParam("exquisiteMax") int exquisiteMax,
            @PathParam("fineUsed") double fineUsed, @PathParam("fineMax") int fineMax, @PathParam("poorUsed") double poorUsed, @PathParam("poorMax") int poorMax) {

        characterName = characterName.replace('.', ' ');
        groundArray = groundArray.replace('_', ';');
        buildingArray = buildingArray.replace('_', ';');

        if (handler.getPlotQH().modifyPlot(plotId, characterName, plotAmount, duchyName, sizeValue, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(buildingArray), happiness, monthlyIncome, workersUsed, workerMax, exquisiteUsed, exquisiteMax, fineUsed, fineMax, poorUsed, poorMax)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrievePlotsOwnedByCharacter/{characterID}")
    @Produces("text/plain")
    public String retrievePlotsOwnedByCharacter(@PathParam("characterID") int characterID) {
        return converter.ArrToUrl(handler.getPlotQH().retrievePlotsOwnedByCharacter(characterID));
    }

    @GET
    @Path("retrievePlotDetails/{plotID}")
    @Produces("text/plain")
    public String retrievePlotDetails(@PathParam("plotID") int plotID) {
        return converter.ToUrl(handler.getPlotQH().retrievePlotDetails(plotID));
    }

    @GET
    @Path("searchPlotBy/{characterName}/{duchy}/{size}/{quality}")
    @Produces("text/plain")
    public String searchPlotBy(@PathParam("characterName") String characterName, @PathParam("duchy") String duchy, @PathParam("size") int size, @PathParam("quality") String quality) {
        characterName = characterName.replace('.', ' ');
        if ("-".equals(characterName)) {
            characterName = "";
        }
        if ("-".equals(duchy)) {
            duchy = "";
        }
        if ("-".equals(quality)) {
            quality = "";
        }

        return converter.ArrToUrl(handler.getPlotQH().searchPlotBy(characterName, duchy, size, quality));
    }

    @GET
    @Path("deletePlot/{plotID}")
    @Produces("text/plain")
    public String deletePlot(@PathParam("plotID") int plotID) {
        if (handler.getPlotQH().deletePlot(plotID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrieveAllPlots")
    @Produces("text/plain")
    public String retrieveAllPlots() {
        return converter.ArrToUrl(handler.getPlotQH().retrieveAllPlots());
    }

    @GET
    @Path("getCurrentAmount/{plotID}")
    @Produces("text/html")
    public String getCurrentAmount(@PathParam("plotID") int plotID) {
        return converter.ToUrl(handler.getPlotQH().getCurrentAmount(plotID));
    }

    @GET
    @Path("modifyAmount/{inPlotID}/{amountPlatinum}/{amountGold}/{amountSilver}")
    @Produces("text/html")
    public String modifyAmount(@PathParam("inPlotID") int inPlotID, @PathParam("amountPlatinum") int amountPlatinum, @PathParam("amountGold") int amountGold, @PathParam("amountSilver") int amountSilver) {
        if (handler.getPlotQH().modifyAmount(inPlotID, amountPlatinum, amountGold, amountSilver)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("depositAmount/{inPlotID}/{amountPlatinum}/{amountGold}/{amountSilver}")
    @Produces("text/html")
    public String depositAmount(@PathParam("inPlotID") int inPlotID, @PathParam("amountPlatinum") int amountPlatinum, @PathParam("amountGold") int amountGold, @PathParam("amountSilver") int amountSilver) {
        if (handler.getPlotQH().depositAmount(inPlotID, amountPlatinum, amountGold, amountSilver)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("withdrawAmount/{inPlotID}/{amountPlatinum}/{amountGold}/{amountSilver}")
    @Produces("text/html")
    public String withdrawAmount(@PathParam("inPlotID") int inPlotID, @PathParam("amountPlatinum") int amountPlatinum, @PathParam("amountGold") int amountGold, @PathParam("amountSilver") int amountSilver) {
        if (handler.getPlotQH().withdrawAmount(inPlotID, amountPlatinum, amountGold, amountSilver)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("expandPlot/{plotID}/{quality}/{groundArray}")
    @Produces("text/html")
    public String expandPlot(@PathParam("plotID") int plotID, @PathParam("quality") String quality, @PathParam("quality") String groundArray) {
        if (handler.getPlotQH().expandPlot(plotID, quality, handler.getPlotQH().convertFromArray(groundArray))) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("getAcreQualityAmounts/{plotID}")
    @Produces("text/html")
    public String getAcreQualityAmounts(@PathParam("plotID") int plotID) {
        return converter.ToUrl(handler.getPlotQH().getAcreQualityAmounts(plotID));
    }

    @GET
    @Path("useAcresOnPlot/{plotID}/{acreExquisite}/{acreFine}/{acrePoor}")
    @Produces("text/html")
    public String useAcresOnPlot(@PathParam("plotID") int plotID, @PathParam("acreExquisite") String acreExquisite, @PathParam("acreFine") String acreFine, @PathParam("acrePoor") String acrePoor) {
        if (handler.getPlotQH().useAcresOnPlot(plotID, Double.parseDouble(acreExquisite), Double.parseDouble(acreFine), Double.parseDouble(acrePoor))) {
            return "true";
        } else {
            return "false";
        }
    }
}
