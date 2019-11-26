package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.sql.Date;

//----------------------------------------------------------------------------------------------------------------------
// Form information with validation for the time sheet
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    //------------------------------------------------------------------------------------------------------------------
    @NotNull
    private String number_of_days;

    //------------------------------------------------------------------------------------------------------------------
    @NotNull
    private String overtime;

    //------------------------------------------------------------------------------------------------------------------
    @NotNull
    private Date start_date;

    //------------------------------------------------------------------------------------------------------------------
    @NotNull
    private Date end_date;

    //------------------------------------------------------------------------------------------------------------------
    private String notes;
}
