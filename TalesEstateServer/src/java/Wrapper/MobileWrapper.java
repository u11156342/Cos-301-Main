/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import QueryHandlers.MobileQueryHandler;
import QueryHandlers.MobileQueryHandler;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author User
 */
@Stateless
@Path("/MobileWrapper")
public class MobileWrapper {

    @GET
    @Path("getdetails/{userName}")
    @Produces("text/html")
    public String checkLogin(@PathParam("userName") String userName) {
        //   userName = userName.replace(".", " ");
        try {
            MobileQueryHandler handler = new MobileQueryHandler();
            return handler.getnames(userName);
        } catch (Exception ex) {
        }
        return "error has occured";

    }
}
