package admiral.data;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.DatabaseConnection;
import admiral.domain.TimeSheet;
import admiral.service.TimeSheetRepo;
import admiral.service.events.TimeSheetMade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Repository DB connection for Time Sheets
@Repository
public class TimeSheetRepoJDBC implements TimeSheetRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Attributes for DB connection
    private JdbcTemplate jdbc;
    Connection conn;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning attributes
    @Autowired
    TimeSheetRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;
        conn = new DatabaseConnection().getCon();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns a list of all time sheets from the DB
    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm){

        // Define sql code
        String sql = "SELECT * FROM timesheet WHERE status = '"+ searchTerm+"'" ;

        //--------------------------------------------------------------------------------------------------------------
        // Executes the sql code
        List<TimeSheet> timesheetList = new ArrayList<TimeSheet>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            //----------------------------------------------------------------------------------------------------------
            // Maps the data to a Time Sheet list
            while(temp.next()){
                timesheetList.add(new TimeSheet(temp.getInt(1),
                        temp.getInt(2),
                        temp.getInt(3),
                        temp.getInt(4),
                        temp.getDate(5).toLocalDate(),
                        temp.getDate(6).toLocalDate(),
                        temp.getDate(7).toLocalDate(),
                        temp.getString(8),
                        temp.getString(9)));

            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Outputs DBG error message on DB connection failure
        catch (SQLException e) {
            e.printStackTrace();
        }

        return timesheetList;
    };

    //------------------------------------------------------------------------------------------------------------------
    // Saves new Time Sheets to the database
    @Override
    public void saveTimeSheetEvent(TimeSheetMade timeSheetMade) {

        // Define sql code, pulling data from passed TimeSheetMade object
        String sql = "INSERT INTO timesheet (contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, " +
                "status) VALUES (3, '"+ timeSheetMade.getNumber_of_days() +"', '"+ timeSheetMade.getOvertime() +"'," +
                " '"+ timeSheetMade.getStart_date() +"', '"+timeSheetMade.getEnd_date()+"', " +
                "'"+timeSheetMade.getEnd_date()+"', '"+timeSheetMade.getNotes() +"', 'Pending')";

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
    public void updateTimeSheetEntry(TimeSheet timesheet){

        try {
            PreparedStatement st = conn.prepareStatement("UPDATE timesheet SET `status` = ?, `notes` = ? WHERE timesheet_id = ?");  //make the sql statement with ?'s as variables
            st.setString(1,timesheet.getStatus());
            st.setString(2,timesheet.getNotes());       //set each ? to be the correct variable from the timesheet
            st.setLong(3,timesheet.getTime_sheet_id());
            st.executeUpdate();     //execute the sql which updates the database
        }catch(SQLException e){ //catch an error that is thrown
            e.printStackTrace();
        }
    };
}
