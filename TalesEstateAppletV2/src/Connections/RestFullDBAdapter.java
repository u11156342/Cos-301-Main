package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class RestFullDBAdapter {

    String serverURL = "216.172.99.153";
    //String serverURL = "localhost";
    int serverPort = 8080;
    String server = "/TalesEstateServer/resources/";
    Converter Conv = new Converter();
    SecretKeySpec key;
    Cipher aes;
    BufferedReader reader = null;
    String ServerIP = "";

    //Class used to connect to the server
    public RestFullDBAdapter() {

        System.out.println(ServerIP);
        try {
            aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            String passphrase = "Space, the final frontier. These are the voyages of the Starship Enterprise. Its five-year mission: to explore strange new worlds, to seek out new life and new civilizations, to boldly go where no man has gone before.";
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(passphrase.getBytes());
            key = new SecretKeySpec(digest.digest(), 0, 16, "AES");


        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RestFullDBAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RestFullDBAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String DoServerRequest(String path) {

        String temp = "";

        try {
            Calendar cal = Calendar.getInstance();
            DateFormat Format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String ID = Format.format(cal.getTime());
            //check for inconsistensies in the path
            String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~:[]@!$&()*+,;=/";

            for (int i = 0; i < path.length(); i++) {
                if (valid.contains("" + path.charAt(i))) {
                } else {
                    path = path.replaceAll("" + path.charAt(i), ".");
                }
            }
            Random r = new Random(100000);
            path = r.nextInt() + "+" + ID + "+" + path;

            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = aes.doFinal(path.getBytes());
            String enc = "";
            for (int i = 0; i < ciphertext.length; i++) {
                enc = enc + "`" + ciphertext[i];
            }

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "SecurityWrapper/" + "ServerRequest" + "/" + enc);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            int marker = 0;
            temp = "";
            while ((inputLine = in.readLine()) != null) {
                if (!"null".equals(inputLine)) {
                    if (marker == 0) {
                        temp = inputLine;
                        marker++;
                    } else {
                        temp = temp + "\n" + inputLine;
                    }
                }
            }


            // aes.init(Cipher.DECRYPT_MODE, key);

//            StringTokenizer tokens = new StringTokenizer(inputLine, "`");
//            byte[] bt = new byte[tokens.countTokens()];
//            int c1 = 0;
//            while (tokens.hasMoreTokens()) {
//                String tok = tokens.nextToken();
//
//                bt[c1] = (byte) Integer.parseInt(tok);
//                c1++;
//
//            }
//            temp = new String(aes.doFinal(bt));
            // temp = inputLine;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public boolean registerEstateCharacter(String characterName, String userID) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "registerEstateCharacter" + "/" + characterName + "/" + userID);
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
            userName = userName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "retrieveCharacterID" + "/" + userName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Integer.parseInt(temp);
    }

    public ArrayList<String[]> retrieveAllCharacters() {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "retrieveAllCharacters");
            System.out.println("ALL CHARS " + temp);
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
        System.out.println(" conveter " + inArray);
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

        System.out.println(result.length);
        return result;
    }

    public boolean checkLogin(String userID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "UserWrapper/" + "checkLogin" + "/" + userID);
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
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "UserWrapper/" + "checkHasCharacter" + "/" + userID);
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

            temp = DoServerRequest(serverURL + ":" + serverPort + server + "UserWrapper/" + "retrieveCharactersOwnedByUser/" + userID);
            System.out.println(temp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public ArrayList<String[]> queryPlotPrice(String duchy, String quality) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "queryPlotPrice/" + duchy + "/" + quality);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String> retrieveMonthlyUpkeep(String duchy, String quality) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PriceWrapper/" + "retrieveMonthlyUpkeep/" + duchy + "/" + quality);
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "queryPlotPrice/" + duchy + "/" + quality);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        temp = temp.replaceAll("@", "" + '\n');
        return Conv.FromUrl(temp);
    }

    public boolean addPlotToCharacter(String characterName, String duchyName, String abby, String name, String quality, int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome, int workersUsed, int workerMax) {
        String temp = "";
        try {
            characterName = characterName.replaceAll(" ", ".");
            name = name.replaceAll(" ", ".");
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "addPlotToCharacter" + "/" + characterName + "/" + duchyName + "/" + quality + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax + "/" + abby + "/" + name);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("".equals(temp)) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<String> getCharacterAmounts(String characterName) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "getCharacterAmounts/" + characterName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public boolean modifyAmount(String characterName, int amountPlatinum, int amountGold, int amountSilver) {
        System.out.println(amountPlatinum + " " + amountGold + " " + amountSilver);
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "modifyAmount" + "/" + characterName + "/" + amountPlatinum + "/" + amountGold + "/" + amountSilver);
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
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "retrievePlotsOwnedByCharacter/" + characterID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String> getCurrentAmount(int plotID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "getCurrentAmount/" + plotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public boolean modifyAmount(int inPlotID, int amountPlatinum, int amountGold, int amountSilver) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "modifyAmount" + "/" + inPlotID + "/" + amountPlatinum + "/" + amountGold + "/" + amountSilver);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("".equals(temp)) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<String> retrievePlotDetails(int plotID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "retrievePlotDetails/" + plotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public boolean expandPlot(int plotID, String quality, int[][] groundArray, int[][] buildingArray) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "expandPlot" + "/" + plotID + "/" + quality + "/" + this.convertToArray(groundArray) + "/" + this.convertToArray(buildingArray));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }

    }

    public String getStatus(int propertyId) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "Wrapper/" + "StatusReport/" + propertyId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public String getSuperStatusReport(int propertyId) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "Wrapper/" + "SuperStatusReport/" + propertyId);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public boolean modifyPlot(int plotId, String characterName, String plotAmount, String duchyName, int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome, int workersUsed, int workerMax, double exquisiteUsed, int exquisiteMax, double fineUsed, int fineMax, double poorUsed, int poorMax, double defenceValue) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "modifyPlot" + "/" + plotId + "/" + characterName + "/" + plotAmount + "/" + duchyName + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax + "/" + exquisiteUsed + "/" + exquisiteMax + "/" + fineUsed + "/" + fineMax + "/" + poorUsed + "/" + poorMax + "/" + defenceValue);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String[]> listBuildingBy(String duchy, String industry) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "BuildingWrapper/" + "listBuildingBy/" + duchy + "/" + industry);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String[]> retrieveBuildingDetailsById(int buildingID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "BuildingWrapper/" + "retrieveBuildingDetailsById/" + buildingID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public void logBuildingBuilt(int characterID, int plotiD, int buildingID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "LogWrapper/" + "logBuildingBuilt/" + characterID + "/" + plotiD + "/" + buildingID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String[]> retrieveAllBuildingsOwnedByCharacter(int characterID, int plotid) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "BuildingWrapper/" + "retrieveAllBuildingsOwnedByCharacter/" + characterID + "/" + plotid);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String> retrieveDuchyList() {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "DuchyWrapper/" + "retrieveDuchyList");
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
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "searchPlotBy/" + characterName + "/" + duchy + "/" + size + "/" + quality);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public ArrayList<String[]> retrieveCharacterIDExtra(String characterName) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "retrieveCharacterIDExtra/" + characterName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public String checkBuildingPrerequisites(int plotID, int buildingID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "BuildingWrapper/" + "checkBuildingPrerequisites/" + plotID + "/" + buildingID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public ArrayList<String> getPlotAccess(int plotID, int userID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "getPlotAccess/" + plotID + "/" + userID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.FromUrl(temp);
    }

    public ArrayList<String[]> getAllAccess(int plotID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "getAllAccess/" + plotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public String retrieveCharacterName(int characterID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "retrieveCharacterName/" + characterID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        temp = temp.replace('.', ' ');

        return temp;
    }

    public String addPlotAccess(int plotID, int UserId, boolean deposit, boolean withdraw, boolean buy, boolean place, boolean exspand, boolean status) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "addPlotAccess/" + plotID + "/" + UserId + "/" + deposit + "/" + withdraw + "/" + buy + "/" + place + "/" + exspand + "/" + status);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return temp;
    }

    public boolean removeAccess(int plotId, int UserID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "removeAccess" + "/" + plotId + "/" + UserID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String[]> AllPlotsIHaveAccess(int UserID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "AllPlotsIHaveAccess/" + UserID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public boolean useAcresOnPlot(int plotId, double acreExquisite, double acreFine, double acrePoor) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "useAcresOnPlot" + "/" + plotId + "/" + acreExquisite + "/" + acreFine + "/" + acrePoor);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addEvent(int plotId, String eventName, String eventDescription, int platinumMod, int goldMod, int silverMod, int happinessMod, int incomeMod, int defenceMod) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "EventWrapper/" + "addEvent" + "/" + plotId + "/" + eventName + "/" + eventDescription + "/" + platinumMod + "/" + goldMod + "/" + silverMod + "/" + happinessMod + "/" + incomeMod + "/" + defenceMod);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("false".equals(temp)) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String[]> getEvent(int month, int PlotID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "EventWrapper/" + "getEvent/" + month + "/" + PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return Conv.ArrFromUrl(temp);
    }

    public void DoExspand(int pId, double Upkeep, int workerMax) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "DoExspand/" + pId + "/" + Upkeep + "/" + workerMax);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void LogPlot(int PlotID, String desc, int UserID) {
        String temp = "";
        desc = desc.replace(" ", "*");
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "LogWrapper/" + "PlotLog/" + PlotID + "/" + desc + "/" + UserID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void LogChar(int CharID, String desc) {
        String temp = "";
        desc = desc.replace(" ", "*");
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "LogWrapper/" + "CharacterLog/" + CharID + "/" + desc);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getLogPlot(int PlotID, String num) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "LogWrapper/" + "getPlotLog/" + num + "/" + PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return temp;
    }

    public String getLogChar(int CharID, String num) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "LogWrapper/" + "getCharacterLog/" + num + "/" + CharID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return temp;
    }

    public String GlobalStatus() {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "Wrapper/" + "GlobalStatus");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return temp;
    }

    public String PlaceBuilding(int PlotID, int[][] buildings) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "PlaceBuilding/" + PlotID + "/" + convertToArray(buildings));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return temp;
    }

    public String MarkBuildingAsPlaced(int BuildLogID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "MarkBuildingAsPlaced/" + BuildLogID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return temp;
    }

    public boolean isAdmin(int userID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "isAdmin/" + userID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("true".equals(temp)) {
            return true;
        } else {
            return false;
        }
    }

    public void setDescription(String desc, int PlotID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "setDescription/" + desc + "/" + PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getDescription(int PlotID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "getDescription/" + PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public void setPlotName(int pId, String plotName) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "setName/" + pId + "/" + plotName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void placeWater(int PlotID, int[][] tileStates) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "placeWater/" + PlotID + "/" + convertToArray(tileStates));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void unPlaceBuilding(int PlotID, int[][] gridstates) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "unPlaceBuilding/" + PlotID + "/" + convertToArray(gridstates));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void MarkBuildingsAsUnPlaced(int PlotID, int buildingToGiveBack) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "MarkBuildingsAsUnPlaced/" + buildingToGiveBack + "/" + PlotID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean addRequest(int plotID, String text) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "addRequest/" + plotID + "/" + text);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if ("true".equals(temp)) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String[]> getAllOutstandingRequests() {

        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "getAllOutstandingRequests");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return this.Conv.ArrFromUrl(temp);
    }

    public void approveRequest(int requestID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "approveRequest/" + requestID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

    public void deleteRequest(int requestID) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "deleteRequest/" + requestID);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
