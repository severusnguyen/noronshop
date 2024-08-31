package com.example.noronshopapi.service.transaction_history;

import com.example.noronshopapi.service.IService;
import com.example.noronshopcommons.data.dto.request.TransactionHistoryRequest;
import com.example.noronshopcommons.data.dto.response.TransactionHistoryResponse;
import com.example.noronshopcommons.data.tables.pojos.TransactionHistory;

public interface ITransactionHistoryService extends IService<TransactionHistoryRequest, TransactionHistoryResponse, Integer> {
}
