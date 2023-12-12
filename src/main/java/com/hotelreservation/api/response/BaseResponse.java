package com.hotelreservation.api.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.hotelreservation.lib.constants.Constants.SUCCES;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {
    private String resultCode = SUCCES;

    private String resultDescription = SUCCES;
}
