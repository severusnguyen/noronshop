package com.example.noronshopcms.service.address_shop;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.core.exception.DBException;
import com.example.noronshopcommons.data.dto.request.AddressShopRequest;
import com.example.noronshopcommons.data.dto.response.AddressShopResponse;
import com.example.noronshopcommons.data.mapper.addressshop.AddressShopMapper;
import com.example.noronshopcommons.data.tables.pojos.AddressShop;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.addressshop.AddressShopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.noronshopcommons.data.constrant.AddressShop.AddressShopMessageConstrant.*;

@Service
@Slf4j
public class AddressShopService extends AbsCmsService<AddressShopRequest, AddressShopResponse, AddressShop, Integer, AddressShopRepository, AddressShopMapper> {

    @Autowired
    public AddressShopService(AddressShopRepository addressShopRepository, AddressShopMapper addressShopMapper) {
        this.repository = addressShopRepository;
        this.mapper = addressShopMapper;
    }

    @Override
    public AddressShopResponse update(Integer integer, AddressShopRequest request) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(ADDRESS_SHOP_NOT_FOUND, 404));
            AddressShop newAddressShop = mapper.toPOJO(request);
            int isSaved = repository.update(newAddressShop, integer);
            if (isSaved != 1){
                throw new ApiException(ADDRESS_SHOP_UPDATE_FAILED);
            }else{
                Optional<AddressShop> addressShopOptional = repository.findById(integer);
                AddressShopResponse addressShopResponse = mapper.toResponse(addressShopOptional.get());
                return addressShopResponse;
            }
        } catch (ApiException apiException) {
            log.error("[ERROR] update{} " + apiException.getMessage());
            throw new ApiException(apiException.getMessage());
        }
    }

    @Override
    public AddressShopResponse deleteById(Integer integer) {
        try {
            repository.findById(integer)
                    .orElseThrow(() -> new ApiException(ADDRESS_SHOP_NOT_FOUND, 404));

            Integer isDeleted = repository.delete(integer);
            if (isDeleted != 1) {
                throw new ApiException(ADDRESS_SHOP_DELETE_FAILED);
            } else {
                Optional<AddressShop> addressShopOptional = repository.findById(integer);
                AddressShopResponse addressShopResponse = mapper.toResponse(addressShopOptional.get());
                return addressShopResponse;
            }
        } catch (ApiException exception) {
            log.error("[ERROR] deleteById{} " + exception.getMessage());
            throw new ApiException(exception.getMessage());
        }
    }
}
