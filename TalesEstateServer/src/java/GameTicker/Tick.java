/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTicker;

import QueryHandlers.QueryHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *
 * @author Fiyah
 */
public class Tick {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    QueryHandler qhandler = new QueryHandler(0);

    public void pulse(Connection dbConnection) {

        con = dbConnection;

        // have to update all values so lets start with buildings that are done now
      //  System.out.println("Starting Buildings");
        UpdateBuildings();
      //  System.out.println("Starting events");
        UpdateEvents();
       // System.out.println("STARTING INCOME");
        // now that the new buildings are added we start with the user income
        UpdateIncome();
       // System.out.println("STARTING HAPINESS");
        CheckHapiness();


    }

    public void UpdateBuildings() {
        // updates all buildings build in current month
        Calendar cal = Calendar.getInstance();

        DateFormat monthF = new SimpleDateFormat("MM");
        String month = monthF.format(cal.getTime());

        DateFormat dayF = new SimpleDateFormat("dd");
        String day = dayF.format(cal.getTime());



        try {
            //what if a building was build sothat it overlaps with new month so cant check months
            sql = "SELECT * FROM BuildLog WHERE  BuildLogCompleted='false'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {

                //    System.out.println("PROCESSING BUILDING LOG ENTRY " + rs.getString("BuildLogID"));
                    int BuildLogID = Integer.parseInt(rs.getString("BuildLogID"));

                    int PlotID = Integer.parseInt(rs.getString("BuildLogPlotID"));

                    int BuildingID = Integer.parseInt(rs.getString("BuildLogBuildingID"));

                    String datebuild = rs.getString("BuildLogDateTimeBuilt");


               //     System.out.println("retrieving buildig details");
                    ArrayList<String[]> retrieveBuildingDetailsById = qhandler.getBuildingQH().retrieveBuildingDetailsById(BuildingID);
                //    System.out.println("done retreiving " + retrieveBuildingDetailsById.get(0)[0]);
                    double income = Double.parseDouble(retrieveBuildingDetailsById.get(0)[6]);
                    int hapiness = Integer.parseInt(retrieveBuildingDetailsById.get(0)[10]);
                    double defenceValue = Double.parseDouble(retrieveBuildingDetailsById.get(0)[11]);
                    int buildTimeNeeded = Integer.parseInt(retrieveBuildingDetailsById.get(0)[8]);

                    double newIncome = 0;
                    int newHappiness = 0;
                    double newDefenceValue = 0;


                    if (CheckIfBuildingIsComplete(datebuild, buildTimeNeeded)) {

                        //before update need to find all the current values
                        Statement current = con.createStatement();
                  //      System.out.println("RETIEVE PLOT DETAILS");
                        ResultSet rsC = current.executeQuery("SELECT * FROM Plot WHERE PlotID=" + PlotID);
                        double currentInc = 0;
                        int currentHap = 0;
                        double currentDef = 0;
                        rsC.next();
                   //     System.out.println("PLOT FOUND GETTING VALUES " + PlotID);
                        currentInc = Double.parseDouble(rsC.getString("PlotMonthlyIncome"));
                        currentHap = Integer.parseInt(rsC.getString("PlotHappiness"));
                        currentDef = Double.parseDouble(rsC.getString("PlotDefenseValue"));

                    //    System.out.println("STARTING UPDATE");
                        Statement stmtIncome = null;
                        stmtIncome = con.createStatement();
                        // k now update income
                        newIncome = currentInc + income;
                    //    System.out.println("INCOME");
                        stmtIncome.execute("UPDATE Plot SET PlotMonthlyIncome=" + newIncome + " WHERE PlotID=" + PlotID);

                        // hapiness
                        newHappiness = currentHap + hapiness;
                    //    System.out.println("HAPINESS");
                        stmtIncome.execute("UPDATE Plot SET PlotHappiness=" + newHappiness + " WHERE PlotID=" + PlotID);

                        //defence
                        newDefenceValue = currentDef + defenceValue;
                    //    System.out.println("DEFENCE");
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

        //update income earned for the month

        Calendar cal = Calendar.getInstance();

        DateFormat monthF = new SimpleDateFormat("MM");
        String month = monthF.format(cal.getTime());

        DateFormat dayF = new SimpleDateFormat("dd");
        String day = dayF.format(cal.getTime());


        if ("28".equals(day)) {//28 of every month

            ArrayList<String[]> allreadyProcessed = new ArrayList();
            try {

                // al the income processed this month, to make sure I add income multiple times
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

            //  System.out.println(allreadyProcessed.size());

            try {

                String sql2 = "SELECT * FROM Plot";
                Statement stmt2 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery(sql2);

                while (rs2.next()) {
                    int PlotID = Integer.parseInt(rs2.getString("PlotID"));
                    double Income = Double.parseDouble(rs2.getString("PlotMonthlyIncome"));
                    int AmountID = Integer.parseInt(rs2.getString("PlotAmount"));


                    if (!AllreadyProcessed(allreadyProcessed, PlotID)) {

                        // first need to check if there is an event for the plot in this month where its income is increased


                        Statement checkLogs = con.createStatement();
                        ResultSet check = checkLogs.executeQuery("SELECT * FROM EventTriggerLog WHERE MONTH(EventTriggerLogDateProcessed)=" + month + " AND PlotID=" + PlotID);


                        while (check.next()) {

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

    private void UpdateEvents() {

        Calendar cal = Calendar.getInstance();

        DateFormat monthF = new SimpleDateFormat("MM");
        String month = monthF.format(cal.getTime());

        DateFormat dayF = new SimpleDateFormat("dd");
        String day = dayF.format(cal.getTime());

        if ("28".equals(day)) {
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

          //  System.out.println(allreadyProcessed.size());

            try {

                sql = "SELECT * FROM EventLog";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int EventLog = Integer.parseInt(rs.getString("EventLogID"));

                 //   System.out.println("found event " + EventLog);
                    if (!AllreadyProcessed(allreadyProcessed, EventLog)) {
                   //     System.out.println("processing");
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
//still need to implement

    private void CheckHapiness() {


        try {
            Statement allPlots = con.createStatement();
            ResultSet plots = allPlots.executeQuery("SELECT * FROM Plot");

            while (plots.next()) {

                int PlotID = Integer.parseInt(plots.getString("PlotID"));
                String eventName = "Unhappy workers";
                String eventDescription = "Your workers are unhappy, they work at reduced production";
                
                int hapiness = Integer.parseInt(plots.getString("PlotHappiness"));
                
                ArrayList<String> happinessEffect = qhandler.getPlotQH().getHappinessEffect(hapiness);
                
                int HapinessMod=Integer.parseInt(happinessEffect.get(0));
                int HapinessRebel=Integer.parseInt(happinessEffect.get(1));
                
                double incomeLoss=0;

                if (hapiness < 0) {
                    
                  //  qhandler.getEventQH().addEvent(PlotID, eventName, eventDescription, 0, 0, 0, 0, incomeLoss);
                }


            }
        } catch (Exception ex) {
            System.out.println("ERROR IN PROCESSING HAPINESS AFFECTS");
        }


    }


    public boolean CheckIfBuildingIsComplete(String dateBuild, int weeks) {

        Calendar cal = Calendar.getInstance();
        int days = 7 * weeks;
        days=days*-1;
        cal.add(Calendar.DATE, days);
        DateFormat monthF = new SimpleDateFormat("MM");
        String month = monthF.format(cal.getTime());

        DateFormat dayF = new SimpleDateFormat("dd");
        String day = dayF.format(cal.getTime());

        DateFormat yearF = new SimpleDateFormat("YYYY");
        String year = yearF.format(cal.getTime());
        
        StringTokenizer tokens=new StringTokenizer(dateBuild,"-");
        
        String yearB=tokens.nextToken();
        String mountB=tokens.nextToken();
        String dayB=tokens.nextToken();
      //  System.out.println("Building was build on "+dateBuild+" and it will be completed when "+year+"-"+month+"-"+day+" equals "+dateBuild);
        
        if(yearB.equals(year) && mountB.equals(month) && dayB.equals(day))
        {
            // the exact date that the building was build
            return true;
        }

        return false;
    }
}
