package com.example.noronshopcms.service.finance;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.FinanceRequest;
import com.example.noronshopcommons.data.dto.response.FinanceResponse;
import com.example.noronshopcommons.data.mapper.finance.FinanceMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Finance;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.fiance.FinanceRepository;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.example.noronshopcommons.data.constrant.Finance.FinanceMessageConstrant.*;

@Service
@Slf4j
public class FinanceService extends AbsCmsService<FinanceRequest, FinanceResponse, Finance, Integer, FinanceRepository, FinanceMapper> implements IFinanceService {

    @Autowired
    ShopRepositoryImpl shopRepository;

    @Autowired
    public FinanceService(FinanceRepository financeRepository, ShopRepositoryImpl shopRepository, FinanceMapper financeMapper) {
        this.repository = financeRepository;
        this.mapper = financeMapper;
        this.shopRepository = shopRepository;
    }


    @Override
    public FinanceResponse update(Integer integer, FinanceRequest request) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(FINANCE_NOT_FOUND));
            Finance finance = mapper.toPOJO(request);
            int isUpdated = repository.update(finance, integer);
            if (isUpdated != 1) {
                throw new ApiException(FINANCE_UPDATE_FAILED);
            } else {
                Optional<Finance> financeOptional = repository.findById(integer);
                FinanceResponse financeResponse = mapper.toResponse(financeOptional.get());
                return financeResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] update{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public FinanceResponse deleteById(Integer integer) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(FINANCE_NOT_FOUND));
            int isDeleted = repository.delete(integer);
            if (isDeleted != 1) {
                throw new ApiException(FINANCE_DELETE_FAILED);
            } else {
                Optional<Finance> financeOptional = repository.findById(integer);
                FinanceResponse financeResponse = mapper.toResponse(financeOptional.get());
                return financeResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] deleteById{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public Page<FinanceResponse> search(SearchRequest searchRequest) {
        try {
            List<Finance> finances = repository.search(searchRequest);
            Long countFiance = repository.count(searchRequest);
            Map<Integer, Shop> shopMap = new HashMap<>();
            List<FinanceResponse> financeResponses = new ArrayList<>();
            if (finances.size() > 0) {
                throw new ApiException(FINANCE_NOT_FOUND, 404);
            } else {
                List<Integer> shopIds = CollectionUtils.extractField(finances, Finance::getShopId);
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                financeResponses = mapper.toCustomResponse(finances, shopMap);
                return new Page<FinanceResponse>()
                        .setTotal(countFiance)
                        .setItems(financeResponses);
            }
        } catch (ApiException exception) {
            log.error("[ERROR] search{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }
}
