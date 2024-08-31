package com.example.noronshopcms.service.tiki_products_price;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.dto.request.TikiProductPriceRequest;
import com.example.noronshopcommons.data.dto.response.TikiProductPriceResponse;
import com.example.noronshopcommons.data.mapper.tiki_products_price.TikiProductPriceMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.TikiProductPrice;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import com.example.noronshoprepository.repository.tiki_products_price.TikiProductPriceRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.TikiProductPriceConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.TikiProductPriceConstant.NOT_FOUND;

@Service
@Slf4j
public class TikiProductPriceServiceImp extends AbsCmsService<TikiProductPriceRequest, TikiProductPriceResponse, TikiProductPrice, Integer,
        TikiProductPriceRepositoryImp, TikiProductPriceMapper> implements ITikiProductPriceService {

    @Autowired
    ProductRepositoryImpl productRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(TikiProductPriceServiceImp.class);

    public TikiProductPriceServiceImp(TikiProductPriceRepositoryImp repositoryImp, TikiProductPriceMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Override
    public TikiProductPriceResponse update(Integer integer, TikiProductPriceRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Override
    public TikiProductPriceResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<TikiProductPriceResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<TikiProductPrice> tikiProductPrices = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Product> productMap;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> productIds = CollectionUtils.extractField(tikiProductPrices, TikiProductPrice::getProductId);
                List<Product> products = productRepository.getProductByIds(productIds);
                productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));
                List<TikiProductPriceResponse> tikiProductPriceResponses = mapper.toResponsesCustom(tikiProductPrices, productMap);

                return new Page<TikiProductPriceResponse>()
                        .setPage(searchRequest.getPage())
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(tikiProductPriceResponses);
            }

        } catch (ApiException e){
            LOGGER.error("[ERROR] Error occurred while searching tiki product price: " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
