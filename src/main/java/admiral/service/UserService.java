package admiral.service;

import admiral.domain.User;

public interface UserService {

    void saveUser(User user);
    boolean isUserAlreadyPresent(User user);

}
