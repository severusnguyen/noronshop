package com.example.noronshoprepository.repository.shop;

import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.records.ShopRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.noronshopcommons.data.tables.Shop.SHOP;

@Repository
public class ShopRepositoryImpl extends AbsRepository<ShopRecord, Shop, Integer> implements IShopRepository {
    @Override
    protected TableImpl<ShopRecord> getTable() {
        return SHOP;
    }

    public List<Shop> getShopByIds(List<Integer> shopIds){
        return dslContext.select(SHOP.ID, SHOP.NAME, SHOP.LOGO, SHOP.RATING, SHOP.PRODUCT_QUANTITY)
                .from(SHOP)
                .where(SHOP.ID.in(shopIds))
                .fetchInto(Shop.class);
    }
}
