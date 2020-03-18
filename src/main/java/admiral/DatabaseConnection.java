package admiral;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//----------------------------------------------------------------------------------------------------------------------
// Class to connect to the admiral database
public class DatabaseConnection {

    // attribute for the connection path
    private Connection con;

    //------------------------------------------------------------------------------------------------------------------
    // Connects to the pre-created admiral database
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/admiral","root","comsc123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getter for the connection path
    public Connection getCon() {
        return con;
    }
}
