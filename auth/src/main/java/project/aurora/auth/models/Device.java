package project.aurora.auth.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "devices")
public class Device {

    @Id
    @Column(name = "device_id", updatable = false, nullable = false)
    private UUID deviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "refresh_token", length = 255)
    private String refreshToken;

    @Column(name = "browser_name", length = 50)
    private String browserName;

    @Column(name = "operating_system", length = 50)
    private String operatingSystem;

    @Column(name = "device_type", length = 25)
    private String deviceType;

    @Column(name = "last_used", nullable = false)
    private LocalDateTime lastUsed = LocalDateTime.now();
}
