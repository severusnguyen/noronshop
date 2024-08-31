package com.example.noronshopcms.service.shop;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.mapper.shop.ShopMapper;
import com.example.noronshopcommons.data.dto.request.ShopRequest;
import com.example.noronshopcommons.data.dto.response.ShopResponse;
import com.example.noronshopcommons.data.tables.pojos.Shop;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.shop.ShopRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.noronshopcommons.data.constrant.Shop.ShopMessageConstrant.*;

@Service
@Slf4j
public class ShopServiceImpl extends AbsCmsService<ShopRequest, ShopResponse, Shop, Integer, ShopRepositoryImpl, ShopMapper> implements IShopService {


    @Autowired
    public ShopServiceImpl(ShopRepositoryImpl shopRepository, ShopMapper shopMapper) {
        this.repository = shopRepository;
        this.mapper = shopMapper;
    }

    @Override
    public ShopResponse update(Integer integer, ShopRequest request) {
        try {
            repository.findById(integer).orElseThrow(() -> new ApiException(SHOP_NOT_FOUND));
            Shop newShop = mapper.toPOJO(request);
            int isUpdated = repository.update(newShop, integer);
            if (isUpdated != 1){
                throw new ApiException(SHOP_UPDATE_FAILED);
            }else{
                Optional<Shop> shopOptional = repository.findById(integer);
                ShopResponse shopResponse = mapper.toResponse(shopOptional.get());
                return shopResponse;
            }
        }catch (ApiException exception){
            log.error("[ERROR] update{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }

    @Override
    public ShopResponse deleteById(Integer integer) {
        try {
            repository.findById(integer).orElseThrow(() -> new ApiException(SHOP_NOT_FOUND));
            int isDeleted = repository.delete(integer);
            if (isDeleted != 1){
                throw new ApiException(SHOP_DELETE_FAILED);
            }else{
                Optional<Shop> shopOptional = repository.findById(integer);
                ShopResponse shopResponse = mapper.toResponse(shopOptional.get());
                return shopResponse;
            }
        }catch (ApiException exception){
            log.error("[ERROR] deleteById{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }
}
