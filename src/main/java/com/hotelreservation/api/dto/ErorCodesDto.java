package com.hotelreservation.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErorCodesDto {

    private String error;

    private String errorCode;

    private String errorDescription;
}
