package com.hotelReservation.demo.rest.service;


import com.hotelReservation.demo.api.dto.UserDTO;
import com.hotelReservation.demo.api.request.UserAddRequest;
import com.hotelReservation.demo.api.response.BaseResponse;
import com.hotelReservation.demo.api.response.UserListResponse;
import com.hotelReservation.demo.model.Role;
import com.hotelReservation.demo.model.entity.User;
import com.hotelReservation.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    public BaseResponse save(UserAddRequest createUser) {
        log.info("User saved / updated : " + createUser.getFirstName());
        User user = modelMapper.map(createUser, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return new BaseResponse();
    }
    public BaseResponse login (){
        return new BaseResponse();
    }

    public UserDTO findUserById(int userid) {
        return modelMapper.map(userRepository.findUserById(userid), UserDTO.class);
    }
}
