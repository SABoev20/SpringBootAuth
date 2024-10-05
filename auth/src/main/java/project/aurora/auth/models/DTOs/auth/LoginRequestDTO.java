package project.aurora.auth.models.DTOs.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class LoginRequestDTO {
    String email;
    String password;
}
