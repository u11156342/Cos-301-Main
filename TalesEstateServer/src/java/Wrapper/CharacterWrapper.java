/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import java.util.ArrayList;
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

    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();

    @GET
    @Path("registerEstateCharacter/{characterName}/{userID}")
    @Produces("text/plain")
    public String registerEstateCharacter(@PathParam("characterName") String characterName, @PathParam("userID") String userID) {

        characterName = characterName.replace('.', ' ');
        if (handler.getCharacterQH().registerEstateCharacter(characterName, userID)) {
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
    @Path("retrieveCharacterIDExtra/{characterName}")
    @Produces("text/plain")
    public String retrieveCharacterIDExtra(@PathParam("characterName") String userName) {
        userName = userName.replace('.', ' ');
        return converter.ArrToUrl(handler.getCharacterQH().retrieveCharacterIDExtra(userName));
    }

    @GET
    @Path("retrieveAllCharacters")
    @Produces("text/plain")
    public String retrieveAllCharacters() {
        return converter.ArrToUrl(handler.getCharacterQH().retrieveAllCharacters());
    }

    @GET
    @Path("getCharacterAmounts/{characterName}")
    @Produces("text/plain")
    public String getCharacterAmounts(@PathParam("characterName") String characterName) {
        characterName = characterName.replace('.', ' ');
        return converter.ToUrl(handler.getCharacterQH().getCharacterAmounts(characterName));
    }

    @GET
    @Path("modifyAmount/{characterName}/{amountPlatinum}/{amountGold}/{amountSilver}")
    @Produces("text/plain")
    public String modifyAmount(@PathParam("characterName") String characterName, @PathParam("amountPlatinum") int amountPlatinum, @PathParam("amountGold") int amountGold, @PathParam("amountSilver") int amountSilver) {
        characterName = characterName.replace('.', ' ');
        if (handler.getCharacterQH().modifyAmount(characterName, amountPlatinum, amountGold, amountSilver)) {
            return "true";
        } else {
            return "";
        }
    }

    @GET
    @Path("retrieveCharacterName/{characterID}")
    @Produces("text/plain")
    public String retrieveCharacterName(@PathParam("characterID") int characterID) {
        String temp = handler.getCharacterQH().retrieveCharacterName(characterID);
        temp = temp.replace(' ', '.');
        return temp;
    }

    @GET
    @Path("isAdmin/{userID}")
    @Produces("text/plain")
    public String isAdmin(@PathParam("userID") String userID) {
        if (handler.getCharacterQH().isAdmin(userID)) {
            return "true";
        } else {
            return "false";
        }

    }
}
