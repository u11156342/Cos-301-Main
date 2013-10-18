/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class CharacterQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    //Global test variables
    Statement stmt = null;
    ResultSet rs = null;
    private int testCharID = 0;
    
    public CharacterQueryHandlerTest(String testName) {
        super(testName);
        
        //Initialize test variables
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM UserCharacter WHERE "
                    + "UserCharacterName LIKE 'test character%'");
            rs.next();
            testCharID = Integer.parseInt(rs.getString("UserCharacterID"));
        }
        catch(Exception e) {
            System.out.println("Error in BuildingQueryHandlerTEST constructor");
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of registerEstateCharacter method, of class CharacterQueryHandler.
     * 
     * **NOTE: This test would involve inserting and deleting data from the
     * client's database. As we only want to test our database, this test is
     * not needed.
     */
    public void testRegisterEstateCharacter() {
        /*System.out.println("Testing registerEstateCharacter()");
        
        String characterName = "test2character";
        String userID = "T35T-ID";
        CharacterQueryHandler handler = new CharacterQueryHandler(con);
        
        handler.registerEstateCharacter(characterName, userID);
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM UserCharacter WHERE "
                    + "UserCharacterName LIKE 'test2character%'");
            rs.next();
            
        }
        catch(Exception e) {
            System.out.println("Error in testRegisterEstateCharacter()");
            System.out.println(e.getMessage());
        }
        */
    }

    /**
     * Test of retrieveCharacterID method, of class CharacterQueryHandler.
     */
    public void testRetrieveCharacterID() {
        System.out.println("Testing retrieveCharacterID()");
        
        CharacterQueryHandler instance = new CharacterQueryHandler(con);
        String characterName = "test character";
        
        int result = instance.retrieveCharacterID(characterName);
        int expResult = testCharID;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveAllCharacters method, of class CharacterQueryHandler.
     */
    public void testRetrieveAllCharacters() {
        System.out.println("Testing retrieveAllCharacters()");
        
        CharacterQueryHandler instance = new CharacterQueryHandler(con);
        ArrayList<String[]> result = instance.retrieveAllCharacters();
        
        ArrayList<String[]> expResult = new ArrayList();
        
        //One of the results should be the test character inserted into the
        //database
        String[] line = new String[2];
        line[0] = Integer.toString(testCharID);
        line[1] = "test character&*&" + testCharID;
        expResult.add(line);
        
        boolean correct1 = false,
                correct2 = false;
        for(int a = 0; a < result.size(); a++)
        {
            for(int b = 0; b < result.get(a).length; b++)
            {
                if(result.get(a)[b].equals(expResult.get(0)[0]));
                    correct1 = true;
                if(result.get(a)[b].equals(expResult.get(0)[1]))
                    correct2 = true;
            }
        }
        
        assertTrue(correct1);
        assertTrue(correct2);
    }

    /**
     * Test of retrieveCharacterName method, of class CharacterQueryHandler.
     */
    public void testRetrieveCharacterName() {
        System.out.println("Testing retrieveCharacterName()");
        
        int characterID = testCharID;
        CharacterQueryHandler instance = new CharacterQueryHandler(con);
        
        String expResult = "test character&*&" + testCharID;
        String result = instance.retrieveCharacterName(characterID);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveCharacterIDExtra method, of class CharacterQueryHandler.
     */
    public void testRetrieveCharacterIDExtra() {
        System.out.println("Testing retrieveCharacterIDExtra()");
        
        String characterName = "test character&*&" + testCharID;
        CharacterQueryHandler instance = new CharacterQueryHandler(con);
        boolean match = false;
        
        ArrayList<String[]> result = instance.retrieveCharacterIDExtra(characterName);
        if(result.get(0)[0].equals(Integer.toString(testCharID)) &&
                result.get(0)[1].equals(characterName))
                match = true;
        
        assertTrue(match);
    }

    /**
     * Test of getCharacterAmounts method, of class CharacterQueryHandler.
     * 
     * **NOTE: This test would involve inserting and deleting data from the
     * client's database. As we only want to test our database, this test is
     * not needed.
     */
    public void testGetCharacterAmounts() {
        /*System.out.println("Testing getCharacterAmounts()");
         */
    }

    /**
     * Test of modifyAmount method, of class CharacterQueryHandler.
     * 
     * **NOTE: This test would involve inserting and deleting data from the
     * client's database. As we only want to test our database, this test is
     * not needed.
     */
    public void testModifyAmount() {
        /*System.out.println("Testing modifyAmount()");
         */
    }

    /**
     * Test of depositAmount method, of class CharacterQueryHandler.
     * 
     * **NOTE: This test would involve inserting and deleting data from the
     * client's database. As we only want to test our database, this test is
     * not needed.
     */
    public void testDepositAmount() {
        /*System.out.println("Testing depositAmount()");
        */
    }

    /**
     * Test of withdrawAmount method, of class CharacterQueryHandler.
     * 
     * **NOTE: This test would involve inserting and deleting data from the
     * client's database. As we only want to test our database, this test is
     * not needed.
     */
    public void testWithdrawAmount() {
        /*System.out.println("Testing withdrawAmount()");
        */
    }

    /**
     * Test of modifyStatus method, of class CharacterQueryHandler.
     */
    public void testModifyStatus() {
        System.out.println("Testing modifyStatus()");
        
        int characterID = testCharID;
        int statusAmount = 5;
        String expResult = "";
        CharacterQueryHandler instance = new CharacterQueryHandler(con);
        
        boolean result = instance.modifyStatus(characterID, statusAmount);
        if(result) {
            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM UserCharacter WHERE "
                        + "UserCharacterID = " + characterID);
                rs.next();
                expResult = rs.getString("UserCharacterStatus");
            }
            catch(Exception e) {
                System.out.println("Error in testModifyStatus()");
                System.out.println(e.getMessage());
            }
        }
        assertEquals(expResult, "5");
    }
}
