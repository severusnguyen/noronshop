package com.example.noronshopapi.service.sub_review;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.ReviewRequest;
import com.example.noronshopcommons.data.dto.request.SubReviewRequest;
import com.example.noronshopcommons.data.dto.response.ReviewResponse;
import com.example.noronshopcommons.data.dto.response.SubReviewResponse;
import com.example.noronshopcommons.data.mapper.review.ReviewMapper;
import com.example.noronshopcommons.data.mapper.sub_review.SubReviewMapper;
import com.example.noronshopcommons.data.tables.pojos.Review;
import com.example.noronshopcommons.data.tables.pojos.SubReview;
import com.example.noronshoprepository.repository.review.ReviewRepositoryImpl;
import com.example.noronshoprepository.repository.sub_review.SubReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class SubReviewServiceImpl extends AbsService<SubReviewRequest, SubReviewResponse,
        SubReview, Integer, SubReviewRepository, SubReviewMapper> {

    public SubReviewServiceImpl(SubReviewRepository repository, SubReviewMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }
}
