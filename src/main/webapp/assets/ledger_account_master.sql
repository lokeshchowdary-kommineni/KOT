/*
SQLyog Community v12.3.2 (32 bit)
MySQL - 5.1.36-community-log : Database - accounting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`accounting` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `accounting`;

/*Table structure for table `ledger_account_master` */

DROP TABLE IF EXISTS `ledger_account_master`;

CREATE TABLE `ledger_account_master` (
  `id_ledger` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `b_type` varchar(20) DEFAULT NULL,
  `cell_no` varchar(10) DEFAULT NULL,
  `name_of_ledger` varchar(50) DEFAULT NULL,
  `opening_amount` double DEFAULT NULL,
  `opening_date` date DEFAULT NULL,
  `opening_type` varchar(10) DEFAULT NULL,
  `pan_no` varchar(10) DEFAULT NULL,
  `predefined` int(11) DEFAULT NULL,
  `r_id` varchar(20) DEFAULT NULL,
  `tin` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `under_group` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_ledger`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `ledger_account_master` */

insert  into `ledger_account_master`(`id_ledger`,`address`,`b_type`,`cell_no`,`name_of_ledger`,`opening_amount`,`opening_date`,`opening_type`,`pan_no`,`predefined`,`r_id`,`tin`,`type`,`under_group`) values 
(1,'',NULL,'','CASH',0,'2017-04-20','DR','',1,NULL,NULL,NULL,15),
(2,'',NULL,'','PROFIT & LOSS A/C',0,'2017-04-20','DR','',1,NULL,NULL,NULL,0),
(3,'',NULL,'','SALES ROUNDING',0,'2017-04-20','DR','',1,NULL,NULL,NULL,27),
(4,'',NULL,'','PURCHASE ROUNDING',0,'2017-04-20','DR','',1,NULL,NULL,NULL,22),
(5,'',NULL,'','PURCHASE EXPENSE',0,'2017-04-20','DR','',1,NULL,NULL,NULL,24),
(6,'','customer','9874563210','CASH BUYER',NULL,'2017-04-20','DR','',NULL,'10','','Buyer',21),
(7,'','mediator','9865985623','CASH MEDIATOR',NULL,'2017-04-20','DR','',NULL,'11','','Buyer',21),
(8,'',NULL,'','CASH SUPPLIER',NULL,'2017-04-20','CR','',1,'4','','Supplier',13),
(9,'',NULL,'','ITC OF CGST',0,'2017-04-20','DR','',1,NULL,NULL,NULL,11),
(10,'',NULL,'','ITC OF VAT',0,'2017-04-20','DR','',1,NULL,NULL,NULL,11),
(11,'',NULL,'','OTD OF CGST',0,'2017-04-20','DR','',1,NULL,NULL,NULL,11),
(12,'',NULL,'','OTD OF VAT',0,'2017-04-20','DR','',1,NULL,NULL,NULL,11),
(13,'',NULL,'','ITC OF IGST',0,'2017-04-20','DR','',1,NULL,NULL,NULL,11),
(14,'',NULL,'','OTD OF IGST',0,'2017-04-20','DR','',1,NULL,NULL,NULL,11),
(15,'',NULL,'','PRODUCTION COST',0,'2017-04-20','DR','',1,NULL,NULL,NULL,23),
(16,'',NULL,'','MCA EXPENSES',0,'2017-04-20','DR','',1,NULL,NULL,NULL,24),
(17,'',NULL,'','PURCHASE OF EXEMPTED GOODS',0,'2017-04-20','DR','',1,NULL,NULL,NULL,22),
(18,'',NULL,'','SALES OF EXEMPTED GOODS',0,'2017-04-20','DR','',1,NULL,NULL,NULL,25);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
