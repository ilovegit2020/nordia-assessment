--CREATE DATABASE `batchdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

DROP TABLE IF EXISTS `batchdb`.`paymentidentification`;
CREATE TABLE  `batchdb`.`paymentidentification` (
  `instrId` varchar(50) NOT NULL ,
  `endToEndId` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`instrId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;