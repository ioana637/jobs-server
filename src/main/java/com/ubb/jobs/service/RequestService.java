package com.ubb.jobs.service;

import com.ubb.jobs.dto.RequestDto;
import com.ubb.jobs.model.Request;
import com.ubb.jobs.repo.impl.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;

    public RequestDto add(RequestDto dto) {
        RequestDto saved =  requestRepo.addRequest(dto);
        return saved;
    }

    public List<RequestDto> getByClient(int id) {
        return requestRepo.getRequestByClient(id);
    }

    public List<RequestDto> getByProvider(int id) {
        return requestRepo.getRequestByProvider(id);
    }


}
