package com.example.noronshopcms.service.category_shop;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.response.CategoryShopResponse;
import com.example.noronshopcommons.data.mapper.categoryshop.CategoryShopMapper;
import com.example.noronshopcommons.data.dto.request.CategoryShopRequest;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.CategoryShop;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.categoryshop.CategoryShopRepository;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcommons.data.constrant.CategoryShop.CategoryShopMessageConstrant.*;

@Service
@Slf4j
public class CategoryShopService extends AbsCmsService<CategoryShopRequest, CategoryShopResponse, CategoryShop, Integer, CategoryShopRepository, CategoryShopMapper> implements ICategoryShopService {

    private ShopRepositoryImpl shopRepository;

    @Autowired
    public CategoryShopService(CategoryShopRepository categoryShopRepository, CategoryShopMapper categoryShopMapper, ShopRepositoryImpl shopRepository) {
        this.repository = categoryShopRepository;
        this.mapper = categoryShopMapper;
        this.shopRepository = shopRepository;
    }

    @Override
    public List<CategoryShopResponse> findByShopId(int shopId) {
        List<CategoryShop> categoryShop = repository.findByShopId(shopId);
        List<CategoryShopResponse> categoryShopResponses = new ArrayList<>();
        try {
            if (categoryShop.size() == 0) {
                return new ArrayList<>();
            } else {
                categoryShopResponses = mapper.toResponses(categoryShop);
            }
        } catch (Exception e) {
            log.error("[ERROR] {findByShopId} " + e.getMessage());
        }
        return categoryShopResponses;

    }

    @Override
    public CategoryShopResponse update(Integer integer, CategoryShopRequest request) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(CATEGORY_SHOP_NOT_FOUND, 404));

            CategoryShop newCategoryShop = mapper.toPOJO(request);
            Integer isUpdated = repository.update(newCategoryShop, integer);
            if (isUpdated != 1){
                throw new ApiException(CATEGORY_SHOP_UPDATE_FAILED);
            }else{
                Optional<CategoryShop> categoryShopOptional = repository.findById(integer);
                CategoryShopResponse categoryShopResponse = mapper.toResponse(categoryShopOptional.get());
                return categoryShopResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] update{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public CategoryShopResponse deleteById(Integer integer) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(CATEGORY_SHOP_NOT_FOUND, 404));
            int isDeleted = repository.delete(integer);
            if (isDeleted != 1){
                throw new ApiException(CATEGORY_SHOP_DELETE_FAILED);
            }else{
                Optional<CategoryShop> categoryShopOptional = repository.findById(integer);
                CategoryShopResponse categoryShopResponse = mapper.toResponse(categoryShopOptional.get());
                return categoryShopResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] deleteById{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public Page<CategoryShopResponse> search(SearchRequest searchRequest) {
        try {
            List<CategoryShop> categoryShops = repository.search(searchRequest);
            Long count = repository.count(searchRequest);
            Map<Integer, Shop> shopMap = new HashMap<>();
            List<CategoryShopResponse> categoryShopResponses = new ArrayList<>();
            if (categoryShops.size() <= 0) {
                throw new ApiException(CATEGORY_SHOP_NOT_FOUND, 404);
            }else{
                List<Integer> shopIds = CollectionUtils.extractField(categoryShops, CategoryShop::getShopId);
                List<Shop> shopByIds = shopRepository.getShopByIds(shopIds);
                shopMap = shopByIds.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                categoryShopResponses  = mapper.toCustomResponse(categoryShops, shopMap);
            }
            return new Page<CategoryShopResponse>()
                    .setTotal(count)
                    .setItems(categoryShopResponses);
        }catch (ApiException exception){
            log.error("[ERROR] search{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }
}
