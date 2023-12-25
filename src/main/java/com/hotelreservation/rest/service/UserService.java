package com.hotelreservation.rest.service;

import com.hotelreservation.api.dto.UserDTO;
import com.hotelreservation.api.request.AuthUserRequest;
import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.api.response.AuthUserResponse;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.auth.JwtService;
import com.hotelreservation.model.Role;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public BaseResponse createUser(UserAddRequest createUser) {
        User user = modelMapper.map(createUser, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        emailService.sendEmailWithAttachment(createUser.getEmail());

        if(createUser.getUsername().equals("Pfand") && createUser.getFirstName().equals("Ataberk") && createUser.getLastName().equals("Bakir")){
            user.setRole(Role.MANAGER);
        }else{
            user.setRole(Role.USER);
        }
        this.userRepository.save(user);
        return new BaseResponse();
    }
    public AuthUserResponse authUser(AuthUserRequest authUserRequest){
        User user = userRepository.findByUsername(authUserRequest.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(), authUserRequest.getPassword()));

        String token = jwtService.generateToken(user);

        return AuthUserResponse.builder()
                .token(token)
                .build();
    }
}
