package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.modal.CartProduct;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class CartRequest {
    private Integer id;
    private String cartToken;
    private LocalDateTime createdAt;
    private Integer updatedAt;
    private List<CartProduct> products;
    private Long userId;
}
