package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.modal.Info;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jooq.JSON;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductRequest {
    private Integer id;
    private String handle;
    private Integer categoryId;
    private String title;
    private String description;
    private String type;
    private String tags;
    private Integer numReview;
    private Double rating;
    private List<Image> images;
    private List<Info> infos;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime createdAt;
    private Long createdBy;
    private Long updatedBy;
    private Double originPrice;
    private Double finalPrice;
    private Double discount;
    private Double originCoin;
    private Double finalCoin;
    private Integer numComment;
    private String shortDescription;
    private Integer numView;
    private Integer numBuy;
    private Integer vendorId;
    private String status;
    private Integer shopId;
    private Integer categoryShopId;
    private String origin;
    private String code;
    private Integer itemsCount;
    private Integer productShipInfoId;

}
