package com.example.noronshopcms.service.report;

import com.example.noronshopcommons.data.dto.response.ReportResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface IReportService {
    Page<ReportResponse> searchCustom(SearchRequest searchRequest);
}
