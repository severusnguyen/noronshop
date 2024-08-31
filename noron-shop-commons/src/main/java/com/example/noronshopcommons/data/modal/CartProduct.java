package com.example.noronshopcommons.data.modal;

import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CartProduct<T> {

    @JsonProperty("product")
    private T t;

    private Integer productId;
    private Integer quantity;
    private Integer totalAmount;
    private Integer totalCoin;
}
