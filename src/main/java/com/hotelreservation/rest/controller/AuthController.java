package com.hotelreservation.rest.controller;

import com.hotelreservation.api.request.AuthUserRequest;
import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.api.response.AuthUserResponse;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.rest.exception.AuthException;
import com.hotelreservation.rest.service.UserService;
import com.hotelreservation.rest.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path ="/auth")
@Slf4j
@CrossOrigin
public class AuthController {

    private final UserService userService;

    private final UserValidator userValidator;

    @GetMapping(path ="/login")
    public String login() {
        return "oldlogin";
    }
    @GetMapping(path = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(path = "/register")
    public ResponseEntity<BaseResponse> createUser(
            @NonNull @RequestBody @Valid UserAddRequest userAddRequest) throws AuthException {
        userValidator.validateUserRegister(userAddRequest);
        return ResponseEntity.ok(userService.createUser(userAddRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthUserResponse> loginUser(
            @NonNull @RequestBody @Valid AuthUserRequest userLoginRequest) throws AuthException {
        userValidator.authenticationUserLogin(userLoginRequest);
        return ResponseEntity.ok(userService.authUser(userLoginRequest));
    }

}