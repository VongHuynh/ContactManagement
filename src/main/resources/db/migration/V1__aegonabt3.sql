CREATE DATABASE  IF NOT EXISTS `aegonabt3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: aegonabt3
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
                           `id_user` int NOT NULL AUTO_INCREMENT,
                           `email` varchar(255) DEFAULT NULL,
                           `fullname` varchar(255) DEFAULT NULL,
                           `password` varchar(255) DEFAULT NULL,
                           `username` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'huynhvanvong200q2@gmail.com','huynh van vong','vong1234','huynhvanvong'),(3,'huynhvanvong22002@gmail.com','huynh van vong 2','vong1234','huynhvanvong2'),(8,'huynhvanvong32002@gmail.com','huynh van vong 3','vong1234','huynhvanvong3'),(9,'huynhvanvong42002@gmail.com','huynh van vong 4','vong1234','huynhvanvong4'),(10,'huynhvanvong52002@gmail.com','huynh van vong 55','vong1235','huynhvanvong5'),(14,'usertest1@gmail.com','nguyen van teo ty','123456','tesst2'),(15,'usertest22@gmail.com','usertest2','1234567','tesstuser2'),(17,'usertest53@gmail.com','nguyen van teo 2','1234567','tesstuser6');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `userid` int DEFAULT NULL,
                               `roleid` varchar(10) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `UKalsfrxvobk9ad9of368ncljuc` (`userid`,`roleid`),
                               KEY `FKevi9708lm1k06pa4dugc91k7v` (`roleid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,1,'ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `date` datetime DEFAULT NULL,
                           `email` varchar(255) DEFAULT NULL,
                           `fullname` varchar(255) DEFAULT NULL,
                           `message` varchar(255) DEFAULT NULL,
                           `phone` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,NULL,'huynhvanvoing@gmail.com','van vong','good','0989876423'),(16,NULL,'fsdfádfà@gmail.com','huỳnh văn vọng','ádfadsfâfad','0987122367'),(12,NULL,'huynhvanvoing@gmail.com','van vong','good','0989876423'),(11,NULL,'vonghvps19238@fpt.edu.vn','huỳnh văn vọng','áđa','0987122367'),(10,NULL,'vonghvps19238@fpt.edu.vn','Huỳnh Văn Vọng','kkk','0987122367'),(17,NULL,'huynhvanvong2002@gmail.com','a','dsađâ','0987122367'),(70,NULL,'huynhvanvong2002@gmail.com','Huỳnh Văn Vọng','âfđáâsd','(+84) 987132367'),(20,NULL,'vonghvps19238@fpt.edu.vn','d','sdsds','(+84) 987132367'),(64,NULL,'vonghvps19238@fpt.edu.vn','Huỳnh Văn Vọng','adsá','0987122367'),(63,NULL,'huynhvanvong2002@gmail.com','Huỳnh Văn Vọng','sấ','0987122367'),(61,NULL,'huynhvanvong2002@gmail.com','Huỳnh Văn Vọng','sâsâ','0987122367'),(60,NULL,'vonghvps19238@fpt.edu.vn','sá','sáấ','0987122367'),(66,NULL,'huynhvanvong2002@gmail.com','Huỳnh Văn Vọng','good','0987132367'),(65,NULL,'huynhvanvong2002@gmail.com','huỳnh văn vọng','ađa',''),(71,NULL,'orodliff911@who.int','abcde','q','0987132367'),(73,NULL,'huynhvanvong2002@gmail.com','Huỳnh Văn Vọng','good job','0987132367'),(74,NULL,'huynhvanvong2002@gmail.com','Huỳnh Văn Vọng','great','0987132367'),(75,NULL,'vonghvps19238@fpt.edu.vn','Huỳnh Văn Vọng','q','0987132367'),(76,NULL,'vonghvps19238@fpt.edu.vn','huỳnh văn vọng','q','0987132367');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` varchar(10) NOT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('ADMIN','Adminstrator'),('USER','User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'aegonabt3'
--

--
-- Dumping routines for database 'aegonabt3'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-12 11:25:35
