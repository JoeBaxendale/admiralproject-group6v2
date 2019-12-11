package admiral.service;

import admiral.domain.User;
import org.springframework.stereotype.Service;

public interface UserService {

    void saveUser(User user);
    boolean isUserAlreadyPresent(User user);

}
