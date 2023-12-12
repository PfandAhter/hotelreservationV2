package com.hotelreservation.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder

public class UserDTO {
    private String username;

    private String email;

    private String role;
}
