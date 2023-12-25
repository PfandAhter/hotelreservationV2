package com.hotelreservation.rest.service;

import com.hotelreservation.api.dto.RoomDTO;
import com.hotelreservation.api.request.BaseRequest;
import com.hotelreservation.api.request.BuyRoomRequest;
import com.hotelreservation.api.request.SetCheckInRequest;
import com.hotelreservation.api.request.UserListInRoomsRequest;
import com.hotelreservation.api.response.BaseResponse;
import com.hotelreservation.api.response.BuyRoomResponse;
import com.hotelreservation.api.response.UserListInRoomsResponse;
import com.hotelreservation.auth.JwtService;
import com.hotelreservation.model.entity.Balance;
import com.hotelreservation.model.entity.ReservationList;
import com.hotelreservation.model.entity.Room;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.BalanceRepository;
import com.hotelreservation.repository.ReservationListRepository;
import com.hotelreservation.repository.RoomRepository;
import com.hotelreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
@Service
@RequiredArgsConstructor
public class RoomReservationService {

    private final RoomRepository roomRepository;

    private final MapperService mapperService;

    private final BalanceRepository balanceRepository;

    private final UserRepository userRepository;

    private final ReservationListRepository reservationListRepository;

    private final JwtService jwtService;


    public List<RoomDTO> findAllAvailableRooms(BaseRequest baseRequest) {
        resetRoomIfDepartDateExpired();
        return mapperService.modelMapper(roomRepository.findAll(), RoomDTO.class);
    }

    public BuyRoomResponse buyRoom(BuyRoomRequest request) {
        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));

        Balance balanceRepo = balanceRepository.findByUserId(tokenUser.getId());

        Room room = roomRepository.findRoomById(request.getRoomnumber());

        balanceRepo.setAmount(balanceRepo.getAmount() - room.getPrice());

        balanceRepository.save(balanceRepo);
        room.setIsAvailable("FALSE");
        setReservationList(request); // will set reservationList for details information

        room.setMember1(request.getMember1());
        room.setMember2(request.getMember2());
        roomRepository.save(room);

        BuyRoomResponse buyRoomResponse = new BuyRoomResponse();
        buyRoomResponse.setPurchasedRoom(room.getId());
        buyRoomResponse.setBalance(balanceRepo.getAmount());

        return buyRoomResponse;
    }

    public UserListInRoomsResponse getUserListInRoom(UserListInRoomsRequest request) {
        resetRoomIfDepartDateExpired();

        User tokenUser = userRepository.findByUsername(jwtService.extractUsername(jwtService.decryptJwt(request.getToken().split(" ")[1])));

        ReservationList reservationList = reservationListRepository.findReservationListByUserid(tokenUser.getId());

        Room room = roomRepository.findRoomById(reservationList.getRoomid());

        UserListInRoomsResponse userListInRoomsResponse = new UserListInRoomsResponse();

        //TODO use modelmapper

        userListInRoomsResponse.setIsAvailable(room.getIsAvailable());
        userListInRoomsResponse.setFloor(room.getFloor());
        userListInRoomsResponse.setRoomnumber(room.getId());
        userListInRoomsResponse.setMember1(room.getMember1());
        userListInRoomsResponse.setMember2(room.getMember2());

        return userListInRoomsResponse;
    }

    private void resetRoomIfDepartDateExpired() {
        Date date = new Date();

        String month = String.valueOf(date.getMonth());
        String day = String.valueOf(date.getDay());

        for(int i = 0; i < reservationListRepository.findAll().size();i++){
            ReservationList reservationList = reservationListRepository.findUserById(Long.valueOf(i)+1);
            Room room = roomRepository.findRoomById(reservationList.getRoomid());

            String userDepMonth = reservationList.getDepartdate().substring(0,2);
            String userDepDay = reservationList.getDepartdate().substring(3,5);

            if(month.equals(userDepMonth) || day.equals(userDepDay)){
                room.setIsAvailable("TRUE");
                room.setMember1("NULL");
                room.setMember2("NULL");
                reservationList.setCheckin("DEPARTED");
                reservationListRepository.save(reservationList);
                roomRepository.save(room);
            }
        }
    }

    public void setReservationList(BuyRoomRequest request) {

        Room room = roomRepository.findRoomById(request.getRoomnumber());

        User user1 = userRepository.findByUsername(request.getMember1());
        User user2 = userRepository.findByUsername(request.getMember2());

        ReservationList reservationList1 = new ReservationList();
        ReservationList reservationList2 = new ReservationList();

        reservationList1.setUserid(user1.getId());
        reservationList1.setRoomid(room.getId());
        reservationList1.setCheckin("FALSE");
        reservationList1.setEntrydate(request.getEntrydate());
        reservationList1.setDepartdate(request.getDepartdate());

        reservationList2.setUserid(user2.getId());
        reservationList2.setRoomid(room.getId());
        reservationList2.setCheckin("FALSE");
        reservationList2.setEntrydate(request.getEntrydate());
        reservationList2.setDepartdate(request.getDepartdate());

        reservationListRepository.save(reservationList1);
        reservationListRepository.save(reservationList2);
    }

    public BaseResponse setCheckIn(SetCheckInRequest request) {
        ReservationList reservationList = reservationListRepository.findReservationListByUserid(request.getUserid());

        reservationList.setCheckin("TRUE");
        reservationListRepository.save(reservationList);

        return new BaseResponse();
    }
}
