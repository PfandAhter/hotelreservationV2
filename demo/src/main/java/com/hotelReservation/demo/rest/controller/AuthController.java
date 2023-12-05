package com.hotelReservation.demo.rest.controller;

import com.hotelReservation.demo.api.dto.UserDTO;
import com.hotelReservation.demo.api.request.UserAddRequest;
import com.hotelReservation.demo.api.request.UserLoginRequest;
import com.hotelReservation.demo.api.response.BaseResponse;
import com.hotelReservation.demo.api.response.UserListResponse;
import com.hotelReservation.demo.model.entity.User;
import com.hotelReservation.demo.rest.exception.AuthException;
import com.hotelReservation.demo.rest.service.UserService;
import com.hotelReservation.demo.rest.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping( "/users")
@Slf4j
public class AuthController {
    private final UserService userService;

    private final UserValidator userValidator;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/users")
    public String users(){
        return "users";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserAddRequest userAddRequest) throws AuthException {
        userValidator.validateUserRegister(userAddRequest);
        return ResponseEntity.ok(userService.save(userAddRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> loginUser (@RequestBody UserLoginRequest userLoginRequest) throws AuthException{
        userValidator.authenticationUserLogin(userLoginRequest);
        return ResponseEntity.ok(userService.login());
    }

    @GetMapping("/{userid}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable int userid) {
        log.info("controller is working well , userid : " + userid);

        return ResponseEntity.ok(userService.findUserById(userid));
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