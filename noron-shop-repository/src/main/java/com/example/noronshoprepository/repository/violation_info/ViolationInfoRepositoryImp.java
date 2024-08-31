package com.example.noronshoprepository.repository.violation_info;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.ViolationInfo;
import com.example.noronshopcommons.data.tables.records.ViolationInfoRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ViolationInfoRepositoryImp extends AbsRepository<ViolationInfoRecord, ViolationInfo, Integer> {

    @Override
    protected TableImpl<ViolationInfoRecord> getTable() {
        return Tables.VIOLATION_INFO;
    }
}
