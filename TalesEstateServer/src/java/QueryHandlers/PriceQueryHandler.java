package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class PriceQueryHandler {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private String sql = "";
    
    public PriceQueryHandler(Connection c)
    {
        super();

        con = c;
    }
    
        
    public ArrayList<String> retrieveMonthlyUpkeep(String duchy, String quality)
    {
        ArrayList<String> values = null;
        String line = "";
        
        values = new ArrayList();
        try
        {
            duchy = duchy.toLowerCase();
            quality = quality.toLowerCase();
            
            //Get duchy and qualit ID's
            sql = "SELECT DuchyID FROM Duchy WHERE "
                    + "LOWER(DuchyName) = '" + duchy.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            duchy = rs.getString("DuchyID");
            
            sql = "SELECT QualityID FROM Quality WHERE "
                    + "LOWER(QualityDescription) = '" + quality.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            quality = rs.getString("QualityID");
            
            //Get monthly upkeep
            sql = "SELECT PriceMonthlyUpkeep FROM Price WHERE "
                    + "DuchyID = " + duchy + " AND "
                    + "QualityID = " + quality;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            line = rs.getString("PriceMonthlyUpkeep");
            
            sql = "SELECT * FROM Amount WHERE "
                    + "AmountID = " + line;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            values.add(rs.getString("AmountPlatinum"));
            values.add(rs.getString("AmountGold"));
            values.add(rs.getString("AmountSilver"));
            
            return values;
        }
        catch(Exception e)
        {
            System.out.println("Could not execute function retrieveMonthlyUpkeep()");
            e.printStackTrace();
        }
        
        return values;
    }
}
