package com.hotelreservation.rest.aspect;

import com.hotelreservation.api.request.AuthUserRequest;
import com.hotelreservation.api.request.BaseRequest;
import com.hotelreservation.api.response.AuthUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class GeneralInterceptorAspect {
    private final BaseRequest baseRequest;

    /*@Pointcut("execution(* com.hotelreservation.rest.controller.AuthController.loginUser(..))")
    public void settingToken(){
        log.info("hello world : settingToken is working well...");
    }*/
    /*@Before(value = "execution (* com.hotelreservation.rest.controller.AuthController.loginUser(..))")
    public void testingSomeThing (JoinPoint joinPoint ){
        baseRequest = new BaseRequest();
        log.info("Token : ");
    }*/

    @AfterReturning(value = "execution(* com.hotelreservation.rest.service.UserService.authUser(..))" , returning = "token" )
    public void afterMethodInvoked (AuthUserResponse token){
        log.warn("TOKEN HERE : " + token);
        baseRequest.setToken(token.getToken());
        baseRequest.getToken();
        log.info("working...");
    }
}
