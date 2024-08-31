package com.example.noronshoprepository.repository.report;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.tables.pojos.Report;
import com.example.noronshopcommons.data.tables.records.ReportRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepositoryImp extends AbsRepository<ReportRecord, Report, Integer> {

    @Override
    protected TableImpl<ReportRecord> getTable() {
        return Tables.REPORT;
    }
}
