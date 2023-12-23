package com.hotelreservation.rest.validator;

import com.hotelreservation.api.request.*;
import com.hotelreservation.auth.JwtService;
import com.hotelreservation.lib.constants.Constants;
import com.hotelreservation.model.Role;
import com.hotelreservation.model.entity.Balance;
import com.hotelreservation.model.entity.Room;
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

    public void getBalance(GetBalanceRequest request) throws AuthException {
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));
        //request.getToken().split(" ")[1]
        Balance balance = balanceRepository.findByUserId(tokenUser.getId());

        if(jwtService.isTokenValid(request.getToken().split(" ")[1],tokenUser)){
            throw new AuthException(Constants.TOKEN_EXPIRED);
        }else if (balance == null) {
            throw new AuthException(Constants.ACCESS_DENIED);
        }
    }

    public void validateUserBuyRoom (BuyRoomRequest request)throws AuthException{
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));

        Balance balanceRepo = balanceRepository.findByUserId(tokenUser.getId());

        Room roomRepo = roomRepository.findRoomById(request.getRoomnumber());

        if(!jwtService.isTokenValid(request.getToken().split(" ")[1],tokenUser)){
            throw new AuthException(Constants.TOKEN_EXPIRED);
        }else if(roomRepo.getIsAvailable().equals("FALSE")){
            throw new AuthException(Constants.ROOM_IS_NOT_AVAILABLE);
        }else if (balanceRepo.getAmount() < roomRepository.findById(request.getRoomnumber()).get().getPrice()) {
            throw new AuthException(Constants.INSUFFICIENT_FUNDS);
        }
    }
    public void hasAuthority(UserListInRoomsRequest request)throws AuthException{
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));

        if(jwtService.isTokenValid(request.getToken().split(" ")[1],tokenUser)){
            throw new AuthException(Constants.TOKEN_EXPIRED);
        }else if(tokenUser.getRole() != Role.MANAGER){
            throw new AuthException(Constants.HAVE_NOT_PERMISSION);
        }
    }
}