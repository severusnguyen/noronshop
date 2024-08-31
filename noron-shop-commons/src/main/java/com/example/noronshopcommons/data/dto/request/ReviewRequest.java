package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.modal.Image;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jooq.JSON;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ReviewRequest {
    private Integer id;
    private Integer productId;
    private String content;
    private List<Image> images;
    private Integer rating;
    private Integer numLike;
    private String isPurchase;
    private Long ownerId;
    private Integer numSubReview;
    private Integer orderDetailId;
}
