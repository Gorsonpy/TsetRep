-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: cityweather
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city_id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `lat` varchar(100) NOT NULL,
  `lon` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_city_id_IDX` (`city_id`) USING BTREE,
  UNIQUE KEY `city_name_IDX` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (2,'101230101','福州','26.07530','119.30623'),(3,'101230501','泉州','24.90885','118.58942'),(7,'101230108','平潭','25.50367','119.79119'),(8,'101230601','漳州','24.51089','117.66180'),(9,'101280601','深圳','22.54700','114.08594'),(10,'101270101','成都','30.65946','104.06573'),(11,'7E2C','伦敦','42.98485','-81.24659'),(12,'101130101','乌鲁木齐','43.79281','87.61772'),(13,'101030100','天津','39.12559','117.19018'),(14,'101320101','香港','22.30699','114.17700'),(15,'1E98E','纽约','40.77899','-73.96900'),(16,'101010100','北京','39.90498','116.40528'),(17,'101020100','上海','31.23170','121.47264');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather`
--

DROP TABLE IF EXISTS `weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weather` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fxDate` date NOT NULL,
  `tempMax` varchar(100) NOT NULL,
  `tempMin` varchar(100) NOT NULL,
  `textDay` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city_second_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `weather_fxDate_IDX` (`fxDate`,`city_second_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=411 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather`
--

LOCK TABLES `weather` WRITE;
/*!40000 ALTER TABLE `weather` DISABLE KEYS */;
INSERT INTO `weather` VALUES (4,'2022-01-17','12','11','阴','101230101'),(9,'2022-01-16','14','11','阴','101230108'),(12,'2022-01-16','21','16','多云','101320101'),(13,'2022-01-17','21','15','多云','101320101'),(14,'2022-01-18','19','13','多云','101320101'),(15,'2022-01-16','21','14','晴','101230601'),(16,'2022-01-17','21','14','阴','101230601'),(17,'2022-01-18','20','12','多云','101230601'),(18,'2022-01-16','2','-5','多云','101030100'),(19,'2022-01-17','4','-4','晴','101030100'),(20,'2022-01-18','5','-4','多云','101030100'),(21,'2022-01-16','-2','-13','晴','101130101'),(22,'2022-01-17','4','-12','晴','101130101'),(23,'2022-01-18','2','-9','晴','101130101'),(24,'2022-01-16','15','11','小雨','101230101'),(26,'2022-01-18','13','11','小雨','101230101'),(28,'2022-01-17','13','11','小雨','101230108'),(29,'2022-01-18','13','11','阴','101230108'),(30,'2022-01-16','18','13','多云','101230501'),(31,'2022-01-17','16','12','阴','101230501'),(32,'2022-01-18','16','12','多云','101230501'),(36,'2022-01-16','15','6','多云','101270101'),(37,'2022-01-17','13','7','多云','101270101'),(38,'2022-01-18','12','7','阴','101270101'),(39,'2022-01-16','23','14','多云','101280601'),(40,'2022-01-17','22','14','多云','101280601'),(41,'2022-01-18','19','13','多云','101280601'),(72,'2022-01-16','-2','-9','晴','7E2C'),(73,'2022-01-17','-4','-6','雪','7E2C'),(74,'2022-01-18','-2','-4','阵雪','7E2C'),(162,'2022-01-16','0','0','阵雪','1E98E'),(163,'2022-01-17','6','-2','雨雪天气','1E98E'),(164,'2022-01-18','1','-2','少云','1E98E'),(272,'2022-01-19','0','-6','晴','101030100'),(275,'2022-01-19','0','-9','晴','101130101'),(278,'2022-01-19','19','11','多云','101230101'),(281,'2022-01-19','15','12','阴','101230108'),(284,'2022-01-19','20','12','多云','101230501'),(287,'2022-01-19','23','12','多云','101230601'),(290,'2022-01-19','12','7','阴','101270101'),(293,'2022-01-19','21','13','多云','101280601'),(296,'2022-01-19','21','13','多云','101320101'),(401,'2022-01-19','6','0','多云','1E98E'),(404,'2022-01-19','3','-9','多云','7E2C'),(405,'2022-01-17','4','-8','晴','101010100'),(406,'2022-01-18','3','-7','晴','101010100'),(407,'2022-01-19','0','-8','多云','101010100'),(408,'2022-01-17','11','6','阴','101020100'),(409,'2022-01-18','12','5','晴','101020100'),(410,'2022-01-19','14','6','多云','101020100');
/*!40000 ALTER TABLE `weather` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-17 20:15:54
