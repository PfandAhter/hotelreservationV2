package com.hotelreservation.api.response;

import com.hotelreservation.lib.constants.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class BuyRoomResponse {

    private Long purchasedRoom;
    private String result = Constants.SHOPPING_SUCCESSFULL;
    private Long balance;
}
