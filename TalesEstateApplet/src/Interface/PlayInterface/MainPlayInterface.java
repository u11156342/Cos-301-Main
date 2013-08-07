/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.PlayInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import talesestateapplet.BasePanel;

/**
 *
 * @author NightFiyah
 */
public class MainPlayInterface extends BasePanel {

    JTextPane textZone = new JTextPane();
    int size;
    String duchy;
    int quality;
    int propertyID;
    int[][] tiles;
    int[][] buildings;

    public MainPlayInterface(String name, int JFXPANEL_WIDTH_INT, int JFXPANEL_HEIGHT_INT, int propertyIDz, String duchys, int qual, int sizes, int[][] tilesz, int[][] buildingsz) {
        super(name);

        size = sizes;
        duchy = duchys;
        quality = qual;
        propertyID = propertyIDz;
        tiles = tilesz;
        buildings = buildingsz;

        JScrollPane scrollText = new JScrollPane(textZone);
        scrollText.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

        add(scrollText, BorderLayout.CENTER);

        MainPlaySideMenu menu = new MainPlaySideMenu();
        menu.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT / 5, JFXPANEL_HEIGHT_INT));
        add(menu, BorderLayout.EAST);

        textZone.setContentType("text/html");

        textZone.setText("<html>\n" +
"\n" +
"<head>\n" +
"</head>\n" +
"\n" +
"<body>\n" +
"\n" +
"	<table>\n" +
"		<tr>\n" +
"			<td>Current Funds     -</td>\n" +
"			<td>Platinum : 0</td>\n" +
"			<td>Gold : 0</td>\n" +
"			<td>Silver : 0</td>\n" +
"		</tr>\n" +
"		\n" +
"		<tr>\n" +
"			<td>Income -</td>\n" +
"			<td>Platinum : 0</td>\n" +
"			<td>Gold : 0</td>\n" +
"			<td>Silver : 0</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"		<td></td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Plot Details:</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Plot ID : 0</td>\n" +
"			<td>Duchy : a</td>\n" +
"			<td>Quality : q</td>\n" +
"			<td>Acres : 1</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Plot Income:</td>\n" +
"		</tr>\n" +
"		<tr>\n" +
"			<td>Ground Upkeep : 0</td>\n" +
"			<td>Building Upkeep : a</td>\n" +
"			<td>Quality : q</td>\n" +
"			<td>Acres : 1</td>\n" +
"		</tr>\n" +
"	</table>\n" +
"	\n" +
"<h1> <font color=\"blue\">Estate Details</font></h1>\n" +
"<table>\n" +
"<tr>\n" +
"<td>Player Name</td><td> Rean Lubbe</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>Character Name </td><td>Aihire Thule</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>Estate Number </td><td>SAK0003</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>Duchy </td><td> Sarkland</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>County</td><td> Nuidottir</td>\n" +
"</tr>\n" +
"</table>\n" +
"<br>\n" +
"\n" +
"<h1> <font color=\"blue\">Happiness, Workers and Events</font></h1>\n" +
"<table border=\"1\">\n" +
"<tr>\n" +
"<td>Happiness</td><td>  3</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>Workers Currently Employed </td><td> 20</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>Funds in Estate </td><td> 24</td>\n" +
"</tr>\n" +
"\n" +
"</table>\n" +
"\n" +
"</p>\n" +
"This month you withdrew money from your estate as indicated in your income sheet.  In addition, you \n" +
"\n" +
"had a boom month as the demand for Silk rose sharply due to the first invitations going out for the \n" +
"\n" +
"wedding of the century between Duke Rotheim’s granddaughter and Duke Svaerstein’s son.  This has \n" +
"\n" +
"resulted in a 50% increase in profits\n" +
"\n" +
"Please note that that his increase is valid for this month only.\n" +
"</p>\n" +
"\n" +
"<br>\n" +
"\n" +
"<h1> <font color=\"blue\">Estate Monthly Income</font></h1>\n" +
"Your Income Table for this month is:\n" +
"<table border=\"1\">\n" +
"\n" +
"\n" +
"<tr>\n" +
"<td>1-Jun-13  </td><td>Withdrawal -15.5</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>30-Jun-13  </td><td>SAK0003-0001 -1.5</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>30-Jun-13  </td><td>SAK0003-0001-01 10.5</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>30-Jun-13  </td><td>SAK0003-0001-02 15</td>\n" +
"</tr>\n" +
"\n" +
"</table>\n" +
"\n" +
"\n" +
"<h1> <font color=\"blue\">Estate Purchases</font></h1>\n" +
"\n" +
"Your current estate purchases are:\n" +
"\n" +
"<table border=\"1\">\n" +
"<th>What Purchased</th>\n" +
"<th>Real Date</th>\n" +
"<th>Crowns</th>\n" +
"<th>Purchased Number</th>\n" +
"<th>Acre Req</th>\n" +
"<th>Workers</th>\n" +
"<th>Hapiness</th>\n" +
"\n" +
"<tr>\n" +
"<td>Poor Land</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>\n" +
"\n" +
"</tr>\n" +
"\n" +
"<tr>\n" +
"<td>Silkworks</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>\n" +
"</tr>\n" +
"\n" +
"<tr>\n" +
"<td>Spider </td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>\n" +
"</tr>\n" +
"\n" +
"<tr>\n" +
"<td>Silkworks</td><td>1-May-13</td><td> 30 </td><td>SAK0003-0001</td><td>0</td><td>10</td><td>3</td>\n" +
"</tr>\n" +
"\n" +
"\n" +
"</table>\n" +
"</body>\n" +
"\n" +
"\n" +
"</html>");

    }
}
