package com.example.noronshopcms.service.tiki_products;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.TikiProductsRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductsResponse;
import com.example.noronshopcommons.data.mapper.tiki_products.TikiProductsMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.TikiProducts;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import com.example.noronshoprepository.repository.tiki_products.TikiProductsRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.TikiProductsConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.TikiProductsConstant.NOT_FOUND;

@Service
@Slf4j
public class TikiProductsServiceImp extends AbsCmsService<TikiProductsRequest, TikiProductsResponse, TikiProducts, Integer, TikiProductsRepositoryImp, TikiProductsMapper>
                                    implements ITikiProductsService{

    @Autowired
    ProductRepositoryImpl productRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(TikiProducts.class);

    public TikiProductsServiceImp(TikiProductsRepositoryImp repositoryImp, TikiProductsMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public TikiProductsResponse update(Integer integer, TikiProductsRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public TikiProductsResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()) {
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<TikiProductsResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<TikiProducts> tikiProducts = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Product> productMap;
            List<TikiProductsResponse> tikiProductsResponses;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            }else {
                List<Integer> productIds = CollectionUtils.extractField(tikiProducts, TikiProducts::getProductId);
                List<Product> products = productRepository.getProductByIds(productIds);
                productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));
                tikiProductsResponses = mapper.toResponsesCustom(tikiProducts, productMap);

                return new Page<TikiProductsResponse>()
                        .setPage(searchRequest.getPage())
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(tikiProductsResponses);
            }
        } catch (ApiException e){
            LOGGER.error("[ERROR] Error occurred while searching tiki product: " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
