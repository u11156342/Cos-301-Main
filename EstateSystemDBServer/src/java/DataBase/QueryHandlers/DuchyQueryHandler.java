package DataBase.QueryHandlers;

import DataBase.DBConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class DuchyQueryHandler {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private String sql = "";
    
    public DuchyQueryHandler(Connection c)
    {
        con =c;
    }
    
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
            e.printStackTrace();
        }
        
        return null;
    }
}
