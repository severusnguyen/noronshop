package com.example.noronshopcms.service.violation_info;

import com.example.noronshopcommons.data.dto.response.ViolationInfoResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface IViolationInfoService {
    Page<ViolationInfoResponse> searchCustom(SearchRequest searchRequest);
}
