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
import com.hotelreservation.rest.config.BcryptGenerator;
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

//    private final AuthUserResponse authUserResponse;
//    private final AuthToken authToken;



    public BaseResponse createUser(UserAddRequest createUser) {
        log.info("User saved / updated : " + createUser.getUsername());
        User user = modelMapper.map(createUser, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(createUser.getUsername().equals("Pfand") && createUser.getFirstName().equals("Ataberk") && createUser.getLastName().equals("Bakir")){
            user.setRole(Role.MANAGER);
        }else{
            user.setRole(Role.USER);
        }
        this.userRepository.save(user);

//        String token = jwtService.generateToken(user);

//        return AuthUserResponse.builder()
//                .token(token)
//                .build();

        return new BaseResponse();

    }
    public AuthUserResponse authUser(AuthUserRequest authUserRequest){
        log.info(String.format("username %s password %s", authUserRequest.getUsername(), authUserRequest.getPassword()));
        User user = userRepository.findByUsername(authUserRequest.getUsername());


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authUserRequest.getUsername(), authUserRequest.getPassword()));

        String token = jwtService.generateToken(user);
//        authUserResponse.setToken(token);
//        authUserResponse.getToken();

//        authToken.setToken(token);
//        authToken.getToken();


        return AuthUserResponse.builder()
                .token(token)
                .build();
    }

    /*public Boolean verifyToken(String token){
        User user =
    }*/

    public UserDTO findUserById(Long userid) {
        return modelMapper.map(userRepository.findUserById(userid), UserDTO.class);
    }
}
