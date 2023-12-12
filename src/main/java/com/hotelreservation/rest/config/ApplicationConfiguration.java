package com.hotelreservation.rest.config;

import com.hotelreservation.lib.constants.Constants;
import com.hotelreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {
    private final UserRepository userRepository;

    // why we used :: between userRepository and findByUsername ?

    //beans always should be public.
    @Bean
    public UserDetailsService userDetailsService(){
        try{
            //check here what is that

            return userRepository::findByUsername;
        }catch (Exception e){
            throw new UsernameNotFoundException(Constants.USERNAME_NOT_FOUND);
        }
    }
}
