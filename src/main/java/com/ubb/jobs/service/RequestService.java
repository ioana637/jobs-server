package com.ubb.jobs.service;

import com.ubb.jobs.dto.JobDto;
import com.ubb.jobs.dto.RequestDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.Request;
import com.ubb.jobs.repo.impl.JobRepo;
import com.ubb.jobs.repo.impl.RequestRepo;
import com.ubb.jobs.repo.impl.UserRepo;
import com.ubb.jobs.utils.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    @Qualifier("MailSender")
    private MailSender mailSender;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JobRepo jobRepo;

    public RequestDto add(RequestDto dto) {
        RequestDto saved =  requestRepo.addRequest(dto);
        UserDto userFrom = userRepo.getOne(Integer.valueOf(saved.getUserFrom().getId()));
        UserDto userTo = userRepo.getOne(Integer.valueOf(saved.getUserTo().getId()));
        Thread mailThread = new Thread(()->{
            mailSender.sendMail("Cerere noua din partea userului " + userFrom.getUsername(), "Intra in aplicatie pentru afla mai multe detalii", userTo.getEmail());
        });
        mailThread.start();
        return saved;
    }

    public List<RequestDto> getByClient(int id) {
        List<RequestDto> requests = requestRepo.getRequestByClient(id);
        return createDtos(requests);
    }

    public List<RequestDto> getByProvider(int id) {
        List<RequestDto> requests = requestRepo.getRequestByProvider(id);
        return createDtos(requests);

    }

    private List<RequestDto> createDtos(List<RequestDto> requests) {
        return requests.stream()
                .map(this::createDto)
                .collect(Collectors.toList());
    }

    private RequestDto createDto(RequestDto requestDto) {
        UserDto userFrom = userRepo.getOne(Integer.valueOf(requestDto.getUserFrom().getId()));
        userFrom.setPassword(null);
        UserDto userTo = userRepo.getOne(Integer.valueOf(requestDto.getUserTo().getId()));
        userTo.setPassword(null);
        JobDto job = jobRepo.getOne(Integer.valueOf(requestDto.getJob().getId()));
        requestDto.setJob(job);
        requestDto.setUserFrom(userFrom);
        requestDto.setUserTo(userTo);
        return requestDto;
    }

    public RequestDto getRequestById(Integer id) {
        RequestDto request = requestRepo.getRequestById(id);
        return createDto(request);
    }
}
