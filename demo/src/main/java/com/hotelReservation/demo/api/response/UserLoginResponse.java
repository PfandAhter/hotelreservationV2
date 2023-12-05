package com.hotelReservation.demo.api.response;
import lombok.Getter;
import lombok.Setter;

import static com.hotelReservation.demo.lib.constants.Constants.LOGIN_SUCCESSFULL;
import static com.hotelReservation.demo.lib.constants.Constants.SUCCES;

@Getter
@Setter
public class UserLoginResponse extends BaseResponse{

    private String login = LOGIN_SUCCESSFULL;
}
