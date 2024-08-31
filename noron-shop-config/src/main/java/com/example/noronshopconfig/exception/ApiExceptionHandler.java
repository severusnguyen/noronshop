package com.example.noronshopconfig.exception;

import com.example.noronshopcommons.data.modal.DfResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<DfResponse> TodoException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(DfResponse.badRequest(ex.getMessage()));
    }
}
