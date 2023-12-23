package com.hotelreservation.rest.controller;

import com.hotelreservation.api.dto.RoomDTO;
import com.hotelreservation.api.request.AddBalanceRequest;
import com.hotelreservation.api.request.BuyRoomRequest;
import com.hotelreservation.api.request.ChangeRoleRequest;
import com.hotelreservation.api.request.UserListInRoomsRequest;
import com.hotelreservation.api.response.*;
import com.hotelreservation.rest.exception.AuthException;
import com.hotelreservation.rest.service.AuthService;
import com.hotelreservation.rest.service.RoomReservationService;
import com.hotelreservation.rest.service.UserService;
import com.hotelreservation.rest.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/hotel")
@RequiredArgsConstructor
public class UinterfaceController {
    private final AuthService authService;

    private final UserValidator userValidator;

    private final RoomReservationService roomReservationService;

    private final UserService userService;




    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    public String users(){
        return "users";
    }

    @GetMapping("/addbalance")
    public String addbalance(){
        return "addbalance";
    }

    @PostMapping("/changeRole")
    public ResponseEntity<BaseResponse> changeRole(@RequestBody ChangeRoleRequest request) throws AuthException {
        userValidator.changeRolePermission(request);
        return ResponseEntity.ok(authService.changeRoleRequest(request));
    }

    @PostMapping("/addbalance")
    public ResponseEntity<BaseResponse> addBalance(@RequestBody AddBalanceRequest request) {
        return ResponseEntity.ok(authService.addBalanceRequest(request));
    }

    @GetMapping("/roomlist")
    public ResponseEntity<List<RoomDTO>> getRoomList(){
        return ResponseEntity.ok(roomReservationService.findAllAvailableRooms());
    }
    @PostMapping("/buyroom")
    public ResponseEntity<BuyRoomResponse> buyRoom(@RequestBody BuyRoomRequest request)throws AuthException{
        userValidator.validateUserBuyRoom(request);
        return ResponseEntity.ok(roomReservationService.buyRoom(request));
    }
    @PostMapping(path= "/memberListInRooms")
    public ResponseEntity<UserListInRoomsResponse> getMemberListInRooms(@RequestBody UserListInRoomsRequest request)throws AuthException{
        userValidator.hasAuthority(request);
        return ResponseEntity.ok(roomReservationService.getUserListInRoom(request));
    }
    /*@GetMapping
    public ResponseEntity<HttpResponse> confirmUserAccount (@RequestParam("token") String token){
//        Boolean isSuccess = userService.verifyToken(token);
        return R
    }*/

}
