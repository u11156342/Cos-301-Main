
package Data.Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RestFullDBAdapter {

    String serverURL = "localhost";
    int serverPort = 8080;
    Converter Conv = new Converter();

    public ArrayList<String> retrieveCompleteBuildingList() {

        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveCompleteBuildingList");
            System.out.println("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveCompleteBuildingList");
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
            System.out.println("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/listBuildingBy/" + duchy + "/" + industry);
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/listBuildingBy/" + duchy + "/" + industry);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveBuildingDetailsById/" + buildingID);
            System.out.println("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveBuildingDetailsById/" + buildingID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveDuchyList");
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/queryPlotPrice/" + duchy + "/" + quality);
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

    public boolean addPlotToCharacter(String characterName, String duchyName, int sizeValue, String quality, int[][] groundArray, int[][] buildingArray, double acresUsed, double acreMax, int workersUsed, int workerMax, int happiness, double monthlyIncome) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/addPlotToCharacter" + "/" + characterName + "/" + duchyName + "/" + sizeValue + "/" + quality + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + acresUsed + "/" + acreMax + "/" + workersUsed + "/" + workerMax + "/" + happiness + "/" + monthlyIncome);
            System.out.println("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/addPlotToCharacter" + "/" + characterName + "/" + duchyName + "/" + sizeValue + "/" + quality + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + acresUsed + "/" + acreMax + "/" + workersUsed + "/" + workerMax + "/" + happiness + "/" + monthlyIncome);
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

    public boolean modifyPlot(int plotId, String characterName, String duchyName, int sizeValue, String quality, int[][] groundArray, int[][] buildingArray, double acresUsed, double acreMax, int workersUsed, int workerMax, int happiness, double monthlyIncome) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/modifyPlot" + "/" + plotId + "/" + characterName + "/" + duchyName + "/" + sizeValue + "/" + quality + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + acresUsed + "/" + acreMax + "/" + workersUsed + "/" + workerMax + "/" + happiness + "/" + monthlyIncome);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrievePlotsOwnedByCharacter/" + characterID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrievePlotDetails/" + plotID);
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
            if (0==(size)) {
                size = -1;
            }
            if ("".equals(quality)) {
                quality = quality + "-";
            }
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/searchPlotBy/" + characterName + "/" + duchy + "/" + size + "/" + quality);
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
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/deletePlot" + "/" + plotID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveMonthlyUpkeep/" + duchy + "/" + quality);
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
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/registerEstateCharacter" + "/" + characterName);
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

    public int retrieveCharacterID(String userID) {
        String temp = "";
        try {
            String tt = "";
            for (int a = 0; a < userID.length(); a++) {
                if (userID.charAt(a) != ' ') {
                    tt = tt + userID.charAt(a);
                } else {
                    tt = tt + "%20";
                }
            }
            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveCharacterID" + "/" + tt);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveAllCharacters");
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveCharactersOwnedByUser" + "/" + userID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/checkHasCharacter" + "/" + userID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + "/ContentServer/resources/Wrapper/retrieveCharactersOwnedByUser/" + userID);
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
