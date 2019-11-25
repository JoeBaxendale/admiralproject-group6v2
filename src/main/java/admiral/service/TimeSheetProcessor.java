package admiral.service;


import admiral.service.events.TimeSheetMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimeSheetProcessor implements TimeSheetCreator {

    private TimeSheetRepository timeSheetRepository;

    public TimeSheetProcessor(TimeSheetRepository aRepo){
        timeSheetRepository = aRepo;
    }

    public void makeTimeSheet(TimeSheetMade timeSheet) {

        timeSheetRepository.saveTimeSheetEvent(timeSheet);
    }

}
