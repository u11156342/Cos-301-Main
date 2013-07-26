package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;


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
    public PictureQueryHandler Picqh;

    public QueryHandler() {
        db = new DatabaseConnection();
        con1 =db.openConnectionEstate();
        con2 =db.openConnectionProd();
        
        bqh=new BuildingQueryHandler(con1);
        cqh=new CharacterQueryHandler(con1);
        dqh=new DuchyQueryHandler(con1);
        pqh=new PlotQueryHandler(con1);
        prqh=new PriceQueryHandler(con1);
        Picqh=new PictureQueryHandler(con1);
        uqh=new UserQueryHandler(con2);
    }
      public void closeConnection() {
        db.closeConnection();
    }    
}
