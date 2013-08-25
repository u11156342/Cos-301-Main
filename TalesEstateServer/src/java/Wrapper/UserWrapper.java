/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author NightFiyah
 */
@Stateless
@Path("/UserWrapper")
public class UserWrapper {

    QueryHandler handler = handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("checkLogin/{userID}")
    @Produces("text/plain")
    public String checkLogin(@PathParam("userID") String userID) {
        if (handler.getUserQH().checkLogin(userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("checkHasCharacter/{userID}")
    @Produces("text/plain")
    public String checkHasCharacter(@PathParam("userID") String userID) {
        if (handler.getUserQH().checkHasCharacter(userID)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrieveCharactersOwnedByUser/{userID}")
    @Produces("text/plain")
    public String retrieveCharactersOwnedByUser(@PathParam("userID") String userID) {
        return converter.ToUrl(handler.getUserQH().retrieveCharactersOwnedByUser(userID));
    }
}
