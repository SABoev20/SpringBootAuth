package project.aurora.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationDeniedException extends RuntimeException{

    public AuthenticationDeniedException(String message) {
        super(message);
    }
}
