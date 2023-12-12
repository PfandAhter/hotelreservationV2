package com.hotelreservation.rest.service;

import com.hotelreservation.api.dto.UserDTO;
import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.model.Role;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    public BaseResponse createUser(UserAddRequest createUser) {
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

    public UserDTO findUserById(Long userid) {
        return modelMapper.map(userRepository.findUserById(userid), UserDTO.class);
    }
}
