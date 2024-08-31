package com.example.noronshopcms.service.banner;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.dto.request.BannerRequest;
import com.example.noronshopcommons.data.dto.request.BannerResponse;
import com.example.noronshopcommons.data.mapper.banner.BannerMapper;
import com.example.noronshopcommons.data.tables.pojos.Banner;
import com.example.noronshoprepository.repository.banner.BannerRepositoryImp;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImp extends AbsCmsService<BannerRequest, BannerResponse, Banner, Integer, BannerRepositoryImp, BannerMapper> {

    public BannerServiceImp(BannerRepositoryImp repositoryImp, BannerMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }
}
