package org.kst.lms.exceptions;

import org.kst.lms.dtos.CustomResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceAlreadyProcessedException.class})
    public ResponseEntity<CustomResponseBody> conflictRequestExceptionHandler(Exception ex, WebRequest request) {
        CustomResponseBody response = new CustomResponseBody();
        response.setStatus(HttpStatus.CONFLICT.name());
        response.setMessage(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<CustomResponseBody> badRequestExceptionHandler(Exception ex, WebRequest request) {
        CustomResponseBody response = new CustomResponseBody();
        response.setStatus(HttpStatus.BAD_REQUEST.name());
        response.setMessage(ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler({UsernameNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<CustomResponseBody> resourceNotFoundExceptionsHandler(Exception ex, WebRequest request) {
        CustomResponseBody response = new CustomResponseBody();
        response.setStatus(HttpStatus.NOT_FOUND.name());
        response.setMessage(ex.getMessage());

        return ResponseEntity
                .notFound()
                .build();
    }
}
