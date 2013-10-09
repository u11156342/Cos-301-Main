/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import Wrapper.Wrapper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.io.BufferedReader;
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
public class requeryhandler {

    String lname = "";
    private DatabaseConnection db = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conEstate = null;
    String sql = "";

    public requeryhandler() throws SQLException {
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

                //  URL url = new URL("http://localhost:8080/TalesEstateServer/resources/Wrapper/StatusReport/"+   rs.getString("PlotID"));
                //InputStream is = url.openConnection().getInputStream();

                //BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );

                String line = StatusReport(Integer.parseInt(rs.getString("PlotID")));

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

    public String StatusReport(@PathParam("PropertyId") int PropertyId) {
        QueryHandler handler = new QueryHandler(0);
        ArrayList<String> details = handler.getPlotQH().retrievePlotDetails(PropertyId);

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");

        html.append("<body>");

        html.append("<h1> <font color=\"blue\">Estate Details</font></h1>");

        html.append("<table>");
        html.append("	<tr>");
        html.append("		<td>Character Name </td><td>").append(details.get(1)).append("</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Estate Number </td><td>").append(details.get(0)).append("</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Duchy </td><td>").append(details.get(3)).append("</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Acres</td><td></td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Poor</td><td>").append(details.get(15)).append("/").append(details.get(16)).append("</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Fine</td><td>").append(details.get(13)).append("/").append(details.get(14)).append("</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Exquisit</td><td>").append(details.get(11)).append("/").append(details.get(12)).append("</td>");
        html.append("	</tr>");

        html.append("</table>");

        html.append("<br>");

        html.append("<h1> <font color=\"blue\">Happiness and Workers</font></h1>");

        html.append("<table border=\"1\">");
        html.append("	<tr>");
        html.append("		<td>Happiness</td><td>").append(details.get(7)).append("</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Workers Currently Employed </td><td>").append(details.get(9)).append("</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Maximum workers </td><td>").append(details.get(10)).append("</td>");
        html.append("	</tr>");

        ArrayList<String> money = handler.getPlotQH().getCurrentAmount(PropertyId);
        html.append("</table>");
        html.append("<br>");
        html.append("<table>");
        html.append("	<tr>");
        html.append("		<td>Funds in Estate </td><td></td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Platinum </td><td>").append(money.get(0)).append("</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Gold </td><td>").append(money.get(1)).append("</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Silver</td><td>").append(money.get(2)).append("</td>");
        html.append("	</tr>");

        html.append("</table>");
        html.append("</br>");

        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1; //zero-based

        ArrayList<String[]> event = handler.getEventQH().getEvent(month, PropertyId);

        if (event != null) {
            if (event.size() > 0) {
                html.append("<h1> <font color=\"blue\">Events</font></h1>");
                for (int i = 0; i < event.size(); i++) {
                    html.append("<br>");
                    html.append("Name : ").append(event.get(i)[2]).append("");
                    html.append("<br>");
                    html.append("<p> Description : ").append(event.get(i)[3]).append("</p>");
                    html.append("<br>");
                    html.append("<table border=\"1\">");
                    html.append("	<tr>");
                    html.append("<td>Platinum Effect</td><td>").append(event.get(i)[5]).append("</td>");
                    html.append("	</tr>");
                    html.append("	<tr>");
                    html.append("<td>Gold Effect</td><td>").append(event.get(i)[6]).append("</td>");
                    html.append("	</tr>");
                    html.append("	<tr>");
                    html.append("<td>Silver Effect</td><td>").append(event.get(i)[7]).append("</td>");
                    html.append("	</tr>");
                    html.append("	<tr>");
                    html.append("<td>Happiness Effect</td><td>").append(event.get(i)[8]).append("</td>");
                    html.append("	</tr>");
                    html.append("	<tr>");
                    html.append("<td>Income Effect</td><td>").append(event.get(i)[9]).append("</td>");
                    html.append("	</tr>");
                    html.append("</table>");
                }
            }
        }

        html.append("</body>");


        html.append("</html>");

        return html.toString();


    }
}
