package admiral.data;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

//----------------------------------------------------------------------------------------------------------------------
// Setting up spring security configuration using java config, to use
// JDBC based authentication and authorization
@Configuration
//to override the default configuration of spring security the annotation below is needed
@EnableWebSecurity
@Slf4j
public class SecurityConfigurationJDBC extends WebSecurityConfigurerAdapter {

    // static final Logger LOG = LoggerFactory.getLogger(SecurityConfigurationJDBC.class);

    //------------------------------------------------------------------------------------------------------------------
    // Attribute to encode the password
    @Autowired
    private PasswordEncoder passwordEncoder;

    //------------------------------------------------------------------------------------------------------------------
    // Attribute to identify the source
    @Autowired
    private DataSource dataSource;

    //------------------------------------------------------------------------------------------------------------------
    // queries from application properties
    @Value("${spring.users-query}")
    private String usersQuery;

    //------------------------------------------------------------------------------------------------------------------
    // Sql query to identify role
    @Value("${spring.roles-query}")
    private String rolesQuery;

    //------------------------------------------------------------------------------------------------------------------
    // Encoding the password
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Configure method for DB access from http
    @Override
    protected void configure (HttpSecurity http) throws Exception{

        //--------------------------------------------------------------------------------------------------------------
        // URLs matching for access rights
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/Timesheet/**").hasAnyAuthority("Manager", "Admin", "Contractor")
//                .antMatchers("/Timesheet/**").hasAnyAuthority("Manager", "Admin")
                .anyRequest().authenticated()
                .and()

                //------------------------------------------------------------------------------------------------------
                // form login
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/Timesheet")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()

                //------------------------------------------------------------------------------------------------------
                // Form logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    //------------------------------------------------------------------------------------------------------------------
    // Configure method for DB access from WebSecurity
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/images/**",
                "/fonts.poppins/**", "/js/**", "/vendor/**");
    }
}

//Page used for the code above https://tutorials.webencyclop.com/spring-boot/03-create-user-login-registration/
