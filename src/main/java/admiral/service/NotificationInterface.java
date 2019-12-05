package admiral.service;

//------------------------------------------------------------------------------------------------------------------
// Interface for timesheet notifications. It is intended that notification will be sent to Microsoft teams.
public interface NotificationInterface {

    //------------------------------------------------------------------------------------------------------------------
    // Method to send a notification to Microsoft Teams when the specified Time Sheet has been created.
    // The method implementation wil determine how this is sent (e.g. using Teams webhook)
    public void sendTimeSheetCreateNotification(int time_sheet_id);
}
