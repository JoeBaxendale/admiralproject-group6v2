package service;

import org.springframework.stereotype.Service;

@Service
public class TimeSheetQueries implements TimeSheetFinder {

    private TimeSheetRepo timeSheetRepo;

    public TimeSheetQueries(TimeSheetRepo aRepo){
        timeSheetRepo = aRepo;
    }

    public List<> findTimeSheetsByStatus(String searchTerm){
        return timeSheetRepo.findTimeSheetsByStatus(searchTerm);
    }

}
