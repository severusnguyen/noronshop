package com.example.noronshopcommons.data.dto.response;

import lombok.Data;

@Data
public class ErrorMessage {
    private int statusCode;
    private String message;

    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
