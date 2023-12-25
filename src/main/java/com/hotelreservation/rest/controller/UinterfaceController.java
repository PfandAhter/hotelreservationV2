package com.hotelreservation.rest.controller;

import com.hotelreservation.api.dto.RoomDTO;
import com.hotelreservation.api.request.*;
import com.hotelreservation.api.response.*;
import com.hotelreservation.rest.exception.AuthException;
import com.hotelreservation.rest.service.AuthService;
import com.hotelreservation.rest.service.RoomReservationService;
import com.hotelreservation.rest.validator.UserValidator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/hotel")
@RequiredArgsConstructor
public class UinterfaceController {
    private final AuthService authService;

    private final UserValidator userValidator;

    private final RoomReservationService roomReservationService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    public String users(){
        return "users";
    }

    @GetMapping("/addbalance")
    public String addBalance(){
        return "addbalance";
    }

    @GetMapping("/getbalance")
    public ResponseEntity<GetBalanceResponse> getBalance(BaseRequest request) throws AuthException {
        userValidator.getBalance(request);
        return ResponseEntity.ok(authService.getBalance(request));
    }

    @PostMapping("/addbalance")
    public ResponseEntity<BaseResponse> addBalance(@RequestBody @NonNull AddBalanceRequest request) {
        return ResponseEntity.ok(authService.addBalanceRequest(request));
    }

    @GetMapping("/roomlist")
    public ResponseEntity<List<RoomDTO>> getRoomList(BaseRequest baseRequest){
        return ResponseEntity.ok(roomReservationService.findAllAvailableRooms(baseRequest));
    }
    @PostMapping("/buyroom")
    public ResponseEntity<BuyRoomResponse> buyRoom(@RequestBody @NonNull BuyRoomRequest request)throws AuthException{
        userValidator.validateUserBuyRoom(request);
        return ResponseEntity.ok(roomReservationService.buyRoom(request));
    }
    @PostMapping(path= "/memberlistinrooms")
    public ResponseEntity<UserListInRoomsResponse> getMemberListInRooms(@RequestBody @NonNull UserListInRoomsRequest request)throws AuthException{
        userValidator.hasAuthority(request);
        return ResponseEntity.ok(roomReservationService.getUserListInRoom(request));
    }
    @PostMapping(path = "/setcheckin")
    public ResponseEntity<BaseResponse> setCheckIn (@RequestBody @NonNull SetCheckInRequest request) throws AuthException {
        userValidator.hasAuthority(request);
        return ResponseEntity.ok(roomReservationService.setCheckIn(request));
    }
}
