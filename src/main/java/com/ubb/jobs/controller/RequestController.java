package com.ubb.jobs.controller;

import com.ubb.jobs.dto.RequestDto;
import com.ubb.jobs.service.RequestService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(EndPoint.REQUEST)
public class RequestController {
    @Autowired
    private RequestService service;

    @PostMapping
    public ResponseEntity<RequestDto> save(@RequestBody RequestDto request) {
        RequestDto saved = service.add(request);
        if (saved == null) {
            log.info("Unable to send this recommendation!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<RequestDto> getByClient(@PathVariable Integer id) {
        List<RequestDto> requests = service.getByClient(id);
        if (requests == null) {
            log.info("Client has no requests!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Found " + requests.size() + "request(s)");
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<RequestDto> getByProvider(@PathVariable Integer id) {
        List<RequestDto> requests = service.getByProvider(id);
        if (requests == null) {
            log.info("Provider has no requests!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Found " + requests.size() + "request(s)");
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }
}