package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class BuildingQueryHandler {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private String sql = "";
    private ArrayList<String> duchyList, industryList, qualityList;
        
    public BuildingQueryHandler(Connection c)
    {
        super();
        con = c;

        duchyList = new ArrayList();
        industryList = new ArrayList();
        qualityList = new ArrayList();
        
        try
        {
            sql = "SELECT DuchyName FROM Duchy";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                duchyList.add(rs.getString("DuchyName"));
            }
            
            sql = "SELECT BuildingTypeDescription FROM BuildingType";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                industryList.add(rs.getString("BuildingTypeDescription"));
            }
            
            sql = "SELECT QualityDescription FROM Quality";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                qualityList.add(rs.getString("QualityDescription"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String capitalizeFirst(String name)
    {
        String correction = Character.toString(name.charAt(0));
        correction = correction.toUpperCase();
        name = name.replaceFirst(Character.toString(name.charAt(0)), correction);
        return name;
    }
    
    public ArrayList<String> retrieveCompleteBuildingList()
    {
        ArrayList<String> values = null;
        
        values = new ArrayList();
        sql = "SELECT BuildingTypeOfIndustry FROM Building";
        
        try
        {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                values.add(rs.getString("BuildingTypeOfIndustry"));
            }
        }
        catch(Exception e)
        {
            System.out.println("Unable to execute function retrieveBuildingList()");
            e.printStackTrace();
        }

        return values;
    }    
   
    public ArrayList<String[]> listBuildingBy(String duchy, String industry)
    {
        ArrayList<String[]> values = null;
        String[] line;
        
        duchy = duchy.toLowerCase();
        industry = industry.toLowerCase();
        boolean correctDuchy = false, correctIndustry = false;
        
        //Check parameter validity
        for(int a = 0; a < duchyList.size(); a++)
        {
            if(duchyList.get(a).toLowerCase().equals(duchy))
            {
                correctDuchy = true;
            }
        }
        
        for(int b = 0; b < industryList.size(); b++)
        {
            if(industryList.get(b).toLowerCase().equals(industry))
            {
                correctIndustry = true;
            }
        }
        
        if(correctDuchy != true || correctIndustry != true)
        {
            System.out.println("Incorrect parameters supplied to function listBuildingBy()");
        }
        else
        {
            try
            {
                sql = "SELECT * FROM BuildingType";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                values = new ArrayList();

                while(rs.next())
                {
                    line = new String[2];
                    line[0] = rs.getString("BuildingTypeID");
                    line[1] = rs.getString("BuildingTypeDescription");
                    values.add(line);
                }

                int remember = -1;
                for(int a = 0; a < values.size(); a++)
                {
                    for(int b = 0; b < values.get(a).length; b++)
                    {
                        if(values.get(a)[1].toLowerCase().equals(industry))
                        {
                            remember = Integer.parseInt(values.get(a)[0]);
                        }
                    }
                }

                //Ensure first letter of duchy is uppercase
                duchy = capitalizeFirst(duchy);

                sql = "SELECT BuildingAvailabilityID FROM BuildingAvailability WHERE "
                        + "BuildingAvailability" + duchy + " = 1";
                ArrayList<String> regions = new ArrayList();
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                while(rs.next())
                {
                    regions.add(rs.getString("BuildingAvailabilityID"));
                }
                
                sql = "SELECT BuildingID, BuildingTypeOfIndustry FROM Building WHERE BuildingTypeID"
                        + " = " + remember + " AND (BuildingAvailabilityID = " + regions.get(0);

                if(regions.size() > 1)
                {
                    for(int a = 1; a < regions.size(); a++)
                    {
                        sql += " OR BuildingAvailabilityID = " + regions.get(a);
                    }
                }

                sql += ")";


                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                values = new ArrayList();

                while(rs.next())
                {
                    line = new String[2];
                    line[0] = rs.getString("BuildingID");
                    line[1] = rs.getString("BuildingTypeOfIndustry");
                    values.add(line);
                }
            }
            catch(Exception e)
            {
                System.out.println("Unable to execute function listBuildingBy()");
                e.printStackTrace();
            }

            return values;
        }
        
        return null;
    }
    
    public ArrayList<String[]> retrieveBuildingDetailsById(int id)
    {
        ArrayList<String[]> values;
        String[] line;
        String answer = "";
        int count;
        
        //Test if id exists
            
        //Retrieve details
        try
        {
            sql = "SELECT * FROM Building WHERE BuildingID = " + id;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
                
            values = new ArrayList();
            
            while(rs.next())
            {
                line = new String[11];
                line[0] = rs.getString("BuildingTypeID");
                line[1] = rs.getString("BuildingTypeOfIndustry");
                line[2] = rs.getString("BuildingAvailabilityID");
                line[3] = rs.getString("BuildingPrerequisiteID");
                line[4] = rs.getString("BuildingCost");
                line[5] = rs.getString("BuildingSetupCost");
                line[6] = rs.getString("BuildingMonthlyIncome");
                line[7] = rs.getString("BuildingWorkersNeeded");
                line[8] = rs.getString("BuildingTimeToBuild");
                line[9] = rs.getString("BuildingSizeRequired");
                line[10] = rs.getString("BuildingHappiness");
                values.add(line);
            }
            
            for(int a = 0; a < values.size(); a++)
            {
                sql = "SELECT BuildingTypeDescription FROM BuildingType "
                        + "WHERE BuildingTypeID = " + values.get(a)[0];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[0] = rs.getString("BuildingTypeDescription");
                
                sql = "SELECT * FROM BuildingAvailability WHERE "
                        + "BuildingAvailabilityID = " + values.get(a)[2];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                count = 0;
                answer = "";
                
                if(rs.getString("BuildingAvailabilityThegnheim").equals("1"))
                {
                    answer = "Thegnheim";
                    ++count;
                }
                if(rs.getString("BuildingAvailabilitySarkland").equals("1"))
                {
                    if(count > 0)
                    {
                        answer += ",Sarkland";
                    }
                    else
                    {
                        answer += "Sarkland";
                    }
                }
                if(rs.getString("BuildingAvailabilityRagonvaldr").equals("1"))
                {
                    if(count > 0)
                    {
                        answer += ",Ragonvaldr";
                    }
                    else
                    {
                        answer += "Ragonvaldr";
                    }
                }
                if(rs.getString("BuildingAvailabilitySvaerstein").equals("1"))
                {
                    if(count > 0)
                    {
                        answer += ",Svaerstein";
                    }
                    else
                    {
                        answer += "Svaerstein";
                    }
                }
                if(rs.getString("BuildingAvailabilityRotheim").equals("1"))
                {
                    if(count > 0)
                    {
                        answer += ",Rotheim";
                    }
                    else
                    {
                        answer += "Rotheim";
                    }
                }
                if(rs.getString("BuildingAvailabilityLangzerund").equals("1"))
                {
                    if(count > 0)
                    {
                        answer += ",Langzerund";
                    }
                    else
                    {
                        answer += "Langzerund";
                    }
                }
                
                values.get(a)[2] = answer;
                
                sql = "SELECT BuildingPrerequisiteDescription FROM BuildingPrerequisite "
                        + "WHERE BuildingPrerequisiteID = " + values.get(a)[3];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[3] = rs.getString("BuildingPrerequisiteDescription");
            }
            
            return values;
        }
        catch(Exception e)
        {
            System.out.println("Unable to execute function retrieveBuildingDetailsById()");
            e.printStackTrace();
        }
        
        return null;
    }
}
