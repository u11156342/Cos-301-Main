/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase.QueryHandlers;

import DataBase.DBConnection.DatabaseConnection;
import java.sql.Connection;

/**
 *
 * @author NightFiyah
 */
public class QueryHandler {

    private DatabaseConnection db = null;
    private Connection con1 = null;
    private Connection con2 = null;
    
    public BuildingQueryHandler bqh;
    public CharacterQueryHandler cqh;
    public DuchyQueryHandler dqh; 
    public PlotQueryHandler pqh;
    public PriceQueryHandler prqh;
    public UserQueryHandler uqh;

    public QueryHandler() {
        db = new DatabaseConnection();
        con1 =db.openConnectionEstate();
        con2 =db.openConnectionProd();
        
        bqh=new BuildingQueryHandler(con1);
        cqh=new CharacterQueryHandler(con1);
        dqh=new DuchyQueryHandler(con1);
        pqh=new PlotQueryHandler(con1);
        prqh=new PriceQueryHandler(con1);
        uqh=new UserQueryHandler(con2);


    }
      public void closeConnection() {
        db.closeConnection();
    }    
}
