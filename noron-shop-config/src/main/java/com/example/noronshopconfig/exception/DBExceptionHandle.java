package com.example.noronshopconfig.exception;

import com.example.noronshopcommons.core.exception.DBException;
import com.example.noronshopcommons.data.modal.DfResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DBExceptionHandle {

    @ExceptionHandler(DBException.class)
    public ResponseEntity<DfResponse> TodoException(Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(DfResponse.badRequest(ex.getMessage()));
    }
}
