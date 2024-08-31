package com.example.noronshoprepository.repository.product;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.records.ProductRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.example.noronshopcommons.data.tables.Product.PRODUCT;
import static com.example.noronshopcommons.data.tables.Shop.SHOP;

@Repository
public class ProductRepositoryImpl extends AbsRepository<ProductRecord, Product, Integer> implements IProductRepository{
    @Override
    protected TableImpl<ProductRecord> getTable() {
        return PRODUCT;
    }

    @Override
    public List<Product> getProductByIds(List<Integer> ids) {
        return dslContext.selectFrom(PRODUCT)
                .where(Tables.PRODUCT.ID.in(ids))
                //.fetchMap(PRODUCT.ID, Product.class);
                .fetchInto(Product.class);
    }

    @Override
    public List<Product> getListProductByIds(Collection<Integer> ids) {
        return dslContext.selectFrom(PRODUCT)
                .where(Tables.PRODUCT.ID.in(ids))
                .fetchInto(Product.class);
    }

}
