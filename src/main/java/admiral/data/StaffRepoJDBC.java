package admiral.data;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import admiral.DatabaseConnection;
import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import admiral.service.StaffRepo;
import admiral.service.events.ContractorUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Repository DB connection for Time Sheets
@Repository
public class StaffRepoJDBC implements StaffRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Attributes for DB connection
    private JdbcTemplate jdbc;
    Connection conn;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning attributes
    @Autowired
    StaffRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;
        conn = new DatabaseConnection().getCon();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all contractors from DB
    public List<ContractorUser> findContractors(){

        // Define sql code
        String sql = "Select contractor_id, manager_id, contractors.user_id, first_name, last_name, email, role_id, active from contractors inner join users on contractors.user_id = users.user_id WHERE active = 1" ;

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
                        " ",
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
        String sql = "Select contractor_id, manager_id, contractors.user_id, first_name, last_name, email, role_id, active from contractors inner join users on contractors.user_id = users.user_id where contractor_id = '"+ searchTerm+"' AND active = 1";

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
                        " ",
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
    // Finds and returns a list of all contractors with the same manager
    public List<ContractorUser> findContractorByManager(String searchTerm){

        // Define sql code
        String sql = "Select contractor_id, manager_id, contractors.user_id, first_name, last_name, email, role_id, active from contractors inner join users on contractors.user_id = users.user_id where manager_id = '"+ searchTerm+"' AND active = 1" ;

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
                        " ",
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
    public int getContractorByUser(long userId){

        // Define sql code
        String sql = "Select contractor_id from contractors where user_id = '"+ userId+"'";
        int tempId = 0;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<ContractorUser> contractorUserList = new ArrayList<ContractorUser>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            while(temp.next()){
                tempId = temp.getInt(1);
            }
            return tempId;

        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return tempId;
    };

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all contractors from DB
    public void updateContractor(ContractorUpdated contractorUpdated) {

        // Define sql code, pulling data from passed TimeSheetMade object
        String sql = "update contractors inner join users on contractors.user_id = users.user_id Set first_name = '"+
                contractorUpdated.getFirst_name() +"', manager_id = '"+ contractorUpdated.getManager_id() +"', last_name ='"+
                contractorUpdated.getLast_name() +"', email ='"+ contractorUpdated.getEmail() +"' WHERE contractor_id = '"+ contractorUpdated.getContractor_id() +"'";

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Upadtes users password
    public void updateContractorPassword(int contractorId, String newPassword) {

        // Define sql code, pulling data from passed TimeSheetMade object
        String sql = "update contractors inner join users on contractors.user_id = users.user_id Set password ='"+
                newPassword +"' WHERE contractor_id = '"+ contractorId +"'";

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all contractors from DB
    public void deactivateContractor(int contractorId) {

        // Define sql code, pulling data from passed TimeSheetMade object
        String sql = "update contractors inner join users on contractors.user_id = users.user_id Set active = 0 WHERE " +
                "contractor_id = '"+ contractorId +"'";

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all contractors from DB
    public List<ManagerUser> findManagers(){

        // Define sql code
        String sql = "Select manager_id, managers.user_id, first_name, last_name, email, active from managers inner join users on managers.user_id = users.user_id WHERE active = 1" ;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<ManagerUser> managerUserList = new ArrayList<ManagerUser>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            //----------------------------------------------------------------------------------------------------------
            // Maps the data to a Contractor list
            while (temp.next()) {
                managerUserList.add(new ManagerUser(temp.getInt(1),
                        temp.getInt(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getBoolean(6)));

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return managerUserList;
    };

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a contractor based on id (List to handle DB errors)
    public List<ManagerUser> findManagerById(int searchId){

        // Define sql code
        String sql = "Select manager_id, managers.user_id, first_name, last_name, email, active from managers inner join users on managers.user_id = users.user_id WHERE active = 1 AND managers.manager_id = '"+ searchId +"'" ;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<ManagerUser> managerUserList = new ArrayList<ManagerUser>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            //----------------------------------------------------------------------------------------------------------
            // Maps the data to a Contractor list
            while (temp.next()) {
                managerUserList.add(new ManagerUser(temp.getInt(1),
                        temp.getInt(2),
                        temp.getString(3),
                        temp.getString(4),
                        temp.getString(5),
                        temp.getBoolean(6)));

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return managerUserList;
    };
}
