package com.hotelReservation.demo.repository;

import com.hotelReservation.demo.api.response.UserListResponse;
import com.hotelReservation.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserById(int userid);
    User findByEmail(String email);

}
