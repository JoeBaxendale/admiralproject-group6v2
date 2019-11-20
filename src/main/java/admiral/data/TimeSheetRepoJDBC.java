package admiral.data;

import admiral.domain.TimeSheet;
import admiral.service.TimeSheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeSheetRepoJDBC implements TimeSheetRepo {

    private JdbcTemplate jdbc;

    private RowMapper<TimeSheet> timeSheetMapper;

    @Autowired
    TimeSheetRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;

        timeSheetMapper = (rs,i) ->(new TimeSheet(
                rs.getInt("timesheet_id"),
                rs.getInt("contractor_id"),
                rs.getInt("number_days"),
                rs.getInt("overtime"),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getDate("date_submitted"),
                rs.getString("notes"),
                rs.getString("status")
        ));
    }

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm){
        return jdbc.query(
                "SELECT * FROM timesheets WHERE status = ?",
                new Object[]{searchTerm},
                timeSheetMapper
        );
    };
}
