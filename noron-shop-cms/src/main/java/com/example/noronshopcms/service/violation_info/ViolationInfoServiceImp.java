package com.example.noronshopcms.service.violation_info;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.ViolationInfoRequest;
import com.example.noronshopcommons.data.dto.response.ViolationInfoResponse;
import com.example.noronshopcommons.data.mapper.violidation_info.ViolationInfoMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.data.tables.pojos.ViolationInfo;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import com.example.noronshoprepository.repository.violation_info.ViolationInfoRepositoryImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.ViolationInfoConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.ViolationInfoConstant.NOT_FOUND;

@Service
public class ViolationInfoServiceImp extends AbsCmsService<ViolationInfoRequest, ViolationInfoResponse, ViolationInfo, Integer, ViolationInfoRepositoryImp, ViolationInfoMapper>
                                            implements IViolationInfoService{

    @Autowired
    ProductRepositoryImpl productRepository;

    @Autowired
    ShopRepositoryImpl shopRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(ViolationInfoServiceImp.class);

    public ViolationInfoServiceImp(ViolationInfoRepositoryImp repositoryImp, ViolationInfoMapper mapper) {
        this.mapper = mapper;
        this.repository = repositoryImp;
    }

    @Log
    @Override
    public ViolationInfoResponse update(Integer integer, ViolationInfoRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public ViolationInfoResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<ViolationInfoResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<ViolationInfo> violationInfos = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Product> productMap;
            Map<Integer, Shop> shopMap;
            List<ViolationInfoResponse> violationInfoResponses;
            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> productIds = CollectionUtils.extractField(violationInfos, ViolationInfo::getProductId);
                List<Integer> shopIds = CollectionUtils.extractField(violationInfos, ViolationInfo::getShopId);
                List<Product> products = productRepository.getProductByIds(productIds);
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));
                shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                violationInfoResponses = mapper.toResponsesCustom(violationInfos, productMap, shopMap);

                return new Page<ViolationInfoResponse>()
                        .setPage(searchRequest.getPage())
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(violationInfoResponses);
            }
        } catch (Exception e){
            LOGGER.error("[ERROR] search {} " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
