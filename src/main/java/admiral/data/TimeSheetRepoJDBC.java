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


    @Autowired
    TimeSheetRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;

    }

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm){

        Connection conn = new DatabaseConnection().getCon();

        String sql = "SELECT * FROM timesheet WHERE status = '"+ searchTerm+"'" ;

        List<TimeSheet> timesheetList = new ArrayList<TimeSheet>();
        try {
            Statement st = conn.createStatement();
            ResultSet temp = st.executeQuery(sql);

            while(temp.next()){
                timesheetList.add(new TimeSheet(temp.getInt(1),
                        temp.getInt(2),
                        temp.getInt(3),
                        temp.getDate(4),
                        temp.getDate(5),
                        temp.getDate(6),
                        temp.getString(7),
                        temp.getString(8)));

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

        jdbc.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                        PreparedStatement ps = con.prepareStatement(
                                "insert into timesheet values (numberOfDays, overtime, startDate, endDate," +
                                        " dateSubmitted, notes, status) values (?, ?, ?, ?, ?, ?, ?)");

                                ps.setInt(1, timeSheetMade.getNumber_of_days());
                                ps.setInt(2, timeSheetMade.getOvertime());
                                ps.setDate(3, timeSheetMade.getStart_date());
                                ps.setDate(4, timeSheetMade.getEnd_date());
                                ps.setDate(5, timeSheetMade.getEnd_date());
                                ps.setString(6, timeSheetMade.getNotes());
                                ps.setString(7, "Pending");
                                return ps;
                    }
                }

        );
        System.out.println("DBG:----------------------------------------->>");
    }
}
