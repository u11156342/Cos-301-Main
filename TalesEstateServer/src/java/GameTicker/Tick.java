/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTicker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
                    System.out.println("PROCESSING BUILDING LOG ENTRY " + rs.getString("BuildLogID"));

                    int PlotID = Integer.parseInt(rs.getString("BuildLogPlotID"));

                    int BuildingID = Integer.parseInt(rs.getString("BuildLogBuildingID"));
                    System.out.println("retrieving buildig details");
                    ArrayList<String[]> retrieveBuildingDetailsById = retrieveBuildingDetailsById(BuildingID);
                    System.out.println("done retreiving " + retrieveBuildingDetailsById.get(0));
                    double income = Double.parseDouble(retrieveBuildingDetailsById.get(0)[6]);
                    int hapiness = Integer.parseInt(retrieveBuildingDetailsById.get(0)[10]);

                    //needs to be but dont see in database
                    double defenceValue = 0;

                    double newIncome = 0;
                    int newHappiness = 0;
                    double newDefenceValue = 0;


                    //before update need to find all the current values
                    Statement current = con.createStatement();
                    System.out.println("RETIEVE PLOT DETAILS");
                    ResultSet rsC = current.executeQuery("SELECT * FROM Plot WHERE PlotID=" + PlotID);

                    double currentInc = 0;
                    int currentHap = 0;
                    double currentDef = 0;

                    rs.next();


                    System.out.println("PLOT FOUND GETTING VALUES");
                    currentInc = Double.parseDouble(rsC.getString("PlotMonthlyIncome"));

                    currentHap = Integer.parseInt(rsC.getString("PlotHappiness"));

                    currentDef = Double.parseDouble(rsC.getString("PlotDefenseValue"));






                    System.out.println("STARTING UPDATE");

                    Statement stmtIncome = null;
                    stmtIncome = con.createStatement();

                    // k now update income
                    newIncome = currentInc + income;
                    System.out.println("INCOME");
                    stmtIncome.execute("UPDATE Plot SET PlotMonthlyIncome=" + newIncome + " WHERE PlotID=" + PlotID);

                    // hapiness
                    newHappiness = currentHap + hapiness;
                    System.out.println("HAPINESS");
                    stmtIncome.execute("UPDATE Plot SET PlotHappiness=" + newHappiness + " WHERE PlotID=" + PlotID);

                    //defence
                    newDefenceValue = currentDef + defenceValue;
                    System.out.println("DEFENCE");
                    stmtIncome.execute("UPDATE Plot SET PlotDefenseValue=" + newDefenceValue + " WHERE PlotID=" + PlotID);

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

    public ArrayList<String[]> retrieveBuildingDetailsById(int id) {
        ArrayList<String[]> values;
        String[] line;
        String answer = "";
        int count;

        //Test if id exists

        //Retrieve details
        try {
            sql = "SELECT * FROM Building WHERE BuildingID = " + id;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            values = new ArrayList();

            while (rs.next()) {
                line = new String[11];
                line[0] = rs.getString("BuildingTypeID");
                line[1] = rs.getString("BuildingTypeOfIndustry");
                line[2] = rs.getString("BuildingAvailabilityID");
                line[3] = rs.getString("BuildingPrerequisiteID");
                line[4] = rs.getString("BuildingCost");
                line[5] = rs.getString("BuildingSetupCost");
                line[6] = rs.getString("BuildingMonthlyIncome");
                line[7] = rs.getString("BuildingWorkersNeeded");
                line[8] = rs.getString("BuildingTimeToBuild");
                line[9] = rs.getString("BuildingSizeRequired");
                line[10] = rs.getString("BuildingHappiness");
                values.add(line);
            }

            for (int a = 0; a < values.size(); a++) {
                sql = "SELECT BuildingTypeDescription FROM BuildingType "
                        + "WHERE BuildingTypeID = " + values.get(a)[0];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[0] = rs.getString("BuildingTypeDescription");

                sql = "SELECT * FROM BuildingAvailability WHERE "
                        + "BuildingAvailabilityID = " + values.get(a)[2];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                count = 0;
                answer = "";

                if (rs.getString("BuildingAvailabilityThegnheim").equals("1")) {
                    answer = "Thegnheim";
                    ++count;
                }
                if (rs.getString("BuildingAvailabilitySarkland").equals("1")) {
                    if (count > 0) {
                        answer += ",Sarkland";
                    } else {
                        answer += "Sarkland";
                    }
                }
                if (rs.getString("BuildingAvailabilityRagonvaldr").equals("1")) {
                    if (count > 0) {
                        answer += ",Ragonvaldr";
                    } else {
                        answer += "Ragonvaldr";
                    }
                }
                if (rs.getString("BuildingAvailabilitySvaerstein").equals("1")) {
                    if (count > 0) {
                        answer += ",Svaerstein";
                    } else {
                        answer += "Svaerstein";
                    }
                }
                if (rs.getString("BuildingAvailabilityRotheim").equals("1")) {
                    if (count > 0) {
                        answer += ",Rotheim";
                    } else {
                        answer += "Rotheim";
                    }
                }
                if (rs.getString("BuildingAvailabilityLangzerund").equals("1")) {
                    if (count > 0) {
                        answer += ",Langzerund";
                    } else {
                        answer += "Langzerund";
                    }
                }

                values.get(a)[2] = answer;

                sql = "SELECT BuildingPrerequisiteDescription FROM BuildingPrerequisite "
                        + "WHERE BuildingPrerequisiteID = " + values.get(a)[3];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[3] = rs.getString("BuildingPrerequisiteDescription");
            }

            return values;
        } catch (Exception e) {
            System.out.println("Unable to execute function retrieveBuildingDetailsById()");
            System.out.println(e.getMessage());
        }

        return null;
    }
}
