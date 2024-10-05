/*
package project.aurora.auth.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "oauth2_providers")
public class OAuth2Provider {

    @Id
    @Column(name = "provider_id", updatable = false, nullable = false)
    private UUID providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "provider_name", length = 10, nullable = false)
    private String providerName;

    @Column(name = "provided_user_id", length = 255, nullable = false)
    private String providedUserId;
}
*/
