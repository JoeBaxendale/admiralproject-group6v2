package admiral.data;

import admiral.domain.TimeSheet;
import admiral.service.TimeSheetRepo;
import admiral.service.events.TimeSheetMade;
import org.springframework.stereotype.Repository;

@Repository
public class TimeSheetRepoJPAAdaptor implements TimeSheetRepo {

    private TimeSheetRepoJPA timeSheetRepoJPA;

    public TimeSheetRepoJPAAdaptor(TimeSheetRepoJPA aRepo) {
        timeSheetRepoJPA = aRepo;
    }


    @Override
    public void saveTimeSheetEvent(TimeSheetMade timeSheetMade) {



    }

}
