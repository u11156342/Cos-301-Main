package Wrapper;

import QueryHandlers.QueryHandler;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * This class hosts the restfull web services that is used to connect to the
 * database
 *
 * @author NightFiyah
 * @version 1.0
 * @since 2013-07-26
 */
@Stateless
@Path("/Wrapper")
public class Wrapper {

    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();

    public Wrapper() {
    }

    @GET
    @Path("StatusReport/{PropertyId}")
    @Produces("text/html")
    public String StatusReport(@PathParam("PropertyId") int PropertyId) {

        ArrayList<String> details = handler.getPlotQH().retrievePlotDetails(PropertyId);

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");

        /* CSS for page
         */
        html.append("<style type=\"text/css\">");
        html.append("body{"
                + "font-family: \"century gothic\";"
                + "background-color: white;"
                + "border-right-width: 2px;"
                + "border-bottom-width: 2px;"
                + "border-left-width: 2px;"
                + "border-top-width: 2px;"
                + "border-top-style: solid;"
                + "border-right-style: solid;"
                + "border-bottom-style: solid;"
                + "border-left-style: solid;"
                + "border-top-color: #FF0000;"
                + "border-right-color: #FF0000;"
                + "border-bottom-color: #FF0000;"
                + "border-left-color: #FF0000;"
                + "}");
        html.append("h1{"
                + "text-align: center;"
                + "}");
        html.append("table{"
                + "width: 90%;"
                + "}");
        html.append("td{"
                + "width: 50%;"
                + "}");
        html.append("hr{"
                + ""
                + "}");
        html.append(".hilight{"
                + "font-size: 12px;"
                + "}");
        html.append(".sheading{"
                + "font-size: 14px;"
                + "font-weight: bold;"
                + "}");
        html.append(".ssheading{"
                + "font-size: 10px;"
                + "font-weight: bold;"
                + "}");
        html.append("</style>");
        html.append("</head>");

        html.append("<body>");
        html.append("<h1>Status report</h1>");

        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"hilight\">Character Name:</td><td>").append(details.get(1)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td class=\"hilight\">Estate Number:</td><td>").append(details.get(0)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td class=\"hilight\">Duchy:</td><td>").append(details.get(3)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"sheading\">Acres</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Poor:</td><td>").append(details.get(15)).append("/").append(details.get(16)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Fine:</td><td>").append(details.get(13)).append("/").append(details.get(14)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Exquisite:</td><td>").append(details.get(11)).append("/").append(details.get(12)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"sheading\">Happiness and Workers</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Happiness:</td><td>").append(details.get(7)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Workers employed:</td><td>").append(details.get(9)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Maximum workers:</td><td>").append(details.get(10)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        ArrayList<String> money = handler.getPlotQH().getCurrentAmount(PropertyId);
        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"sheading\">Estate funds</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Income:</td><td>").append(details.get(8)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Platinum:</td><td>").append(money.get(0)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Gold:</td><td>").append(money.get(1)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Silver:</td><td>").append(money.get(2)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1; //zero-based

        ArrayList<String[]> event = handler.getEventQH().getEvent(month, PropertyId);

        if (event != null) {
            if (event.size() > 0) {
                html.append("<p class=\"sheading\">Events</p>");
                html.append("<br/><hr/>");

                for (int i = 0; i < event.size(); i++) {
                    html.append("<table>");
                    html.append("<tr><td class=\"ssheading\">Name:</td><td>").append(event.get(i)[2]).append("</td></tr>");
                    html.append("<tr><td class=\"ssheading\">Description:</td><td>").append(event.get(i)[3].replace("~", " ")).append("</td></tr>");

                    html.append("<tr>");
                    html.append("<td>Platinum Effect:</td><td>").append(event.get(i)[5]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Gold Effect:</td><td>").append(event.get(i)[6]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Silver Effect:</td><td>").append(event.get(i)[7]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Happiness Effect:</td><td>").append(event.get(i)[8]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Income Effect:</td><td>").append(event.get(i)[9]).append("%</td>");
                    html.append("</tr>");
                    html.append("</table>");
                    html.append("<hr/>");
                }
            }
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    @GET
    @Path("SuperStatusReport/{PropertyId}")
    @Produces("text/html")
    public String SuperStatusReport(@PathParam("PropertyId") int PropertyId) {

        ArrayList<String> details = handler.getPlotQH().retrievePlotDetails(PropertyId);

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");

        /* CSS for page
         */
        html.append("<style type=\"text/css\">");
        html.append("body{"
                + "font-family: \"century gothic\";"
                + "background-color: white;"
                + "border-right-width: 2px;"
                + "border-bottom-width: 2px;"
                + "border-left-width: 2px;"
                + "border-top-width: 2px;"
                + "border-top-style: solid;"
                + "border-right-style: solid;"
                + "border-bottom-style: solid;"
                + "border-left-style: solid;"
                + "border-top-color: #FF0000;"
                + "border-right-color: #FF0000;"
                + "border-bottom-color: #FF0000;"
                + "border-left-color: #FF0000;"
                + "}");
        html.append("h1{"
                + "text-align: center;"
                + "}");
        html.append("table{"
                + "width: 90%;"
                + "}");
        html.append("td{"
                + "width: 50%;"
                + "}");
        html.append("th{"
                + "text-align: left;"
                + "}");
        html.append(".hilight{"
                + "font-size: 12px;"
                + "}");
        html.append(".sheading{"
                + "font-size: 14px;"
                + "font-weight: bold;"
                + "}");
        html.append(".ssheading{"
                + "font-size: 10px;"
                + "font-weight: bold;"
                + "}");
        html.append("</style>");
        html.append("</head>");

        html.append("<body>");

        html.append("<h1>Status report</h1>");

        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"hilight\">Character Name:</td><td>").append(details.get(1)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td class=\"hilight\">Estate Number:</td><td>").append(details.get(0)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td class=\"hilight\">Duchy:</td><td>").append(details.get(3)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"sheading\">Acres</td><td></td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Poor:</td><td>").append(details.get(15)).append("/").append(details.get(16)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Fine:</td><td>").append(details.get(13)).append("/").append(details.get(14)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Exquisite:</td><td>").append(details.get(11)).append("/").append(details.get(12)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        html.append("<table>");
        html.append("<tr><td class=\"sheading\">Happiness and workers</td></tr>");
        html.append("<tr>");
        html.append("<td>Happiness:</td><td>").append(details.get(7)).append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Workers employed:</td><td>").append(details.get(9)).append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Maximum workers:</td><td>").append(details.get(10)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        ArrayList<String> money = handler.getPlotQH().getCurrentAmount(PropertyId);

        html.append("<table>");
        html.append("<tr>");
        html.append("<td class=\"sheading\">Estate funds</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Platinum:</td><td>").append(money.get(0)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Gold:</td><td>").append(money.get(1)).append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Silver:</td><td>").append(money.get(2)).append("</td>");
        html.append("</tr>");
        html.append("</table>");

        html.append("<br/>");

        ArrayList<String[]> retrieveAllBuildingsOwnedByCharacter = handler.getBuildingQH().retrieveAllBuildingsOwnedByCharacter(handler.getCharacterQH().retrieveCharacterID(details.get(1)), PropertyId);
        ArrayList<String[]> tempresult;

        html.append("<table>");
        html.append("<tr><td class=\"sheading\">Estate buildings</td></tr>");

        if (retrieveAllBuildingsOwnedByCharacter.size() == 0) {
            html.append("<p>There are currently no buildings bought for this estate.</p>");
        } else {
            html.append("<th>Building ID</th>");
            html.append("<th>Building name </th>");
            html.append("<th>Income</th>");
            html.append("<th>Happiness</th>");

            for (int a = 0; a < retrieveAllBuildingsOwnedByCharacter.size(); a++) {
                tempresult = handler.getBuildingQH().retrieveBuildingDetailsById(Integer.parseInt(retrieveAllBuildingsOwnedByCharacter.get(a)[0]));
                html.append("<tr>");
                html.append("<td>").append(retrieveAllBuildingsOwnedByCharacter.get(a)[0]).append("</td>");
                html.append("<td>").append(tempresult.get(0)[1]).append("</td>");
                html.append("<td>").append(tempresult.get(0)[6]).append("</td>");
                html.append("<td>").append(tempresult.get(0)[10]).append("</td>");
                html.append("</tr>");
            }
        }

        html.append("</table>");

        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1; //zero-based

        ArrayList<String[]> event = handler.getEventQH().getEvent(month, PropertyId);

        if (event != null) {
            if (event.size() > 0) {
                html.append("<p class=\"sheading\">Events<p>");
                html.append("<br/><hr/>");

                for (int i = 0; i < event.size(); i++) {
                    html.append("<table>");
                    html.append("<tr></td class=\"ssheading\">Name:</td><td>").append(event.get(i)[2]).append("</td></tr>");
                    html.append("<tr></td class=\"ssheading\">Description:</td><td>").append(event.get(i)[3].replace("~", " ")).append("</td></tr>");

                    html.append("<tr>");
                    html.append("<td>Platinum Effect:</td><td>").append(event.get(i)[5]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Gold Effect:</td><td>").append(event.get(i)[6]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Silver Effect:</td><td>").append(event.get(i)[7]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Happiness Effect:</td><td>").append(event.get(i)[8]).append("</td>");
                    html.append("</tr>");

                    html.append("<tr>");
                    html.append("<td>Income Effect:</td><td>").append(event.get(i)[9]).append("%</td>");
                    html.append("</tr>");
                    html.append("</table>");
                    html.append("<hr/>");
                }
            }
        }

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    @GET
    @Path("StatusReport2/{PropertyId}")
    @Produces("text/html")
    public String StatusReport2(@PathParam("PropertyId") int PropertyId) {

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("</head>");

        html.append("<body>");

        html.append("<h1> <font color=\"blue\">Estate Details</font></h1>");

        html.append("<table>");
        html.append("	<tr>");
        html.append("		<td>Player Name</td><td> Rean Lubbe</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Character Name </td><td>Aihire Thule</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Estate Number </td><td>SAK0003</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Duchy </td><td> Sarkland</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>County</td><td> Nuidottir</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Acres</td><td> 1</td>");
        html.append("	</tr>");
        html.append("</table>");

        html.append("<br>");

        html.append("<h1> <font color=\"blue\">Happiness, Workers and Events</font></h1>");

        html.append("<table border=\"1\">");
        html.append("	<tr>");
        html.append("		<td>Happiness</td><td>  3</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Workers Currently Employed </td><td> 20</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Maximum workers </td><td> 40</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>Funds in Estate </td><td> 24</td>");
        html.append("	</tr>");

        html.append("</table>");
        html.append("</br>");

        html.append("</p>");
        html.append("This month you withdrew money from your estate as indicated in your income sheet.  In addition, you ");

        html.append("had a boom month as the demand for Silk rose sharply due to the first invitations going out for the");

        html.append("wedding of the century between Duke Rotheimâ€™s granddaughter and Duke Svaersteinâ€™s son.  This has ");

        html.append("resulted in a 50% increase in profits");

        html.append("Please note that that his increase is valid for this month only.");
        html.append("</p>");

        html.append("<br>");

        html.append("<h1> <font color=\"blue\">Estate Monthly Income</font></h1>");
        html.append("Your Income Table for this month is:");

        html.append("<table border=\"1\">");
        html.append("	<tr>");
        html.append("		<td>1-Jun-13  </td><td>Withdrawal -15.5</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>30-Jun-13  </td><td>SAK0003-0001 -1.5</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>30-Jun-13  </td><td>SAK0003-0001-01 10.5</td>");
        html.append("	</tr>");
        html.append("	<tr>");
        html.append("		<td>30-Jun-13  </td><td>SAK0003-0001-02 15</td>");
        html.append("	</tr>");
        html.append("</table>");


        html.append("<h1> <font color=\"blue\">Estate Purchases</font></h1>");

        html.append("Your current estate purchases are:");

        html.append("<table border=\"1\">");
        html.append("<th>What Purchased</th>");
        html.append("<th>Real Date</th>");
        html.append("<th>Crowns</th>");
        html.append("<th>Purchased Number</th>");
        html.append("<th>Acre Req</th>");
        html.append("<th>Workers</th>");
        html.append("<th>Hapiness</th>");

        html.append("	<tr>");
        html.append("		<td>Poor Land</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Silkworks</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Spider </td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");

        html.append("	<tr>");
        html.append("		<td>Silkworks</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>");
        html.append("	</tr>");


        html.append("</table>");
        html.append("</body>");


        html.append("</html>");

        return html.toString();


    }
}
