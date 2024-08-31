package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Integer id;
    private Integer productId;
    private ProductResponse productResponse;
    private String content;
    private Long ownerId;
    private String ownerName;
    private Integer numSubComment;
    private Integer numLike;
    private Integer numDislike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer verified;
    private String shortDescription;
    private Long parentId;
    private LocalDateTime deletedAt;
}
