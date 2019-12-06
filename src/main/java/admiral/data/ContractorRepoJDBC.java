package admiral.data;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import admiral.DatabaseConnection;
import admiral.domain.ContractorUser;
import admiral.domain.TimeSheet;
import admiral.service.ContractorRepo;
import admiral.service.TimeSheetRepo;
import admiral.service.events.TimeSheetMade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Repository DB connection for Time Sheets
@Repository
public class ContractorRepoJDBC implements ContractorRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Attributes for DB connection
    private JdbcTemplate jdbc;
    Connection conn;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning attributes
    @Autowired
    ContractorRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;
        conn = new DatabaseConnection().getCon();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all contractors with the same manager
    public List<ContractorUser> findContractorByManager(String searchTerm){

        // Define sql code
        String sql = "Select contractor_id, manager_id, contractors.user_id, first_name, last_name, email, role_id, active from contractors inner join users on contractors.user_id = users.user_id where manager_id = '"+ searchTerm+"'" ;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<ContractorUser> contractorUserList = new ArrayList<ContractorUser>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            //----------------------------------------------------------------------------------------------------------
            // Maps the data to a Contractor list
            while (temp.next()) {
                contractorUserList.add(new ContractorUser(temp.getInt(1),
                        temp.getInt(2),
                        temp.getInt(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getString(6),
                        temp.getInt(7),
                        temp.getBoolean(8)));

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return contractorUserList;
    };

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a contractor based on id (List to handle DB errors)
    public List<ContractorUser> findContractorById(String searchTerm){

        // Define sql code
        String sql = "Select contractor_id, manager_id, contractors.user_id, first_name, last_name, email, role_id, active from contractors inner join users on contractors.user_id = users.user_id where contractor_id = '"+ searchTerm+"'" ;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<ContractorUser> contractorUserList = new ArrayList<ContractorUser>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            //----------------------------------------------------------------------------------------------------------
            // Maps the data to a Contractor list
            while (temp.next()) {
                contractorUserList.add(new ContractorUser(temp.getInt(1),
                        temp.getInt(2),
                        temp.getInt(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getString(6),
                        temp.getInt(7),
                        temp.getBoolean(8)));

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return contractorUserList;
    };

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all contractors from DB
    public List<ContractorUser> findContractors(){

        // Define sql code
        String sql = "Select contractor_id, manager_id, contractors.user_id, first_name, last_name, email, role_id, active from contractors inner join users on contractors.user_id = users.user_id" ;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<ContractorUser> contractorUserList = new ArrayList<ContractorUser>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            //----------------------------------------------------------------------------------------------------------
            // Maps the data to a Contractor list
            while (temp.next()) {
                contractorUserList.add(new ContractorUser(temp.getInt(1),
                        temp.getInt(2),
                        temp.getInt(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getString(6),
                        temp.getInt(7),
                        temp.getBoolean(8)));

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return contractorUserList;
    };


}
