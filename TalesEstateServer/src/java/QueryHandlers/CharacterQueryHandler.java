package QueryHandlers;

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
        super();
        con = c;
    }    
    
    /* This function registers a certain character to the estate system database.
     * The function returns true if a character has been successfully added, and
     * false if not.
     */
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
            System.out.println(e.getMessage());
        }
        
        return false;
    }
    
    /* This function returns a certain character's ID based on the character
     * name supplied.
     */ 
    public int retrieveCharacterID(String characterName)
    {
        System.out.println(characterName);
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
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
     /* This function returns a list of all the characters registered to the
     * estate system database.
     */ 
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
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
