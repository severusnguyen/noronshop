package com.example.noronshoprepository.repository.review;

import com.example.noronshopcommons.data.tables.pojos.Review;
import com.example.noronshopcommons.data.tables.records.ReviewRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.tables.Review.REVIEW;

@Repository
public class ReviewRepositoryImpl extends AbsRepository<ReviewRecord, Review, Integer> implements IReviewRepository{

    @Override
    protected TableImpl<ReviewRecord> getTable() {
        return REVIEW;
    }

}
