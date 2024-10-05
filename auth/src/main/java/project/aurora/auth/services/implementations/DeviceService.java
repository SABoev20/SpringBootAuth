package project.aurora.auth.services.implementations;

import jakarta.servlet.http.Cookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.aurora.auth.exceptions.EntityNotFoundException;
import project.aurora.auth.exceptions.ReauthenticationRequiredException;
import project.aurora.auth.models.Device;
import project.aurora.auth.models.User;
import org.springframework.stereotype.Service;
import project.aurora.auth.repositories.DeviceRepository;
import project.aurora.auth.services.contracts.IDeviceService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class
DeviceService implements IDeviceService {

    private final PasswordEncoder passwordEncoder;
    private final DeviceRepository deviceRepository;

    public DeviceService(PasswordEncoder passwordEncoder, DeviceRepository deviceRepository){
        this.passwordEncoder = passwordEncoder;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device registerNewDevice(User user, HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");

        // Extract device info from User-Agent header
        String browserName = extractBrowserName(userAgent);
        String operatingSystem = extractOperatingSystem(userAgent);
        String deviceType = determineDeviceType(userAgent);

        Device device = new Device();
        device.setUser(user);
        device.setBrowserName(browserName);
        device.setOperatingSystem(operatingSystem);
        device.setDeviceType(deviceType);
        device.setLastUsed(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    @Override
    public void updateDeviceLastUsed(String deviceId) {
        Device device = deviceRepository.findById(UUID.fromString(deviceId)).orElseThrow(() ->  new EntityNotFoundException("Device", deviceId));
        device.setLastUsed(LocalDateTime.now());
        deviceRepository.save(device);
    }

    @Override
    public void assignRefreshTokenToDevice(User user, String deviceId, String refreshToken) {
        Device device = deviceRepository.findById(UUID.fromString(deviceId)).orElseThrow(() -> new EntityNotFoundException("Device", deviceId));
        device.setRefreshToken(passwordEncoder.encode(refreshToken));
        deviceRepository.save(device);
    }

    @Override
    public boolean deviceMatches(String deviceId, String refreshToken){
        Device device = deviceRepository.findById(UUID.fromString(deviceId)).orElseThrow(() -> new EntityNotFoundException("Device", deviceId));
        return passwordEncoder.matches(refreshToken, device.getRefreshToken());
    }

    @Override
    public boolean deviceMatches(String deviceId, User user) {
        return deviceRepository.existsByDeviceIdAndUser(UUID.fromString(deviceId), user);
    }

    // Methods for extracting information
    private String extractBrowserName(String userAgent) {
        if (userAgent.contains("Chrome")) return "Chrome";
        if (userAgent.contains("Firefox")) return "Firefox";
        return "Unknown";
    }

    private String extractOperatingSystem(String userAgent) {
        if (userAgent.contains("Windows")) return "Windows";
        if (userAgent.contains("Mac")) return "MacOS";
        if (userAgent.contains("Linux")) return "Linux";
        return "Unknown";
    }

    private String determineDeviceType(String userAgent) {
        if (userAgent.contains("Mobi")) return "Mobile";
        return "Desktop";
    }
}
