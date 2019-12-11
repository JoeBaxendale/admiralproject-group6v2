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
// Paired Programming between Dan and Dexter 25/11/2019
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    //------------------------------------------------------------------------------------------------------------------
    // Validates the number of days is less than a week
    @NotNull(message = "Number of Days worked required")
    @Min(value = 0, message = "Cannot work longer than a week")
    @Max(value = 7, message = "Cannot work longer than a week")
    private int number_of_days;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Saturday for notes
    private Boolean worked_saturday;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Sunday for notes
    private Boolean worked_sunday;

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
