package com.example.noronshoprepository.repository;

import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.records.CategoryRecord;
import com.example.noronshoprepository.repository.utils.MysqlUtil;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.example.noronshopcommons.data.tables.Category.CATEGORY;
import static com.example.noronshopcommons.data.tables.Shop.SHOP;

@Repository
public class CategoryRepository extends AbsRepository<CategoryRecord, Category, Integer>{

    @Override
    protected TableImpl<CategoryRecord> getTable() {
        return CATEGORY;
    }

    public Map<Integer, Category> getCategoryByIds(List<Integer> categoryIds){
        return dslContext.selectFrom(CATEGORY)
                .where(CATEGORY.ID.in(categoryIds))
                .fetchMap(CATEGORY.ID, Category.class);
    }
}
