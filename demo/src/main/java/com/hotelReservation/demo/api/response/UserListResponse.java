package com.hotelReservation.demo.api.response;

import com.hotelReservation.demo.api.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserListResponse extends BaseResponse{

    private List<UserDTO> userList;
}
