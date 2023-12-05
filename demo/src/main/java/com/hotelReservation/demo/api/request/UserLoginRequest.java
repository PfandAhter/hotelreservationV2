package com.hotelReservation.demo.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {

    private String email;
    private String password;
}
