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
@Path("/CharacterWrapper")
public class CharacterWrapper {

    QueryHandler handler = handler = new QueryHandler();
    Converter converter = new Converter();
      @GET
    @Path("registerEstateCharacter/{characterName}")
    @Produces("text/plain")
    public String registerEstateCharacter(@PathParam("characterName") String characterName) {

        characterName = characterName.replace('.', ' ');
        if (handler.getCharacterQH().registerEstateCharacter(characterName)) {
            return "true";
        } else {
            return "false";
        }
    }

    @GET
    @Path("retrieveCharacterID/{userID}")
    @Produces("text/plain")
    public String retrieveCharacterID(@PathParam("userID") String userName) {
        userName = userName.replace('.', ' ');
        return "" + handler.getCharacterQH().retrieveCharacterID(userName);
    }

    @GET
    @Path("retrieveAllCharacters")
    @Produces("text/plain")
    public String retrieveAllCharacters() {
        return converter.ArrToUrl(handler.getCharacterQH().retrieveAllCharacters());
    }
}
