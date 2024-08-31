package com.example.noronshopapi.service.form_return;

import com.example.noronshopapi.service.AbsService;
import com.example.noronshopcommons.data.dto.request.FormReturnRequest;
import com.example.noronshopcommons.data.dto.response.FormReturnResponse;
import com.example.noronshopcommons.data.mapper.form_return.FormReturnMapper;
import com.example.noronshopcommons.data.tables.pojos.FormReturn;
import com.example.noronshoprepository.repository.form_return.FormReturnRepository;
import org.springframework.stereotype.Service;

@Service
public class FormReturnService extends AbsService<FormReturnRequest, FormReturnResponse, FormReturn, Integer,
        FormReturnRepository, FormReturnMapper> {

    public FormReturnService(FormReturnRepository repository, FormReturnMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }

}
