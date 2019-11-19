package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import service.TimeSheetRepo;

@Repository
public class TimeSheetRepoJDBC implements TimeSheetRepo {

    private JdbcTemplate jdbc;

    private RowMapper<> timeSheetMapper;

    @Autowired
    TimeSheetRepoJDBC(JdbcTemplate aTemplate){
        jdbc = aTemplate;

        timeSheetMapper = (rs,i) ->();
    }

    public List<> findTimeSheetsByStatus(String searchTerm){
        return jdbc.query(
                "SELECT * FROM *timesheets* WHERE status = ?",
                new Object[]{searchTerm},
                timeSheetMapper
        );
    };
}
