package com.example.noronshoprepository.repository.tiki_products_price;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.dto.request.TikiProductPriceRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductPriceResponse;
import com.example.noronshopcommons.data.tables.pojos.TikiProductPrice;
import com.example.noronshopcommons.data.tables.records.TikiProductPriceRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class TikiProductPriceRepositoryImp extends AbsRepository<TikiProductPriceRecord, TikiProductPrice, Integer> {

    @Override
    protected TableImpl<TikiProductPriceRecord> getTable() {
        return Tables.TIKI_PRODUCT_PRICE;
    }
}
