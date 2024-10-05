package project.aurora.auth.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.aurora.auth.models.DTOs.auth.LoginRequestDTO;
import project.aurora.auth.models.DTOs.user.UserCreationDTO;
import project.aurora.auth.models.DTOs.user.UserRegistrationDTO;
import project.aurora.auth.models.User;
import project.aurora.auth.models.constants.PathConstants;
import project.aurora.auth.services.contracts.*;

@RestController
@RequestMapping(PathConstants.AUTH_MAPPING_PATH)
public class AuthController {
    private final IUserService userService;
    private final IAuthService authService;

    public AuthController(IUserService userService, IAuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        authService.signUserIn(user, request, response);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationDTO userRegistrationDTO, HttpServletRequest request, HttpServletResponse response) {
        User user = userService.createUser(userRegistrationDTO.getEmail(), userRegistrationDTO.getPassword(), "LISTENER");
        UserCreationDTO userCreationDTO = new UserCreationDTO(userRegistrationDTO.getName(), null, userRegistrationDTO.getDateOfBirth());
        authService.signUserUp(user, userCreationDTO, request, response);

        return ResponseEntity.ok().build();
    }
}