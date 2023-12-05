package com.hotelReservation.demo.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
