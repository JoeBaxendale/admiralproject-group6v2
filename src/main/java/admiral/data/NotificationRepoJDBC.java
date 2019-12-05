package admiral.data;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.DatabaseConnection;
import admiral.domain.NotificationDetails;
import admiral.service.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;

//----------------------------------------------------------------------------------------------------------------------
// Repository DB connection and queries for Notification details
@Repository
public class NotificationRepoJDBC implements NotificationRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Attributes for DB connection
    private JdbcTemplate jdbc;
    Connection conn;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning attributes
    @Autowired
    NotificationRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;
        conn = new DatabaseConnection().getCon();
    }



    //------------------------------------------------------------------------------------------------------------------
    // Gets the notification details for a specified timesheet id from the DB
    // This used a join across multiple tables
    public NotificationDetails findNotificationDetailsById(int time_sheet_id) {

        String notifSql = "SELECT ts.timesheet_id," +
                "conuser.first_name as contractor_first," +
                "conuser.last_name as contractor_last," +
                "manuser.first_name as manager_first," +
                "manuser.last_name as manager_last," +
                "ts.number_days," +
                "ts.overtime," +
                "ts.start_date," +
                "ts.end_date," +
                "ts.date_submitted, " +
                "ts.notes " +
                "FROM " +
                " timesheet as ts INNER JOIN " +
                " contractors as con " +
                " ON (ts.contractor_id = con.contractor_id) INNER JOIN " +
                " users as conuser " +
                " ON (con.user_id = conuser.user_id) INNER JOIN " +
                " managers as man " +
                " ON (con.manager_id = man.manager_id) INNER JOIN " +
                " users as manuser " +
                " ON (manuser.user_id = man.user_id) " +
                "WHERE ts.timesheet_id = " + time_sheet_id;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code. Only one row will be returned
        NotificationDetails notificationDetails = new NotificationDetails();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(notifSql);

            if (temp.next()) {
                //----------------------------------------------------------------------------------------------------------
                // Maps the data to the Notification details object
                notificationDetails.setTime_sheet_id(temp.getInt(1));
                notificationDetails.setContractor_name(temp.getString(2) + " " + temp.getString(3));
                notificationDetails.setManager_name(temp.getString(4) + " " + temp.getString(5));
                notificationDetails.setNumber_of_days(temp.getInt(6));
                notificationDetails.setOvertime(temp.getInt(7));
                notificationDetails.setStart_date(temp.getDate(8).toLocalDate());
                notificationDetails.setEnd_date(temp.getDate(9).toLocalDate());
                notificationDetails.setDate_submitted(temp.getDate(10).toLocalDate());
                notificationDetails.setNotes(temp.getString(11));
            } else {

                throw new IllegalArgumentException("Row not found, invalid time_sheet_id: " + time_sheet_id);

            }

        }
        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return notificationDetails;
    }

}

