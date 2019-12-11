package admiral.domain;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

//----------------------------------------------------------------------------------------------------------------------
// Class to map the time sheet object to the database
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeSheet {

    //------------------------------------------------------------------------------------------------------------------
    // Unique identifier
    // Paired Programming between Dan and Dexter 25/11/2019
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int time_sheet_id;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "admiral_role_id")
    private int admiral_role_id;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "number_days")
    private int number_days;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "overtime")
    private int overtime;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "start_date")
    private LocalDate start_date;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "end_date")
    private LocalDate end_date;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "date_submitted")
    private LocalDate date_submitted;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "notes")
    private String notes;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "status")
    private String status;


}
