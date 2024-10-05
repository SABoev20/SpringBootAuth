package project.aurora.auth.services.implementations;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;
import project.aurora.auth.models.User;
import project.aurora.auth.services.contracts.ICookieService;

import static project.aurora.auth.models.constants.TimeConstants.INVALIDATION_EXPIRY;

@Service
public class CookieService implements ICookieService {
    @Override
    public Cookie createSecuredCookie(String name, String value, String path, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(expiry);
        return cookie;
    }

    @Override
    public Cookie invalidateCookie(Cookie cookie) {
        return createSecuredCookie(cookie.getName(), cookie.getValue(), cookie.getPath(), INVALIDATION_EXPIRY);
    }

    @Override
    public void verifyDeviceCookie(User user, Cookie deviceCookie) {

    }
}
