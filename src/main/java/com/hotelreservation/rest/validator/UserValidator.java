package com.hotelreservation.rest.validator;

import com.hotelreservation.api.request.*;
import com.hotelreservation.api.response.AuthUserResponse;
import com.hotelreservation.auth.JwtService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserValidator {

    private final UserRepository userRepository;

    private final BcryptGenerator bcryptGenerator;

    private final RoomRepository roomRepository;

    private final BalanceRepository balanceRepository;

    //private final AuthUserResponse authUserResponse;
    private final AuthUserResponse authUserResponse;

    private AuthUserRequest authUserRequest;

    private final JwtService jwtService;


    public void validateUserRegister(UserAddRequest userAddRequest) throws AuthException {
        if (userRepository.findByEmail(userAddRequest.getUsername()) != null) {
            throw new AuthException(Constants.EMAIL_IN_USE);
        } else if (userRepository.findByUsername(userAddRequest.getUsername()) != null) {
            throw new AuthException(Constants.USERNAME_IN_USE);
        }
    }

    public void authenticationUserLogin(AuthUserRequest userLoginRequest) throws AuthException {

        if (userRepository.findByUsername(userLoginRequest.getUsername()) == null) {
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }else if(userRepository.findByUsername(userLoginRequest.getUsername()).getUsername().isEmpty() ||
                !(bcryptGenerator.passwordDecoder(userLoginRequest.getPassword(),
                        userRepository.findByUsername(userLoginRequest.getUsername()).getPassword()))) {
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }
    }

    public void changeRolePermission(ChangeRoleRequest changeRoleRequest) throws AuthException {
        jwtService.decryptJwt(authUserResponse.getToken());
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(authUserResponse.getToken()));

        log.info("TokenUsers firstname : " + tokenUser.getFirstName() + " , lastName : "+ tokenUser.getLastName()+" , username : "+ tokenUser.getUsername());

        //User tokenUser1 = userRepository.findByUsername(jwtService.extractUsername(authUserResponse.getToken()));


        //User tokenUser = userRepository.findByUsername(changeRoleRequest.getAdminUsername());
        if(jwtService.isTokenValid(authUserResponse.getToken(),tokenUser)){
            throw new AuthException(Constants.TOKEN_EXPIRED);
        }
        else if (userRepository.findByUsername(changeRoleRequest.getAdminUsername()) == null) {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
        else if (userRepository.findByUsername(changeRoleRequest.getAdminUsername()).getRole() != Role.ADMIN) {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
    }

    public void validateUserBuyRoom (BuyRoomRequest request)throws AuthException{
        String token = jwtService.decryptJwt(request.getToken());

        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(token));

        log.info("TokenUsers firstname : " + tokenUser.getFirstName() + " , lastName : "+ tokenUser.getLastName()+" , username : "+ tokenUser.getUsername());


        //log.info("TokenUsers first name : "+ tokenUser.getFirstName()+" , last name : "+tokenUser.getLastName() + " ,email : "+tokenUser.getEmail());

        Balance balanceRepo = balanceRepository.findByUserIdAndMoneyCode(tokenUser.getId(),request.getMoneyCode());

        /*if (!(bcryptGenerator.passwordDecoder(tokenUser.getPassword(), tokenUser.getPassword()))) {
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }*/
        if (balanceRepo.getAmount() < roomRepository.findById(request.getRoomnumber()).get().getPrice()) {
            throw new AuthException(Constants.INSUFFICIENT_FUNDS);
        }
    }
    public void hasAuthority(UserListInRoomsRequest request)throws AuthException{
        User user = userRepository.findByUsername(request.getUsername());

        authUserResponse.setToken(jwtService.decryptJwt(authUserResponse.getToken()));
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(authUserResponse.getToken()));

//        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(authUserResponse.getToken()));

        /*if(jwtService.isTokenValid(authUserResponse.getToken(),user)){
            throw new AuthException(Constants.TOKEN_EXPIRED);
        }*/if(user.getRole() != Role.MANAGER){
            throw new AuthException(Constants.HAVE_NOT_PERMISSION);
        }/* else if(userRepository.findByUsername(request.getUsername()) == null){
            throw new AuthException(Constants.USERNAME_OR_PASSWORD_NOT_MATCHED);
        }*/
    }
}