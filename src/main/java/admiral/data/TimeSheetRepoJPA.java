package admiral.data;

import admiral.domain.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepoJPA extends JpaRepository<TimeSheet, Long> {

}
