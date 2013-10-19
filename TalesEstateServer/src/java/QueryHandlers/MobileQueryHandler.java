/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author User
 */
public class MobileQueryHandler {

    String lname = "";
    private DatabaseConnection db = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conEstate = null;
    String sql = "";

    public MobileQueryHandler() throws SQLException {
    }

    public String getdetails(String name) {
        ArrayList<String> userList = new ArrayList();
        db = new DatabaseConnection();
        lname = name;
        conEstate = db.openConnectionEstate();

        name = name.replaceAll("\\.", " ");
        sql = "SELECT UserCharacterID FROM UserCharacter where UserCharacterName like '%" + name + "%';";
        try {
            stmt = conEstate.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                userList.add(rs.getString("UserCharacterID"));
            }

            String t = "";
            t = t + sql;
            if (!userList.isEmpty()) {

                String owner = userList.get(0);

                String completereturn = "";
                sql = "SELECT PlotID FROM Plot where PlotOwnedBy ='" + owner + "';";

                stmt = conEstate.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getString("PlotID"));
                    // rs.getString("PlotID")

                    URL url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/SuperStatusReport/" + rs.getString("PlotID"));
                    InputStream is = url.openConnection().getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                    // String line = StatusReport(Integer.parseInt(rs.getString("PlotID")));
                    String line = reader.readLine();

                    line = line.replaceAll("<head>", "");
                    line = line.replaceAll("</head>", "");
                    line = line.replaceAll("<html>", "");
                    line = line.replaceAll("</html>", "");
                    line = line.replaceAll("<body>", "<p>");
                    line = line.replaceAll("</body>", "</p>");
                    completereturn = completereturn + line;


                }



                return "<html>" + "<head></head>" + "<body>" + completereturn + "</body>" + "</html>";
            } else {
                return "User Not Found " + t;
            }
        } catch (Exception e) {
            return "Error in mobile getid";
            
        }
        

    }

    public String getnames(String id) {

        String toreturn = "";
        ArrayList<String> userList = new ArrayList();
        String line = "";
        String sql = "";
        String styles = "";
        String stylesin = "";
        db = new DatabaseConnection();

        conEstate = db.openConnectionEstate();

        int tochange = 0;
        String idtochange = "Id" + tochange;
        String longscriptpart = "";
        String completeline = "";
        try {
            String script = "";
            sql = "SELECT UserCharacterName FROM UserCharacter where ProdUserID='" + id + "';";
            stmt = conEstate.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // System.out.println("----");
                userList.add(rs.getString("UserCharacterName"));
            }




            String divtop = "";

            for (int i = 0; i < userList.size(); i++) {
                tochange = i;
                idtochange = "Id" + tochange;
                styles = styles + idtochange + ".hidden {display:hidden;}";
                // String sanitize = URLEncoder.encode(userList.get(i), "ISO-8859-1"); 
                String sanitize = userList.get(i).replaceAll(" ", "_");

                URL url = new URL("http://localhost:8080/TalesEstateServer/resources/MobileWrapper/getdetails/" + sanitize);
                InputStream is = url.openConnection().getInputStream();
                if (is == null) {
                    return "There is no information regarding your character";
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                // String line = StatusReport(Integer.parseInt(rs.getString("PlotID")));
                line = reader.readLine();



                divtop = "<div id=\"" + idtochange + "\"  style=\"display:none;\"  class=\"more\">\n";
                String wat =
                        ""
                        + "	<p><a href=\"#\" id=\"example-hide\" class=\"hideLink\" \n"
                        + "	onclick=\"showHide" + tochange + "('" + idtochange + "');return false;\">" + sanitize.substring(0, sanitize.indexOf('&')).replace("_", " ") + "</a></p>\n";


                line = line.replaceAll("<head>", "");
                line = line.replaceAll("</head>", "");
                line = line.replaceAll("<html>", "");
                line = line.replaceAll("</html>", "");
                line = line.replaceAll("<body>", "<p>");
                line = line.replaceAll("</body>", "</p>");
                line = divtop + line + "</div>" + wat;

                script = "function showHide" + tochange + "(" + idtochange + ") {\n"
                        + "	if (document.getElementById(" + idtochange + ")) {\n"
                        + "		if (document.getElementById(" + idtochange + ").style.display != 'none') {\n"
                        + "			document.getElementById(" + idtochange + ").style.display = 'none';\n"
                        + "		}\n"
                        + "		else {\n"
                        + "			document.getElementById(" + idtochange + ").style.display = 'inline';\n"
                        + "		}\n"
                        + "	}\n"
                        + "}";

                longscriptpart = longscriptpart + "\n" + script;

                completeline = line + completeline;
            }


        } catch (Exception e) {

            System.out.println("Error in getnames");
        }

        return "<html>" + "<head><style type=\"text/css\">" + styles + "</style><script>" + longscriptpart + "</script></head>" + "<body>" + completeline + "</body>" + "</html>";


    }
}
