package com.example.noronshopcms.service.auth;

import com.example.noronshopcms.service.AbsCmsService;
import com.example.noronshopcommons.data.dto.request.AuthRequest;
import com.example.noronshopcommons.data.dto.request.UserRequest;
import com.example.noronshopcommons.data.dto.response.TokenResponse;
import com.example.noronshopcommons.data.dto.response.UserResponse;
import com.example.noronshopcommons.data.mapper.user.UserMapper;
import com.example.noronshopcommons.data.tables.pojos.User;
import com.example.noronshopconfig.config.JwtUtil;
import com.example.noronshopconfig.exception.ApiException;
import com.example.noronshoprepository.repository.account.UserRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.noronshopcms.service.constant.AuthConstant.REGISTER_FAIL;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImp extends AbsCmsService<UserRequest, UserResponse, User, Integer, UserRepositoryImp, UserMapper> implements IAuthService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepositoryImp userRepositoryImp;

    public AuthServiceImp(AuthenticationManager authenticationManager, UserRepositoryImp repositoryImp, UserMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.repository = repositoryImp;
        this.mapper = mapper;
    }

    @Override
    public TokenResponse authenticate(AuthRequest authRequest){

        try {
            if (userRepositoryImp.findUserByUsername(authRequest.getUsername()).isPresent()) {
                String token = null;
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

                token = jwtUtil.generateToken(authRequest.getUsername());

                return new TokenResponse(token);
            } else {
                throw new ApiException("User don't exist.");
            }
        } catch (ApiException e){
            log.error("[ERROR] " + e.getMessage());
            throw new ApiException("[ERROR] " + e.getMessage());
        }

    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        try {
            if (userRepositoryImp.findUserByUsername(userRequest.getEmail()).isPresent()){
               throw  new ApiException("User existed!");
            } else {
                User user = mapper.toPOJO(userRequest);
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                repository.insertReturning(user);
                UserResponse userResponse = mapper.toResponse(user);
                return userResponse;
            }
        } catch (ApiException e){
            log.error("[ERROR] {} Register faild." + e.getMessage());
            throw new ApiException(REGISTER_FAIL);
        }

    }
}
