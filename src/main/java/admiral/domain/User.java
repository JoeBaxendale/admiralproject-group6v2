package admiral.domain;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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
    @Column(name="first_name")
    private String firstName;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="last_name")
    private String lastName;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="email")
    private String staffEmail;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="password")
    private String password;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="role_id")
    private String roleId;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="active")
    private Boolean active;
}
