package com.akul.taskslist.service.impl;

import com.akul.taskslist.service.AuthService;
import com.akul.taskslist.service.UserService;
import com.akul.taskslist.web.dto.auth.JwtRequest;
import com.akul.taskslist.web.dto.auth.JwtResponse;
import com.akul.taskslist.web.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(final String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }

}
