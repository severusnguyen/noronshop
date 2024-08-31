package com.example.noronshopapi.service.category_shop;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.constrant.CategoryShopProduct.CategoryShopProductMessageConstrant;
import com.example.noronshopcommons.data.dto.request.CategoryShopRequest;
import com.example.noronshopcommons.data.dto.response.CategoryShopResponse;
import com.example.noronshopcommons.data.mapper.categoryshop.CategoryShopMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.CategoryShop;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.category_shop_product.CateShopProductRepo;
import com.example.noronshoprepository.repository.category_shop_product.ICateShopProductRepo;
import com.example.noronshoprepository.repository.categoryshop.CategoryShopRepository;
import com.example.noronshoprepository.repository.categoryshop.ICategoryShopRepositoty;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.noronshopcommons.data.constrant.CategoryShop.CategoryShopMessageConstrant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategoryShopService extends AbsService<CategoryShopRequest, CategoryShopResponse, CategoryShop, Integer, CategoryShopRepository, CategoryShopMapper> implements ICategoryShopService {

    private ICateShopProductRepo cateShopProductRepo;

    @Autowired
    public CategoryShopService(CategoryShopRepository categoryShopRepository, CategoryShopMapper categoryShopMapper, ICateShopProductRepo cateShopProductRepo) {
        this.repository = categoryShopRepository;
        this.mapper = categoryShopMapper;
        this.cateShopProductRepo = cateShopProductRepo;
    }

    @Override
    public Page<CategoryShopResponse> search(SearchRequest searchRequest) {
        try {
            List<CategoryShop> categoryShops = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            if (categoryShops.size() <= 0){
                throw new ApiException(CATEGORY_SHOP_NOT_FOUND, 404);
            }else{
                List<Integer> categoryShopIds = CollectionUtils.extractField(categoryShops, CategoryShop::getId);
                List<CategoryShopResponse> categoryShopResponses = mapper.toResponses(categoryShops);
                Result<Record2<Integer, Integer>> result = cateShopProductRepo.countProduct(categoryShopIds);
                Map<Integer, Integer> productQuantity = new HashMap<>();
                if (result.size() == 0){
                    throw new ApiException(CategoryShopProductMessageConstrant.CATEGORYSHOP_PRODUCT_NOT_FOUND);
                }else{
                    result.stream().forEach(record2 -> {
                        Field categoryIdField = record2.field1();
                        Field quantityProductField = record2.field2();
                        Integer categoryId = (Integer) record2.get(categoryIdField);
                        Integer quantityProduct = (Integer) record2.get(quantityProductField);
                        productQuantity.put(categoryId, quantityProduct);
                    });

                    categoryShopResponses.stream().forEach(categoryShopResponse -> {
                        int categoryId = categoryShopResponse.getId();
                        if (!productQuantity.containsKey(categoryId)){
                            categoryShopResponse.setQuantity(0);
                        }else{
                            categoryShopResponse.setQuantity(productQuantity.get(categoryId));
                        }
                    });
                }
                return new Page<CategoryShopResponse>()
                        .setTotal(total)
                        .setItems(categoryShopResponses);
            }
        }catch (ApiException apiException){
            log.error("[ERROR] search{} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage());
        }
    }
}
