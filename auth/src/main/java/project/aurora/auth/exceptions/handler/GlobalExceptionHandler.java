package project.aurora.auth.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.aurora.auth.exceptions.AuthenticationDeniedException;
import project.aurora.auth.exceptions.EntityNotFoundException;
import project.aurora.auth.exceptions.InvalidCredentialsException;
import project.aurora.auth.exceptions.ReauthenticationRequiredException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReauthenticationRequiredException.class)
    public ResponseEntity<Map<String, String>> handleReauthenticationRequiredException(ReauthenticationRequiredException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "ReauthenticationRequired");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "EntityNotFound");
        errorResponse.put("message" +  "Reauthentication required!", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationDeniedException(AuthenticationDeniedException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "AuthenticationDenied");
        errorResponse.put("message", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "InvalidCredentials");
        errorResponse.put("message", "Authentication failed: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
