package com.hotelreservation.rest.service;

import com.hotelreservation.api.request.AddBalanceRequest;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.lib.constants.Constants;
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

    private final UserRepository userRepository;


    public BaseResponse addBalanceRequest(AddBalanceRequest request){
        User user = userRepository.findByUsername(request.getUsername());
        Balance balanceRepo = balanceRepository.findByUserIdAndMoneyCode(user.getId(), request.getMoneyCode());

        if(request.getUsername().equals(user.getUsername())){
            if(balanceRepo == null){
                Balance balance = new Balance();
                balance.setAmount(request.getAmount());
                balance.setMoneyCode(request.getMoneyCode());
                balance.setUser(user);
                balanceRepository.save(balance);
            }else{
                balanceRepo.setAmount(balanceRepo.getAmount() + request.getAmount());
                balanceRepository.save(balanceRepo);
            }
            userRepository.save(user);
        }else{
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setResultCode(Constants.NOT_HAVE_PERMISSION);
            baseResponse.setResultDescription(Constants.NOT_HAVE_PERMISSION);
            return baseResponse;
        }
        return new BaseResponse();
    }

}
