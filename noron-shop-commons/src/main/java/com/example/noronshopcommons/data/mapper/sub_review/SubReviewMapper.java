package com.example.noronshopcommons.data.mapper.sub_review;

import com.example.noronshopcommons.data.dto.request.SubReviewRequest;
import com.example.noronshopcommons.data.dto.response.SubReviewResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.SubReview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SubReviewMapper extends BaseMap<SubReviewRequest, SubReviewResponse, SubReview> {
}
