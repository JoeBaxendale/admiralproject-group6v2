package admiral.config;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//----------------------------------------------------------------------------------------------------------------------
// Class to obtain configuration to remember me functionality.
// All config will exist under a 'rememberme.' prefix in the config file
@Configuration
@ConfigurationProperties(prefix = "rememberme")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RememberMeConfig {
    //----------------------------------------------------------------------------------------------------------------------
    // The remember me unique secret.
    private String secret;
    //----------------------------------------------------------------------------------------------------------------------
    // The remember me token validity period in seconds.
    private int validityPeriod;
}