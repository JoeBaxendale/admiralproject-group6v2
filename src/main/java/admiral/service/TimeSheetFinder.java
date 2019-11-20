package admiral.service;

import admiral.domain.TimeSheet;

import java.util.List;

public interface TimeSheetFinder {

    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm);

}
