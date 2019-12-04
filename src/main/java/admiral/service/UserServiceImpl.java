package admiral.service;

import admiral.domain.Role;
import admiral.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepositoy userRepositoy;

    @Override
    public void saveUser (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("contractor");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepositoy.save(user);

    }

    @Override
    public boolean isUserAlreadyPresent (User user){
        return false;
    }
}
