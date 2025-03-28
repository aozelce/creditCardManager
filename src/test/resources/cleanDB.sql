-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: credit_card_db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cardissuers`
--

DROP TABLE IF EXISTS `cardissuers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cardissuers` (
                               `issuer_id` int NOT NULL AUTO_INCREMENT,
                               `issuer_name` varchar(255) NOT NULL,
                               PRIMARY KEY (`issuer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardissuers`
--

LOCK TABLES `cardissuers` WRITE;
/*!40000 ALTER TABLE `cardissuers` DISABLE KEYS */;
INSERT INTO `cardissuers` VALUES (1,'Visa'),(2,'Mastercard'),(3,'American Express'),(4,'Discover'),(5,'Capital One'),(6,'Citibank'),(7,'Chase'),(8,'Wells Fargo'),(9,'Bank of America'),(10,'Barclays'),(11,'HSBC'),(12,'PayPal Credit'),(13,'US Bank');
/*!40000 ALTER TABLE `cardissuers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcards`
--

DROP TABLE IF EXISTS `creditcards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditcards` (
                               `card_id` int NOT NULL AUTO_INCREMENT,
                               `user_id` int NOT NULL,
                               `issuer_id` int NOT NULL,
                               `card_number` varchar(255) NOT NULL,
                               `exp_date` date NOT NULL,
                               `ccv` varchar(255) NOT NULL,
                               `due_date` date NOT NULL,
                               `credit_limit` decimal(10,2) NOT NULL,
                               `current_balance` decimal(10,2) NOT NULL DEFAULT '0.00',
                               PRIMARY KEY (`card_id`),
                               KEY `FK_0` (`user_id`),
                               KEY `FK_1` (`issuer_id`),
                               CONSTRAINT `FK_0` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
                               CONSTRAINT `FK_1` FOREIGN KEY (`issuer_id`) REFERENCES `cardissuers` (`issuer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcards`
--

LOCK TABLES `creditcards` WRITE;
/*!40000 ALTER TABLE `creditcards` DISABLE KEYS */;
INSERT INTO `creditcards` VALUES (1,1,1,'4111111111111111','2025-03-31','123','2025-04-10',5000.00,1500.00),(2,2,2,'5500000000000004','2024-12-31','456','2025-01-05',10000.00,2500.00),(3,3,3,'340000000000009','2026-06-30','789','2026-07-15',15000.00,7500.00),(4,1,4,'4111222233334444','2025-08-31','321','2025-09-10',6000.00,1200.00),(5,2,5,'5500111122223333','2024-11-30','654','2024-12-05',8000.00,4000.00),(6,3,6,'340077778888999','2026-10-31','987','2026-11-15',15000.00,1250.00),(7,1,8,'4123000100002000','2027-01-31','111','2027-02-10',4500.00,0.00),(8,3,1,'5500300040005000','2026-05-31','222','2026-06-10',10000.00,7000.00),(9,2,7,'378282246310005','2025-09-30','333','2025-10-05',12000.00,2000.00),(10,3,13,'6011111111111117','2026-12-31','444','2027-01-10',20000.00,15000.00),(11,1,2,'4111777788889999','2027-06-30','555','2027-07-15',3000.00,2500.00),(12,2,10,'5500555566667777','2025-02-28','666','2025-03-05',5000.00,1000.00),(13,1,12,'340000100001000','2026-08-31','777','2026-09-10',7000.00,3500.00);
/*!40000 ALTER TABLE `creditcards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `external_auth_id` varchar(255) DEFAULT NULL,
                         `first_name` varchar(255) DEFAULT NULL,
                         `last_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`user_id`),
                         UNIQUE KEY `AK_0` (`username`),
                         UNIQUE KEY `AK_1` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john_doe','john.doe@example.com',NULL,'John','John'),(2,'jane_smith','jane.smith@example.com',NULL,'Jane','Smith'),(3,'peter_jones','peter.jones@example.com',NULL,'Peter','Jones'),(4,'emily_davis','emily.davis@example.com',NULL,'Emily','Davis'),(5,'michael_brown','michael.brown@example.com',NULL,'Michael','Brown'),(6,'sophia_wilson','sophia.wilson@example.com',NULL,'Sophia','Wilson'),(7,'james_moore','james.moore@example.com',NULL,'James','Moore'),(8,'linda_taylor','linda.taylor@example.com',NULL,'Linda','Taylor'),(9,'william_clark','william.clark@example.com',NULL,'William','Clark'),(10,'olivia_martinez','olivia.martinez@example.com',NULL,'Olivia','Martinez'),(11,'ethan_thomas','ethan.thomas@example.com',NULL,'Ethan','Thomas'),(12,'amelia_white','amelia.white@example.com',NULL,'Amelia','White'),(13,'daniel_harris','daniel.harris@example.com',NULL,'Daniel','Harris');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-28 16:34:36
