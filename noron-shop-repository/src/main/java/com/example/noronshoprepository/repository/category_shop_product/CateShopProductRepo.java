package com.example.noronshoprepository.repository.category_shop_product;

import static com.example.noronshopcommons.data.Tables.CATEGORYSHOP_PRODUCT;

import com.example.noronshopcommons.data.tables.pojos.CategoryshopProduct;
import com.example.noronshopcommons.data.tables.records.CategoryshopProductRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CateShopProductRepo extends AbsRepository<CategoryshopProductRecord, CategoryshopProduct, Integer> implements ICateShopProductRepo {

    @Override
    protected TableImpl<CategoryshopProductRecord> getTable() {
        return CATEGORYSHOP_PRODUCT;
    }

    @Override
    public Result<Record2<Integer, Integer>> countProduct(List<Integer> idCategoryShop) {
        return dslContext.select(CATEGORYSHOP_PRODUCT.CATEGORY_SHOP_ID, CATEGORYSHOP_PRODUCT.PRODUCT_ID.count())
                .from(CATEGORYSHOP_PRODUCT)
                .where(CATEGORYSHOP_PRODUCT.CATEGORY_SHOP_ID.in(idCategoryShop))
                .groupBy(CATEGORYSHOP_PRODUCT.CATEGORY_SHOP_ID)
                .fetch();
    }
}
