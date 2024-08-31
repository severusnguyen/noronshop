package com.example.noronshopcommons.data.dto.response;

import com.example.noronshopcommons.data.modal.CartProduct;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {
    private Integer id;
    private String cartToken;
    private LocalDateTime createdAt;
    private Integer updatedAt;
    private List<CartProduct> products;
}
