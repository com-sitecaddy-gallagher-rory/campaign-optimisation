-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: localhost    Database: campaign_groups
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Table structure for table `campaign`
--

DROP TABLE IF EXISTS `campaign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campaign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `budget` decimal(10,0) NOT NULL DEFAULT '0',
  `impressions` int(11) NOT NULL DEFAULT '0',
  `revenue` decimal(10,0) NOT NULL DEFAULT '0',
  `id_group` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `GROUP_idx` (`id_group`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign`
--

LOCK TABLES `campaign` WRITE;
/*!40000 ALTER TABLE `campaign` DISABLE KEYS */;
INSERT INTO `campaign` VALUES (30,'2021-July-BOF-Books',1588,36358,0,9),(31,'3_299_BBQ_G-A_CV_SHP',1323,29980,0,9),(32,'3_299_Bulbs_G-A_CV_SHP',2514,57561,0,9),(33,'3_299_Containers_G-A_OT_SHP',1191,25864,0,9),(34,'3_299_Furniture_G-A_CV_SHP',3043,68640,0,9),(35,'3_299_Gifts_AOC_G-A_OT_SHP',1455,32743,0,9),(36,'3_299_Lawn_Care_G-A_CV_SHP',1323,31023,0,9),(37,'3_299_Vegepod_G-A_CV_SHP',662,15209,0,9),(38,'3_299_Wild_Bird_G-A_AOC_SHP',265,4931,0,9),(39,'Optily-July2021-TOF-Test',0,0,0,9),(40,'Campaign new 1',492,300,500,10),(41,'Campaign new 2',1271,800,900,10),(42,'Campaign new 3',1107,700,600,10),(44,'Campaign new 4',1271,800,750,10);
/*!40000 ALTER TABLE `campaign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaigngroup`
--

DROP TABLE IF EXISTS `campaigngroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `campaigngroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaigngroup`
--

LOCK TABLES `campaigngroup` WRITE;
/*!40000 ALTER TABLE `campaigngroup` DISABLE KEYS */;
INSERT INTO `campaigngroup` VALUES (9,'Group1'),(10,'Group2');
/*!40000 ALTER TABLE `campaigngroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `optimisation`
--

DROP TABLE IF EXISTS `optimisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `optimisation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `status` varchar(32) DEFAULT NULL,
  `id_group` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_GROUP_idx` (`id_group`),
  CONSTRAINT `FK_GROUP_OPT` FOREIGN KEY (`id_group`) REFERENCES `campaigngroup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optimisation`
--

LOCK TABLES `optimisation` WRITE;
/*!40000 ALTER TABLE `optimisation` DISABLE KEYS */;
INSERT INTO `optimisation` VALUES (7,'proposed',9),(8,'applied',9),(9,'applied',10);
/*!40000 ALTER TABLE `optimisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommendation`
--

DROP TABLE IF EXISTS `recommendation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recommendation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_optimisation` int(11) NOT NULL,
  `id_campaign` int(11) NOT NULL,
  `budget` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CAMPAIGN_idx` (`id_campaign`),
  KEY `FK_OPT_idx` (`id_optimisation`),
  CONSTRAINT `FK_CAMPAIGN` FOREIGN KEY (`id_campaign`) REFERENCES `campaign` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_OPT` FOREIGN KEY (`id_optimisation`) REFERENCES `optimisation` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommendation`
--

LOCK TABLES `recommendation` WRITE;
/*!40000 ALTER TABLE `recommendation` DISABLE KEYS */;
INSERT INTO `recommendation` VALUES (24,7,30,1588),(25,7,31,1323),(26,7,32,2514),(27,7,33,1191),(28,7,34,3043),(29,7,35,1455),(30,7,36,1323),(31,7,37,662),(32,7,38,265),(33,7,39,0),(34,8,30,1588),(35,8,31,1323),(36,8,32,2514),(37,8,33,1191),(38,8,34,3043),(39,8,35,1455),(40,8,36,1323),(41,8,37,662),(42,8,38,265),(43,8,39,0),(44,9,40,492),(45,9,41,1271),(46,9,42,1107),(47,9,44,1271);
/*!40000 ALTER TABLE `recommendation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-19 17:29:57
