package com.hotelreservation.rest.config;

import com.hotelreservation.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    // todo 1:30:27


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(configure -> configure
                        .disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("asdf");
//    }

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
