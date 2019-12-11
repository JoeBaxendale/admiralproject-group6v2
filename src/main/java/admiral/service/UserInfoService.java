package admiral.service;

import admiral.data.UserRepoJDBC;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserInfoService implements UserDetailsService {
    // Link to a User repository
    private UserRepoJDBC userRepo;
    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the user  repository
    public UserInfoService(UserRepoJDBC aRepo){
        userRepo = aRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserInfoByEmail(username);
    }
}


