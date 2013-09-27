/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTicker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author Fiyah
 */
public class Tick {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";

    public void pulse(Connection dbConnection) {

        con = dbConnection;

        // have to update all values so lets start with buildings that are done now
        UpdateBuildings();

        // now that the new buildings are added we start with the user income



    }

    public void UpdateBuildings() {
        // updates all uncompleted buildings build last month to completed and adjusts the owners stats


        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1; //zero-based so +1 is this month +0 is last month keeping it +1 for testing purposes

        // this will get all the buildings so I can adjust stats aprop 
        try {
            sql = "SELECT * FROM BuildLog WHERE MONTH(BuildLogDateTimeBuilt) =" + month + " AND BuildLogCompleted='false'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    System.out.println("PROCESSING BUILDING LOG ENTRY "+rs.getString("BuildLogID") );
                    System.out.println(rs.getString("BuildLogCharacterID"));
                }
            }


        } catch (Exception e) {
            System.out.println("ticker error 1");
            System.out.println(e.getMessage());
        }


        // now to change their status to completed


        try {
            sql = "UPDATE  BuildLog SET  BuildLogCompleted='true'  WHERE MONTH(BuildLogDateTimeBuilt) =" + month;
            stmt = con.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("ticker error 2");
            System.out.println(e.getMessage());
        }
    }

    public void UpdateIncome() {
        // this adds each propertys income to the property
    }
}
