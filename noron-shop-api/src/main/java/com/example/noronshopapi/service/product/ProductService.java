package com.example.noronshopapi.service.product;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Order;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.CategoryRepository;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import com.example.noronshoprepository.repository.shop.IShopRepository;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.noronshopcommons.data.constrant.Product.ProductMessageConstrant.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService extends AbsService<ProductRequest, ProductResponse, Product, Integer, ProductRepositoryImpl, ProductMapper> implements IProductService {

    private IShopRepository shopRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepositoryImpl productRepository, ProductMapper productMapper, IShopRepository shopRepository, CategoryRepository categoryRepository) {
        this.repository = productRepository;
        this.mapper = productMapper;
        this.shopRepository = shopRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<ProductResponse> search(SearchRequest searchRequest) {
        try {
            List<Product> products = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Shop> shopMap;
            List<ProductResponse> productResponses;
            if (products.size() == 0){
                throw new ApiException(PRODUCT_NOT_FOUND);
            }else{
                List<Integer> shopIds = CollectionUtils.extractField(products, Product::getShopId);
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                productResponses = mapper.toResponsesCustom(products, shopMap);
                return new Page<ProductResponse>()
                        .setTotal(total)
                        .setItems(productResponses);
            }
        }catch (ApiException apiException){
            log.error("[ERROR] search{} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage());
        }
    }

    @Override
    public ProductResponse findById(Integer integer) {
        try{
            Product product = repository.findById(integer)
                    .orElseThrow(() -> new ApiException(PRODUCT_NOT_FOUND));
            Shop shop = shopRepository.findById(product.getShopId()).orElse(new Shop());
            Category category = categoryRepository.findById(product.getCategoryId()).orElse(new Category());
            ProductResponse productResponse = mapper.toResponseCustom(product, category, shop);
            return productResponse;
        }catch (ApiException apiException){
            log.error("[ERROR] findById{} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage());
        }
    }
}
