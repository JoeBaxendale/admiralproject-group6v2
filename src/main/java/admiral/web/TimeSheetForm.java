package admiral.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSheetForm {

    @NotNull
    private String number_of_days;

    @NotNull
    private String overtime;
}
