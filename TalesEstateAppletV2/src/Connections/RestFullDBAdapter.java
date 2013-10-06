package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
            String passphrase = "correct horse battery staple";
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(passphrase.getBytes());
            SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
            aes.init(Cipher.ENCRYPT_MODE, key);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RestFullDBAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(RestFullDBAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(RestFullDBAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String DoServerRequest(String path) {

        String temp = "";

        try {



            byte[] ciphertext = aes.doFinal(path.getBytes());
            for (int i = 0; i < ciphertext.length; i++) {
                System.out.println(ciphertext[i]);
            }
            String enc = "";
            for (int i = 0; i < ciphertext.length; i++) {
                enc = enc + "@" + ciphertext[i];
            }


            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "SecurityWrapper/" + "ServerRequest" + "/" + enc);
            System.out.println("http://" + serverURL + ":" + serverPort + server + "SecurityWrapper/" + "ServerRequest" + "/" + enc);
            System.out.println(new String(ciphertext));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                temp = temp+ inputLine;
                
            }
            in.close();
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "UserWrapper/" + "checkHasCharacter" + "/" + userID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "UserWrapper/" + "retrieveCharactersOwnedByUser/" + userID);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "queryPlotPrice/" + duchy + "/" + quality);
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

    public ArrayList<String> retrieveMonthlyUpkeep(String duchy, String quality) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PriceWrapper/" + "retrieveMonthlyUpkeep/" + duchy + "/" + quality);
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

    public boolean addPlotToCharacter(String characterName, String duchyName, String quality, int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome, int workersUsed, int workerMax) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "addPlotToCharacter" + "/" + characterName + "/" + duchyName + "/" + quality + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax);
            System.out.println(url);
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

    public ArrayList<String> getCharacterAmounts(String characterName) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "CharacterWrapper/" + "getCharacterAmounts/" + characterName);
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

    public boolean modifyAmount(String characterName, int amountPlatinum, int amountGold, int amountSilver) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "CharacterWrapper/" + "modifyAmount" + "/" + characterName + "/" + amountPlatinum + "/" + amountGold + "/" + amountSilver);

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

    public ArrayList<String[]> retrievePlotsOwnedByCharacter(int characterID) {
        String temp = "";

        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "retrievePlotsOwnedByCharacter/" + characterID);
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

    public ArrayList<String> getCurrentAmount(int plotID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "getCurrentAmount/" + plotID);
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

    public boolean modifyAmount(int inPlotID, int amountPlatinum, int amountGold, int amountSilver) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "modifyAmount" + "/" + inPlotID + "/" + amountPlatinum + "/" + amountGold + "/" + amountSilver);

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

    public ArrayList<String> retrievePlotDetails(int plotID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "retrievePlotDetails/" + plotID);
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

    public boolean expandPlot(int plotID, String quality, int[][] groundArray) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "expandPlot" + "/" + plotID + "/" + quality + "/" + this.convertToArray(groundArray));

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            inputLine = in.readLine();
            temp = temp + inputLine;

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

    public String getStatus(int propertyId) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "Wrapper/" + "StatusReport/" + propertyId);
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

    public String getSuperStatusReport(int propertyId) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "Wrapper/" + "SuperStatusReport/" + propertyId);
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

    public boolean modifyPlot(int plotId, String characterName, String plotAmount, String duchyName, int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome, int workersUsed, int workerMax, double exquisiteUsed, int exquisiteMax, double fineUsed, int fineMax, double poorUsed, int poorMax, double defenceValue) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "modifyPlot" + "/" + plotId + "/" + characterName + "/" + plotAmount + "/" + duchyName + "/" + sizeValue + "/" + convertToArray(groundArray) + "/" + convertToArray(buildingArray) + "/" + happiness + "/" + monthlyIncome + "/" + workersUsed + "/" + workerMax + "/" + exquisiteUsed + "/" + exquisiteMax + "/" + fineUsed + "/" + fineMax + "/" + poorUsed + "/" + poorMax + "/" + defenceValue);
            System.out.println(url);
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

    public ArrayList<String[]> listBuildingBy(String duchy, String industry) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "BuildingWrapper/" + "listBuildingBy/" + duchy + "/" + industry);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "BuildingWrapper/" + "retrieveBuildingDetailsById/" + buildingID);
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

    public void logBuildingBuilt(int characterID, int plotiD, int buildingID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "LogWrapper/" + "logBuildingBuilt/" + characterID + "/" + plotiD + "/" + buildingID);
            System.out.println("http://" + serverURL + ":" + serverPort + server + "LogWrapper/" + "logBuildingBuilt/" + characterID + "/" + plotiD + "/" + buildingID);
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

    public ArrayList<String[]> retrieveAllBuildingsOwnedByCharacter(int characterID, int plotid) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "BuildingWrapper/" + "retrieveAllBuildingsOwnedByCharacter/" + characterID + "/" + plotid);
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

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "DuchyWrapper/" + "retrieveDuchyList");
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
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "searchPlotBy/" + characterName + "/" + duchy + "/" + size + "/" + quality);
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

    public ArrayList<String[]> retrieveCharacterIDExtra(String characterName) {
        String temp = "";
        try {
            characterName = characterName.replace(' ', '.');
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "CharacterWrapper/" + "retrieveCharacterIDExtra/" + characterName);
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

    public String checkBuildingPrerequisites(int plotID, int buildingID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "BuildingWrapper/" + "checkBuildingPrerequisites/" + plotID + "/" + buildingID);
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

    public ArrayList<String> getPlotAccess(int plotID, int userID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "getPlotAccess/" + plotID + "/" + userID);
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

    public ArrayList<String[]> getAllAccess(int plotID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "getAllAccess/" + plotID);
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

    public String retrieveCharacterName(int characterID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "CharacterWrapper/" + "retrieveCharacterName/" + characterID);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                temp = temp + "\n" + inputLine;
            }
            in.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        temp = temp.replace('.', ' ');

        return temp;
    }

    public String addPlotAccess(int plotID, int UserId, boolean deposit, boolean withdraw, boolean buy, boolean place, boolean exspand, boolean status) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "addPlotAccess/" + plotID + "/" + UserId + "/" + deposit + "/" + withdraw + "/" + buy + "/" + place + "/" + exspand + "/" + status);
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

    public boolean removeAccess(int plotId, int UserID) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "removeAccess" + "/" + plotId + "/" + UserID);
            System.out.println(url);
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

    public ArrayList<String[]> AllPlotsIHaveAccess(int UserID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "AllPlotsIHaveAccess/" + UserID);
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

    public boolean useAcresOnPlot(int plotId, double acreExquisite, double acreFine, double acrePoor) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "PlotWrapper/" + "useAcresOnPlot" + "/" + plotId + "/" + acreExquisite + "/" + acreFine + "/" + acrePoor);
            System.out.println(url);
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

    public boolean addEvent(int plotId, String eventName, String eventDescription, int platinumMod, int goldMod, int silverMod, int happinessMod, int incomeMod) {
        String temp = "";
        try {

            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "EventWrapper/" + "addEvent" + "/" + plotId + "/" + eventName + "/" + eventDescription + "/" + platinumMod + "/" + goldMod + "/" + silverMod + "/" + happinessMod + "/" + incomeMod);
            System.out.println(url);
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

    public ArrayList<String[]> getEvent(int month, int PlotID) {
        String temp = "";
        try {
            URL url = new URL("http://" + serverURL + ":" + serverPort + server + "EventWrapper/" + "getEvent/" + month + "/" + PlotID);
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
}
