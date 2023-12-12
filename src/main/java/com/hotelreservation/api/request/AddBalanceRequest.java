package com.hotelreservation.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBalanceRequest extends BaseRequest{

    private String username;

    private Long amount;

    private String moneyCode;

}
