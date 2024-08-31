package com.example.noronshopcms.service.auth;

import com.example.noronshopcommons.data.dto.request.AuthRequest;
import com.example.noronshopcommons.data.dto.request.UserRequest;
import com.example.noronshopcommons.data.dto.response.TokenResponse;
import com.example.noronshopcommons.data.dto.response.UserResponse;

public interface IAuthService {
    TokenResponse authenticate(AuthRequest authRequest);
    UserResponse register(UserRequest userRequest);
}
