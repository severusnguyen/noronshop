package com.example.noronshopcms.service.transaction_history;

import com.example.noronshopcommons.data.dto.response.TransactionHistoryResponse;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;

public interface ITransactionHistoryService {
    Page<TransactionHistoryResponse> searchCustom(SearchRequest searchRequest);
}
