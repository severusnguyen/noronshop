package com.example.noronshoprepository.repository.book_info;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.BookInfo;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.records.BookInfoRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.example.noronshopcommons.data.tables.Category.CATEGORY;

@Repository
public class BookInfoRepositoryImp extends AbsRepository<BookInfoRecord, BookInfo, Integer> implements IBookInfoRepository{
    @Override
    protected TableImpl<BookInfoRecord> getTable() {
        return Tables.BOOK_INFO;
    }


}
