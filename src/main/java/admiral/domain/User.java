package admiral.domain;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

//----------------------------------------------------------------------------------------------------------------------
//Defining mapping of the table users
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    //------------------------------------------------------------------------------------------------------------------
    // Unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    //------------------------------------------------------------------------------------------------------------------
//    @NotNull(message = "First name is compulsory")
    @NotBlank(message = "First name is compulsory")
    @Column(name="first_name")
    private String firstName;

    //------------------------------------------------------------------------------------------------------------------
//    @NotNull(message = "Last name is compulsory")
    @NotBlank(message = "Last name is compulsory")
    @Column(name="last_name")
    private String lastName;

    //------------------------------------------------------------------------------------------------------------------
//    @NotNull(message = "Email is compulsory")
    @NotBlank(message = "Email is compulsory")
    @Email(message = "Email is invalid")
    @Column(name="email")
    private String email;

    //------------------------------------------------------------------------------------------------------------------
    @NotBlank(message = "Password is compulsory")
    @Length(min=5, message = "Password should be at least 5 characters")
    @Column(name="password")
    private String password;

    //------------------------------------------------------------------------------------------------------------------
    @NotNull(message = "Role ID is compulsory")
    @Column(name="role_id")
    private Integer roleId;

    //------------------------------------------------------------------------------------------------------------------
    @NotNull(message = "Active is compulsory")
    @Column(name="active")
    private Boolean active;

}
