package com.example.noronshopcommons.data.dto.response;

import lombok.Data;

@Data
public class SuccessMessage {
    private int code;
    private String message;

    public SuccessMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
