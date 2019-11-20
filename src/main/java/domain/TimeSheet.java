package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TimeSheet {

    private int timesheetId;
    private int contractorId;
    private int number_of_days_worked;
    private int overtime;
    private Date start_date;
    private Date end_date;
    private Date date_submitted;
    private String notes;
    private String Status;
}
