package admiral.service;


import admiral.service.events.TimeSheetMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimeSheetProcessor implements TimeSheetCreator {

    private TimeSheetRepo timeSheetRepo;

    public TimeSheetProcessor(TimeSheetRepo aRepo){
        timeSheetRepo = aRepo;
    }

    public void makeTimeSheet(TimeSheetMade timeSheet) {

        timeSheetRepo.saveTimeSheetEvent(timeSheet);
    }

}
