package project.aurora.auth.services.contracts;

import jakarta.servlet.http.Cookie;
import project.aurora.auth.models.User;

public interface ICookieService{

    Cookie createSecuredCookie(String name, String value, String path, int expiry);

    Cookie invalidateCookie(Cookie cookie);

    void verifyDeviceCookie(User user, Cookie deviceCookie);
}
