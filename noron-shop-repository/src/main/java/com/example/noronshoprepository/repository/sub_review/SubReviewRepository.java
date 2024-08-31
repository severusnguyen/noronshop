package com.example.noronshoprepository.repository.sub_review;

import com.example.noronshopcommons.data.tables.pojos.SubReview;
import com.example.noronshopcommons.data.tables.records.SubReviewRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.tables.SubReview.SUB_REVIEW;

@Repository
public class SubReviewRepository extends AbsRepository<SubReviewRecord, SubReview, Integer> implements ISubReviewRepository{

    @Override
    protected TableImpl<SubReviewRecord> getTable() {
        return SUB_REVIEW;
    }
}
