package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.StringTokenizer;

public class LogQueryHandler {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    
    public LogQueryHandler(Connection c)
    {
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
    public void logBuildingBuilt(int characterID, int buildingID, Date date)
    {
        String day, month, year, hourS, time, period;
        int hour;
        
        StringTokenizer st = new StringTokenizer(date.toString(), " ");
        st.nextToken();
        month = st.nextToken();
        day = st.nextToken();
        time = st.nextToken();
        st.nextToken();
        year = st.nextToken();
        
        if(month.equals("Jan"))
            month = "01";
        if(month.equals("Feb"))
            month = "02";
        if(month.equals("Mar"))
            month = "03";
        if(month.equals("Apr"))
            month = "04";
        if(month.equals("May"))
            month = "05";
        if(month.equals("Jun"))
            month = "06";
        if(month.equals("Jul"))
            month = "07";
        if(month.equals("Aug"))
            month = "08";
        if(month.equals("Sep"))
            month = "09";
        if(month.equals("Oct"))
            month = "10";
        if(month.equals("Nov"))
            month = "11";
        if(month.equals("Dec"))
            month = "12";
        
        st = new StringTokenizer(time, ":");
        hour = Integer.parseInt(st.nextToken());
        if(hour < 12)
        {
            period = "AM";
        }
        else
        {
            if(hour != 12)
                hour = hour - 12;
            period = "PM";
        }
        
        if(hour < 10)
            hourS = "0" + hour;
        else
            hourS = Integer.toString(hour);
        
        time = hourS + ":" + st.nextToken() + ":" + st.nextToken() + " " + period;
        sql = "INSERT INTO BuildLog(BuildLogCharacterID, BuildLogBuildingID, "
                + "BuildLogDateTimeBuilt) VALUES "
                + "(" + characterID + ", " + buildingID + ", '" + year + month + day + " " + time + "')";
        
        System.out.println(sql);
        try
        {
            stmt = con.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e)
        {
            System.out.println("Error when executing logBuildingBuilt()");
            System.out.println(e.getMessage());
        }
    }
}
