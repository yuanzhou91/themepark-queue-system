CREATE DATABASE  IF NOT EXISTS `themeparks` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `themeparks`;
-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: themeparks
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attraction`
--

DROP TABLE IF EXISTS `attraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attraction` (
  `attractionid` varchar(255) NOT NULL,
  `attraction_name` varchar(255) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `physical_area` varchar(255) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attractionid`),
  KEY `FK5022pleqfnee4xpm94aqeg2yg` (`operatorid`),
  CONSTRAINT `FK5022pleqfnee4xpm94aqeg2yg` FOREIGN KEY (`operatorid`) REFERENCES `operator` (`operatorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attraction`
--

LOCK TABLES `attraction` WRITE;
/*!40000 ALTER TABLE `attraction` DISABLE KEYS */;
INSERT INTO `attraction` VALUES ('ATT0001','Roller Coaster',30,'East park','TPO10001'),('ATT0002','Water Park',20,'West park','TPO10001'),('ATT0003','Haunting Mansion',20,'South park','TPO10001'),('ATT0004','Costume Parade',20,'Central park','TPO10001'),('ATT0005','Great Adventure',20,'North park','TPO10001');
/*!40000 ALTER TABLE `attraction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `guest_account` varchar(255) NOT NULL,
  `guest_email` varchar(255) DEFAULT NULL,
  `guest_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `guest_phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`guest_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES ('248d15a3-7f92-4185-b29e-ae8115dec602','usertwo@themepark.com','Two User2','two','1231231232'),('39603876-7c78-49ec-96cb-9a43f5adcc1d','userten@themapark.com','Ten user','ten','1000000000'),('724df48c-9df0-4a23-9801-18e4da8a4dcc','userthree@themepark.com','Three User','three','3333333333'),('893939c2-647f-4ab6-8f63-e58d888d7a0a','usersix@themepark.com','Six User','six','666-666-6666'),('ac391953-e6e0-423a-b742-d267c5917839','userfour@themepark.com','Four User','four','4444444444'),('b8eb68d9-d36d-4458-af54-2cd959aa8fb7','userseven@themepark.com','Seven User','seven','7777777777'),('cf646bc9-20a0-48a6-ba50-875397036055','userone@themepark.com','One User','one','1231231233'),('d05e8fdb-623e-46c7-957b-e1fb8faa73f4','userfive@themepark.com','Five User','five','555-555-5555'),('de451aaf-e41d-40af-8e27-92ffba515deb','usereight@themepark.com','Eight User','eight','888888888'),('de748f98-d611-4ea7-8df0-6e4b8c34b6a7','usernine@themepark.com','Nine User','nine','999999999');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_group`
--

DROP TABLE IF EXISTS `guest_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdrvj5mj0sus63e2erwte9urkc` (`member`),
  KEY `FK29bs6gsrso0ux87j1yf2ntsw1` (`owner`),
  CONSTRAINT `FK29bs6gsrso0ux87j1yf2ntsw1` FOREIGN KEY (`owner`) REFERENCES `guest` (`guest_account`),
  CONSTRAINT `FKdrvj5mj0sus63e2erwte9urkc` FOREIGN KEY (`member`) REFERENCES `guest` (`guest_account`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_group`
--

LOCK TABLES `guest_group` WRITE;
/*!40000 ALTER TABLE `guest_group` DISABLE KEYS */;
INSERT INTO `guest_group` VALUES (1,'cf646bc9-20a0-48a6-ba50-875397036055','724df48c-9df0-4a23-9801-18e4da8a4dcc'),(3,'248d15a3-7f92-4185-b29e-ae8115dec602','724df48c-9df0-4a23-9801-18e4da8a4dcc'),(6,'248d15a3-7f92-4185-b29e-ae8115dec602','cf646bc9-20a0-48a6-ba50-875397036055'),(7,'724df48c-9df0-4a23-9801-18e4da8a4dcc','cf646bc9-20a0-48a6-ba50-875397036055'),(8,'893939c2-647f-4ab6-8f63-e58d888d7a0a','b8eb68d9-d36d-4458-af54-2cd959aa8fb7'),(9,'d05e8fdb-623e-46c7-957b-e1fb8faa73f4','b8eb68d9-d36d-4458-af54-2cd959aa8fb7'),(10,'ac391953-e6e0-423a-b742-d267c5917839','cf646bc9-20a0-48a6-ba50-875397036055'),(11,'d05e8fdb-623e-46c7-957b-e1fb8faa73f4','cf646bc9-20a0-48a6-ba50-875397036055'),(12,'893939c2-647f-4ab6-8f63-e58d888d7a0a','cf646bc9-20a0-48a6-ba50-875397036055'),(13,'b8eb68d9-d36d-4458-af54-2cd959aa8fb7','893939c2-647f-4ab6-8f63-e58d888d7a0a'),(14,'de451aaf-e41d-40af-8e27-92ffba515deb','893939c2-647f-4ab6-8f63-e58d888d7a0a');
/*!40000 ALTER TABLE `guest_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_wait_in_attraction`
--

DROP TABLE IF EXISTS `guest_wait_in_attraction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest_wait_in_attraction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number_in_line` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `attractionid` varchar(255) DEFAULT NULL,
  `guest_account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8n4y53huhf3i5fqu7hv0otvtb` (`attractionid`),
  KEY `FKo8x5ipdgu6frxxjac9opvyyr1` (`guest_account`),
  CONSTRAINT `FK8n4y53huhf3i5fqu7hv0otvtb` FOREIGN KEY (`attractionid`) REFERENCES `attraction` (`attractionid`),
  CONSTRAINT `FKo8x5ipdgu6frxxjac9opvyyr1` FOREIGN KEY (`guest_account`) REFERENCES `guest` (`guest_account`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_wait_in_attraction`
--

LOCK TABLES `guest_wait_in_attraction` WRITE;
/*!40000 ALTER TABLE `guest_wait_in_attraction` DISABLE KEYS */;
INSERT INTO `guest_wait_in_attraction` VALUES (54,1,1,'ATT0003','893939c2-647f-4ab6-8f63-e58d888d7a0a'),(55,2,1,'ATT0003','b8eb68d9-d36d-4458-af54-2cd959aa8fb7'),(56,3,0,'ATT0003','de451aaf-e41d-40af-8e27-92ffba515deb');
/*!40000 ALTER TABLE `guest_wait_in_attraction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operator` (
  `operatorid` varchar(255) NOT NULL,
  `operator_email` varchar(255) DEFAULT NULL,
  `operator_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `operator_phone_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`operatorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
INSERT INTO `operator` VALUES ('TPO10001','op1@themepark.com','Admin Green','admin','650-1231111'),('TPO10002','op2@themepark.com','Alex Lee','admin','650-1232222'),('TPO10003','op3@themepark.com','Tom Baber','admin','650-1233333'),('TPO10004','op4@themepark.com','Sunny Hadi','admin','650-1234444'),('TPO10005','op5@themepark.com','Allen Green','admin','650-1231231');
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservationid` varchar(255) NOT NULL,
  `status` int DEFAULT NULL,
  `attractionid` varchar(255) DEFAULT NULL,
  `guest_account` varchar(255) DEFAULT NULL,
  `scheduleid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reservationid`),
  KEY `FKbf0jdwqlmdd85nsom7khl2ghn` (`attractionid`),
  KEY `FK834xyb5h24ab9lejhmp1fi683` (`guest_account`),
  KEY `FKy4wjjwqj3dqsptm3hmwxwtk9` (`scheduleid`),
  CONSTRAINT `FK834xyb5h24ab9lejhmp1fi683` FOREIGN KEY (`guest_account`) REFERENCES `guest` (`guest_account`),
  CONSTRAINT `FKbf0jdwqlmdd85nsom7khl2ghn` FOREIGN KEY (`attractionid`) REFERENCES `attraction` (`attractionid`),
  CONSTRAINT `FKy4wjjwqj3dqsptm3hmwxwtk9` FOREIGN KEY (`scheduleid`) REFERENCES `schedule` (`scheduleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('48bcbdc2-3c6f-4bdf-a011-8a314b290982',5,'ATT0001','248d15a3-7f92-4185-b29e-ae8115dec602','c4365670-774d-4346-827b-0ceea4650e93'),('8d1f4177-96db-4e45-9f96-fe675f699267',5,'ATT0001','724df48c-9df0-4a23-9801-18e4da8a4dcc','c4365670-774d-4346-827b-0ceea4650e93'),('a50cb9bd-d3e7-4ba6-9f91-82f4da0c2272',5,'ATT0001','ac391953-e6e0-423a-b742-d267c5917839','c4365670-774d-4346-827b-0ceea4650e93'),('df9af9a6-21da-4fde-b624-b4ab310bf678',5,'ATT0001','d05e8fdb-623e-46c7-957b-e1fb8faa73f4','c4365670-774d-4346-827b-0ceea4650e93'),('dfde943c-d28f-4e13-a1e8-0deb1842d2d8',5,'ATT0001','cf646bc9-20a0-48a6-ba50-875397036055','c4365670-774d-4346-827b-0ceea4650e93');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `scheduleid` varchar(255) NOT NULL,
  `available_capacity` int DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `physical_area` varchar(255) DEFAULT NULL,
  `reserved_for_queue` int DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `total_capacity` int DEFAULT NULL,
  `attractionid` varchar(255) DEFAULT NULL,
  `operatorid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`scheduleid`),
  KEY `FKdx5n7fvhat7o3tgg5yxmgfqej` (`attractionid`),
  KEY `FKs67gkt1xoatx1u39xid19gd3q` (`operatorid`),
  CONSTRAINT `FKdx5n7fvhat7o3tgg5yxmgfqej` FOREIGN KEY (`attractionid`) REFERENCES `attraction` (`attractionid`),
  CONSTRAINT `FKs67gkt1xoatx1u39xid19gd3q` FOREIGN KEY (`operatorid`) REFERENCES `operator` (`operatorid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES ('1e782a5a-2374-455f-87b0-f915cfacec79',10,'15:00',NULL,5,'14:00',0,10,'ATT0001',NULL),('41adcd62-1c4c-4bf2-a4e2-57f89a3b9fd6',0,'13:00',NULL,2,'12:00',3,2,'ATT0003',NULL),('50075c12-f730-4eb6-879f-272196b5ee37',10,'19:00',NULL,5,'18:00',0,10,'ATT0002',NULL),('9c4f49c7-1bcf-494a-b70a-ad374df59f15',10,'13:00',NULL,5,'12:00',4,10,'ATT0002',NULL),('c4365670-774d-4346-827b-0ceea4650e93',6,'13:00',NULL,5,'12:00',3,10,'ATT0001',NULL);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-05 15:51:13
