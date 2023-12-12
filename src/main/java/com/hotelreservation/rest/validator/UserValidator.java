package com.hotelreservation.rest.validator;

import com.hotelreservation.api.request.UserAddRequest;
import com.hotelreservation.api.request.UserLoginRequest;
import com.hotelreservation.lib.constants.Constants;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.UserRepository;
import com.hotelreservation.rest.config.BcryptGenerator;
import com.hotelreservation.rest.exception.AuthException;
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
        } else if (userRepository.findByUsername(userAddRequest.getUsername()) != null) {
            throw new AuthException(Constants.USERNAME_IN_USE);
        }
    }

    public void authenticationUserLogin(UserLoginRequest userLoginRequest) throws AuthException {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());

        String currentUserPassword = userLoginRequest.getPassword();

        if (userRepository.findByEmail(userLoginRequest.getEmail()) == null) {
            throw new AuthException(Constants.EMAIl_OR_PASSWORD_NOT_MATCHED);
        }else if(user.getEmail().isEmpty() || !(bcryptGenerator.passwordDecoder(currentUserPassword, user.getPassword()))) {
            throw new AuthException(Constants.EMAIl_OR_PASSWORD_NOT_MATCHED);
        }
    }
}

/*else if (!(bcryptGenerator.passwordDecoder(currentUserPassword, user.getPassword()))) {
            throw new AuthException(Constants.EMAIl_OR_PASSWORD_NOT_MATCHED);
        }

 */