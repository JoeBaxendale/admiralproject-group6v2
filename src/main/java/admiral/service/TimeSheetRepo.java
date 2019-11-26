package admiral.service;

import admiral.service.events.TimeSheetMade;

public interface TimeSheetRepo {

    public void saveTimeSheetEvent(TimeSheetMade timeSheetMade);

}
