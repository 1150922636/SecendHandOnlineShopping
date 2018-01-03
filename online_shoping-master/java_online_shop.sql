-- Adminer 4.3.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

USE `java_online_shop`;

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(100) NOT NULL,
  `cat_desc` varchar(200) NOT NULL,
  PRIMARY KEY (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `category` (`cat_id`, `cat_name`, `cat_desc`) VALUES
(1,	'Mobile',	'Android Mobile'),
(2,	'Laptop',	'Laptop'),
(3,	'Desktop',	'computers');

DROP TABLE IF EXISTS `delivery_cost`;
CREATE TABLE `delivery_cost` (
  `delivery_id` int(11) NOT NULL AUTO_INCREMENT,
  `zone_name` varchar(50) NOT NULL,
  `delivery_cost` double NOT NULL,
  PRIMARY KEY (`delivery_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_det_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `order_date` date NOT NULL,
  `order_qty` int(11) NOT NULL,
  `total_price` double NOT NULL,
  `order_status` varchar(45) NOT NULL DEFAULT '"N"',
  `delivery_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `delivery_id` (`delivery_id`),
  KEY `order_det_id` (`order_det_id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`delivery_id`) REFERENCES `delivery_cost` (`delivery_id`),
  CONSTRAINT `order_ibfk_4` FOREIGN KEY (`order_det_id`) REFERENCES `order_detail` (`order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `prod_id` int(11) NOT NULL,
  `prod_qty` int(11) NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `order_id` (`order_id`),
  KEY `prod_id` (`prod_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`prod_id`) REFERENCES `product` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_cat_id` int(10) unsigned NOT NULL,
  `prod_name` varchar(45) NOT NULL,
  `prod_qty` int(11) NOT NULL,
  `prod_price` double NOT NULL,
  `prod_url` varchar(45) NOT NULL,
  `prod_desc` text NOT NULL,
  PRIMARY KEY (`prod_id`),
  KEY `sub_cat_id` (`sub_cat_id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`sub_cat_id`) REFERENCES `sub_category` (`sub_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `product` (`prod_id`, `sub_cat_id`, `prod_name`, `prod_qty`, `prod_price`, `prod_url`, `prod_desc`) VALUES
(1,	2,	'LG G4',	10,	345.99,	's-l1600.jpg',	'LG G4 ...'),
(2,	1,	'IPHONE 4S',	10,	59.99,	'iphone_4s.jpg',	'Iphone 4s'),
(3,	1,	'IPHONE S4 black',	20,	65.99,	'530201374810pm-635-iphone-4s.png',	'IPHONE S4 black'),
(4,	10,	'HP 8200 Elite Desktop',	5,	411.01,	's-lhp-desktop.jpg',	'HP 8200 Elite Desktop'),
(5,	10,	'Dell Optiplex 7010',	10,	129.99,	's-dell-desktop.jpg',	'Brand:	\r\nDell\r\nType:	PC	Model:	\r\nOptiplex 7010'),
(6,	2,	'NEW Lenovo G500 15.6\"',	3,	895.53,	's-l500.jpg',	'Type:	Notebook/ Laptop\r\nProcessor Speed:	2.60 GHz	Brand:	\r\nLenovo');

DROP TABLE IF EXISTS `sub_category`;
CREATE TABLE `sub_category` (
  `sub_cat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cat_id` int(10) unsigned NOT NULL,
  `sub_cat_name` varchar(45) NOT NULL,
  `sub_cat_desc` varchar(200) NOT NULL,
  PRIMARY KEY (`sub_cat_id`),
  KEY `cat_id` (`cat_id`),
  CONSTRAINT `sub_category_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `sub_category` (`sub_cat_id`, `cat_id`, `sub_cat_name`, `sub_cat_desc`) VALUES
(1,	1,	'Apple',	'Test'),
(2,	2,	'Dell',	'Dell'),
(3,	1,	'LG',	'LG phones'),
(4,	2,	'Apple',	'Apple macs'),
(5,	1,	'Apple',	'Apple macs'),
(6,	1,	'LG',	'LG'),
(7,	1,	'LG',	'LG'),
(8,	1,	'LG',	'LG'),
(9,	1,	'LG',	'LG'),
(10,	3,	'HP',	'Hewlett-Packard'),
(11,	3,	'DELL',	'DELL computers'),
(12,	2,	'Acer',	'Acer laptop'),
(13,	2,	'Lenovo',	'Lenovo');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `user_fname` varchar(50) NOT NULL,
  `user_lname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `reg_date` date NOT NULL,
  `contact_num` varchar(11) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` (`user_id`, `username`, `pass`, `user_fname`, `user_lname`, `email`, `reg_date`, `contact_num`, `role_id`) VALUES
(1,	'gagi',	'123456',	'gagi',	'shmagi',	'gagi.test@gmai.com',	'2017-06-27',	'0545555555',	1);

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `user_role` (`role_id`, `role_name`) VALUES
(1,	'Administrator'),
(2,	'Editor'),
(3,	'User');

-- 2017-07-08 17:22:12
