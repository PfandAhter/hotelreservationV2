package com.hotelReservation.demo.api.response;

import com.hotelReservation.demo.api.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends BaseResponse{
    private UserDTO userDTO;
}
