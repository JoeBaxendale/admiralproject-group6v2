package admiral.service;

import admiral.domain.NotificationDetails;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the timesheet Notification repository, including all methods required to obtain the information
// needed to support timesheet notifications
public interface NotificationRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve notification information for a specified timesheet identity
    // The notification details will require obtaining information from multiple tables
    public NotificationDetails findNotificationDetailsById(int time_sheet_id);

}
