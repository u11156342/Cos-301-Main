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

    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("queryPlotPrice/{duc}/{qual}")
    @Produces("text/plain")
    public String queryPlotPrice(@PathParam("duc") String duchy, @PathParam("qual") String quality) {
        return converter.ArrToUrl(handler.getPlotQH().queryPlotPrice(duchy, quality));
    }

    @Path("addPlotToCharacter/{characterName}/{duchyName}/{quality}/{sizeValue}/{groundArray}/{buildingArray}/{happiness}/{monthlyIncome}/{workersUsed}/{workerMax}/{abby}/{name}")
    @Produces("text/plain")
    public String addPlotToCharacter(@PathParam("characterName") String characterName, @PathParam("duchyName") String duchyName, @PathParam("quality") String quality,
            @PathParam("sizeValue") int sizeValue, @PathParam("groundArray") String groundArray, @PathParam("buildingArray") String buildingArray, @PathParam("happiness") int happiness,
            @PathParam("monthlyIncome") double monthlyIncome, @PathParam("workersUsed") int workersUsed, @PathParam("workerMax") int workerMax, @PathParam("abby") String abby, @PathParam("name") String name) {
        characterName = characterName.replaceAll("\\.", " ");
        //  name=name.replaceAll("\\."," ");
        System.out.println("YES " + characterName);
        groundArray = groundArray.replace('_', ';');
        buildingArray = buildingArray.replace('_', ';');
        System.out.println("groundArray " + groundArray);
        System.out.println("buildingArray " + buildingArray);
        System.out.println(quality);
        if (handler.getPlotQH().addPlotToCharacter(characterName, duchyName, quality, sizeValue, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(buildingArray), happiness, monthlyIncome, workersUsed, workerMax, abby, name)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("modifyPlot/{plotId}/{characterName}/{plotAmount}/{duchyName}/{sizeValue}/{groundArray}/{buildingArray}/{happiness}/{monthlyIncome}/{workersUsed}/{workerMax}/{exquisiteUsed}/{exquisiteMax}/{fineUsed}/{fineMax}/{poorUsed}/{poorMax}/{defenseValue}")
    @Produces("text/plain")
    public String modifyPlot(@PathParam("plotId") int plotId, @PathParam("characterName") String characterName, @PathParam("plotAmount") String plotAmount,
            @PathParam("duchyName") String duchyName, @PathParam("sizeValue") int sizeValue, @PathParam("groundArray") String groundArray,
            @PathParam("buildingArray") String buildingArray, @PathParam("happiness") int happiness, @PathParam("monthlyIncome") double monthlyIncome,
            @PathParam("workersUsed") int workersUsed, @PathParam("workerMax") int workerMax, @PathParam("exquisiteUsed") double exquisiteUsed, @PathParam("exquisiteMax") int exquisiteMax,
            @PathParam("fineUsed") double fineUsed, @PathParam("fineMax") int fineMax, @PathParam("poorUsed") double poorUsed, @PathParam("poorMax") int poorMax, @PathParam("defenseValue") double defenseValue,
            @PathParam("estateName") String estateName, @PathParam("countyDesc") String countyDesc) {

        characterName = characterName.replace('.', ' ');
        groundArray = groundArray.replace('_', ';');
        buildingArray = buildingArray.replace('_', ';');

        if (handler.getPlotQH().modifyPlot(plotId, characterName, plotAmount, duchyName, sizeValue, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(buildingArray), happiness, monthlyIncome, workersUsed, workerMax, exquisiteUsed, exquisiteMax, fineUsed, fineMax, poorUsed, poorMax, defenseValue, estateName, countyDesc)) {
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
    @Path("expandPlot/{plotID}/{quality}/{groundArray}/{barray}")
    @Produces("text/html")
    public String expandPlot(@PathParam("plotID") int plotID, @PathParam("quality") String quality, @PathParam("groundArray") String groundArray, @PathParam("barray") String barray) {
        System.out.println(plotID);
        groundArray = groundArray.replace('_', ';');
        barray = barray.replace('_', ';');
        if (handler.getPlotQH().expandPlot(plotID, quality, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(barray))) {
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

    @GET
    @Path("addPlotAccess/{plotID}/{userID}/{deposit}/{withdraw}/{buy}/{place}/{expand}/{status}")
    @Produces("text/html")
    public String addPlotAccess(@PathParam("plotID") int plotID, @PathParam("userID") int userID, @PathParam("deposit") boolean deposit, @PathParam("withdraw") boolean withdraw, @PathParam("buy") boolean buy, @PathParam("place") boolean place, @PathParam("expand") boolean expand, @PathParam("status") boolean status) {
        if (handler.getPlotQH().addPlotAccess(plotID, userID, deposit, withdraw, buy, place, expand, status)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("modifyPlotAccess/{plotID}/{userID}/{deposit}/{withdraw}/{buy}/{place}/{expand}/{status}")
    @Produces("text/html")
    public String modifyPlotAccess(@PathParam("plotID") int plotID, @PathParam("userID") int userID, @PathParam("deposit") boolean deposit, @PathParam("withdraw") boolean withdraw, @PathParam("buy") boolean buy, @PathParam("place") boolean place, @PathParam("expand") boolean expand, @PathParam("status") boolean status) {
        if (handler.getPlotQH().addPlotAccess(plotID, userID, deposit, withdraw, buy, place, expand, status)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("getPlotAccess/{plotID}/{userID}")
    @Produces("text/html")
    public String getPlotAccess(@PathParam("plotID") int plotID, @PathParam("userID") int userID) {
        return converter.ToUrl(handler.getPlotQH().getPlotAccess(plotID, userID));
    }

    @GET
    @Path("getAllAccess/{plotID}")
    @Produces("text/html")
    public String getAllAccess(@PathParam("plotID") int plotID) {
        return converter.ArrToUrl(handler.getPlotQH().getAllAccess(plotID));
    }

    @GET
    @Path("removeAccess/{plotID}/{userID}")
    @Produces("text/html")
    public String RemoveAccess(@PathParam("plotID") int plotID, @PathParam("userID") int userID) {
        if (handler.getPlotQH().removeAccess(plotID, userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("AllPlotsIHaveAccess/{userID}")
    @Produces("text/html")
    public String RemoveAccess(@PathParam("userID") int userID) {
        return converter.ArrToUrl(handler.getPlotQH().AllPlotsIHaveAccess(userID));
    }

    @GET
    @Path("DoExspand/{pId}/{Upkeep}/{workerMax}")
    @Produces("text/html")
    public String DoExspand(@PathParam("pId") int pId, @PathParam("Upkeep") String Upkeep, @PathParam("workerMax") int workerMax) {
        System.out.println("CALLLED");
        handler.getPlotQH().DoExspand(pId, Double.parseDouble(Upkeep), workerMax);
        return "true";
    }

    @GET
    @Path("PlaceBuilding/{PlotID}/{Buildings}")
    @Produces("text/html")
    public String PlaceBuilding(@PathParam("PlotID") int PlotID, @PathParam("Buildings") String Buildings) {
        Buildings = Buildings.replace('_', ';');
        try {
            handler.getPlotQH().PlaceBuilding(PlotID, handler.getPlotQH().convertFromArray(Buildings));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "false";
        }
        return "true";
    }

    @GET
    @Path("MarkBuildingAsPlaced/{BuildLogID}")
    @Produces("text/html")
    public String MarkBuildingAsPlaced(@PathParam("BuildLogID") int BuildLogID) {

        try {
            handler.getPlotQH().MarkBuildingAsPlaced(BuildLogID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "false";
        }
        return "true";
    }

    @GET
    @Path("setDescription/{desc}/{PlotID}")
    @Produces("text/html")
    public String setDescription(@PathParam("desc") String desc, @PathParam("PlotID") int PlotID) {

        try {
            handler.getPlotQH().setDescription(desc, PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

    @GET
    @Path("getDescription/{PlotID}")
    @Produces("text/html")
    public String getDescription(@PathParam("PlotID") int PlotID) {

        try {

            String t = handler.getPlotQH().getDescription(PlotID);
            return t;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    @GET
    @Path("getHappinessEffect/{happiness}")
    @Produces("text/html")
    public String getHappinessEffect(@PathParam("happiness") int happiness) {
        try {
            return converter.ToUrl(handler.getPlotQH().getHappinessEffect(happiness));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    @GET
    @Path("setName/{pId}/{name}")
    @Produces("text/html")
    public String setName(@PathParam("pId") int pId, @PathParam("name") String name) {
        try {
            handler.getPlotQH().setName(pId, name);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }

    @GET
    @Path("placeWater/{PlotID}/{tileStates}")
    @Produces("text/plain")
    public String placeWater(@PathParam("PlotID") int PlotID, @PathParam("tileStates") String tileStates) {
        tileStates = tileStates.replace('_', ';');
        try {
            handler.getPlotQH().placeWater(PlotID, handler.getPlotQH().convertFromArray(tileStates));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }

    @GET
    @Path("unPlaceBuilding/{PlotID}/{gridstates}")
    @Produces("text/plain")
    public String unPlaceBuilding(@PathParam("PlotID") int PlotID, @PathParam("gridstates") String gridstates) {
        gridstates = gridstates.replace('_', ';');
        try {
            handler.getPlotQH().unPlaceBuilding(PlotID, handler.getPlotQH().convertFromArray(gridstates));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }

    @GET
    @Path("MarkBuildingsAsUnPlaced/{buildingToGiveBack}/{PlotID}")
    @Produces("text/plain")
    public String MarkBuildingsAsUnPlaced(@PathParam("buildingToGiveBack") int buildingToGiveBack, @PathParam("PlotID") int PlotID) {
        try {
            handler.getPlotQH().MarkBuildingsAsUnPlaced(buildingToGiveBack, PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }

    @GET
    @Path("addRequest/{plotID}/{text}")
    @Produces("text/plain")
    public String addRequest(@PathParam("plotID") int plotID, @PathParam("text") String text) {
        try {
            if (handler.getPlotQH().addRequest(plotID, text)) {
                return "true";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "false";
    }

    @GET
    @Path("getAllOutstandingRequests")
    @Produces("text/plain")
    public String getAllOutstandingRequests() {
        try {
            return converter.ArrToUrl(handler.getPlotQH().getAllOutstandingRequests());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }

    @GET
    @Path("approveRequest/{requestID}")
    @Produces("text/plain")
    public String approveRequest(@PathParam("requestID") int requestID) {
        try {
            handler.getPlotQH().approveRequest(requestID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }

    @GET
    @Path("deleteRequest/{requestID}")
    @Produces("text/plain")
    public String deleteRequest(@PathParam("requestID") int requestID) {
        try {
            handler.getPlotQH().deleteRequest(requestID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        return "";
    }
}
