package admiral.service.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class TimeSheetMade {

    private int number_of_days;
    private int overtime;
    private Date start_date;
    private Date end_date;
    private String notes;
}
