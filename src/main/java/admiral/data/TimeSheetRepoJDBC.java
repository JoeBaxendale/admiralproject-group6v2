package admiral.data;

import admiral.DatabaseConnection;
import admiral.domain.TimeSheet;
import admiral.service.TimeSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
                        temp.getInt(4),
                        temp.getDate(5),
                        temp.getDate(6),
                        temp.getDate(7),
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
}
