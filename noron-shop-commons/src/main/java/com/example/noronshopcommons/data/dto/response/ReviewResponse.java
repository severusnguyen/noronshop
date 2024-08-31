package com.example.noronshopcommons.data.dto.response;

import com.example.noronshopcommons.data.modal.Image;
import lombok.Data;
import org.jooq.JSON;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewResponse {
    private Integer id;
    private Integer productId;
    private String content;
    private List<Image> images;
    private Integer rating;
    private Integer numLike;
    private String isPurchase;
    private Long ownerId;
    private LocalDateTime createdAt;
    private Integer numSubReview;
}
