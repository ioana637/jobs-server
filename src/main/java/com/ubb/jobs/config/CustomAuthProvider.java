package com.ubb.jobs.config;

import com.ubb.jobs.dto.UserDto;
import com.ubb.jobs.model.User;
import com.ubb.jobs.repo.impl.UserRepo;
import com.ubb.jobs.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CustomAuthProvider implements AuthenticationProvider {


    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();


        UserDto userDto = userService.login(username, password);
        if (userDto == null) {
            log.warn("Invalid username or password");
            return null;
        }

        log.warn("username " + userDto.getUsername() +  " password " + userDto.getPassword());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userDto.getRole()));
        log.warn("Authorities: " + userDto.getRole());
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
