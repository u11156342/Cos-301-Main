/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package talesestateappletv2;

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class CookieReader {

    String User = System.getProperty("user.dir");
    String FireFox = "AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\wqy4xwl0.default\\cookies.sqlite";
    String Ie = "AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\cookie:user@www.teana.co";
    String Chrome = "AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cookies";
    String Opera = "AppData\\Roaming\\Opera\\Opera\\cookies4.dat";
    public String userID = "";

    public CookieReader() {


        String temp = "";
        int counter = 0;
        for (int i = 0; i < User.length(); i++) {

            if (counter < 3) {
                temp = temp + User.charAt(i);
            }

            if (User.charAt(i) == '\\') {
                counter++;
            }
        }
        // System.out.println(temp);
        TryRead(temp + FireFox, 1);
        TryRead(temp + Chrome, 2);
        TryRead(temp + Opera, 3);

        if ("".equals(userID)) {
            System.out.println("NO COOKIE FOUND");
        }

    }

    public void TryRead(String path, int num) {

        userID = "";

        BufferedReader brFireFox = null;

        try {

            String sCurrentLine;

            brFireFox = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = brFireFox.readLine()) != null) {

                if (num == 1) {
                    if (sCurrentLine.contains("UserSettings")) {

                        int where = sCurrentLine.indexOf("UserID");
                        if (where != -1) {
                            String newS = sCurrentLine;
                            newS = newS.substring(where, where + 43);
                            StringTokenizer tokens = new StringTokenizer(newS, "=");
                            tokens.nextToken();
                            userID = tokens.nextToken();
                        }
                    }
                }
                if (num == 2) {
                    if (sCurrentLine.contains("UserSettings")) {

                        String newS = sCurrentLine;
                        int pos = newS.indexOf("UserID");
                        newS = newS.substring(pos + 7, pos + 43);

                        userID = newS;
                    }
                }
                if (num == 3) {
                    //opera
                    if (sCurrentLine.contains("UserSettings")) {
                        //System.out.println(sCurrentLine);
                        String newS = sCurrentLine;

                        int pos = newS.indexOf("UserID");
                        newS = newS.substring(pos, pos + 43);
                        //int pos2 = newS.indexOf("www.teana.co.za");

                        // System.out.println(newS);
                        //  newS = newS.substring(0, pos2);

                        // System.out.println(newS);

                        StringTokenizer tokens = new StringTokenizer(newS, "=");
                        tokens.nextToken();
                        userID = tokens.nextToken();


                    }
                }
            }



        } catch (Exception e) {
            System.out.println(path);
            System.out.println(e.getMessage());
        } finally {
            try {
                if (brFireFox != null) {
                    brFireFox.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(path + " " + userID);
    }
}
