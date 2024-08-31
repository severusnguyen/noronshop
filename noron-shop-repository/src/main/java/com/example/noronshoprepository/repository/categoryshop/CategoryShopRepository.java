package com.example.noronshoprepository.repository.categoryshop;


import static com.example.noronshopcommons.data.Tables.CATEGORY_SHOP;
import com.example.noronshopcommons.data.tables.pojos.CategoryShop;
import com.example.noronshopcommons.data.tables.records.CategoryShopRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryShopRepository extends AbsRepository<CategoryShopRecord, CategoryShop, Integer> implements ICategoryShopRepositoty{
    @Override
    protected TableImpl<CategoryShopRecord> getTable() {
        return CATEGORY_SHOP;
    }

    @Override
    public List<CategoryShop> findByShopId(int shopId) {
        return dslContext.selectFrom(CATEGORY_SHOP)
                .where(CATEGORY_SHOP.SHOP_ID.eq(shopId))
                .fetchInto(CategoryShop.class);
    }

}
