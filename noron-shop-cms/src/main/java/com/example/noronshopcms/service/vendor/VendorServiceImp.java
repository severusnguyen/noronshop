package com.example.noronshopcms.service.vendor;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.VendorRequest;
import com.example.noronshopcommons.data.dto.response.VendorResponse;
import com.example.noronshopcommons.data.mapper.vendor.VendorMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.Vendor;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.vendor.VendorRepositoryImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.VendorConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.VendorConstant.NOT_FOUND;

@Service
public class VendorServiceImp extends AbsCmsService<VendorRequest, VendorResponse, Vendor, Integer, VendorRepositoryImp, VendorMapper> implements IVendorService{

    @Autowired
    ShopRepositoryImpl shopRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImp.class);

    public VendorServiceImp(VendorRepositoryImp repositoryImp, VendorMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public VendorResponse update(Integer integer, VendorRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public VendorResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<VendorResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<Vendor> vendors = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Shop> shopMap;
            List<VendorResponse> vendorResponses;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> shopIds = CollectionUtils.extractField(vendors, Vendor::getShopId);
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                vendorResponses = mapper.toResponsesCustom(vendors, shopMap);

                return new Page<VendorResponse>()
                        .setPage(searchRequest.getPage())
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(vendorResponses);
            }
        } catch (ApiException e){
            LOGGER.error("[ERROR] search {} " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
