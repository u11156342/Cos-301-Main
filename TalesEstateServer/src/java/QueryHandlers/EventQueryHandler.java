package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EventQueryHandler {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    
    public EventQueryHandler(Connection c) {
        super();
        con = c;
    }
    
    /* This function adds an event that will trigger at the end of the
     * month inserted.
     * The Modifiers, with suffix 'Mod', adds to, or substracts from, the
     * 'prefix-element'. I.e.
     * If a certain plot has 50 gold, and an event modifies it with the
     * following: -20, then what remains will be 30 gold. (the same can be said
     * for (+)20; etc.
     */
    public boolean addEvent(int plotID, String eventName, String eventDescription,
            int platinumMod, int goldMod, int silverMod, int happinessMod,
            int incomeMod) {
        
        sql = "INSERT INTO EventLog (PlotID, EventLogName, EventLogDescription"
                + ", EventLogDateAdded, EventLogEffectPlatinum, EventLogEffectGold, "
                + "EventLogEffectSilver, EventLogEffectHappiness, EventLogEffectIncome) "
                + "VALUES("
                + plotID + ", "
                + "'" + eventName + "', "
                + "'" + eventDescription + "', "
                + "GETDATE(), "
                + platinumMod + ", "
                + goldMod + ", "
                + silverMod + ", "
                + happinessMod + ", "
                + incomeMod + ")";
                
        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            
            return true;
        }
        catch(Exception e) {
            System.out.println("Error in function addEvent():");
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    /* This function returns all events for the given month.
     * 
     */
    public ArrayList<String[]> getEvent(int month,int PlotID) {
        ArrayList<String[]> results = new ArrayList();
        String[] result = null;
        boolean added = false;
        
        sql = "SELECT * FROM EventLog WHERE "
                + "MONTH(EventLogDateAdded) = " + month
                + " AND  PlotID= " + PlotID;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                added = true;  //at least 1 result was added
                
                result = new String[10];
                result[0] = rs.getString("EventLogID");
                result[1] = rs.getString("PlotID");
                result[2] = rs.getString("EventLogName");
                result[3] = rs.getString("EventLogDescription");
                result[4] = rs.getString("EventLogDateAdded");
                result[5] = rs.getString("EventLogEffectPlatinum");
                result[6] = rs.getString("EventLogEffectGold");
                result[7] = rs.getString("EventLogEffectSilver");
                result[8] = rs.getString("EventLogEffectHappiness");
                result[9] = rs.getString("EventLogEffectIncome");
                results.add(result);
            }
            
            if(added)
                return results;
        }
        catch(Exception e) {
            System.out.println("Error in function getEvent():");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
