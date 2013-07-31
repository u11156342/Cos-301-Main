/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Vincent
 */
public class CharacterQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionEstate();
    
    public CharacterQueryHandlerTest(String testName) {
        super(testName);
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
     * *Note: since we do not want to add unnecessary characters to the database,
     * thus function will remain unimplemented for now.
     */
    public void testRegisterEstateCharacter() {
        //System.out.println("Testing registerEstateCharacter()");
        //String characterName = "";
        //CharacterQueryHandler instance = new CharacterQueryHandler();
    }

    /**
     * Test of retrieveCharacterID method, of class CharacterQueryHandler.
     */
    public void testRetrieveCharacterID() {
        System.out.println("Testing retrieveCharacterID()");
        CharacterQueryHandler instance = new CharacterQueryHandler(con);
        String characterName = "abcharacter";
        int result = instance.retrieveCharacterID(characterName);
        
        int expResult = 4;
        
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
        
        //One of the results that should be listed
        String[] line = new String[2];
        line[0] = "3";
        line[1] = "Gilthana";
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
}
