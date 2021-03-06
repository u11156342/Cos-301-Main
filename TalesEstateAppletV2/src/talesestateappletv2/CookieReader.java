package talesestateappletv2;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CookieReader {

    String User = System.getProperty("user.name");
    // String FireFox = "AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\wqy4xwl0.default\\cookies.sqlite";.
    String FireFox = "\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\";
    String Ie = "\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files\\cookie:user@www.teana.co";
    String Chrome = "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cookies";
    String Opera = "\\AppData\\Roaming\\Opera\\Opera\\cookies4.dat";
    public ArrayList<String> userIDs = new ArrayList();
    public String userID = "";
    TransferContainer tain;

    public CookieReader(TransferContainer tc) {
        tain = tc;

        String temp = "";
        int counter = 0;
        TryRead("C:\\Users\\" + User + temp + Ie, 0);
        TryRead("C:\\Users\\" + User + temp + FireFox, 1);
        TryRead("C:\\Users\\" + User + temp + Chrome, 2);
        TryRead("C:\\Users\\" + User + temp + Opera, 3);

        if (userIDs.isEmpty()) {
            System.out.println("NO COOKIE FOUND");
        }

    }

    public void TryRead(String path, int num) {

        userID = "";
        BufferedReader brFireFox = null;

        try {

            String sCurrentLine;
            if (num == 0) {
                System.out.println(path);
                brFireFox = new BufferedReader(new FileReader(path));
                while ((sCurrentLine = brFireFox.readLine()) != null) {
                    if (sCurrentLine.contains("UserSettings")) {

                        int where = sCurrentLine.indexOf("UserID");
                        if (where != -1) {
                            String newS = sCurrentLine;
                            newS = newS.substring(where, where + 43);
                            StringTokenizer tokens = new StringTokenizer(newS, "=");
                            tokens.nextToken();
                            userID = tokens.nextToken();
                            userIDs.add(userID);
                        }
                    }
                }
            }
            if (num == 1) {
                File f = new File(path);
                File[] listFiles = f.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    path = listFiles[i] + "\\cookies.sqlite";
                    System.out.println(path);
                    try {
                        brFireFox = new BufferedReader(new FileReader(path));
                        while ((sCurrentLine = brFireFox.readLine()) != null) {
                            if (sCurrentLine.contains("UserSettings")) {

                                int where = sCurrentLine.indexOf("UserID");
                                if (where != -1) {
                                    String newS = sCurrentLine;
                                    newS = newS.substring(where, where + 43);
                                    StringTokenizer tokens = new StringTokenizer(newS, "=");
                                    tokens.nextToken();
                                    userID = tokens.nextToken();
                                    userIDs.add(userID);
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }



            }
            if (num == 2) {
                System.out.println(path);
                brFireFox = new BufferedReader(new FileReader(path));
                while ((sCurrentLine = brFireFox.readLine()) != null) {
                    if (sCurrentLine.contains("UserSettings")) {

                        String newS = sCurrentLine;
                        int pos = newS.indexOf("UserID");
                        newS = newS.substring(pos + 7, pos + 43);

                        userID = newS;
                        userIDs.add(userID);
                    }
                }
            }

            if (num == 3) {
                brFireFox = new BufferedReader(new FileReader(path));

                while ((sCurrentLine = brFireFox.readLine()) != null) {
                    if (sCurrentLine.contains("UserSettings")) {
                        String newS = sCurrentLine;
                        int pos = newS.indexOf("UserID");
                        newS = newS.substring(pos, pos + 43);
                        StringTokenizer tokens = new StringTokenizer(newS, "=");
                        tokens.nextToken();
                        userID = tokens.nextToken();
                        userIDs.add(userID);
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
