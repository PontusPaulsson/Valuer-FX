
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class SQLClient {
    private static final String CONN_STRING = "jdbc:sqlserver://gascanius.ddns.net:1433;databaseName=Valuables;";
    private static final String USERNAME = "GasCan";
    private static final String PASSWORD = "greger1337";


    public static void insertStock(String name, double worth, String type, String quote, int amount){
        try
        {
            UUID uuid = UUID.randomUUID();
            // Step 1: "Load" the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Step 2: Establish the connection to the database
            Connection conn = DriverManager.getConnection(CONN_STRING,USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO dbo.valuables VALUES('" + uuid.toString() + "','" + name + "','" + type + "'," + worth + ")");
            statement.executeUpdate("INSERT INTO dbo.stocks VALUES('" + quote  + "," + amount + ",'" + uuid.toString() + "')");
            conn.close();

        }
        catch (Exception e)
        {
            System.err.println("Exception caught: ");
            System.err.println(e.getMessage());
        }
    }
    public static void insertJewelery(String name, double worth, String type, int gemStones, boolean isGold){
        int gold = 0;
        if(isGold)
            gold = 1;
        try
        {
            UUID uuid = UUID.randomUUID();
            // Step 1: "Load" the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Step 2: Establish the connection to the database
            Connection conn = DriverManager.getConnection(CONN_STRING,USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO dbo.valuables VALUES('" + uuid.toString() + "','" + name + "','" + type + "'," + worth + ")");
            statement.executeUpdate("INSERT INTO dbo.jewelery VALUES('" + gemStones + "'," + gold + ",'" + uuid.toString() + "')");
            conn.close();

        }
        catch (Exception e)
        {
            System.err.println("Exception caught: ");
            System.err.println(e.getMessage());
        }
    }
    public static void insertDevice(String name, double worth, String type, double priceWhenBought, int condition){
        try
        {
            UUID uuid = UUID.randomUUID();
            // Step 1: "Load" the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Step 2: Establish the connection to the database
            Connection conn = DriverManager.getConnection(CONN_STRING,USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO dbo.valuables VALUES('" + uuid.toString() + "','" + name + "','" + type + "'," + worth + ")");
            statement.executeUpdate("INSERT INTO dbo.jewelery VALUES(" + priceWhenBought + "," + condition + ",'" + uuid.toString() + "')");
            conn.close();

        }
        catch (Exception e)
        {
            System.err.println("Exception caught: ");
            System.err.println(e.getMessage());
        }
    }
    public static ArrayList<String> selectAll(){
        ArrayList<String> valuables = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM dbo.jewelery AS t1\n" +
                    "INNER JOIN dbo.valuables AS t2 on t1.fk_valuables = t2.ID");
            while (rs.next()) {
                String type = "Silver";
                if(rs.getInt("IsGold") == 1)
                    type = "Gold";
                valuables.add("Jewelery: " + rs.getString("Name") + " Worth: " + rs.getDouble ("Worth") + " Gemstones: " + rs.getString("Gemstones") + " Type: " + type);
            }
            rs = statement.executeQuery(
                    "SELECT * FROM dbo.device AS t1\n" +
                            "INNER JOIN dbo.valuables AS t2 on t1.fk_valuables = t2.ID");
            while (rs.next()){
                valuables.add("Device: " + rs.getString("Name") + " Worth: " + rs.getDouble("Worth") + " Price: " + rs.getFloat("PriceWhenBought") + " Condition: " + rs.getString("Condition"));
            }
            rs = statement.executeQuery(
                    "SELECT * FROM dbo.stock AS t1\n" +
                            "INNER JOIN dbo.valuables AS t2 on t1.fk_valuables = t2.ID");
            while (rs.next()){
                valuables.add("Stock: " + rs.getString("Name") + " Worth: " + rs.getDouble("Worth") + " Quote: " + rs.getString("StockQuote") + " Amount: " + rs.getString("Amount"));
            }


        }
        catch(Exception e){
            System.err.println("Exception caught: ");
            System.err.println(e.getMessage());
        }
        return valuables;
    }
}
