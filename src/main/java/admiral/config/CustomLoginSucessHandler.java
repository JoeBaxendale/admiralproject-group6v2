package admiral.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Configuration
public class CustomLoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    HttpSession session;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response,
                          Authentication authentication) throws IOException{

        this.session = request.getSession();

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication){
        String url = "/login?error=true";

//        Fetch the roles from authentication object
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a: authorities) {
            roles.add(a.getAuthority());
        }

        session.setAttribute("loginEmail",authentication.getName());    //add the email used to login to the session for later use

//        Check user role and decide the redirect URL
        if (roles.contains("Admin")) {
            url = "/timesheetDashboard";
        }
        else if (roles.contains("Manager")) {
            url = "/timesheetDashboard";
        }
        else if (roles.contains("Contractor")) {
            url = "/Timesheet";
        }
        return url;
    }
}
