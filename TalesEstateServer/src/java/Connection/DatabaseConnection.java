package Connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseConnection {

    private Connection con = null;  //Connection used by all functions
    private Driver d = null;
    private String connectionURL = "";
    private BufferedReader reader;
    private String ServerIP="localhost";

    public DatabaseConnection() {
        try {
            reader = new BufferedReader(new FileReader("ServerConfig.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
               // System.out.println(line);
                if (line.contains("databaseIP")) {
                    ServerIP = line.substring(line.indexOf("=") + 1, line.length());
                }
            }

            //System.out.println(folderLocation);
        } catch (Exception ex) {
        }
      //  System.out.println(ServerIP);
    }

    public Connection openConnectionProd() {     
        try {
            connectionURL = "jdbc:sqlserver://" + ServerIP + ":1433;databaseName=TalesProd;integratedSecurity=true";
            d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = d.connect(connectionURL, new Properties());
        } catch (Exception e) {
            System.out.println("Could not execute function openConnectionProd()");
            e.printStackTrace();
        }

        return con;
    }

    public Connection openConnectionEstate() {

        try {
            connectionURL = "jdbc:sqlserver://" + ServerIP + ":1433;databaseName=MarieEstate;integratedSecurity=true";
            d = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = d.connect(connectionURL, new Properties());
        } catch (Exception e) {
            System.out.println("Could not execute function openConnectionEstate()");
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Unable to close connection.");
            }
        }
    }

    /*
     * Returns an ArrayList of String[] containing all the results of the
     * provided query.
     */
    public ArrayList<String[]> executeQuery(String inSQL) {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            //Execute query & get results
            stmt = con.createStatement();
            rs = stmt.executeQuery(inSQL);
            rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();

            //Get column names
            String[] columnNames = new String[count];
            for (int a = 0; a < count; a++) {
                columnNames[a] = rsmd.getColumnName(a + 1);
            }

            //Add results to ArrayList
            ArrayList<String[]> results = new ArrayList();
            String[] item;

            //Iterate through results
            while (rs.next()) {
                item = new String[count];
                for (int a = 0; a < count; a++) {
                    item[a] = rs.getString(columnNames[a]);
                }
                results.add(item);
            }

            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }
        }

        return null;
    }
}
