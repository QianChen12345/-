/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.29 : Database - items
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`items` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `items`;

/*Table structure for table `orderlist` */

DROP TABLE IF EXISTS `orderlist`;

CREATE TABLE `orderlist` (
  `ordernum` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `account` char(11) COLLATE utf8mb4_bin NOT NULL,
  `price` float NOT NULL,
  `goodsid` char(15) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `orderlist` */

insert  into `orderlist`(`ordernum`,`account`,`price`,`goodsid`) values ('20230418110957281877','11',0.11,'600008933'),('20230418111039002107','11',0.11,'600008933'),('20230418111043380576','11',0.11,'600008933'),('20230418111049004810','11',0.11,'600008933'),('20230418111233318240','11',0.11,'600008933'),('20230418111313251560','11',0.11,'600008933'),('20230418111316478641','11',0.11,'600008933'),('20230418111342572530','11',0.11,'600008933'),('20230418111432186835','11',0.11,'600008933'),('20230418112034321605','11',0.11,'600008933'),('20230418112041646780','11',2,'416807937'),('20230418112129131128','11',0.11,'600008933'),('20230418112350203116','11',2,'416807937'),('20230418135343421332','11',0.11,'600008933'),('20230418135819854731','11',0.11,'600008933'),('20230418140044376447','11',0.11,'600008933'),('20230418140139634133','11',2,'416807937'),('20230418140500608105','11',0.11,'600008933'),('20230418140835666871','11',2,'416807937'),('20230418141631203126','11',0.11,'600008933'),('20230418141717473666','11',0.11,'600008933'),('20230418141730301117','11',0.11,'600008933'),('20230418141947217350','11',0.11,'600008933'),('20230418141955748265','11',0.11,'600008933'),('20230418143213466134','11',2,'416807937'),('20230418143849257707','11',0.11,'600008933'),('20230418155604112780','11',2,'416807937'),('20230418155633013314','11',2,'416807937'),('20230418155702535285','11',0.11,'600008933'),('20230418155739220355','11',2,'416807937'),('20230418155908315237','11',2,'416807937'),('20230418155934005523','11',0.11,'600008933'),('20230418160000848470','11',0.11,'600008933'),('20230418160101284756','11',2,'416807937'),('20230418160127050146','11',0.11,'600008933'),('20230418161156150454','11',0.11,'600008933'),('20230418191356470107','11',0.11,'600008933'),('20230418191504025013','11',0.11,'600008933'),('20230418191521582035','11',0.11,'600008933'),('20230418193316347505','11',0.11,'600008933'),('20230418201902586407','11',0.11,'600008933');

/*Table structure for table `totalgoods` */

DROP TABLE IF EXISTS `totalgoods`;

CREATE TABLE `totalgoods` (
  `itemimage` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `itemtype` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '其他',
  `itemid` char(10) COLLATE utf8mb4_bin NOT NULL DEFAULT '000000',
  `itemname` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '未添加名称',
  `itemprice` float NOT NULL DEFAULT '0',
  `itemdesc` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '无',
  `itemaddress` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  KEY `itemid` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `totalgoods` */

insert  into `totalgoods`(`itemimage`,`itemtype`,`itemid`,`itemname`,`itemprice`,`itemdesc`,`itemaddress`) values ('http://tmp/lCaFBxHprglN66adeaa5f9d37eeddb788832be5e4585.jpg-http://tmp/3MG6ecRMyPufa0097fe12971f584d98550014a9881a8.webp-http://tmp/D6lbckzeXdgC704c686a1732a756ff860b148216f210.webp','书籍','603588649','红楼梦',29.9,'买回来只有三个月，很新','学校一号教学楼下'),('http://tmp/yobDwau6pB4R8562fc6fb151dc8ebb6056118566f7d2.webp','其他','606113672','草稿纸',5.9,'100张，纸张很舒服','学校一号食堂门口'),('http://tmp/1WW2B662X6LCab5a89c549ffd9030d1b67c091fa7957.webp','电子产品','464431443','手表',99.9,'电子表，有哒哒声','学校一号教学楼下'),('http://tmp/H5fz4PhU1C5Af49c5e56565c44793d6390869b857d94.webp','电子产品','173709664','平板电脑',999,'插头有一点坏，但不影响','学校东门'),('http://tmp/PZ4MP1YjgS1j8de99ee32210fa94e50581472d1ffbc2.webp','文具','651851368','黑笔',4,'想要就拍,2支','学校东门'),('http://tmp/hpxXj5cSQduy12b7773698f108fc44eef371b0034248.webp','生活用品','383726345','衣架',15,'5个衣架','学校东门'),('http://tmp/h7hTtKShbJPD88dfc903c43688322abe711c7cf4d67e.webp','衣物','563841621','T恤',50,'白色，很干净','学校西门'),('http://tmp/MTg7A9b3MUR7f6032b396ca1a1769dbf84438d9070ea.webp','化妆品','229282750','防晒霜',89,'只用了五分之一','学校西门'),('http://tmp/BMWyobja5hRCdcc70b1ce3656552f7ea696cfa60b099.webp','首饰','941106931','耳环',120,'毫无瑕疵','学校西门'),('http://tmp/oAy9dwNcGBva3c13c84e18096237fb12a0959511fc90.webp','手机','802691443','小米6',580,'便宜卖掉','学校南门'),('http://tmp/Vc1Z6NA3d5ldf055d722662e8f53b0b47ce28dbe6c24.webp','鞋子','335920418','耐克鞋',199,'鞋边有点脏','学校南门');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `account` char(10) COLLATE utf8mb4_bin NOT NULL DEFAULT '123456',
  `password` char(10) COLLATE utf8mb4_bin NOT NULL DEFAULT '123456'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `user` */

insert  into `user`(`account`,`password`) values ('11','11'),('66','1'),('111','222'),('1','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
