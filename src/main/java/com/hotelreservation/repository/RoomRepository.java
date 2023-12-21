package com.hotelreservation.repository;

import com.hotelreservation.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findRoomById(Long roomid);
}
