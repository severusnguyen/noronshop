package com.example.noronshoprepository.repository.reports_category;

import com.example.noronshopcommons.data.dto.request.ReportsCategoryRequest;
import com.example.noronshopcommons.data.dto.response.ReportsCategoryResponse;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.ReportsCategory;
import com.example.noronshopcommons.data.tables.pojos.Shop;

import java.util.List;
import java.util.Map;

public interface IReportsCategoryRepository {
    List<ReportsCategory> getReportsCategoryById(List<Integer> shopIds);
}
