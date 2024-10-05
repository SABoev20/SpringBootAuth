package project.aurora.auth.services.implementations;

import jakarta.servlet.http.Cookie;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import project.aurora.auth.config.JwtUtil;
import project.aurora.auth.exceptions.ReauthenticationRequiredException;
import project.aurora.auth.models.User;
import project.aurora.auth.services.contracts.ITokenService;
import project.aurora.auth.services.contracts.IUserService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Objects;

import static project.aurora.auth.models.constants.TimeConstants.*;

@Service
public class TokenService implements ITokenService {

    private final JwtUtil jwtUtil;
    private final IUserService userService;

    public TokenService(JwtUtil jwtUtil, IUserService userService){
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public String generateRefreshToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        return jwtUtil.generateToken(user.getUserId().toString(), claims, REFRESH_TOKEN_EXPIRY);
    }

    @Override
    public String generateAccessToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        return jwtUtil.generateToken(user.getUserId().toString(), claims, ACCESS_TOKEN_EXPIRY);
    }

    @Override
    public boolean shouldRotateRefreshToken(String refreshToken) {
        Jwt jwt = jwtUtil.decodeToken(refreshToken);
        Instant now = Instant.now();
        Instant expiration = jwt.getExpiresAt();

        if (Instant.now().isAfter(Objects.requireNonNull(expiration))) throw new ReauthenticationRequiredException("Refresh-token has expired");

        return now.isAfter(expiration.minusSeconds(REFRESH_TOKEN_ROTATION));
    }

    @Override
    public User getUserFromToken(String token){
        Jwt jwt = jwtUtil.decodeToken(token);
        return userService.findUserById(jwt.getSubject());
    }

}
