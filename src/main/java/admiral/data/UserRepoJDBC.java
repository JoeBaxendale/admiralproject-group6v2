package admiral.data;
//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.DatabaseConnection;
import admiral.domain.UserInfo;
import admiral.service.UserInfoRepo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.sql.*;
//----------------------------------------------------------------------------------------------------------------------
// Repository DB connection for User info
@Repository
@Log
public class UserRepoJDBC implements UserInfoRepo {
    //------------------------------------------------------------------------------------------------------------------
    // Attributes for DB connection
    private JdbcTemplate jdbc;
    private Connection conn;
    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning attributes
    @Autowired
    UserRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;
        conn = new DatabaseConnection().getCon();
    }
    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns logged in user details via their email address
    public UserInfo findUserInfoByEmail(String emailAddress) throws UsernameNotFoundException {
        // Define sql code
        String sql = "SELECT usr.user_id, " +
                "IFNULL(con.contractor_id,0), " +
                "IFNULL(man.manager_id,0), " +
                "usr.password, " +
                "usr.active, " +
                "usr.first_name, " +
                "usr.last_name, " +
                "usr.email, " +
                "r.role_name " +
                "FROM " +
                "users as usr " +
                "INNER JOIN roles r " +
                " ON (usr.role_id = r.role_id) " +
                "LEFT JOIN contractors as con " +
                " ON (usr.user_id = con.user_id) " +
                "LEFT JOIN managers as man " +
                " ON (con.manager_id = man.manager_id) " +
                "WHERE usr.email = '" + emailAddress + "'";
        log.info("Generated sql: " + sql);
        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code. Only one row will be returned
        UserInfo userInfo = new UserInfo();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);
            if (temp.next()) {
                //----------------------------------------------------------------------------------------------------------
                // Maps the data to the UserInfo object
                userInfo.setUserId(temp.getInt(1));
                userInfo.setContractorId(temp.getInt(2));
                userInfo.setManagerId(temp.getInt(3));
                userInfo.setPassword(temp.getString(4));
                userInfo.setEnabled(temp.getBoolean(5));
                userInfo.setFirstName(temp.getString(6));
                userInfo.setLastName(temp.getString(7));
                userInfo.setUsername(temp.getString(8));
                userInfo.setRole(temp.getString(9));
                userInfo.setAccountNonExpired(true);
                userInfo.setAccountNonLocked(true);
                userInfo.setCredentialsNonExpired(true);
                log.info("UserInfo object: " + userInfo.toString());
            } else {
                throw new UsernameNotFoundException("User not found: " + emailAddress) ;
            }
        }
        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    };
}