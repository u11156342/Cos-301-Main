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
        int amountID;
        try
        {
            //Check if character is already registered to the Estate system
            sql = "SELECT * FROM UserCharacter "
                    + "WHERE Convert(VARCHAR(255), UserCharacterName) = '" + characterName + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
 

            if(!rs.next())
            {
                sql = "INSERT INTO Amount VALUES(0,0,0);";
                stmt = con.createStatement();
                stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);

                rs = stmt.getGeneratedKeys();
                rs.next();
                amountID = rs.getInt(1);
                
                sql = "INSERT INTO UserCharacter (UserCharacterName, UserCharacterAmount) "
                        + "VALUES ('" + characterName + "', " + amountID + ")";
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
    
    public ArrayList<String> getCharacterAmounts(String characterName)
    {
        ArrayList<String> result = new ArrayList();
        int amountID = 0;
        
        sql = "SELECT UserCharacterAmount FROM UserCharacter WHERE "
                + "UserCharacterName = '" + characterName + "'";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("UserCharacterID"));
            
            sql = "SELECT AmountPlatinum, AmountGold, AmountSilver FROM "
                    + "Amount WHERE AmountID = " + amountID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            result.add(rs.getString("AmountPlatinum"));
            result.add(rs.getString("AmountGold"));
            result.add(rs.getString("AmountSilver"));
            
            return result;
        }
        catch(Exception e)
        {
            System.out.println("Error in CharacterQueryHandler, function getCharacterAmounts()");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public boolean modifyAmount(String characterName, int amountPlatinum, int amountGold, int amountSilver)
    {
        int amountID;
        int curPlat, curGold, curSilv;
        
        sql = "SELECT UserCharacterAmount FROM UserCharacter WHERE "
                + "UserCharacterName = '" + characterName + "'";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("UserCharacterAmount"));
            
            sql = "UPDATE Amount SET "
                    + "AmountPlatinum = " + amountPlatinum
                    + " AmountGold = " + amountGold
                    + " AmountSilver = " + amountSilver
                    + " WHERE AmountID = " + amountID;
            stmt = con.createStatement();
            stmt.execute(sql);
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error in CharacterQueryHandler, function modifyAmount()");
        }
        
        return false;
    }
    
    public boolean depositAmount(String characterName, int amountPlatinum, int amountGold, int amountSilver)
    {
        int amountID;
        int curPlat, curGold, curSilv;
        
        sql = "SELECT UserCharacterAmount FROM UserCharacter WHERE "
                + "UserCharacterName = '" + characterName + "'";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("UserCharacterAmount"));
            
            sql = "SELECT AmountPlatinum, AmountGold, AmountSilver FROM Amount "
                    + "WHERE AmountID = " + amountID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            curPlat = Integer.parseInt(rs.getString("AmountPlatinum"));
            curGold = Integer.parseInt(rs.getString("AmountGold"));
            curSilv = Integer.parseInt(rs.getString("AmountSilver"));
            
            sql = "UPDATE Amount SET "
                    + "AmountPlatinum = " + (curPlat + amountPlatinum)
                    + " AmountGold = " + (curGold + amountGold)
                    + " AmountSilver = " + (curSilv + amountSilver)
                    + " WHERE AmountID = " + amountID;
            stmt = con.createStatement();
            stmt.execute(sql);
            
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error in CharacterQueryHandler, function modifyAmount()");
        }
        
        return false;
    }
    
    public boolean withdrawAmount(String characterName, int amountPlatinum, int amountGold, int amountSilver)
    {
        int amountID;
        int curPlat, curGold, curSilv;
        
        sql = "SELECT UserCharacterAmount FROM UserCharacter WHERE "
                + "UserCharacterName = '" + characterName + "'";
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("UserCharacterAmount"));
            
            sql = "SELECT AmountPlatinum, AmountGold, AmountSilver FROM Amount "
                    + "WHERE AmountID = " + amountID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            curPlat = Integer.parseInt(rs.getString("AmountPlatinum"));
            curGold = Integer.parseInt(rs.getString("AmountGold"));
            curSilv = Integer.parseInt(rs.getString("AmountSilver"));
            
            if((curPlat - amountPlatinum) < 0)
                System.out.println("Character does not have enough platinum.");
            else if((curGold - amountGold) < 0)
                System.out.println("Character does not have enough gold.");
            else if((curSilv - amountSilver) < 0)
                System.out.println("Character does not have enough silver.");
            else
            {
                sql = "UPDATE Amount SET "
                        + "AmountPlatinum = " + (curPlat - amountPlatinum)
                        + " AmountGold = " + (curGold - amountGold)
                        + " AmountSilver = " + (curSilv - amountSilver)
                        + " WHERE AmountID = " + amountID;
                stmt = con.createStatement();
                stmt.execute(sql);

                return true;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in CharacterQueryHandler, function modifyAmount()");
        }
        
        return false;
    }
}
