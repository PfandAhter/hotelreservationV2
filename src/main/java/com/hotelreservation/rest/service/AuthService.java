package com.hotelreservation.rest.service;

import com.hotelreservation.api.request.AddBalanceRequest;
import com.hotelreservation.api.request.GetBalanceRequest;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.api.response.GetBalanceResponse;
import com.hotelreservation.auth.JwtService;
import com.hotelreservation.model.entity.Balance;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.BalanceRepository;
import com.hotelreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BalanceRepository balanceRepository;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    public BaseResponse addBalanceRequest(AddBalanceRequest request) {
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));

        Balance balanceRepo = balanceRepository.findByUserId(tokenUser.getId());

        if (balanceRepo == null) {
            Balance balance = new Balance();
            balance.setAmount(request.getAmount());
            balance.setMoneyCode("TL");
            balance.setUser(tokenUser);
            balanceRepository.save(balance);
        } else {
            balanceRepo.setAmount(balanceRepo.getAmount() + request.getAmount());
            balanceRepository.save(balanceRepo);
        }
        userRepository.save(tokenUser);
        return new BaseResponse();
    }

    public GetBalanceResponse getBalance(GetBalanceRequest request) {
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));

        Balance balanceRepo = balanceRepository.findByUserId(tokenUser.getId());

        GetBalanceResponse getBalanceResponse = new GetBalanceResponse();
        getBalanceResponse.setBalance(balanceRepo.getAmount());

        return getBalanceResponse;
    }
}

/*if (request.getUsername().equals(user.getUsername())) {
            if (balanceRepo == null) {
                Balance balance = new Balance();
                balance.setAmount(request.getAmount());
                balance.setMoneyCode("TL");
                balance.setUser(user);
                balanceRepository.save(balance);

            } else {
                balanceRepo.setAmount(balanceRepo.getAmount() + request.getAmount());
                balanceRepository.save(balanceRepo);
            }
            userRepository.save(user);
        } else {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setErrorCode(Constants.HAVE_NOT_PERMISSION);
            baseResponse.setErrorDescription(Constants.HAVE_NOT_PERMISSION);
            return baseResponse;
        }
* */