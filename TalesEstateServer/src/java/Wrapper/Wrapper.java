package Wrapper;

import QueryHandlers.QueryHandler;
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

    QueryHandler handler = handler = new QueryHandler(0);
    Converter converter = new Converter();

    public Wrapper() {
    }

    @GET
    @Path("StatusReport/{PropertyId}")
    @Produces("text/html")
    public String StatusReport(@PathParam("PropertyId") int PropertyId) {

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
