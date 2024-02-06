/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.28-MariaDB : Database - assignment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`assignment` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `assignment`;

/*Table structure for table `assign` */

DROP TABLE IF EXISTS `assign`;

CREATE TABLE `assign` (
  `Empno` int(11) NOT NULL,
  `Ename` varchar(20) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `Hiredate` date DEFAULT NULL,
  `sal` double DEFAULT NULL,
  `comm` double DEFAULT NULL,
  `Deptno` int(11) DEFAULT NULL,
  PRIMARY KEY (`Empno`),
  KEY `Deptno` (`Deptno`),
  CONSTRAINT `assign_ibfk_1` FOREIGN KEY (`Deptno`) REFERENCES `depttable` (`Deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `assign` */

insert  into `assign`(`Empno`,`Ename`,`job`,`mgr`,`Hiredate`,`sal`,`comm`,`Deptno`) values 
(7369,'Smith','Clerk',7902,'1980-12-17',900,NULL,20),
(7499,'Allen','Salesman',7698,'1981-02-20',1600,300,30),
(7521,'Ward','Salesman',7698,'1981-02-22',1250,500,30),
(7566,'Jones','Manager',7839,'1981-04-02',2975,NULL,20),
(7654,'Martin','Salesman',7698,'1981-09-28',1250,1400,30),
(7698,'Blake','Manager',7839,'1981-05-01',2850,NULL,30),
(7782,'Clark','Manager',7839,'1981-06-09',2450,NULL,10),
(7788,'Scott','Analyst',7566,'1987-06-11',3000,NULL,20),
(7839,'King','President',NULL,'1981-08-09',1500,0,10),
(7876,'Adams','Clerk',7788,'1987-07-13',1100,NULL,20),
(7900,'James','Clerk',7698,'1981-03-12',950,NULL,30),
(7902,'Ford','Analyst',7566,'1981-03-12',3000,NULL,20),
(7934,'Miller','Clerk',7782,'1982-01-23',1300,NULL,10);

/*Table structure for table `depttable` */

DROP TABLE IF EXISTS `depttable`;

CREATE TABLE `depttable` (
  `Deptno` int(11) NOT NULL,
  `Dname` varchar(14) DEFAULT NULL,
  `loc` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`Deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `depttable` */

insert  into `depttable`(`Deptno`,`Dname`,`loc`) values 
(10,'Accounting','Newyork'),
(20,'Research','Dallas'),
(30,'Sales','Chicago'),
(40,'Operations','Boston');

/*Table structure for table `emplogtable` */

DROP TABLE IF EXISTS `emplogtable`;

CREATE TABLE `emplogtable` (
  `Emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_date` date DEFAULT NULL,
  `new_salary` int(11) DEFAULT NULL,
  `Action` varchar(20) DEFAULT NULL,
  KEY `Emp_id` (`Emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `emplogtable` */

insert  into `emplogtable`(`Emp_id`,`log_date`,`new_salary`,`Action`) values 
(1,NULL,800,NULL);

/*Table structure for table `studenttable` */

DROP TABLE IF EXISTS `studenttable`;

CREATE TABLE `studenttable` (
  `Rno` int(11) NOT NULL,
  `Sname` varchar(40) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Pin` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Rno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `studenttable` */

/* Trigger structure for table `assign` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `NewSalary` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `NewSalary` BEFORE UPDATE ON `assign` FOR EACH ROW BEGIN
	insert into emplogtable(new_salary) value (old.sal);
    END */$$


DELIMITER ;

/* Procedure structure for procedure `dno` */

/*!50003 DROP PROCEDURE IF EXISTS  `dno` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `dno`(in Deptno int)
BEGIN
		select Ename , Dname from assign a join depttable c on a.Deptno=c.Deptno;
	END */$$
DELIMITER ;

/*Table structure for table `v1` */

DROP TABLE IF EXISTS `v1`;

/*!50001 DROP VIEW IF EXISTS `v1` */;
/*!50001 DROP TABLE IF EXISTS `v1` */;

/*!50001 CREATE TABLE  `v1`(
 `ename` varchar(20) ,
 `job` varchar(20) ,
 `dname` varchar(14) ,
 `loc` varchar(13) 
)*/;

/*View structure for view v1 */

/*!50001 DROP TABLE IF EXISTS `v1` */;
/*!50001 DROP VIEW IF EXISTS `v1` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v1` AS (select `a`.`Ename` AS `ename`,`a`.`job` AS `job`,`d`.`Dname` AS `dname`,`d`.`loc` AS `loc` from (`assign` `a` join `depttable` `d` on(`a`.`Deptno` = `d`.`Deptno`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
