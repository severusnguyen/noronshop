package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.modal.OrderDetailId;
import lombok.Data;
import org.jooq.JSON;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FormReturnRequest {
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
