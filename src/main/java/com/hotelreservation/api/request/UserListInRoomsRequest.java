package com.hotelreservation.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListInRoomsRequest {
    private String username;
    private String password;
    private Long userid;
}
