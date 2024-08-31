package com.example.noronshopapi.service.review;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
import com.example.noronshopcommons.data.mapper.review.ReviewMapper;
import com.example.noronshopcommons.data.tables.pojos.Review;
import com.example.noronshoprepository.repository.review.ReviewRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl extends AbsService<ReviewRequest, ReviewResponse,
        Review, Integer, ReviewRepositoryImpl, ReviewMapper> {

    public ReviewServiceImpl(ReviewRepositoryImpl repository, ReviewMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }
}
