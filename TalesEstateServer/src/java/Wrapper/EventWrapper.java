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
            + "/{goldMod}/{silverMod}/{happinessMod}/{incomeMod}/{defenceMod}")
    @Produces("text/plain")
    public String addEvent(@PathParam("plotID")int plotID,@PathParam("eventName") String eventName,@PathParam("eventDescription") String eventDescription,
            @PathParam("platinumMod")int platinumMod,@PathParam("goldMod") int goldMod,@PathParam("silverMod") int silverMod,@PathParam("happinessMod") int happinessMod,
            @PathParam("incomeMod")int incomeMod,@PathParam("defenceMod")int defenceMod ) {
        if(handler.getEventQH().addEvent(plotID, eventName, eventDescription,
                platinumMod, goldMod, silverMod, happinessMod, incomeMod,defenceMod))
            return "true";
        else
            return "false";
    }
    
    @GET
    @Path("getEvent/{month}/{PlotID}")
    @Produces("text/plain")
    public String getEvent(@PathParam("month")int month,@PathParam("PlotID")int PlotID) {
        return converter.ArrToUrl(handler.getEventQH().getEvent(month,PlotID));
    }
}
