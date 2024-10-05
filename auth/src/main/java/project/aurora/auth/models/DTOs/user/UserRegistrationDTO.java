package project.aurora.auth.models.DTOs.user;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserRegistrationDTO {
    String email;
    String password;
    String name;
    LocalDate dateOfBirth;
}
