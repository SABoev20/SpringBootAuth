package project.aurora.auth.services.implementations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.aurora.auth.exceptions.AuthenticationDeniedException;
import project.aurora.auth.models.User;
import project.aurora.auth.repositories.RoleRepository;
import project.aurora.auth.repositories.UserRepository;
import project.aurora.auth.services.contracts.IUserService;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository ,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User authenticateUser(String email, String password) {
        Optional<User> optionalUser = Optional.ofNullable(findUserByEmail(email));
        if(optionalUser.isEmpty()) throw new AuthenticationDeniedException("This email address is not linked to an account");
        if(!passwordEncoder.matches(password, optionalUser.get().getPassword())) throw new AuthenticationDeniedException("Incorrect password");
        return optionalUser.get();
    }

    @Override
    public User findUserById(String uuid) {
        return userRepository.findByUserId(UUID.fromString(uuid));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User createUser(String email, String password, String roleName){
        return saveUser(new User(null, roleRepository.findByRoleName(roleName), email, password, true, null));
    }
}
