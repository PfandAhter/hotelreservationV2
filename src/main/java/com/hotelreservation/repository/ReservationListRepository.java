package com.hotelreservation.repository;

import com.hotelreservation.model.entity.ReservationList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationListRepository extends JpaRepository<ReservationList,Long> {
    ReservationList findUserById(Long userid);

    ReservationList findReservationListByUserid(Long userid);
}
