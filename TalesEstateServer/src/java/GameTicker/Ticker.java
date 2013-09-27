/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTicker;

import Connection.DatabaseConnection;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;
import javax.ejb.Stateless;

/**
 *
 * @author Fiyah
 */
@Stateless
public class Ticker {

    // ok so this is the class that will run through each of the things in the database and update them according
    private Connection con = null;
    private DatabaseConnection dbcon=new DatabaseConnection();
    private Tick tick = new Tick();

    public Ticker() {
        System.out.println("-----------------------------GAME TICKER ONLINE-------------------------------------");
        // connection to db
        con=dbcon.openConnectionEstate();
        
        Timer timer = new Timer();

        // checks every 10 sec for now

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick.pulse(con);
            }
        }, 0, 10 * 1000);
    }
}
