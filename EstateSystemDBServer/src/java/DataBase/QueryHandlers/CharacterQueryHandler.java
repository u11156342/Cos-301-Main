package DataBase.QueryHandlers;

import DataBase.DBConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class CharacterQueryHandler {
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private String sql = "";
    
    public CharacterQueryHandler(Connection c)
    {
        con = c;
    }    
    public boolean registerEstateCharacter(String characterName)
    {
        try
        {
            //Check if character is already registered to the Estate system
            sql = "SELECT * FROM UserCharacter "
                    + "WHERE Convert(VARCHAR(255), UserCharacterName) = '" + characterName + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
 

            if(!rs.next())
            {
                sql = "INSERT INTO UserCharacter (UserCharacterName) "
                        + "VALUES ('" + characterName + "')";
                stmt = con.createStatement();
                stmt.execute(sql);
                return true;
            }
            else
            {
                return false;
            }
                
        }
        catch(Exception e)
        {
            System.out.println("Could not execute function registerEstateCharacter()");
            e.printStackTrace();
        }
        
        return false;
    }
    
    public int retrieveCharacterID(String characterName)
    {
        try
        {
            sql = "SELECT UserCharacterID FROM UserCharacter WHERE "
                    + "UserCharacterName = '" + characterName + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            return Integer.parseInt(rs.getString("UserCharacterID"));
        }
        catch(Exception e)
        {
            System.out.println("Could not execute function retrieveCharacterID()");
            e.printStackTrace();
        }
        return 0;
    }
    
   
    public ArrayList<String[]> retrieveAllCharacters()
    {
        ArrayList<String[]> values = null;
        String[] line = null;
        
        values = new ArrayList();
        
        try
        {
            sql = "SELECT * FROM UserCharacter";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                line = new String[2];
                line[0] = rs.getString("UserCharacterID");
                line[1] = rs.getString("UserCharacterName");
                values.add(line);
            }
            
            return values;
        }
        catch(Exception e)
        {
            System.out.println("Could not execute function retrieveAllCharacters()");
            e.printStackTrace();
        }
        
        return null;
    }
}
