package com.hotelreservation.rest.aspect;

import com.hotelreservation.api.request.AuthUserRequest;
import com.hotelreservation.api.request.BaseRequest;
import com.hotelreservation.api.response.AuthUserResponse;
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

    /*@Pointcut("execution(* com.hotelreservation.rest.controller.AuthController.loginUser(..))")
    public void settingToken(){
        log.info("hello world : settingToken is working well...");
    }*/
    /*@Before(value = "execution (* com.hotelreservation.rest.controller.AuthController.loginUser(..))")
    public void testingSomeThing (JoinPoint joinPoint ){
        baseRequest = new BaseRequest();
        log.info("Token : ");
    }*/

//    @AfterReturning(value = "execution(* com.hotelreservation.rest.service.UserService.authUser(..))" , returning = "token" )
//    public void afterMethodInvoked (AuthUserResponse token){
//        baseRequest.setToken(token.getToken());
//        log.info("Token setted in BaseRequest.");
//    }
    @Before(value = "execution(* com.hotelreservation.rest.controller..*(..)) ")
    public void beforeController (JoinPoint joinPoint){
        Object [] parameters = joinPoint.getArgs();
        for(Object param : parameters){
            if(param instanceof BaseRequest){
                ((BaseRequest) param).setToken(request.getHeader("Authorization"));
            }
        }
    }
}
