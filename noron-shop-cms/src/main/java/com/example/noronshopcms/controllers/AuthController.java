package com.example.noronshopcms.controllers;

import com.example.noronshopcms.service.auth.AuthServiceImp;
import com.example.noronshopcommons.data.dto.request.AuthRequest;
import com.example.noronshopcommons.data.dto.request.UserRequest;
import com.example.noronshopcommons.data.dto.response.TokenResponse;
import com.example.noronshopcommons.data.dto.response.UserResponse;
import com.example.noronshopcommons.data.modal.DfResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthServiceImp authService;

    @PostMapping("/signin")
    public ResponseEntity<DfResponse<TokenResponse>> login(@RequestBody AuthRequest authRequest){
        return DfResponse
                .okEntity(authService.authenticate(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<DfResponse<UserResponse>> register(@RequestBody UserRequest userRequest){
        return DfResponse
                .okEntity(authService.register(userRequest));
    }

}
