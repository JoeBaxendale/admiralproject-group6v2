package admiral.service.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TimeSheetMade {

    private int number_of_days;
    private int overtime;
    private LocalDate start_date;
    private LocalDate end_date;
    private String notes;
}
