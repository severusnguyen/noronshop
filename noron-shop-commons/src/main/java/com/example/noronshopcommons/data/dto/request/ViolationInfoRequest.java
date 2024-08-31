package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViolationInfoRequest {
    private Integer id;
    private String reason;
    private Integer productId;
    private Integer shopId;
    private LocalDateTime createdAt;
}
