package service;

import domain.TimeSheet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSheetQueries implements TimeSheetFinder {

    private TimeSheetRepo timeSheetRepo;

    public TimeSheetQueries(TimeSheetRepo aRepo){
        timeSheetRepo = aRepo;
    }

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm){
        return timeSheetRepo.findTimeSheetsByStatus(searchTerm);
    }

}
