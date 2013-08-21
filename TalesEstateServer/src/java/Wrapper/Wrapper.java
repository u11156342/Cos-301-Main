package Wrapper;

import QueryHandlers.QueryHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * This class hosts the restfull web services that is used to connect to the
 * database
 *
 * @author NightFiyah
 * @version 1.0
 * @since 2013-07-26
 */
@Stateless
@Path("/Wrapper")
public class Wrapper {

    QueryHandler handler = handler = new QueryHandler();
    Converter converter = new Converter();

    public Wrapper() {
    }

    //<editor-fold defaultstate="collapsed" desc="buildings">
    //buildings
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
    ///buildings end
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="duchy">
    //duchy
    @GET
    @Path("retrieveDuchyList")
    @Produces("text/plain")
    public String retrieveDuchyList() throws IOException {
        return converter.ToUrl(handler.getDuchyQH().retrieveDuchyList());
    }

    //duchy end
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Plot">
    //Plot
    @GET
    @Path("queryPlotPrice/{duc}/{qual}")
    @Produces("text/plain")
    public String queryPlotPrice(@PathParam("duc") String duchy, @PathParam("qual") String quality) {
        return converter.ArrToUrl(handler.getPlotQH().queryPlotPrice(duchy, quality));
    }

    @GET
    @Path("addPlotToCharacter/{characterName}/{duchyName}/{sizeValue}/{quality}/{groundArray}/{buildingArray}/{acresUsed}/{acreMax}/{workersUsed}/{workerMax}/{happiness}/{monthlyIncome}")
    @Produces("text/plain")
    public String addPlotToCharacter(@PathParam("characterName") String characterName, @PathParam("duchyName") String duchyName, @PathParam("sizeValue") int sizeValue,
            @PathParam("quality") String quality, @PathParam("groundArray") String groundArray, @PathParam("buildingArray") String buildingArray, @PathParam("acresUsed") double acresUsed,
            @PathParam("acreMax") double acreMax, @PathParam("workersUsed") int workersUsed, @PathParam("workerMax") int workerMax, @PathParam("happiness") int happiness, @PathParam("monthlyIncome") double monthlyIncome) {

        characterName = characterName.replace('.', ' ');
        groundArray = groundArray.replace('_', ';');
        buildingArray = buildingArray.replace('_', ';');
        System.out.println("groundArray " + groundArray);
        System.out.println("buildingArray " + buildingArray);
        if (handler.getPlotQH().addPlotToCharacter(characterName, duchyName, sizeValue, quality, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(buildingArray), acresUsed, acreMax, workersUsed, workerMax, happiness, monthlyIncome)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("modifyPlot/{plotId}/{characterName}/{duchyName}/{sizeValue}/{quality}/{groundArray}/{buildingArray}/{acresUsed}/{acreMax}/{workersUsed}/{workerMax}/{happiness}/{monthlyIncome}")
    @Produces("text/plain")
    public String modifyPlot(@PathParam("plotId") int plotId, @PathParam("characterName") String characterName, @PathParam("duchyName") String duchyName, @PathParam("sizeValue") int sizeValue,
            @PathParam("quality") String quality, @PathParam("groundArray") String groundArray, @PathParam("buildingArray") String buildingArray, @PathParam("acresUsed") double acresUsed,
            @PathParam("acreMax") double acreMax, @PathParam("workersUsed") int workersUsed, @PathParam("workerMax") int workerMax, @PathParam("happiness") int happiness, @PathParam("monthlyIncome") double monthlyIncome) {

        characterName = characterName.replace('.', ' ');
        groundArray = groundArray.replace('_', ';');
        buildingArray = buildingArray.replace('_', ';');

        if (handler.getPlotQH().modifyPlot(plotId, characterName, duchyName, sizeValue, quality, handler.getPlotQH().convertFromArray(groundArray), handler.getPlotQH().convertFromArray(buildingArray), acresUsed, acreMax, workersUsed, workerMax, happiness, monthlyIncome)) {
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

    //plot end
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Price">
    //price
    @GET
    @Path("retrieveMonthlyUpkeep/{duchy}/{quality}")
    @Produces("text/plain")
    public String retrieveMonthlyUpkeep(@PathParam("duchy") String duchy, @PathParam("quality") String quality) {
        return converter.ToUrl(handler.getPriceQH().retrieveMonthlyUpkeep(duchy, quality));
    }

    //price end
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Character">
    @GET
    @Path("registerEstateCharacter/{characterName}")
    @Produces("text/plain")
    public String registerEstateCharacter(@PathParam("characterName") String characterName) {

        characterName = characterName.replace('.', ' ');
        if (handler.getCharacterQH().registerEstateCharacter(characterName)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrieveCharacterID/{userID}")
    @Produces("text/plain")
    public String retrieveCharacterID(@PathParam("userID") String userName) {
        userName = userName.replace('.', ' ');
        return "" + handler.getCharacterQH().retrieveCharacterID(userName);
    }

    @GET
    @Path("retrieveAllCharacters")
    @Produces("text/plain")
    public String retrieveAllCharacters() {
        return converter.ArrToUrl(handler.getCharacterQH().retrieveAllCharacters());
    }
    //character end
    //</editor-fold>

    @GET
    @Path("checkLogin/{userID}")
    @Produces("text/plain")
    public String checkLogin(@PathParam("userID") String userID) {
        if (handler.getUserQH().checkLogin(userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("checkHasCharacter/{userID}")
    @Produces("text/plain")
    public String checkHasCharacter(@PathParam("userID") String userID) {
        if (handler.getUserQH().checkHasCharacter(userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrieveCharactersOwnedByUser/{userID}")
    @Produces("text/plain")
    public String retrieveCharactersOwnedByUser(@PathParam("userID") String userID) {
        return converter.ToUrl(handler.getUserQH().retrieveCharactersOwnedByUser(userID));
    }

    @GET
    @Path("retrieveAllPlots")
    @Produces("text/plain")
    public String retrieveAllPlots() {
        return converter.ArrToUrl(handler.getPlotQH().retrieveAllPlots());
    }

    @GET
    @Path("getImageByID/{id}")
    @Produces("image/gif")
    public Response getImageByID(@PathParam("id") int imageID) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int a = 0; a < handler.getPictureQH().loadedPictures.length; a++) {
                if (a == imageID) {
                    ImageIO.write(handler.getPictureQH().loadedPictures[a], "gif", baos);
                }
            }
            byte[] imageData = baos.toByteArray();
            return Response.ok(imageData).build();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    // this one is hardcoded atm,need to complete it still
    @GET
    @Path("StatusReport/{PropertyId}")
    @Produces("text/html")
    public String StatusReport(@PathParam("PropertyId") int PropertyId) {

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");

        html.append("<body>");

        html.append("<h1> <font color=\"blue\">Estate Details</font></h1>");

        html.append("<table>");
        html.append("	<tr>");
        html.append("		<td>Player Name</td><td> Rean Lubbe</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Character Name </td><td>Aihire Thule</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Estate Number </td><td>SAK0003</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Duchy </td><td> Sarkland</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>County</td><td> Nuidottir</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Acres</td><td> 1</td>");
        html.append("	</tr>");
        html.append("</table>");

        html.append("<br>");

        html.append("<h1> <font color=\"blue\">Happiness, Workers and Events</font></h1>");

        html.append("<table border=\"1\">");
        html.append("	<tr>");
        html.append("		<td>Happiness</td><td>  3</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Workers Currently Employed </td><td> 20</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Maximum workers </td><td> 40</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Funds in Estate </td><td> 24</td>");
        html.append("	</tr>");

        html.append("</table>");
        html.append("</br>");

        html.append("</p>");
        html.append("This month you withdrew money from your estate as indicated in your income sheet.  In addition, you ");

        html.append("had a boom month as the demand for Silk rose sharply due to the first invitations going out for the");

        html.append("wedding of the century between Duke Rotheimâ€™s granddaughter and Duke Svaersteinâ€™s son.  This has ");

        html.append("resulted in a 50% increase in profits");

        html.append("Please note that that his increase is valid for this month only.");
        html.append("</p>");

        html.append("<br>");

        html.append("<h1> <font color=\"blue\">Estate Monthly Income</font></h1>");
        html.append("Your Income Table for this month is:");

        html.append("<table border=\"1\">");
        html.append("	<tr>");
        html.append("		<td>1-Jun-13  </td><td>Withdrawal -15.5</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>30-Jun-13  </td><td>SAK0003-0001 -1.5</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>30-Jun-13  </td><td>SAK0003-0001-01 10.5</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>30-Jun-13  </td><td>SAK0003-0001-02 15</td>");
        html.append("	</tr>");
        html.append("</table>");


        html.append("<h1> <font color=\"blue\">Estate Purchases</font></h1>");

        html.append("Your current estate purchases are:");

        html.append("<table border=\"1\">");
        html.append("<th>What Purchased</th>");
        html.append("<th>Real Date</th>");
        html.append("<th>Crowns</th>");
        html.append("<th>Purchased Number</th>");
        html.append("<th>Acre Req</th>");
        html.append("<th>Workers</th>");
        html.append("<th>Hapiness</th>");

        html.append("	<tr>");
        html.append("		<td>Poor Land</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Silkworks</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Spider </td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Silkworks</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");


        html.append("</table>");
        html.append("</body>");


        html.append("</html>");

        return html.toString();


    }

    @GET
    @Path("logBuildingBuilt/{characterID}/{plotID}/{buildingID}")
    @Produces("text/html")
    public String logBuildingBuilt(@PathParam("characterID") int characterID,
            @PathParam("plotID") int plotID, @PathParam("buildingID") int buildingID) {

        handler.getLogQH().logBuildingBuilt(characterID, plotID, buildingID, new Date());
        return "";

    }

    @GET
    @Path("retrieveAllBuildingsOwnedByCharacter/{characterID}/{plotID}")
    @Produces("text/html")
    public String retrieveAllBuildingsOwnedByCharacter(@PathParam("characterID") int characterID,
            @PathParam("plotID") int plotID) {

        return converter.ToUrl( handler.getBuildingQH().retrieveAllBuildingsOwnedByCharacter(characterID, plotID));

    }
}
