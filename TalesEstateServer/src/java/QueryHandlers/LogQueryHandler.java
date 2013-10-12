package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class LogQueryHandler {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";

    public LogQueryHandler(Connection c) {
        super();
        con = c;
    }

    /*
     * This function logs buildings that are built.
     * 
     * The 'date' parameter must be supplied using the Date class.
     * Tip: Date d = new Date();
     * Simply create the date object and send it through as the parameter.
     */
    public void logBuildingBuilt(int characterID, int buildingID, int plotID, Date date) {
        String day, month, year, hourS, time, period, ttb = "";
        int hour, resultCount = 0;
        ResultSet rs = null;

        //Check if character exists
        sql = "SELECT * FROM UserCharacter WHERE " + characterID + " = "
                + "UserCharacterID";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    ++resultCount;
                }
            }
        } catch (Exception e) {
            System.out.println("Error when executing logBuildingBuilt()");
            System.out.println(e.getMessage());
        }

        if (resultCount == 0) {
            System.out.println("Error in function logBuildingBuilt(): characterID does not exist.");
        } else {
            sql = "SELECT * FROM Building WHERE " + buildingID + " = "
                    + "BuildingID";
            resultCount = 0;
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    ttb = rs.getString("BuildingTimeToBuild");
                    ++resultCount;
                }
            } catch (Exception e) {
                System.out.println("Error when executing logBuildingBuilt()");
                System.out.println(e.getMessage());
            }

            if (resultCount == 0) {
                System.out.println("Error in function logBuildingBuilt(): buildingID does not exist.");
            } else {
                //Modified here
                try {


                    sql = "SELECT * FROM Plot WHERE " + plotID + " = "
                            + "PlotID";
                    resultCount = 0;

                    stmt = con.createStatement();
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        ++resultCount;
                    }
                } catch (Exception e) {
                    System.out.println("Error when executing logBuildingBuilt()");
                    System.out.println(e.getMessage());
                }
                ///////////////

                if (resultCount == 0) {
                    System.out.println("Error in function logBuildingBuilt(): plotID does not exist.");
                } else {
                    StringTokenizer st = new StringTokenizer(date.toString(), " ");
                    st.nextToken();
                    month = st.nextToken();
                    day = st.nextToken();
                    time = st.nextToken();
                    st.nextToken();
                    year = st.nextToken();

                    if (month.equals("Jan")) {
                        month = "01";
                    }
                    if (month.equals("Feb")) {
                        month = "02";
                    }
                    if (month.equals("Mar")) {
                        month = "03";
                    }
                    if (month.equals("Apr")) {
                        month = "04";
                    }
                    if (month.equals("May")) {
                        month = "05";
                    }
                    if (month.equals("Jun")) {
                        month = "06";
                    }
                    if (month.equals("Jul")) {
                        month = "07";
                    }
                    if (month.equals("Aug")) {
                        month = "08";
                    }
                    if (month.equals("Sep")) {
                        month = "09";
                    }
                    if (month.equals("Oct")) {
                        month = "10";
                    }
                    if (month.equals("Nov")) {
                        month = "11";
                    }
                    if (month.equals("Dec")) {
                        month = "12";
                    }

                    st = new StringTokenizer(time, ":");
                    hour = Integer.parseInt(st.nextToken());
                    if (hour < 12) {
                        period = "AM";
                    } else {
                        if (hour != 12) {
                            hour = hour - 12;
                        }
                        period = "PM";
                    }

                    if (hour < 10) {
                        hourS = "0" + hour;
                    } else {
                        hourS = Integer.toString(hour);
                    }

                    time = hourS + ":" + st.nextToken() + ":" + st.nextToken() + " " + period;
                    sql = "INSERT INTO BuildLog(BuildLogCharacterID, BuildLogPlotID, "
                            + " BuildLogBuildingID, BuildLogDateTimeBuilt, "
                            + "BuildLogTimeToComplete, BuildLogCompleted) VALUES "
                            + "(" + characterID + ", " + plotID + ", "
                            + buildingID + ", '" + year + month + day + " " + time + "'"
                            + ", " + ttb + ", 0)";

                    System.out.println(sql);
                    try {
                        stmt = con.createStatement();
                        stmt.execute(sql);
                    } catch (Exception e) {
                        System.out.println("Error when executing logBuildingBuilt()");
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public ArrayList<String[]> getPlotLog(int number, int PlotID) {

        ArrayList<String[]> list = new ArrayList();
        String line[];
        try {

            sql = "SELECT * FROM PlotLog WHERE MONTH(PlotLogDateTime)=" + number + " AND PlotID=" + PlotID;

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                line = new String[3];
                line[0] = rs.getString("PlotID");
                line[1] = rs.getString("PlotLogDateTime");
                line[2] = rs.getString("PlotLogMessage");
                list.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error when executing getPlotLog()");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public ArrayList<String[]> getCharacterLog(int number, int CharaterID) {
        ArrayList<String[]> list = new ArrayList();
        String line[];
        try {

            sql = "SELECT * FROM CharacterLog WHERE MONTH(CharacterLogDateTime)=" + number + " AND CharacterID=" + CharaterID;

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                line = new String[3];
                line[0] = rs.getString("CharacterID");
                line[1] = rs.getString("CharacterLogDateTime");
                line[2] = rs.getString("CharacterLogMessage");
                System.out.println(line[2]);
                list.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error when executing getCharacterLog()");
            System.out.println(e.getMessage());
        }

        return list;
    }

    public void CharacterLog(int characterID, String description) {

      //  description = description.replace(".", " ");
        try {
            stmt = con.createStatement();
            sql = "INSERT INTO CharacterLog(CharacterID,CharacterLogDateTime,CharacterLogMessage) VALUES (" + characterID + ",CONVERT (datetime, SYSDATETIME()),'" + description + "')";
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Error when executing CharacterLog()");
            System.out.println(e.getMessage());
        }
    }

    public void PlotLog(int plotID, String description,int UserID) {
       // description = description.replace(".", " ");
        try {
            stmt = con.createStatement();
            sql = "INSERT INTO PlotLog(PlotID,PlotLogDateTime,PlotLogMessage) VALUES (" + plotID + ",CONVERT (datetime, SYSDATETIME()),'" + description + "')";
            stmt.execute(sql);
        } catch (Exception e) {
            System.out.println("Error when executing PlotLog()");
            System.out.println(e.getMessage());
        }
    }
}
