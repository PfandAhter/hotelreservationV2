package com.hotelreservation.rest.controller;

import com.hotelreservation.api.dto.UserDTO;
import com.hotelreservation.api.request.AddBalanceRequest;
import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.api.request.UserLoginRequest;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.rest.exception.AuthException;
import com.hotelreservation.rest.service.AuthService;
import com.hotelreservation.rest.service.UserService;
import com.hotelreservation.rest.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.util.regex.Pattern.matches;

@Controller
@RequiredArgsConstructor
//@RequestMapping( "/users")
@Slf4j
public class AuthController {
    private final UserService userService;

    private final UserValidator userValidator;
    private final AuthService authService;



    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "oldlogin";
    }
    @GetMapping("/users")
    public String users(){
        return "users";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/addbalance")
    public String addbalance(){
        return "addbalance";
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserAddRequest userAddRequest) throws AuthException {
        userValidator.validateUserRegister(userAddRequest);
        return ResponseEntity.ok(userService.createUser(userAddRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> loginUser (UserLoginRequest userLoginRequest) throws AuthException {
        userValidator.authenticationUserLogin(userLoginRequest);
        return ResponseEntity.ok(userService.login());
    }

    @GetMapping("/{userid}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable Long userid) {
        log.info("controller is working well , userid : " + userid);

        return ResponseEntity.ok(userService.findUserById(userid));
    }

    @PutMapping("/addbalance")
    public ResponseEntity<BaseResponse> addBalance(AddBalanceRequest request){
        return ResponseEntity.ok(authService.addBalanceRequest(request));
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
