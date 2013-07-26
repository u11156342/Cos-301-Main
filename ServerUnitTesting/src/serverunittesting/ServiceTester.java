package serverunittesting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author NightFiyah
 */
public class ServiceTester {

    private URL url;
    private BufferedReader in;
    private String inputLine;
    private String temp = "";

    public ServiceTester() {
        RunTests();
    }

    public final void RunTests() {
        retrieveCompleteBuildingList();
        listBuildingBy();
        retrieveBuildingDetailsById();
        retrieveDuchyList();
        queryPlotPrice();
        retrievePlotsOwnedByCharacter();
        retrievePlotDetails();
        searchPlotBy();
        retrieveMonthlyUpkeep();
        registerEstateCharacter();
        retrieveCharacterID();
        retrieveAllCharacters();
        checkLogin();
        checkHasCharacter();
        retrieveCharactersOwnedByUser();
        getImageByID();
    }

    /**
     * Unit test for restfull webservice retrieveCompleteBuildingList
     */
    public void retrieveCompleteBuildingList() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveCompleteBuildingList");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveCompleteBuildingList failed");
            } else {
                System.out.println("Unit test on retrieveCompleteBuildingList passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice listBuildingBy
     *
     * @param duchy duchy the building is located in
     * @param industry type of industry the building is
     */
    public void listBuildingBy() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/listBuildingBy/Thegnheim/Mining");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on listBuildingBy failed");
            } else {
                System.out.println("Unit test on listBuildingBy passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice retrieveBuildingDetailsById
     *
     * @param buildingID id of the building
     */
    public void retrieveBuildingDetailsById() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveBuildingDetailsById/1");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveBuildingDetailsById failed");
            } else {
                System.out.println("Unit test on retrieveBuildingDetailsById passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice retrieveDuchyList
     *
     * @param buildingID id of the building
     */
    public void retrieveDuchyList() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveDuchyList");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveDuchyList failed");
            } else {
                System.out.println("Unit test on retrieveDuchyList passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice queryPlotPrice
     *
     * @param duchy duchy where the plot is located
     * @param quality the quality of the duchy
     */
    public void queryPlotPrice() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/queryPlotPrice/Thegnheim/Fine");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveDuchyList failed");
            } else {
                System.out.println("Unit test on retrieveDuchyList passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    /*
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
     */

    /**
     * Unit test for restfull webservice retrievePlotsOwnedByCharacter
     *
     * @param characterID Id of the character
     */
    public void retrievePlotsOwnedByCharacter() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrievePlotsOwnedByCharacter/1");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrievePlotsOwnedByCharacter failed");
            } else {
                System.out.println("Unit test on retrievePlotsOwnedByCharacter passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice retrievePlotDetails
     *
     * @param plotID Id of the plot
     */
    public void retrievePlotDetails() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrievePlotDetails/11");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrievePlotDetails failed");
            } else {
                System.out.println("Unit test on retrievePlotDetails passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice searchPlotBy
     *
     * @param characterName characters name
     * @param duchy duchy it is located in
     * @param size the size of the plot
     * @param quality the quality of the plot
     */
    public void searchPlotBy() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/searchPlotBy/QRCharacter/Thegnheim/3/Fine");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on searchPlotBy failed");
            } else {
                System.out.println("Unit test on searchPlotBy passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

//    public String deletePlot(@PathParam("plotID") int plotID) {
//        if (handler.pqh.deletePlot(plotID)) {
//            return "true";
//        } else {
//            return "false";
//        }
//    }
    /**
     * Unit test for restfull webservice retrieveMonthlyUpkeep
     *
     * @param duchy duchy it is located in
     * @param quality the quality of the plot
     */
    public void retrieveMonthlyUpkeep() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveMonthlyUpkeep/Thegnheim/Fine");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveMonthlyUpkeep failed");
            } else {
                System.out.println("Unit test on retrieveMonthlyUpkeep passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice registerEstateCharacter
     *
     * @param characterName character to registers name
     */
    public void registerEstateCharacter() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/registerEstateCharacter/Test123");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on registerEstateCharacter failed");
            } else {
                System.out.println("Unit test on registerEstateCharacter passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice retrieveCharacterID
     *
     * @param userID the users Id from TalesProd
     */
    public void retrieveCharacterID() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveCharacterID/474C675A-EFE9-47B8-9AF5-01CEB4E2B98A");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveCharacterID failed");
            } else {
                System.out.println("Unit test on retrieveCharacterID passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice retrieveAllCharacters
     *
     */
    public void retrieveAllCharacters() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveAllCharacters");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveAllCharacters failed");
            } else {
                System.out.println("Unit test on retrieveAllCharacters passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice checkLogin
     *
     * @param userID the users id
     */
    public void checkLogin() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/checkLogin/1");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on checkLogin failed");
            } else {
                System.out.println("Unit test on checkLogin passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice checkHasCharacter
     *
     * @param userID the users id from TalesProd
     */
    public void checkHasCharacter() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/checkHasCharacter/474C675A-EFE9-47B8-9AF5-01CEB4E2B98A");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on checkHasCharacter failed");
            } else {
                System.out.println("Unit test on checkHasCharacter passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice retrieveCharactersOwnedByUser
     *
     * @param userID the users id from TalesProd
     */
    public void retrieveCharactersOwnedByUser() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/retrieveCharactersOwnedByUser/474C675A-EFE9-47B8-9AF5-01CEB4E2B98A");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on retrieveCharactersOwnedByUser failed");
            } else {
                System.out.println("Unit test on retrieveCharactersOwnedByUser passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Unit test for restfull webservice getImageByID
     */
    public void getImageByID() {
        try {

            url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/getImageByID/0");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine = in.readLine();
            if ("".equals(inputLine)) {
                System.out.println("Unit test on getImageByID failed");
            } else {
                System.out.println("Unit test on getImageByID passed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
