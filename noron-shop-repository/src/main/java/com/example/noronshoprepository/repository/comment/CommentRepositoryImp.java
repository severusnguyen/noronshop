package com.example.noronshoprepository.repository.comment;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Comment;
import com.example.noronshopcommons.data.tables.records.CommentRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImp extends AbsRepository<CommentRecord, Comment, Integer> implements ICommentRepository{

    @Override
    protected TableImpl<CommentRecord> getTable() {
        return Tables.COMMENT;
    }
}
