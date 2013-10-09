/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import QueryHandlers.QueryHandler;
import QueryHandlers.requeryhandler;
import QueryHandlers.requeryhandler;
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
@Path("/mobilereq")
public class mobilereq {

    @GET
    @Path("getdetails/{userID}")
    @Produces("text/plain")
    public String checkLogin(@PathParam("userID") String userID) {
        try {
            requeryhandler handler = new requeryhandler();
            return handler.getid(userID);
        } catch (SQLException ex) {
            Logger.getLogger(mobilereq.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(mobilereq.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mobilereq.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
