package admiral.service;
import admiral.domain.UserInfo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public interface UserInfoRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Finds and returns logged in user details via their email address
    public UserInfo findUserInfoByEmail(String emailAddress) throws UsernameNotFoundException;
}