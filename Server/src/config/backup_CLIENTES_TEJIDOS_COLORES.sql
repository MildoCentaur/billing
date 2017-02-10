# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.25)
# Database: billing
# Generation Time: 2015-01-06 03:06:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table client_contacts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `client_contacts`;

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
  CONSTRAINT `FK755DB247DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK755DB247225B58AD` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FK755DB2479FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table clients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clients`;

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
  CONSTRAINT `FK334B8608DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK334B86089FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;

INSERT INTO `clients` (`id`, `createdDate`, `lastModifiedDate`, `deleted`, `IVACondition`, `address`, `addressDelivery`, `balanceActivity`, `balanceBilling`, `clientType`, `comments`, `country`, `credit`, `creditInCheques`, `cuit`, `email`, `fax`, `localidad`, `name`, `nextel`, `nickname`, `phone`, `postalCode`, `state`, `createdBy_id`, `lastModifiedBy_id`)
VALUES
	(1,NULL,NULL,0,6,'Amenabar 844 6p','Blanco Encalada 4435 4b',0,0,1,'','Argentina',0,0,'20308959970','DUMMY@MAIL.com','3456789','Buenos Aires','Alejandro Mildiner Cliente','23456789','Mildo','234567890-','1435','Buenos Aires',NULL,NULL);

/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table colors
# ------------------------------------------------------------

DROP TABLE IF EXISTS `colors`;

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
  CONSTRAINT `FKAF3EBD70DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKAF3EBD709FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;

INSERT INTO `colors` (`id`, `createdDate`, `lastModifiedDate`, `deleted`, `code`, `comments`, `coordinate`, `method`, `name`, `type`, `createdBy_id`, `lastModifiedBy_id`)
VALUES
	(1,'2014-12-31 15:24:49','2015-01-05 19:55:36',0,'9999','','ffffff','Directo','Blanco',0,2,2),
	(2,'2014-12-31 15:25:08','2015-01-05 20:14:49',0,'0000','','000000','Directo','Negro',2,2,2),
	(3,'2014-12-31 15:26:16','2015-01-05 19:22:23',0,'2525','','0000b8','Reactivo','Azul Marino',3,2,2);

/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table fabrics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fabrics`;

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
  CONSTRAINT `FKBEDC186EDB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKBEDC186E9FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `fabrics` WRITE;
/*!40000 ALTER TABLE `fabrics` DISABLE KEYS */;

INSERT INTO `fabrics` (`id`, `createdDate`, `lastModifiedDate`, `deleted`, `code`, `comments`, `cottonPercent`, `cuello`, `diametro`, `galga`, `longname`, `lycraPercent`, `mayas`, `mercerizado`, `pasadas`, `polyesterPercent`, `puno`, `rinde`, `shortname`, `width`, `createdBy_id`, `lastModifiedBy_id`)
VALUES
	(1,'2014-12-31 15:14:41','2015-01-05 20:14:49',0,'3017','',100,0,1,20,'Jersey Algodon mercerizdo ',0,18,0,18,0,0,3,'Jersey',1,2,2),
	(2,NULL,'2015-01-05 19:55:37',0,'8080','',100,1,100,10,'Cuellos y Tiras Algodon doble montura ',0,18,0,18,0,0,100,'Cuellos y Tiras',0.45,NULL,2),
	(3,NULL,'2015-01-05 19:55:36',0,'2430','dasdadasddsadas',100,0,0,20,'Ribb Algodon ',0,18,0,18,0,1,2.5,'Ribb',1,NULL,2);

/*!40000 ALTER TABLE `fabrics` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table fibers
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fibers`;

CREATE TABLE `fibers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL,
  `percentage` double NOT NULL,
  `supplierName` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `createdBy_id` bigint(20) DEFAULT NULL,
  `lastModifiedBy_id` bigint(20) DEFAULT NULL,
  `fabric_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB40409079FB70779` (`createdBy_id`),
  KEY `FKB40409071091726D` (`fabric_id`),
  KEY `FKB4040907DB209962` (`lastModifiedBy_id`),
  CONSTRAINT `FKB4040907DB209962` FOREIGN KEY (`lastModifiedBy_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKB40409071091726D` FOREIGN KEY (`fabric_id`) REFERENCES `fabrics` (`id`),
  CONSTRAINT `FKB40409079FB70779` FOREIGN KEY (`createdBy_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `fibers` WRITE;
/*!40000 ALTER TABLE `fibers` DISABLE KEYS */;

INSERT INTO `fibers` (`id`, `createdDate`, `lastModifiedDate`, `deleted`, `percentage`, `supplierName`, `titulo`, `createdBy_id`, `lastModifiedBy_id`, `fabric_id`)
VALUES
	(1,NULL,NULL,0,0,'Allal','24/1',NULL,NULL,1),
	(2,NULL,NULL,0,0,'Allal','20/1',NULL,NULL,1),
	(3,NULL,NULL,0,0,'Allal','10/1',NULL,NULL,NULL),
	(4,NULL,NULL,0,0,'Allal','24/1',NULL,NULL,NULL),
	(5,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL),
	(6,NULL,NULL,0,0,'Allal','24/1',NULL,NULL,NULL),
	(7,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL),
	(8,NULL,NULL,0,0,'','',NULL,NULL,NULL),
	(9,NULL,NULL,0,0,'Allal','24/1',NULL,NULL,NULL),
	(10,NULL,NULL,0,0,NULL,NULL,NULL,NULL,3),
	(11,NULL,NULL,0,0,'','',NULL,NULL,3),
	(12,NULL,NULL,0,0,'','',NULL,NULL,3),
	(13,NULL,NULL,0,0,'Allal','24/1',NULL,NULL,3);

/*!40000 ALTER TABLE `fibers` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
