package project.aurora.auth.services.contracts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.aurora.auth.models.DTOs.user.UserCreationDTO;
import project.aurora.auth.models.User;

public interface IAuthService {
    void signUserIn(User user, HttpServletRequest request, HttpServletResponse response);
    void signUserUp(User user, UserCreationDTO userCreationDTO, HttpServletRequest request, HttpServletResponse response);
    void signUserOut(HttpServletRequest request, HttpServletResponse response);
}
