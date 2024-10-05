package project.aurora.auth.models.DTOs.user;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {
    private String name;
    private String profilePicture;
    private LocalDate dateOfBirth;
}
