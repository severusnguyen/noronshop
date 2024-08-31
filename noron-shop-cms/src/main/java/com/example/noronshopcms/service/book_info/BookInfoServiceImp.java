package com.example.noronshopcms.service.book_info;

import com.example.noronshopcms.service.constant.BookInfoConstant;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.BookInfoRequest;
import com.example.noronshopcommons.data.dto.response.BookInfoResponse;
import com.example.noronshopcommons.data.mapper.book_info.BookInfoMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.BookInfo;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.book_info.BookInfoRepositoryImp;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.noronshopcms.service.AbsCmsService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.BookInfoConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.OrderConstant.NOT_FOUND;

@Repository
@Slf4j
public class BookInfoServiceImp extends AbsCmsService<BookInfoRequest, BookInfoResponse, BookInfo, Integer, BookInfoRepositoryImp, BookInfoMapper> implements IBookInfoService{

    @Autowired
    ProductRepositoryImpl productRepository;

    public static final Logger logger = LoggerFactory.getLogger(BookInfoServiceImp.class);

    public BookInfoServiceImp(BookInfoRepositoryImp repositoryImp, BookInfoMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public BookInfoResponse update(Integer integer, BookInfoRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(BookInfoConstant.NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public BookInfoResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(BookInfoConstant.NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<BookInfoResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<BookInfo> bookInfos = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Product> productMap;
            List<BookInfoResponse> bookInfoResponses;
            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> productIds = CollectionUtils.extractField(bookInfos, BookInfo::getProductId);
                List<Product> products = productRepository.getProductByIds(productIds);
                productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));
                bookInfoResponses = mapper.toResponsesCustom(bookInfos, productMap);
            }

            return new Page<BookInfoResponse>()
                    .setPage(searchRequest.getPage())
                    .setKey(searchRequest.getKeyword())
                    .setTotal(total)
                    .setItems(bookInfoResponses);
        } catch (ApiException e){
            logger.error("Error occurred while searching book infos: " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
