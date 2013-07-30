package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DuchyQueryHandler {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    
    public DuchyQueryHandler(Connection c)
    {
        super();
        con =c;
    }
    
    /* This function retrieves a list of all the duchies.
     */
    public ArrayList<String> retrieveDuchyList()
    {
        ArrayList<String> values = null;
        
        values = new ArrayList();
        
        try
        {
            sql = "SELECT * FROM Duchy";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                values.add(rs.getString("DuchyName"));
            }
            
            return values;
        }
        catch(Exception e)
        {
            System.out.println("Could not execute function retrieveDuchyList()");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
