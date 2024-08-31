package com.example.noronshopcms.service.reports_caterory;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.dto.request.ReportsCategoryRequest;
import com.example.noronshopcommons.data.dto.response.ReportsCategoryResponse;
import com.example.noronshopcommons.data.mapper.reports_category.ReportsCategoryMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.ReportsCategory;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.reports_category.ReportsCategoryRepositoryImp;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.ReportCategoryConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.ReportCategoryConstant.NOT_FOUND;

@Service
@Slf4j
public class ReportsCategoryServiceImp extends
        AbsCmsService<ReportsCategoryRequest, ReportsCategoryResponse, ReportsCategory, Integer, ReportsCategoryRepositoryImp, ReportsCategoryMapper>
        implements IReportsCategoryService{

    @Autowired
    ShopRepositoryImpl shopRepository;

    public ReportsCategoryServiceImp(ReportsCategoryRepositoryImp repositoryImp, ReportsCategoryMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Override
    public ReportsCategoryResponse update(Integer integer, ReportsCategoryRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Override
    public ReportsCategoryResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<ReportsCategoryResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<ReportsCategory> reportsCategories = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, Shop> shopMap;
            List<ReportsCategoryResponse> reportsCategoryResponses;

            if (total <= 0){
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> shopIds = CollectionUtils.extractField(reportsCategories, ReportsCategory::getShopId);
                List<Shop> shops = shopRepository.getShopByIds(shopIds);
                shopMap = shops.stream()
                        .collect(Collectors.toMap(Shop::getId, Function.identity()));
                reportsCategoryResponses = mapper.toResponsesCustom(reportsCategories, shopMap);

                return new Page<ReportsCategoryResponse>()
                        .setPage(searchRequest.getPage())
                        .setKey(searchRequest.getKeyword())
                        .setTotal(total)
                        .setItems(reportsCategoryResponses);
            }
        } catch (Exception e){
            log.error("[ERROR] Error occurred while searching comments: " + e);
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
