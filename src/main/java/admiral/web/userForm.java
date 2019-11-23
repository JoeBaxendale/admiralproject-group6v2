package admiral.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class userForm {

    private int userId;
    private String firstName;
    private String lastName;
    private String staffEmail;
    private String password;
    private Boolean status;
}
