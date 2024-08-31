package com.example.noronshoprepository.repository.tiki_products;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.TikiProducts;
import com.example.noronshopcommons.data.tables.records.TikiProductsRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class TikiProductsRepositoryImp extends AbsRepository<TikiProductsRecord, TikiProducts, Integer> {

    @Override
    protected TableImpl<TikiProductsRecord> getTable() {
        return Tables.TIKI_PRODUCTS;
    }
}
