package com.hotelreservation.repository;

import com.hotelreservation.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long userid);
    User findByEmail(String email);

    User findByUsername(String username);

}