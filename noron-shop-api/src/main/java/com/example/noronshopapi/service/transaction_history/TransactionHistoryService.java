package com.example.noronshopapi.service.transaction_history;

import com.example.noronshopapi.service.AbsService;

import com.example.noronshopcommons.data.dto.request.TransactionHistoryRequest;
import com.example.noronshopcommons.data.dto.response.TransactionHistoryResponse;
import com.example.noronshopcommons.data.mapper.transaction_history.TransactionHistoryMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.TransactionHistory;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.shop.IShopRepository;
import com.example.noronshoprepository.repository.transaction_history.TransactionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.noronshopcommons.data.constrant.TransactionHistory.TransactionHistoryMessageConstrant.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionHistoryService extends AbsService<TransactionHistoryRequest, TransactionHistoryResponse, TransactionHistory, Integer, TransactionHistoryRepository, TransactionHistoryMapper> implements ITransactionHistoryService {

    private IShopRepository shopRepository;

    @Autowired
    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository, TransactionHistoryMapper transactionHistoryMapper, IShopRepository shopRepository) {
        this.repository = transactionHistoryRepository;
        this.mapper = transactionHistoryMapper;
        this.shopRepository = shopRepository;
    }

    @Override
    public Page<TransactionHistoryResponse> search(SearchRequest searchRequest) {
        try {
            List<TransactionHistory> transactionHistories = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            if (total == 0) {
                throw new ApiException(TRANSACTION_HISTORY_NULL);
            } else if (transactionHistories.isEmpty()) {
                throw new ApiException(TRANSACTION_HISTORY_NOT_FOUND);
            } else {
                List<Integer> shopIds = CollectionUtils.extractField(transactionHistories, TransactionHistory::getShopId);
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                Map<Integer, Shop> shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                List<TransactionHistoryResponse> histories = mapper.toResponseCustom(transactionHistories, shopMap);
                return new Page<TransactionHistoryResponse>()
                        .setTotal(total)
                        .setItems(histories);
            }
        } catch (ApiException apiException) {
            log.error("[ERROR] search{} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage());
        }
    }
}
