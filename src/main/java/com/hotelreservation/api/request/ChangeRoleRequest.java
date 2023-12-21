package com.hotelreservation.api.request;

import com.hotelreservation.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeRoleRequest {
    private String adminUsername;
    private String username;
    private Role role;
}
