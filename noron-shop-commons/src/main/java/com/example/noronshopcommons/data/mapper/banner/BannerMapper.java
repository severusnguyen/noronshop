package com.example.noronshopcommons.data.mapper.banner;

import com.example.noronshopcommons.data.dto.request.BannerRequest;
import com.example.noronshopcommons.data.dto.request.BannerResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.Banner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BannerMapper extends BaseMap<BannerRequest, BannerResponse, Banner> {
}
