package com.example.sol_guide.service;


import com.example.sol_guide.model.LoginRequest;
import com.example.sol_guide.model.TokenResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {


    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginRequest request){
        if("admin".equals(request.getUsername()) && "password".equals(request.getPassword())){
            List<String> roles = List.of("ROLE_ADMIN");
            String token = jwtService.generateToken(request.getUsername(), roles);
            return new TokenResponse(token);
        }
        throw new RuntimeException("Invalid");
    }
}
