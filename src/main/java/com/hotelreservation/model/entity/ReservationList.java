package com.hotelreservation.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "reservationlist")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReservationList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_id")
    private Long roomid;

    @Column(name = "user_id")
    private Long userid;

    @Column(name ="entrydate")
    private String entrydate;

    @Column(name ="departdate")
    private String departdate;

    @Column(name = "checkin")
    private String checkin;
}
