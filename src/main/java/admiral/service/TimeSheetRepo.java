package admiral.service;

import admiral.domain.TimeSheet;

import java.util.List;

public interface TimeSheetRepo {

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm);


}
