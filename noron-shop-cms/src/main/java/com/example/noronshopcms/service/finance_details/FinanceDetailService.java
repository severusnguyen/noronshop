package com.example.noronshopcms.service.finance_details;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.dto.request.FinanceDetailRequest;
import com.example.noronshopcommons.data.dto.response.FinanceDetailResponse;
import com.example.noronshopcommons.data.mapper.finance_details_mapper.FinanceDetailMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.FinanceDetail;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.finance_details.FinanceDetailRepository;
import com.example.noronshoprepository.repository.order.OrderRepositoryImp;
import com.example.noronshoprepository.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcommons.data.constrant.FinanceDetail.FinanceDetailMessageConstrant.*;

@Service
@Slf4j
public class FinanceDetailService extends AbsCmsService<FinanceDetailRequest, FinanceDetailResponse, FinanceDetail, Integer, FinanceDetailRepository, FinanceDetailMapper> {

    private OrderRepositoryImp orderRepositoryImp;

    private UserRepository userRepository;

    @Autowired
    public FinanceDetailService(FinanceDetailRepository financeDetailRepository, FinanceDetailMapper financeDetailMapper, OrderRepositoryImp orderRepositoryImp, UserRepository userRepository) {
        this.repository = financeDetailRepository;
        this.mapper = financeDetailMapper;
        this.orderRepositoryImp = orderRepositoryImp;
        this.userRepository = userRepository;
    }

    @Override
    public FinanceDetailResponse update(Integer integer, FinanceDetailRequest request) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(FINANCE_DETAIL_NOT_FOUND));
            FinanceDetail financeDetail = mapper.toPOJO(request);
            int isUpdated = repository.update(financeDetail, integer);
            if (isUpdated != 1){
                throw new ApiException(FINANCE_DETAIL_UPDATE_FAILED);
            }else{
                Optional<FinanceDetail> financeDetailOptional = repository.findById(integer);
                FinanceDetailResponse financeDetailResponse = mapper.toResponse(financeDetailOptional.get());
                return financeDetailResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] update{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public FinanceDetailResponse deleteById(Integer integer) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(FINANCE_DETAIL_NOT_FOUND));
            int isDeleted = repository.delete(integer);
            if (isDeleted != 1){
                throw new ApiException(FINANCE_DETAIL_DELETE_FAILED);
            }else{
                Optional<FinanceDetail> financeDetailOptional = repository.findById(integer);
                FinanceDetailResponse financeDetailResponse = mapper.toResponse(financeDetailOptional.get());
                return financeDetailResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] delete{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public Page<FinanceDetailResponse> search(SearchRequest searchRequest) {
        try {
            List<FinanceDetail> financeDetails = repository.search(searchRequest);
            Long countFinanceDetail = repository.count(searchRequest);
            Map<Integer, Order> orderMap = new HashMap<>();
            Map<Integer, User> userMap = new HashMap<>();
            List<FinanceDetailResponse> financeDetailResponses = new ArrayList<>();
            if (financeDetails.size() <= 0) {
                throw new ApiException(FINANCE_DETAIL_NOT_FOUND);
            }else{
                List<Integer> orderIds = CollectionUtils.extractField(financeDetails, FinanceDetail::getOrderId);
                List<Integer> userIds = CollectionUtils.extractField(financeDetails, FinanceDetail::getUserId);
                List<Order> orders = orderRepositoryImp.getOrderByIds(orderIds);
                List<User> users = userRepository.getUserByIds(userIds);

                orderMap = orders.stream().collect(Collectors.toMap(Order::getId, Function.identity()));
                userMap = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));

                financeDetailResponses = mapper.toCustomResponse(financeDetails, orderMap, userMap);
                return new Page<FinanceDetailResponse>()
                        .setTotal(countFinanceDetail)
                        .setItems(financeDetailResponses);
            }
        } catch (Exception exception) {
            log.error("[ERROR] search{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }
}
