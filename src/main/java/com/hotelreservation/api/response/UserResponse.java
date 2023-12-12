package com.hotelreservation.api.response;

import com.hotelreservation.api.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserResponse {
    private UserDTO userDTO;
}
