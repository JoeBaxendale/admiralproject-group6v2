package admiral.service;

import admiral.service.events.TimeSheetMade;

public interface TimeSheetCreator {

    public void makeTimeSheet(TimeSheetMade timeSheet);

}
