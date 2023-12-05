package com.hotelReservation.demo.rest.exception;

import com.hotelReservation.demo.api.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.hotelReservation.demo.lib.constants.Constants.FAILED;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse> handleException(RuntimeException e) {
        log.error("Error: ",e);
        return ResponseEntity.internalServerError().body(createFailResponse(FAILED));

    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<BaseResponse> handleException(AuthException e) {
        return ResponseEntity.badRequest().body(createFailResponse(e.getMessage()));
    }

    private BaseResponse createFailResponse(String message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResultCode(message);
        baseResponse.setResultDescription(message);
        return baseResponse;
    }
}
