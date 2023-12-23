package com.hotelreservation.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BuyRoomRequest extends BaseRequest{

    private String username;
    private String password;
    private Long roomnumber;
    private String moneyCode;

    private String member1;
    private String member2;
    private String entrydate;
    private String departdate;
}
