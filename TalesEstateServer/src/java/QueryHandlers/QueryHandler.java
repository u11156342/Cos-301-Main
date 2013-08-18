package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;

public class QueryHandler {
    private DatabaseConnection db = null;
    private Connection conEstate = null;    //Connection to Estate database
    private Connection conProd = null;      //Connection to TalesProd database
    
    private BuildingQueryHandler bqh;
    private CharacterQueryHandler cqh;
    private DuchyQueryHandler dqh; 
    private PlotQueryHandler pqh;
    private PriceQueryHandler prqh;
    private UserQueryHandler uqh;
    private PictureQueryHandler Picqh;
    private LogQueryHandler lqh;

    public QueryHandler() {
        db = new DatabaseConnection();
        conEstate = db.openConnectionEstate();
        conProd = db.openConnectionProd();
 
        bqh = new BuildingQueryHandler(conEstate);
        cqh = new CharacterQueryHandler(conEstate);
        dqh = new DuchyQueryHandler(conEstate);
        pqh = new PlotQueryHandler(conEstate);
        prqh = new PriceQueryHandler(conEstate);
        Picqh = new PictureQueryHandler(conEstate);
        lqh=new LogQueryHandler(conEstate);
        uqh = new UserQueryHandler(conProd);      
     
    }    
    public void closeConnection() {
        db.closeConnection();
    }
    
    public LogQueryHandler getLogQH()
    {
        return lqh;
    }
    
    public BuildingQueryHandler getBuildingQH()
    {
        return bqh;
    }
    
    public CharacterQueryHandler getCharacterQH()
    {
        return cqh;
    }
    
    public DuchyQueryHandler getDuchyQH()
    {
        return dqh;
    }
    
    public PlotQueryHandler getPlotQH()
    {
        return pqh;
    }
    
    public PriceQueryHandler getPriceQH()
    {
        return prqh;
    }
    
    public PictureQueryHandler getPictureQH()
    {
        return Picqh;
    }
    
    public UserQueryHandler getUserQH()
    {
        return uqh;
    }
}
