package admiral.data;

import admiral.DatabaseConnection;
import admiral.domain.TimeSheet;
import admiral.service.TimeSheetRepo;
import admiral.service.events.TimeSheetMade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeSheetRepoJDBC implements TimeSheetRepo {

    private JdbcTemplate jdbc;
    Connection conn;


    @Autowired
    TimeSheetRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;
        conn = new DatabaseConnection().getCon();
    }

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm){

        String sql = "SELECT * FROM timesheet WHERE status = '"+ searchTerm+"'" ;

        List<TimeSheet> timesheetList = new ArrayList<TimeSheet>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return timesheetList;

//        return jdbc.query(
//                "SELECT * FROM timesheets WHERE status = ?",
//                new Object[]{searchTerm},
//                timeSheetMapper
//        );
    };

    @Override
    public void saveTimeSheetEvent(TimeSheetMade timeSheetMade) {

        String sql = "INSERT INTO timesheet (contractor_id, number_days, overtime, start_date, end_date, date_submitted, notes, " +
                "status) VALUES (3, '"+ timeSheetMade.getNumber_of_days() +"', '"+ timeSheetMade.getOvertime() +"'," +
                " '"+ timeSheetMade.getStart_date() +"', '"+timeSheetMade.getEnd_date()+"', " +
                "'"+timeSheetMade.getEnd_date()+"', '"+timeSheetMade.getNotes() +"', 'Pending')";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DBG:----------------------------------------->>");
    }
}
