package com.example.noronshopcommons.data.mapper.form_return;

import com.example.noronshopcommons.config.JsonMapper;
import com.example.noronshopcommons.data.dto.request.FormReturnRequest;
import com.example.noronshopcommons.data.dto.response.FormReturnResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.modal.Image;
import com.example.noronshopcommons.data.modal.OrderDetailId;
import com.example.noronshopcommons.data.tables.pojos.FormReturn;
import com.example.noronshopcommons.utils.CollectionUtils;
import lombok.SneakyThrows;
import org.jooq.JSON;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public abstract class FormReturnMapper extends BaseMap<FormReturnRequest, FormReturnResponse, FormReturn> {

    @Override
    @Named("toPOJO")
    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "orderDetailId", ignore = true)
    })
    public abstract FormReturn toPOJO(FormReturnRequest request);

    @Override
    @Mappings({
            @Mapping(target = "images", ignore = true),
            @Mapping(target = "orderDetailId", ignore = true)
    })
    @Named("toResponse")
    public abstract FormReturnResponse toResponse(FormReturn pojo);

    @SneakyThrows
    @AfterMapping
    public void afterToPojo(@MappingTarget FormReturn formReturn, FormReturnRequest formReturnRequest){
        if (formReturnRequest.getImages() != null){
            String image = JsonMapper.getObjectMapper().writeValueAsString(formReturnRequest.getImages());
            formReturn.setImages(JSON.valueOf(image));
        }
        if (formReturnRequest.getOrderDetailId() != null){
            String orderDetailId = JsonMapper.getObjectMapper().writeValueAsString(formReturnRequest.getOrderDetailId());
            formReturn.setOrderDetailId(JSON.valueOf(orderDetailId));
        }
    }

    @SneakyThrows
    @AfterMapping
    public void afterToResponse(@MappingTarget FormReturnResponse formReturnResponse, FormReturn formReturn){
        formReturnResponse.setImages(CollectionUtils.getOrDefaultList(Image.class, formReturn.getImages(), new ArrayList<>()));
        formReturnResponse.setOrderDetailId(CollectionUtils.getOrDefaultList(OrderDetailId.class, formReturn.getOrderDetailId(), new ArrayList<>()));
    }
}
