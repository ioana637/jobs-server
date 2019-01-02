package com.ubb.jobs.repo.impl;

import com.ubb.jobs.dto.RequestDto;
import com.ubb.jobs.dto.ReviewDto;
import com.ubb.jobs.model.Request;
import com.ubb.jobs.model.Review;
import com.ubb.jobs.repo.JpaRequestRepo;
import com.ubb.jobs.utils.mapper.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestRepo {
    @Autowired
    private JpaRequestRepo requestRepo;

    @Autowired
    private RequestMapper requestMapper;

    public RequestDto addRequest(RequestDto request) {
        Request saved = requestRepo.save(requestMapper.toEntity(request));
        return requestMapper.toDto(saved);
    }

    public List<RequestDto> getRequestByClient(int id){
        List<Request> requests =requestRepo.findRequestByUserTo(id);
        return requestMapper.toDtos(requests);
    }

    public List<RequestDto> getRequestByProvider(int id){
        List<Request> requests =requestRepo.findRequestByUserFrom(id);
        return requestMapper.toDtos(requests);
    }


    public RequestDto getRequestById(Integer id) {
        return requestMapper.toDto(requestRepo.findRequestById(id));
    }
}