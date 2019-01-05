package com.ubb.jobs.controller;

import com.ubb.jobs.dto.AbilityDto;
import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.service.UserService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping()
public class LoginController {

    @Autowired
    private UserService service;

    @GetMapping(EndPoint.LOGIN)
    public ResponseEntity<UserDto> login() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        UserDto userDto = service.getUser(username, password);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(EndPoint.REGISTER)
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        UserDto saved = service.add(user);
        if (saved == null) {
            log.info("Unable to register this user!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @GetMapping("/limit={pageSize}&start={pageNumber}")
    public ResponseEntity<List<UserDto>> getProviderPaginated(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNumber") Integer pageNumber) {
        List<UserDto> users = service.findProvidersPaginated(pageNumber, pageSize);
        if (users == null) {
            log.info("Unable to find any providers for the given user");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + users.size() + " from page " + pageNumber);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/rating={starAvg}")
    public ResponseEntity<List<UserDto>> getProvidersByRating(@PathVariable("starAvg") Float starAvg) {
        List<UserDto> allProviders = service.getProvidersByRating(starAvg);
        if (allProviders == null) {
            log.info("Unable to find any providers");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + allProviders.size());
        return new ResponseEntity<>(allProviders, HttpStatus.OK);
    }

    @GetMapping("/abilities={abilities}")
    public ResponseEntity<List<UserDto>> getProvidersByAbilities(@PathVariable("abilities") List<Integer> abilities){
        List<UserDto> allProviders = service.getProvidersByAbilities(abilities);
        if (allProviders == null) {
            log.info("Unable to find any providers");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + allProviders.size());
        return new ResponseEntity<>(allProviders, HttpStatus.OK);
    }

    @GetMapping("providers")
    public ResponseEntity<List<UserDto>> getProviders() {
        List<UserDto> users = service.findProviders();
        if (users == null) {
            log.info("Unable to find any providers");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("clients")
    public ResponseEntity<List<UserDto>> getClients() {
        List<UserDto> users = service.findAllClients();
        if (users == null) {
            log.info("Unable to find any clients for the given user");

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Returning " + users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}
