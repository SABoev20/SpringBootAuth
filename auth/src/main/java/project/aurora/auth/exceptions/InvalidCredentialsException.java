package project.aurora.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCredentialsException extends RuntimeException {
    public enum CredentialType {
        EMAIL, PASSWORD, COOKIE
    }

    public InvalidCredentialsException(CredentialType credentialType) {
        super(getDefaultMessage(credentialType));
    }

    public InvalidCredentialsException(CredentialType credentialType, String message) {
        super(message);
    }

    private static String getDefaultMessage(CredentialType credentialType) {
        return switch (credentialType) {
            case EMAIL -> "Invalid email provided";
            case PASSWORD -> "Invalid password provided";
            case COOKIE -> "Invalid cookie provided";
            default -> "Invalid credentials provided";
        };
    }
}
