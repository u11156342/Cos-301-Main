package Wrapper;

import QueryHandlers.QueryHandler;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/EventWrapper")
public class EventWrapper {
    QueryHandler handler = new QueryHandler(0);
    Converter converter = new Converter();
    
    @GET
    @Path("addEvent/{plotID}/{eventName}/{eventDescription}/{platinumMod}"
            + "/{goldMod}/{silverMod}/{happinessMod}/{incomeMod}")
    @Produces("text/plain")
    public String addEvent(int plotID, String eventName, String eventDescription,
            int platinumMod, int goldMod, int silverMod, int happinessMod,
            int incomeMod) {
        if(handler.getEventQH().addEvent(plotID, eventName, eventDescription,
                platinumMod, goldMod, silverMod, happinessMod, incomeMod))
            return "true";
        else
            return "false";
    }
    
    @GET
    @Path("getEvent/{month}")
    @Produces("text/plain")
    public String getEvent(int month) {
        return converter.ArrToUrl(handler.getEventQH().getEvent(month));
    }
}
