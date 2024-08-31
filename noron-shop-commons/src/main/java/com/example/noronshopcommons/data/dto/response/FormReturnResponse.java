package com.example.noronshopcommons.data.dto.response;

import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.modal.OrderDetailId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormReturnResponse {
    private Integer id;
    private String code;
    private Double totalCoinReturn;
    private String reason;
    private List<Image> images;
    private Integer orderId;
    private Long userId;
    private String status;
    private List<OrderDetailId> orderDetailId;
}
