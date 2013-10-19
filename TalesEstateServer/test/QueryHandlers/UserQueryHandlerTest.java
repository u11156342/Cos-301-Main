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
public class UserQueryHandlerTest extends TestCase {
    private DatabaseConnection db = new DatabaseConnection();
    private Connection con = db.openConnectionProd();
    
    public UserQueryHandlerTest(String testName) {
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
     * Test of closeConnection method, of class UserQueryHandler.
     */
    public void testCloseConnection() {
        //System.out.println("Testing closeConnection()");
    }

    /**
     * Test of printUserList method, of class UserQueryHandler.
     * 
     * *Note: this function has no return value, but simply prints out all
     * user ID's and will thus not need to be tested.
     */
    public void testPrintUserList() {
        //System.out.println("Testing printUserList()");
    }

    /**
     * Test of checkLogin method, of class UserQueryHandler.
     */
    public void testCheckLogin() {
        System.out.println("Testing checkLogin()");
        UserQueryHandler instance = new UserQueryHandler(con);
        String userID = "50EA8CFF-E433-4454-9CC1-FB46CC39AA75";
        boolean result = instance.checkLogin(userID);
        
        boolean expResult = false;
        
        assertEquals(true, result);
    }

    /**
     * Test of checkHasCharacter method, of class UserQueryHandler.
     * 
     * *Note: a correct response can be true, or false, but we will test a
     * true result in this case.
     */
    public void testCheckHasCharacter() {
        System.out.println("Testing checkHasCharacter()");
        UserQueryHandler instance = new UserQueryHandler(con);
        String userID = "E42C254A-679A-497B-B4F4-00B198A5F7A3";
        boolean result = instance.checkHasCharacter(userID);
        
        boolean expResult = true;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of retrieveCharactersOwnedByUser method, of class UserQueryHandler.
     */
    public void testRetrieveCharactersOwnedByUser() {
        System.out.println("Testing retrieveCharactersOwnedByUser()");
        UserQueryHandler instance = new UserQueryHandler(con);
        String userID = "E42C254A-679A-497B-B4F4-00B198A5F7A3";
        ArrayList<String> result = instance.retrieveCharactersOwnedByUser(userID);
        
        ArrayList<String> expResult = new ArrayList();
        expResult.add("Sebastion");
        
        boolean correct = false;
        if(result.get(0).equals(expResult.get(0)))
            correct = true;
        
        assertEquals(true, correct);
    }
    
    public void testGetCharacterSilver() {
        System.out.println("Testing getCharacterSilver()");
        UserQueryHandler instance = new UserQueryHandler(con);
        String charID = "68E92453-C8DD-4CD6-BA01-7CDCC020B7BB";
        int result = instance.getCharacterSilver(charID);
        
        assertEquals(85, result);
    }

    /**
     * Test of setCharacterSilver method, of class UserQueryHandler.
     * 
     * **Note: This test would involve changing values on the client's database.
     * As we only want to test the estate database, this function test will re-
     * main unimplemented.
     */
    public void testSetCharacterSilver() {
        /*System.out.println("Testing setCharacterSilver()");
         */
    }

    /**
     * Test of getCharacterName method, of class UserQueryHandler.
     * 
     * **Note: This test would involve changing values on the client's database.
     * As we only want to test the estate database, this function test will re-
     * main unimplemented.
     */
    public void testGetCharacterName() {
        /*System.out.println("Testing getCharacterName()");
         */
    }
}
