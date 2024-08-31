package com.example.noronshopcms.service;

import com.example.noronshopcommons.config.annotation.Log;
import com.example.noronshopcommons.data.dto.response.SuccessMessage;
import com.example.noronshopcommons.data.mapper.BaseMap;
import com.example.noronshopcommons.data.modal.SearchRequest;
import com.example.noronshopcommons.data.modal.paging.Page;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.AbsRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.example.noronshopcommons.data.constrant.BaseConstant.*;

@Slf4j
public abstract class AbsCmsService<Rq, Rp, Po, ID, Repo extends AbsRepository<?, Po, ID>,
        Mp extends BaseMap<Rq, Rp, Po>> implements ICmsService<Rq, Rp, ID> {

    protected Mp mapper;
    protected Repo repository;

    @Log
    @Override
    public Rp insert(Rq request) {
        try {
            Po po = mapper.toPOJO(request);
            Po poInsert =  repository.insertReturning(po)
                    .orElseThrow(() -> new ApiException(INSERT_FAIL));
            Rp res = mapper.toResponse(poInsert);
            return res;
        }catch (Exception e){
            throw new ApiException(INSERT_FAIL);
        }
    }

    @Log
    @Override
    public Rp update(ID id, Rq request) {
        try {
            Po po = mapper.toPOJO(request);
            Integer result = repository.update(po,id);
            if(result != 1){
                throw new ApiException(UPDATE_FAIL);
            }else {
                Po poFinded = repository.findById(id).get();
                if(poFinded == null){
                    throw new ApiException(NOT_FOUND_USER);
                }
                Rp res = mapper.toResponse(poFinded);
                return res;
            }
        }catch (Exception e){
            throw new ApiException(UPDATE_FAIL);
        }
    }

    @Log
    @Override
    public Rp findById(ID id) {
        try {
            Po poFinded = repository.findById(id).get();
            if(poFinded == null){
                throw new ApiException(NOT_FOUND_USER);
            }
            Rp res = mapper.toResponse(poFinded);
            return res;
        }catch (Exception e){
            throw new ApiException(NOT_FOUND);
        }
    }

    @Log
    @Override
    public Rp deleteById(ID id) {
        try {
            Integer result = repository.delete(id);
            if(result != 1){
                throw new ApiException(DELETE_FAIL);
            }else {
                Po poFinded = repository.findById(id).get();
                if(poFinded == null){
                    throw new ApiException(NOT_FOUND_USER);
                }
                Rp res = mapper.toResponse(poFinded);
                return res;
            }
        }catch (Exception e){
            throw new ApiException(DELETE_FAIL);
        }
    }


    @Log
    @Override
    public Page<Rp> search(SearchRequest searchRequest) {
        try {
            List<Po> rpo = repository.search(searchRequest);
            List<Rp> rps = mapper.toResponses(rpo);
            Long total = repository.count(searchRequest);
            return new Page<Rp>()
                    .setPage(searchRequest.getPage())
                    .setKey(searchRequest.getKeyword())
                    .setItems(rps)
                    .setTotal(total);
        }catch (Exception e){
            throw new ApiException(SEARCH_FAIL);
        }
    }
}
