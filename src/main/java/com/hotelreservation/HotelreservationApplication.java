package com.hotelreservation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class HotelreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelreservationApplication.class, args);
	}
}
