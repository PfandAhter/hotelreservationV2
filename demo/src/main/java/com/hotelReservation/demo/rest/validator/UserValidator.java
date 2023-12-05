package com.hotelReservation.demo.rest.validator;

import com.hotelReservation.demo.api.request.UserAddRequest;
import com.hotelReservation.demo.api.request.UserLoginRequest;
import com.hotelReservation.demo.api.response.BaseResponse;
import com.hotelReservation.demo.lib.constants.Constants;
import com.hotelReservation.demo.model.entity.User;
import com.hotelReservation.demo.repository.UserRepository;
import com.hotelReservation.demo.rest.config.BcryptGenerator;
import com.hotelReservation.demo.rest.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;
    private final BcryptGenerator bcryptGenerator;

    public void validateUserRegister(UserAddRequest userAddRequest) throws AuthException {
        if (userRepository.findByEmail(userAddRequest.getEmail()) != null) {
            throw new AuthException(Constants.EMAIL_IN_USE);
        }
    }

    public void authenticationUserLogin(UserLoginRequest userLoginRequest) throws AuthException {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());

        String currentUserPassword = userLoginRequest.getPassword();

        if (userRepository.findByEmail(userLoginRequest.getEmail()) == null) {
            throw new AuthException(Constants.EMAIl_OR_PASSWORD_NOT_MATCHED);
        }else if(user.getEmail().isEmpty()) {
            throw new AuthException(Constants.EMAIl_OR_PASSWORD_NOT_MATCHED);
        }else if (!(bcryptGenerator.passwordDecoder(currentUserPassword, user.getPassword()))) {
            throw new AuthException(Constants.EMAIl_OR_PASSWORD_NOT_MATCHED);
        }
    }
}
/*
else {
            throw new AuthException(Constants.LOGIN_SUCCESSFULL);
        }
* */