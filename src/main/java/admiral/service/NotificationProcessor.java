package admiral.service;


import admiral.config.NotificationConfig;
import admiral.data.NotificationRepoJDBC;
import admiral.domain.NotificationDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

//------------------------------------------------------------------------------------------------------------------
// An implementation of the Notification interface. This will send to Microsoft Teams using a inbound Webhook URL
// that will be stored in configuration.
@Slf4j
@Service
public class NotificationProcessor implements NotificationInterface {

    // Link to a Notification repository
    private NotificationRepoJDBC notificationRepo;

    // Get the Notification configuration
    @Autowired
    private NotificationConfig notificationConfig;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Notification repository
    public NotificationProcessor(NotificationRepoJDBC aRepo){
        notificationRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to send TimeSheet notification to Microsoft Teams
    public void sendTimeSheetCreateNotification(int time_sheet_id) {

        NotificationDetails notificationDetails = notificationRepo.findNotificationDetailsById(time_sheet_id);

        // Construct a URL that links to the Timesheet dashboard which shows all the timesheets with Pending status.
        String dashboardURL = ServletUriComponentsBuilder.fromCurrentRequestUri()
                                    .replacePath("timeSheetDashboard").toUriString();

        // Construct a Microsoft Teams message card JSON string
        String notificationJson = "{\n" +
                "    \"@type\": \"MessageCard\",\n" +
                "    \"@context\": \"https://schema.org/extensions\",\n" +
                "    \"summary\": \"Timeheet Created\",\n" +
                "    \"themeColor\": \"0078D7\",\n" +
                "    \"title\": \"Timesheet created\",\n" +
                "    \"sections\": [\n" +
                "        {\n" +
                "            \"activityTitle\": \"" + notificationDetails.getContractor_name() + "\",\n" +
                "            \"activitySubtitle\": \"" + notificationDetails.getStart_date() + "\",\n" +
                "            \"facts\": [\n" +
                "                {\n" +
                "                    \"name\": \"Days worked:\",\n" +
                "                    \"value\": \"" + notificationDetails.getNumber_of_days() + "\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Overtime:\",\n" +
                "                    \"value\": \"" + notificationDetails.getOvertime() + "\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"name\": \"Manager:\",\n" +
                "                    \"value\": \"" + notificationDetails.getManager_name() + "\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"text\": \"A timesheet has been created. Please review.\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"potentialAction\": [\n" +
                "        {\n" +
                "            \"@type\": \"OpenUri\",\n" +
                "            \"name\": \"Review Timesheet\",\n" +
                "            \"targets\": [\n" +
                "                {\n" +
                "                    \"os\": \"default\",\n" +
                "                    \"uri\": \"" + dashboardURL + "\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // Post the JSON to the REST api provided by a Microsoft Teams inbound Webhook
        RestTemplate sendNotification = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(notificationJson,headers);

        ResponseEntity<String> response = sendNotification.postForEntity(notificationConfig.getWebHookURL(),
                notificationJson, String.class);

    }
}
