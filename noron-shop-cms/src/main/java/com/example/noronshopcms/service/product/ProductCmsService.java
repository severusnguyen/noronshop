package com.example.noronshopcms.service.product;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.dto.request.ProductRequest;
import com.example.noronshopcommons.data.dto.response.ProductResponse;
import com.example.noronshopcommons.data.mapper.ProductMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Category;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshoprepository.repository.CategoryRepository;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductCmsService extends AbsCmsService<ProductRequest, ProductResponse,
        Product, Integer, ProductRepositoryImpl, ProductMapper> implements IProductCmsService{

    public ProductCmsService(ProductRepositoryImpl repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Autowired
    private ShopRepositoryImpl shopRepository;


    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Page<ProductResponse> searchCustomRes(SearchRequest searchRequest) {
//        List<Product> productList = repository.search(searchRequest);
//        List<Integer> categoryIds = CollectionUtils.extractField(productList,Product::getCategoryId);
//        List<Integer> shopIds = CollectionUtils.extractField(productList, Product::getShopId);
//        Map<Integer, Category> categoryMap = categoryRepository.getCategoryByIds(categoryIds);
//        Map<Integer, Shop> shopMap = shopRepository.getShopByIds(shopIds);
//        Long total = repository.count(searchRequest);
//        List<ProductResponse> productResponses = mapper.toResponsesCustom(productList,categoryMap,shopMap);
//        return new Page<ProductResponse>()
//                .setPage(searchRequest.getPage())
//                .setKey(searchRequest.getKeyword())
//                .setItems(productResponses)
//                .setTotal(total);
        return null;
    }
}
