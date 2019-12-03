package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.time.LocalDate;

//----------------------------------------------------------------------------------------------------------------------
// Form mapper with validation for the time sheet
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    //------------------------------------------------------------------------------------------------------------------
    // Validates the number of days is less than a week
    @NotNull(message = "Number of Days worked required")
    @Min(value = 0, message = "Value must be between 0 and 7")
    @Max(value = 7, message = "Value must be between 0 and 7")
    private int number_of_days;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Saturday for notes
    private String worked_saturday;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Sunday for notes
    private String worked_sunday;

    //------------------------------------------------------------------------------------------------------------------
    // Validates the overtime with reasonable numbers
    @NotNull(message = "Overtime required")
    @PositiveOrZero(message = "Overtime must be greater than or equal to 0")
    private int overtime;

    //------------------------------------------------------------------------------------------------------------------
    // Validates the start date by checking its in the past, correct pattern
    @NotNull(message = "Start Date required")
    @PastOrPresent(message = "Date must be today or earlier")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    //------------------------------------------------------------------------------------------------------------------
    // Validates the start date by checking its in the past, correct pattern
    @NotNull(message = "End Date required")
    @PastOrPresent(message = "Date must be today or earlier")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end_date;

    //------------------------------------------------------------------------------------------------------------------
    // Validates the notes column by checking if a reasonable size
    @Size(max = 500, message = "Notes must be less than 500 characters")
    private String notes;
}
