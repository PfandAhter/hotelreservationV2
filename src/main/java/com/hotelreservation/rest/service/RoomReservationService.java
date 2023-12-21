package com.hotelreservation.rest.service;

import com.hotelreservation.api.dto.RoomDTO;
import com.hotelreservation.api.request.BuyRoomRequest;
import com.hotelreservation.api.request.UserListInRoomsRequest;
import com.hotelreservation.api.response.BuyRoomResponse;
import com.hotelreservation.api.response.UserListInRoomsResponse;
import com.hotelreservation.model.entity.Balance;
import com.hotelreservation.model.entity.ReservationList;
import com.hotelreservation.model.entity.Room;
import com.hotelreservation.model.entity.User;
import com.hotelreservation.repository.BalanceRepository;
import com.hotelreservation.repository.ReservationListRepository;
import com.hotelreservation.repository.RoomRepository;
import com.hotelreservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomReservationService {

    private final RoomRepository roomRepository;
    
    private final MapperService mapperService;

    private final BalanceRepository balanceRepository;

    private final UserRepository userRepository;

    private final ReservationListRepository reservationListRepository;
//    private final ModelMapper modelMapper;


    public List<RoomDTO> findAllAvailableRooms() {
        return mapperService.modelMapper(roomRepository.findAll(), RoomDTO.class);
    }

    public BuyRoomResponse buyRoom(BuyRoomRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        Balance balanceRepo = balanceRepository.findByUserIdAndMoneyCode(user.getId(), request.getMoneyCode());
        Room room = roomRepository.findRoomById(request.getRoomnumber());

        balanceRepo.setAmount(balanceRepo.getAmount() - room.getPrice());

        balanceRepository.save(balanceRepo);

        User user1 = userRepository.findByUsername(request.getMember1());
        User user2 = userRepository.findByUsername(request.getMember2());


        room.setIsAvailable("FALSE");
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


        room.setMember1(request.getMember1());
        room.setMember2(request.getMember2());
        roomRepository.save(room);

        BuyRoomResponse buyRoomResponse = new BuyRoomResponse();
        buyRoomResponse.setPurchasedRoom(room.getId());
        buyRoomResponse.setBalance(balanceRepo.getAmount());

        return buyRoomResponse;
    }

    public UserListInRoomsResponse getUserListInRoom(UserListInRoomsRequest request){
        ReservationList reservationList = reservationListRepository.findUserById(request.getUserid());
        Room room = roomRepository.findRoomById(reservationList.getRoomid());

        UserListInRoomsResponse userListInRoomsResponse = new UserListInRoomsResponse();

        //todo use modelmapper

        userListInRoomsResponse.setIsAvailable(room.getIsAvailable());
        userListInRoomsResponse.setFloor(room.getFloor());
        userListInRoomsResponse.setRoomnumber(room.getId());
        userListInRoomsResponse.setMember1(room.getMember1());
        userListInRoomsResponse.setMember2(room.getMember2());

        return userListInRoomsResponse;
    }

}
