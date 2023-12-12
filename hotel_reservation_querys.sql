CREATE DATABASE  IF NOT EXISTS `hotel_reservation`;

USE `hotel_reservation`;

--
-- users
--

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL ,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- balance
--

DROP TABLE IF EXISTS `balance`;

CREATE TABLE `balance` (
 `id` BIGINT NOT NULL AUTO_INCREMENT,
 `amount` BIGINT DEFAULT NULL,
 `money_code` varchar(255) DEFAULT NULL,
 `user_user_id` BIGINT DEFAULT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (user_user_id) REFERENCES users(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Rooms
--    

DROP TABLE IF EXISTS `rooms`;

CREATE TABLE `rooms` (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `floor` int DEFAULT NULL,
  `roomsize` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `isAvabilable` varchar(45) DEFAULT FALSE,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Room members learn how to many to many / one to many
--

DROP TABLE IF EXISTS `reservationlist`;

CREATE TABLE `reservationlist` (
  `res_id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT DEFAULT NULL ,
  `user_id` INT DEFAULT NULL,
  `entrydate` varchar(45) DEFAULT NULL,
  `departdate`varchar(45) DEFAULT NULL,
  `checkin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- error_codes
-- 
DROP TABLE IF EXISTS `error_codes`;

CREATE TABLE `error_codes` (
  `id` BIGINT NOT NULL ,
  `error` varchar(255) DEFAULT NULL,
  `error_code` varchar(45) DEFAULT NULL,
  `error_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
    
    