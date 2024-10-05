package project.aurora.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ReauthenticationRequiredException extends RuntimeException {

    public ReauthenticationRequiredException(String message) {
        super(message);
    }
}
