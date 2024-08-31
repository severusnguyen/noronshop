package com.example.noronshopcms.service.categoryshop_product;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.CateShopProductRequest;
import com.example.noronshopcommons.data.dto.response.CateShopProductResponse;
import com.example.noronshopcommons.data.mapper.categoryshop_product.CateShopProductMapper;
import com.example.noronshopcommons.data.tables.Category;
import com.example.noronshopcommons.data.tables.pojos.CategoryshopProduct;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.category_shop_product.CateShopProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.noronshopcommons.data.constrant.CategoryShopProduct.CategoryShopProductMessageConstrant.*;

@Service
@Slf4j
public class CateShopProductService extends AbsCmsService<CateShopProductRequest, CateShopProductResponse, CategoryshopProduct, Integer, CateShopProductRepo, CateShopProductMapper> {

    @Autowired
    public CateShopProductService(CateShopProductRepo cateShopProductRepo, CateShopProductMapper cateShopProductMapper) {
        this.repository = cateShopProductRepo;
        this.mapper = cateShopProductMapper;
    }

    @Override
    public CateShopProductResponse update(Integer integer, CateShopProductRequest request) {
        try{
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(CATEGORYSHOP_PRODUCT_NOT_FOUND, 404));

            CategoryshopProduct categoryshopProduct = mapper.toPOJO(request);
            int isUpdated = repository.update(categoryshopProduct, integer);
            if (isUpdated != 1){
                throw new ApiException(CATEGORYSHOP_PRODUCT_UPDATE_FAILED);
            }else{
                Optional<CategoryshopProduct> categoryshopProductOptional = repository.findById(integer);
                CateShopProductResponse cateShopProductResponse = mapper.toResponse(categoryshopProductOptional.get());
                return cateShopProductResponse;
            }
        }catch (ApiException exception){
            log.error("[ERROR] update{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public CateShopProductResponse deleteById(Integer integer) {
        try{
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(CATEGORYSHOP_PRODUCT_NOT_FOUND, 404));

            int isDeleted = repository.delete(integer);
            if (isDeleted != 1){
                throw new ApiException(CATEGORYSHOP_PRODUCT_DELETE_FAILED);
            }else{
                Optional<CategoryshopProduct> categoryshopProductOptional = repository.findById(integer);
                CateShopProductResponse cateShopProductResponse = mapper.toResponse(categoryshopProductOptional.get());
                return cateShopProductResponse;
            }
        }catch (ApiException exception){
            log.error("[ERROR] delete{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

}
