package com.hotelreservation.rest.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hotelreservation.api.dto.UserDTO;
import com.hotelreservation.api.request.AddBalanceRequest;
import com.hotelreservation.api.request.AuthUserRequest;
import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.api.request.UserLoginRequest;
import com.hotelreservation.api.response.AuthUserResponse;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.rest.exception.AuthException;
import com.hotelreservation.rest.service.AuthService;
import com.hotelreservation.rest.service.UserService;
import com.hotelreservation.rest.validator.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
public class AuthController {

    private final UserService userService;

    private final UserValidator userValidator;

    private final AuthService authService;

    @GetMapping(path ="/login")
    public String login() {
        return "oldlogin";
    }

    @GetMapping(path = "/register")
    public String register() {
        return "register";
    }

    //TODO add mailsender.

    @PostMapping(path = "/register")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Valid UserAddRequest userAddRequest) throws AuthException {
        userValidator.validateUserRegister(userAddRequest);
        return ResponseEntity.ok(userService.createUser(userAddRequest));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthUserResponse> loginUser(@RequestBody @Valid AuthUserRequest userLoginRequest) throws AuthException {
        userValidator.authenticationUserLogin(userLoginRequest);
        return ResponseEntity.ok(userService.authUser(userLoginRequest));
    }

    @GetMapping(path = "/{userid}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable @Valid Long userid) {
        log.info("controller is working well , userid : " + userid);

        return ResponseEntity.ok(userService.findUserById(userid));
    }
    @GetMapping(path ="/greeting")
    public @ResponseBody String greeting(){
        return authService.testingSomeThing();
    }

}
/*
* @PostMapping("/login")
    public void loginUser (@RequestBody UserLoginRequest userLoginRequest) throws AuthException{
        userValidator.authenticationUserLogin(userLoginRequest);
    }
* */

/*@PostMapping("/login")
    public ResponseEntity<BaseResponse> loginUser (@RequestBody UserLoginRequest userLoginRequest) throws AuthException{
        userValidator.authenticationUserLogin(userLoginRequest);
        return ResponseEntity.ok(userService.login());
    }
* */


// BIR ONCEKI VERSIYONLARI
    /*@PostMapping("/login")
    public ResponseEntity<BaseResponse> loginUser (@RequestBody UserLoginRequest userLoginRequest) throws AuthException {
        userValidator.authenticationUserLogin(userLoginRequest);
        return ResponseEntity.ok(userService.login());
    }*/

/*  @PostMapping("/register")
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserAddRequest userAddRequest) throws AuthException {
        userValidator.validateUserRegister(userAddRequest);
        return ResponseEntity.ok(userService.save(userAddRequest));
    }
*
* */
