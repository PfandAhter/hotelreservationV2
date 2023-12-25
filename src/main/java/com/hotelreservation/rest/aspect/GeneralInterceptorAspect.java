package com.hotelreservation.rest.aspect;

import com.hotelreservation.api.request.BaseRequest;
import jakarta.servlet.http.HttpServletRequest;
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
    private final HttpServletRequest request;

    @Before(value = "execution(* com.hotelreservation.rest.controller..*(..)) ")
    public void beforeController (JoinPoint joinPoint){
        Object [] parameters = joinPoint.getArgs();
        for(Object param : parameters){
            if(param instanceof BaseRequest){
                ((BaseRequest) param).setToken(request.getHeader("Authorization"));
            }
        }
    }

    @Before(value = "execution (* com.hotelreservation.rest.service.UserService.authUser(..))")
    public void beforeLoginUser(JoinPoint joinPoint){
        log.info("UserService trying to login");
    }
    @After(value = "execution (* com.hotelreservation.rest.service.UserService.authUser(..))")
    public void afterLoginUser(JoinPoint joinPoint){
        log.info("UserService login successfully");
    }
    @Before(value = "execution (* com.hotelreservation.rest.service.UserService.createUser(..))")
    public void beforeCreatingUser(JoinPoint joinPoint){
        log.info("UserService trying to create new user");
    }
    @After(value = "execution (* com.hotelreservation.rest.service.UserService.createUser(..))")
    public void afterCreatingUser(JoinPoint joinPoint){
        log.info("UserService success creating user");
    }
    @AfterThrowing(value =  "execution (* com.hotelreservation.rest.service.UserService.authUser(..))" ,throwing = "exception")
    public void afterThrowingUserLogin(JoinPoint joinPoint,Exception exception){
        //TODO learn how to use AfterThrowing... btw it doesn't working
        Object [] parameters = joinPoint.getArgs();
        for(Object param : parameters){
            if(param instanceof Exception){
                log.info("Login failed for the reason : " + ((Exception) param).getMessage());
                log.info("Login failed for the reason : " + exception.getMessage());
            }
        }
    }
}
