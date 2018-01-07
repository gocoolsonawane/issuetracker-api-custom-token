CREATE DATABASE  IF NOT EXISTS `myissuetracker_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `myissuetracker_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for osx10.6 (i386)
--
-- Host: localhost    Database: myissuetracker_db
-- ------------------------------------------------------
-- Server version	5.6.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `issues`
--

DROP TABLE IF EXISTS `issues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issues` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `issue_name` varchar(500) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` bigint(20) DEFAULT NULL,
  `assign_to` varchar(100) DEFAULT NULL,
  `last_modify_date` bigint(20) DEFAULT NULL,
  `assgin_date` bigint(20) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `issue_type` varchar(50) DEFAULT NULL,
  `severity` varchar(50) DEFAULT NULL,
  `priority` varchar(50) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `project_id123` (`project_id`),
  KEY `myissueid` (`id`) USING BTREE,
  CONSTRAINT `project_id123` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issues`
--

LOCK TABLES `issues` WRITE;
/*!40000 ALTER TABLE `issues` DISABLE KEYS */;
INSERT INTO `issues` VALUES (1,'XYZ issue','Gokul',1497768209000,'Aniket Knase',1498405120778,1497968209000,'CLOSED','BUG','MODERATE','NORMAL','error xyz comes in xyz condition',1),(2,'PQR issue','Gokul',1497768209000,'Ganesh Jadhav',1498404366682,1497968209000,'NEW','BUG','MODERATE','NORMAL','error xyz comes in xyz condition',1),(10,'ashsh','Gokul Sonawane',1498407530705,NULL,1498407530705,NULL,'NEW','BUG','CRITICAL','LOW','sheshsh',2),(11,'afawg','Gokul Sonawane',1498407599074,NULL,1498407599074,NULL,'NEW','BUG','CRITICAL','LOW','gwagg',2),(12,'Top10Doctor-Server','Gokul Sonawane',1498407712970,NULL,1498407712970,NULL,'NEW','BUG','CRITICAL','LOW','Top10Doctor-Server',2);
/*!40000 ALTER TABLE `issues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(1000) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` bigint(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `projectid` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Resto','Complete Resturant Mgt Software','Gokul',1497768209000),(2,'Top10Docotr','Complete dcotor app software','Gokul',1497768209000),(3,'Cricket','CrickBuzz Like app','Gokul Sonawane',1498572082603);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'GPS'),(2,'Ganesh'),(4,'gocool'),(5,'gocool'),(6,'gocool');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `email` varchar(1000) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `mobile` varchar(13) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `token` varchar(95) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emailid` (`email`(767)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Gokul Sonawane','gokul@gmail.com','202cb962ac59075b964b07152d234b70','7588551048','ADMIN','1a554633-55bb-430d-8d61-a7064cdc0990'),(2,'Ganesh Jadhav','ganesh@gmail.com','202cb962ac59075b964b07152d234b70','9874563210','USER',NULL),(3,'Aniket Knase','aniket@gmail.com','202cb962ac59075b964b07152d234b70','5445121212','USER','cf15cf96-a033-4390-8bb5-65a205a74fd3'),(4,'Abhishek Nalkande','abhi@gmail.com','13213215465','9874566231','USER',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-08  0:21:26
