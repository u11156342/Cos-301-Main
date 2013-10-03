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


        System.out.println("Starting events");

        UpdateEvents();

        System.out.println("STARTING INCOME");
        // now that the new buildings are added we start with the user income
        UpdateIncome();


    }

    public void UpdateBuildings() {
        // updates all uncompleted buildings build last month to completed and adjusts the owners stats
        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1;  

        try {
            sql = "SELECT * FROM BuildLog WHERE MONTH(BuildLogDateTimeBuilt) =" + month + " AND BuildLogCompleted='false'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {

                    System.out.println("PROCESSING BUILDING LOG ENTRY " + rs.getString("BuildLogID"));
                    int BuildLogID = Integer.parseInt(rs.getString("BuildLogID"));

                    int PlotID = Integer.parseInt(rs.getString("BuildLogPlotID"));

                    int BuildingID = Integer.parseInt(rs.getString("BuildLogBuildingID"));

                    String datebuild = rs.getString("BuildLogDateTimeBuilt");


                    System.out.println("retrieving buildig details");
                    ArrayList<String[]> retrieveBuildingDetailsById = retrieveBuildingDetailsById(BuildingID);
                    System.out.println("done retreiving " + retrieveBuildingDetailsById.get(0));
                    double income = Double.parseDouble(retrieveBuildingDetailsById.get(0)[6]);
                    int hapiness = Integer.parseInt(retrieveBuildingDetailsById.get(0)[10]);
                    double defenceValue = Double.parseDouble(retrieveBuildingDetailsById.get(0)[11]);
                    int buildTimeNeeded = Integer.parseInt(retrieveBuildingDetailsById.get(0)[7]);

                    double newIncome = 0;
                    int newHappiness = 0;
                    double newDefenceValue = 0;


                    if (CheckIfBuildingIsComplete(datebuild, buildTimeNeeded)) {

                        //before update need to find all the current values
                        Statement current = con.createStatement();
                        System.out.println("RETIEVE PLOT DETAILS");
                        ResultSet rsC = current.executeQuery("SELECT * FROM Plot WHERE PlotID=" + PlotID);
                        double currentInc = 0;
                        int currentHap = 0;
                        double currentDef = 0;
                        rsC.next();
                        System.out.println("PLOT FOUND GETTING VALUES " + PlotID);
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

                        //change building to complete
                        Statement update = con.createStatement();
                        update.execute("UPDATE  BuildLog SET  BuildLogCompleted='true'  WHERE BuildLogID=" + BuildLogID);


                    }
                }
            }


        } catch (Exception e) {
            System.out.println("Error in processing building");
            System.out.println(e.getMessage());

        }


    }

    public void UpdateIncome() {

        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1; //zero-based so +1 is the current month

        int day = cal.get(cal.DAY_OF_MONTH);

        if (day == 1) {
            ArrayList<String[]> allreadyProcessed = new ArrayList();
            try {

                // al the buildings processed this month, to make sure I add income multiple times
                sql = "SELECT * FROM IncomeLog WHERE MONTH(IncomeDateProcessed) =" + month;
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String[] line = new String[4];
                    line[0] = rs.getString("IncomeLogID");
                    line[1] = rs.getString("PlotID");
                    line[2] = rs.getString("IncomeValue");
                    line[3] = rs.getString("IncomeDateProcessed");
                    allreadyProcessed.add(line);
                }

            } catch (Exception ex) {
                System.out.println("Error in retrieving allready processd income");
                System.out.println(ex.getMessage());
            }

            System.out.println(allreadyProcessed.size());

            try {

                sql = "SELECT * FROM Plot";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int PlotID = Integer.parseInt(rs.getString("PlotID"));
                    double Income = Double.parseDouble(rs.getString("PlotMonthlyIncome"));
                    int AmountID = Integer.parseInt(rs.getString("PlotAmount"));


                    if (!AllreadyProcessed(allreadyProcessed, PlotID)) {

                        // first need to check if there is an event for the plot in this month where its income is increased

                        Statement checkLogs = con.createStatement();
                        ResultSet check = checkLogs.executeQuery("SELECT * FROM EventTriggerLog WHERE MONTH(EventTriggerLogDateProcessed)=" + month + " AND PlotID=" + PlotID);

                        if (check.isLast()) {
                            //no entry so do nothing
                        } else {
                            check.next();
                            int eventID = Integer.parseInt(check.getString("EventLogID"));

                            Statement eventDetails = con.createStatement();
                            ResultSet event = eventDetails.executeQuery("SELECT * FROM EventLog where EventLogID=" + eventID);
                            event.next();

                            int incomeModPer = Integer.parseInt(event.getString("EventLogEffectIncome"));

                            if (incomeModPer != 0) {
                                Income = Income / 100;
                                Income = Income * (100 + incomeModPer);
                            }
                        }


                        // insert log entry first
                        Statement log = con.createStatement();
                        log.execute("INSERT INTO IncomeLog(PlotID,IncomeValue,IncomeDateProcessed) VALUES (" + PlotID + "," + Income + ",CONVERT (date, SYSDATETIME()))");


                        //first get current funds
                        Statement current = con.createStatement();
                        ResultSet rsC = current.executeQuery("SELECT * FROM Amount where AmountID=" + AmountID);

                        rsC.next();

                        int Plat = Integer.parseInt(rsC.getString("AmountPlatinum"));
                        int Gold = Integer.parseInt(rsC.getString("AmountGold"));
                        int Silver = Integer.parseInt(rsC.getString("AmountSilver"));


                        double combinedSilver = Plat * 100.0 + Gold * 10.0 + Silver;

                        //income van gold na silver
                        Income = Income * 10;

                        double newSilver = combinedSilver + Income;


                        int newPlat = (int) (newSilver / 100);
                        newSilver = newSilver - (newPlat * 100);
                        int newGold = (int) (newSilver / 10);
                        newSilver = newSilver - (newGold * 10);
                        int news = (int) newSilver;


                        //now add the income
                        Statement IncomeUpdater = con.createStatement();
                        IncomeUpdater.execute("UPDATE Amount SET AmountPlatinum=" + newPlat + ",AmountGold=" + newGold + ",AmountSilver=" + news + " WHERE AmountID=" + AmountID);
                    }


                }
            } catch (Exception ex) {
                System.out.println("Error in processing income");
                System.out.println(ex.getMessage());
            }
        }

    }

    public boolean AllreadyProcessed(ArrayList<String[]> processed, int tobeP) {
        for (int i = 0; i < processed.size(); i++) {

            int ptemp = Integer.parseInt(processed.get(i)[1]);
            if (ptemp == tobeP) {
                return true;
            }
        }

        return false;
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
                line = new String[12];
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
                line[11] = rs.getString("BuildingDefenseValue");
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

    private void UpdateEvents() {

        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1; //zero-based so +1 is the current month
        int day = cal.get(cal.DAY_OF_MONTH);
        if (day != 1) {//need to make == 1 for fist of month
            ArrayList<String[]> allreadyProcessed = new ArrayList();
            try {

                // al the events processed this month
                sql = "SELECT * FROM EventTriggerLog WHERE MONTH(EventTriggerLogDateProcessed) =" + month;
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String[] line = new String[4];
                    line[0] = rs.getString("EventTriggerLogID");
                    line[1] = rs.getString("EventLogID");
                    line[2] = rs.getString("PlotID");
                    line[3] = rs.getString("EventTriggerLogDateProcessed");
                    allreadyProcessed.add(line);
                }

            } catch (Exception ex) {
                System.out.println("Error in retrieving allready processd events");
                System.out.println(ex.getMessage());
            }

            System.out.println(allreadyProcessed.size());

            try {

                sql = "SELECT * FROM EventLog";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int EventLog = Integer.parseInt(rs.getString("EventLogID"));

                    System.out.println("found event "+EventLog);
                    if (!AllreadyProcessed(allreadyProcessed, EventLog)) {
                        System.out.println("processing");
                        int PlotID = Integer.parseInt(rs.getString("PlotID"));

                        String EventName = rs.getString("EventLogName");
                        String description = rs.getString("EventLogDescription");

                        int PlatEffect = Integer.parseInt(rs.getString("EventLogEffectPlatinum"));
                        int GoldEffect = Integer.parseInt(rs.getString("EventLogEffectGold"));
                        int SilverEffect = Integer.parseInt(rs.getString("EventLogEffectSilver"));
                        int HapinessEffect = Integer.parseInt(rs.getString("EventLogEffectHappiness"));

                        //insert into log
                        Statement log = con.createStatement();
                        log.execute("INSERT INTO EventTriggerLog(EventLogID,PlotID,EventTriggerLogDateProcessed) VALUES (" + EventLog + "," + PlotID + ",CONVERT (date, SYSDATETIME()))");


                        //first get the funds of the plot I want to event on

                        Statement currentPlot = con.createStatement();
                        ResultSet PlotResultSet = currentPlot.executeQuery("SELECT * FROM Plot where PlotID=" + PlotID);

                        PlotResultSet.next();
                        int AmountID = Integer.parseInt(PlotResultSet.getString("PlotAmount"));

                        Statement currentFund = con.createStatement();
                        ResultSet fundsResultSet = currentFund.executeQuery("SELECT * FROM Amount where AmountID=" + AmountID);

                        fundsResultSet.next();

                        int Plat = Integer.parseInt(fundsResultSet.getString("AmountPlatinum"));
                        int Gold = Integer.parseInt(fundsResultSet.getString("AmountGold"));
                        int Silver = Integer.parseInt(fundsResultSet.getString("AmountSilver"));


                        double combinedSilver = Plat * 100.0 + Gold * 10.0 + Silver;
                        //income van gold na silver         

                        double silverToadd = PlatEffect * 100.0 + GoldEffect * 10.0 + SilverEffect;



                        double newSilver = combinedSilver + silverToadd;


                        int newPlat = (int) (newSilver / 100);
                        newSilver = newSilver - (newPlat * 100);
                        int newGold = (int) (newSilver / 10);
                        newSilver = newSilver - (newGold * 10);
                        int news = (int) newSilver;


                        //now add to plots amount
                        Statement IncomeUpdater = con.createStatement();
                        IncomeUpdater.execute("UPDATE Amount SET AmountPlatinum=" + newPlat + ",AmountGold=" + newGold + ",AmountSilver=" + news + " WHERE AmountID=" + AmountID);



              

                        int currentHapiness = Integer.parseInt(PlotResultSet.getString("PlotHappiness"));

                        int newhapiness = currentHapiness + HapinessEffect;

                        Statement HappinessUpdater = con.createStatement();
                        HappinessUpdater.execute("UPDATE Plot SET PlotHappiness=" + newhapiness);

                    }


                }
            } catch (Exception ex) {
                System.out.println("Error in processing event");
                System.out.println(ex.getMessage());
            }
        }

    }

    private void CheckHapiness() {


        try {
        } catch (Exception ex) {
        }


    }

    public boolean CheckIfBuildingIsComplete(String dateBuild, int weeks) {

        Calendar currentC = Calendar.getInstance();
        int month = currentC.get(currentC.MONTH) + 1;
        int day = currentC.get(currentC.DAY_OF_MONTH);
        int year = currentC.get(currentC.YEAR);


        System.out.println(dateBuild);

        return true;
    }
}
