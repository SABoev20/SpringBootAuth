package project.aurora.auth.services.contracts;

import jakarta.servlet.http.Cookie;
import project.aurora.auth.models.User;

public interface ITokenService {
    String generateRefreshToken(User user);

    String generateAccessToken(User user);

    boolean shouldRotateRefreshToken(String refreshToken);

    User getUserFromToken(String token);
}
