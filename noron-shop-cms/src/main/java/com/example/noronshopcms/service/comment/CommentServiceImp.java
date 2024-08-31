package com.example.noronshopcms.service.comment;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcms.service.constant.CommentConstant;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.CommentRequest;
import com.example.noronshopcommons.data.dto.response.CommentResponse;
import com.example.noronshopcommons.data.mapper.comment.CommentMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Comment;
import com.example.noronshopcommons.data.tables.pojos.Product;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.comment.CommentRepositoryImp;
import com.example.noronshoprepository.repository.product.ProductRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.CommentConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.CommentConstant.NOT_FOUND;

@Service
@Slf4j
public class CommentServiceImp extends AbsCmsService<CommentRequest, CommentResponse, Comment, Integer, CommentRepositoryImp, CommentMapper> implements  ICommentService{

    @Autowired
    ProductRepositoryImpl productRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImp.class);

    public CommentServiceImp(CommentRepositoryImp repositoryImp, CommentMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public CommentResponse update(Integer integer, CommentRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(CommentConstant.NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public CommentResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(CommentConstant.NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<CommentResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<Comment> comments = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Product> productMap;
            List<CommentResponse> commentResponses;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> productIds = CollectionUtils.extractField(comments, Comment::getProductId);
                List<Product> products = productRepository.getProductByIds(productIds);
                productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));
                commentResponses = mapper.toResponsesCustom(comments, productMap);

                return new Page<CommentResponse>()
                        .setPage(searchRequest.getPage())
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(commentResponses);
            }
        } catch (ApiException e){
            logger.error("[ERROR] Error occurred while searching comments: " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
