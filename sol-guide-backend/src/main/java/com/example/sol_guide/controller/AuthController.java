package com.example.sol_guide.controller;

import com.example.sol_guide.service.AuthService;
import com.example.sol_guide.model.LoginRequest;
import com.example.sol_guide.model.TokenResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

}
