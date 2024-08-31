package com.example.noronshopcms.service.report;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.ReportRequest;
import com.example.noronshopcommons.data.dto.response.ReportResponse;
import com.example.noronshopcommons.data.mapper.report.ReportMapper;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopcommons.data.tables.pojos.Report;
import com.example.noronshopcommons.data.tables.pojos.ReportsCategory;
import com.example.noronshopcommons.utils.CollectionUtils;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.report.ReportRepositoryImp;
import com.example.noronshoprepository.repository.reports_category.ReportsCategoryRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.noronshopcms.service.constant.ReportConstant.FAIL_SEARCH;
import static com.example.noronshopcms.service.constant.ReportConstant.NOT_FOUND;

@Service
@Slf4j
public class ReportServiceImp extends AbsCmsService<ReportRequest, ReportResponse, Report, Integer, ReportRepositoryImp, ReportMapper> implements IReportService{

    @Autowired
    ReportsCategoryRepositoryImp repositoryImp;

    public static final Logger logger = LoggerFactory.getLogger(ReportServiceImp.class);

    public ReportServiceImp(ReportRepositoryImp repositoryImp, ReportMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public ReportResponse update(Integer integer, ReportRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public ReportResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }

    @Override
    public Page<ReportResponse> searchCustom(SearchRequest searchRequest) {
        try {
            List<Report> reports = repository.search(searchRequest);
            Long total = repository.count(searchRequest);
            Map<Integer, ReportsCategory> reportsCategoryMap;
            List<ReportResponse> reportResponses;

            if (total <= 0) {
                throw new ApiException(NOT_FOUND);
            } else {
                List<Integer> reportsCateIds = CollectionUtils.extractField(reports, Report::getReportsCategoryId);
                List<ReportsCategory> reportsCategorys = repositoryImp.getReportsCategoryById(reportsCateIds);
                //Map<Integer, ReportsCategory> reportsCategoryMap = repositoryImp.getReportsCategoryById(reportsCateIds);
                reportsCategoryMap = reportsCategorys.stream()
                        .collect(Collectors.toMap(ReportsCategory::getId, Function.identity()));
                reportResponses = mapper.toResponsesCustom(reports, reportsCategoryMap);
            }

            return new Page<ReportResponse>()
                    .setPage(searchRequest.getPage())
                    .setKey(searchRequest.getKeyword())
                    .setTotal(total)
                    .setItems(reportResponses);
        }catch (ApiException e){
            logger.error("[ERROR] Error occurred while searching report: " + e.getMessage());
            throw new ApiException(FAIL_SEARCH);
        }
    }
}
