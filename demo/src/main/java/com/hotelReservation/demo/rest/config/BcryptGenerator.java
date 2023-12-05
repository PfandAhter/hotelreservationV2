package com.hotelReservation.demo.rest.config;

import com.hotelReservation.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BcryptGenerator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public String passwordEncdoer(String password){
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }


    public boolean passwordDecoder (String currentPassword,String existingPassword){

        if(passwordEncoder.matches(currentPassword,existingPassword)){
            return true;
        }else{
            return false;
        }
    }

}
