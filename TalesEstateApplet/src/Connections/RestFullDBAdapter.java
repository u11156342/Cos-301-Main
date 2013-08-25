package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RestFullDBAdapter {

    String serverURL = "localhost";
    int serverPort = 8080;
    String server = "/TalesEstateServer/resources/";
    Converter Conv = new Converter();

    public ArrayList<String> retrieveCompleteBuildingList() {

        String temp = "";
        try {
            URL url = new URL("http://" + serverURL+ ":" + serverPort + server +"BuildingWrapper/" + "retrieveCompleteBuildingList");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public ArrayList<String[]> listBuildingBy(String duchy, String industry) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL +":" + serverPort + server +"BuildingWrapper/" + "listBuildingBy/" + duchy + "/" + industry);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String[]> retrieveBuildingDetailsById(int buildingID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"BuildingWrapper/"+ "retrieveBuildingDetailsById/" + buildingID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String> retrieveDuchyList() {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"DuchyWrapper/"+ "retrieveDuchyList");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);

    }

    public ArrayList<String[]> queryPlotPrice(String duchy, String quality) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PlotWrapper/"+ "queryPlotPrice/" + duchy + "/" + quality);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }
  
    public boolean addPlotToCharacter(String characterName, String duchyName, String quality, int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome, int workersUsed, int workerMax) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
 
            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PlotWrapper/"+ "addPlotToCharacter" + "/" + characterName + "/" + duchyName + "/" + quality + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) +  "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            inputLine = in.readLine();
            temp = temp + inputLine;

            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("".equals(temp)) {
            return false;
        } else {
            return true;
        }

    }
    public boolean modifyPlot(int plotId, String characterName,int plotAmount, String duchyName, int sizeValue,int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome,int workersUsed, int workerMax, double exquisiteUsed,int exquisiteMax,double fineUsed,int fineMax,double poorUsed,int poorMax) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PlotWrapper/"+ "modifyPlot" + "/" + plotId + "/" + characterName + "/"+plotAmount+"/" + duchyName + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax + "/" + exquisiteUsed + "/" + exquisiteMax+"/"+fineUsed+"/"+fineMax+"/"+poorUsed+"/"+poorMax);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String[]> retrievePlotsOwnedByCharacter(int characterID) {
        String temp = "";

        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PlotWrapper/"+ "retrievePlotsOwnedByCharacter/" + characterID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String[]> retrieveAllPlots() {
        String temp = "";

        try {

            URL url = new URL("http://"  +serverURL + ":" + serverPort + server +"PlotWrapper/"+ "retrieveAllPlots");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String> retrievePlotDetails(int plotID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PlotWrapper/"+ "retrievePlotDetails/" + plotID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;

            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public ArrayList<String[]> searchPlotBy(String characterName, String duchy, int size, String quality) {
        String temp = "";
        try {

            characterName = characterName.replace(' ', '.');
            if ("".equals(characterName)) {
                characterName = characterName + "-";
            }
            if ("".equals(duchy)) {
                duchy = duchy + "-";
            }
            if (0 == (size)) {
                size = -1;
            }
            if ("".equals(quality)) {
                quality = quality + "-";
            }
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/"+"searchPlotBy/" + characterName + "/" + duchy + "/" + size + "/" + quality);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public boolean deletePlot(int plotID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PlotWrapper/"+ "deletePlot" + "/" + plotID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> retrieveMonthlyUpkeep(String duchy, String quality) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"PriceWrapper/"+ "retrieveMonthlyUpkeep/" + duchy + "/" + quality);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public boolean registerEstateCharacter(String characterName) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"CharacterWrapper/"+ "registerEstateCharacter" + "/" + characterName);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public int retrieveCharacterID(String userName) {
        String temp = "";
        try {
            String tt = "";

            userName = userName.replace(' ', '.');

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"CharacterWrapper/"+ "retrieveCharacterID" + "/" + userName);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            inputLine = in.readLine();
            temp = temp + inputLine;

            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return Integer.parseInt(temp);
    }

    public ArrayList<String[]> retrieveAllCharacters() {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"CharacterWrapper/"+ "retrieveAllCharacters");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public String convertToArray(int[][] inArray) {
        String result = "";

        for (int a = 0; a < inArray.length; a++) {
            for (int b = 0; b < inArray[a].length; b++) {
                if (b == inArray[a].length - 1) {
                    result += inArray[a][b];
                } else {
                    result += inArray[a][b] + ",";
                }
            }
            result += "_";
        }

        return result;
    }

    public int[][] convertFromArray(String inArray) {
        System.out.println(inArray);
        int[][] result;
        StringTokenizer str, stc;
        int rows, columns;

        str = new StringTokenizer(inArray, ";");
        rows = str.countTokens();
        stc = new StringTokenizer(str.nextToken(), ",");
        columns = stc.countTokens();
        result = new int[rows][columns];

        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < columns; b++) {
                result[a][b] = Integer.parseInt(stc.nextToken());
            }
            if (a < rows - 1) {
                stc = new StringTokenizer(str.nextToken(), ",");
            }
        }

        return result;
    }

    public boolean checkLogin(String userID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"UserWrapper/"+ "checkLogin" + "/" + userID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkHasCharacter(String userID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"UserWrapper/"+ "checkHasCharacter" + "/" + userID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> retrieveCharactersOwnedByUser(String userID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"UserWrapper/"+ "retrieveCharactersOwnedByUser/" + userID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public String getStatus(int propertyId) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"Wrapper/"+ "StatusReport/" + propertyId);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public void logBuildingBuilt(int characterID, int plotiD,int buildingID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"LogWrapper/"+ "logBuildingBuilt/" + characterID + "/" +plotiD +"/"+buildingID);
            System.out.println("http://" + serverURL + ":" + serverPort + server +"LogWrapper/"+ "logBuildingBuilt/" + characterID + "/" + plotiD+"/"+buildingID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String> retrieveAllBuildingsOwnedByCharacter(int characterID, int plotid) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server +"BuildingWrapper/"+ "retrieveAllBuildingsOwnedByCharacter/" + characterID+"/"+plotid);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }
}
