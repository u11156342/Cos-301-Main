/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class MobileQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    private int testPlotID = 0;
    
    public MobileQueryHandlerTest(String testName) {
        super(testName);
        
        //Initialize test variables
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM UserCharacter WHERE "
                    + "UserCharacterName LIKE 'test character%'");
            rs.next();
            testCharID = Integer.parseInt(rs.getString("UserCharacterID"));
            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Plot WHERE PlotOwnedBy = "
                    + testCharID);
            rs.next();
            testPlotID = Integer.parseInt(rs.getString("PlotID"));
        }
        catch(Exception e) {
            System.out.println("Error in BuildingQueryHandlerTEST constructor");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getid method, of class MobileQueryHandler.
     */
 

    /**
     * Test of getdetails method, of class MobileQueryHandler.
     */
    public void testGetdetails() {
        System.out.println("Testing getdetails()");
        String name = "test character&*&1";
        MobileQueryHandler instance = null;
        String expResult = "<html><head></head><body><style type=\"text/css\">body{font-family: \"century gothic\";background-color: white;border-right-width: 2px;border-bottom-width: 2px;border-left-width: 2px;border-top-width: 2px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #FF0000;border-right-color: #FF0000;border-bottom-color: #FF0000;border-left-color: #FF0000;}h1{text-align: center;}table{width: 90%;}td{width: 50%;}th{text-align: left;}.hilight{font-size: 12px;}.sheading{font-size: 14px;font-weight: bold;}.ssheading{font-size: 10px;font-weight: bold;}</style><p><h1>Status report</h1><table><tr><td class=\"hilight\">Character Name:</td><td>test character</td></tr><tr><td class=\"hilight\">Estate Number:</td><td>1</td></tr><tr><td class=\"hilight\">Estate Name:</td><td>test estate</td></tr><tr><td class=\"hilight\">Duchy:</td><td>Thegnheim</td></tr><tr><td class=\"hilight\">Countie:</td><td>Aiber</td></tr></table><br/><table><tr><td class=\"sheading\">Acres</td><td></td></tr><tr><td>Poor:</td><td>1.0/1</td></tr><tr><td>Fine:</td><td>1.0/1</td></tr><tr><td>Exquisite:</td><td>1.0/1</td></tr></table><br/><table><tr><td class=\"sheading\">Happiness and workers</td></tr><tr><td>Happiness:</td><td>10</td></tr><tr><td>Workers employed:</td><td>10</td></tr><tr><td>Maximum workers:</td><td>20</td></tr></table><br/><table><tr><td class=\"sheading\">Estate funds</td></tr><tr><td>Platinum thrones:</td><td>50</td></tr><tr><td>Gold crowns:</td><td>50</td></tr><tr><td>Silver shields:</td><td>50</td></tr></table><br/><table><tr><td class=\"sheading\">Estate buildings</td></tr><th>Building ID</th><th>Building name </th><th>Income</th><th>Happiness</th><tr><td>7</td><td>Fishing Operation</td><td>5.0</td><td>0</td></tr><tr><td>9</td><td>Orchards (Fruit)</td><td>6.0</td><td>0</td></tr></table></p></body></html>";
        String result = "";
        
        try {
            instance = new MobileQueryHandler();
            result = instance.getdetails(name);
        }
        catch(Exception e) {
            System.out.println("Error in function testGetdetails()");
            System.out.println(e.getMessage());
        }

        assertEquals(expResult, result);
    }

    /**
     * Test of getnames method, of class MobileQueryHandler.
     */
    public void testGetnames() {
        System.out.println("Testing getnames()");
        
        String id = "T35T-ID";
        MobileQueryHandler instance = null;
        String expResult = "<html><head><style type=\"text/css\">Id0.hidden {display:hidden;}</style><script>\n" +
"function showHide0(Id0) {\n" +
"	if (document.getElementById(Id0)) {\n" +
"		if (document.getElementById(Id0).style.display != 'none') {\n" +
"			document.getElementById(Id0).style.display = 'none';\n" +
"		}\n" +
"		else {\n" +
"			document.getElementById(Id0).style.display = 'inline';\n" +
"		}\n" +
"	}\n" +
"}</script></head><body><div id=\"Id0\"  style=\"display:none;\"  class=\"more\">\n" +
"<p><style type=\"text/css\">body{font-family: \"century gothic\";background-color: white;border-right-width: 2px;border-bottom-width: 2px;border-left-width: 2px;border-top-width: 2px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #FF0000;border-right-color: #FF0000;border-bottom-color: #FF0000;border-left-color: #FF0000;}h1{text-align: center;}table{width: 90%;}td{width: 50%;}th{text-align: left;}.hilight{font-size: 12px;}.sheading{font-size: 14px;font-weight: bold;}.ssheading{font-size: 10px;font-weight: bold;}</style><p><h1>Status report</h1><table><tr><td class=\"hilight\">Character Name:</td><td>test character</td></tr><tr><td class=\"hilight\">Estate Number:</td><td>1</td></tr><tr><td class=\"hilight\">Estate Name:</td><td>test estate</td></tr><tr><td class=\"hilight\">Duchy:</td><td>Thegnheim</td></tr><tr><td class=\"hilight\">Countie:</td><td>Aiber</td></tr></table><br/><table><tr><td class=\"sheading\">Acres</td><td></td></tr><tr><td>Poor:</td><td>1.0/1</td></tr><tr><td>Fine:</td><td>1.0/1</td></tr><tr><td>Exquisite:</td><td>1.0/1</td></tr></table><br/><table><tr><td class=\"sheading\">Happiness and workers</td></tr><tr><td>Happiness:</td><td>10</td></tr><tr><td>Workers employed:</td><td>10</td></tr><tr><td>Maximum workers:</td><td>20</td></tr></table><br/><table><tr><td class=\"sheading\">Estate funds</td></tr><tr><td>Platinum thrones:</td><td>50</td></tr><tr><td>Gold crowns:</td><td>50</td></tr><tr><td>Silver shields:</td><td>50</td></tr></table><br/><table><tr><td class=\"sheading\">Estate buildings</td></tr><th>Building ID</th><th>Building name </th><th>Income</th><th>Happiness</th><tr><td>7</td><td>Fishing Operation</td><td>5.0</td><td>0</td></tr><tr><td>9</td><td>Orchards (Fruit)</td><td>6.0</td><td>0</td></tr></table></p></p></div>	<p><a href=\"#\" id=\"example-hide\" class=\"hideLink\" \n" +
"	onclick=\"showHide0('Id0');return false;\">test character</a></p>\n" +
"</body></html>";
        String result = "";
        
        try {
            instance = new MobileQueryHandler();
            result = instance.getnames(id);
        }
        catch(Exception e) {
            System.out.println("Error in function testGetdetails()");
            System.out.println(e.getMessage());
        }

        assertEquals(expResult, result);
    }
}
