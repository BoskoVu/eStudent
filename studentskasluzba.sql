/*
SQLyog Community v12.4.3 (32 bit)
MySQL - 10.4.25-MariaDB : Database - studentskasluzba
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentskasluzba` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `studentskasluzba`;

/*Table structure for table `katedra` */

DROP TABLE IF EXISTS `katedra`;

CREATE TABLE `katedra` (
  `KatedraID` int(11) NOT NULL AUTO_INCREMENT,
  `NazivKatedre` varchar(50) NOT NULL,
  PRIMARY KEY (`KatedraID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `katedra` */

insert  into `katedra`(`KatedraID`,`NazivKatedre`) values 
(1,'Silab');

/*Table structure for table `polaganje` */

DROP TABLE IF EXISTS `polaganje`;

CREATE TABLE `polaganje` (
  `BrojIndeksa` varchar(9) NOT NULL,
  `TerminID` int(11) NOT NULL,
  `Ocena` int(11) NOT NULL,
  `Bodovi` int(11) DEFAULT NULL,
  KEY `terminpolaganje` (`TerminID`),
  KEY `studentpolaganje` (`BrojIndeksa`),
  CONSTRAINT `studentpolaganje` FOREIGN KEY (`BrojIndeksa`) REFERENCES `student` (`BrojIndeksa`),
  CONSTRAINT `terminpolaganje` FOREIGN KEY (`TerminID`) REFERENCES `termin` (`TerminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `polaganje` */

insert  into `polaganje`(`BrojIndeksa`,`TerminID`,`Ocena`,`Bodovi`) values 
('0171/2017',2,0,0),
('0171/2017',3,0,0),
('0171/2017',1,8,71);

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `PredmetID` int(11) NOT NULL AUTO_INCREMENT,
  `ESPB` int(11) NOT NULL,
  `NazivPredmeta` varchar(50) NOT NULL,
  `KatedraID` int(11) NOT NULL,
  PRIMARY KEY (`PredmetID`),
  KEY `predmetkatedra` (`KatedraID`),
  CONSTRAINT `predmetkatedra` FOREIGN KEY (`KatedraID`) REFERENCES `katedra` (`KatedraID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `predmet` */

insert  into `predmet`(`PredmetID`,`ESPB`,`NazivPredmeta`,`KatedraID`) values 
(1,6,'Projektovanje softvera',1),
(2,6,'Programiranje 1',1);

/*Table structure for table `rok` */

DROP TABLE IF EXISTS `rok`;

CREATE TABLE `rok` (
  `RokID` int(11) NOT NULL AUTO_INCREMENT,
  `NazivRoka` varchar(20) NOT NULL,
  `Pocetak` date NOT NULL,
  `Zavrsetak` date NOT NULL,
  PRIMARY KEY (`RokID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `rok` */

insert  into `rok`(`RokID`,`NazivRoka`,`Pocetak`,`Zavrsetak`) values 
(1,'Januarski','2024-01-12','2024-01-26'),
(2,'Februarski','2024-02-02','2024-02-21');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `BrojIndeksa` varchar(9) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `DatumRodjenja` date NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`BrojIndeksa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

insert  into `student`(`BrojIndeksa`,`Ime`,`Prezime`,`DatumRodjenja`,`Username`,`Password`) values 
('0171/2017','Pera','Peric','1998-03-12','peraperic@student.fon.bg.ac.rs','peraperic');

/*Table structure for table `termin` */

DROP TABLE IF EXISTS `termin`;

CREATE TABLE `termin` (
  `TerminID` int(11) NOT NULL AUTO_INCREMENT,
  `PredmetID` int(11) NOT NULL,
  `RokID` int(11) NOT NULL,
  `Datum` datetime NOT NULL,
  PRIMARY KEY (`TerminID`),
  KEY `rokfk` (`RokID`),
  KEY `predmetfk` (`PredmetID`),
  CONSTRAINT `predmetfk` FOREIGN KEY (`PredmetID`) REFERENCES `predmet` (`PredmetID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rokfk` FOREIGN KEY (`RokID`) REFERENCES `rok` (`RokID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `termin` */

insert  into `termin`(`TerminID`,`PredmetID`,`RokID`,`Datum`) values 
(1,1,1,'2024-01-12 12:00:00'),
(2,2,1,'2024-01-24 15:00:00'),
(3,1,2,'2024-02-09 15:40:50'),
(4,2,2,'2024-02-10 15:00:00'),
(5,1,1,'2024-01-25 00:00:00');

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `ZaposleniID` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `DatumRodjenja` date NOT NULL,
  `Status` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `KatedraID` int(11) NOT NULL,
  PRIMARY KEY (`ZaposleniID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`ZaposleniID`,`Ime`,`Prezime`,`DatumRodjenja`,`Status`,`Username`,`Password`,`KatedraID`) values 
(1,'Mika','Mikic','1974-07-17','Redovan profesor','mikamikic@zaposleni.fon.bg.ac.rs','mikamikic',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
