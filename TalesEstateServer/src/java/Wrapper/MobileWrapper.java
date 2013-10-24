package Wrapper;

import QueryHandlers.MobileQueryHandler;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Stateless
@Path("/MobileWrapper")
public class MobileWrapper {

    @GET
    @Path("getdetails/{userName}")
    @Produces("text/html")
    public String getdetails(@PathParam("userName") String userName) {
        //   userName = userName.replace(".", " ");
        try {
            MobileQueryHandler handler = new MobileQueryHandler();
            return handler.getdetails(userName);
        } catch (Exception ex) {
        }
        return "error has occured";

    }

    @GET
    @Path("getnames/{userid}")
    @Produces("text/html")
    public String getnames(@PathParam("userid") String userid) {
        //   userName = userName.replace(".", " ");
        try {
            MobileQueryHandler handler = new MobileQueryHandler();
            return handler.getnames(userid);
        } catch (Exception ex) {
        }
        return "error has occured";

    }
}
