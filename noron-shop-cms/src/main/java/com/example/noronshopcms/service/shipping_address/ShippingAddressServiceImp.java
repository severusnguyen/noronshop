package com.example.noronshopcms.service.shipping_address;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.ShippingAddressRequest;
import com.example.noronshopcommons.data.dto.response.ShippingAddressResponse;
import com.example.noronshopcommons.data.mapper.shipping_address.ShippingAddressMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.ShippingAddress;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.account.UserRepositoryImp;
import com.example.noronshoprepository.repository.shipping_address.ShippingAddressRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.ShippingAddressConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.ShippingAddressConstant.NOT_FOUND;

@Service
@Slf4j
public class ShippingAddressServiceImp extends AbsCmsService<ShippingAddressRequest, ShippingAddressResponse, ShippingAddress,
        Integer, ShippingAddressRepositoryImp, ShippingAddressMapper> implements IShippingAddressService{

    @Autowired
    UserRepositoryImp userRepositoryImp;

    public ShippingAddressServiceImp(ShippingAddressRepositoryImp repositoryImp, ShippingAddressMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public ShippingAddressResponse update(Integer integer, ShippingAddressRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public ShippingAddressResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<ShippingAddressResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<ShippingAddress> shippingAddresses = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, User> userMap;
            List<ShippingAddressResponse> shippingAddressResponses;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Long> userIds = (List<Long>) CollectionUtils.extractField(shippingAddresses, ShippingAddress::getUserId);
                List<User> users = userRepositoryImp.findUserByUserIds(userIds);
                userMap = users.stream()
                        .collect(Collectors.toMap(User::getId, Function.identity()));
                shippingAddressResponses = mapper.toResponsesCustom(shippingAddresses, userMap);

                return new Page<ShippingAddressResponse>()
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(shippingAddressResponses);
            }

        } catch (ApiException e){
            log.error("Error search {} " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
