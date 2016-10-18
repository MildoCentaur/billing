-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: billing
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.12.04.1

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `accountType` varchar(255) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `concept` varchar(255) DEFAULT NULL,
  `credit` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `debit` double DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  `receipt_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB9D38A2D9FB70779` (`createdBy_id`),
  KEY `FKB9D38A2DE5484787` (`payment_id`),
  KEY `FKB9D38A2D225B58AD` (`client_id`),
  KEY `FKB9D38A2D25B861C7` (`order_id`),
  KEY `FKB9D38A2D4CD1A44D` (`supplier_id`),
  KEY `FKB9D38A2DDB209962` (`lastModifiedBy_id`),
  KEY `FKB9D38A2D219037ED` (`bill_id`),
  KEY `FKB9D38A2DF88A9133` (`order_id`),
  KEY `FKB9D38A2DB70247` (`receipt_id`),
  CONSTRAINT `FKB9D38A2DB70247` FOREIGN KEY (`receipt_id`) REFERENCES `receipts` (`id`),
  CONSTRAINT `FKB9D38A2D219037ED` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`),
  CONSTRAINT `FKB9D38A2D225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKB9D38A2D25B861C7` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKB9D38A2D4CD1A44D` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `FKB9D38A2D9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKB9D38A2DDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKB9D38A2DE5484787` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`),
  CONSTRAINT `FKB9D38A2DF88A9133` FOREIGN KEY (`order_id`) REFERENCES `delivery_orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointement`
--

DROP TABLE IF EXISTS `appointement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `alert` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5A28DD229FB70779` (`createdBy_id`),
  KEY `FK5A28DD22DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK5A28DD229FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK5A28DD22DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointement`
--

LOCK TABLES `appointement` WRITE;
/*!40000 ALTER TABLE `appointement` DISABLE KEYS */;
INSERT INTO `appointement` VALUES (1,'2015-05-09 13:52:33','2015-05-09 13:52:33',0,0,NULL,'','TBD','Cumpleaños Ale',3,3),(2,'2015-05-09 14:38:35','2015-05-09 14:38:35',0,0,NULL,'','dsadasdasdasdasd','Cumple mama',3,3);
/*!40000 ALTER TABLE `appointement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banks`
--

DROP TABLE IF EXISTS `banks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banks` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `code` int(11) NOT NULL,
  `longName` varchar(255) DEFAULT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK592C1B79FB70779` (`createdBy_id`),
  KEY `FK592C1B7DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK592C1B79FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK592C1B7DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banks`
--

LOCK TABLES `banks` WRITE;
/*!40000 ALTER TABLE `banks` DISABLE KEYS */;
/*!40000 ALTER TABLE `banks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill_items`
--

DROP TABLE IF EXISTS `bill_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `amount` double DEFAULT NULL,
  `packages` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `color_id` bigint(20) DEFAULT NULL,
  `product_family_id` bigint(20) DEFAULT NULL,
  `bill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK20690D88B600EDA7` (`color_id`),
  KEY `FK20690D88219037ED` (`bill_id`),
  KEY `FK20690D88A1927786` (`product_family_id`),
  CONSTRAINT `FK20690D88219037ED` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`),
  CONSTRAINT `FK20690D88A1927786` FOREIGN KEY (`product_family_id`) REFERENCES `product_families` (`id`),
  CONSTRAINT `FK20690D88B600EDA7` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_items`
--

LOCK TABLES `bill_items` WRITE;
/*!40000 ALTER TABLE `bill_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bills` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `billNumber` bigint(20) DEFAULT NULL,
  `billType` varchar(255) DEFAULT NULL,
  `cancelled` tinyint(1) NOT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `ivaCategory` int(11) DEFAULT NULL,
  `ivaTax` double DEFAULT NULL,
  `orderNumber` bigint(20) DEFAULT NULL,
  `packages` int(11) DEFAULT NULL,
  `payed` tinyint(1) NOT NULL,
  `saleCondition` int(11) DEFAULT NULL,
  `subTotal` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `deliveryOrder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5965D4C9FB70779` (`createdBy_id`),
  KEY `FK5965D4C225B58AD` (`client_id`),
  KEY `FK5965D4CDB209962` (`lastModifiedBy_id`),
  KEY `FK5965D4CCD8E7927` (`deliveryOrder_id`),
  CONSTRAINT `FK5965D4CCD8E7927` FOREIGN KEY (`deliveryOrder_id`) REFERENCES `delivery_orders` (`id`),
  CONSTRAINT `FK5965D4C225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK5965D4C9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK5965D4CDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bills`
--

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cheque`
--

DROP TABLE IF EXISTS `cheque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cheque` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `activity` tinyint(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `crossed` tinyint(1) NOT NULL,
  `cuitFirmante` varchar(255) DEFAULT NULL,
  `dateHanded` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `firmante` varchar(255) DEFAULT NULL,
  `handedTo` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `others` tinyint(1) NOT NULL,
  `received` datetime DEFAULT NULL,
  `rejected` tinyint(1) NOT NULL,
  `value` double NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAED8F2219FB70779` (`createdBy_id`),
  KEY `FKAED8F221225B58AD` (`client_id`),
  KEY `FKAED8F22114050FCD` (`bank_id`),
  KEY `FKAED8F221DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKAED8F22114050FCD` FOREIGN KEY (`bank_id`) REFERENCES `banks` (`id`),
  CONSTRAINT `FKAED8F221225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKAED8F2219FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKAED8F221DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cheque`
--

LOCK TABLES `cheque` WRITE;
/*!40000 ALTER TABLE `cheque` DISABLE KEYS */;
/*!40000 ALTER TABLE `cheque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_contacts`
--

DROP TABLE IF EXISTS `client_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nextel` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `office` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK755DB2479FB70779` (`createdBy_id`),
  KEY `FK755DB247225B58AD` (`client_id`),
  KEY `FK755DB247DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK755DB247225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK755DB2479FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK755DB247DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_contacts`
--

LOCK TABLES `client_contacts` WRITE;
/*!40000 ALTER TABLE `client_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_payment_bills`
--

DROP TABLE IF EXISTS `client_payment_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_payment_bills` (
  `client_payment_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  KEY `FKA87F9C5F99D72870` (`client_payment_id`),
  KEY `FKA87F9C5F219037ED` (`bill_id`),
  CONSTRAINT `FKA87F9C5F219037ED` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`),
  CONSTRAINT `FKA87F9C5F99D72870` FOREIGN KEY (`client_payment_id`) REFERENCES `client_payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_payment_bills`
--

LOCK TABLES `client_payment_bills` WRITE;
/*!40000 ALTER TABLE `client_payment_bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_payment_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_payment_delivery_orders`
--

DROP TABLE IF EXISTS `client_payment_delivery_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_payment_delivery_orders` (
  `client_payment_id` bigint(20) NOT NULL,
  `delivery_order_id` bigint(20) NOT NULL,
  KEY `FKAD946CE3C968D91E` (`delivery_order_id`),
  KEY `FKAD946CE399D72870` (`client_payment_id`),
  CONSTRAINT `FKAD946CE399D72870` FOREIGN KEY (`client_payment_id`) REFERENCES `client_payments` (`id`),
  CONSTRAINT `FKAD946CE3C968D91E` FOREIGN KEY (`delivery_order_id`) REFERENCES `delivery_orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_payment_delivery_orders`
--

LOCK TABLES `client_payment_delivery_orders` WRITE;
/*!40000 ALTER TABLE `client_payment_delivery_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_payment_delivery_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_payments`
--

DROP TABLE IF EXISTS `client_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE99A61219FB70779` (`createdBy_id`),
  KEY `FKE99A6121E5484787` (`payment_id`),
  KEY `FKE99A6121225B58AD` (`client_id`),
  KEY `FKE99A6121DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKE99A6121225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKE99A61219FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKE99A6121DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKE99A6121E5484787` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_payments`
--

LOCK TABLES `client_payments` WRITE;
/*!40000 ALTER TABLE `client_payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `IVACondition` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `addressDelivery` varchar(255) DEFAULT NULL,
  `balanceActivity` double DEFAULT NULL,
  `balanceBilling` double DEFAULT NULL,
  `clientType` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `credit` double DEFAULT NULL,
  `creditInCheques` double DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nextel` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickname` (`nickname`,`name`),
  KEY `FK334B86089FB70779` (`createdBy_id`),
  KEY `FK334B8608DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK334B86089FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK334B8608DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `coordinate` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `coordinate` (`coordinate`),
  KEY `FKAF3EBD709FB70779` (`createdBy_id`),
  KEY `FKAF3EBD70DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKAF3EBD709FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKAF3EBD70DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,NULL,'2015-05-09 13:54:56',0,'999','','ffffff','Directo','Blanco',0,NULL,3),(2,NULL,'2015-03-14 12:12:32',0,'546','','fdd44e','Directo','Maiz',1,NULL,3),(3,NULL,'2015-03-14 12:55:20',0,'527','','ffc299','Directo','Crudo',1,NULL,3),(4,NULL,'2015-05-09 13:57:45',0,'508','','2f9278','Directo','Cemento Claro',1,NULL,3),(5,NULL,'2015-03-14 12:12:14',0,'543','','5a6647','Directo','Cemento oscuro',1,NULL,3),(6,NULL,'2015-03-14 12:12:21',0,'544','','10e1e5','Directo','Celeste',1,NULL,3),(7,NULL,'2015-03-14 12:12:26',0,'545','','bfbfbf','Directo','Vapor',1,NULL,3),(11,NULL,'2015-03-14 12:15:14',0,'539','','006e99','Directo','Gabardina',1,NULL,3),(12,NULL,'2015-03-14 13:11:06',0,'531','','ffbb00','Directo','Arena',1,NULL,3),(13,NULL,'2015-03-14 12:14:34',0,'547','','ff80ae','Directo','Rosa',1,NULL,3),(14,NULL,'2015-03-14 12:14:40',0,'548','','f76492','Directo','Rosa BB',1,NULL,3),(15,NULL,'2015-03-14 12:14:50',0,'542','','ee3a97','Directo','Rosa chicle',1,NULL,3),(16,NULL,'2015-03-14 12:14:57',0,'541','','fe8bc1','Directo','Rosa nostalgia',1,NULL,3),(17,NULL,'2015-03-14 12:15:05',0,'540','','7cfe96','Directo','Verde menta',1,NULL,3),(18,NULL,'2015-06-02 07:38:29',0,'532','','4db01c','Directo','Verde malva',1,NULL,2),(19,NULL,'2015-05-09 13:56:51',0,'512','','3dffff','Directo','Agua marina',1,NULL,3),(20,NULL,'2015-03-14 12:53:25',0,'526','','b3b3b3','Directo','Gris acero',1,NULL,3),(21,NULL,'2015-05-09 13:59:59',0,'499','','faa499','Directo','Salmon',1,NULL,3),(22,NULL,'2015-03-28 12:19:27',0,'518','','ec468e','Directo','Frambuesa',1,NULL,3),(23,NULL,'2015-03-28 12:53:11',0,'516','','ff9c38','Directo','Durazno',1,NULL,3),(24,NULL,'2015-05-09 13:59:27',0,'502','','ff6f47','Directo','Langostino',1,NULL,3),(25,NULL,'2015-03-28 12:58:50',0,'517','','b84900','Directo','Lacre',1,NULL,3),(26,NULL,'2015-03-28 13:09:49',0,'515','','a2bf22','Directo','Mostaza',1,NULL,3),(27,NULL,'2015-05-09 13:59:19',0,'503','','ff70ff','Directo','Lila',1,NULL,3),(28,NULL,'2015-05-09 13:59:09',0,'504','','6b6bff','Directo','Indigo',1,NULL,3),(29,NULL,'2015-05-09 13:55:15',0,'549','','f2f2f2','Directo','Gaspe',1,NULL,3),(30,NULL,'2015-03-14 13:09:12',0,'530','','158093','Directo','Azulejo',1,NULL,3),(31,NULL,'2015-03-14 13:07:36',0,'528','','006600','Directo','Verde militar',2,NULL,3),(33,NULL,'2015-03-14 13:36:31',0,'533','','006570','Reactivo','Pizarra',2,NULL,3),(34,NULL,'2015-05-09 13:58:54',0,'505','','3863a3','Reactivo','Aeronautico',2,NULL,3),(35,NULL,'2015-03-14 12:39:24',0,'000','','000000','Reactivo','Negro',2,NULL,3),(36,NULL,'2015-05-09 13:57:57',0,'500','','00008f','Reactivo','Azul marino',2,NULL,3),(37,NULL,'2015-05-09 13:57:19',0,'510','','00bbff','Reactivo','Calipso',2,NULL,3),(38,NULL,'2015-05-09 13:56:22',0,'513','','522100','Reactivo','Chocolate',2,NULL,3),(39,NULL,'2015-03-28 13:12:31',0,'514','','ff6600','Reactivo','Naranja',2,NULL,3),(40,NULL,'2015-03-14 13:08:16',0,'529','','ff0000','Reactivo','Rojo',2,NULL,3),(41,NULL,'2015-05-09 13:55:33',0,'551','','e8e8e8','Directo','Melange',2,NULL,3),(42,NULL,'2015-05-09 13:57:06',0,'511','','fd358c','Reactivo','Fucsia',2,NULL,3),(43,NULL,'2015-03-28 12:04:29',0,'519','','007500','Reactivo','Verde colegial',2,NULL,3),(44,NULL,'2015-05-09 13:58:37',0,'506','','6666ff','Reactivo','Liberty',2,NULL,3),(45,NULL,'2015-05-09 13:58:26',0,'507','','ff7b24','Reactivo','Mandarina',2,NULL,3),(46,NULL,'2015-05-09 13:55:24',0,'550','','35fdfd','Reactivo','Turqueza',2,NULL,3),(47,NULL,'2015-06-02 10:33:20',0,'525','','006eff','Reactivo','Francia',3,NULL,3),(49,NULL,'2015-03-14 12:17:31',0,'524','','00b300','Reactivo','Verde benetton',3,NULL,3),(50,NULL,'2015-03-14 12:17:25',0,'523','','00d100','Reactivo','Verde nuevo',3,NULL,3),(51,NULL,'2015-03-14 12:17:19',0,'522','','3d3d3d','Reactivo','Gris topo',3,NULL,3),(52,NULL,'2015-03-14 12:17:13',0,'521','','8a0e00','Reactivo','Bordeaux',3,NULL,3),(53,NULL,'2015-03-14 12:16:54',0,'520','','00008a','Reactivo','Azulino',3,NULL,3),(54,NULL,'2015-05-09 13:58:11',0,'501','','0000bd','Reactivo','Petroleo',2,NULL,3),(55,NULL,'2015-05-09 13:57:32',0,'509','','ffffd6','Directo','tostado',1,NULL,3);
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_order_items`
--

DROP TABLE IF EXISTS `delivery_order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB023CBC4F88A9133` (`order_id`),
  KEY `FKB023CBC4A13F2E7` (`product_id`),
  CONSTRAINT `FKB023CBC4A13F2E7` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKB023CBC4F88A9133` FOREIGN KEY (`order_id`) REFERENCES `delivery_orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_order_items`
--

LOCK TABLES `delivery_order_items` WRITE;
/*!40000 ALTER TABLE `delivery_order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_orders`
--

DROP TABLE IF EXISTS `delivery_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `date` datetime DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84F4A9909FB70779` (`createdBy_id`),
  KEY `FK84F4A99025B861C7` (`order_id`),
  KEY `FK84F4A990DB209962` (`lastModifiedBy_id`),
  KEY `FK84F4A990225B58AD` (`client_id`),
  CONSTRAINT `FK84F4A990225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK84F4A99025B861C7` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK84F4A9909FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK84F4A990DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_orders`
--

LOCK TABLES `delivery_orders` WRITE;
/*!40000 ALTER TABLE `delivery_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabrics`
--

DROP TABLE IF EXISTS `fabrics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fabrics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `cottonPercent` double NOT NULL,
  `cuello` tinyint(1) NOT NULL,
  `diametro` int(11) NOT NULL,
  `galga` int(11) NOT NULL,
  `longname` varchar(255) DEFAULT NULL,
  `lycraPercent` double NOT NULL,
  `mayas` int(11) NOT NULL,
  `mercerizado` tinyint(1) NOT NULL,
  `pasadas` int(11) NOT NULL,
  `polyesterPercent` double NOT NULL,
  `puno` tinyint(1) NOT NULL,
  `rinde` double NOT NULL,
  `shortname` varchar(255) DEFAULT NULL,
  `width` double NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `FKBEDC186E9FB70779` (`createdBy_id`),
  KEY `FKBEDC186EDB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKBEDC186E9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKBEDC186EDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabrics`
--

LOCK TABLES `fabrics` WRITE;
/*!40000 ALTER TABLE `fabrics` DISABLE KEYS */;
INSERT INTO `fabrics` VALUES (1,NULL,'2015-06-02 11:06:08',0,'2018','',100,0,18,20,'Panal  algodon',0,14,0,14,0,0,2,'Panal',2,NULL,3),(3,'2015-03-09 12:29:20','2015-03-09 12:29:20',0,'3116L','',100,0,20,0,'Yersey semi-pesado algodon ',0,14,0,14,0,0,0,'Yersey semi-pesado',0,3,3),(4,'2015-03-09 12:31:19','2015-03-09 12:31:19',0,'3131L','',100,0,30,24,'Yersey algodon',0,14,0,14,0,0,3.1,'Yersey algodon',0.92,3,3),(5,'2015-03-09 12:32:54','2015-03-09 12:32:54',0,'1132L','',100,0,24,24,'yersey liviano algodon',0,14,0,14,0,0,3.6,'Yersey liviano',0.9,3,3),(6,'2015-03-09 12:35:35','2015-03-09 12:35:35',0,'1020','',100,0,0,20,'Friza Liviana algodon',0,16,0,16,0,0,1.75,'Friza Liviana ',0.92,3,3),(7,'2015-03-09 12:40:20','2015-03-09 12:40:20',0,'1050','',100,0,0,24,'Friza pesada algodon',0,16,0,16,0,0,1.65,'Friza Pesada',0.95,3,3),(8,NULL,'2015-03-10 22:56:06',0,'3456789876543','',98,0,0,21,'Ale testdsadsa',1,2,0,2,1,0,2,'Ale test',2,NULL,3),(9,NULL,'2015-06-02 11:01:10',0,'9999','',100,0,0,0,'surtido de tejido',0,20,0,20,0,0,2,'Varios',2,NULL,3);
/*!40000 ALTER TABLE `fabrics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fibers`
--

DROP TABLE IF EXISTS `fibers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fibers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `percentage` double NOT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `fabric_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB40409071091726D` (`fabric_id`),
  CONSTRAINT `FKB40409071091726D` FOREIGN KEY (`fabric_id`) REFERENCES `fabrics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fibers`
--

LOCK TABLES `fibers` WRITE;
/*!40000 ALTER TABLE `fibers` DISABLE KEYS */;
INSERT INTO `fibers` VALUES (1,0,0,'ALAL - PLATEX','24/1 Peinado',NULL),(2,0,0.8,'ALAL - PLATEX','24/1 peinado',NULL),(3,0,0.2,'NAHAMIAS','Poliester 150',NULL),(6,0,100,'ALAL - PLATEX','16/1 Peinado',3),(7,0,100,'ALAL - PLATEX','24/1 Cardado',4),(8,0,100,'ALAL - PLATEX','24/1 Cardado',5),(9,0,65,'ALAL - PLATEX','20/1 Peinado',6),(10,0,35,'ALAL - PLATEX','12/1 Peinado',6),(11,0,65,'ALAL - PLATEX','20/1 Peinado',7),(12,0,35,'ALAL - PLATEX','12/1 Peinado',7),(13,0,0,NULL,NULL,NULL),(14,0,0,'ALAL - PLATEX','24/1 Peinado',NULL),(15,0,0,NULL,NULL,NULL),(16,0,0,'','',NULL),(17,0,0,'ALAL - PLATEX','24/1 Peinado',NULL),(18,0,0,NULL,NULL,1),(19,0,0,'','',1),(20,0,0,'','',1),(21,0,0,'ALAL - PLATEX','24/1 Peinado',1);
/*!40000 ALTER TABLE `fibers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_price`
--

DROP TABLE IF EXISTS `list_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_price` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2A0356689FB70779` (`createdBy_id`),
  KEY `FK2A035668DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK2A0356689FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK2A035668DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_price`
--

LOCK TABLES `list_price` WRITE;
/*!40000 ALTER TABLE `list_price` DISABLE KEYS */;
INSERT INTO `list_price` VALUES (1,'2015-03-06 00:00:00','2015-03-06 00:00:00',0,1,'2015-03-06 00:00:00','Lista de Precios General',1,1);
/*!40000 ALTER TABLE `list_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_price_item`
--

DROP TABLE IF EXISTS `list_price_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_price_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `price` double NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `list_price_id` bigint(20) DEFAULT NULL,
  `product_family_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK17869E8A9FB70779` (`createdBy_id`),
  KEY `FK17869E8ADB209962` (`lastModifiedBy_id`),
  KEY `FK17869E8AA7BC11EA` (`list_price_id`),
  KEY `FK17869E8AA1927786` (`product_family_id`),
  CONSTRAINT `FK17869E8A9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK17869E8AA1927786` FOREIGN KEY (`product_family_id`) REFERENCES `product_families` (`id`),
  CONSTRAINT `FK17869E8AA7BC11EA` FOREIGN KEY (`list_price_id`) REFERENCES `list_price` (`id`),
  CONSTRAINT `FK17869E8ADB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_price_item`
--

LOCK TABLES `list_price_item` WRITE;
/*!40000 ALTER TABLE `list_price_item` DISABLE KEYS */;
INSERT INTO `list_price_item` VALUES (1,NULL,NULL,0,10,NULL,NULL,1,1),(2,NULL,NULL,0,10,NULL,NULL,1,2),(3,NULL,NULL,0,10,NULL,NULL,1,3),(4,NULL,NULL,0,10,NULL,NULL,1,4),(5,NULL,NULL,0,50,NULL,NULL,1,5),(6,NULL,NULL,0,51,NULL,NULL,1,6),(7,NULL,NULL,0,52,NULL,NULL,1,7),(8,NULL,NULL,0,53,NULL,NULL,1,8),(9,NULL,NULL,0,150,NULL,NULL,1,9),(10,NULL,NULL,0,151,NULL,NULL,1,10),(11,NULL,NULL,0,152,NULL,NULL,1,11),(12,NULL,NULL,0,153,NULL,NULL,1,12),(13,NULL,NULL,0,50,NULL,NULL,1,13),(14,NULL,NULL,0,51,NULL,NULL,1,14),(15,NULL,NULL,0,52,NULL,NULL,1,15),(16,NULL,NULL,0,53,NULL,NULL,1,16),(17,NULL,NULL,0,50,NULL,NULL,1,17),(18,NULL,NULL,0,51,NULL,NULL,1,18),(19,NULL,NULL,0,52,NULL,NULL,1,19),(20,NULL,NULL,0,53,NULL,NULL,1,20),(21,NULL,NULL,0,50,NULL,NULL,1,21),(22,NULL,NULL,0,51,NULL,NULL,1,22),(23,NULL,NULL,0,52,NULL,NULL,1,23),(24,NULL,NULL,0,53,NULL,NULL,1,24),(25,NULL,NULL,0,50,NULL,NULL,1,25),(26,NULL,NULL,0,51,NULL,NULL,1,26),(27,NULL,NULL,0,52,NULL,NULL,1,27),(28,NULL,NULL,0,53,NULL,NULL,1,28),(29,NULL,NULL,0,51,NULL,NULL,1,29),(30,NULL,NULL,0,52,NULL,NULL,1,30),(31,NULL,NULL,0,53,NULL,NULL,1,31),(32,NULL,NULL,0,54,NULL,NULL,1,32),(33,NULL,NULL,0,55,NULL,NULL,1,33),(34,NULL,NULL,0,56,NULL,NULL,1,34),(35,NULL,NULL,0,57,NULL,NULL,1,35),(36,NULL,NULL,0,58,NULL,NULL,1,36),(37,NULL,NULL,0,90,NULL,NULL,1,37),(38,NULL,NULL,0,90,NULL,NULL,1,38),(39,NULL,NULL,0,90,NULL,NULL,1,39),(40,NULL,NULL,0,90,NULL,NULL,1,40),(41,NULL,NULL,0,90,NULL,NULL,1,41),(42,NULL,NULL,0,90,NULL,NULL,1,42),(43,NULL,NULL,0,90,NULL,NULL,1,43),(44,NULL,NULL,0,90,NULL,NULL,1,44),(45,NULL,NULL,0,45,NULL,NULL,1,45),(46,NULL,NULL,0,46,NULL,NULL,1,46),(47,NULL,NULL,0,47,NULL,NULL,1,47),(48,NULL,NULL,0,48,NULL,NULL,1,48);
/*!40000 ALTER TABLE `list_price_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operation_log`
--

DROP TABLE IF EXISTS `operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(255) DEFAULT NULL,
  `completed` tinyint(1) NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  `error` text,
  `intention` varchar(255) DEFAULT NULL,
  `operationEnded` time DEFAULT NULL,
  `operationStart` time DEFAULT NULL,
  `result` text,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=784 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation_log`
--

LOCK TABLES `operation_log` WRITE;
/*!40000 ALTER TABLE `operation_log` DISABLE KEYS */;
INSERT INTO `operation_log` VALUES (1,'CREATE',1,'2015-03-09 12:22:14','','Registra un tejido','12:22:14','12:22:14','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(2,'CREATE',1,'2015-03-09 12:25:28','','Registra un tejido','12:25:28','12:25:28','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(3,'CREATE',1,'2015-03-09 12:27:01','','Registra un tejido','12:27:01','12:27:01','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(4,'CREATE',1,'2015-03-09 12:29:20','','Registra un tejido','12:29:20','12:29:20','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(5,'CREATE',1,'2015-03-09 12:31:19','','Registra un tejido','12:31:19','12:31:19','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(6,'CREATE',1,'2015-03-09 12:32:54','','Registra un tejido','12:32:54','12:32:54','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(7,'CREATE',1,'2015-03-09 12:35:35','','Registra un tejido','12:35:35','12:35:35','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(8,'CREATE',1,'2015-03-09 12:40:20','','Registra un tejido','12:40:20','12:40:20','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(9,'CREATE',1,'2015-03-09 20:51:17','','Registra un nuevo color','20:51:17','20:51:17','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(10,'UPDATE',0,'2015-03-09 20:56:59','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','20:56:59','20:56:59','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(11,'UPDATE',0,'2015-03-09 20:57:20','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','20:57:20','20:57:20','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(12,'CREATE',1,'2015-03-09 21:02:27','','Registra un tejido','21:02:27','21:02:27','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(13,'CREATE',1,'2015-03-09 21:04:39','','Registra un tejido','21:04:39','21:04:39','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(14,'UPDATE',0,'2015-03-09 21:12:39','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','21:12:39','21:12:39','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(15,'UPDATE',1,'2015-03-09 21:12:45','','Incrementa el sctock de un producto y genera su codigo de barras','21:12:45','21:12:45','0010010210006815','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(16,'UPDATE',0,'2015-03-09 21:29:29','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','21:29:29','21:29:29','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(17,'UPDATE',1,'2015-03-09 21:29:36','','Incrementa el sctock de un producto y genera su codigo de barras','21:29:36','21:29:36','0010010198006815','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(18,'UPDATE',0,'2015-03-09 23:55:38','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','23:55:38','23:55:38','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(19,'CREATE',1,'2015-03-10 00:14:23','','Registra un patron de rayado','00:14:23','00:14:23','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(20,'UPDATE',1,'2015-03-10 00:21:22','','Incrementa el sctock de un producto y genera su codigo de barras','00:21:22','00:21:22','0050010210006915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(21,'UPDATE',0,'2015-03-10 13:23:27','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','13:23:27','13:23:27','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(22,'UPDATE',0,'2015-03-10 13:23:39','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','13:23:39','13:23:39','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(23,'UPDATE',0,'2015-03-10 13:23:40','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','13:23:40','13:23:40','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(24,'UPDATE',1,'2015-03-10 13:24:05','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:05','13:24:05','0050010200406915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(25,'CREATE',1,'2015-03-10 22:56:06','','Registra un tejido','22:56:06','22:56:06','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(26,'CREATE',1,'2015-03-10 23:02:57','','Registra un patron de rayado','23:02:57','23:02:57','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(27,'CREATE',1,'2015-03-10 23:18:48','','Registra un nuevo color','23:18:48','23:18:48','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(28,'CREATE',1,'2015-03-10 23:20:15','','Registra un nuevo color','23:20:15','23:20:15','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(29,'CREATE',1,'2015-03-10 23:24:29','','Registra un nuevo color','23:24:29','23:24:29','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(30,'CREATE',1,'2015-03-10 23:26:42','','Registra un nuevo color','23:26:42','23:26:42','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(31,'CREATE',1,'2015-03-10 23:28:30','','Registra un nuevo color','23:28:30','23:28:30','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(32,'CREATE',1,'2015-03-10 23:29:12','','Registra un nuevo color','23:29:12','23:29:12','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(33,'CREATE',0,'2015-03-10 23:34:57','Duplicate entry \'ffffff\' for key \'coordinate\'; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: Duplicate entry \'ffffff\' for key \'coordinate\'','Registra un nuevo color','23:34:57','23:34:57','','FAILURE','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(34,'CREATE',0,'2015-03-10 23:35:27','Duplicate entry \'ffffff\' for key \'coordinate\'; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: Duplicate entry \'ffffff\' for key \'coordinate\'','Registra un nuevo color','23:35:27','23:35:27','','FAILURE','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(35,'CREATE',0,'2015-03-10 23:36:14','Duplicate entry \'ffffff\' for key \'coordinate\'; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: Duplicate entry \'ffffff\' for key \'coordinate\'','Registra un nuevo color','23:36:14','23:36:14','','FAILURE','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(36,'CREATE',1,'2015-03-10 23:37:00','','Registra un nuevo color','23:37:00','23:37:00','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(37,'CREATE',1,'2015-03-10 23:37:33','','Registra un nuevo color','23:37:33','23:37:33','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(38,'CREATE',1,'2015-03-10 23:38:31','','Registra un nuevo color','23:38:31','23:38:31','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(39,'CREATE',1,'2015-03-10 23:41:12','','Registra un nuevo color','23:41:12','23:41:12','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(40,'CREATE',1,'2015-03-10 23:44:47','','Registra un nuevo color','23:44:47','23:44:47','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(41,'CREATE',1,'2015-03-10 23:45:34','','Registra un nuevo color','23:45:34','23:45:34','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(42,'CREATE',1,'2015-03-10 23:46:03','','Registra un nuevo color','23:46:03','23:46:03','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(43,'CREATE',1,'2015-03-10 23:46:12','','Registra un nuevo color','23:46:12','23:46:12','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(44,'CREATE',1,'2015-03-10 23:46:21','','Registra un nuevo color','23:46:21','23:46:21','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(45,'CREATE',1,'2015-03-10 23:46:41','','Registra un nuevo color','23:46:41','23:46:41','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(46,'CREATE',1,'2015-03-10 23:46:54','','Registra un nuevo color','23:46:54','23:46:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(47,'CREATE',1,'2015-03-10 23:47:29','','Registra un nuevo color','23:47:29','23:47:29','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(48,'CREATE',1,'2015-03-10 23:47:38','','Registra un nuevo color','23:47:38','23:47:38','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(49,'CREATE',1,'2015-03-10 23:47:45','','Registra un nuevo color','23:47:45','23:47:45','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(50,'CREATE',1,'2015-03-10 23:47:53','','Registra un nuevo color','23:47:53','23:47:53','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(51,'CREATE',1,'2015-03-10 23:48:45','','Registra un nuevo color','23:48:45','23:48:45','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(52,'CREATE',1,'2015-03-10 23:49:30','','Registra un nuevo color','23:49:30','23:49:30','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(53,'CREATE',1,'2015-03-10 23:52:14','','Registra un nuevo color','23:52:14','23:52:14','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(54,'CREATE',1,'2015-03-10 23:53:07','','Registra un nuevo color','23:53:07','23:53:07','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(55,'CREATE',1,'2015-03-10 23:54:54','','Registra un nuevo color','23:54:54','23:54:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(56,'CREATE',1,'2015-03-10 23:55:54','','Registra un nuevo color','23:55:54','23:55:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(57,'CREATE',1,'2015-03-10 23:57:13','','Registra un nuevo color','23:57:13','23:57:13','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(58,'CREATE',1,'2015-03-10 23:59:07','','Registra un nuevo color','23:59:07','23:59:07','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(59,'CREATE',1,'2015-03-10 23:59:51','','Registra un nuevo color','23:59:51','23:59:51','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(60,'CREATE',1,'2015-03-11 00:00:54','','Registra un nuevo color','00:00:54','00:00:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(61,'CREATE',1,'2015-03-11 00:02:21','','Registra un nuevo color','00:02:21','00:02:21','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(62,'CREATE',1,'2015-03-11 00:02:54','','Registra un nuevo color','00:02:54','00:02:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(63,'CREATE',1,'2015-03-11 00:03:24','','Registra un nuevo color','00:03:24','00:03:24','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(64,'CREATE',1,'2015-03-11 00:04:26','','Registra un nuevo color','00:04:26','00:04:26','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(65,'CREATE',1,'2015-03-11 00:12:06','','Registra un nuevo color','00:12:06','00:12:06','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(66,'CREATE',1,'2015-03-11 00:12:52','','Registra un nuevo color','00:12:52','00:12:52','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(67,'CREATE',1,'2015-03-11 00:13:39','','Registra un nuevo color','00:13:39','00:13:39','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(68,'CREATE',1,'2015-03-11 00:14:16','','Registra un nuevo color','00:14:16','00:14:16','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(69,'CREATE',1,'2015-03-11 00:14:37','','Registra un nuevo color','00:14:37','00:14:37','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(70,'CREATE',1,'2015-03-11 00:15:16','','Registra un nuevo color','00:15:16','00:15:16','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(71,'CREATE',1,'2015-03-11 00:16:03','','Registra un nuevo color','00:16:03','00:16:03','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(72,'CREATE',1,'2015-03-11 00:16:51','','Registra un nuevo color','00:16:51','00:16:51','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(73,'CREATE',1,'2015-03-11 00:17:13','','Registra un nuevo color','00:17:13','00:17:13','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(74,'CREATE',1,'2015-03-11 00:17:42','','Registra un nuevo color','00:17:42','00:17:42','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(75,'CREATE',1,'2015-03-11 00:18:47','','Registra un nuevo color','00:18:47','00:18:47','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(76,'CREATE',1,'2015-03-11 00:19:42','','Registra un nuevo color','00:19:42','00:19:42','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(77,'CREATE',1,'2015-03-11 00:20:08','','Registra un nuevo color','00:20:08','00:20:08','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(78,'CREATE',1,'2015-03-11 00:20:49','','Registra un nuevo color','00:20:49','00:20:49','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(79,'CREATE',1,'2015-03-11 00:21:24','','Registra un nuevo color','00:21:24','00:21:24','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(80,'CREATE',1,'2015-03-11 00:22:17','','Registra un nuevo color','00:22:17','00:22:17','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(81,'CREATE',1,'2015-03-11 00:22:56','','Registra un nuevo color','00:22:56','00:22:56','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(82,'CREATE',0,'2015-03-11 00:23:39','Duplicate entry \'b3b3b3\' for key \'coordinate\'; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: Duplicate entry \'b3b3b3\' for key \'coordinate\'','Registra un nuevo color','00:23:39','00:23:39','','FAILURE','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(83,'CREATE',1,'2015-03-11 00:24:12','','Registra un nuevo color','00:24:12','00:24:12','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(84,'CREATE',1,'2015-03-11 00:24:59','','Registra un nuevo color','00:24:59','00:24:59','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(85,'CREATE',1,'2015-03-11 00:25:35','','Registra un nuevo color','00:25:35','00:25:35','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(86,'CREATE',1,'2015-03-11 00:25:57','','Registra un nuevo color','00:25:57','00:25:57','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(87,'CREATE',1,'2015-03-11 00:27:16','','Registra un nuevo color','00:27:16','00:27:16','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(88,'CREATE',1,'2015-03-11 00:28:40','','Registra un nuevo color','00:28:40','00:28:40','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(89,'CREATE',1,'2015-03-11 00:29:10','','Registra un nuevo color','00:29:10','00:29:10','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(90,'UPDATE',1,'2015-03-13 14:24:03','','Incrementa el sctock de un producto y genera su codigo de barras','14:24:03','14:24:03','0210010200007215','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(91,'CREATE',1,'2015-03-14 11:48:21','','Registra un nuevo color','11:48:21','11:48:21','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(92,'CREATE',1,'2015-03-14 12:01:46','','Registra un nuevo color','12:01:46','12:01:46','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(93,'CREATE',1,'2015-03-14 12:07:45','','Registra un nuevo color','12:07:45','12:07:45','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(94,'CREATE',1,'2015-03-14 12:12:07','','Registra un nuevo color','12:12:07','12:12:07','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(95,'CREATE',1,'2015-03-14 12:12:14','','Registra un nuevo color','12:12:14','12:12:14','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(96,'CREATE',1,'2015-03-14 12:12:21','','Registra un nuevo color','12:12:21','12:12:21','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(97,'CREATE',1,'2015-03-14 12:12:26','','Registra un nuevo color','12:12:26','12:12:26','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(98,'CREATE',1,'2015-03-14 12:12:32','','Registra un nuevo color','12:12:32','12:12:32','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(99,'CREATE',1,'2015-03-14 12:14:34','','Registra un nuevo color','12:14:34','12:14:34','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(100,'CREATE',1,'2015-03-14 12:14:40','','Registra un nuevo color','12:14:40','12:14:40','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(101,'CREATE',1,'2015-03-14 12:14:50','','Registra un nuevo color','12:14:50','12:14:50','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(102,'CREATE',1,'2015-03-14 12:14:57','','Registra un nuevo color','12:14:57','12:14:57','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(103,'CREATE',1,'2015-03-14 12:15:05','','Registra un nuevo color','12:15:05','12:15:05','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(104,'CREATE',1,'2015-03-14 12:15:14','','Registra un nuevo color','12:15:14','12:15:14','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(105,'CREATE',1,'2015-03-14 12:16:54','','Registra un nuevo color','12:16:54','12:16:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(106,'CREATE',1,'2015-03-14 12:17:13','','Registra un nuevo color','12:17:13','12:17:13','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(107,'CREATE',1,'2015-03-14 12:17:19','','Registra un nuevo color','12:17:19','12:17:19','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(108,'CREATE',1,'2015-03-14 12:17:25','','Registra un nuevo color','12:17:25','12:17:25','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(109,'CREATE',1,'2015-03-14 12:17:31','','Registra un nuevo color','12:17:31','12:17:31','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(110,'CREATE',1,'2015-03-14 12:17:38','','Registra un nuevo color','12:17:38','12:17:38','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(111,'CREATE',1,'2015-03-14 12:21:48','','Registra un tejido','12:21:48','12:21:48','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(112,'CREATE',1,'2015-03-14 12:31:37','','Registra un patron de rayado','12:31:37','12:31:37','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(113,'CREATE',1,'2015-03-14 12:35:11','','Registra un patron de rayado','12:35:11','12:35:11','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(114,'UPDATE',0,'2015-03-14 12:37:19','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','12:37:19','12:37:19','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(115,'CREATE',1,'2015-03-14 12:39:24','','Registra un nuevo color','12:39:24','12:39:24','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(116,'UPDATE',1,'2015-03-14 12:40:09','','Incrementa el sctock de un producto y genera su codigo de barras','12:40:09','12:40:09','0380040220007315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(117,'UPDATE',1,'2015-03-14 12:40:47','','Incrementa el sctock de un producto y genera su codigo de barras','12:40:47','12:40:47','0390351211507315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(118,'UPDATE',1,'2015-03-14 12:41:07','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:07','12:41:07','0390351211107315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(119,'UPDATE',1,'2015-03-14 12:41:44','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:44','12:41:44','0380050221207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(120,'UPDATE',1,'2015-03-14 12:42:18','','Incrementa el sctock de un producto y genera su codigo de barras','12:42:18','12:42:18','0390351205607315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(121,'UPDATE',0,'2015-03-14 12:43:05','Tipo de producto fuera de la lista de precios','Incrementa el sctock de un producto y genera su codigo de barras','12:43:05','12:43:05','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(122,'UPDATE',1,'2015-03-14 12:43:47','','Incrementa el sctock de un producto y genera su codigo de barras','12:43:47','12:43:47','0380050219907315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(123,'UPDATE',1,'2015-03-14 12:44:42','','Incrementa el sctock de un producto y genera su codigo de barras','12:44:42','12:44:42','0390351212507315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(124,'UPDATE',1,'2015-03-14 12:45:58','','Incrementa el sctock de un producto y genera su codigo de barras','12:45:58','12:45:58','0420050214607315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(125,'UPDATE',1,'2015-03-14 12:46:53','','Incrementa el sctock de un producto y genera su codigo de barras','12:46:53','12:46:53','0420060215807315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(126,'UPDATE',1,'2015-03-14 12:47:09','','Incrementa el sctock de un producto y genera su codigo de barras','12:47:09','12:47:09','0420060205407315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(127,'UPDATE',1,'2015-03-14 12:47:27','','Incrementa el sctock de un producto y genera su codigo de barras','12:47:27','12:47:27','0420060208107315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(128,'UPDATE',1,'2015-03-14 12:47:44','','Incrementa el sctock de un producto y genera su codigo de barras','12:47:44','12:47:44','0420060209307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(129,'UPDATE',1,'2015-03-14 12:49:03','','Incrementa el sctock de un producto y genera su codigo de barras','12:49:03','12:49:03','0420070207907315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(130,'UPDATE',1,'2015-03-14 12:50:45','','Incrementa el sctock de un producto y genera su codigo de barras','12:50:45','12:50:45','0380040207207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(131,'UPDATE',1,'2015-03-14 12:52:27','','Incrementa el sctock de un producto y genera su codigo de barras','12:52:27','12:52:27','0390351205207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(132,'CREATE',1,'2015-03-14 12:53:25','','Registra un nuevo color','12:53:25','12:53:25','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(133,'UPDATE',1,'2015-03-14 12:53:46','','Incrementa el sctock de un producto y genera su codigo de barras','12:53:46','12:53:46','0390361206307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(134,'UPDATE',1,'2015-03-14 12:54:23','','Incrementa el sctock de un producto y genera su codigo de barras','12:54:23','12:54:23','0380201205607315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(135,'CREATE',1,'2015-03-14 12:55:20','','Registra un nuevo color','12:55:20','12:55:20','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(136,'UPDATE',1,'2015-03-14 12:55:38','','Incrementa el sctock de un producto y genera su codigo de barras','12:55:38','12:55:38','0380060220007315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(137,'UPDATE',1,'2015-03-14 12:56:25','','Incrementa el sctock de un producto y genera su codigo de barras','12:56:25','12:56:25','0390361228607315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(138,'UPDATE',1,'2015-03-14 12:59:38','','Incrementa el sctock de un producto y genera su codigo de barras','12:59:38','12:59:38','0390361208607315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(139,'UPDATE',1,'2015-03-14 13:01:09','','Incrementa el sctock de un producto y genera su codigo de barras','13:01:09','13:01:09','0390361211607315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(140,'UPDATE',1,'2015-03-14 13:01:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:01:24','13:01:24','0390361211107315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(141,'UPDATE',1,'2015-03-14 13:01:34','','Incrementa el sctock de un producto y genera su codigo de barras','13:01:34','13:01:34','0390361210007315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(142,'UPDATE',1,'2015-03-14 13:01:59','','Incrementa el sctock de un producto y genera su codigo de barras','13:01:59','13:01:59','0390361209207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(143,'UPDATE',1,'2015-03-14 13:02:44','','Incrementa el sctock de un producto y genera su codigo de barras','13:02:44','13:02:44','0380040219907315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(144,'UPDATE',1,'2015-03-14 13:03:33','','Incrementa el sctock de un producto y genera su codigo de barras','13:03:33','13:03:33','0380070210107315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(145,'CREATE',1,'2015-03-14 13:07:36','','Registra un nuevo color','13:07:36','13:07:36','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(146,'CREATE',1,'2015-03-14 13:08:16','','Registra un nuevo color','13:08:16','13:08:16','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(147,'UPDATE',1,'2015-03-14 13:09:07','','Incrementa el sctock de un producto y genera su codigo de barras','13:09:07','13:09:07','0390400210107315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(148,'CREATE',1,'2015-03-14 13:09:12','','Registra un nuevo color','13:09:12','13:09:12','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(149,'UPDATE',1,'2015-03-14 13:09:43','','Incrementa el sctock de un producto y genera su codigo de barras','13:09:43','13:09:43','0390541212207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(150,'UPDATE',1,'2015-03-14 13:10:12','','Incrementa el sctock de un producto y genera su codigo de barras','13:10:12','13:10:12','0380300208207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(151,'UPDATE',1,'2015-03-14 13:10:57','','Incrementa el sctock de un producto y genera su codigo de barras','13:10:57','13:10:57','0380050206707315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(152,'CREATE',1,'2015-03-14 13:11:06','','Registra un nuevo color','13:11:06','13:11:06','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(153,'CREATE',1,'2015-03-14 13:11:13','','Registra un nuevo color','13:11:13','13:11:13','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(154,'UPDATE',1,'2015-03-14 13:11:42','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:42','13:11:42','0390311207307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(155,'CREATE',1,'2015-03-14 13:12:00','','Registra un nuevo color','13:12:00','13:12:00','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(156,'CREATE',1,'2015-03-14 13:16:25','','Registra un patron de rayado','13:16:25','13:16:25','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(157,'CREATE',1,'2015-03-14 13:16:52','','Registra un patron de rayado','13:16:52','13:16:52','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(158,'UPDATE',1,'2015-03-14 13:21:16','','Incrementa el sctock de un producto y genera su codigo de barras','13:21:16','13:21:16','0420040205707315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(159,'UPDATE',1,'2015-03-14 13:23:25','','Incrementa el sctock de un producto y genera su codigo de barras','13:23:25','13:23:25','0420051151107315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(160,'UPDATE',1,'2015-03-14 13:24:07','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:07','13:24:07','0420060215307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(161,'UPDATE',1,'2015-03-14 13:24:51','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:51','13:24:51','0380060211307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(162,'UPDATE',1,'2015-03-14 13:25:34','','Incrementa el sctock de un producto y genera su codigo de barras','13:25:34','13:25:34','0390361209207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(163,'UPDATE',1,'2015-03-14 13:26:28','','Incrementa el sctock de un producto y genera su codigo de barras','13:26:28','13:26:28','0380060212507315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(164,'UPDATE',1,'2015-03-14 13:27:27','','Incrementa el sctock de un producto y genera su codigo de barras','13:27:27','13:27:27','0390361213507315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(165,'UPDATE',1,'2015-03-14 13:28:28','','Incrementa el sctock de un producto y genera su codigo de barras','13:28:28','13:28:28','0390311217007315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(166,'UPDATE',1,'2015-03-14 13:29:16','','Incrementa el sctock de un producto y genera su codigo de barras','13:29:16','13:29:16','0380040220807315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(167,'UPDATE',1,'2015-03-14 13:30:07','','Incrementa el sctock de un producto y genera su codigo de barras','13:30:08','13:30:07','0390351212207315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(168,'UPDATE',1,'2015-03-14 13:30:55','','Incrementa el sctock de un producto y genera su codigo de barras','13:30:55','13:30:55','0390361211307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(169,'UPDATE',1,'2015-03-14 13:31:36','','Incrementa el sctock de un producto y genera su codigo de barras','13:31:36','13:31:36','0390541206707315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(170,'UPDATE',1,'2015-03-14 13:32:20','','Incrementa el sctock de un producto y genera su codigo de barras','13:32:20','13:32:20','0390361210807315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(171,'CREATE',1,'2015-03-14 13:36:31','','Registra un nuevo color','13:36:31','13:36:31','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(172,'UPDATE',1,'2015-03-14 13:37:41','','Incrementa el sctock de un producto y genera su codigo de barras','13:37:41','13:37:41','0390331205307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(173,'UPDATE',1,'2015-03-14 13:38:03','','Incrementa el sctock de un producto y genera su codigo de barras','13:38:03','13:38:03','0390331205307315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(174,'UPDATE',1,'2015-03-16 12:41:59','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:59','12:41:59','0220050217507515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(175,'UPDATE',1,'2015-03-16 12:43:31','','Incrementa el sctock de un producto y genera su codigo de barras','12:43:31','12:43:31','0220050198607515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(176,'UPDATE',1,'2015-03-16 12:43:38','','Incrementa el sctock de un producto y genera su codigo de barras','12:43:38','12:43:38','0220050188007515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(177,'UPDATE',1,'2015-03-16 12:45:45','','Incrementa el sctock de un producto y genera su codigo de barras','12:45:45','12:45:45','0230540187207515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(178,'UPDATE',1,'2015-03-16 12:46:04','','Incrementa el sctock de un producto y genera su codigo de barras','12:46:04','12:46:04','0230310179407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(179,'CREATE',1,'2015-03-16 12:55:39','','Registra un nuevo color','12:55:39','12:55:39','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(180,'UPDATE',1,'2015-03-16 12:56:29','','Incrementa el sctock de un producto y genera su codigo de barras','12:56:29','12:56:29','0220290196407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(181,'UPDATE',1,'2015-03-16 12:59:00','','Incrementa el sctock de un producto y genera su codigo de barras','12:59:00','12:59:00','0230540187907515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(182,'UPDATE',1,'2015-03-16 12:59:21','','Incrementa el sctock de un producto y genera su codigo de barras','12:59:21','12:59:21','0230360208307515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(183,'UPDATE',1,'2015-03-16 12:59:37','','Incrementa el sctock de un producto y genera su codigo de barras','12:59:37','12:59:37','0220290188807515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(184,'UPDATE',1,'2015-03-16 13:02:25','','Incrementa el sctock de un producto y genera su codigo de barras','13:02:25','13:02:25','0220290196707515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(185,'UPDATE',1,'2015-03-16 13:02:37','','Incrementa el sctock de un producto y genera su codigo de barras','13:02:37','13:02:37','0230360207407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(186,'UPDATE',1,'2015-03-16 13:02:49','','Incrementa el sctock de un producto y genera su codigo de barras','13:02:49','13:02:49','0220040195207515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(187,'UPDATE',1,'2015-03-16 13:04:26','','Incrementa el sctock de un producto y genera su codigo de barras','13:04:26','13:04:26','0210010200407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(188,'UPDATE',1,'2015-03-16 13:11:11','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:12','13:11:11','0140060214607515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(189,'UPDATE',1,'2015-03-16 13:11:27','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:27','13:11:27','0150360230607515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(190,'UPDATE',1,'2015-03-16 13:11:38','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:38','13:11:38','0130010214707515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(191,'UPDATE',1,'2015-03-16 13:11:51','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:51','13:11:51','0150540220207515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(192,'UPDATE',1,'2015-03-16 13:15:21','','Incrementa el sctock de un producto y genera su codigo de barras','13:15:21','13:15:21','0150360199607515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(193,'UPDATE',1,'2015-03-16 13:15:34','','Incrementa el sctock de un producto y genera su codigo de barras','13:15:34','13:15:34','0140060223107515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(194,'UPDATE',1,'2015-03-16 13:17:30','','Incrementa el sctock de un producto y genera su codigo de barras','13:17:30','13:17:30','0150360241507515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(195,'UPDATE',1,'2015-03-16 13:19:20','','Incrementa el sctock de un producto y genera su codigo de barras','13:19:20','13:19:20','0150360221007515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(196,'UPDATE',1,'2015-03-16 13:19:32','','Incrementa el sctock de un producto y genera su codigo de barras','13:19:32','13:19:32','0140060227807515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(197,'UPDATE',1,'2015-03-16 13:23:44','','Incrementa el sctock de un producto y genera su codigo de barras','13:23:44','13:23:44','0150360235407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(198,'UPDATE',1,'2015-03-16 13:24:09','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:09','13:24:09','0140300217407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(199,'UPDATE',1,'2015-03-16 13:26:09','','Incrementa el sctock de un producto y genera su codigo de barras','13:26:09','13:26:09','0150360231207515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(200,'UPDATE',1,'2015-03-16 13:26:15','','Incrementa el sctock de un producto y genera su codigo de barras','13:26:15','13:26:15','0150360227007515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(201,'UPDATE',1,'2015-03-16 13:30:09','','Incrementa el sctock de un producto y genera su codigo de barras','13:30:09','13:30:09','0070360175207515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(202,'UPDATE',1,'2015-03-16 13:30:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:30:24','13:30:24','0070310198007515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(203,'UPDATE',1,'2015-03-16 13:30:34','','Incrementa el sctock de un producto y genera su codigo de barras','13:30:34','13:30:34','0070540199707515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(204,'UPDATE',1,'2015-03-16 13:33:58','','Incrementa el sctock de un producto y genera su codigo de barras','13:33:58','13:33:58','0070540194807515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(205,'UPDATE',1,'2015-03-16 13:34:10','','Incrementa el sctock de un producto y genera su codigo de barras','13:34:10','13:34:10','0080470175907515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(206,'UPDATE',1,'2015-03-16 13:34:20','','Incrementa el sctock de un producto y genera su codigo de barras','13:34:21','13:34:20','0060180194107515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(207,'UPDATE',1,'2015-03-16 13:34:35','','Incrementa el sctock de un producto y genera su codigo de barras','13:34:35','13:34:35','0070360191407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(208,'CREATE',1,'2015-03-16 13:35:52','','Registra un nuevo color','13:35:52','13:35:52','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(209,'UPDATE',1,'2015-03-16 13:36:30','','Incrementa el sctock de un producto y genera su codigo de barras','13:36:30','13:36:30','0060120192707515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(210,'UPDATE',1,'2015-03-16 13:44:38','','Incrementa el sctock de un producto y genera su codigo de barras','13:44:38','13:44:38','0070540195307515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(211,'UPDATE',1,'2015-03-16 13:44:52','','Incrementa el sctock de un producto y genera su codigo de barras','13:44:52','13:44:52','0060050204907515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(212,'UPDATE',1,'2015-03-16 13:45:04','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:04','13:45:04','0070310200007515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(213,'UPDATE',1,'2015-03-16 13:45:18','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:18','13:45:18','0060040207407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(214,'CREATE',1,'2015-03-16 13:45:27','','Registra un nuevo color','13:45:27','13:45:27','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(215,'UPDATE',1,'2015-03-16 13:46:06','','Incrementa el sctock de un producto y genera su codigo de barras','13:46:06','13:46:06','0070410200407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(216,'UPDATE',1,'2015-03-16 13:54:22','','Incrementa el sctock de un producto y genera su codigo de barras','13:54:22','13:54:22','0070310199607515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(217,'UPDATE',1,'2015-03-16 13:54:36','','Incrementa el sctock de un producto y genera su codigo de barras','13:54:36','13:54:36','0080520170907515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(218,'UPDATE',1,'2015-03-16 13:54:47','','Incrementa el sctock de un producto y genera su codigo de barras','13:54:47','13:54:47','0070350193907515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(219,'UPDATE',1,'2015-03-16 13:54:59','','Incrementa el sctock de un producto y genera su codigo de barras','13:54:59','13:54:59','0060020182707515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(220,'UPDATE',1,'2015-03-16 13:55:11','','Incrementa el sctock de un producto y genera su codigo de barras','13:55:11','13:55:11','0070310200407515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(221,'UPDATE',1,'2015-03-16 13:58:56','','Incrementa el sctock de un producto y genera su codigo de barras','13:58:56','13:58:56','0070310204807515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(222,'UPDATE',1,'2015-03-16 13:59:01','','Incrementa el sctock de un producto y genera su codigo de barras','13:59:01','13:59:01','0070310195707515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(223,'UPDATE',1,'2015-03-16 13:59:06','','Incrementa el sctock de un producto y genera su codigo de barras','13:59:06','13:59:06','0070310203607515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(224,'UPDATE',1,'2015-03-21 11:53:59','','Incrementa el sctock de un producto y genera su codigo de barras','11:53:59','11:53:59','0070310191008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(225,'UPDATE',1,'2015-03-21 11:55:18','','Incrementa el sctock de un producto y genera su codigo de barras','11:55:18','11:55:18','0300300199408015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(226,'UPDATE',1,'2015-03-21 11:56:11','','Incrementa el sctock de un producto y genera su codigo de barras','11:56:11','11:56:11','0300180211508015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(227,'UPDATE',1,'2015-03-21 11:59:03','','Incrementa el sctock de un producto y genera su codigo de barras','11:59:03','11:59:03','0310310189308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(228,'CREATE',1,'2015-03-21 12:04:05','','Registra un nuevo color','12:04:05','12:04:05','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(229,'UPDATE',1,'2015-03-21 12:13:45','','Incrementa el sctock de un producto y genera su codigo de barras','12:13:45','12:13:45','0300060202708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(230,'UPDATE',1,'2015-03-21 12:14:26','','Incrementa el sctock de un producto y genera su codigo de barras','12:14:26','12:14:26','0310370205608015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(231,'UPDATE',1,'2015-03-21 12:14:54','','Incrementa el sctock de un producto y genera su codigo de barras','12:14:55','12:14:54','0320470217308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(232,'UPDATE',1,'2015-03-21 12:15:09','','Incrementa el sctock de un producto y genera su codigo de barras','12:15:09','12:15:09','0320470212308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(233,'UPDATE',1,'2015-03-21 12:15:22','','Incrementa el sctock de un producto y genera su codigo de barras','12:15:22','12:15:22','0320470212808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(234,'UPDATE',1,'2015-03-21 12:16:11','','Incrementa el sctock de un producto y genera su codigo de barras','12:16:11','12:16:11','0310350222808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(235,'UPDATE',1,'2015-03-21 12:16:41','','Incrementa el sctock de un producto y genera su codigo de barras','12:16:41','12:16:41','0300300213708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(236,'CREATE',1,'2015-03-21 12:17:05','','Registra un nuevo color','12:17:05','12:17:05','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(237,'UPDATE',1,'2015-03-21 12:17:34','','Incrementa el sctock de un producto y genera su codigo de barras','12:17:34','12:17:34','0310420196008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(238,'UPDATE',1,'2015-03-21 12:17:49','','Incrementa el sctock de un producto y genera su codigo de barras','12:17:49','12:17:49','0320470214408015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(239,'UPDATE',1,'2015-03-21 12:18:32','','Incrementa el sctock de un producto y genera su codigo de barras','12:18:32','12:18:32','0310310190008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(240,'UPDATE',1,'2015-03-21 12:19:15','','Incrementa el sctock de un producto y genera su codigo de barras','12:19:15','12:19:15','0310540199608015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(241,'UPDATE',1,'2015-03-21 12:19:29','','Incrementa el sctock de un producto y genera su codigo de barras','12:19:29','12:19:29','0300180195008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(242,'CREATE',1,'2015-03-21 12:19:37','','Registra un nuevo color','12:19:37','12:19:37','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(243,'UPDATE',1,'2015-03-21 12:19:54','','Incrementa el sctock de un producto y genera su codigo de barras','12:19:54','12:19:54','0310410190308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(244,'UPDATE',1,'2015-03-21 12:20:27','','Incrementa el sctock de un producto y genera su codigo de barras','12:20:27','12:20:27','0320510225008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(245,'UPDATE',1,'2015-03-21 12:22:36','','Incrementa el sctock de un producto y genera su codigo de barras','12:22:36','12:22:36','0300190213708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(246,'UPDATE',1,'2015-03-21 12:23:57','','Incrementa el sctock de un producto y genera su codigo de barras','12:23:57','12:23:57','0070360207008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(247,'UPDATE',1,'2015-03-21 12:24:09','','Incrementa el sctock de un producto y genera su codigo de barras','12:24:09','12:24:09','0060040194608015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(248,'UPDATE',1,'2015-03-21 12:24:50','','Incrementa el sctock de un producto y genera su codigo de barras','12:24:50','12:24:50','0270360207008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(249,'UPDATE',1,'2015-03-21 12:25:06','','Incrementa el sctock de un producto y genera su codigo de barras','12:25:06','12:25:06','0260040194608015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(250,'UPDATE',1,'2015-03-21 12:25:26','','Incrementa el sctock de un producto y genera su codigo de barras','12:25:26','12:25:26','0270310198008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(251,'UPDATE',1,'2015-03-21 12:25:34','','Incrementa el sctock de un producto y genera su codigo de barras','12:25:34','12:25:34','0260190212808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(252,'UPDATE',1,'2015-03-21 12:25:46','','Incrementa el sctock de un producto y genera su codigo de barras','12:25:46','12:25:46','0260150187708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(253,'UPDATE',1,'2015-03-21 12:25:58','','Incrementa el sctock de un producto y genera su codigo de barras','12:25:58','12:25:58','0270350206208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(254,'UPDATE',1,'2015-03-21 12:26:28','','Incrementa el sctock de un producto y genera su codigo de barras','12:26:28','12:26:28','0270310196408015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(255,'UPDATE',1,'2015-03-21 12:27:11','','Incrementa el sctock de un producto y genera su codigo de barras','12:27:11','12:27:11','0270360207808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(256,'UPDATE',1,'2015-03-21 12:27:57','','Incrementa el sctock de un producto y genera su codigo de barras','12:27:57','12:27:57','0250010193208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(257,'UPDATE',1,'2015-03-21 12:28:12','','Incrementa el sctock de un producto y genera su codigo de barras','12:28:12','12:28:12','0260190188208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(258,'CREATE',1,'2015-03-21 12:28:58','','Registra un nuevo color','12:28:58','12:28:58','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(259,'UPDATE',1,'2015-03-21 12:29:32','','Incrementa el sctock de un producto y genera su codigo de barras','12:29:32','12:29:32','0270380203408015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(260,'UPDATE',1,'2015-03-21 12:31:49','','Incrementa el sctock de un producto y genera su codigo de barras','12:31:49','12:31:49','0310360264208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(261,'UPDATE',1,'2015-03-21 12:32:24','','Incrementa el sctock de un producto y genera su codigo de barras','12:32:24','12:32:24','0300140196908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(262,'UPDATE',1,'2015-03-21 12:32:30','','Incrementa el sctock de un producto y genera su codigo de barras','12:32:30','12:32:30','0300140233008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(263,'UPDATE',1,'2015-03-21 12:33:00','','Incrementa el sctock de un producto y genera su codigo de barras','12:33:00','12:33:00','0310540202908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(264,'UPDATE',1,'2015-03-21 12:33:54','','Incrementa el sctock de un producto y genera su codigo de barras','12:33:54','12:33:54','0310360236108015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(265,'UPDATE',1,'2015-03-21 12:34:20','','Incrementa el sctock de un producto y genera su codigo de barras','12:34:20','12:34:20','0310360210808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(266,'UPDATE',1,'2015-03-21 12:34:43','','Incrementa el sctock de un producto y genera su codigo de barras','12:34:43','12:34:43','0310360260608015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(267,'UPDATE',1,'2015-03-21 12:35:45','','Incrementa el sctock de un producto y genera su codigo de barras','12:35:46','12:35:45','0310360237008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(268,'UPDATE',1,'2015-03-21 12:36:01','','Incrementa el sctock de un producto y genera su codigo de barras','12:36:01','12:36:01','0310360215408015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(269,'UPDATE',1,'2015-03-21 12:36:20','','Incrementa el sctock de un producto y genera su codigo de barras','12:36:20','12:36:20','0310310226308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(270,'UPDATE',1,'2015-03-21 12:36:50','','Incrementa el sctock de un producto y genera su codigo de barras','12:36:50','12:36:50','0310360231208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(271,'UPDATE',1,'2015-03-21 12:37:32','','Incrementa el sctock de un producto y genera su codigo de barras','12:37:32','12:37:32','0260030195708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(272,'UPDATE',1,'2015-03-21 12:41:15','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:15','12:41:15','0270540191108015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(273,'UPDATE',1,'2015-03-21 12:41:43','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:43','12:41:43','0270410152808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(274,'UPDATE',1,'2015-03-21 12:42:42','','Incrementa el sctock de un producto y genera su codigo de barras','12:42:42','12:42:42','0060040190908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(275,'UPDATE',1,'2015-03-21 12:43:20','','Incrementa el sctock de un producto y genera su codigo de barras','12:43:20','12:43:20','0070540198908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(276,'UPDATE',1,'2015-03-21 12:44:15','','Incrementa el sctock de un producto y genera su codigo de barras','12:44:15','12:44:15','0320510203508015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(277,'UPDATE',1,'2015-03-21 12:45:23','','Incrementa el sctock de un producto y genera su codigo de barras','12:45:23','12:45:23','0070370210708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(278,'UPDATE',1,'2015-03-21 12:45:38','','Incrementa el sctock de un producto y genera su codigo de barras','12:45:38','12:45:38','0070370194308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(279,'UPDATE',1,'2015-03-21 12:46:22','','Incrementa el sctock de un producto y genera su codigo de barras','12:46:22','12:46:22','0060060166708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(280,'UPDATE',1,'2015-03-21 12:46:52','','Incrementa el sctock de un producto y genera su codigo de barras','12:46:52','12:46:52','0070540180008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(281,'UPDATE',1,'2015-03-21 12:47:06','','Incrementa el sctock de un producto y genera su codigo de barras','12:47:06','12:47:06','0070540191208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(282,'UPDATE',1,'2015-03-21 12:47:32','','Incrementa el sctock de un producto y genera su codigo de barras','12:47:32','12:47:32','0060180182608015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(283,'UPDATE',1,'2015-03-21 12:48:06','','Incrementa el sctock de un producto y genera su codigo de barras','12:48:06','12:48:06','0050010191708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(284,'UPDATE',1,'2015-03-21 12:52:52','','Incrementa el sctock de un producto y genera su codigo de barras','12:52:52','12:52:52','0310350218208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(285,'UPDATE',1,'2015-03-21 12:53:51','','Incrementa el sctock de un producto y genera su codigo de barras','12:53:51','12:53:51','0060180216308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(286,'UPDATE',1,'2015-03-21 12:54:14','','Incrementa el sctock de un producto y genera su codigo de barras','12:54:14','12:54:14','0070410196208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(287,'UPDATE',1,'2015-03-21 12:54:35','','Incrementa el sctock de un producto y genera su codigo de barras','12:54:35','12:54:35','0050010212308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(288,'UPDATE',1,'2015-03-21 12:55:24','','Incrementa el sctock de un producto y genera su codigo de barras','12:55:24','12:55:24','0190360198808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(289,'UPDATE',1,'2015-03-21 12:55:42','','Incrementa el sctock de un producto y genera su codigo de barras','12:55:42','12:55:42','0210010196808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(290,'UPDATE',1,'2015-03-21 13:10:58','','Incrementa el sctock de un producto y genera su codigo de barras','13:10:58','13:10:58','0310350205908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(291,'UPDATE',1,'2015-03-21 13:11:10','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:10','13:11:10','0310400202708015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(292,'UPDATE',1,'2015-03-21 13:11:22','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:22','13:11:22','0300140191208015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(293,'UPDATE',1,'2015-03-21 13:13:35','','Incrementa el sctock de un producto y genera su codigo de barras','13:13:35','13:13:35','0310350207508015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(294,'UPDATE',1,'2015-03-21 13:13:46','','Incrementa el sctock de un producto y genera su codigo de barras','13:13:46','13:13:46','0310410203908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(295,'UPDATE',1,'2015-03-21 13:13:58','','Incrementa el sctock de un producto y genera su codigo de barras','13:13:58','13:13:58','0300030208008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(296,'UPDATE',1,'2015-03-21 13:14:13','','Incrementa el sctock de un producto y genera su codigo de barras','13:14:13','13:14:13','0310360240008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(297,'UPDATE',1,'2015-03-21 13:18:18','','Incrementa el sctock de un producto y genera su codigo de barras','13:18:18','13:18:18','0310360218808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(298,'UPDATE',1,'2015-03-21 13:18:30','','Incrementa el sctock de un producto y genera su codigo de barras','13:18:30','13:18:30','0300020188008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(299,'UPDATE',1,'2015-03-21 13:18:43','','Incrementa el sctock de un producto y genera su codigo de barras','13:18:43','13:18:43','0310350210008015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(300,'UPDATE',1,'2015-03-21 13:23:50','','Incrementa el sctock de un producto y genera su codigo de barras','13:23:50','13:23:50','0300030201108015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(301,'UPDATE',1,'2015-03-21 13:24:03','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:03','13:24:03','0310360195308015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(302,'UPDATE',1,'2015-03-21 13:24:14','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:14','13:24:14','0310310207108015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(303,'UPDATE',1,'2015-03-21 13:24:35','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:35','13:24:35','0310400211908015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(304,'CREATE',1,'2015-03-21 13:24:42','','Registra un nuevo color','13:24:42','13:24:42','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(305,'UPDATE',1,'2015-03-21 13:25:28','','Incrementa el sctock de un producto y genera su codigo de barras','13:25:28','13:25:28','0310460231808015','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(306,'UPDATE',1,'2015-03-26 13:43:00','','Incrementa el sctock de un producto y genera su codigo de barras','13:43:00','13:43:00','0310360244708515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(307,'UPDATE',1,'2015-03-26 13:43:16','','Incrementa el sctock de un producto y genera su codigo de barras','13:43:16','13:43:16','0300020193508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(308,'UPDATE',1,'2015-03-26 13:45:26','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:26','13:45:26','0310360235108515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(309,'UPDATE',1,'2015-03-26 13:45:37','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:37','13:45:37','0320470217008515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(310,'UPDATE',1,'2015-03-26 14:00:34','','Incrementa el sctock de un producto y genera su codigo de barras','14:00:34','14:00:34','0310460209808515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(311,'UPDATE',1,'2015-03-26 14:00:47','','Incrementa el sctock de un producto y genera su codigo de barras','14:00:47','14:00:47','0310460168208515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(312,'UPDATE',1,'2015-03-26 14:00:56','','Incrementa el sctock de un producto y genera su codigo de barras','14:00:56','14:00:56','0310460188908515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(313,'UPDATE',1,'2015-03-26 14:01:03','','Incrementa el sctock de un producto y genera su codigo de barras','14:01:03','14:01:03','0310460206208515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(314,'UPDATE',1,'2015-03-26 14:04:27','','Incrementa el sctock de un producto y genera su codigo de barras','14:04:27','14:04:27','0220170183508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(315,'UPDATE',1,'2015-03-26 14:05:33','','Incrementa el sctock de un producto y genera su codigo de barras','14:05:33','14:05:33','0270380206208515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(316,'UPDATE',1,'2015-03-26 14:14:45','','Incrementa el sctock de un producto y genera su codigo de barras','14:14:45','14:14:45','0210010193608515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(317,'UPDATE',1,'2015-03-26 14:15:02','','Incrementa el sctock de un producto y genera su codigo de barras','14:15:02','14:15:02','0230350163508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(318,'UPDATE',1,'2015-03-26 14:15:20','','Incrementa el sctock de un producto y genera su codigo de barras','14:15:20','14:15:20','0230310210308515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(319,'UPDATE',1,'2015-03-26 14:15:47','','Incrementa el sctock de un producto y genera su codigo de barras','14:15:47','14:15:47','0230540220308515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(320,'UPDATE',1,'2015-03-26 14:15:58','','Incrementa el sctock de un producto y genera su codigo de barras','14:15:58','14:15:58','0220020199508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(321,'UPDATE',1,'2015-03-26 14:23:24','','Incrementa el sctock de un producto y genera su codigo de barras','14:23:24','14:23:24','0220190190208515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(322,'UPDATE',1,'2015-03-26 14:23:37','','Incrementa el sctock de un producto y genera su codigo de barras','14:23:37','14:23:37','0230310193708515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(323,'UPDATE',1,'2015-03-26 14:23:48','','Incrementa el sctock de un producto y genera su codigo de barras','14:23:48','14:23:48','0220110193908515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(324,'UPDATE',1,'2015-03-26 14:24:00','','Incrementa el sctock de un producto y genera su codigo de barras','14:24:00','14:24:00','0230540228408515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(325,'UPDATE',1,'2015-03-26 14:24:13','','Incrementa el sctock de un producto y genera su codigo de barras','14:24:14','14:24:13','0240490190508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(326,'UPDATE',1,'2015-03-26 14:30:20','','Incrementa el sctock de un producto y genera su codigo de barras','14:30:20','14:30:20','0220290195508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(327,'UPDATE',1,'2015-03-26 14:30:36','','Incrementa el sctock de un producto y genera su codigo de barras','14:30:36','14:30:36','0220050194408515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(328,'UPDATE',1,'2015-03-26 14:30:51','','Incrementa el sctock de un producto y genera su codigo de barras','14:30:51','14:30:51','0220050193808515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(329,'UPDATE',1,'2015-03-26 14:38:24','','Incrementa el sctock de un producto y genera su codigo de barras','14:38:24','14:38:24','0230400192808515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(330,'UPDATE',1,'2015-03-26 14:38:46','','Incrementa el sctock de un producto y genera su codigo de barras','14:38:46','14:38:46','0230310198008515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(331,'UPDATE',1,'2015-03-26 14:38:58','','Incrementa el sctock de un producto y genera su codigo de barras','14:38:58','14:38:58','0230540204508515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(332,'UPDATE',1,'2015-03-26 14:39:16','','Incrementa el sctock de un producto y genera su codigo de barras','14:39:16','14:39:16','0230540203708515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(333,'UPDATE',1,'2015-03-26 14:42:22','','Incrementa el sctock de un producto y genera su codigo de barras','14:42:22','14:42:22','0230540201308515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(334,'UPDATE',1,'2015-03-26 14:42:37','','Incrementa el sctock de un producto y genera su codigo de barras','14:42:37','14:42:37','0210010196108515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(335,'UPDATE',1,'2015-03-26 14:46:31','','Incrementa el sctock de un producto y genera su codigo de barras','14:46:31','14:46:31','0180050193008515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(336,'UPDATE',1,'2015-03-26 14:46:40','','Incrementa el sctock de un producto y genera su codigo de barras','14:46:40','14:46:40','0180050192908515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(337,'UPDATE',1,'2015-03-26 14:48:13','','Incrementa el sctock de un producto y genera su codigo de barras','14:48:13','14:48:13','0260110220708515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(338,'UPDATE',1,'2015-03-27 13:35:35','','Incrementa el sctock de un producto y genera su codigo de barras','13:35:35','13:35:35','0220110201108615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(339,'UPDATE',1,'2015-03-27 13:36:04','','Incrementa el sctock de un producto y genera su codigo de barras','13:36:04','13:36:04','0210010202108615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(340,'UPDATE',1,'2015-03-27 13:36:19','','Incrementa el sctock de un producto y genera su codigo de barras','13:36:19','13:36:19','0230540196608615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(341,'UPDATE',1,'2015-03-27 13:38:56','','Incrementa el sctock de un producto y genera su codigo de barras','13:38:56','13:38:56','0220290184108615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(342,'UPDATE',1,'2015-03-27 13:39:10','','Incrementa el sctock de un producto y genera su codigo de barras','13:39:10','13:39:10','0230310227808615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(343,'UPDATE',1,'2015-03-27 13:39:20','','Incrementa el sctock de un producto y genera su codigo de barras','13:39:20','13:39:20','0220060187908615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(344,'UPDATE',1,'2015-03-27 13:42:20','','Incrementa el sctock de un producto y genera su codigo de barras','13:42:20','13:42:20','0220050201108615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(345,'UPDATE',1,'2015-03-27 13:42:33','','Incrementa el sctock de un producto y genera su codigo de barras','13:42:33','13:42:33','0220110201308615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(346,'UPDATE',1,'2015-03-27 13:45:35','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:35','13:45:35','0220110201408615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(347,'UPDATE',1,'2015-03-27 13:45:46','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:46','13:45:46','0230310182608615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(348,'UPDATE',1,'2015-03-27 13:45:58','','Incrementa el sctock de un producto y genera su codigo de barras','13:45:58','13:45:58','0210010121508615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(349,'UPDATE',1,'2015-03-27 13:48:28','','Incrementa el sctock de un producto y genera su codigo de barras','13:48:28','13:48:28','0230310202608615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(350,'UPDATE',1,'2015-03-27 13:55:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:55:24','13:55:24','0220050203408615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(351,'UPDATE',1,'2015-03-27 13:55:33','','Incrementa el sctock de un producto y genera su codigo de barras','13:55:33','13:55:33','0230310198808615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(352,'UPDATE',1,'2015-03-27 13:55:41','','Incrementa el sctock de un producto y genera su codigo de barras','13:55:41','13:55:41','0210010202908615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(353,'UPDATE',1,'2015-03-27 14:31:25','','Incrementa el sctock de un producto y genera su codigo de barras','14:31:25','14:31:25','0220190190508615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(354,'UPDATE',1,'2015-03-27 14:31:43','','Incrementa el sctock de un producto y genera su codigo de barras','14:31:43','14:31:43','0220110200908615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(355,'UPDATE',1,'2015-03-27 14:31:55','','Incrementa el sctock de un producto y genera su codigo de barras','14:31:55','14:31:55','0220040205708615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(356,'UPDATE',1,'2015-03-27 14:34:34','','Incrementa el sctock de un producto y genera su codigo de barras','14:34:34','14:34:34','0220040210208615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(357,'UPDATE',1,'2015-03-27 14:36:19','','Incrementa el sctock de un producto y genera su codigo de barras','14:36:19','14:36:19','0070310199808615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(358,'UPDATE',1,'2015-03-28 11:47:42','','Incrementa el sctock de un producto y genera su codigo de barras','11:47:42','11:47:42','0140020208208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(359,'UPDATE',1,'2015-03-28 11:48:05','','Incrementa el sctock de un producto y genera su codigo de barras','11:48:05','11:48:05','0140020208208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(360,'UPDATE',1,'2015-03-28 11:48:19','','Incrementa el sctock de un producto y genera su codigo de barras','11:48:19','11:48:19','0140020207608715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(361,'UPDATE',1,'2015-03-28 11:55:15','','Incrementa el sctock de un producto y genera su codigo de barras','11:55:15','11:55:15','0140060215808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(362,'UPDATE',1,'2015-03-28 11:56:46','','Incrementa el sctock de un producto y genera su codigo de barras','11:56:46','11:56:46','0140110218208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(363,'UPDATE',1,'2015-03-28 11:57:58','','Incrementa el sctock de un producto y genera su codigo de barras','11:57:58','11:57:58','0140050211808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(364,'UPDATE',1,'2015-03-28 11:59:34','','Incrementa el sctock de un producto y genera su codigo de barras','11:59:34','11:59:34','0140050212308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(365,'UPDATE',1,'2015-03-28 11:59:55','','Incrementa el sctock de un producto y genera su codigo de barras','11:59:55','11:59:55','0140050211408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(366,'UPDATE',1,'2015-03-28 12:00:21','','Incrementa el sctock de un producto y genera su codigo de barras','12:00:21','12:00:21','0140050215108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(367,'UPDATE',1,'2015-03-28 12:02:59','','Incrementa el sctock de un producto y genera su codigo de barras','12:02:59','12:02:59','0140050238708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(368,'CREATE',1,'2015-03-28 12:04:29','','Registra un nuevo color','12:04:29','12:04:29','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(369,'UPDATE',1,'2015-03-28 12:04:57','','Incrementa el sctock de un producto y genera su codigo de barras','12:04:57','12:04:57','0150430210308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(370,'UPDATE',1,'2015-03-28 12:05:02','','Incrementa el sctock de un producto y genera su codigo de barras','12:05:02','12:05:02','0150430210308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(371,'UPDATE',1,'2015-03-28 12:05:20','','Incrementa el sctock de un producto y genera su codigo de barras','12:05:20','12:05:20','0150430213408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(372,'UPDATE',1,'2015-03-28 12:05:55','','Incrementa el sctock de un producto y genera su codigo de barras','12:05:55','12:05:55','0150430209708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(373,'UPDATE',1,'2015-03-28 12:08:16','','Incrementa el sctock de un producto y genera su codigo de barras','12:08:16','12:08:16','0140200219508715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(374,'UPDATE',1,'2015-03-28 12:08:26','','Incrementa el sctock de un producto y genera su codigo de barras','12:08:26','12:08:26','0140200216008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(375,'UPDATE',1,'2015-03-28 12:09:16','','Incrementa el sctock de un producto y genera su codigo de barras','12:09:16','12:09:16','0150540210908715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(376,'UPDATE',1,'2015-03-28 12:09:34','','Incrementa el sctock de un producto y genera su codigo de barras','12:09:34','12:09:34','0150540217108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(377,'UPDATE',1,'2015-03-28 12:11:14','','Incrementa el sctock de un producto y genera su codigo de barras','12:11:14','12:11:14','0140300209108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(378,'UPDATE',1,'2015-03-28 12:12:04','','Incrementa el sctock de un producto y genera su codigo de barras','12:12:04','12:12:04','0140300201808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(379,'UPDATE',1,'2015-03-28 12:13:02','','Incrementa el sctock de un producto y genera su codigo de barras','12:13:02','12:13:02','0140030210808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(380,'UPDATE',1,'2015-03-28 12:13:11','','Incrementa el sctock de un producto y genera su codigo de barras','12:13:11','12:13:11','0140030205108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(381,'UPDATE',1,'2015-03-28 12:13:49','','Incrementa el sctock de un producto y genera su codigo de barras','12:13:49','12:13:49','0140030205008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(382,'UPDATE',1,'2015-03-28 12:13:56','','Incrementa el sctock de un producto y genera su codigo de barras','12:13:56','12:13:56','0140030209908715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(383,'UPDATE',1,'2015-03-28 12:14:29','','Incrementa el sctock de un producto y genera su codigo de barras','12:14:29','12:14:29','0140060214808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(384,'UPDATE',1,'2015-03-28 12:15:13','','Incrementa el sctock de un producto y genera su codigo de barras','12:15:13','12:15:13','0140060218208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(385,'UPDATE',1,'2015-03-28 12:15:35','','Incrementa el sctock de un producto y genera su codigo de barras','12:15:35','12:15:35','0150330212608715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(386,'UPDATE',1,'2015-03-28 12:17:52','','Incrementa el sctock de un producto y genera su codigo de barras','12:17:52','12:17:52','0150310210108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(387,'UPDATE',1,'2015-03-28 12:17:58','','Incrementa el sctock de un producto y genera su codigo de barras','12:17:58','12:17:58','0150310214108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(388,'UPDATE',1,'2015-03-28 12:19:04','','Incrementa el sctock de un producto y genera su codigo de barras','12:19:04','12:19:04','0380040208308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(389,'CREATE',1,'2015-03-28 12:19:27','','Registra un nuevo color','12:19:27','12:19:27','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(390,'UPDATE',1,'2015-03-28 12:20:10','','Incrementa el sctock de un producto y genera su codigo de barras','12:20:10','12:20:10','0420041138208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(391,'UPDATE',1,'2015-03-28 12:20:54','','Incrementa el sctock de un producto y genera su codigo de barras','12:20:54','12:20:54','0150430218108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(392,'UPDATE',1,'2015-03-28 12:21:11','','Incrementa el sctock de un producto y genera su codigo de barras','12:21:11','12:21:11','0150350215008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(393,'UPDATE',1,'2015-03-28 12:22:53','','Incrementa el sctock de un producto y genera su codigo de barras','12:22:53','12:22:53','0390361208208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(394,'UPDATE',1,'2015-03-28 12:23:24','','Incrementa el sctock de un producto y genera su codigo de barras','12:23:24','12:23:24','0390351224908715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(395,'UPDATE',1,'2015-03-28 12:26:35','','Incrementa el sctock de un producto y genera su codigo de barras','12:26:35','12:26:35','0390351211808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(396,'UPDATE',1,'2015-03-28 12:41:07','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:07','12:41:07','0420060206108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(397,'UPDATE',1,'2015-03-28 12:41:53','','Incrementa el sctock de un producto y genera su codigo de barras','12:41:53','12:41:53','0390331204708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(398,'UPDATE',1,'2015-03-28 12:42:43','','Incrementa el sctock de un producto y genera su codigo de barras','12:42:43','12:42:43','0390541210408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(399,'CREATE',1,'2015-03-28 12:52:27','','Registra un nuevo color','12:52:27','12:52:27','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(400,'CREATE',1,'2015-03-28 12:53:11','','Registra un nuevo color','12:53:11','12:53:11','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(401,'UPDATE',1,'2015-03-28 12:58:09','','Incrementa el sctock de un producto y genera su codigo de barras','12:58:09','12:58:09','0310360211108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(402,'UPDATE',1,'2015-03-28 12:58:21','','Incrementa el sctock de un producto y genera su codigo de barras','12:58:21','12:58:21','0310360218908715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(403,'CREATE',1,'2015-03-28 12:58:50','','Registra un nuevo color','12:58:50','12:58:50','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(404,'UPDATE',1,'2015-03-28 12:59:29','','Incrementa el sctock de un producto y genera su codigo de barras','12:59:29','12:59:29','0310360214408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(405,'UPDATE',1,'2015-03-28 12:59:48','','Incrementa el sctock de un producto y genera su codigo de barras','12:59:48','12:59:48','0310360253608715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(406,'UPDATE',1,'2015-03-28 13:00:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:00:24','13:00:24','0310360208708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(407,'UPDATE',1,'2015-03-28 13:00:34','','Incrementa el sctock de un producto y genera su codigo de barras','13:00:34','13:00:34','0310360229908715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(408,'UPDATE',1,'2015-03-28 13:01:21','','Incrementa el sctock de un producto y genera su codigo de barras','13:01:21','13:01:21','0310360205208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(409,'UPDATE',1,'2015-03-28 13:02:59','','Incrementa el sctock de un producto y genera su codigo de barras','13:02:59','13:02:59','0310360258708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(410,'UPDATE',1,'2015-03-28 13:03:57','','Incrementa el sctock de un producto y genera su codigo de barras','13:03:57','13:03:57','0300040196408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(411,'UPDATE',1,'2015-03-28 13:04:16','','Incrementa el sctock de un producto y genera su codigo de barras','13:04:16','13:04:16','0300180210508715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(412,'UPDATE',1,'2015-03-28 13:06:33','','Incrementa el sctock de un producto y genera su codigo de barras','13:06:33','13:06:33','0310350208108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(413,'UPDATE',1,'2015-03-28 13:06:40','','Incrementa el sctock de un producto y genera su codigo de barras','13:06:40','13:06:40','0310350198008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(414,'UPDATE',1,'2015-03-28 13:06:47','','Incrementa el sctock de un producto y genera su codigo de barras','13:06:47','13:06:47','0310350199508715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(415,'UPDATE',1,'2015-03-28 13:08:10','','Incrementa el sctock de un producto y genera su codigo de barras','13:08:10','13:08:10','0310400222608715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(416,'UPDATE',1,'2015-03-28 13:09:05','','Incrementa el sctock de un producto y genera su codigo de barras','13:09:05','13:09:05','0310350191708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(417,'UPDATE',1,'2015-03-28 13:09:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:09:24','13:09:24','0310350191308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(418,'CREATE',1,'2015-03-28 13:09:49','','Registra un nuevo color','13:09:49','13:09:49','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(419,'UPDATE',1,'2015-03-28 13:11:13','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:13','13:11:13','0310410208308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(420,'UPDATE',1,'2015-03-28 13:11:39','','Incrementa el sctock de un producto y genera su codigo de barras','13:11:39','13:11:39','0310460208008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(421,'UPDATE',1,'2015-03-28 13:12:19','','Incrementa el sctock de un producto y genera su codigo de barras','13:12:19','13:12:19','0310410204708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(422,'CREATE',1,'2015-03-28 13:12:31','','Registra un nuevo color','13:12:31','13:12:31','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(423,'UPDATE',1,'2015-03-28 13:13:06','','Incrementa el sctock de un producto y genera su codigo de barras','13:13:06','13:13:06','0310390165308715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(424,'UPDATE',1,'2015-03-28 13:13:25','','Incrementa el sctock de un producto y genera su codigo de barras','13:13:25','13:13:25','0300140188008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(425,'UPDATE',1,'2015-03-28 13:13:46','','Incrementa el sctock de un producto y genera su codigo de barras','13:13:46','13:13:46','0300140192108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(426,'UPDATE',1,'2015-03-28 13:14:56','','Incrementa el sctock de un producto y genera su codigo de barras','13:14:56','13:14:56','0310460194508715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(427,'UPDATE',1,'2015-03-28 13:15:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:15:24','13:15:24','0310540196608715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(428,'UPDATE',1,'2015-03-28 13:16:02','','Incrementa el sctock de un producto y genera su codigo de barras','13:16:02','13:16:02','0310350193008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(429,'UPDATE',1,'2015-03-28 13:16:31','','Incrementa el sctock de un producto y genera su codigo de barras','13:16:31','13:16:31','0310540196008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(430,'UPDATE',1,'2015-03-28 13:17:01','','Incrementa el sctock de un producto y genera su codigo de barras','13:17:01','13:17:01','0320510184608715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(431,'UPDATE',1,'2015-03-28 13:17:29','','Incrementa el sctock de un producto y genera su codigo de barras','13:17:29','13:17:29','0310380153408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(432,'UPDATE',1,'2015-03-28 13:18:17','','Incrementa el sctock de un producto y genera su codigo de barras','13:18:17','13:18:17','0300180195008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(433,'UPDATE',1,'2015-03-28 13:22:24','','Incrementa el sctock de un producto y genera su codigo de barras','13:22:24','13:22:24','0320510179108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(434,'UPDATE',1,'2015-03-28 13:22:40','','Incrementa el sctock de un producto y genera su codigo de barras','13:22:40','13:22:40','0310350199808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(435,'UPDATE',1,'2015-03-28 13:22:50','','Incrementa el sctock de un producto y genera su codigo de barras','13:22:50','13:22:50','0310350195708715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(436,'UPDATE',1,'2015-03-28 13:23:16','','Incrementa el sctock de un producto y genera su codigo de barras','13:23:16','13:23:16','0310380216108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(437,'UPDATE',1,'2015-03-28 13:24:22','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:22','13:24:22','0310400220008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(438,'UPDATE',1,'2015-03-28 13:24:53','','Incrementa el sctock de un producto y genera su codigo de barras','13:24:53','13:24:53','0300180203008715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(439,'UPDATE',1,'2015-03-28 13:25:34','','Incrementa el sctock de un producto y genera su codigo de barras','13:25:34','13:25:34','0320470216108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(440,'UPDATE',1,'2015-03-28 13:25:59','','Incrementa el sctock de un producto y genera su codigo de barras','13:25:59','13:25:59','0310400195208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(441,'CREATE',1,'2015-03-28 13:27:08','','Registra un nuevo color','13:27:08','13:27:08','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(442,'UPDATE',1,'2015-03-28 13:28:47','','Incrementa el sctock de un producto y genera su codigo de barras','13:28:47','13:28:47','0300120217408715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(443,'UPDATE',1,'2015-03-28 13:37:55','','Incrementa el sctock de un producto y genera su codigo de barras','13:37:55','13:37:55','0280470204808715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(444,'UPDATE',1,'2015-03-28 13:40:22','','Incrementa el sctock de un producto y genera su codigo de barras','13:40:22','13:40:22','0270360218208715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(445,'UPDATE',1,'2015-03-28 13:44:30','','Incrementa el sctock de un producto y genera su codigo de barras','13:44:30','13:44:30','0270360205108715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(446,'UPDATE',1,'2015-03-28 13:51:42','','Incrementa el sctock de un producto y genera su codigo de barras','13:51:42','13:51:42','0270540194508715','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(447,'UPDATE',1,'2015-04-25 10:06:50','','Incrementa el sctock de un producto y genera su codigo de barras','10:06:50','10:06:50','0310350206011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(448,'UPDATE',1,'2015-04-25 10:07:32','','Incrementa el sctock de un producto y genera su codigo de barras','10:07:32','10:07:32','0310350211411515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(449,'UPDATE',1,'2015-04-25 10:08:10','','Incrementa el sctock de un producto y genera su codigo de barras','10:08:10','10:08:10','0310350210411515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(450,'UPDATE',1,'2015-04-25 10:10:11','','Incrementa el sctock de un producto y genera su codigo de barras','10:10:11','10:10:11','0310350192311515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(451,'UPDATE',1,'2015-04-25 10:10:42','','Incrementa el sctock de un producto y genera su codigo de barras','10:10:42','10:10:42','0310350185011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(452,'UPDATE',1,'2015-04-25 10:11:11','','Incrementa el sctock de un producto y genera su codigo de barras','10:11:11','10:11:11','0310350210511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(453,'UPDATE',1,'2015-04-25 10:13:01','','Incrementa el sctock de un producto y genera su codigo de barras','10:13:01','10:13:01','0310350200511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(454,'UPDATE',1,'2015-04-25 10:13:28','','Incrementa el sctock de un producto y genera su codigo de barras','10:13:28','10:13:28','0310350210211515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(455,'UPDATE',1,'2015-04-25 10:13:56','','Incrementa el sctock de un producto y genera su codigo de barras','10:13:56','10:13:56','0310350197111515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(456,'UPDATE',1,'2015-04-25 10:16:44','','Incrementa el sctock de un producto y genera su codigo de barras','10:16:44','10:16:44','0310360206411515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(457,'UPDATE',1,'2015-04-25 10:17:12','','Incrementa el sctock de un producto y genera su codigo de barras','10:17:12','10:17:12','0310360209511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(458,'UPDATE',1,'2015-04-25 10:18:29','','Incrementa el sctock de un producto y genera su codigo de barras','10:18:29','10:18:29','0310360200511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(459,'UPDATE',1,'2015-04-25 10:20:03','','Incrementa el sctock de un producto y genera su codigo de barras','10:20:03','10:20:03','0310360261811515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(460,'UPDATE',1,'2015-04-25 10:21:04','','Incrementa el sctock de un producto y genera su codigo de barras','10:21:04','10:21:04','0310360217911515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(461,'UPDATE',1,'2015-04-25 10:21:34','','Incrementa el sctock de un producto y genera su codigo de barras','10:21:34','10:21:34','0310360218911515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(462,'UPDATE',1,'2015-04-25 10:23:27','','Incrementa el sctock de un producto y genera su codigo de barras','10:23:27','10:23:27','0310360195311515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(463,'UPDATE',1,'2015-04-25 10:28:26','','Incrementa el sctock de un producto y genera su codigo de barras','10:28:26','10:28:26','0310380187111515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(464,'UPDATE',1,'2015-04-25 10:29:26','','Incrementa el sctock de un producto y genera su codigo de barras','10:29:26','10:29:26','0270380205911515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(465,'UPDATE',1,'2015-04-25 10:32:40','','Incrementa el sctock de un producto y genera su codigo de barras','10:32:40','10:32:40','0260060211711515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(466,'UPDATE',1,'2015-04-25 10:33:07','','Incrementa el sctock de un producto y genera su codigo de barras','10:33:07','10:33:07','0300060211711515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(467,'UPDATE',1,'2015-04-25 10:34:57','','Incrementa el sctock de un producto y genera su codigo de barras','10:34:57','10:34:57','0300060210111515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(468,'UPDATE',1,'2015-04-25 10:39:19','','Incrementa el sctock de un producto y genera su codigo de barras','10:39:19','10:39:19','0300060209911515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(469,'UPDATE',1,'2015-04-25 10:39:58','','Incrementa el sctock de un producto y genera su codigo de barras','10:39:58','10:39:58','0310310190711515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(470,'UPDATE',1,'2015-04-25 10:40:27','','Incrementa el sctock de un producto y genera su codigo de barras','10:40:27','10:40:27','0300040202711515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(471,'UPDATE',1,'2015-04-25 10:40:55','','Incrementa el sctock de un producto y genera su codigo de barras','10:40:55','10:40:55','0310390194511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(472,'UPDATE',1,'2015-04-25 10:44:34','','Incrementa el sctock de un producto y genera su codigo de barras','10:44:34','10:44:34','0310360215011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(473,'UPDATE',1,'2015-04-25 10:47:57','','Incrementa el sctock de un producto y genera su codigo de barras','10:47:57','10:47:57','0270410191011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(474,'UPDATE',1,'2015-04-25 10:48:20','','Incrementa el sctock de un producto y genera su codigo de barras','10:48:20','10:48:20','0270460212011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(475,'UPDATE',1,'2015-04-25 10:48:35','','Incrementa el sctock de un producto y genera su codigo de barras','10:48:35','10:48:35','0300140246011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(476,'UPDATE',1,'2015-04-25 10:50:26','','Incrementa el sctock de un producto y genera su codigo de barras','10:50:26','10:50:26','0310380184311515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(477,'UPDATE',1,'2015-04-25 11:02:37','','Incrementa el sctock de un producto y genera su codigo de barras','11:02:37','11:02:37','0180140193211515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(478,'UPDATE',1,'2015-04-25 11:02:45','','Incrementa el sctock de un producto y genera su codigo de barras','11:02:45','11:02:45','0180140192611515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(479,'UPDATE',1,'2015-04-25 11:04:38','','Incrementa el sctock de un producto y genera su codigo de barras','11:04:38','11:04:38','0190360202311515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(480,'UPDATE',1,'2015-04-25 11:07:45','','Incrementa el sctock de un producto y genera su codigo de barras','11:07:45','11:07:45','0170010200511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(481,'UPDATE',1,'2015-04-25 11:09:32','','Incrementa el sctock de un producto y genera su codigo de barras','11:09:32','11:09:32','0190310196411515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(482,'UPDATE',1,'2015-04-25 11:13:02','','Incrementa el sctock de un producto y genera su codigo de barras','11:13:02','11:13:02','0060020185511515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(483,'UPDATE',1,'2015-04-25 11:13:15','','Incrementa el sctock de un producto y genera su codigo de barras','11:13:15','11:13:15','0060150210311515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(484,'UPDATE',1,'2015-04-25 11:13:34','','Incrementa el sctock de un producto y genera su codigo de barras','11:13:34','11:13:34','0070310173211515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(485,'UPDATE',1,'2015-04-25 11:48:17','','Incrementa el sctock de un producto y genera su codigo de barras','11:48:17','11:48:17','0310540226711515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(486,'UPDATE',1,'2015-04-25 11:48:31','','Incrementa el sctock de un producto y genera su codigo de barras','11:48:31','11:48:31','0310400197711515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(487,'UPDATE',1,'2015-04-25 11:48:43','','Incrementa el sctock de un producto y genera su codigo de barras','11:48:43','11:48:43','0310360193011515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(488,'UPDATE',1,'2015-04-25 11:48:53','','Incrementa el sctock de un producto y genera su codigo de barras','11:48:53','11:48:53','0310460201611515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(489,'UPDATE',1,'2015-04-25 11:49:46','','Incrementa el sctock de un producto y genera su codigo de barras','11:49:46','11:49:46','0310360195111515','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(490,'CREATE',1,'2015-05-09 13:52:33','','Registra un nuevo evento','13:52:33','13:52:33','','SUCCESS','EventService.save(..)   ar.com.adriabe.services.EventService','sergio'),(491,'CREATE',1,'2015-05-09 13:54:56','','Registra un nuevo color','13:54:56','13:54:56','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(492,'CREATE',1,'2015-05-09 13:55:15','','Registra un nuevo color','13:55:15','13:55:15','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(493,'CREATE',1,'2015-05-09 13:55:24','','Registra un nuevo color','13:55:24','13:55:24','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(494,'CREATE',1,'2015-05-09 13:55:33','','Registra un nuevo color','13:55:33','13:55:33','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(495,'CREATE',1,'2015-05-09 13:56:22','','Registra un nuevo color','13:56:22','13:56:22','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(496,'CREATE',1,'2015-05-09 13:56:51','','Registra un nuevo color','13:56:51','13:56:51','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(497,'CREATE',1,'2015-05-09 13:57:06','','Registra un nuevo color','13:57:06','13:57:06','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(498,'CREATE',1,'2015-05-09 13:57:19','','Registra un nuevo color','13:57:19','13:57:19','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(499,'CREATE',1,'2015-05-09 13:57:32','','Registra un nuevo color','13:57:32','13:57:32','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(500,'CREATE',1,'2015-05-09 13:57:45','','Registra un nuevo color','13:57:45','13:57:45','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(501,'CREATE',1,'2015-05-09 13:57:57','','Registra un nuevo color','13:57:57','13:57:57','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(502,'CREATE',1,'2015-05-09 13:58:11','','Registra un nuevo color','13:58:11','13:58:11','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(503,'CREATE',1,'2015-05-09 13:58:26','','Registra un nuevo color','13:58:26','13:58:26','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(504,'CREATE',1,'2015-05-09 13:58:37','','Registra un nuevo color','13:58:37','13:58:37','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(505,'CREATE',1,'2015-05-09 13:58:54','','Registra un nuevo color','13:58:54','13:58:54','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(506,'CREATE',1,'2015-05-09 13:59:09','','Registra un nuevo color','13:59:09','13:59:09','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(507,'CREATE',1,'2015-05-09 13:59:19','','Registra un nuevo color','13:59:19','13:59:19','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(508,'CREATE',1,'2015-05-09 13:59:27','','Registra un nuevo color','13:59:27','13:59:27','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(509,'CREATE',1,'2015-05-09 13:59:59','','Registra un nuevo color','13:59:59','13:59:59','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(510,'CREATE',1,'2015-05-09 14:38:35','','Registra un nuevo evento','14:38:36','14:38:35','','SUCCESS','EventService.save(..)   ar.com.adriabe.services.EventService','sergio'),(511,'UPDATE',1,'2015-05-29 16:35:28','','Incrementa el sctock de un producto y genera su codigo de barras','16:35:28','16:35:28','0310360228414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(512,'UPDATE',1,'2015-05-29 16:35:41','','Incrementa el sctock de un producto y genera su codigo de barras','16:35:41','16:35:41','0310360203214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(513,'UPDATE',1,'2015-05-29 16:35:48','','Incrementa el sctock de un producto y genera su codigo de barras','16:35:48','16:35:48','0310360202814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(514,'UPDATE',1,'2015-05-29 16:35:54','','Incrementa el sctock de un producto y genera su codigo de barras','16:35:54','16:35:54','0310360201214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(515,'UPDATE',1,'2015-05-29 16:35:59','','Incrementa el sctock de un producto y genera su codigo de barras','16:35:59','16:35:59','0310360205914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(516,'UPDATE',1,'2015-05-29 16:36:06','','Incrementa el sctock de un producto y genera su codigo de barras','16:36:06','16:36:06','0310360218714915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(517,'UPDATE',1,'2015-05-29 16:39:23','','Incrementa el sctock de un producto y genera su codigo de barras','16:39:23','16:39:23','0260060201814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(518,'UPDATE',1,'2015-05-29 16:39:54','','Incrementa el sctock de un producto y genera su codigo de barras','16:39:54','16:39:54','0280470202514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(519,'UPDATE',1,'2015-05-29 16:42:47','','Incrementa el sctock de un producto y genera su codigo de barras','16:42:47','16:42:47','0310460210114915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(520,'UPDATE',1,'2015-05-29 16:43:04','','Incrementa el sctock de un producto y genera su codigo de barras','16:43:04','16:43:04','0310420100814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(521,'UPDATE',1,'2015-05-29 16:43:23','','Incrementa el sctock de un producto y genera su codigo de barras','16:43:23','16:43:23','0310310227214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(522,'UPDATE',1,'2015-05-29 16:43:58','','Incrementa el sctock de un producto y genera su codigo de barras','16:43:58','16:43:58','0300030202614915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(523,'UPDATE',1,'2015-05-29 16:44:19','','Incrementa el sctock de un producto y genera su codigo de barras','16:44:19','16:44:19','0310410228914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(524,'UPDATE',1,'2015-05-29 16:46:47','','Incrementa el sctock de un producto y genera su codigo de barras','16:46:47','16:46:47','0300180192614915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(525,'UPDATE',1,'2015-05-29 16:46:54','','Incrementa el sctock de un producto y genera su codigo de barras','16:46:54','16:46:54','0300180200014915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(526,'UPDATE',1,'2015-05-29 16:47:13','','Incrementa el sctock de un producto y genera su codigo de barras','16:47:13','16:47:13','0300180194314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(527,'UPDATE',1,'2015-05-29 16:47:39','','Incrementa el sctock de un producto y genera su codigo de barras','16:47:39','16:47:39','0300180190514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(528,'UPDATE',1,'2015-05-29 16:49:24','','Incrementa el sctock de un producto y genera su codigo de barras','16:49:24','16:49:24','0310350202514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(529,'UPDATE',1,'2015-05-29 16:49:40','','Incrementa el sctock de un producto y genera su codigo de barras','16:49:40','16:49:40','0310350198714915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(530,'UPDATE',1,'2015-05-29 16:49:47','','Incrementa el sctock de un producto y genera su codigo de barras','16:49:47','16:49:47','0310350190914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(531,'UPDATE',1,'2015-05-29 16:50:08','','Incrementa el sctock de un producto y genera su codigo de barras','16:50:08','16:50:08','0310350208514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(532,'UPDATE',1,'2015-05-29 16:53:12','','Incrementa el sctock de un producto y genera su codigo de barras','16:53:12','16:53:12','0310360207914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(533,'UPDATE',1,'2015-05-29 16:53:16','','Incrementa el sctock de un producto y genera su codigo de barras','16:53:16','16:53:16','0310360202014915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(534,'UPDATE',1,'2015-05-29 16:53:20','','Incrementa el sctock de un producto y genera su codigo de barras','16:53:20','16:53:20','0310360203814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(535,'UPDATE',1,'2015-05-29 16:53:24','','Incrementa el sctock de un producto y genera su codigo de barras','16:53:24','16:53:24','0310360206314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(536,'UPDATE',1,'2015-05-29 16:53:30','','Incrementa el sctock de un producto y genera su codigo de barras','16:53:30','16:53:30','0310360222414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(537,'UPDATE',1,'2015-05-29 16:53:36','','Incrementa el sctock de un producto y genera su codigo de barras','16:53:36','16:53:36','0310360203214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(538,'UPDATE',1,'2015-05-29 16:57:31','','Incrementa el sctock de un producto y genera su codigo de barras','16:57:31','16:57:31','0310310202114915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(539,'UPDATE',1,'2015-05-29 16:58:27','','Incrementa el sctock de un producto y genera su codigo de barras','16:58:27','16:58:27','0320470213614915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(540,'UPDATE',1,'2015-05-29 16:58:58','','Incrementa el sctock de un producto y genera su codigo de barras','16:58:58','16:58:58','0300140214514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(541,'UPDATE',1,'2015-05-29 16:59:04','','Incrementa el sctock de un producto y genera su codigo de barras','16:59:04','16:59:04','0300140212214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(542,'UPDATE',0,'2015-05-29 17:00:48','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:00:48','17:00:48','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(543,'UPDATE',0,'2015-05-29 17:00:58','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:00:58','17:00:58','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(544,'UPDATE',0,'2015-05-29 17:01:10','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:01:10','17:01:10','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(545,'UPDATE',0,'2015-05-29 17:01:25','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:01:25','17:01:25','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(546,'UPDATE',0,'2015-05-29 17:01:26','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:01:26','17:01:26','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(547,'UPDATE',0,'2015-05-29 17:01:49','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:01:49','17:01:49','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(548,'UPDATE',0,'2015-05-29 17:01:49','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:01:49','17:01:49','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(549,'UPDATE',0,'2015-05-29 17:01:49','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:01:49','17:01:49','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(550,'UPDATE',1,'2015-05-29 17:01:53','','Incrementa el sctock de un producto y genera su codigo de barras','17:01:53','17:01:53','0320510206914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(551,'UPDATE',1,'2015-05-29 17:02:05','','Incrementa el sctock de un producto y genera su codigo de barras','17:02:05','17:02:05','0320510212914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(552,'UPDATE',1,'2015-05-29 17:07:01','','Incrementa el sctock de un producto y genera su codigo de barras','17:07:01','17:07:01','0310310216714915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(553,'UPDATE',1,'2015-05-29 17:07:09','','Incrementa el sctock de un producto y genera su codigo de barras','17:07:09','17:07:09','0310460188414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(554,'UPDATE',1,'2015-05-29 17:07:18','','Incrementa el sctock de un producto y genera su codigo de barras','17:07:18','17:07:18','0310420219314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(555,'UPDATE',0,'2015-05-29 17:09:58','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:09:58','17:09:58','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(556,'UPDATE',0,'2015-05-29 17:10:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:08','17:10:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(557,'UPDATE',0,'2015-05-29 17:10:15','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:15','17:10:15','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(558,'UPDATE',0,'2015-05-29 17:10:19','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:19','17:10:19','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(559,'UPDATE',0,'2015-05-29 17:10:20','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:20','17:10:20','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(560,'UPDATE',0,'2015-05-29 17:10:25','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:25','17:10:25','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(561,'UPDATE',0,'2015-05-29 17:10:32','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:32','17:10:32','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(562,'UPDATE',0,'2015-05-29 17:10:33','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:33','17:10:33','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(563,'UPDATE',0,'2015-05-29 17:10:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:46','17:10:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(564,'UPDATE',0,'2015-05-29 17:10:47','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:47','17:10:47','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(565,'UPDATE',0,'2015-05-29 17:10:47','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:47','17:10:47','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(566,'UPDATE',0,'2015-05-29 17:10:47','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:47','17:10:47','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(567,'UPDATE',0,'2015-05-29 17:10:48','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:48','17:10:48','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(568,'UPDATE',0,'2015-05-29 17:10:48','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:48','17:10:48','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(569,'UPDATE',0,'2015-05-29 17:10:48','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:48','17:10:48','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(570,'UPDATE',0,'2015-05-29 17:10:52','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:52','17:10:52','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(571,'UPDATE',0,'2015-05-29 17:10:58','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:10:58','17:10:58','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(572,'UPDATE',0,'2015-05-29 17:11:17','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:11:17','17:11:17','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(573,'UPDATE',0,'2015-05-29 17:11:18','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:11:18','17:11:18','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(574,'UPDATE',1,'2015-05-29 17:11:37','','Incrementa el sctock de un producto y genera su codigo de barras','17:11:37','17:11:37','0270380195114915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(575,'UPDATE',0,'2015-05-29 17:12:06','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:12:06','17:12:06','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(576,'UPDATE',0,'2015-05-29 17:12:07','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:12:07','17:12:07','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(577,'UPDATE',0,'2015-05-29 17:12:11','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:12:11','17:12:11','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(578,'UPDATE',0,'2015-05-29 17:12:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:12:12','17:12:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(579,'UPDATE',0,'2015-05-29 17:12:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:12:12','17:12:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(580,'UPDATE',0,'2015-05-29 17:12:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:12:12','17:12:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(581,'UPDATE',1,'2015-05-29 17:12:29','','Incrementa el sctock de un producto y genera su codigo de barras','17:12:29','17:12:29','0270380195114915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(582,'UPDATE',1,'2015-05-29 17:12:35','','Incrementa el sctock de un producto y genera su codigo de barras','17:12:35','17:12:35','0270380201014915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(583,'UPDATE',1,'2015-05-29 17:12:39','','Incrementa el sctock de un producto y genera su codigo de barras','17:12:39','17:12:39','0270380208414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(584,'UPDATE',1,'2015-05-29 17:12:44','','Incrementa el sctock de un producto y genera su codigo de barras','17:12:44','17:12:44','0270380204514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(585,'UPDATE',1,'2015-05-29 17:12:52','','Incrementa el sctock de un producto y genera su codigo de barras','17:12:52','17:12:52','0270380204314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(586,'UPDATE',1,'2015-05-29 17:12:58','','Incrementa el sctock de un producto y genera su codigo de barras','17:12:58','17:12:58','0270380204214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(587,'UPDATE',1,'2015-05-29 17:19:14','','Incrementa el sctock de un producto y genera su codigo de barras','17:19:14','17:19:14','0250010215414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(588,'UPDATE',1,'2015-05-29 17:19:18','','Incrementa el sctock de un producto y genera su codigo de barras','17:19:18','17:19:18','0250010220414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(589,'UPDATE',1,'2015-05-29 17:19:24','','Incrementa el sctock de un producto y genera su codigo de barras','17:19:24','17:19:24','0250010191814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(590,'UPDATE',1,'2015-05-29 17:19:38','','Incrementa el sctock de un producto y genera su codigo de barras','17:19:38','17:19:38','0270310210914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(591,'UPDATE',0,'2015-05-29 17:19:49','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:49','17:19:49','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(592,'UPDATE',0,'2015-05-29 17:19:51','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:51','17:19:51','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(593,'UPDATE',0,'2015-05-29 17:19:51','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:51','17:19:51','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(594,'UPDATE',0,'2015-05-29 17:19:52','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:52','17:19:52','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(595,'UPDATE',0,'2015-05-29 17:19:52','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:52','17:19:52','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(596,'UPDATE',0,'2015-05-29 17:19:53','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:53','17:19:53','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(597,'UPDATE',0,'2015-05-29 17:19:56','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:19:56','17:19:56','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(598,'UPDATE',1,'2015-05-29 17:25:26','','Incrementa el sctock de un producto y genera su codigo de barras','17:25:26','17:25:26','0270460190214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(599,'UPDATE',1,'2015-05-29 17:25:29','','Incrementa el sctock de un producto y genera su codigo de barras','17:25:29','17:25:29','0270460210314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(600,'UPDATE',1,'2015-05-29 17:25:49','','Incrementa el sctock de un producto y genera su codigo de barras','17:25:49','17:25:49','0270540199814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(601,'UPDATE',1,'2015-05-29 17:25:59','','Incrementa el sctock de un producto y genera su codigo de barras','17:25:59','17:25:59','0270360205314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(602,'UPDATE',1,'2015-05-29 17:26:13','','Incrementa el sctock de un producto y genera su codigo de barras','17:26:13','17:26:13','0260190207814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(603,'UPDATE',1,'2015-05-29 17:29:18','','Incrementa el sctock de un producto y genera su codigo de barras','17:29:18','17:29:18','0140020209214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(604,'UPDATE',1,'2015-05-29 17:29:42','','Incrementa el sctock de un producto y genera su codigo de barras','17:29:42','17:29:42','0140030208614915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(605,'UPDATE',1,'2015-05-29 17:30:18','','Incrementa el sctock de un producto y genera su codigo de barras','17:30:18','17:30:18','0140200220014915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(606,'UPDATE',0,'2015-05-29 17:30:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:28','17:30:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(607,'UPDATE',0,'2015-05-29 17:30:29','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:29','17:30:29','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(608,'UPDATE',0,'2015-05-29 17:30:30','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:30','17:30:30','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(609,'UPDATE',0,'2015-05-29 17:30:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:31','17:30:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(610,'UPDATE',0,'2015-05-29 17:30:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:31','17:30:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(611,'UPDATE',0,'2015-05-29 17:30:35','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:35','17:30:35','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(612,'UPDATE',0,'2015-05-29 17:30:52','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:52','17:30:52','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(613,'UPDATE',0,'2015-05-29 17:30:53','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:53','17:30:53','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(614,'UPDATE',0,'2015-05-29 17:30:53','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:53','17:30:53','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(615,'UPDATE',0,'2015-05-29 17:30:54','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:54','17:30:54','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(616,'UPDATE',0,'2015-05-29 17:30:59','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:30:59','17:30:59','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(617,'UPDATE',0,'2015-05-29 17:31:00','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:31:00','17:31:00','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(618,'UPDATE',0,'2015-05-29 17:31:00','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:31:00','17:31:00','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(619,'UPDATE',1,'2015-05-29 17:31:14','','Incrementa el sctock de un producto y genera su codigo de barras','17:31:14','17:31:14','0150310215214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(620,'UPDATE',1,'2015-05-29 17:31:26','','Incrementa el sctock de un producto y genera su codigo de barras','17:31:26','17:31:26','0150330228414915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(621,'UPDATE',1,'2015-05-29 17:31:40','','Incrementa el sctock de un producto y genera su codigo de barras','17:31:40','17:31:40','0150540222514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(622,'UPDATE',1,'2015-05-29 17:31:49','','Incrementa el sctock de un producto y genera su codigo de barras','17:31:49','17:31:49','0150540217314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(623,'UPDATE',1,'2015-05-29 17:31:56','','Incrementa el sctock de un producto y genera su codigo de barras','17:31:56','17:31:56','0150540220214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(624,'UPDATE',1,'2015-05-29 17:32:07','','Incrementa el sctock de un producto y genera su codigo de barras','17:32:07','17:32:07','0140060213014915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(625,'UPDATE',1,'2015-05-29 17:32:17','','Incrementa el sctock de un producto y genera su codigo de barras','17:32:17','17:32:17','0140060213014915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(626,'UPDATE',1,'2015-05-29 17:32:49','','Incrementa el sctock de un producto y genera su codigo de barras','17:32:49','17:32:49','0140110212914915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(627,'UPDATE',1,'2015-05-29 17:39:50','','Incrementa el sctock de un producto y genera su codigo de barras','17:39:50','17:39:50','0220050194114915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(628,'UPDATE',1,'2015-05-29 17:40:00','','Incrementa el sctock de un producto y genera su codigo de barras','17:40:00','17:40:00','0220050187714915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(629,'UPDATE',1,'2015-05-29 17:40:06','','Incrementa el sctock de un producto y genera su codigo de barras','17:40:06','17:40:06','0220050189814915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(630,'UPDATE',1,'2015-05-29 17:40:13','','Incrementa el sctock de un producto y genera su codigo de barras','17:40:14','17:40:13','0220050197614915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(631,'UPDATE',1,'2015-05-29 17:40:20','','Incrementa el sctock de un producto y genera su codigo de barras','17:40:20','17:40:20','0220050185214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(632,'UPDATE',1,'2015-05-29 17:40:34','','Incrementa el sctock de un producto y genera su codigo de barras','17:40:34','17:40:34','0220040189514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(633,'UPDATE',0,'2015-05-29 17:40:45','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:40:45','17:40:45','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(634,'UPDATE',0,'2015-05-29 17:40:54','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:40:54','17:40:54','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(635,'UPDATE',0,'2015-05-29 17:41:15','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:15','17:41:15','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(636,'UPDATE',0,'2015-05-29 17:41:17','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:17','17:41:17','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(637,'UPDATE',0,'2015-05-29 17:41:18','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:18','17:41:18','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(638,'UPDATE',0,'2015-05-29 17:41:18','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:18','17:41:18','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(639,'UPDATE',0,'2015-05-29 17:41:18','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:18','17:41:18','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(640,'UPDATE',1,'2015-05-29 17:41:32','','Incrementa el sctock de un producto y genera su codigo de barras','17:41:32','17:41:32','0210010185714915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(641,'UPDATE',1,'2015-05-29 17:41:38','','Incrementa el sctock de un producto y genera su codigo de barras','17:41:38','17:41:38','0210010192314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(642,'UPDATE',1,'2015-05-29 17:41:53','','Incrementa el sctock de un producto y genera su codigo de barras','17:41:53','17:41:53','0210010194314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(643,'UPDATE',1,'2015-05-29 17:42:04','','Incrementa el sctock de un producto y genera su codigo de barras','17:42:04','17:42:04','0210010197514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(644,'UPDATE',1,'2015-05-29 17:42:13','','Incrementa el sctock de un producto y genera su codigo de barras','17:42:13','17:42:13','0210010142314915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(645,'UPDATE',1,'2015-05-29 17:42:20','','Incrementa el sctock de un producto y genera su codigo de barras','17:42:20','17:42:20','0210010205214915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(646,'UPDATE',1,'2015-05-29 17:42:28','','Incrementa el sctock de un producto y genera su codigo de barras','17:42:28','17:42:28','0210010199714915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(647,'UPDATE',1,'2015-05-29 17:49:15','','Incrementa el sctock de un producto y genera su codigo de barras','17:49:15','17:49:15','0210010193514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(648,'UPDATE',1,'2015-05-29 17:49:18','','Incrementa el sctock de un producto y genera su codigo de barras','17:49:18','17:49:18','0210010200514915','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(649,'UPDATE',0,'2015-06-01 17:25:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:25:08','17:25:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(650,'UPDATE',0,'2015-06-01 17:26:00','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:26:00','17:26:00','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(651,'UPDATE',0,'2015-06-01 17:26:45','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:26:45','17:26:45','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(652,'UPDATE',0,'2015-06-01 17:27:01','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:27:01','17:27:01','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(653,'UPDATE',0,'2015-06-01 17:27:10','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:27:10','17:27:10','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(654,'UPDATE',0,'2015-06-01 17:28:01','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:28:01','17:28:01','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(655,'UPDATE',0,'2015-06-01 17:28:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:28:31','17:28:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(656,'UPDATE',0,'2015-06-01 17:32:20','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:32:20','17:32:20','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(657,'UPDATE',0,'2015-06-01 17:40:30','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:40:30','17:40:30','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(658,'UPDATE',0,'2015-06-01 17:40:35','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:40:35','17:40:35','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(659,'UPDATE',0,'2015-06-01 17:40:36','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:40:36','17:40:36','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(660,'UPDATE',0,'2015-06-01 17:41:16','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:16','17:41:16','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(661,'UPDATE',0,'2015-06-01 17:41:26','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:41:26','17:41:26','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(662,'UPDATE',0,'2015-06-01 17:42:37','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:42:37','17:42:37','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(663,'UPDATE',0,'2015-06-01 17:43:30','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:43:30','17:43:30','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(664,'UPDATE',0,'2015-06-01 17:43:32','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:43:32','17:43:32','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(665,'UPDATE',1,'2015-06-01 17:45:36','','Incrementa el sctock de un producto y genera su codigo de barras','17:45:36','17:45:36','0130010100015215','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(666,'UPDATE',0,'2015-06-01 17:46:03','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:46:03','17:46:03','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(667,'UPDATE',1,'2015-06-01 17:46:20','','Incrementa el sctock de un producto y genera su codigo de barras','17:46:20','17:46:20','0270310216815215','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(668,'UPDATE',0,'2015-06-01 17:54:44','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','17:54:44','17:54:44','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(669,'CREATE',1,'2015-06-02 07:38:29','','Registra un nuevo color','07:38:29','07:38:29','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','admin'),(670,'CREATE',1,'2015-06-02 07:39:43','','Registra un tejido','07:39:43','07:39:43','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','admin'),(671,'UPDATE',0,'2015-06-02 10:15:11','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:15:11','10:15:11','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(672,'UPDATE',0,'2015-06-02 10:15:14','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:15:14','10:15:14','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(673,'UPDATE',0,'2015-06-02 10:15:29','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:15:29','10:15:29','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(674,'UPDATE',0,'2015-06-02 10:16:53','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:16:53','10:16:53','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(675,'UPDATE',0,'2015-06-02 10:17:44','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:44','10:17:44','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(676,'UPDATE',0,'2015-06-02 10:17:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:46','10:17:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(677,'UPDATE',0,'2015-06-02 10:17:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:46','10:17:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(678,'UPDATE',0,'2015-06-02 10:17:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:47','10:17:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(679,'UPDATE',0,'2015-06-02 10:17:47','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:47','10:17:47','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(680,'UPDATE',0,'2015-06-02 10:17:47','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:47','10:17:47','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(681,'UPDATE',0,'2015-06-02 10:17:48','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:17:48','10:17:48','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(682,'UPDATE',0,'2015-06-02 10:18:30','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:18:30','10:18:30','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(683,'UPDATE',0,'2015-06-02 10:18:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:18:31','10:18:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(684,'UPDATE',0,'2015-06-02 10:18:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:18:31','10:18:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(685,'UPDATE',0,'2015-06-02 10:18:32','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:18:32','10:18:32','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(686,'CREATE',1,'2015-06-02 10:33:20','','Registra un nuevo color','10:33:20','10:33:20','','SUCCESS','ColorService.save(..)   ar.com.adriabe.services.ColorService','sergio'),(687,'CREATE',1,'2015-06-02 10:47:01','','Registra un tejido','10:47:01','10:47:01','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(688,'UPDATE',0,'2015-06-02 10:52:39','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:39','10:52:39','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(689,'UPDATE',0,'2015-06-02 10:52:43','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:43','10:52:43','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(690,'UPDATE',0,'2015-06-02 10:52:44','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:44','10:52:44','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(691,'UPDATE',0,'2015-06-02 10:52:45','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:45','10:52:45','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(692,'UPDATE',0,'2015-06-02 10:52:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:46','10:52:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(693,'UPDATE',0,'2015-06-02 10:52:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:46','10:52:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(694,'UPDATE',0,'2015-06-02 10:52:46','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:52:46','10:52:46','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(695,'UPDATE',0,'2015-06-02 10:53:57','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:53:57','10:53:57','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(696,'UPDATE',0,'2015-06-02 10:54:55','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:54:55','10:54:55','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(697,'UPDATE',0,'2015-06-02 10:54:57','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:54:57','10:54:57','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(698,'UPDATE',0,'2015-06-02 10:55:07','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:55:07','10:55:07','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(699,'UPDATE',0,'2015-06-02 10:55:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:55:08','10:55:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(700,'UPDATE',0,'2015-06-02 10:55:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:55:08','10:55:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(701,'UPDATE',0,'2015-06-02 10:55:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:55:08','10:55:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(702,'UPDATE',0,'2015-06-02 10:55:13','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:55:13','10:55:13','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(703,'UPDATE',1,'2015-06-02 10:55:16','','Incrementa el sctock de un producto y genera su codigo de barras','10:55:16','10:55:16','0060180217315315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(704,'UPDATE',0,'2015-06-02 10:57:54','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:57:54','10:57:54','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(705,'UPDATE',0,'2015-06-02 10:57:55','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','10:57:55','10:57:55','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(706,'CREATE',1,'2015-06-02 11:01:10','','Registra un tejido','11:01:10','11:01:10','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(707,'UPDATE',0,'2015-06-02 11:04:27','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:04:27','11:04:27','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(708,'UPDATE',0,'2015-06-02 11:04:57','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:04:57','11:04:57','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(709,'CREATE',1,'2015-06-02 11:06:08','','Registra un tejido','11:06:08','11:06:08','','SUCCESS','ProductService.save(..)   ar.com.adriabe.services.ProductService','sergio'),(710,'UPDATE',0,'2015-06-02 11:06:25','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:06:25','11:06:25','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(711,'UPDATE',0,'2015-06-02 11:06:26','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:06:26','11:06:26','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(712,'UPDATE',0,'2015-06-02 11:06:27','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:06:27','11:06:27','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(713,'UPDATE',0,'2015-06-02 11:06:27','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:06:27','11:06:27','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(714,'UPDATE',0,'2015-06-02 11:06:51','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:06:51','11:06:51','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(715,'UPDATE',0,'2015-06-02 11:06:53','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:06:53','11:06:53','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(716,'UPDATE',0,'2015-06-02 11:08:14','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:14','11:08:14','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(717,'UPDATE',0,'2015-06-02 11:08:16','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:16','11:08:16','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(718,'UPDATE',0,'2015-06-02 11:08:17','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:17','11:08:17','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(719,'UPDATE',0,'2015-06-02 11:08:17','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:17','11:08:17','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(720,'UPDATE',0,'2015-06-02 11:08:48','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:48','11:08:48','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(721,'UPDATE',0,'2015-06-02 11:08:49','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:49','11:08:49','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(722,'UPDATE',0,'2015-06-02 11:08:50','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:50','11:08:50','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(723,'UPDATE',0,'2015-06-02 11:08:50','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:08:50','11:08:50','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(724,'UPDATE',0,'2015-06-02 11:09:01','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:01','11:09:01','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(725,'UPDATE',0,'2015-06-02 11:09:01','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:01','11:09:01','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(726,'UPDATE',0,'2015-06-02 11:09:02','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:02','11:09:02','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(727,'UPDATE',0,'2015-06-02 11:09:02','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:02','11:09:02','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(728,'UPDATE',0,'2015-06-02 11:09:02','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:02','11:09:02','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(729,'UPDATE',0,'2015-06-02 11:09:24','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:24','11:09:24','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(730,'UPDATE',0,'2015-06-02 11:09:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:28','11:09:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(731,'UPDATE',0,'2015-06-02 11:09:30','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:30','11:09:30','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(732,'UPDATE',0,'2015-06-02 11:09:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:31','11:09:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(733,'UPDATE',0,'2015-06-02 11:09:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:31','11:09:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(734,'UPDATE',0,'2015-06-02 11:09:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:31','11:09:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(735,'UPDATE',0,'2015-06-02 11:09:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:31','11:09:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(736,'UPDATE',0,'2015-06-02 11:09:40','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:40','11:09:40','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(737,'UPDATE',0,'2015-06-02 11:09:41','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:09:41','11:09:41','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(738,'UPDATE',0,'2015-06-02 11:10:07','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:07','11:10:07','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(739,'UPDATE',0,'2015-06-02 11:10:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:08','11:10:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(740,'UPDATE',0,'2015-06-02 11:10:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:12','11:10:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(741,'UPDATE',0,'2015-06-02 11:10:13','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:13','11:10:13','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(742,'UPDATE',0,'2015-06-02 11:10:13','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:13','11:10:13','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(743,'UPDATE',0,'2015-06-02 11:10:14','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:14','11:10:14','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(744,'UPDATE',0,'2015-06-02 11:10:25','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:25','11:10:25','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(745,'UPDATE',0,'2015-06-02 11:10:30','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:30','11:10:30','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(746,'UPDATE',0,'2015-06-02 11:10:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:31','11:10:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(747,'UPDATE',0,'2015-06-02 11:10:55','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:10:55','11:10:55','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(748,'UPDATE',0,'2015-06-02 11:11:05','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:05','11:11:05','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(749,'UPDATE',0,'2015-06-02 11:11:06','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:06','11:11:06','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(750,'UPDATE',0,'2015-06-02 11:11:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:12','11:11:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(751,'UPDATE',0,'2015-06-02 11:11:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:12','11:11:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(752,'UPDATE',0,'2015-06-02 11:11:12','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:12','11:11:12','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(753,'UPDATE',0,'2015-06-02 11:11:13','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:13','11:11:13','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(754,'UPDATE',0,'2015-06-02 11:11:19','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:19','11:11:19','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(755,'UPDATE',0,'2015-06-02 11:11:20','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:20','11:11:20','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(756,'UPDATE',0,'2015-06-02 11:11:22','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:22','11:11:22','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(757,'UPDATE',0,'2015-06-02 11:11:26','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:26','11:11:26','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(758,'UPDATE',0,'2015-06-02 11:11:33','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:34','11:11:33','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(759,'UPDATE',0,'2015-06-02 11:11:35','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:35','11:11:35','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(760,'UPDATE',0,'2015-06-02 11:11:35','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:35','11:11:35','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(761,'UPDATE',0,'2015-06-02 11:11:36','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:36','11:11:36','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(762,'UPDATE',0,'2015-06-02 11:11:36','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:11:36','11:11:36','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(763,'UPDATE',1,'2015-06-02 11:11:39','','Incrementa el sctock de un producto y genera su codigo de barras','11:11:39','11:11:39','0150310217115315','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(764,'UPDATE',0,'2015-06-02 11:12:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:08','11:12:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(765,'UPDATE',0,'2015-06-02 11:12:08','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:09','11:12:08','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(766,'UPDATE',0,'2015-06-02 11:12:09','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:09','11:12:09','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(767,'UPDATE',0,'2015-06-02 11:12:27','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:27','11:12:27','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(768,'UPDATE',0,'2015-06-02 11:12:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:28','11:12:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(769,'UPDATE',0,'2015-06-02 11:12:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:28','11:12:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(770,'UPDATE',0,'2015-06-02 11:12:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:28','11:12:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(771,'UPDATE',0,'2015-06-02 11:12:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:28','11:12:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(772,'UPDATE',0,'2015-06-02 11:12:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:28','11:12:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(773,'UPDATE',0,'2015-06-02 11:12:29','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:12:29','11:12:29','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(774,'UPDATE',0,'2015-06-02 11:13:37','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','11:13:37','11:13:37','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(775,'UPDATE',1,'2015-06-05 18:09:34','','Incrementa el sctock de un producto y genera su codigo de barras','18:09:34','18:09:34','0130010210015615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(776,'UPDATE',0,'2015-06-05 18:32:31','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','18:32:31','18:32:31','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(777,'UPDATE',0,'2015-06-05 18:32:38','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','18:32:38','18:32:38','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(778,'UPDATE',0,'2015-06-05 18:33:28','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','18:33:28','18:33:28','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(779,'UPDATE',0,'2015-06-05 18:33:43','The given id must not be null!; nested exception is java.lang.IllegalArgumentException: The given id must not be null!','Incrementa el sctock de un producto y genera su codigo de barras','18:33:43','18:33:43','','FAILURE','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(780,'UPDATE',1,'2015-06-05 18:34:26','','Incrementa el sctock de un producto y genera su codigo de barras','18:34:26','18:34:26','0130010210015615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(781,'UPDATE',1,'2015-06-05 18:37:32','','Incrementa el sctock de un producto y genera su codigo de barras','18:37:32','18:37:32','0160470210015615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(782,'UPDATE',1,'2015-06-05 18:50:59','','Incrementa el sctock de un producto y genera su codigo de barras','18:50:59','18:50:59','0140140210015615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM'),(783,'UPDATE',1,'2015-06-05 19:32:53','','Incrementa el sctock de un producto y genera su codigo de barras','19:32:53','19:32:53','0140070210015615','SUCCESS','ProductService.incrementStock(..)   ar.com.adriabe.services.ProductService','SYSTEM');
/*!40000 ALTER TABLE `operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `mainItem` tinyint(1) NOT NULL,
  `price` double NOT NULL,
  `quantity` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2D110D6425B861C7` (`order_id`),
  KEY `FK2D110D64A13F2E7` (`product_id`),
  CONSTRAINT `FK2D110D6425B861C7` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK2D110D64A13F2E7` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item_detail`
--

DROP TABLE IF EXISTS `order_item_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `weight` double NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `order_item_id` bigint(20) DEFAULT NULL,
  `delivery_order_item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK82E5EF8C332B85A4` (`order_item_id`),
  KEY `FK82E5EF8C9FB70779` (`createdBy_id`),
  KEY `FK82E5EF8CDB209962` (`lastModifiedBy_id`),
  KEY `FK82E5EF8CAC391205` (`delivery_order_item_id`),
  CONSTRAINT `FK82E5EF8C332B85A4` FOREIGN KEY (`order_item_id`) REFERENCES `order_items` (`id`),
  CONSTRAINT `FK82E5EF8C9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK82E5EF8CAC391205` FOREIGN KEY (`delivery_order_item_id`) REFERENCES `delivery_order_items` (`id`),
  CONSTRAINT `FK82E5EF8CDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item_detail`
--

LOCK TABLES `order_item_detail` WRITE;
/*!40000 ALTER TABLE `order_item_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item_order_item`
--

DROP TABLE IF EXISTS `order_item_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item_order_item` (
  `order_item_id` bigint(20) NOT NULL,
  `accesories_id` bigint(20) NOT NULL,
  UNIQUE KEY `accesories_id` (`accesories_id`),
  KEY `FK2308CF1F332B85A4` (`order_item_id`),
  KEY `FK2308CF1FFB6084E3` (`accesories_id`),
  CONSTRAINT `FK2308CF1F332B85A4` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`id`),
  CONSTRAINT `FK2308CF1FFB6084E3` FOREIGN KEY (`accesories_id`) REFERENCES `order_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item_order_item`
--

LOCK TABLES `order_item_order_item` WRITE;
/*!40000 ALTER TABLE `order_item_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `price` double NOT NULL,
  `quantity` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `mainProduct_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK75109F8F9FB70779` (`createdBy_id`),
  KEY `FK75109F8F25B861C7` (`order_id`),
  KEY `FK75109F8FA13F2E7` (`product_id`),
  KEY `FK75109F8FDB209962` (`lastModifiedBy_id`),
  KEY `FK75109F8FD57788B2` (`mainProduct_id`),
  CONSTRAINT `FK75109F8F25B861C7` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK75109F8F9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK75109F8FA13F2E7` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK75109F8FD57788B2` FOREIGN KEY (`mainProduct_id`) REFERENCES `order_items` (`id`),
  CONSTRAINT `FK75109F8FDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `deliveredDate` datetime DEFAULT NULL,
  `estimatedDate` datetime DEFAULT NULL,
  `orderedDate` datetime DEFAULT NULL,
  `prepearedDate` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  `totalDeliveredRolls` int(11) NOT NULL,
  `totalOrderedRolls` int(11) NOT NULL,
  `totalPrepearedRolls` int(11) NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC3DF62E59FB70779` (`createdBy_id`),
  KEY `FKC3DF62E5225B58AD` (`client_id`),
  KEY `FKC3DF62E5DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKC3DF62E5225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKC3DF62E59FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKC3DF62E5DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_cheques`
--

DROP TABLE IF EXISTS `payment_cheques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_cheques` (
  `payment_id` bigint(20) NOT NULL,
  `cheque_id` bigint(20) NOT NULL,
  KEY `FK8539C539E5484787` (`payment_id`),
  KEY `FK8539C539C3D9B0ED` (`cheque_id`),
  CONSTRAINT `FK8539C539C3D9B0ED` FOREIGN KEY (`cheque_id`) REFERENCES `cheque` (`id`),
  CONSTRAINT `FK8539C539E5484787` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_cheques`
--

LOCK TABLES `payment_cheques` WRITE;
/*!40000 ALTER TABLE `payment_cheques` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_cheques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_transfers`
--

DROP TABLE IF EXISTS `payment_transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_transfers` (
  `payment_id` bigint(20) NOT NULL,
  `transfers_id` bigint(20) NOT NULL,
  KEY `FK2C6122EFE5484787` (`payment_id`),
  KEY `FK2C6122EF99C550D0` (`transfers_id`),
  CONSTRAINT `FK2C6122EF99C550D0` FOREIGN KEY (`transfers_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FK2C6122EFE5484787` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_transfers`
--

LOCK TABLES `payment_transfers` WRITE;
/*!40000 ALTER TABLE `payment_transfers` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_transfers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments`
--

DROP TABLE IF EXISTS `payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `activity` tinyint(1) NOT NULL,
  `cash` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `dateReviewed` datetime DEFAULT NULL,
  `number` bigint(20) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `transfer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK526A0F2DA1BEFBED` (`transfer_id`),
  KEY `FK526A0F2D9FB70779` (`createdBy_id`),
  KEY `FK526A0F2DDB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK526A0F2D9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK526A0F2DA1BEFBED` FOREIGN KEY (`transfer_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FK526A0F2DDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments`
--

LOCK TABLES `payments` WRITE;
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payments_cheque`
--

DROP TABLE IF EXISTS `payments_cheque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payments_cheque` (
  `payments_id` bigint(20) NOT NULL,
  `cheques_id` bigint(20) NOT NULL,
  UNIQUE KEY `cheques_id` (`cheques_id`),
  KEY `FKC6D4E37329630500` (`payments_id`),
  KEY `FKC6D4E3736AB622FC` (`cheques_id`),
  CONSTRAINT `FKC6D4E37329630500` FOREIGN KEY (`payments_id`) REFERENCES `payments` (`id`),
  CONSTRAINT `FKC6D4E3736AB622FC` FOREIGN KEY (`cheques_id`) REFERENCES `cheque` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payments_cheque`
--

LOCK TABLES `payments_cheque` WRITE;
/*!40000 ALTER TABLE `payments_cheque` DISABLE KEYS */;
/*!40000 ALTER TABLE `payments_cheque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_families`
--

DROP TABLE IF EXISTS `product_families`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_families` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `COLOR_TYPE` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fabric_id` bigint(20) DEFAULT NULL,
  `stripe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCC1C6452A46EF16D` (`stripe_id`),
  KEY `FKCC1C64521091726D` (`fabric_id`),
  CONSTRAINT `FKCC1C64521091726D` FOREIGN KEY (`fabric_id`) REFERENCES `fabrics` (`id`),
  CONSTRAINT `FKCC1C6452A46EF16D` FOREIGN KEY (`stripe_id`) REFERENCES `stripes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_families`
--

LOCK TABLES `product_families` WRITE;
/*!40000 ALTER TABLE `product_families` DISABLE KEYS */;
INSERT INTO `product_families` VALUES (1,0,'BLANCO','Ale test ',8,NULL),(2,0,'CLARO','Ale test ',8,NULL),(3,0,'OSCURO','Ale test ',8,NULL),(4,0,'ESPECIAL','Ale test ',8,NULL),(5,0,'BLANCO','Yersey liviano ',5,NULL),(6,0,'CLARO','Yersey liviano ',5,NULL),(7,0,'OSCURO','Yersey liviano ',5,NULL),(8,0,'ESPECIAL','Yersey liviano ',5,NULL),(9,0,'BLANCO','Yersey liviano ryd. verano 14-15',5,1),(10,0,'CLARO','Yersey liviano ryd. verano 14-15',5,1),(11,0,'OSCURO','Yersey liviano ryd. verano 14-15',5,1),(12,0,'ESPECIAL','Yersey liviano ryd. verano 14-15',5,1),(13,0,'BLANCO','Panal Liso  ',1,NULL),(14,0,'CLARO','Panal Liso  ',1,NULL),(15,0,'OSCURO','Panal Liso  ',1,NULL),(16,0,'ESPECIAL','Panal Liso  ',1,NULL),(17,0,'BLANCO','Yersey semi-pesado ',3,NULL),(18,0,'CLARO','Yersey semi-pesado ',3,NULL),(19,0,'OSCURO','Yersey semi-pesado ',3,NULL),(20,0,'ESPECIAL','Yersey semi-pesado ',3,NULL),(21,0,'BLANCO','Yersey algodon ',4,NULL),(22,0,'CLARO','Yersey algodon ',4,NULL),(23,0,'OSCURO','Yersey algodon ',4,NULL),(24,0,'ESPECIAL','Yersey algodon ',4,NULL),(25,0,'BLANCO','Friza Liviana  ',6,NULL),(26,0,'CLARO','Friza Liviana  ',6,NULL),(27,0,'OSCURO','Friza Liviana  ',6,NULL),(28,0,'ESPECIAL','Friza Liviana  ',6,NULL),(29,0,'BLANCO','Friza Pesada ',7,NULL),(30,0,'CLARO','Friza Pesada ',7,NULL),(31,0,'OSCURO','Friza Pesada ',7,NULL),(32,0,'ESPECIAL','Friza Pesada ',7,NULL),(33,0,'BLANCO','Panal Liso  ryd. CLASICA',1,2),(34,0,'CLARO','Panal Liso  ryd. CLASICA',1,2),(35,0,'OSCURO','Panal Liso  ryd. CLASICA',1,2),(36,0,'ESPECIAL','Panal Liso  ryd. CLASICA',1,2),(37,0,'BLANCO','Panal ryd. Mono Raya',1,3),(38,0,'CLARO','Panal ryd. Mono Raya',1,3),(39,0,'OSCURO','Panal ryd. Mono Raya',1,3),(40,0,'ESPECIAL','Panal ryd. Mono Raya',1,3),(41,0,'BLANCO','Panal ryd. Rayado Duo',1,4),(42,0,'CLARO','Panal ryd. Rayado Duo',1,4),(43,0,'OSCURO','Panal ryd. Rayado Duo',1,4),(44,0,'ESPECIAL','Panal ryd. Rayado Duo',1,4),(45,0,'BLANCO','Varios ',9,NULL),(46,0,'CLARO','Varios ',9,NULL),(47,0,'OSCURO','Varios ',9,NULL),(48,0,'ESPECIAL','Varios ',9,NULL);
/*!40000 ALTER TABLE `product_families` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_packages`
--

DROP TABLE IF EXISTS `product_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_packages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `product` tinyblob,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_packages`
--

LOCK TABLES `product_packages` WRITE;
/*!40000 ALTER TABLE `product_packages` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `stock` bigint(20) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `color_id` bigint(20) DEFAULT NULL,
  `fabric_id` bigint(20) DEFAULT NULL,
  `family_id` bigint(20) DEFAULT NULL,
  `striped_id` bigint(20) DEFAULT NULL,
  `striped_combination_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC42BD164552D37C3` (`striped_id`),
  KEY `FKC42BD1649FB70779` (`createdBy_id`),
  KEY `FKC42BD164EF829FA2` (`striped_combination_id`),
  KEY `FKC42BD1641091726D` (`fabric_id`),
  KEY `FKC42BD164990FCED6` (`family_id`),
  KEY `FKC42BD164B600EDA7` (`color_id`),
  KEY `FKC42BD164DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKC42BD1641091726D` FOREIGN KEY (`fabric_id`) REFERENCES `fabrics` (`id`),
  CONSTRAINT `FKC42BD164552D37C3` FOREIGN KEY (`striped_id`) REFERENCES `stripes` (`id`),
  CONSTRAINT `FKC42BD164990FCED6` FOREIGN KEY (`family_id`) REFERENCES `product_families` (`id`),
  CONSTRAINT `FKC42BD1649FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKC42BD164B600EDA7` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  CONSTRAINT `FKC42BD164DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKC42BD164EF829FA2` FOREIGN KEY (`striped_combination_id`) REFERENCES `stripe_combination` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,NULL,NULL,0,2,NULL,NULL,1,8,1,NULL,NULL),(2,NULL,NULL,0,4,NULL,NULL,1,5,5,NULL,NULL),(3,NULL,NULL,0,17,NULL,NULL,1,4,21,NULL,NULL),(4,NULL,NULL,0,5,NULL,NULL,4,1,38,3,2),(5,NULL,NULL,0,8,NULL,NULL,35,1,39,3,3),(6,NULL,NULL,0,3,NULL,NULL,5,1,38,3,2),(7,NULL,NULL,0,1,NULL,NULL,5,1,42,4,4),(8,NULL,NULL,0,6,NULL,NULL,6,1,42,4,4),(9,NULL,NULL,0,1,NULL,NULL,7,1,42,4,4),(10,NULL,NULL,0,12,NULL,NULL,36,1,39,3,3),(11,NULL,NULL,0,1,NULL,NULL,20,1,38,3,3),(12,NULL,NULL,0,3,NULL,NULL,6,1,38,3,2),(13,NULL,NULL,0,1,NULL,NULL,7,1,38,3,2),(14,NULL,NULL,0,1,NULL,NULL,40,1,39,3,2),(15,NULL,NULL,0,3,NULL,NULL,54,1,39,3,3),(16,NULL,NULL,0,1,NULL,NULL,30,1,38,3,2),(17,NULL,NULL,0,2,NULL,NULL,31,1,39,3,3),(18,NULL,NULL,0,1,NULL,NULL,4,1,42,4,4),(19,NULL,NULL,0,1,NULL,NULL,5,1,42,4,5),(20,NULL,NULL,0,3,NULL,NULL,33,1,39,3,3),(21,NULL,NULL,0,12,NULL,NULL,5,4,22,NULL,NULL),(22,NULL,NULL,0,8,NULL,NULL,54,4,23,NULL,NULL),(23,NULL,NULL,0,8,NULL,NULL,31,4,23,NULL,NULL),(24,NULL,NULL,0,5,NULL,NULL,29,4,22,NULL,NULL),(25,NULL,NULL,0,2,NULL,NULL,36,4,23,NULL,NULL),(26,NULL,NULL,0,4,NULL,NULL,4,4,22,NULL,NULL),(27,NULL,NULL,0,8,NULL,NULL,6,1,14,NULL,NULL),(28,NULL,NULL,0,7,NULL,NULL,36,1,15,NULL,NULL),(29,NULL,NULL,0,2,NULL,NULL,1,1,13,NULL,NULL),(30,NULL,NULL,0,6,NULL,NULL,54,1,15,NULL,NULL),(31,NULL,NULL,0,3,NULL,NULL,30,1,14,NULL,NULL),(32,NULL,NULL,0,3,NULL,NULL,36,5,7,NULL,NULL),(33,NULL,NULL,0,10,NULL,NULL,31,5,7,NULL,NULL),(34,NULL,NULL,0,6,NULL,NULL,54,5,7,NULL,NULL),(35,NULL,NULL,0,1,NULL,NULL,47,5,8,NULL,NULL),(36,NULL,NULL,0,4,NULL,NULL,18,5,6,NULL,NULL),(37,NULL,NULL,0,1,NULL,NULL,12,5,6,NULL,NULL),(38,NULL,NULL,0,1,NULL,NULL,5,5,6,NULL,NULL),(39,NULL,NULL,0,3,NULL,NULL,4,5,6,NULL,NULL),(40,NULL,NULL,0,2,NULL,NULL,41,5,7,NULL,NULL),(41,NULL,NULL,0,1,NULL,NULL,52,5,8,NULL,NULL),(42,NULL,NULL,0,1,NULL,NULL,35,5,7,NULL,NULL),(43,NULL,NULL,0,2,NULL,NULL,2,5,6,NULL,NULL),(44,NULL,NULL,0,2,NULL,NULL,30,7,30,NULL,NULL),(45,NULL,NULL,0,9,NULL,NULL,18,7,30,NULL,NULL),(46,NULL,NULL,0,8,NULL,NULL,31,7,31,NULL,NULL),(47,NULL,NULL,0,4,NULL,NULL,6,7,30,NULL,NULL),(48,NULL,NULL,0,1,NULL,NULL,37,7,31,NULL,NULL),(49,NULL,NULL,0,7,NULL,NULL,47,7,32,NULL,NULL),(50,NULL,NULL,0,26,NULL,NULL,35,7,31,NULL,NULL),(51,NULL,NULL,0,3,NULL,NULL,42,7,31,NULL,NULL),(52,NULL,NULL,0,5,NULL,NULL,54,7,31,NULL,NULL),(53,NULL,NULL,0,5,NULL,NULL,41,7,31,NULL,NULL),(54,NULL,NULL,0,6,NULL,NULL,51,7,32,NULL,NULL),(55,NULL,NULL,0,1,NULL,NULL,19,7,30,NULL,NULL),(56,NULL,NULL,0,5,NULL,NULL,36,6,27,NULL,NULL),(57,NULL,NULL,0,1,NULL,NULL,4,6,26,NULL,NULL),(58,NULL,NULL,0,4,NULL,NULL,31,6,27,NULL,NULL),(59,NULL,NULL,0,3,NULL,NULL,19,6,26,NULL,NULL),(60,NULL,NULL,0,1,NULL,NULL,15,6,26,NULL,NULL),(61,NULL,NULL,0,1,NULL,NULL,35,6,27,NULL,NULL),(62,NULL,NULL,0,4,NULL,NULL,1,6,25,NULL,NULL),(63,NULL,NULL,0,10,NULL,NULL,38,6,27,NULL,NULL),(64,NULL,NULL,0,42,NULL,NULL,36,7,31,NULL,NULL),(65,NULL,NULL,0,8,NULL,NULL,14,7,30,NULL,NULL),(66,NULL,NULL,0,1,NULL,NULL,3,6,26,NULL,NULL),(67,NULL,NULL,0,3,NULL,NULL,54,6,27,NULL,NULL),(68,NULL,NULL,0,2,NULL,NULL,41,6,27,NULL,NULL),(69,NULL,NULL,0,2,NULL,NULL,37,5,7,NULL,NULL),(70,NULL,NULL,0,1,NULL,NULL,6,5,6,NULL,NULL),(71,NULL,NULL,0,2,NULL,NULL,36,3,19,NULL,NULL),(72,NULL,NULL,0,6,NULL,NULL,40,7,31,NULL,NULL),(73,NULL,NULL,0,3,NULL,NULL,3,7,30,NULL,NULL),(74,NULL,NULL,0,2,NULL,NULL,2,7,30,NULL,NULL),(75,NULL,NULL,0,10,NULL,NULL,46,7,31,NULL,NULL),(76,NULL,NULL,0,1,NULL,NULL,17,4,22,NULL,NULL),(77,NULL,NULL,0,1,NULL,NULL,35,4,23,NULL,NULL),(78,NULL,NULL,0,1,NULL,NULL,2,4,22,NULL,NULL),(79,NULL,NULL,0,2,NULL,NULL,19,4,22,NULL,NULL),(80,NULL,NULL,0,5,NULL,NULL,11,4,22,NULL,NULL),(81,NULL,NULL,0,1,NULL,NULL,49,4,24,NULL,NULL),(82,NULL,NULL,0,1,NULL,NULL,40,4,23,NULL,NULL),(83,NULL,NULL,0,2,NULL,NULL,5,3,18,NULL,NULL),(84,NULL,NULL,0,1,NULL,NULL,11,6,26,NULL,NULL),(85,NULL,NULL,0,1,NULL,NULL,6,4,22,NULL,NULL),(86,NULL,NULL,0,4,NULL,NULL,2,1,14,NULL,NULL),(87,NULL,NULL,0,2,NULL,NULL,11,1,14,NULL,NULL),(88,NULL,NULL,0,5,NULL,NULL,5,1,14,NULL,NULL),(89,NULL,NULL,0,5,NULL,NULL,43,1,15,NULL,NULL),(90,NULL,NULL,0,3,NULL,NULL,20,1,14,NULL,NULL),(91,NULL,NULL,0,5,NULL,NULL,3,1,14,NULL,NULL),(92,NULL,NULL,0,2,NULL,NULL,33,1,15,NULL,NULL),(93,NULL,NULL,0,4,NULL,NULL,31,1,15,NULL,NULL),(94,NULL,NULL,0,1,NULL,NULL,4,1,42,4,5),(95,NULL,NULL,0,1,NULL,NULL,35,1,15,NULL,NULL),(96,NULL,NULL,0,2,NULL,NULL,4,7,30,NULL,NULL),(97,NULL,NULL,0,2,NULL,NULL,39,7,31,NULL,NULL),(98,NULL,NULL,0,4,NULL,NULL,38,7,31,NULL,NULL),(99,NULL,NULL,0,1,NULL,NULL,12,7,30,NULL,NULL),(100,NULL,NULL,0,2,NULL,NULL,47,6,28,NULL,NULL),(101,NULL,NULL,0,2,NULL,NULL,6,6,26,NULL,NULL),(102,NULL,NULL,0,3,NULL,NULL,46,6,27,NULL,NULL),(103,NULL,NULL,0,2,NULL,NULL,14,3,18,NULL,NULL),(104,NULL,NULL,0,1,NULL,NULL,1,3,17,NULL,NULL),(105,NULL,NULL,0,1,NULL,NULL,31,3,19,NULL,NULL),(106,NULL,NULL,0,1,NULL,NULL,15,5,6,NULL,NULL),(107,NULL,NULL,0,0,NULL,NULL,47,1,16,NULL,NULL),(108,NULL,NULL,0,1,NULL,NULL,14,1,14,NULL,NULL),(109,NULL,NULL,0,1,NULL,NULL,7,1,14,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_bills`
--

DROP TABLE IF EXISTS `receipt_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt_bills` (
  `receipt_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  KEY `FK1774A885B70247` (`receipt_id`),
  KEY `FK1774A885219037ED` (`bill_id`),
  CONSTRAINT `FK1774A885219037ED` FOREIGN KEY (`bill_id`) REFERENCES `bills` (`id`),
  CONSTRAINT `FK1774A885B70247` FOREIGN KEY (`receipt_id`) REFERENCES `receipts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_bills`
--

LOCK TABLES `receipt_bills` WRITE;
/*!40000 ALTER TABLE `receipt_bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_cheques`
--

DROP TABLE IF EXISTS `receipt_cheques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt_cheques` (
  `payment_id` bigint(20) NOT NULL,
  `cheque_id` bigint(20) NOT NULL,
  KEY `FK3FBDB36B54AE87B9` (`payment_id`),
  KEY `FK3FBDB36BC3D9B0ED` (`cheque_id`),
  CONSTRAINT `FK3FBDB36BC3D9B0ED` FOREIGN KEY (`cheque_id`) REFERENCES `cheque` (`id`),
  CONSTRAINT `FK3FBDB36B54AE87B9` FOREIGN KEY (`payment_id`) REFERENCES `receipts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_cheques`
--

LOCK TABLES `receipt_cheques` WRITE;
/*!40000 ALTER TABLE `receipt_cheques` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt_cheques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_transfers`
--

DROP TABLE IF EXISTS `receipt_transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt_transfers` (
  `payment_id` bigint(20) NOT NULL,
  `transfers_id` bigint(20) NOT NULL,
  KEY `FK55A24CA154AE87B9` (`payment_id`),
  KEY `FK55A24CA199C550D0` (`transfers_id`),
  CONSTRAINT `FK55A24CA199C550D0` FOREIGN KEY (`transfers_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FK55A24CA154AE87B9` FOREIGN KEY (`payment_id`) REFERENCES `receipts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_transfers`
--

LOCK TABLES `receipt_transfers` WRITE;
/*!40000 ALTER TABLE `receipt_transfers` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt_transfers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipts`
--

DROP TABLE IF EXISTS `receipts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `cash` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCFCBD53B9FB70779` (`createdBy_id`),
  KEY `FKCFCBD53B225B58AD` (`client_id`),
  KEY `FKCFCBD53BDB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKCFCBD53BDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKCFCBD53B225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKCFCBD53B9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipts`
--

LOCK TABLES `receipts` WRITE;
/*!40000 ALTER TABLE `receipts` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN','Producción'),(2,'ROLE_ADMIN','Productos'),(3,'ROLE_ADMIN','Cobranzas'),(4,'ROLE_ADMIN','Ventas'),(5,'ROLE_ADMIN','Tecnico'),(6,'ROLE_ADMIN','Clientes'),(7,'ROLE_ADMIN','Proveedores'),(8,'ROLE_ADMIN','Impuestos'),(9,'ROLE_ADMIN','Caja'),(10,'ROLE_ADMIN','Facturación');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBD40D5387A352D8D` (`role_id`),
  CONSTRAINT `FKBD40D5387A352D8D` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (1,'ADMIN',1),(2,'USER',1),(3,'ADMIN',2),(4,'USER',2),(5,'ADMIN',3),(6,'USER',3),(7,'ADMIN',4),(8,'USER',4),(9,'ADMIN',5),(10,'USER',5),(11,'ADMIN',6),(12,'USER',6),(13,'ADMIN',7),(14,'USER',7),(15,'ADMIN',8),(16,'USER',8),(17,'ADMIN',9),(18,'USER',9),(19,'ADMIN',10),(20,'USER',10);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,'Facturación','Número de facturas A','Número de facturas A','nro-factura-a','0000'),(2,'Facturación','Número de remitos A','Número de remitos A','nro-remito-a','0000'),(3,'Facturación','Número de facturas B','Número de facturas B','nro-factura-b','0000'),(4,'Facturación','Número de remitos B','Número de remitos B','nro-remito-b','0000');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `atiende` varchar(255) DEFAULT NULL,
  `feria` varchar(255) DEFAULT NULL,
  `pasillo` varchar(255) DEFAULT NULL,
  `puesto` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCAD423B29FB70779` (`createdBy_id`),
  KEY `FKCAD423B2225B58AD` (`client_id`),
  KEY `FKCAD423B2DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKCAD423B2225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKCAD423B29FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKCAD423B2DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stripe_combination`
--

DROP TABLE IF EXISTS `stripe_combination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stripe_combination` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `colorFingerA` varchar(255) DEFAULT NULL,
  `colorFingerB` varchar(255) DEFAULT NULL,
  `colorFingerC` varchar(255) DEFAULT NULL,
  `colorFingerD` varchar(255) DEFAULT NULL,
  `colorFingerE` varchar(255) DEFAULT NULL,
  `colorFingerF` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `stripe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC76AEDDDA46EF16D` (`stripe_id`),
  CONSTRAINT `FKC76AEDDDA46EF16D` FOREIGN KEY (`stripe_id`) REFERENCES `stripes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stripe_combination`
--

LOCK TABLES `stripe_combination` WRITE;
/*!40000 ALTER TABLE `stripe_combination` DISABLE KEYS */;
INSERT INTO `stripe_combination` VALUES (1,0,'ff0000','ffff00','0000ff','800080','008000','ffffff','comb1',1),(2,0,'ffffff','000000','ffffff','ffffff','ffffff','ffffff','Negro',3),(3,0,'ffff00','ffffff','ffffff','ffffff','ffffff','ffffff','Blanco',3),(4,0,'000000','ffffff','ffffff','ffffff','ffffff','ffffff','Negro-Blanco-Negro',4),(5,0,'ffffff','000000','ffffff','ffffff','ffffff','ffffff','Blanco-Negro-Blanco',4);
/*!40000 ALTER TABLE `stripe_combination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stripes`
--

DROP TABLE IF EXISTS `stripes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stripes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `code` varchar(255) NOT NULL,
  `colors` int(11) NOT NULL,
  `listingFormula` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`),
  KEY `FK8FD6A9C69FB70779` (`createdBy_id`),
  KEY `FK8FD6A9C6DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK8FD6A9C69FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK8FD6A9C6DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stripes`
--

LOCK TABLES `stripes` WRITE;
/*!40000 ALTER TABLE `stripes` DISABLE KEYS */;
INSERT INTO `stripes` VALUES (1,'2015-03-10 00:14:23','2015-03-10 00:14:23',0,'123',1,'2a2b3a3f4d4e','verano 14-15',3,3),(2,'2015-03-10 23:02:57','2015-03-10 23:02:57',0,'01',2,'22f5A3b22f','CLASICA',3,3),(3,'2015-03-14 12:31:37','2015-03-14 12:31:37',0,'R001',1,'20A5B','Mono Raya',3,3),(4,'2015-03-14 12:35:11','2015-03-14 12:35:11',0,'R002',3,'2a3b2a','Rayado Duo',NULL,NULL);
/*!40000 ALTER TABLE `stripes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_bills`
--

DROP TABLE IF EXISTS `supplier_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_bills` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `billNumber` bigint(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `ivaCategory` varchar(255) DEFAULT NULL,
  `ivaTax` double DEFAULT NULL,
  `orderNumber` bigint(20) DEFAULT NULL,
  `packages` int(11) DEFAULT NULL,
  `subTotal` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB6731199FB70779` (`createdBy_id`),
  KEY `FKB6731194CD1A44D` (`supplier_id`),
  KEY `FKB673119DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKB6731194CD1A44D` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `FKB6731199FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKB673119DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_bills`
--

LOCK TABLES `supplier_bills` WRITE;
/*!40000 ALTER TABLE `supplier_bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_contacts`
--

DROP TABLE IF EXISTS `supplier_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nextel` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA6A4DD669FB70779` (`createdBy_id`),
  KEY `FKA6A4DD664CD1A44D` (`supplier_id`),
  KEY `FKA6A4DD66DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKA6A4DD664CD1A44D` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `FKA6A4DD669FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKA6A4DD66DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_contacts`
--

LOCK TABLES `supplier_contacts` WRITE;
/*!40000 ALTER TABLE `supplier_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_payment_bills`
--

DROP TABLE IF EXISTS `supplier_payment_bills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_payment_bills` (
  `supplier_payment_id` bigint(20) NOT NULL,
  `bill_id` bigint(20) NOT NULL,
  KEY `FKC0E18CA0C80D2F6E` (`supplier_payment_id`),
  KEY `FKC0E18CA014389CD9` (`bill_id`),
  CONSTRAINT `FKC0E18CA014389CD9` FOREIGN KEY (`bill_id`) REFERENCES `supplier_bills` (`id`),
  CONSTRAINT `FKC0E18CA0C80D2F6E` FOREIGN KEY (`supplier_payment_id`) REFERENCES `supplier_payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_payment_bills`
--

LOCK TABLES `supplier_payment_bills` WRITE;
/*!40000 ALTER TABLE `supplier_payment_bills` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_payment_bills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_payments`
--

DROP TABLE IF EXISTS `supplier_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_payments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1AE18C409FB70779` (`createdBy_id`),
  KEY `FK1AE18C40E5484787` (`payment_id`),
  KEY `FK1AE18C404CD1A44D` (`supplier_id`),
  KEY `FK1AE18C40DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FK1AE18C404CD1A44D` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `FK1AE18C409FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK1AE18C40DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK1AE18C40E5484787` FOREIGN KEY (`payment_id`) REFERENCES `payments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_payments`
--

LOCK TABLES `supplier_payments` WRITE;
/*!40000 ALTER TABLE `supplier_payments` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `IVACondition` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `balanceActivity` double DEFAULT NULL,
  `balanceBilling` double DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `credit` double DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nextel` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `rubro` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `supplierType` int(11) NOT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFEA340279FB70779` (`createdBy_id`),
  KEY `FKFEA34027DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKFEA340279FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKFEA34027DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfers`
--

DROP TABLE IF EXISTS `transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `dateRecibed` date DEFAULT NULL,
  `dateTransferred` date DEFAULT NULL,
  `destinationAccount` varchar(255) DEFAULT NULL,
  `sourceAccount` varchar(255) DEFAULT NULL,
  `transferType` int(11) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `destinationBank_id` bigint(20) DEFAULT NULL,
  `sourceBank_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3EBE45E89FB70779` (`createdBy_id`),
  KEY `FK3EBE45E8B66F6352` (`sourceBank_id`),
  KEY `FK3EBE45E8DB209962` (`lastModifiedBy_id`),
  KEY `FK3EBE45E85CBABFFF` (`destinationBank_id`),
  CONSTRAINT `FK3EBE45E85CBABFFF` FOREIGN KEY (`destinationBank_id`) REFERENCES `banks` (`id`),
  CONSTRAINT `FK3EBE45E89FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK3EBE45E8B66F6352` FOREIGN KEY (`sourceBank_id`) REFERENCES `banks` (`id`),
  CONSTRAINT `FK3EBE45E8DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfers`
--

LOCK TABLES `transfers` WRITE;
/*!40000 ALTER TABLE `transfers` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `user_id` bigint(20) NOT NULL,
  `role_permission_id` bigint(20) NOT NULL,
  KEY `FK30BA72C31F5FF16D` (`user_id`),
  KEY `FK30BA72C39235743A` (`role_permission_id`),
  CONSTRAINT `FK30BA72C31F5FF16D` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK30BA72C39235743A` FOREIGN KEY (`role_permission_id`) REFERENCES `role_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK143BF46A7A352D8D` (`role_id`),
  KEY `FK143BF46A1F5FF16D` (`user_id`),
  CONSTRAINT `FK143BF46A1F5FF16D` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK143BF46A7A352D8D` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(3,1),(3,2),(3,3),(3,4),(3,5);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` tinyint(1) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,0,'','','__anonymous__'),(2,0,'','32c4e84e3dd5ce344e920d02eba468d8e53c8b83','admin'),(3,0,'','6ed32edf4e92ab3c0a4dc6f90242953c344051ad','sergio');
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

-- Dump completed on 2015-06-05 19:45:10
