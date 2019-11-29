package admiral.config;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//----------------------------------------------------------------------------------------------------------------------
// Class to assist in password encryption
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //------------------------------------------------------------------------------------------------------------------
    // defining the bean for password encoder, to be able to autowire.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
