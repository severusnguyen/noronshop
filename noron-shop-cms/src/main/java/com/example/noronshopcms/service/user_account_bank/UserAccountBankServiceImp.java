package com.example.noronshopcms.service.user_account_bank;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.request.UserAccountBankRequest;
import com.example.noronshopcommons.data.dto.response.UserAccountBankResponse;
import com.example.noronshopcommons.data.mapper.user_account_bank.UserAccountBankMapper;
import com.example.noronshopcommons.data.tables.pojos.UserAccountBank;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.user_account_bank.UserAccountBankRepositoryImp;
import org.springframework.stereotype.Service;

import static com.example.noronshopcms.service.constant.UserAccountBankConstant.NOT_FOUND;

@Service
public class UserAccountBankServiceImp extends AbsCmsService<UserAccountBankRequest, UserAccountBankResponse, UserAccountBank, Integer, UserAccountBankRepositoryImp, UserAccountBankMapper> {

    public UserAccountBankServiceImp(UserAccountBankRepositoryImp repositoryImp, UserAccountBankMapper mapper) {
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Log
    @Override
    public UserAccountBankResponse update(Integer integer, UserAccountBankRequest request) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.update(integer, request);
    }

    @Log
    @Override
    public UserAccountBankResponse deleteById(Integer integer) {
        if (!repository.findById(integer).isPresent()){
            throw new ApiException(NOT_FOUND);
        }
        return super.deleteById(integer);
    }
}
