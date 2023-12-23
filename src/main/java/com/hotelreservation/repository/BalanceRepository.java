package com.hotelreservation.repository;

import com.hotelreservation.model.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Long> {

    Balance findByUserId(Long userId);
}
