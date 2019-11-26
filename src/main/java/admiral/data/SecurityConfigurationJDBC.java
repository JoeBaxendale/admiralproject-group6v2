package admiral.data;

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

//setting up spring security configuration using java config, to use
//JDBC based authentication and authorization

@Configuration
//to override the default configuration of spring security the annotation below is needed
@EnableWebSecurity
@Slf4j
public class SecurityConfigurationJDBC extends WebSecurityConfigurerAdapter {

//    static final Logger LOG = LoggerFactory.getLogger(SecurityConfigurationJDBC.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

//    queries from application properties
    @Value("${spring.users-query}")
    private String usersQuery;

    @Value("${spring.roles-query}")
    private String rolesQuery;

    //    encoding the password
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{

        http.authorizeRequests()
//                URLs matching for access rights
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/Timesheet/**").hasAnyAuthority("Manager", "Admin", "Contractor")
                .anyRequest().authenticated()
                .and()
//                form login
                .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/Timesheet")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
//                form logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/images/**");
    }

}

//Page used for the code above https://tutorials.webencyclop.com/spring-boot/03-create-user-login-registration/
