package QueryHandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PlotQueryHandler {

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql = "";
    private ArrayList<String> duchyList, qualityList;

    public PlotQueryHandler(Connection c) {
        super();
        con = c;

        duchyList = new ArrayList();
        qualityList = new ArrayList();

        try {
            sql = "SELECT DuchyName FROM Duchy";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                duchyList.add(rs.getString("DuchyName"));
            }

            sql = "SELECT QualityDescription FROM Quality";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                qualityList.add(rs.getString("QualityDescription"));
            }
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler Constructor");
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function capitalizes the first letter of the word sent in.
     */
    public String capitalizeFirst(String name) {
        String correction = Character.toString(name.charAt(0));
        correction = correction.toUpperCase();
        name = name.replaceFirst(Character.toString(name.charAt(0)), correction);
        return name;
    }

    /**
     * This function returns the price of a certain plot, and the amount needed
     * for monthly upkeep - depending on the provided duchy, and quality of the
     * plot.
     */
    public ArrayList<String[]> queryPlotPrice(String duchy, String quality) {
        boolean correctDuchy = false, correctQuality = false;
        int duchyID, qualityID;
        int plotPrice, plotUpkeep;
        ArrayList<String[]> values;
        String[] line;

        duchy = duchy.toLowerCase();
        quality = quality.toLowerCase();
        values = new ArrayList();
        line = new String[3];

        //Check parameter validity
        for (int a = 0; a < duchyList.size(); a++) {
            if (duchyList.get(a).toLowerCase().equals(duchy)) {
                correctDuchy = true;
            }
        }

        for (int a = 0; a < qualityList.size(); a++) {
            if (qualityList.get(a).toLowerCase().equals(quality)) {
                correctQuality = true;
            }
        }

        if (correctDuchy != true || correctQuality != true) {
            System.out.println("Incorrect parameters supplied to function queryPlotPrice()");
        } else {
            //Capitalize first letter of duchy and quality
            duchy = capitalizeFirst(duchy);
            quality = capitalizeFirst(quality);

            try {
                //Get the unique ID's of both the duchy, and quality
                sql = "SELECT DuchyID FROM Duchy WHERE DuchyName = '" + duchy + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                rs.next();
                duchyID = Integer.parseInt(rs.getString("DuchyID"));

                sql = "SELECT QualityID FROM Quality WHERE QualityDescription = '" + quality + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);

                rs.next();
                qualityID = Integer.parseInt(rs.getString("QualityID"));

                //Determine price and upkeep costs
                sql = "SELECT PriceLand, PriceMonthlyUpkeep FROM Price "
                        + "WHERE DuchyID = " + duchyID + " AND QualityID = "
                        + qualityID;
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                plotPrice = Integer.parseInt(rs.getString("PriceLand"));
                plotUpkeep = Integer.parseInt(rs.getString("PriceMonthlyUpkeep"));

                //Find price values
                sql = "SELECT AmountPlatinum, AmountGold, AmountSilver "
                        + "FROM Amount WHERE AmountID = "
                        + plotPrice;
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                line[0] = rs.getString("AmountPlatinum");
                line[1] = rs.getString("AmountGold");
                line[2] = rs.getString("AmountSilver");
                values.add(line);

                line = new String[3];
                sql = "SELECT AmountPlatinum, AmountGold, AmountSilver "
                        + "FROM Amount WHERE AmountID = "
                        + plotUpkeep;
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                line[0] = rs.getString("AmountPlatinum");
                line[1] = rs.getString("AmountGold");
                line[2] = rs.getString("AmountSilver");
                values.add(line);

                return values;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    /**
     * This function will convert a 2D integer array, to make it suitable to
     * store in the database.
     */
    public String convertToArray(int[][] inArray) {
        String result = "";

        for (int a = 0; a < inArray.length; a++) {
            for (int b = 0; b < inArray[a].length; b++) {
                if (b == inArray[a].length - 1) {
                    result += inArray[a][b];
                } else {
                    result += inArray[a][b] + ",";
                }
            }
            result += ";";
        }

        return result;
    }

    /**
     * This function will convert the 1D integer array, that was stored in the
     * database, back to a 2D integer array usable by the grid system.
     */
    public int[][] convertFromArray(String inArray) {
        int[][] result;
        StringTokenizer str, stc;
        int rows, columns;

        str = new StringTokenizer(inArray, ";");
        rows = str.countTokens();
        stc = new StringTokenizer(str.nextToken(), ",");
        columns = stc.countTokens();
        result = new int[rows][columns];

        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < columns; b++) {
                result[a][b] = Integer.parseInt(stc.nextToken());
            }
            if (a < rows - 1) {
                stc = new StringTokenizer(str.nextToken(), ",");
            }
        }

        return result;
    }

    /**
     * This function will add a certain plot to a character, as each character
     * in the game system can own one or more plots.
     */
    public boolean addPlotToCharacter(String characterName, String duchyName, String quality,
            int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness,
            double monthlyIncome, int workersUsed, int workerMax) {
        int characterID, duchyID, qualityID;
        String ground, building;
        int exquisite = 0, fine = 0, poor = 0;

        //Resolve all ID's
        //*Fault checking
        try {
            sql = "SELECT UserCharacterID FROM UserCharacter WHERE "
                    + "LOWER(UserCharacterName) = '" + characterName.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            characterID = Integer.parseInt(rs.getString("UserCharacterID"));

            sql = "SELECT DuchyID FROM Duchy WHERE "
                    + "LOWER(DuchyName) = '" + duchyName.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            duchyID = Integer.parseInt(rs.getString("DuchyID"));

            sql = "SELECT QualityID FROM Quality WHERE "
                    + "LOWER(QualityDescription) = '" + quality.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            qualityID = Integer.parseInt(rs.getString("QualityID"));

            if (qualityID == 3) {
                exquisite += 1;
            } else if (qualityID == 2) {
                fine += 1;
            } else if (qualityID == 1) {
                poor += 1;
            }

            ground = convertToArray(groundArray);
            building = convertToArray(buildingArray);

            //Add the plot to database
            int amountID;
            sql = "INSERT INTO Amount VALUES(0,0,0);";
            stmt = con.createStatement();
            stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);

            rs = stmt.getGeneratedKeys();
            rs.next();
            amountID = rs.getInt(1);

            sql = "INSERT INTO Plot (PlotOwnedBy, PlotAmount, PlotDuchy, PlotSize, "
                    + "PlotGroundArray, PlotBuildingArray, PlotHappiness, "
                    + "PlotMonthlyIncome, PlotWorkersUsed, PlotWorkerMax, "
                    + "PlotAcreExquisite, PlotAcreExquisiteMax, "
                    + "PlotAcreFine, PlotAcreFineMax, "
                    + "PlotAcrePoor, PlotAcrePoorMax) VALUES "
                    + "(" + characterID + ", " + amountID + ", " + duchyID + ", " + sizeValue
                    + ", '" + ground + "', '" + building + "', "
                    + +happiness + ", " + monthlyIncome + ", " + workersUsed + ", " + workerMax
                    + ",0 , " + exquisite + ", 0, " + fine + ", 0, " + poor + ")";

            stmt = con.createStatement();
            stmt.execute(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Could not execute function addPlotToCharacter()");
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * This function will retrieve a list of all the plots owned by the provided
     * character.
     */
    public ArrayList<String[]> retrievePlotsOwnedByCharacter(int characterID) {
        ArrayList<String[]> values;
        String[] line;

        values = new ArrayList();

        try {
            sql = "SELECT * FROM Plot WHERE "
                    + "PlotOwnedBy = " + characterID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                line = new String[17];
                line[0] = rs.getString("PlotID");
                line[1] = rs.getString("PlotOwnedBy");
                line[2] = rs.getString("PlotAmount");
                line[3] = rs.getString("PlotDuchy");
                line[4] = rs.getString("PlotSize");
                line[5] = rs.getString("PlotGroundArray");
                line[6] = rs.getString("PlotBuildingArray");
                line[7] = rs.getString("PlotHappiness");
                line[8] = rs.getString("PlotMonthlyIncome");
                line[9] = rs.getString("PlotWorkersUsed");
                line[10] = rs.getString("PlotWorkerMax");
                line[11] = rs.getString("PlotAcreExquisite");
                line[12] = rs.getString("PlotAcreExquisiteMax");
                line[13] = rs.getString("PlotAcreFine");
                line[14] = rs.getString("PlotAcreFineMax");
                line[15] = rs.getString("PlotAcrePoor");
                line[16] = rs.getString("PlotAcrePoorMax");

                values.add(line);
            }

            //Exchange ID's with values
            for (int a = 0; a < values.size(); a++) {
                sql = "SELECT UserCharacterName FROM UserCharacter "
                        + "WHERE UserCharacterID = " + values.get(a)[1];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[1] = rs.getString("UserCharacterName");

                sql = "SELECT * FROM Amount WHERE AmountID = "
                        + values.get(a)[2];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[2] = rs.getString("AmountPlatinum") + "-"
                        + rs.getString("AmountGold") + "-"
                        + rs.getString("AmountSilver");

                sql = "SELECT DuchyName FROM Duchy WHERE "
                        + "DuchyID = " + values.get(a)[3];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                values.get(a)[3] = rs.getString("DuchyName");
            }

            return values;
        } catch (Exception e) {
            System.out.println("Could not execute function retrievePlotsOwnedByCharacter()");
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * This function returns all details regarding the provided plotID.
     */
    public ArrayList<String> retrievePlotDetails(int plotID) {
        ArrayList<String> value;

        value = new ArrayList();

        try {
            sql = "SELECT * FROM Plot WHERE PlotID = " + plotID;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            value.add(rs.getString("PlotID")); //0
            value.add(rs.getString("PlotOwnedBy"));//1
            value.add(rs.getString("PlotAmount"));//2
            value.add(rs.getString("PlotDuchy"));//3
            value.add(rs.getString("PlotSize"));//4
            value.add(rs.getString("PlotGroundArray"));//5
            value.add(rs.getString("PlotBuildingArray"));//6
            value.add(rs.getString("PlotHappiness"));//7
            value.add(rs.getString("PlotMonthlyIncome"));//8
            value.add(rs.getString("PlotWorkersUsed"));//9
            value.add(rs.getString("PlotWorkerMax"));//10
            value.add(rs.getString("PlotAcreExquisite"));//11
            value.add(rs.getString("PlotAcreExquisiteMax"));//12
            value.add(rs.getString("PlotAcreFine"));//13
            value.add(rs.getString("PlotAcreFineMax"));//14
            value.add(rs.getString("PlotAcrePoor"));//15
            value.add(rs.getString("PlotAcrePoorMax"));//16

            sql = "SELECT UserCharacterName FROM UserCharacter "
                    + "WHERE UserCharacterID = " + value.get(1);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            value.set(1, rs.getString("UserCharacterName"));

            sql = "SELECT * FROM Amount WHERE AmountID = "
                    + value.get(2);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            value.set(2, rs.getString("AmountPlatinum") + "-"
                    + rs.getString("AmountGold") + "-"
                    + rs.getString("AmountSilver"));

            sql = "SELECT DuchyName FROM Duchy WHERE "
                    + "DuchyID = " + value.get(3);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            value.set(3, rs.getString("DuchyName"));

            return value;
        } catch (Exception e) {
            System.out.println("Error, could not execute function retrievePlotDetails()");
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * This function changes plot details to the ones provided. *Danger: This
     * function has the power to change values beyond their specified
     * parameters. PlotAmount supplied in the following format:
     * amountExquisite-amountFine-amountPoor i.e. 1-0-1
     */
    public boolean modifyPlot(int plotID, String characterName, String plotAmount, String duchyName,
            int sizeValue, int[][] groundArray, int[][] buildingArray, int happiness, double monthlyIncome,
            int workersUsed, int workerMax, double acreE, int acreEM, double acreF, int acreFM,
            double acreP, int acrePM) {

        int characterID, duchyID, amountID;
        String ground, building;
        int plat, gold, silver;

        //Exchange ID's with values  
        try {
            sql = "SELECT UserCharacterID FROM UserCharacter WHERE "
                    + "LOWER(UserCharacterName) = '" + characterName.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            characterID = Integer.parseInt(rs.getString("UserCharacterID"));

            sql = "SELECT DuchyID FROM Duchy WHERE "
                    + "LOWER(DuchyName) = '" + duchyName.toLowerCase() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            duchyID = Integer.parseInt(rs.getString("DuchyID"));

            StringTokenizer st = new StringTokenizer(plotAmount, "-");
            plat = Integer.parseInt(st.nextToken());
            gold = Integer.parseInt(st.nextToken());
            silver = Integer.parseInt(st.nextToken());

            sql = "SELECT AmountID FROM Amount WHERE AmountPlatinum = "
                    + plat + " AND AmountGold = " + gold
                    + " AND AmountSilver = " + silver;
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("AmountID"));
            ground = convertToArray(groundArray);
            building = convertToArray(buildingArray);

            System.out.println(happiness);

            //ek gaan dit edit om ni die plots te update ni
            
            sql = "UPDATE Plot SET "
                    + "PlotOwnedBy = " + characterID + ", "
                    + "PlotAmount = " + amountID + ", "
                    + "PlotDuchy = " + duchyID + ", "
                    + "PlotSize = " + sizeValue + ", "
                    + "PlotGroundArray = '" + ground + "', "
                    + "PlotBuildingArray = '" + building + "', "
                    + "PlotHappiness = " + happiness + ", "
                    + "PlotMonthlyIncome = " + monthlyIncome + ", "
                    + "PlotWorkersUsed = " + workersUsed + ", "
                    +"PlotWorkerMax = " + workerMax + " "
                    //+ "PlotWorkerMax = " + workerMax + ", "
//                    + "PlotAcreExquisite = " + acreE + ", "
//                    + "PlotAcreExquisiteMax = " + acreEM + ", "
//                    + "PlotAcreFine = " + acreF + ", "
//                    + "PlotAcreFineMax = " + acreFM + ", "
//                    + "PlotAcrePoor = " + acreP + ", "
//                    + "PlotAcrePoorMax = " + acrePM + " "
                    + "WHERE PlotID = " + plotID;
            stmt = con.createStatement();

            stmt.execute(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Could not execute function modifyPlot()");
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * This function acts as a search function, with multiple criteria, that
     * returns a certain plot's details depending on the criteria provided.
     * Empty fields will yield all results.
     */
    public ArrayList<String[]> searchPlotBy(String characterName, String duchy, int size, String quality) {
        ArrayList<String[]> values = null;
        int characterID = 0, duchyID = 0, qualityID = 0;
        String[] line;

        values = new ArrayList();

        try {
            //Get ID's
            if (!characterName.equals("")) {
                sql = "SELECT UserCharacterID FROM UserCharacter WHERE "
                        + "LOWER(UserCharacterName) = '" + characterName.toLowerCase() + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                characterID = Integer.parseInt(rs.getString("UserCharacterID"));
            }

            if (!duchy.equals("")) {
                sql = "SELECT DuchyID FROM Duchy WHERE "
                        + "LOWER(DuchyName) = '" + duchy.toLowerCase() + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                duchyID = Integer.parseInt(rs.getString("DuchyID"));
            }

            if (!quality.equals("")) {
                sql = "SELECT QualityID FROM Quality WHERE "
                        + "LOWER(QualityDescription) = '" + quality.toLowerCase() + "'";
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                qualityID = Integer.parseInt(rs.getString("QualityID"));
            }

            sql = "SELECT * FROM Plot";
            if (characterID != 0) {
                sql += " WHERE PlotOwnedBy LIKE " + characterID;
            }

            if (duchyID != 0 && characterID != 0) {
                sql += " AND PlotDuchy LIKE " + duchyID;
            } else if (duchyID != 0) {
                sql += " WHERE PlotDuchy LIKE " + duchyID;
            }

            if (size > 0 && (characterID != 0 || duchyID != 0)) {
                sql += " AND PlotSize LIKE " + size;
            } else if (size > 0) {
                sql += " WHERE PlotSize LIKE " + size;
            }

            if (qualityID != 0 && (characterID != 0 || duchyID != 0 || size > 0)) {
                if (qualityID == 1) {
                    sql += " AND PlotAcrePoorMax > 0";
                } else if (qualityID == 2) {
                    sql += " AND PlotAcreFineMax > 0";
                } else if (qualityID == 3) {
                    sql += " AND PlotAcreExquisiteMax > 0";
                }
            } else if (qualityID != 0) {
                if (qualityID == 1) {
                    sql += " WHERE PlotAcrePoorMax > 0";
                } else if (qualityID == 2) {
                    sql += " WHERE PlotAcreFineMax > 0";
                } else if (qualityID == 3) {
                    sql += " WHERE PlotAcreExquisiteMax > 0";
                }
            }

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                line = new String[17];
                line[0] = rs.getString("PlotID");
                line[1] = rs.getString("PlotOwnedBy");
                line[2] = rs.getString("PlotAmount");
                line[3] = rs.getString("PlotDuchy");
                line[4] = rs.getString("PlotSize");
                line[5] = rs.getString("PlotGroundArray");
                line[6] = rs.getString("PlotBuildingArray");
                line[7] = rs.getString("PlotHappiness");
                line[8] = rs.getString("PlotMonthlyIncome");
                line[9] = rs.getString("PlotWorkersUsed");
                line[10] = rs.getString("PlotWorkerMax");
                line[11] = rs.getString("PlotAcreExquisite");
                line[12] = rs.getString("PlotAcreExquisiteMax");
                line[13] = rs.getString("PlotAcreFine");
                line[14] = rs.getString("PlotAcreFineMax");
                line[15] = rs.getString("PlotAcrePoor");
                line[16] = rs.getString("PlotAcrePoorMax");
                values.add(line);
            }

            return values;
        } catch (Exception e) {
            System.out.println("Could not execute function searchPlotBy()");
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * This function deletes a plot. Returns true if deleted successfully, and
     * false if not.
     */
    public boolean deletePlot(int plotID) {
        try {
            sql = "DELETE FROM Plot WHERE PlotID = " + plotID;
            stmt = con.createStatement();
            stmt.execute(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Could not execute function deletePlot()");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public ArrayList<String[]> retrieveAllPlots() {
        String sql = "";
        ArrayList<String[]> result = null;
        String[] line = null;

        try {
            sql = "SELECT * FROM Plot";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            ResultSet lrs;
            result = new ArrayList();
            while (rs.next()) {
                line = new String[17];
                line[0] = rs.getString("PlotID");
                line[1] = rs.getString("PlotOwnedBy");
                line[2] = rs.getString("PlotAmount");
                line[3] = rs.getString("PlotDuchy");
                line[4] = rs.getString("PlotSize");
                line[5] = rs.getString("PlotGroundArray");
                line[6] = rs.getString("PlotBuildingArray");
                line[7] = rs.getString("PlotHappiness");
                line[8] = rs.getString("PlotMonthlyIncome");
                line[9] = rs.getString("PlotWorkersUsed");
                line[10] = rs.getString("PlotWorkerMax");
                line[11] = rs.getString("PlotAcreExquisite");
                line[12] = rs.getString("PlotAcreExquisiteMax");
                line[13] = rs.getString("PlotAcreFine");
                line[14] = rs.getString("PlotAcreFineMax");
                line[15] = rs.getString("PlotAcrePoor");
                line[16] = rs.getString("PlotAcrePoorMax");

                sql = "SELECT UserCharacterName FROM UserCharacter "
                        + "WHERE UserCharacterID = " + line[1];
                stmt = con.createStatement();
                lrs = stmt.executeQuery(sql);
                lrs.next();
                line[1] = lrs.getString("UserCharacterName");

                sql = "SELECT * FROM Amount WHERE AmountID = "
                        + line[2];
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                line[2] = rs.getString("AmountPlatinum") + "-"
                        + rs.getString("AmountGold") + "-"
                        + rs.getString("AmountSilver");

                sql = "SELECT DuchyName FROM Duchy WHERE "
                        + "DuchyID = " + line[3];
                stmt = con.createStatement();
                lrs = stmt.executeQuery(sql);
                lrs.next();
                line[3] = lrs.getString("DuchyName");

                result.add(line);
            }

            return result;
        } catch (Exception e) {
            System.out.println("Could not execute function retrieveAllPlots()");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* This function retrieves the balance of the provided plot.
     */
    public ArrayList<String> getCurrentAmount(int plotID) {
        ArrayList<String> amounts = new ArrayList();
        int amountID, curPlat, curGold, curSil;

        sql = "SELECT PlotAmount FROM Plot WHERE "
                + "PlotID = " + plotID;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("PlotAmount"));

            sql = "SELECT AmountPlatinum, AmountGold, AmountSilver FROM Amount "
                    + "WHERE AmountID = " + amountID;
            rs = stmt.executeQuery(sql);
            rs.next();
            curPlat = Integer.parseInt(rs.getString("AmountPlatinum"));
            curGold = Integer.parseInt(rs.getString("AmountGold"));
            curSil = Integer.parseInt(rs.getString("AmountSilver"));

            amounts.add("" + curPlat);
            amounts.add("" + curGold);
            amounts.add("" + curSil);

            return amounts;
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, function getCurrentAmount().");
            System.out.println(e.getMessage());
        }

        return null;
    }
    /* This function modifies they amount of currency(i.e. silver, gold, platinum)
     * that an estate ownes.
     */

    public boolean modifyAmount(int inPlotID, int amountPlatinum, int amountGold, int amountSilver) {
        String sql = "";
        int amountID = 0;

        sql = "SELECT PlotAmount FROM Plot WHERE "
                + "PlotID = " + inPlotID;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("PlotAmount"));
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, function modifyAmount().");
            System.out.println(e.getMessage());
        }
        System.out.println(amountPlatinum + " " + amountGold + " " + amountSilver + " " + amountID);
        sql = "UPDATE Amount SET "
                + "AmountPlatinum = " + amountPlatinum
                + ", AmountGold = " + amountGold
                + ", AmountSilver = " + amountSilver
                + " WHERE AmountID = " + amountID;


        try {
            System.out.println(sql);
            stmt = con.createStatement();
            stmt.executeQuery(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, function modifyAmount() marker2");
            System.out.println(e.getMessage());
        }

        return false;  //Not successful
    }

    /* This function adds an amount to the provided plot.
     */
    public boolean depositAmount(int plotID, int amountPlatinum, int amountGold, int amountSilver) {
        int amountID, curPlat, curGold, curSil;

        sql = "SELECT PlotAmount FROM Plot WHERE PlotID = " + plotID;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("PlotAmount"));

            sql = "SELECT AmountPlatinum, AmountGold, AmountSilver FROM Amount "
                    + "WHERE AmountID = " + amountID;
            rs = stmt.executeQuery(sql);
            rs.next();
            curPlat = Integer.parseInt(rs.getString("AmountPlatinum"));
            curGold = Integer.parseInt(rs.getString("AmountGold"));
            curSil = Integer.parseInt(rs.getString("AmountSilver"));

            curPlat += amountPlatinum;
            curGold += amountGold;
            curSil += amountSilver;

            sql = "UPDATE Amount SET "
                    + "AmountPlatinum = " + curPlat + ", "
                    + "AmountGold = " + curGold + ", "
                    + "AmountSilver = " + curSil + " "
                    + "WHERE AmountID = " + amountID;
            stmt = con.createStatement();
            stmt.execute(sql);

            return true;
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, function addAmount()");
            System.out.println(e.getMessage());
        }

        return false;
    }

    /* This function removes an amount from the provided plot.
     */
    public boolean withdrawAmount(int plotID, int amountPlatinum, int amountGold, int amountSilver) {
        int amountID, curPlat, curGold, curSil;

        sql = "SELECT PlotAmount FROM Plot WHERE PlotID = " + plotID;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            amountID = Integer.parseInt(rs.getString("PlotAmount"));

            sql = "SELECT AmountPlatinum, AmountGold, AmountSilver FROM Amount "
                    + "WHERE AmountID = " + amountID;
            rs = stmt.executeQuery(sql);
            rs.next();
            curPlat = Integer.parseInt(rs.getString("AmountPlatinum"));
            curGold = Integer.parseInt(rs.getString("AmountGold"));
            curSil = Integer.parseInt(rs.getString("AmountSilver"));

            curPlat -= amountPlatinum;
            curGold -= amountGold;
            curSil -= amountSilver;

            if (curPlat < 0 || curGold < 0 || curSil < 0) {
                System.out.println("Error in removeAmount() - removed too much. An amount fell below zero.");
            } else {
                sql = "UPDATE Amount SET "
                        + "AmountPlatinum = " + curPlat + ", "
                        + "AmountGold = " + curGold + ", "
                        + "AmountSilver = " + curSil + " "
                        + "WHERE AmountID = " + amountID;
                stmt = con.createStatement();
                stmt.execute(sql);

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, function addAmount()");
            System.out.println(e.getMessage());
        }

        return false;
    }

    /*Adds an extra plot(3x3 grid) to the provided estate.
     * plotID - Unique ID of the character's plot
     * quality - Description of the quality of plot to be added. e.g. 'fine'
     * groundArray - 2D array of ground tiles.
     * 
     * *Note: Need to supply groundArray because of lack of functionality
     * in the PlotQueryHandler to generate random water tiles. (To be added
     * later).
     */
    public boolean expandPlot(int plotID, String quality, int[][] groundArray) {
        int exquisite, fine, poor;
        String gArray = "";

        quality = quality.toLowerCase();

        if (quality.equals("exquisite") || quality.equals("fine") || quality.equals("poor")) {
            try {
                sql = "SELECT PlotGroundArray, PlotAcreExquisiteMax, PlotAcreFineMax, PlotAcrePoorMax "
                        + "FROM Plot WHERE PlotID = " + plotID;
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();

                System.out.println(sql);
                exquisite = Integer.parseInt(rs.getString("PlotAcreExquisiteMax"));
                System.out.println("c "+exquisite);
                fine = Integer.parseInt(rs.getString("PlotAcreFineMax"));
                System.out.println("c "+fine);
                poor = Integer.parseInt(rs.getString("PlotAcrePoorMax"));
                System.out.println("c "+poor);
                
               // gArray = rs.getString("PlotGroundArray");
                if (quality.equals("exquisite")) {
                    exquisite += 1;
                } else if (quality.equals("fine")) {
                    fine += 1;
                } else {
                    poor += 1;
                }

                gArray = gArray + convertToArray(groundArray);
                //Add back to database
                
                sql = "UPDATE Plot SET "
                        + "PlotAcreExquisiteMax = " + exquisite + ", "
                        + "PlotAcreFineMax = " + fine + ", "
                        + "PlotAcrePoorMax = " + poor + ","
                        + "PlotGroundArray = '" + gArray + "' "
                        + "WHERE PlotID = " + plotID;
                System.out.println(sql);
                stmt = con.createStatement();
                stmt.execute(sql);
                return true;  //Update successful
            } catch (Exception e) {
                System.out.println("Error in PlotQueryHander, function expandPlot()");
                System.out.println(e.getMessage());
    
            }
        } else {
            System.out.println("Invalid quality supplied.");
        }

        return false;  //Unsuccessful expansion
    }

    /* This function returns the amount of exquisite, fine, poor acres the
     * provided plot has.
     */
    public ArrayList<String> getAcreQualityAmounts(int plotID) {
        ArrayList<String> amounts = new ArrayList();

        sql = "SELECT PlotAcreExquisiteMax, PlotAcreFineMax, PlotAcrePoorMax "
                + "FROM Plot WHERE PlotID = " + plotID;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            amounts.add("" + Integer.parseInt(rs.getString("PlotAcreExquisiteMax")));
            amounts.add("" + Integer.parseInt(rs.getString("PlotAcreFineMax")));
            amounts.add("" + Integer.parseInt(rs.getString("PlotAcrePoorMax")));

            return amounts;
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, function getAcreQualityAmount()");
            System.out.println(e.getMessage());
        }

        return null;
    }

    /* This function will be used when buildings are bought, to add to acres used,
     * and to check for limit overflow.
     * 
     * Returns false if modification is unsuccessful.
     */
    public boolean useAcresOnPlot(int plotID, double acreExquisite, double acreFine, double acrePoor) {
        double curEx, curFine, curPoor;

        sql = "SELECT PlotAcreExquisite, PlotAcreExquisiteMax, PlotAcreFine, PlotAcreFineMax, "
                + "PlotAcrePoor, PlotAcrePoorMax "
                + "FROM Plot WHERE PlotID = " + plotID;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            curEx = Double.parseDouble(rs.getString("PlotAcreExquisite"));
            curFine = Double.parseDouble(rs.getString("PlotAcreFine"));
            curPoor = Double.parseDouble(rs.getString("PlotAcrePoor"));

            //Check for overflow
            if ((curEx + acreExquisite) > Integer.parseInt(rs.getString("PlotAcreExquisiteMax"))) {
                System.out.println("Error in function useAcresOnPlot() - Exquisite acre overflow.");
            } else if ((curFine + acreFine) > Integer.parseInt(rs.getString("PlotAcreFineMax"))) {
                System.out.println("Error in function useAcresOnPlot() - Fine acre overflow.");
            } else if ((curPoor + acrePoor) > Integer.parseInt(rs.getString("PlotAcrePoorMax"))) {
                System.out.println("Error in function useAcresOnPlot() - Poor acre overflow.");
            } else {
                //All acre useages are within limits. Add to table.
                sql = "UPDATE Plot SET "
                        + "PlotAcreExquisite = " + (curEx + acreExquisite)
                        + "PlotAcreFine = " + (curFine + acreFine)
                        + "PlotAcrePoor = " + (curPoor + acrePoor)
                        + " WHERE PlotID = " + plotID;
                stmt = con.createStatement();
                stmt.execute(sql);

                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in PlotQueryHandler, in function useAcresOnPlot().");
            System.out.println(e.getMessage());
        }

        return false;
    }
}
