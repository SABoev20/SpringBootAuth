package project.aurora.auth.services.contracts;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import project.aurora.auth.models.Device;
import project.aurora.auth.models.User;

public interface IDeviceService {
    Device registerNewDevice(User user, HttpServletRequest request);
    void updateDeviceLastUsed(String deviceId);
    void assignRefreshTokenToDevice(User user, String deviceId, String refreshToken);
    boolean deviceMatches(String deviceId, String refreshToken);
    boolean deviceMatches(String deviceId, User user);
}
