package com.example.noronshopcommons.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubReviewResponse {
    private Integer id;
    private Integer reviewId;
    private String content;
    private Long ownerId;
    private String ownerName;
    private Integer numLike;
    private Integer numDislike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer verified;
    private String shortDescription;
    private LocalDateTime deletedAt;
}
