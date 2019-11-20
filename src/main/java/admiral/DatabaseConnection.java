package admiral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admiral","root","comsc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }
}
