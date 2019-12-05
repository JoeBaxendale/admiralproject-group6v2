package admiral.config;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//----------------------------------------------------------------------------------------------------------------------
// Class to obtain configuration to support timesheet notifications.
// All config will exist under a 'notification.' prefix in the config file
@Configuration
@ConfigurationProperties(prefix = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationConfig {


    //----------------------------------------------------------------------------------------------------------------------
    // The incoming webhook URL generated from MS Teams. Used to post notifications.
    private String webHookURL;

}
