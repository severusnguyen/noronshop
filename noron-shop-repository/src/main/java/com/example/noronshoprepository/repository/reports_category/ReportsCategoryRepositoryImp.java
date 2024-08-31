package com.example.noronshoprepository.repository.reports_category;

import com.example.noronshopcommons.data.Tables;
import com.example.noronshopcommons.data.dto.request.ReportsCategoryRequest;
import com.example.noronshopcommons.data.dto.response.ReportsCategoryResponse;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.ReportsCategory;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.records.ReportsCategoryRecord;
import com.example.noronshoprepository.repository.AbsRepository;
import org.jooq.DSLContext;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReportsCategoryRepositoryImp extends AbsRepository<ReportsCategoryRecord, ReportsCategory, Integer> implements IReportsCategoryRepository{

    @Override
    protected TableImpl<ReportsCategoryRecord> getTable() {
        return Tables.REPORTS_CATEGORY;
    }

    @Override
    public List<ReportsCategory> getReportsCategoryById(List<Integer> reportCateId) {
        return dslContext.select(Tables.REPORTS_CATEGORY.ID, Tables.REPORTS_CATEGORY.DESCRIPTION)
                .from(Tables.REPORTS_CATEGORY)
                .where(Tables.REPORTS_CATEGORY.ID.in(reportCateId))
                //.fetchMap(Tables.REPORTS_CATEGORY.ID, ReportsCategory.class);
                .fetchInto(ReportsCategory.class);
    }
}
