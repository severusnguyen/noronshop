package com.example.noronshopcommons.data.mapper.user_account_bank;

import com.example.noronshopcommons.data.dto.request.UserAccountBankRequest;
import com.example.noronshopcommons.data.dto.response.UserAccountBankResponse;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.tables.pojos.UserAccountBank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserAccountBankMapper extends BaseMap<UserAccountBankRequest, UserAccountBankResponse, UserAccountBank> {
}
