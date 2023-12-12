package com.hotelreservation.api.response;

import com.hotelreservation.api.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class UserListResponse {
    private List<UserDTO> userList;
}
