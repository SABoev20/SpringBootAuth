package project.aurora.auth.models.DTOs.auth;

import lombok.Value;

@Value
public class OAuth2CallbackRequestDTO {
    String code;
    String provider;
}
