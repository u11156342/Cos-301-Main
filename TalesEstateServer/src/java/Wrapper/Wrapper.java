package Wrapper;


import QueryHandlers.QueryHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * This class hosts the restfull web services that is used to connect to the database
 * @author NightFiyah
 * @version     1.0
 * @since      2013-07-26
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
        return converter.ToUrl(handler.bqh.retrieveCompleteBuildingList());
    }

    @GET
    @Path("listBuildingBy/{duc}/{ind}")
    @Produces("text/plain")
    public String listBuildingBy(@PathParam("duc") String duchy, @PathParam("ind") String industry) {
        return converter.ArrToUrl(handler.bqh.listBuildingBy(duchy, industry));
    }

    @GET
    @Path("retrieveBuildingDetailsById/{id}")
    @Produces("text/plain")
    public String retrieveBuildingDetailsById(@PathParam("id") int buildingID) {
        return converter.ArrToUrl(handler.bqh.retrieveBuildingDetailsById(buildingID));
    }
    ///buildings end
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="duchy">
    //duchy
    @GET
    @Path("retrieveDuchyList")
    @Produces("text/plain")
    public String retrieveDuchyList() throws IOException {
        return converter.ToUrl(handler.dqh.retrieveDuchyList());
    }

    //duchy end
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Plot">
    //Plot
    @GET
    @Path("queryPlotPrice/{duc}/{qual}")
    @Produces("text/plain")
    public String queryPlotPrice(@PathParam("duc") String duchy, @PathParam("qual") String quality) {
        return converter.ArrToUrl(handler.pqh.queryPlotPrice(duchy, quality));
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
        if (handler.pqh.addPlotToCharacter(characterName, duchyName, sizeValue, quality, handler.pqh.convertFromArray(groundArray), handler.pqh.convertFromArray(buildingArray), acresUsed, acreMax, workersUsed, workerMax, happiness, monthlyIncome)) {
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

        if (handler.pqh.modifyPlot(plotId, characterName, duchyName, sizeValue, quality, handler.pqh.convertFromArray(groundArray), handler.pqh.convertFromArray(buildingArray), acresUsed, acreMax, workersUsed, workerMax, happiness, monthlyIncome)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrievePlotsOwnedByCharacter/{characterID}")
    @Produces("text/plain")
    public String retrievePlotsOwnedByCharacter(@PathParam("characterID") int characterID) {
        return converter.ArrToUrl(handler.pqh.retrievePlotsOwnedByCharacter(characterID));
    }

    @GET
    @Path("retrievePlotDetails/{plotID}")
    @Produces("text/plain")
    public String retrievePlotDetails(@PathParam("plotID") int plotID) {
        return converter.ToUrl(handler.pqh.retrievePlotDetails(plotID));
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

        return converter.ArrToUrl(handler.pqh.searchPlotBy(characterName, duchy, size, quality));
    }

    @GET
    @Path("deletePlot/{plotID}")
    @Produces("text/plain")
    public String deletePlot(@PathParam("plotID") int plotID) {
        if (handler.pqh.deletePlot(plotID)) {
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
        return converter.ToUrl(handler.prqh.retrieveMonthlyUpkeep(duchy, quality));
    }

    //price end
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Character">
    @GET
    @Path("registerEstateCharacter/{characterName}")
    @Produces("text/plain")
    public String registerEstateCharacter(@PathParam("characterName") String characterName) {

        characterName = characterName.replace('.', ' ');
        if (handler.cqh.registerEstateCharacter(characterName)) {
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
        return "" + handler.cqh.retrieveCharacterID(userName);
    }

    @GET
    @Path("retrieveAllCharacters")
    @Produces("text/plain")
    public String retrieveAllCharacters() {
        return converter.ArrToUrl(handler.cqh.retrieveAllCharacters());
    }
    //character end
    //</editor-fold>

    @GET
    @Path("checkLogin/{userID}")
    @Produces("text/plain")
    public String checkLogin(@PathParam("userID") String userID) {
        if (handler.uqh.checkLogin(userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("checkHasCharacter/{userID}")
    @Produces("text/plain")
    public String checkHasCharacter(@PathParam("userID") String userID) {
        if (handler.uqh.checkHasCharacter(userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrieveCharactersOwnedByUser/{userID}")
    @Produces("text/plain")
    public String retrieveCharactersOwnedByUser(@PathParam("userID") String userID) {
        return converter.ToUrl(handler.uqh.retrieveCharactersOwnedByUser(userID));
    }

    @GET
    @Path("getImageByID/{id}")
    @Produces("image/gif")
    public Response getImageByID(@PathParam("id") int imageID) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            for (int a = 0; a < handler.Picqh.loadedPictures.length; a++) {
                if (a == imageID) {
                    ImageIO.write(handler.Picqh.loadedPictures[a], "gif", baos);
                }
            }
            byte[] imageData = baos.toByteArray();
            return Response.ok(imageData).build();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }
}
