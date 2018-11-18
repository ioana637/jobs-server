package com.ubb.jobs.controller;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.User;
import com.ubb.jobs.service.UserService;
import com.ubb.jobs.utils.constants.EndPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(EndPoint.LOGIN)
public class LoginController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<UserDto> login() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        UserDto userDto = service.login(username, password);
        if (userDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        UserDto saved = service.add(user);
        if (saved == null) {
            log.info("Unable to register this user!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

}
