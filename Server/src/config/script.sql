-- initialization queries

INSERT INTO `role` (`id`, `description`, `name`)
VALUES
	(1,'ROLE_ADMIN','Producción'),
	(2,'ROLE_ADMIN','Productos'),
	(3,'ROLE_ADMIN','Cobranzas'),
	(4,'ROLE_ADMIN','Ventas'),
	(5,'ROLE_ADMIN','Tecnico'),
	(6,NULL,'Clientes'),
	(7,NULL,'Proveedores'),
	(8,NULL,'Impuestos'),
	(9,NULL,'Caja'),
	(10,NULL,'Facturación');
INSERT INTO `role_permission` (`id`, `name`, `role_id`)
VALUES
	(1,'ADMIN',1),
	(2,'USER',1),
	(3,'ADMIN',2),
	(4,'USER',2),
	(5,'ADMIN',3),
	(6,'USER',3),
	(7,'ADMIN',4),
	(8,'USER',4),
	(9,'ADMIN',5),
	(10,'USER',5),
	(11,'ADMIN',6),
	(12,'USER',6),
	(13,'ADMIN',7),
	(14,'USER',7),
	(15,'ADMIN',8),
	(16,'USER',8),
	(17,'ADMIN',9),
	(18,'USER',9),
	(19,'ADMIN',10),
	(20,'USER',10);
insert into users (`deleted`,`email`,`password`,`username`) values (1,'','','__anonymous__');
insert into users (`deleted`,`email`,`password`,`username`) values (0,'',SHA1('mildo'),'admin');


insert into list_price (`createdDate`, `lastModifiedDate`,`deleted`,`active`,`date`,`name`,`createdBy_id`,`lastModifiedBy_id`) values (CURDATE(),CURDATE(),0,1,CURDATE(),'Lista de Precios General',(select id from users where username='__anonymous__'),(select id from users where username='__anonymous__'));
insert into settings (category,name,value) values ('facturacion','nro-factura-a','0000');
insert into settings (category,name,value) values ('facturacion','nro-remito-a','0000');
insert into settings (category,name,value) values ('facturacion','nro-factura-b','0000');
insert into settings (category,name,value) values ('facturacion','nro-remito-b','0000');
