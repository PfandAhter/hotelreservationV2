package com.hotelReservation.demo.rest.config;

import com.hotelReservation.demo.model.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j

public class SecurityConfig extends OncePerRequestFilter {

//    @Bean
//    public UserDetailsManager userDetailsManager (DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//    http.csrf(configure -> configure
//                    .disable())
//            .authorizeHttpRequests(authorize -> authorize
//                            .requestMatchers("/users")
//                            .permitAll()
//                            .anyRequest().hasRole(Role.USER));
//
//    return http.build();
//}




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("asdf");
    }
}
/*
* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(configure -> configure
                        .disable())
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/")
                                .permitAll()
                                .anyRequest().authenticated().and()
                        .formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll());



                        /*httpSecurity.csrf(configure -> configure.disable()
                                .authorizeHttpRequests().requestMatchers("/").permitAll()
                                .anyRequest().authenticated().and()
                                .formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
                        );*/


//        httpSecurity.httpBasic(Customizer.withDefaults());

//                return httpSecurity.build();
//                }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.addFilterAfter(
//                new SecurityConfig() , BasicAuthenticationFilter.class);
//        return http.build();
//    }
/*http
        .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/").permitAll()
                    .anyRequest().authenticated()
            ).build();
            */
