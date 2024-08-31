package com.example.noronshopcommons.data.mapper.review;


import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.BusinessItemsRequest;
import com.example.noronshopcommons.data.dto.request.IdentityCardRequest;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.request.ShopRequest;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.tables.pojos.Review;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper extends BaseMap<ReviewRequest, ReviewResponse, Review> {

    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "images", ignore = true),
    })
    public abstract Review toPOJO(ReviewRequest request);

    @Override
    @Named("toResponse")
    @Mappings({
            @Mapping(target = "images", ignore = true),
    })
    public abstract ReviewResponse toResponse(Review pojo);

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget Review review, ReviewRequest reviewRequest){
        if (reviewRequest.getImages() != null){
            String images = JsonMapper.getObjectMapper().writeValueAsString(reviewRequest.getImages());
            review.setImages(JSON.valueOf(images));
        }
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ReviewResponse reviewResponse, Review review){
        reviewResponse.setImages(CollectionUtils.getOrDefaultList(Image.class, review.getImages(), new ArrayList<>()));
    }

}
