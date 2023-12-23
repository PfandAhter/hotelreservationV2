package com.hotelreservation.rest.config;

import com.hotelreservation.api.request.BaseRequest;
import com.hotelreservation.api.response.AuthUserResponse;
import com.hotelreservation.auth.BearerTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {


    /*@Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(bearerTokenInterceptor());
    }
    // TODO should we change that ? / so every request going to that custom bearerTokenInterceptor , so is that good ?
    @Bean
    public BearerTokenInterceptor bearerTokenInterceptor(){
        return new BearerTokenInterceptor(authUserResponse());
    }*/

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST , proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthUserResponse authUserResponse(){
        return new AuthUserResponse();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST , proxyMode = ScopedProxyMode.TARGET_CLASS)
    public BaseRequest baseRequest(){
        return new BaseRequest();
    }
}
