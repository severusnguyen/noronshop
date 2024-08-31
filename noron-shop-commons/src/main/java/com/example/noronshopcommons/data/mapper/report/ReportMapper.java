package com.example.noronshopcommons.data.mapper.report;

import com.example.noronshopcommons.data.dto.request.ReportRequest;
import com.example.noronshopcommons.data.dto.response.ReportResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.mapper.reports_category.ReportsCategoryMapper;
import com.example.noronshopcommons.data.tables.pojos.Report;
import com.example.noronshopcommons.data.tables.pojos.ReportsCategory;
import lombok.SneakyThrows;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ReportMapper extends BaseMap<ReportRequest, ReportResponse, Report> {

    @Autowired
    ReportsCategoryMapper reportsCategoryMapper;

    public abstract ReportResponse toResponseCustom(Report report, @Context ReportsCategory reportsCategory);

    public List<ReportResponse> toResponsesCustom(List<Report> reports, Map<Integer, ReportsCategory> reportsCategoryMap){
        return reports.stream()
                .map(report -> toResponseCustom(report, reportsCategoryMap.get(report.getReportsCategoryId())))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget ReportResponse response, @Context ReportsCategory reportsCategory){
        response.setReportsCategoryResponse(reportsCategoryMapper.toResponse(reportsCategory));
    }
}
