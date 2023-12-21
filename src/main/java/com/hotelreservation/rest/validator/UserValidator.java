package com.hotelreservation.rest.validator;

import com.hotelreservation.api.request.*;
import com.hotelreservation.lib.constants.Constants;
import com.hotelreservation.model.Role;
import com.hotelreservation.model.entity.Balance;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.BalanceRepository;
import com.hotelreservation.repository.RoomRepository;
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

    private final RoomRepository roomRepository;

    private final BalanceRepository balanceRepository;


    public void validateUserRegister(UserAddRequest userAddRequest) throws AuthException {
        if (userRepository.findByEmail(userAddRequest.getUsername()) != null) {
            throw new AuthException(Constants.EMAIL_IN_USE);
        } else if (userRepository.findByUsername(userAddRequest.getUsername()) != null) {
            throw new AuthException(Constants.USERNAME_IN_USE);
        }
    }

    public void authenticationUserLogin(AuthUserRequest userLoginRequest) throws AuthException {
//        User user = userRepository.findByUsername(userLoginRequest.getUsername());

        if (userRepository.findByUsername(userLoginRequest.getUsername()) == null) {
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);

            //user.getPassword()
        }else if(userRepository.findByUsername(userLoginRequest.getUsername()).getUsername().isEmpty() ||
                !(bcryptGenerator.passwordDecoder(userLoginRequest.getPassword(),
                        userRepository.findByUsername(userLoginRequest.getUsername()).getPassword()))) {
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }else if (bcryptGenerator.passwordDecoder(userLoginRequest.getPassword(),userRepository.findByUsername(userLoginRequest.getUsername()).getPassword())){
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }
    }

    public void changeRolePermission(ChangeRoleRequest changeRoleRequest) throws AuthException {
//        User user = userRepository.findByUsername(changeRoleRequest.getAdminUsername());

        if (userRepository.findByUsername(changeRoleRequest.getAdminUsername()) == null) {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
        else if (userRepository.findByUsername(changeRoleRequest.getAdminUsername()).getRole() != Role.ADMIN) {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
    }

    public void validateUserBuyRoom (BuyRoomRequest request)throws AuthException{
        User user = userRepository.findByUsername(request.getUsername());

        Balance balanceRepo = balanceRepository.findByUserIdAndMoneyCode(user.getId(),request.getMoneyCode());

        if(user == null){
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }else if (!(bcryptGenerator.passwordDecoder(request.getPassword(), user.getPassword()))) {
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        } else if (balanceRepo.getAmount() < roomRepository.findById(request.getRoomnumber()).get().getPrice()) {
            throw new AuthException(Constants.INSUFFICIENT_FUNDS);
        }
    }
    public void hasAuthority(UserListInRoomsRequest request)throws AuthException{
        User user = userRepository.findByUsername(request.getUsername());

        if(userRepository.findByUsername(request.getUsername()) == null){
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }
        else if(user.getRole() != Role.MANAGER){
            throw new AuthException(Constants.HAVE_NOT_PERMISSION);
        }
//        else if (user.getRole() != Role.ADMIN) {
//            throw new AuthException(Constants.HAVE_NOT_PERMISSION);
//        }

    }

//    public AuthException responseException() throws AuthException{
//        throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
//    }
}