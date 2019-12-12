package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//----------------------------------------------------------------------------------------------------------------------
// Form mapper with validation for the time sheet
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Saturday for notes
    @NotNull(message = "Password Cannot be empty")
    @NotBlank(message = "Password Cannot be empty")
    private String password1;

    //------------------------------------------------------------------------------------------------------------------
    // Checks if the user worked Sunday for notes
    @NotNull(message = "Password Cannot be empty")
    @NotBlank(message = "Password Cannot be empty")
    private String password2;

}
