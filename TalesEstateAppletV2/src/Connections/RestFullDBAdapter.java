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

        return Conv.FromUrl(temp);    }

   
}
