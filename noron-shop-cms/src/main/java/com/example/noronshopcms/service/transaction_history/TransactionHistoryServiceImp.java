package com.example.noronshopcms.service.transaction_history;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.TransactionHistoryRequest;
import com.example.noronshopcommons.data.dto.response.TransactionHistoryResponse;
import com.example.noronshopcommons.data.mapper.transaction_history.TransactionHistoryMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.TransactionHistory;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.account.UserRepositoryImp;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import com.example.noronshoprepository.repository.transaction_history.TransactionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.ViolationInfoConstant.NOT_FOUND;
import static com.example.noronshopcms.service.constant.TransactionHistoryConstant.FAIL_SEARCH;

@Service
@Slf4j
public class TransactionHistoryServiceImp extends AbsCmsService<TransactionHistoryRequest, TransactionHistoryResponse, TransactionHistory,
        Integer, TransactionHistoryRepository, TransactionHistoryMapper> implements ITransactionHistoryService{

    @Autowired
    ShopRepositoryImpl shopRepository;

    @Autowired
    UserRepositoryImp userRepositoryImp;

    public TransactionHistoryServiceImp(TransactionHistoryRepository repository, TransactionHistoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Log
    @Override
    public TransactionHistoryResponse update(Integer integer, TransactionHistoryRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public TransactionHistoryResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<TransactionHistoryResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<TransactionHistory> transactionHistories = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Shop> shopMap;
            Map<Integer, User> userMap;
            List<TransactionHistoryResponse> transactionHistoryResponses;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> shopIds = CollectionUtils.extractField(transactionHistories,TransactionHistory::getShopId);
                List<Long> userIds = CollectionUtils.extractField(transactionHistories, TransactionHistory::getUserId)
                        .stream()
                        .map(Integer::longValue)
                        .collect(Collectors.toList());
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                List<User> users = userRepositoryImp.findUserByUserIds(userIds);
                shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                userMap = users.stream()
                        .collect(Collectors.toMap(User::getId, Function.identity()));
                transactionHistoryResponses = mapper.toResponsesCustom(transactionHistories, shopMap, userMap);

                return new Page<TransactionHistoryResponse>()
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(transactionHistoryResponses);
            }
        } catch (ApiException e) {
            log.error("[ERROR] search {} " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
