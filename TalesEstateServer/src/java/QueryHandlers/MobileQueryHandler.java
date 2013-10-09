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
import java.util.Calendar;
import javax.ws.rs.PathParam;

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

    public String getid(String name) throws SQLException, MalformedURLException, IOException {
        QueryHandler handler = new QueryHandler(0);
        ArrayList<String> userList = new ArrayList();
        db = new DatabaseConnection();
        lname = name;
        conEstate = db.openConnectionEstate();


        sql = "SELECT UserCharacterID FROM UserCharacter where UserCharacterName='" + name + "';";
        stmt = conEstate.createStatement();
        rs = stmt.executeQuery(sql);

        while (rs.next()) {
            userList.add(rs.getString("UserCharacterID"));
        }


        if (!userList.isEmpty()) {

            String owner = userList.get(0);

            String completereturn = "";
            sql = "SELECT PlotID FROM Plot where PlotOwnedBy ='" + owner + "';";
            stmt = conEstate.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // rs.getString("PlotID")

                URL url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/StatusReport/" + rs.getString("PlotID"));
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
            return "error";
        }

    }
}
