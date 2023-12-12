package com.hotelreservation.api.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.hotelreservation.lib.constants.Constants.LOGIN_SUCCESSFULL;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginResponse {
    private String login = LOGIN_SUCCESSFULL;
}
