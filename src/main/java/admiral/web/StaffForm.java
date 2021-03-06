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
public class StaffForm {

    //------------------------------------------------------------------------------------------------------------------
    // Validates the number of days is less than a week
    @NotNull(message = "Manager Cannot be empty")
    private int manager_id;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Saturday for notes
    @NotBlank(message = "First name Cannot be empty")
    private String first_name;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Sunday for notes
    @NotBlank(message = "Last name Cannot be empty")
    private String last_name;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Sunday for notes
    @NotNull(message = "Email Cannot be empty")
    @Email
    private String email;
}
