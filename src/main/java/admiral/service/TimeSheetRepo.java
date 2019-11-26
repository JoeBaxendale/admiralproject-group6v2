package admiral.service;

import admiral.domain.TimeSheet;
import admiral.service.events.TimeSheetMade;

import java.util.List;

public interface TimeSheetRepo {

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm);

    public void saveTimeSheetEvent(TimeSheetMade timeSheetMade);
}
