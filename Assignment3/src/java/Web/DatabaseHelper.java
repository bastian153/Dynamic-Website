package Web;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {
    // LOCAL HOST
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost/inf124grp08";
    
    /*
    ICS Group Account
    public static final String USERNAME = "inf124grp08";
    public static final String PASSWORD = "@e8hUjaB";
    public static final String URL = "jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124grp08";
    */
    
    public static Connection loginDatbase(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // LOCAL SQL Database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            // ICS SQL Database
            /*
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nobleandbarnes", 
                    USERNAME, PASSWORD);
            */
        } catch(ClassNotFoundException | SQLException e) {
            return null;
        }
        return connection;
    }
    
    
    public static void logoutDatabase(Connection connection){
        try {
           if(connection != null)
            connection.close(); 
        } catch(Exception e){
        }
    }
    
}
