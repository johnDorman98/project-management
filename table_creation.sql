CREATE DATABASE `poisepms`;
USE poisepms;
CREATE TABLE `architect` (
  `architect_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(25) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`architect_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contractor` (
  `contractor_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(25) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contractor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(25) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `engineer` (
  `engineer_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(25) DEFAULT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`engineer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `people` (
  `project_number` int NOT NULL,
  `role` varchar(25) NOT NULL,
  `f_name` varchar(255) DEFAULT NULL,
  `l_name` varchar(255) NOT NULL,
  `number` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_number`,`role`,`l_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `projects` (
  `project_number` int NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `building_type` varchar(50) DEFAULT NULL,
  `project_address` varchar(255) DEFAULT NULL,
  `erf_number` varchar(25) DEFAULT NULL,
  `total_fee` float DEFAULT NULL,
  `total_paid` float DEFAULT NULL,
  `deadline` varchar(10) DEFAULT NULL,
  `completion_date` varchar(10) DEFAULT NULL,
  `project_status` varchar(20) DEFAULT NULL,
  `engineer_id` int DEFAULT NULL,
  `architect_id` int DEFAULT NULL,
  `contractor_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`project_number`),
  KEY `engineer_id` (`engineer_id`),
  KEY `architect_id` (`architect_id`),
  KEY `contractor_id` (`contractor_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`engineer_id`) REFERENCES `engineer` (`engineer_id`),
  CONSTRAINT `projects_ibfk_2` FOREIGN KEY (`architect_id`) REFERENCES `architect` (`architect_id`),
  CONSTRAINT `projects_ibfk_3` FOREIGN KEY (`contractor_id`) REFERENCES `contractor` (`contractor_id`),
  CONSTRAINT `projects_ibfk_4` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
