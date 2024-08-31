package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubReviewRequest {
    private Integer id;
    private Integer reviewId;
    private String content;
    private Long ownerId;
    private String ownerName;
    private Integer numLike;
    private Integer numDislike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String shortDescription;
}
