package admiral.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TimeSheet {

    private int timesheetId;
    private int admiralRoleId;
    private int numberOfDays;
    private int overtime;
    private Date startDate;
    private Date endDate;
    private Date dateSubmitted;
    private String notes;
    private String status;
}
