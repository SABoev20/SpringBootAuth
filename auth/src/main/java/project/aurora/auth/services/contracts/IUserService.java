package project.aurora.auth.services.contracts;

import project.aurora.auth.models.DTOs.user.UserRegistrationDTO;
import project.aurora.auth.models.User;

public interface IUserService {
    User authenticateUser(String email, String password);
    User findUserById(String uuid);
    User findUserByEmail(String email);
    User saveUser(User user);
    User createUser(String email, String password, String roleName);
}
