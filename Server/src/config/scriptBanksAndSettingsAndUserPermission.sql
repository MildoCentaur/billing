# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.5.25)
# Database: billing
# Generation Time: 2015-09-08 18:29:38 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table banks
# ------------------------------------------------------------

DROP TABLE IF EXISTS `banks`;

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



# Dump of table settings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `settings`;

CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;

INSERT INTO `settings` (`id`, `category`, `description`, `label`, `name`, `value`)
VALUES
	(1,'Facturación','Número de facturas A','Número de facturas A','nro-factura-a','0000'),
	(2,'Facturación','Número de remitos A','Número de remitos A','nro-remito-a','0000'),
	(3,'Facturación','Número de facturas B','Número de facturas B','nro-factura-b','0000'),
	(4,'Facturación','Número de remitos B','Número de remitos B','nro-remito-b','0000'),
	(5,'Impresoras','Impresora facturas A','Impresora facturas A','impresora-factura-a','Brother'),
	(6,'Impresoras','Impresora facturas B','Impresora facturas B','impresora-factura-b','Brother'),
	(7,'Impresoras','Impresora de recibos','Recibos','impresora-recibos','Brother'),
	(8,'Impresoras','Dirección de la ticketeadora','URL Ticketeadora','impresora-ticket-url',NULL),
	(9,'Impresoras','Puerto de la ticketeadora','PORT Ticketeadora','impresora-ticket-port',NULL);

/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_permission
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_permission`;

CREATE TABLE `user_permission` (
  `user_id` bigint(20) NOT NULL,
  `role_permission_id` bigint(20) NOT NULL,
  KEY `FK30BA72C31F5FF16D` (`user_id`),
  KEY `FK30BA72C39235743A` (`role_permission_id`),
  CONSTRAINT `FK30BA72C31F5FF16D` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK30BA72C39235743A` FOREIGN KEY (`role_permission_id`) REFERENCES `role_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
