package com.hotelreservation.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserListInRoomsResponse {

    private String isAvailable;
    private Long roomnumber;
    private int floor;
    private String member1;
    private String member2;

}
