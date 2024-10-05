package project.aurora.auth.services.implementations;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import project.aurora.auth.exceptions.InvalidCredentialsException;
import project.aurora.auth.models.DTOs.user.UserCreationDTO;
import project.aurora.auth.models.User;
import project.aurora.auth.services.contracts.*;

import static project.aurora.auth.exceptions.InvalidCredentialsException.CredentialType.COOKIE;
import static project.aurora.auth.models.constants.NameConstants.*;
import static project.aurora.auth.models.constants.PathConstants.BASE_PATH;
import static project.aurora.auth.models.constants.PathConstants.REFRESH_COOKIE_PATH;
import static project.aurora.auth.models.constants.TimeConstants.*;

@Service
public class AuthService implements IAuthService {
    private final IDeviceService deviceService;
    private final ITokenService tokenService;
    private final ICookieService cookieService;

    public AuthService(IDeviceService deviceService, ITokenService authService, ICookieService cookieService) {
        this.deviceService = deviceService;
        this.tokenService = authService;
        this.cookieService = cookieService;
    }

    public void signUserIn(User user, HttpServletRequest request, HttpServletResponse response){
        Cookie deviceCookie = WebUtils.getCookie(request, DEVICE_COOKIE_NAME);
        if(deviceCookie != null && !deviceService.deviceMatches(deviceCookie.getValue(), user)){
            response.addCookie(cookieService.invalidateCookie(deviceCookie));
            throw new InvalidCredentialsException(COOKIE);
        }

        String deviceId;
        if (deviceCookie == null) {
            deviceId = deviceService.registerNewDevice(user, request).getDeviceId().toString();
            response.addCookie(cookieService.createSecuredCookie(DEVICE_COOKIE_NAME, deviceId, BASE_PATH, DEVICE_EXPIRY));
        } else {
            deviceId = deviceCookie.getValue();
            deviceService.updateDeviceLastUsed(deviceId);
        }

        String refreshToken = tokenService.generateRefreshToken(user);
        String accessToken = tokenService.generateAccessToken(user);
        deviceService.assignRefreshTokenToDevice(user, deviceId, refreshToken);

        response.addCookie(cookieService.createSecuredCookie(REFRESH_COOKIE_NAME, refreshToken, REFRESH_COOKIE_PATH, REFRESH_TOKEN_EXPIRY));
        response.addCookie(cookieService.createSecuredCookie(ACCESS_COOKIE_NAME, accessToken, BASE_PATH, ACCESS_TOKEN_EXPIRY));
    }

    public void signUserUp(User user, UserCreationDTO userCreationDTO, HttpServletRequest request, HttpServletResponse response){
        // sent request to resource server to create user userCreationDTO
        signUserIn(user, request, response);
    }

    public void signUserOut(HttpServletRequest request, HttpServletResponse response){}
}
