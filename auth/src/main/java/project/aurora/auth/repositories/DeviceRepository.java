package project.aurora.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.aurora.auth.models.Device;
import project.aurora.auth.models.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    boolean existsByDeviceIdAndUser(UUID deviceId, User user);
}
