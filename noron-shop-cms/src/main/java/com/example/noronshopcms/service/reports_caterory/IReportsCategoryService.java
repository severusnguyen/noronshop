package com.example.noronshopcms.service.reports_caterory;

import com.example.noronshopcommons.data.dto.response.ReportsCategoryResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface IReportsCategoryService {
    Page<ReportsCategoryResponse> searchCustom(SearchRequest searchRequest);
}
