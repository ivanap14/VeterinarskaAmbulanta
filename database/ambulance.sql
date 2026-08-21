/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - ambulance
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ambulance` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ambulance`;

/*Table structure for table `animal` */

DROP TABLE IF EXISTS `animal`;

CREATE TABLE `animal` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `species` varchar(20) NOT NULL,
  `yearOfBirth` int(50) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `idOwner` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idOwner` (`idOwner`),
  CONSTRAINT `animal_ibfk_1` FOREIGN KEY (`idOwner`) REFERENCES `owner` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `animal` */

insert  into `animal`(`id`,`name`,`species`,`yearOfBirth`,`gender`,`idOwner`) values 
(1,'Bobi','PAS',2017,'MUSKI',2),
(2,'Garfild','MACKA',2020,'MUSKI',1),
(3,'Lili - papagaj','PTICA',2015,'ZENSKI',2),
(4,'Dona','MACKA',2014,'ZENSKI',6),
(5,'Leo','ZEC',2020,'MUSKI',3),
(6,'Bela','MACKA',2015,'ZENSKI',4),
(7,'Donatelo - kornjača','VODOZEMAC',2011,'MUSKI',1),
(8,'Sendi - hrčak','GLODAR',2021,'ZENSKI',3),
(9,'Ogi','PAS',2016,'MUSKI',4),
(10,'Bleki','PAS',2018,'MUSKI',1),
(11,'Hugo - kanarinac','PTICA',2018,'MUSKI',5),
(12,'Roki','PAS',2018,'MUSKI',6),
(13,'Nora','PTICA',2021,'ZENSKI',3),
(14,'Sima','GLODAR',2020,'MUSKI',7),
(15,'Bruno','PAS',2019,'MUSKI',9),
(16,'Lola','MACKA',2018,'ZENSKI',11),
(17,'Tom','MACKA',2015,'MUSKI',7),
(18,'Ema','GLODAR',2021,'ZENSKI',5),
(19,'Gari','ZEC',2018,'MUSKI',8),
(20,'Žak','PTICA',2018,'MUSKI',9),
(21,'Leo','ZEC',2021,'MUSKI',10),
(22,'Tara','PAS',2017,'ZENSKI',1);

/*Table structure for table `intervention` */

DROP TABLE IF EXISTS `intervention`;

CREATE TABLE `intervention` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `notes` varchar(255) NOT NULL,
  `discountForLoyalty` int(11) NOT NULL,
  `discountForNumberOfServices` int(11) NOT NULL,
  `totalAmountWithoutDiscount` double NOT NULL,
  `totalAmountWithDiscount` double NOT NULL,
  `idVeterinarian` bigint(20) unsigned NOT NULL,
  `idAnimal` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idVeterinarian` (`idVeterinarian`),
  KEY `idAnimal` (`idAnimal`),
  CONSTRAINT `intervention_ibfk_1` FOREIGN KEY (`idVeterinarian`) REFERENCES `veterinarian` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `intervention_ibfk_2` FOREIGN KEY (`idAnimal`) REFERENCES `animal` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `intervention` */

insert  into `intervention`(`id`,`date`,`notes`,`discountForLoyalty`,`discountForNumberOfServices`,`totalAmountWithoutDiscount`,`totalAmountWithDiscount`,`idVeterinarian`,`idAnimal`) values 
(1,'2026-07-01','Preventivni pregled. Rezultati odlicni.',0,0,2500,2500,4,9),
(2,'2026-07-01','Pratiti stanje. Obavezno previjanje na svaka 2 dana.',0,15,25500,21675,4,5),
(3,'2026-07-02','Preporučena kontrola za 7 dana.',10,15,6500,4972.5,2,15),
(4,'2026-07-02','Preporuka za lek ABC radi smanjenja upale.',0,0,2500,2500,3,3),
(5,'2026-07-03','Pratiti stanje. Pregled za dva meseca.',0,0,3500,3500,5,8),
(6,'2026-07-03','',0,0,3000,3000,1,1),
(7,'2026-07-04','Preporučena kontrola za 7 dana.',0,0,4500,4500,3,4),
(8,'2026-07-04','Izvršeno vađenje zuba zbog infekcije.',0,0,6000,6000,5,8),
(9,'2026-07-05','Odlično opšte stanje.',0,0,3000,3000,1,9),
(10,'2026-07-05','Mekana hrana 5–10 dana.',0,0,8000,8000,9,6),
(11,'2026-07-06','Otok očiju zbog nedostatka vitamina A.',10,0,3000,2700,4,7),
(12,'2026-07-06','Uredna krvna slika.',0,0,500,500,1,8),
(13,'2026-07-07','Povrede glave. Kontrola za 2 dana.',10,15,10000,7650,1,1),
(14,'2026-07-07','Redovna vakcinacija.',10,0,5000,4500,4,12),
(15,'2026-07-08','Sniženi leukociti. Podrška imunitetu.',0,0,500,500,1,6),
(16,'2026-07-08','Upala se smanjila. Terapija još 2 dana.',0,0,2000,2000,3,3),
(17,'2026-07-09','Atopijski dermatitis.',0,0,1000,1000,9,4),
(18,'2026-07-09','Kontrolni pregled nakon terapije.',0,0,3000,3000,7,10),
(19,'2026-07-10','Dovesti životinju za dve nedelje.',0,0,4000,4000,8,11),
(20,'2026-07-10','',0,0,2000,2000,6,3),
(21,'2026-07-11','Kontrola nakon operacije.',10,0,5500,4950,4,2),
(22,'2026-07-11','Zarastanje rane uredno.',10,0,7500,6750,6,1),
(23,'2026-07-12','Preventivni pregled.',0,0,4500,4500,6,8),
(24,'2026-07-13','Vakcinacija.',0,0,2500,2500,4,1),
(25,'2026-07-14','Kontrolni pregled.',0,0,2500,2500,4,1),
(26,'2026-07-15','Laboratorijska analiza.',0,0,500,500,4,9),
(27,'2026-07-16','Kontrola terapije.',0,0,3500,3500,4,3),
(28,'2026-07-17','Laboratorijska analiza.',0,0,500,500,4,1),
(29,'2026-07-19','',10,0,3000,2700,2,15),
(30,'2026-07-19','',10,0,6500,5850,8,14),
(31,'2026-07-20','Ustanovljena upala pluca. Davati zivotinji lek X1Bc antibiotik, jedanput dnevno, nedelju dana.\nPregled za nedelju dana radi odredjivanja dalje terapije. ',10,0,4500,4050,3,14),
(32,'2026-07-21','Uočena blaga dehidratacija, preporučeno povećanje unosa tečnosti.',10,0,2500,2250,2,15),
(33,'2026-08-21','',10,0,2000,1800,2,7);

/*Table structure for table `interventionitem` */

DROP TABLE IF EXISTS `interventionitem`;

CREATE TABLE `interventionitem` (
  `idIntervention` bigint(20) unsigned NOT NULL,
  `rb` int(10) unsigned NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` double NOT NULL,
  `idService` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`idIntervention`,`rb`),
  KEY `idService` (`idService`),
  CONSTRAINT `interventionitem_ibfk_1` FOREIGN KEY (`idService`) REFERENCES `service` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `interventionitem_ibfk_2` FOREIGN KEY (`idIntervention`) REFERENCES `intervention` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `interventionitem` */

insert  into `interventionitem`(`idIntervention`,`rb`,`price`,`quantity`,`amount`,`idService`) values 
(1,1,2000,1,2000,1),
(1,2,500,1,500,3),
(2,1,2000,1,2000,1),
(2,2,2500,1,2500,4),
(2,3,1000,1,1000,6),
(2,4,20000,1,20000,12),
(3,1,1000,2,2000,6),
(3,2,500,1,500,3),
(3,3,2000,1,2000,5),
(3,4,2000,1,2000,8),
(4,1,2000,1,2000,1),
(4,2,500,1,500,3),
(5,1,1000,1,1000,15),
(5,2,2500,1,2500,4),
(6,1,3000,1,3000,7),
(7,1,2000,1,2000,1),
(7,2,500,1,500,3),
(7,3,1000,2,2000,6),
(8,1,6000,1,6000,13),
(9,1,3000,1,3000,7),
(10,1,2000,1,2000,8),
(10,2,6000,1,6000,13),
(11,1,2000,1,2000,1),
(11,2,1000,1,1000,14),
(12,1,500,1,500,3),
(13,1,2000,1,2000,1),
(13,2,5000,1,5000,11),
(13,3,1000,1,1000,15),
(13,4,1000,2,2000,6),
(14,1,2000,1,2000,2),
(14,2,3000,1,3000,7),
(15,1,500,1,500,3),
(16,1,2000,1,2000,1),
(17,1,1000,1,1000,16),
(18,1,3000,1,3000,7),
(19,1,2000,1,2000,1),
(19,2,2000,1,2000,5),
(20,1,2000,1,2000,1),
(21,1,2500,1,2500,4),
(21,2,3000,1,3000,7),
(22,1,2500,1,2500,4),
(22,2,5000,1,5000,11),
(23,1,2500,1,2500,4),
(23,2,2000,1,2000,1),
(24,1,2500,1,2500,4),
(25,1,2500,1,2500,4),
(26,1,500,1,500,3),
(27,1,2000,1,2000,5),
(27,2,1000,1,1000,6),
(27,3,500,1,500,3),
(28,1,500,1,500,3),
(29,1,3000,1,3000,7),
(30,1,500,1,500,3),
(30,2,6000,1,6000,13),
(31,1,2000,1,2000,1),
(31,2,2500,1,2500,4),
(32,1,2000,1,2000,1),
(32,2,500,1,500,3),
(33,1,2000,1,2000,1);

/*Table structure for table `owner` */

DROP TABLE IF EXISTS `owner`;

CREATE TABLE `owner` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `jmbg` varchar(50) NOT NULL,
  `loyaltyCard` tinyint(4) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `owner` */

insert  into `owner`(`id`,`firstname`,`lastname`,`jmbg`,`loyaltyCard`,`phone`,`email`,`address`) values 
(1,'Marko','Marković','7483926150473',1,'063789456','marko.markovic@gmail.com','Kralja Petra 19'),
(2,'Nina','Ninić','9038475612904',0,'062894561','nina.ninic@gmail.com','Nikole Tesle 64'),
(3,'Marija','Marić','5629103847561',0,'063945612','marija.maric@gmail.com','Milutina Milankovica 48'),
(4,'Nikola','Nikolić','8192746503829',0,'065147852','nikolanikolic@gmail.com','Dositeja Obradovica 123'),
(5,'Lena','Katić','6791036408513',1,'063158459','lena.katic@gmail.com','Jove Ilica 17'),
(6,'Elena','Lakicević','7565102892145',0,'063158994','elena.lakicevic@gmail.com','Sime Matavulja 49'),
(7,'Filip','Vasić','4365997135841',1,'062489677','filip.vasic@gmail.com','Hajduk Veljka 65'),
(8,'Darija','Urosević','1674325894564',0,'063401128','darija.urosevic@gmail.com','Bulevar Kralja Aleksandra 32'),
(9,'Vuk','Petrović','8645269971234',1,'061464982','vuk.petrovic@gmail.com','Sarajevska 63'),
(10,'Zorica','Katić','3496577123650',1,'062560886','zorica.katic@gmail.com','Bulevar Oslobodjenja 11'),
(11,'Lazar','Ilić','1957466324158',0,'063452585','lazar.ilic@gmail.com','Beogradska 78'),
(12,'Ivan','Ivić','1602549785236',1,'063425852','ivan.ivic@gmail.com','Sarajevska 38'),
(13,'Dusan','Lekić','7483926150472',0,'063259632','dusan.lekić@gmail.com','Dunavska  74'),
(14,'Ivan','Aleksić','3654298563214',0,'062485963','ivan.katić@gmaul.com','Nikole Tesle 56'),
(15,'Stevan','Jovanović','3596478123056',1,'062359954','sj@gmail.com','Kneza Lazara 23');

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` double unsigned NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `service` */

insert  into `service`(`id`,`name`,`price`,`description`) values 
(1,'Klinički pregled',2000,'Osnovni pregled životinje i procena zdravstvenog stanja.'),
(2,'Vakcinacija protiv besnila',2000,'Primena vakcine protiv besnila radi zaštite životinje.'),
(3,'Vadjenje krvi',500,'Uzimanje uzorka krvi za laboratorijske analize.'),
(4,'Rendgen',2500,'Snimanje unutrašnjih struktura radi dijagnostike.'),
(5,'Ultrazvuk',2000,'Ultrazvučni pregled organa i tkiva životinje.'),
(6,'Infuziona terapija',1000,'Primena tečnosti i lekova putem infuzije.'),
(7,'Šišanje',3000,'Usluga skraćivanja i oblikovanja dlake životinje.'),
(8,'Anestezija Kratkotrajna ',2000,'Kratkotrajna anestezija za manje zahvate.'),
(9,'Anestezija za kraće hiruške intervencije',3000,'Anestezija tokom jednostavnijih hirurških zahvata.'),
(10,'Anestezija za rutinske hiruške intervencije',4000,'Anestezija za standardne operativne procedure.'),
(11,'Anestezija za komplikovana opšta stanja',5000,'Anestezija prilagođena rizičnim pacijentima.'),
(12,'Sanacija preloma',20000,'Lečenje i zbrinjavanje preloma kostiju.'),
(13,'Vađenje zuba',6000,'Uklanjanje oštećenih ili bolesnih zuba.'),
(14,'Oftalmološki pregled',1000,'Pregled očiju i procena vida životinje.'),
(15,'Neurološki pregled',1000,'Pregled nervnog sistema i neuroloških funkcija.'),
(16,'Dermatoloski pregled',1000,'Pregled kože, dlake i kožnih oboljenja.');

/*Table structure for table `specialization` */

DROP TABLE IF EXISTS `specialization`;

CREATE TABLE `specialization` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `specialization` */

insert  into `specialization`(`id`,`name`,`category`,`description`) values 
(1,'Interna medicina','KLINICKA','Dijagnostika i lečenje bolesti unutrašnjih organa i telesnih sistema kod životinja.'),
(2,'Hirurgija','HIRURSKA','Operativno lečenje povreda, bolesti i deformiteta kod životinja.'),
(3,'Dermatologija','KLINICKA','Dijagnostika i terapija bolesti kože i kožnih struktura kod životinja.'),
(4,'Anesteziologija','ANESTEZIOLOSKA','Primena anestezije i analgezije uz praćenje vitalnih funkcija tokom intervencija.'),
(5,'Oftamologija','DIJAGNOSTICKA','Dijagnostika i lečenje bolesti oka i očnih struktura kod životinja.'),
(6,'Neurologija','KLINICKA','Dijagnostika i lečenje bolesti nervnog sistema kod životinja.'),
(7,'Ortopedija','HIRURSKA','Lečenje bolesti i povreda koštano-zglobnog sistema kod životinja.'),
(8,'Kardiologija','KLINICKA','Dijagnostika i lečenje bolesti srca i krvnih sudova kod životinja.');

/*Table structure for table `veterinarian` */

DROP TABLE IF EXISTS `veterinarian`;

CREATE TABLE `veterinarian` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `veterinarian` */

insert  into `veterinarian`(`id`,`firstname`,`lastname`,`birthday`,`phone`,`email`,`password`) values 
(1,'Admin','Admin','2000-01-01','062000000','admin@gmail.com','admin123'),
(2,'Ivana','Pejović','2002-03-20','063644230','ivana@gmail.com','ivana123'),
(3,'Sara','Jović','1989-11-01','062625418','sara@gmail.com','sara1234'),
(4,'Pera','Perić','1980-11-22','063123456','pera@gmail.com','pera1234'),
(5,'Jovana','Jovanić','1992-12-27','063245916','jovana@gmail.com','jovana123'),
(6,'Ognjen','Stevanović','1993-06-24','064156358','ognjen@gmail.com','ognjen123'),
(7,'Filip','Ristić','1996-08-06','063745589','filip@gmail.com','filip123'),
(8,'Milan','Milanović','1973-01-06','064345678','milan@gmail.com','milan123'),
(9,'Ana','Anić','1995-07-15','064234567','ana@gmail.com','ana12345');

/*Table structure for table `vetspec` */

DROP TABLE IF EXISTS `vetspec`;

CREATE TABLE `vetspec` (
  `idVeterinarian` bigint(20) unsigned NOT NULL,
  `idSpecialization` bigint(20) unsigned NOT NULL,
  `graduationDate` date NOT NULL,
  `institution` varchar(100) NOT NULL,
  PRIMARY KEY (`idVeterinarian`,`idSpecialization`),
  KEY `idSpecialization` (`idSpecialization`),
  CONSTRAINT `vetspec_ibfk_1` FOREIGN KEY (`idVeterinarian`) REFERENCES `veterinarian` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `vetspec_ibfk_2` FOREIGN KEY (`idSpecialization`) REFERENCES `specialization` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `vetspec` */

insert  into `vetspec`(`idVeterinarian`,`idSpecialization`,`graduationDate`,`institution`) values 
(1,1,'2020-05-23','Fakultet veterinarske medicine - Beograd'),
(2,1,'2018-10-10','Fakultet veterinarske medicine -  Beograd'),
(2,2,'2021-10-10','Fakultet veterinarske medicine - Beograd'),
(3,3,'2015-09-14','Fakultet veterinarske medicine - Novi Sad'),
(4,4,'2017-06-12','Fakultet veterinarske medicine - Beograd'),
(5,5,'2023-09-01','Fakultet veterinarske medicine - Novi Sad'),
(6,1,'2007-04-26','Fakultet veterinarske medicine - Niš'),
(6,7,'2009-02-07','Fakultet veterinarske medicine - NIš'),
(7,8,'2012-11-17','Fakultet veterinarske medicine -  Niš'),
(8,6,'2016-06-18','Fakultet veterinarske medicine -  Beograd'),
(9,1,'2020-09-06','Fakultet veterinarske medicine -  Beograd');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
