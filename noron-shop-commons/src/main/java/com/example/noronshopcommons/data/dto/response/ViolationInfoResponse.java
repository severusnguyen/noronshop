package com.example.noronshopcommons.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViolationInfoResponse {
    private Integer id;
    private String reason;
    private Integer productId;
    private Integer shopId;
    private LocalDateTime createdAt;
    private ProductResponse productResponse;
    private ShopResponse shopResponse;
}
