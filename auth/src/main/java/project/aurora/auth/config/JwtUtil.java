package project.aurora.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class JwtUtil {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    @Autowired
    public JwtUtil(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public String generateToken(String subject, Map<String, Object> customClaims, int expiry) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry));

        customClaims.forEach(claimsBuilder::claim);

        JwtClaimsSet claims = claimsBuilder.build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Jwt decodeToken(String token) {
        return this.decoder.decode(token);
    }
}