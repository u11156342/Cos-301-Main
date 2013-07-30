package QueryHandlers;

import Connection.DatabaseConnection;
import java.sql.Connection;

public class QueryHandler {
    private DatabaseConnection db = null;
    private Connection conEstate = null;    //Connection to Estate database
    private Connection conProd = null;      //Connection to TalesProd database
    
    public BuildingQueryHandler bqh;
    public CharacterQueryHandler cqh;
    public DuchyQueryHandler dqh; 
    public PlotQueryHandler pqh;
    public PriceQueryHandler prqh;
    public UserQueryHandler uqh;
    public PictureQueryHandler Picqh;

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
        uqh = new UserQueryHandler(conProd);
    }
    
    public void closeConnection() {
        db.closeConnection();
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
