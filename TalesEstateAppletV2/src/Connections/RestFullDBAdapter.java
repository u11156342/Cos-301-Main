package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RestFullDBAdapter {

    String serverURL = "localhost";
    int serverPort = 8080;
    String server = "/TalesEstateServer/resources/";
    Converter Conv = new Converter();
    SecretKeySpec key;
    Cipher aes;

    public RestFullDBAdapter() {

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

            path = ID + "+" + path;

            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = aes.doFinal(path.getBytes());
            String enc = "";
            for (int i = 0; i < ciphertext.length; i++) {
                enc = enc + "`" + ciphertext[i];
            }


            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "SecurityWrapper/" + "ServerRequest" + "/" + enc);
            //  System.out.println("http://" + serverURL + ":" + serverPort + server + "SecurityWrapper/" + "ServerRequest" + "/" + enc);
            //  System.out.println(new String(ciphertext));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = in.readLine();

            aes.init(Cipher.DECRYPT_MODE, key);

            StringTokenizer tokens = new StringTokenizer(inputLine, "`");
            byte[] bt = new byte[tokens.countTokens()];
            int c1 = 0;
            while (tokens.hasMoreTokens()) {
                String tok = tokens.nextToken();

                bt[c1] = (byte) Integer.parseInt(tok);
                c1++;

            }
            temp = new String(aes.doFinal(bt));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return temp;
    }

    public boolean registerEstateCharacter(String characterName) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "CharacterWrapper/" + "registerEstateCharacter" + "/" + characterName);
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

    public boolean addPlotToCharacter(String characterName, String duchyName, String quality, int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome, int workersUsed, int workerMax) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "addPlotToCharacter" + "/" + characterName + "/" + duchyName + "/" + quality + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax);
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

    public boolean expandPlot(int plotID, String quality, int[][] groundArray,int[][] buildingArray) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "expandPlot" + "/" + plotID + "/" + quality + "/" + this.convertToArray(groundArray)+"/"+this.convertToArray(buildingArray));
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

    public boolean addEvent(int plotId, String eventName, String eventDescription, int platinumMod, int goldMod, int silverMod, int happinessMod, int incomeMod) {
        String temp = "";
        try {
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "EventWrapper/" + "addEvent" + "/" + plotId + "/" + eventName + "/" + eventDescription + "/" + platinumMod + "/" + goldMod + "/" + silverMod + "/" + happinessMod + "/" + incomeMod);
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
            temp = DoServerRequest(serverURL + ":" + serverPort + server + "PlotWrapper/" + "DoExspand/" + pId + "/" + Upkeep+"/"+workerMax);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
