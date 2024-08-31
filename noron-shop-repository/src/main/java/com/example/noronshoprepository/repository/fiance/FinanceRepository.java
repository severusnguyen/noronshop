package com.example.noronshoprepository.repository.fiance;


import com.example.noronshopcommons.data.tables.pojos.Finance;
import com.example.noronshopcommons.data.tables.records.FinanceRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import static com.example.noronshopcommons.data.Tables.FINANCE;

@Repository
public class FinanceRepository extends AbsRepository<FinanceRecord, Finance, Integer> implements IFinanceRepository{
    @Override
    protected TableImpl<FinanceRecord> getTable() {
        return FINANCE;
    }
}
