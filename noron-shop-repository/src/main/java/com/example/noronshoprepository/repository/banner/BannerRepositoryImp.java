package com.example.noronshoprepository.repository.banner;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Banner;
import com.example.noronshopcommons.data.tables.records.BannerRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class BannerRepositoryImp extends AbsRepository<BannerRecord, Banner, Integer> {
    @Override
    protected TableImpl<BannerRecord> getTable() {
        return Tables.BANNER;
    }
}
