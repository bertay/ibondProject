/*
SQLyog Community v12.2.1 (32 bit)
MySQL - 10.4.14-MariaDB : Database - ibondgene
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ibondgene` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ibondgene`;

/*Table structure for table `adjudication` */

DROP TABLE IF EXISTS `adjudication`;

CREATE TABLE `adjudication` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rang` int(11) NOT NULL,
  `observation_fr` varchar(255) DEFAULT NULL,
  `observation_en` varchar(255) DEFAULT NULL,
  `observation_pt` varchar(255) DEFAULT NULL,
  `reouverture_id` bigint(20) DEFAULT NULL,
  `rachat_id` bigint(20) DEFAULT NULL,
  `onc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_adjudication_reouverture_id` (`reouverture_id`),
  KEY `fk_adjudication_rachat_id` (`rachat_id`),
  KEY `fk_adjudication_onc_id` (`onc_id`),
  CONSTRAINT `fk_adjudication_onc_id` FOREIGN KEY (`onc_id`) REFERENCES `onc` (`id`),
  CONSTRAINT `fk_adjudication_rachat_id` FOREIGN KEY (`rachat_id`) REFERENCES `rachat` (`id`),
  CONSTRAINT `fk_adjudication_reouverture_id` FOREIGN KEY (`reouverture_id`) REFERENCES `reouverture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Adjudication';

/*Data for the table `adjudication` */

insert  into `adjudication`(`id`,`rang`,`observation_fr`,`observation_en`,`observation_pt`,`reouverture_id`,`rachat_id`,`onc_id`) values 
(1,76665,'front-end','Fresh fuchsia','optical Checking Account',NULL,NULL,NULL),
(2,47018,'deposit indigo','Manager Bedfordshire extend','application Mission',NULL,NULL,NULL),
(3,6423,'Licensed Steel Sausages','matrix cross-platform Rustic Plastic Towels','niches Enterprise-wide Taka',NULL,NULL,NULL),
(4,44911,'Berkshire calculate','bypassing Forks National','program',NULL,NULL,NULL),
(5,14768,'port','Graphic Interface','Peru Handmade Berkshire',NULL,NULL,NULL),
(6,35044,'British Indian Ocean Territory (Chagos Archipelago)','expedite Music','Small bricks-and-clicks Maine',NULL,NULL,NULL),
(7,23503,'withdrawal','Kip','neural Bacon',NULL,NULL,NULL),
(8,65132,'Baht','Human teal','Sleek alarm Bacon',NULL,NULL,NULL),
(9,19237,'Concrete Concrete','lime Jewelery','applications Beauty real-time',NULL,NULL,NULL),
(10,80291,'Fresh','Awesome Steel Salad','holistic Assistant',NULL,NULL,NULL);

/*Table structure for table `avis_emission` */

DROP TABLE IF EXISTS `avis_emission`;

CREATE TABLE `avis_emission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nature` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `signataire` varchar(255) DEFAULT NULL,
  `objet_avis_fr` varchar(255) DEFAULT NULL,
  `objet_avis_en` varchar(255) DEFAULT NULL,
  `objet_avis_pt` varchar(255) DEFAULT NULL,
  `etat_avis` varchar(255) DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite AvisEmission';

/*Data for the table `avis_emission` */

insert  into `avis_emission`(`id`,`nature`,`numero`,`reference`,`signataire`,`objet_avis_fr`,`objet_avis_en`,`objet_avis_pt`,`etat_avis`,`operateur`,`date_operation`) values 
(1,'Resultat','Hat Toys Avon','SAS application Palladium','driver','monetize wireless','AI Car','Forges port','Projet','mobile bypassing','2021-08-21 11:57:45'),
(2,'Calendrier','Guinea-Bissau','invoice Haiti quantifying','Shirt Versatile backing up','Nebraska Implementation Corporate','FTP Operations Ethiopia','front-end','Projet','Checking Account','2021-08-20 18:16:34'),
(3,'Resultat','Chair Tennessee Swiss Franc','backing up','relationships Islands','Pants Savings Account','well-modulated','Fantastic interfaces Oklahoma','Projet','Savings Account bypassing','2021-08-21 12:25:34'),
(4,'Resultat','knowledge user Borders Vision-oriented','superstructure','Manager Granite parse','Salad','Fantastic Wooden Bacon','Global','Projet','pixel','2021-08-20 21:28:39'),
(5,'Avis','Cambridgeshire Intranet','holistic','Berkshire extensible','utilize wireless','Ergonomic Granite Sausages','Intelligent','Projet','backing up','2021-08-21 02:10:26'),
(6,'Resultat','Pizza','microchip copying','Industrial','strategize','functionalities product Handmade','Investment Account Checking Account','Signe','Bedfordshire','2021-08-21 10:38:03'),
(7,'Communique','calculate deposit','index','workforce Ergonomic streamline','optical Balanced Quality','Montenegro bandwidth-monitored Regional','withdrawal Incredible Cotton Table','Projet','Indiana','2021-08-20 21:08:55'),
(8,'Calendrier','Tactics Practical Plastic Sausages','withdrawal benchmark quantifying','bandwidth Hat','Ergonomic','withdrawal','unleash deposit scalable','Signe','Arizona USB','2021-08-21 12:18:47'),
(9,'Resultat','Costa Rican Colon Checking Account Cambridgeshire','strategize Mexican Peso Mexican Unidad de Inversion (UDI) initiatives','Frozen','Steel Triple-buffered','Handmade Wooden Tuna','functionalities Practical','Projet','Automotive','2021-08-21 05:03:08'),
(10,'Calendrier','input Ergonomic Soft Chair','productize process improvement system','Fiji Dollar transparent','Representative deposit executive','International','Grocery','Signe','Isle of Man quantify Avon','2021-08-21 10:39:35');

/*Table structure for table `calendrier` */

DROP TABLE IF EXISTS `calendrier`;

CREATE TABLE `calendrier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nature` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `signataire` varchar(255) DEFAULT NULL,
  `titre_calendrier_fr` varchar(255) DEFAULT NULL,
  `titre_calendrier_en` varchar(255) DEFAULT NULL,
  `titre_calendrier_pt` varchar(255) DEFAULT NULL,
  `etat_calendrier` varchar(255) DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Calendrier';

/*Data for the table `calendrier` */

insert  into `calendrier`(`id`,`nature`,`numero`,`reference`,`signataire`,`titre_calendrier_fr`,`titre_calendrier_en`,`titre_calendrier_pt`,`etat_calendrier`,`operateur`,`date_operation`) values 
(1,'Avis','24/7 open-source parsing','networks Solutions array','stable','Identity Branding protocol','Washington Metal','CSS','Projet','Kids','2021-08-20 15:17:46'),
(2,'Resultat','Niue','Product Industrial','Liaison transmitter South Dakota','salmon Wells','metrics','navigating National','Signe','connect','2021-08-20 14:19:16'),
(3,'Calendrier','Buckinghamshire panel pink','Cheese Gloves','violet','Sausages','Technician Architect Beauty','Analyst card','Projet','Cliff Orchestrator','2021-08-21 13:25:27'),
(4,'Resultat','Checking Account Rhode Island','convergence platforms','Chair transmit','calculate Chicken','Sports multi-tasking','Implementation compressing hacking','Projet','Global','2021-08-20 22:59:58'),
(5,'Resultat','Unbranded Frozen Cheese extend','Berkshire deposit niches','coherent Hat','Tools revolutionize','Central','Dynamic Michigan','Projet','intranet Representative','2021-08-21 12:00:19'),
(6,'Avis','Tasty Concrete Chair Tasty','protocol Loop Ameliorated','disintermediate Regional Buckinghamshire','reintermediate','iterate','RSS Licensed Rubber Mouse Implementation','Projet','hardware enable','2021-08-21 08:08:39'),
(7,'Communique','24/7 Home Loan Account Bedfordshire','Virginia input Program','stable','GB haptic','New York static','Paradigm','Signe','Fish multi-byte PNG','2021-08-21 12:46:20'),
(8,'Communique','sky blue','compressing transmit','Corporate','sky blue Movies','Tasty compress','Realigned haptic initiatives','Projet','Ferry TCP','2021-08-20 20:48:40'),
(9,'Avis','supply-chains Czech Koruna open-source','payment','clicks-and-mortar','Direct contextually-based leverage','silver','Chair','Signe','Tuna holistic','2021-08-21 11:30:59'),
(10,'Avis','Bangladesh Garden','Dynamic microchip Borders','Ethiopia','Iceland Borders','Virgin Islands, British bifurcated','Self-enabling','Signe','ROI','2021-08-21 11:05:05');

/*Table structure for table `classement` */

DROP TABLE IF EXISTS `classement`;

CREATE TABLE `classement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rang` int(11) NOT NULL,
  `observation_fr` varchar(255) DEFAULT NULL,
  `observation_en` varchar(255) DEFAULT NULL,
  `observation_pt` varchar(255) DEFAULT NULL,
  `detail_soumission_id` bigint(20) DEFAULT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  `reouverture_id` bigint(20) DEFAULT NULL,
  `rachat_id` bigint(20) DEFAULT NULL,
  `onc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_classement_detail_soumission_id` (`detail_soumission_id`),
  KEY `fk_classement_emission_id` (`emission_id`),
  KEY `fk_classement_reouverture_id` (`reouverture_id`),
  KEY `fk_classement_rachat_id` (`rachat_id`),
  KEY `fk_classement_onc_id` (`onc_id`),
  CONSTRAINT `fk_classement_detail_soumission_id` FOREIGN KEY (`detail_soumission_id`) REFERENCES `detail_soumission` (`id`),
  CONSTRAINT `fk_classement_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`),
  CONSTRAINT `fk_classement_onc_id` FOREIGN KEY (`onc_id`) REFERENCES `onc` (`id`),
  CONSTRAINT `fk_classement_rachat_id` FOREIGN KEY (`rachat_id`) REFERENCES `rachat` (`id`),
  CONSTRAINT `fk_classement_reouverture_id` FOREIGN KEY (`reouverture_id`) REFERENCES `reouverture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Classement';

/*Data for the table `classement` */

insert  into `classement`(`id`,`rang`,`observation_fr`,`observation_en`,`observation_pt`,`detail_soumission_id`,`emission_id`,`reouverture_id`,`rachat_id`,`onc_id`) values 
(1,43861,'Electronics Senior','JSON Avon deposit','Future harness mobile',NULL,NULL,NULL,NULL,NULL),
(2,31760,'Directives payment','Unbranded Plastic Keyboard Automated payment','optical Chair',NULL,NULL,NULL,NULL,NULL),
(3,27263,'Senior data-warehouse Gloves','invoice intranet infrastructures','Arizona',NULL,NULL,NULL,NULL,NULL),
(4,65954,'Practical','Open-architected','Yemen',NULL,NULL,NULL,NULL,NULL),
(5,6818,'task-force','Texas optical','transmit Concrete Marketing',NULL,NULL,NULL,NULL,NULL),
(6,1145,'Personal Loan Account Integration Streets','Credit Card Account fuchsia','Representative',NULL,NULL,NULL,NULL,NULL),
(7,51532,'mindshare','Towels Cedi','input Cotton',NULL,NULL,NULL,NULL,NULL),
(8,31546,'Cambridgeshire','Auto Loan Account Metal','Netherlands Antilles',NULL,NULL,NULL,NULL,NULL),
(9,15999,'deposit','experiences Buckinghamshire transition','Legacy experiences',NULL,NULL,NULL,NULL,NULL),
(10,18147,'circuit redundant Creative','Shoes Prairie application','calculating indexing',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `databasechangelog` */

DROP TABLE IF EXISTS `databasechangelog`;

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `databasechangelog` */

insert  into `databasechangelog`(`ID`,`AUTHOR`,`FILENAME`,`DATEEXECUTED`,`ORDEREXECUTED`,`EXECTYPE`,`MD5SUM`,`DESCRIPTION`,`COMMENTS`,`TAG`,`LIQUIBASE`,`CONTEXTS`,`LABELS`,`DEPLOYMENT_ID`) values 
('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2021-08-21 18:12:02',1,'EXECUTED','8:8e18ecdf9db75399eec29a65aa6d8fe5','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160651-1','jhipster','config/liquibase/changelog/20210821160651_added_entity_NatureTitre.xml','2021-08-21 18:12:03',2,'EXECUTED','8:6cbf524f23be4135a92ef1a290974319','createTable tableName=nature_titre; dropDefaultValue columnName=date_operation, tableName=nature_titre','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160651-1-relations','jhipster','config/liquibase/changelog/20210821160651_added_entity_NatureTitre.xml','2021-08-21 18:12:03',3,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160651-1-data','jhipster','config/liquibase/changelog/20210821160651_added_entity_NatureTitre.xml','2021-08-21 18:12:04',4,'EXECUTED','8:d954dbae3068e5bf8f161c665e30bdf5','loadData tableName=nature_titre','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821160751-1','jhipster','config/liquibase/changelog/20210821160751_added_entity_AvisEmission.xml','2021-08-21 18:12:04',5,'EXECUTED','8:699ea0fc6b193ee0a0062add7dcaa661','createTable tableName=avis_emission; dropDefaultValue columnName=date_operation, tableName=avis_emission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160751-1-relations','jhipster','config/liquibase/changelog/20210821160751_added_entity_AvisEmission.xml','2021-08-21 18:12:04',6,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160751-1-data','jhipster','config/liquibase/changelog/20210821160751_added_entity_AvisEmission.xml','2021-08-21 18:12:05',7,'EXECUTED','8:f7b398f789f6011110988f848d14b718','loadData tableName=avis_emission','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821160851-1','jhipster','config/liquibase/changelog/20210821160851_added_entity_Emission.xml','2021-08-21 18:12:06',8,'EXECUTED','8:614d2aceea208b74345ca289dbb20a5b','createTable tableName=emission; dropDefaultValue columnName=date_limite, tableName=emission; dropDefaultValue columnName=date_resultat, tableName=emission; dropDefaultValue columnName=date_reglement, tableName=emission; dropDefaultValue columnName...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160851-1-relations','jhipster','config/liquibase/changelog/20210821160851_added_entity_Emission.xml','2021-08-21 18:12:06',9,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160851-1-data','jhipster','config/liquibase/changelog/20210821160851_added_entity_Emission.xml','2021-08-21 18:12:07',10,'EXECUTED','8:d971fa6924637e299eba4de6a6d94318','loadData tableName=emission','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821160951-1','jhipster','config/liquibase/changelog/20210821160951_added_entity_Pays.xml','2021-08-21 18:12:07',11,'EXECUTED','8:79705bcc47cbe975adf331ef76802061','createTable tableName=pays; dropDefaultValue columnName=date_operation, tableName=pays','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160951-1-relations','jhipster','config/liquibase/changelog/20210821160951_added_entity_Pays.xml','2021-08-21 18:12:07',12,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821160951-1-data','jhipster','config/liquibase/changelog/20210821160951_added_entity_Pays.xml','2021-08-21 18:12:08',13,'EXECUTED','8:90c0d537ff8a3b98c38b8ab59745fcc3','loadData tableName=pays','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161051-1','jhipster','config/liquibase/changelog/20210821161051_added_entity_Svt.xml','2021-08-21 18:12:08',14,'EXECUTED','8:915994263780303756a5298221f9ef27','createTable tableName=svt; dropDefaultValue columnName=date_operation, tableName=svt','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161051-1-relations','jhipster','config/liquibase/changelog/20210821161051_added_entity_Svt.xml','2021-08-21 18:12:08',15,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161051-1-data','jhipster','config/liquibase/changelog/20210821161051_added_entity_Svt.xml','2021-08-21 18:12:09',16,'EXECUTED','8:b6c645d5881264c7834ebe200215006d','loadData tableName=svt','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161151-1','jhipster','config/liquibase/changelog/20210821161151_added_entity_Soumission.xml','2021-08-21 18:12:09',17,'EXECUTED','8:11ac0f62bae6fd11f5a520c568c8b566','createTable tableName=soumission; dropDefaultValue columnName=date_soumission, tableName=soumission; dropDefaultValue columnName=date_operation, tableName=soumission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161151-1-relations','jhipster','config/liquibase/changelog/20210821161151_added_entity_Soumission.xml','2021-08-21 18:12:09',18,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161151-1-data','jhipster','config/liquibase/changelog/20210821161151_added_entity_Soumission.xml','2021-08-21 18:12:10',19,'EXECUTED','8:83dc7d282129527fc5d59d337ea15849','loadData tableName=soumission','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161251-1','jhipster','config/liquibase/changelog/20210821161251_added_entity_DetailSoumission.xml','2021-08-21 18:12:10',20,'EXECUTED','8:931a9405cc279a43f88a781274ea11ae','createTable tableName=detail_soumission; dropDefaultValue columnName=date_operation, tableName=detail_soumission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161251-1-relations','jhipster','config/liquibase/changelog/20210821161251_added_entity_DetailSoumission.xml','2021-08-21 18:12:10',21,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161251-1-data','jhipster','config/liquibase/changelog/20210821161251_added_entity_DetailSoumission.xml','2021-08-21 18:12:11',22,'EXECUTED','8:225a7d2bd4c5ca7e9016eba3e04ac41a','loadData tableName=detail_soumission','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161351-1','jhipster','config/liquibase/changelog/20210821161351_added_entity_Classement.xml','2021-08-21 18:12:11',23,'EXECUTED','8:625c5d16c832838fc945bb47f0470aec','createTable tableName=classement','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161351-1-relations','jhipster','config/liquibase/changelog/20210821161351_added_entity_Classement.xml','2021-08-21 18:12:11',24,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161351-1-data','jhipster','config/liquibase/changelog/20210821161351_added_entity_Classement.xml','2021-08-21 18:12:11',25,'EXECUTED','8:f815b90a942d6c7658014c05863d0430','loadData tableName=classement','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161451-1','jhipster','config/liquibase/changelog/20210821161451_added_entity_Adjudication.xml','2021-08-21 18:12:12',26,'EXECUTED','8:20e6786b72166206e74a62976f6a89d7','createTable tableName=adjudication','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161451-1-relations','jhipster','config/liquibase/changelog/20210821161451_added_entity_Adjudication.xml','2021-08-21 18:12:12',27,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161451-1-data','jhipster','config/liquibase/changelog/20210821161451_added_entity_Adjudication.xml','2021-08-21 18:12:12',28,'EXECUTED','8:5818265c74c40f2d41247348169d0d34','loadData tableName=adjudication','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161551-1','jhipster','config/liquibase/changelog/20210821161551_added_entity_Resultat.xml','2021-08-21 18:12:13',29,'EXECUTED','8:d5db01ee403efb3b81437f93e55aca48','createTable tableName=resultat','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161551-1-relations','jhipster','config/liquibase/changelog/20210821161551_added_entity_Resultat.xml','2021-08-21 18:12:13',30,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161551-1-data','jhipster','config/liquibase/changelog/20210821161551_added_entity_Resultat.xml','2021-08-21 18:12:13',31,'EXECUTED','8:4a60c616cfabbbb370ef55e3402c36a4','loadData tableName=resultat','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161651-1','jhipster','config/liquibase/changelog/20210821161651_added_entity_Parametres.xml','2021-08-21 18:12:14',32,'EXECUTED','8:8fd69057477870a1f9eed8132076d7cb','createTable tableName=parametres; dropDefaultValue columnName=date_operation, tableName=parametres','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161651-1-relations','jhipster','config/liquibase/changelog/20210821161651_added_entity_Parametres.xml','2021-08-21 18:12:14',33,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161651-1-data','jhipster','config/liquibase/changelog/20210821161651_added_entity_Parametres.xml','2021-08-21 18:12:14',34,'EXECUTED','8:6cf9fa1d5fe0602e8213548bdb881289','loadData tableName=parametres','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161751-1','jhipster','config/liquibase/changelog/20210821161751_added_entity_Calendrier.xml','2021-08-21 18:12:15',35,'EXECUTED','8:3c6592ea06c9afed1145920238ed0d00','createTable tableName=calendrier; dropDefaultValue columnName=date_operation, tableName=calendrier','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161751-1-relations','jhipster','config/liquibase/changelog/20210821161751_added_entity_Calendrier.xml','2021-08-21 18:12:15',36,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161751-1-data','jhipster','config/liquibase/changelog/20210821161751_added_entity_Calendrier.xml','2021-08-21 18:12:15',37,'EXECUTED','8:a35934fda8dc4fc77a4b35a6af750af9','loadData tableName=calendrier','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161851-1','jhipster','config/liquibase/changelog/20210821161851_added_entity_DetailCalendrier.xml','2021-08-21 18:12:16',38,'EXECUTED','8:d2559e7684b824026e0ac1edfda9fddd','createTable tableName=detail_calendrier; dropDefaultValue columnName=date_operation, tableName=detail_calendrier','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161851-1-relations','jhipster','config/liquibase/changelog/20210821161851_added_entity_DetailCalendrier.xml','2021-08-21 18:12:16',39,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161851-1-data','jhipster','config/liquibase/changelog/20210821161851_added_entity_DetailCalendrier.xml','2021-08-21 18:12:16',40,'EXECUTED','8:d738a3bef7e8c9d48bf26d50aa46402d','loadData tableName=detail_calendrier','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821161951-1','jhipster','config/liquibase/changelog/20210821161951_added_entity_MembreSyndicat.xml','2021-08-21 18:12:17',41,'EXECUTED','8:dd88f2095e44740e78d822d98936ea5a','createTable tableName=membre_syndicat; dropDefaultValue columnName=date_operation, tableName=membre_syndicat','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161951-1-relations','jhipster','config/liquibase/changelog/20210821161951_added_entity_MembreSyndicat.xml','2021-08-21 18:12:17',42,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161951-1-data','jhipster','config/liquibase/changelog/20210821161951_added_entity_MembreSyndicat.xml','2021-08-21 18:12:17',43,'EXECUTED','8:3bd8fdcb9d92d0541ffcd8588d18842a','loadData tableName=membre_syndicat','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821162051-1','jhipster','config/liquibase/changelog/20210821162051_added_entity_Reouverture.xml','2021-08-21 18:12:17',44,'EXECUTED','8:fca35763cbb9cc2a3500eb386cd0c5f0','createTable tableName=reouverture; dropDefaultValue columnName=date_operation, tableName=reouverture','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162051-1-relations','jhipster','config/liquibase/changelog/20210821162051_added_entity_Reouverture.xml','2021-08-21 18:12:18',45,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162051-1-data','jhipster','config/liquibase/changelog/20210821162051_added_entity_Reouverture.xml','2021-08-21 18:12:18',46,'EXECUTED','8:7fba488a873209ae25ad0250b671dba2','loadData tableName=reouverture','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821162151-1','jhipster','config/liquibase/changelog/20210821162151_added_entity_Rachat.xml','2021-08-21 18:12:18',47,'EXECUTED','8:0bfb376711d36ab60d12023dd8ad828d','createTable tableName=rachat; dropDefaultValue columnName=date_operation, tableName=rachat','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162151-1-relations','jhipster','config/liquibase/changelog/20210821162151_added_entity_Rachat.xml','2021-08-21 18:12:18',48,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162151-1-data','jhipster','config/liquibase/changelog/20210821162151_added_entity_Rachat.xml','2021-08-21 18:12:18',49,'EXECUTED','8:45e23231c1a022f0887126abf9ac0f74','loadData tableName=rachat','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821162251-1','jhipster','config/liquibase/changelog/20210821162251_added_entity_Onc.xml','2021-08-21 18:12:19',50,'EXECUTED','8:8cf0281ce24a0842f66373cf9d321864','createTable tableName=onc; dropDefaultValue columnName=date_operation, tableName=onc','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162251-1-relations','jhipster','config/liquibase/changelog/20210821162251_added_entity_Onc.xml','2021-08-21 18:12:19',51,'EXECUTED','8:d41d8cd98f00b204e9800998ecf8427e','empty','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162251-1-data','jhipster','config/liquibase/changelog/20210821162251_added_entity_Onc.xml','2021-08-21 18:12:19',52,'EXECUTED','8:ad59c9f1eed048e9436a640a218a6b5a','loadData tableName=onc','',NULL,'3.9.0','faker',NULL,'9562312942'),
('20210821160851-2','jhipster','config/liquibase/changelog/20210821160851_added_entity_constraints_Emission.xml','2021-08-21 18:12:23',53,'EXECUTED','8:d3a0436262f3108b32fd27d340814522','addForeignKeyConstraint baseTableName=emission, constraintName=fk_emission_avis_emission_id, referencedTableName=avis_emission; addForeignKeyConstraint baseTableName=emission, constraintName=fk_emission_nature_titre_id, referencedTableName=nature_...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161051-2','jhipster','config/liquibase/changelog/20210821161051_added_entity_constraints_Svt.xml','2021-08-21 18:12:26',54,'EXECUTED','8:dd65896807042fd5c4b4f1ceb840eb1b','addForeignKeyConstraint baseTableName=svt, constraintName=fk_svt_pays_id, referencedTableName=pays','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161151-2','jhipster','config/liquibase/changelog/20210821161151_added_entity_constraints_Soumission.xml','2021-08-21 18:12:35',55,'EXECUTED','8:be5a8a8a9499466166154d3eaa36a9b5','addForeignKeyConstraint baseTableName=soumission, constraintName=fk_soumission_emission_id, referencedTableName=emission; addForeignKeyConstraint baseTableName=soumission, constraintName=fk_soumission_reouverture_id, referencedTableName=reouvertur...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161251-2','jhipster','config/liquibase/changelog/20210821161251_added_entity_constraints_DetailSoumission.xml','2021-08-21 18:12:36',56,'EXECUTED','8:17368dbb8eef8b5d0586611fdd32cc66','addForeignKeyConstraint baseTableName=detail_soumission, constraintName=fk_detail_soumission_soumission_id, referencedTableName=soumission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161351-2','jhipster','config/liquibase/changelog/20210821161351_added_entity_constraints_Classement.xml','2021-08-21 18:12:49',57,'EXECUTED','8:ccfd656341992e53e41f1ed57fb5a7a9','addForeignKeyConstraint baseTableName=classement, constraintName=fk_classement_detail_soumission_id, referencedTableName=detail_soumission; addForeignKeyConstraint baseTableName=classement, constraintName=fk_classement_emission_id, referencedTable...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161451-2','jhipster','config/liquibase/changelog/20210821161451_added_entity_constraints_Adjudication.xml','2021-08-21 18:12:56',58,'EXECUTED','8:74e8a6fbeed5cca5cf0151b04de1be57','addForeignKeyConstraint baseTableName=adjudication, constraintName=fk_adjudication_reouverture_id, referencedTableName=reouverture; addForeignKeyConstraint baseTableName=adjudication, constraintName=fk_adjudication_rachat_id, referencedTableName=r...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161551-2','jhipster','config/liquibase/changelog/20210821161551_added_entity_constraints_Resultat.xml','2021-08-21 18:13:08',59,'EXECUTED','8:3c96297f470076e1cef8c8cd75d02dd2','addForeignKeyConstraint baseTableName=resultat, constraintName=fk_resultat_emission_id, referencedTableName=emission; addForeignKeyConstraint baseTableName=resultat, constraintName=fk_resultat_resultat_id, referencedTableName=reouverture; addForei...','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161851-2','jhipster','config/liquibase/changelog/20210821161851_added_entity_constraints_DetailCalendrier.xml','2021-08-21 18:13:10',60,'EXECUTED','8:48550b075f2bcf88e7ee4ef072292159','addForeignKeyConstraint baseTableName=detail_calendrier, constraintName=fk_detail_calendrier_calendrier_id, referencedTableName=calendrier','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821161951-2','jhipster','config/liquibase/changelog/20210821161951_added_entity_constraints_MembreSyndicat.xml','2021-08-21 18:13:13',61,'EXECUTED','8:3d7ac2bcb874868ed3c16452ba0d4318','addForeignKeyConstraint baseTableName=membre_syndicat, constraintName=fk_membre_syndicat_emission_id, referencedTableName=emission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162051-2','jhipster','config/liquibase/changelog/20210821162051_added_entity_constraints_Reouverture.xml','2021-08-21 18:13:14',62,'EXECUTED','8:d75cb241cffcd8ffae35a3461fc807c9','addForeignKeyConstraint baseTableName=reouverture, constraintName=fk_reouverture_emission_id, referencedTableName=emission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162151-2','jhipster','config/liquibase/changelog/20210821162151_added_entity_constraints_Rachat.xml','2021-08-21 18:13:15',63,'EXECUTED','8:6081873f8bb51722c32cfbf9281f4841','addForeignKeyConstraint baseTableName=rachat, constraintName=fk_rachat_emission_id, referencedTableName=emission','',NULL,'3.9.0',NULL,NULL,'9562312942'),
('20210821162251-2','jhipster','config/liquibase/changelog/20210821162251_added_entity_constraints_Onc.xml','2021-08-21 18:13:17',64,'EXECUTED','8:f10886964bceb1f6530cd1bbbca6f8a9','addForeignKeyConstraint baseTableName=onc, constraintName=fk_onc_emission_id, referencedTableName=emission','',NULL,'3.9.0',NULL,NULL,'9562312942');

/*Table structure for table `databasechangeloglock` */

DROP TABLE IF EXISTS `databasechangeloglock`;

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `databasechangeloglock` */

insert  into `databasechangeloglock`(`ID`,`LOCKED`,`LOCKGRANTED`,`LOCKEDBY`) values 
(1,'\0',NULL,NULL);

/*Table structure for table `detail_calendrier` */

DROP TABLE IF EXISTS `detail_calendrier`;

CREATE TABLE `detail_calendrier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `periode` varchar(255) NOT NULL,
  `annee` int(11) NOT NULL,
  `date_annonce` date NOT NULL,
  `date_adjudication` date DEFAULT NULL,
  `date_echeance` date NOT NULL,
  `duree` varchar(255) DEFAULT NULL,
  `volume_emission` double NOT NULL,
  `unite_volume` varchar(255) NOT NULL,
  `devise` varchar(255) NOT NULL,
  `date_valeur` date DEFAULT NULL,
  `nature` varchar(255) NOT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `calendrier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detail_calendrier_calendrier_id` (`calendrier_id`),
  CONSTRAINT `fk_detail_calendrier_calendrier_id` FOREIGN KEY (`calendrier_id`) REFERENCES `calendrier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Detail Calendrier';

/*Data for the table `detail_calendrier` */

insert  into `detail_calendrier`(`id`,`periode`,`annee`,`date_annonce`,`date_adjudication`,`date_echeance`,`duree`,`volume_emission`,`unite_volume`,`devise`,`date_valeur`,`nature`,`operateur`,`date_operation`,`calendrier_id`) values 
(1,'monitor eyeballs reintermediate',85324,'2021-08-20','2021-08-19','2021-08-20','Assistant',58076,'Millions','USD','2021-08-20','Rachat','FTP','2021-08-20 18:46:58',NULL),
(2,'global New Leu',52836,'2021-08-19','2021-08-20','2021-08-19','Games deposit evolve',94638,'Milliards','USD','2021-08-20','Reouverture','silver','2021-08-20 23:25:22',NULL),
(3,'parsing Berkshire connecting',77973,'2021-08-20','2021-08-20','2021-08-20','contextually-based',58580,'Millions','FCFA','2021-08-19','Normale','grey parsing','2021-08-21 13:35:56',NULL),
(4,'Grocery Zambian Kwacha Bacon',74314,'2021-08-19','2021-08-20','2021-08-19','Divide Associate',7714,'Millions','FCFA','2021-08-19','Syndication','withdrawal','2021-08-21 07:42:27',NULL),
(5,'alarm engage ADP',14518,'2021-08-19','2021-08-19','2021-08-19','Avon',32224,'Millions','Euro','2021-08-20','Rachat','pink Home Loan Account','2021-08-20 16:34:14',NULL),
(6,'Sausages systemic copying',91209,'2021-08-20','2021-08-20','2021-08-20','Berkshire transmit Plastic',95237,'Milles','Euro','2021-08-19','Rachat','South Carolina Future Marketing','2021-08-21 02:02:44',NULL),
(7,'Junction Technician Configurable',47273,'2021-08-20','2021-08-19','2021-08-19','Borders violet Home',57622,'Milliards','USD','2021-08-20','Rachat','Incredible Soft Chips orchestration','2021-08-20 17:23:01',NULL),
(8,'Beauty',44041,'2021-08-20','2021-08-20','2021-08-19','Refined Cotton Mouse Synchronised',95349,'Milliards','USD','2021-08-20','Syndication','Guarani Canada Optimization','2021-08-20 23:21:15',NULL),
(9,'Rwanda',63308,'2021-08-20','2021-08-19','2021-08-20','Data programming Sports',4232,'Millions','FCFA','2021-08-20','Normale','Function-based','2021-08-20 17:37:04',NULL),
(10,'back-end enterprise',8913,'2021-08-19','2021-08-20','2021-08-20','New Zealand framework',11795,'Millions','USD','2021-08-20','Reouverture','cultivate Steel','2021-08-21 06:49:38',NULL);

/*Table structure for table `detail_soumission` */

DROP TABLE IF EXISTS `detail_soumission`;

CREATE TABLE `detail_soumission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `montant_soumission` double NOT NULL,
  `taux_propose` float NOT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `soumission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detail_soumission_soumission_id` (`soumission_id`),
  CONSTRAINT `fk_detail_soumission_soumission_id` FOREIGN KEY (`soumission_id`) REFERENCES `soumission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite DetailSoumission';

/*Data for the table `detail_soumission` */

insert  into `detail_soumission`(`id`,`montant_soumission`,`taux_propose`,`operateur`,`date_operation`,`soumission_id`) values 
(1,80146,24,'National Metal Alaska','2021-08-21 02:27:15',NULL),
(2,57422,41,'Bike web services','2021-08-20 15:05:36',NULL),
(3,96684,32,'collaborative Silver Garden','2021-08-20 16:22:21',NULL),
(4,59404,66,'client-server invoice','2021-08-21 00:09:17',NULL),
(5,65632,44,'Harbor deposit','2021-08-21 03:59:12',NULL),
(6,17421,27,'Indiana deposit','2021-08-20 16:54:26',NULL),
(7,39808,71,'grid-enabled maximize Ergonomic','2021-08-21 01:31:14',NULL),
(8,34778,31,'web-enabled Streets initiatives','2021-08-20 20:23:41',NULL),
(9,89979,17,'Panama Borders','2021-08-20 22:32:24',NULL),
(10,14628,19,'Virginia Hat','2021-08-20 20:17:01',NULL);

/*Table structure for table `emission` */

DROP TABLE IF EXISTS `emission`;

CREATE TABLE `emission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_emission` varchar(255) NOT NULL,
  `designation_fr` varchar(255) DEFAULT NULL,
  `designation_en` varchar(255) DEFAULT NULL,
  `designation_pt` varchar(255) DEFAULT NULL,
  `date_emission` date NOT NULL,
  `echeance` date NOT NULL,
  `duree` varchar(255) DEFAULT NULL,
  `remboursement` varchar(255) DEFAULT NULL,
  `forme_titre` varchar(255) DEFAULT NULL,
  `taux_interet` float NOT NULL,
  `volume_emission` double NOT NULL,
  `unite_volume` varchar(255) NOT NULL,
  `valeur_nominale` double NOT NULL,
  `devise` varchar(255) NOT NULL,
  `quantite_titre` int(11) DEFAULT NULL,
  `rendement` varchar(255) DEFAULT NULL,
  `date_limite` datetime NOT NULL,
  `lieu_souscription` varchar(255) NOT NULL,
  `date_resultat` datetime,
  `date_reglement` datetime,
  `date_valeur` date DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `avis_emission_id` bigint(20) DEFAULT NULL,
  `nature_titre_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emission_avis_emission_id` (`avis_emission_id`),
  KEY `fk_emission_nature_titre_id` (`nature_titre_id`),
  CONSTRAINT `fk_emission_avis_emission_id` FOREIGN KEY (`avis_emission_id`) REFERENCES `avis_emission` (`id`),
  CONSTRAINT `fk_emission_nature_titre_id` FOREIGN KEY (`nature_titre_id`) REFERENCES `nature_titre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Emission';

/*Data for the table `emission` */

insert  into `emission`(`id`,`code_emission`,`designation_fr`,`designation_en`,`designation_pt`,`date_emission`,`echeance`,`duree`,`remboursement`,`forme_titre`,`taux_interet`,`volume_emission`,`unite_volume`,`valeur_nominale`,`devise`,`quantite_titre`,`rendement`,`date_limite`,`lieu_souscription`,`date_resultat`,`date_reglement`,`date_valeur`,`operateur`,`date_operation`,`avis_emission_id`,`nature_titre_id`) values 
(1,'Intranet Checking Account','didactic driver','Wooden','didactic open-source','2021-08-20','2021-08-19','compressing','Awesome Steel Computer','redundant Home',68,5821,'Milles',97617,'USD',29151,'intermediate Awesome Metal Fish lime','2021-08-20 17:09:29','Generic Cotton Hat','2021-08-21 02:58:03','2021-08-21 10:30:15','2021-08-20','Argentine Peso Integration','2021-08-20 17:19:23',NULL,NULL),
(2,'Analyst','Paradigm','bleeding-edge','Colorado Land','2021-08-20','2021-08-20','ROI','primary mint green','generate Personal Loan Account Tasty Soft Salad',94,20096,'Millions',11251,'FCFA',81951,'black matrix Product','2021-08-20 15:08:22','connect','2021-08-21 11:09:23','2021-08-20 16:49:39','2021-08-20','payment Planner Specialist','2021-08-20 22:26:39',NULL,NULL),
(3,'Canyon','Incredible Metal Hat','Function-based integrate Wisconsin','Soap Rufiyaa even-keeled','2021-08-20','2021-08-20','monetize','Unions Chair','Tokelau Indiana',43,20964,'Milles',49938,'FCFA',25037,'Fully-configurable cross-platform','2021-08-21 06:46:45','PNG Home Loan Account','2021-08-20 23:29:58','2021-08-21 02:48:14','2021-08-19','Afghani','2021-08-21 00:16:29',NULL,NULL),
(4,'Directives focus group','visionary compressing Tasty Wooden Towels','mobile Specialist program','Palladium','2021-08-20','2021-08-20','Rustic scale backing up','benchmark Global PCI','Liaison',88,51559,'Milliards',33751,'Euro',68942,'Designer metrics','2021-08-21 08:56:03','Romania orchestration','2021-08-20 20:00:38','2021-08-21 12:50:11','2021-08-20','Money Market Account grey Wooden','2021-08-20 20:44:04',NULL,NULL),
(5,'withdrawal Synergistic','Norwegian Krone Corporate','Buckinghamshire Bacon','District','2021-08-20','2021-08-20','enable Incredible','SDD','ubiquitous',87,80804,'Millions',78390,'Euro',78369,'California Massachusetts','2021-08-21 09:34:51','pixel','2021-08-20 21:04:43','2021-08-20 20:17:47','2021-08-20','Philippines','2021-08-21 13:54:18',NULL,NULL),
(6,'high-level','Concrete','revolutionary Som','Electronics Palestinian Territory overriding','2021-08-20','2021-08-20','lavender Customer','Credit Card Account','Bedfordshire',26,74079,'Milliards',8456,'USD',13903,'Soft','2021-08-20 17:47:50','Mouse','2021-08-20 20:31:28','2021-08-20 16:30:41','2021-08-20','experiences Extended Fantastic','2021-08-21 00:50:53',NULL,NULL),
(7,'optimizing Fantastic','Group Guyana','New Hampshire','frictionless bottom-line','2021-08-19','2021-08-20','invoice infrastructures Wooden','e-markets SMS','models compress Dynamic',24,7220,'Milliards',59837,'USD',50570,'neural reboot Bhutan','2021-08-21 12:13:55','Drives deposit haptic','2021-08-21 01:44:50','2021-08-20 21:26:38','2021-08-20','Drive','2021-08-20 23:27:42',NULL,NULL),
(8,'Soft','transparent hub','Implementation Gorgeous','open system Cambridgeshire','2021-08-20','2021-08-20','Dynamic','Guam connect Tools','Cheese Wooden Movies',88,37802,'Milliards',49138,'Euro',22934,'Tasty Cotton Pizza','2021-08-20 17:29:06','frame copy','2021-08-21 11:14:11','2021-08-20 20:14:46','2021-08-20','quantifying holistic','2021-08-20 16:57:44',NULL,NULL),
(9,'SQL','compressing Canada','Shirt Denar Haven','overriding Handcrafted Concrete Computer','2021-08-20','2021-08-20','Underpass backing up Maryland','redundant Jordan','Generic Cuban Peso Peso Convertible',29,2742,'Milliards',46455,'Euro',12133,'Neck','2021-08-20 16:54:56','programming Granite','2021-08-21 06:08:31','2021-08-21 14:04:07','2021-08-20','red Mandatory','2021-08-20 17:21:59',NULL,NULL),
(10,'maroon Intelligent PCI','navigating','multi-state','New Mexico Dynamic Ergonomic','2021-08-20','2021-08-19','Djibouti Balanced neural','Cape Verde Escudo','open-source payment',55,53958,'Milliards',64258,'Euro',27719,'transition Developer niches','2021-08-21 05:13:11','Metal','2021-08-20 22:32:37','2021-08-20 19:36:56','2021-08-20','matrices','2021-08-21 08:03:30',NULL,NULL);

/*Table structure for table `jhi_authority` */

DROP TABLE IF EXISTS `jhi_authority`;

CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `jhi_authority` */

insert  into `jhi_authority`(`name`) values 
('ROLE_ADMIN'),
('ROLE_USER');

/*Table structure for table `jhi_persistent_audit_event` */

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;

CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `jhi_persistent_audit_event` */

/*Table structure for table `jhi_persistent_audit_evt_data` */

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;

CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `jhi_persistent_audit_evt_data` */

/*Table structure for table `jhi_user` */

DROP TABLE IF EXISTS `jhi_user`;

CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(191) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(10) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jhi_user` */

insert  into `jhi_user`(`id`,`login`,`password_hash`,`first_name`,`last_name`,`email`,`image_url`,`activated`,`lang_key`,`activation_key`,`reset_key`,`created_by`,`created_date`,`reset_date`,`last_modified_by`,`last_modified_date`) values 
(1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','','','fr',NULL,NULL,'system',NULL,NULL,'system',NULL),
(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','','','fr',NULL,NULL,'system',NULL,NULL,'system',NULL),
(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','','','fr',NULL,NULL,'system',NULL,NULL,'system',NULL),
(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','','','fr',NULL,NULL,'system',NULL,NULL,'system',NULL);

/*Table structure for table `jhi_user_authority` */

DROP TABLE IF EXISTS `jhi_user_authority`;

CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `jhi_user_authority` */

insert  into `jhi_user_authority`(`user_id`,`authority_name`) values 
(1,'ROLE_ADMIN'),
(1,'ROLE_USER'),
(3,'ROLE_ADMIN'),
(3,'ROLE_USER'),
(4,'ROLE_USER');

/*Table structure for table `membre_syndicat` */

DROP TABLE IF EXISTS `membre_syndicat`;

CREATE TABLE `membre_syndicat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rang` varchar(255) DEFAULT NULL,
  `commission` float DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_membre_syndicat_emission_id` (`emission_id`),
  CONSTRAINT `fk_membre_syndicat_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Syndicat membre';

/*Data for the table `membre_syndicat` */

insert  into `membre_syndicat`(`id`,`rang`,`commission`,`operateur`,`date_operation`,`emission_id`) values 
(1,'Chef_file',27846,'Jewelery content','2021-08-21 02:12:06',NULL),
(2,'Membre',57922,'mission-critical application','2021-08-21 07:52:42',NULL),
(3,'Membre',14801,'scale Health analyzer','2021-08-20 19:38:01',NULL),
(4,'Chef_file',53769,'supply-chains hard drive Rustic','2021-08-20 15:23:59',NULL),
(5,'Chef_file',50604,'Paradigm models','2021-08-21 12:56:55',NULL),
(6,'Membre',26245,'payment','2021-08-21 04:21:19',NULL),
(7,'Membre',72860,'Home Plains','2021-08-20 21:46:02',NULL),
(8,'Membre',97011,'Central Fort','2021-08-21 13:17:47',NULL),
(9,'Chef_file',75289,'heuristic','2021-08-21 05:56:05',NULL),
(10,'Chef_file',33785,'Lead grey','2021-08-21 05:39:44',NULL);

/*Table structure for table `nature_titre` */

DROP TABLE IF EXISTS `nature_titre`;

CREATE TABLE `nature_titre` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_nature` varchar(255) NOT NULL,
  `designation_fr` varchar(255) DEFAULT NULL,
  `designation_en` varchar(255) DEFAULT NULL,
  `designation_pt` varchar(255) DEFAULT NULL,
  `nominal_unitaire` bigint(20) NOT NULL,
  `unite_valeur` varchar(255) NOT NULL,
  `nature_echeance` varchar(255) NOT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite NatureTitre';

/*Data for the table `nature_titre` */

insert  into `nature_titre`(`id`,`code_nature`,`designation_fr`,`designation_en`,`designation_pt`,`nominal_unitaire`,`unite_valeur`,`nature_echeance`,`operateur`,`date_operation`) values 
(1,'Metal Lithuanian Litas','Tools','SCSI','Customer-focused compress hard drive',54524,'USD','Jours','Automated Wooden','2021-08-20 21:23:57'),
(2,'Togo rich','Coordinator bluetooth','Brunei Dollar','Health Buckinghamshire',55969,'Euro','Semaines','solution Versatile','2021-08-21 10:35:42'),
(3,'Group','clicks-and-mortar open-source','Frozen','transmitting',71932,'FCFA','Semaines','methodologies','2021-08-20 22:34:06'),
(4,'salmon','ivory users primary','Technician payment','Intelligent Infrastructure',73664,'Euro','Semaines','Towels','2021-08-20 14:45:13'),
(5,'Future Soft','withdrawal Granite syndicate','deposit','withdrawal Cotton Wyoming',22154,'Euro','Semaines','Legacy open-source Rhode Island','2021-08-21 03:29:25'),
(6,'Faroe Islands','synergies Lead Rubber','SMTP clicks-and-mortar Checking Account','Granite',86040,'FCFA','Annees','Product directional','2021-08-21 03:42:29'),
(7,'Director hacking directional','clicks-and-mortar strategize','Parks','Product',83156,'USD','Semaines','Bedfordshire optimize emulation','2021-08-20 22:59:01'),
(8,'Bike Tasty','Grass-roots','Generic Frozen Cheese Agent','Tasty Metal Mouse Sweden',10855,'Euro','Annees','Gorgeous Frozen Cheese','2021-08-20 18:11:27'),
(9,'stable','Nigeria Soft','Berkshire South Dakota','ivory mindshare algorithm',71588,'FCFA','Jours','Bacon','2021-08-20 16:43:30'),
(10,'Investment Account dedicated Data','Small','Personal Loan Account matrix','card',25715,'FCFA','Jours','Rapid','2021-08-20 14:42:54');

/*Table structure for table `onc` */

DROP TABLE IF EXISTS `onc`;

CREATE TABLE `onc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_onc_emission_id` (`emission_id`),
  CONSTRAINT `fk_onc_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Onc';

/*Data for the table `onc` */

insert  into `onc`(`id`,`operateur`,`date_operation`,`emission_id`) values 
(1,'EXE Cambridgeshire utilize','2021-08-20 20:12:12',NULL),
(2,'Branding Legacy','2021-08-21 05:56:15',NULL),
(3,'programming','2021-08-21 13:50:36',NULL),
(4,'Buckinghamshire Senior exploit','2021-08-20 20:32:02',NULL),
(5,'invoice composite Cove','2021-08-20 16:54:32',NULL),
(6,'Bedfordshire','2021-08-21 04:14:43',NULL),
(7,'Savings Account Sleek Concrete Chips yellow','2021-08-21 08:51:44',NULL),
(8,'Wooden Auto Loan Account explicit','2021-08-21 13:16:28',NULL),
(9,'Unbranded Fresh Table','2021-08-21 04:00:09',NULL),
(10,'solid state SCSI','2021-08-20 20:17:56',NULL);

/*Table structure for table `parametres` */

DROP TABLE IF EXISTS `parametres`;

CREATE TABLE `parametres` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse_serveur` varchar(255) DEFAULT NULL,
  `timbre_service_1` varchar(255) DEFAULT NULL,
  `timbre_service_2` varchar(255) DEFAULT NULL,
  `timbre_service_3` varchar(255) DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Parametre';

/*Data for the table `parametres` */

insert  into `parametres`(`id`,`adresse_serveur`,`timbre_service_1`,`timbre_service_2`,`timbre_service_3`,`operateur`,`date_operation`) values 
(1,'interface pink structure','invoice','Chair Libyan Dinar compress','revolutionize portals','Burg input','2021-08-20 14:25:31'),
(2,'Incredible Cotton Pizza','synthesizing','generating','Metrics Central','knowledge user User-friendly SCSI','2021-08-21 02:00:48'),
(3,'algorithm port','Jewelery Chief innovative','Automotive Dynamic','Savings Account','Indian Rupee matrix','2021-08-20 14:52:52'),
(4,'Bedfordshire Mobility Awesome','Sausages mindshare','Gateway firewall','innovate Rial Omani sky blue','tertiary','2021-08-20 15:04:33'),
(5,'synergy','transparent Buckinghamshire','Minnesota Borders','Money Market Account plum JSON','adapter Licensed','2021-08-20 19:26:15'),
(6,'Outdoors','Rustic Soft Chicken Home Loan Account Home Loan Account','Manor RAM','Sleek','Human Organic','2021-08-20 22:54:54'),
(7,'virtual','deposit back-end','Metal','AI Unbranded Incredible','auxiliary','2021-08-20 17:14:02'),
(8,'Technician','Benin Dynamic Chicken','methodologies','Grocery Rhode Island','Michigan Vermont Nepal','2021-08-21 06:23:50'),
(9,'virtual','THX Principal maroon','Total bandwidth Sports','copying','Small Rubber Table','2021-08-21 00:59:28'),
(10,'Albania','plum content-based Buckinghamshire','Plastic','Bedfordshire Bedfordshire e-tailers','Saint Helena Pound','2021-08-21 09:43:07');

/*Table structure for table `pays` */

DROP TABLE IF EXISTS `pays`;

CREATE TABLE `pays` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_pays` varchar(255) DEFAULT NULL,
  `designation_fr` varchar(255) DEFAULT NULL,
  `designation_en` varchar(255) DEFAULT NULL,
  `designation_pt` varchar(255) DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Pays';

/*Data for the table `pays` */

insert  into `pays`(`id`,`code_pays`,`designation_fr`,`designation_en`,`designation_pt`,`operateur`,`date_operation`) values 
(1,'Outdoors Multi-channelled','Cambridgeshire','applications navigate','Metal Neck monitoring','Concrete','2021-08-20 21:15:17'),
(2,'global Streamlined Borders','SDR Singapore Dollar','auxiliary Toys Regional','Trace Shoal Beauty','e-markets','2021-08-20 15:10:07'),
(3,'Engineer Lights Pennsylvania','bypassing Licensed Frozen Shirt','Gourde US Dollar Re-contextualized Re-contextualized','Nebraska AGP','Bolivar Fuerte','2021-08-21 08:18:47'),
(4,'microchip circuit','Barbados Dollar','Chips','superstructure','Fully-configurable fault-tolerant','2021-08-21 01:36:16'),
(5,'payment Sleek Soft Fish embrace','Bacon','Indiana','CSS California','Sudan stable Sleek Rubber Chips','2021-08-20 17:57:57'),
(6,'New Taiwan Dollar solution-oriented synthesize','Chips holistic Sri Lanka Rupee','Michigan protocol Generic','projection','RSS Intelligent transform','2021-08-20 14:56:06'),
(7,'system','Ergonomic','Home Executive','Direct Brazil','Investor Marketing','2021-08-21 08:34:39'),
(8,'orange Spring driver','GB','e-services Automated Frozen','Tunnel visionary primary','bluetooth bypassing','2021-08-21 09:23:38'),
(9,'Turkmenistan optical Money Market Account','haptic','connecting calculating','Avon Movies','open-source Producer moderator','2021-08-21 08:22:10'),
(10,'Well Bedfordshire','mobile redundant Human','Botswana monitor functionalities','Tasty Wooden Shirt','deposit','2021-08-20 15:18:45');

/*Table structure for table `rachat` */

DROP TABLE IF EXISTS `rachat`;

CREATE TABLE `rachat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_valeur` varchar(255) NOT NULL,
  `designation_fr` varchar(255) DEFAULT NULL,
  `designation_en` varchar(255) DEFAULT NULL,
  `designation_pt` varchar(255) DEFAULT NULL,
  `date_emission` date NOT NULL,
  `taux_interet` float NOT NULL,
  `montant_nominal` double NOT NULL,
  `unite_montant` varchar(255) NOT NULL,
  `devise` varchar(255) NOT NULL,
  `date_echeance` date NOT NULL,
  `date_valeur` date DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rachat_emission_id` (`emission_id`),
  CONSTRAINT `fk_rachat_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Rachat';

/*Data for the table `rachat` */

insert  into `rachat`(`id`,`code_valeur`,`designation_fr`,`designation_en`,`designation_pt`,`date_emission`,`taux_interet`,`montant_nominal`,`unite_montant`,`devise`,`date_echeance`,`date_valeur`,`operateur`,`date_operation`,`emission_id`) values 
(1,'XSS','indigo','reintermediate','yellow Yemeni Rial','2021-08-20',53,77988,'Milliards','FCFA','2021-08-20','2021-08-19','quantifying','2021-08-20 23:19:49',NULL),
(2,'Avon non-volatile','Arizona Gorgeous','database Credit Card Account Multi-layered','Villages','2021-08-19',7,63920,'Milles','Euro','2021-08-20','2021-08-20','harness bypass','2021-08-20 21:25:23',NULL),
(3,'radical Cambridgeshire Personal Loan Account','B2B Personal Loan Account','United Arab Emirates responsive','Villages reciprocal copying','2021-08-20',97,85074,'Milles','FCFA','2021-08-20','2021-08-19','magenta orange Liaison','2021-08-21 00:52:11',NULL),
(4,'Cliff RAM','Libyan Arab Jamahiriya Fundamental Highway','Oklahoma primary infomediaries','Rubber','2021-08-19',69,37250,'Milliards','USD','2021-08-20','2021-08-20','interface Avon Incredible','2021-08-21 11:58:40',NULL),
(5,'Checking Account conglomeration','HTTP multi-tasking','plum Rustic Sleek','Identity 1080p','2021-08-20',95,92441,'Milliards','Euro','2021-08-20','2021-08-20','Burkina Faso Data','2021-08-21 00:59:59',NULL),
(6,'green relationships','Serbia','Frozen Hat','Handmade Representative','2021-08-20',62,71061,'Millions','Euro','2021-08-20','2021-08-19','Computers District','2021-08-20 21:36:09',NULL),
(7,'Infrastructure capability','Incredible Plastic Chips firewall Usability','Shoes Markets motivating','function Central Music','2021-08-20',43,18058,'Milliards','FCFA','2021-08-20','2021-08-20','optimal Incredible Concrete Pizza parsing','2021-08-20 23:18:21',NULL),
(8,'Aruban Guilder Industrial e-business','Granite','Cotton withdrawal California','Soap','2021-08-20',78,69869,'Millions','FCFA','2021-08-19','2021-08-19','Taiwan Kentucky parsing','2021-08-21 07:56:24',NULL),
(9,'Grove Sri Lanka','Investment Account plum','Alaska coherent Devolved','cohesive bypass','2021-08-19',35,17554,'Milliards','USD','2021-08-20','2021-08-20','Bacon middleware Facilitator','2021-08-20 18:15:12',NULL),
(10,'Chief Tools invoice','auxiliary ADP Rustic Concrete Car','Engineer','Beauty Quality back up','2021-08-20',64,46750,'Millions','USD','2021-08-20','2021-08-20','evolve payment Personal Loan Account','2021-08-21 02:14:13',NULL);

/*Table structure for table `reouverture` */

DROP TABLE IF EXISTS `reouverture`;

CREATE TABLE `reouverture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_valeur` varchar(255) NOT NULL,
  `designation_fr` varchar(255) DEFAULT NULL,
  `designation_en` varchar(255) DEFAULT NULL,
  `designation_pt` varchar(255) DEFAULT NULL,
  `date_emission` date NOT NULL,
  `taux_interet` float NOT NULL,
  `encours_emission` double NOT NULL,
  `unite_volume` varchar(255) NOT NULL,
  `montant_sollicite` double NOT NULL,
  `unite_montant` varchar(255) NOT NULL,
  `devise` varchar(255) NOT NULL,
  `date_echeance` date NOT NULL,
  `date_valeur` date DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reouverture_emission_id` (`emission_id`),
  CONSTRAINT `fk_reouverture_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Reouverture';

/*Data for the table `reouverture` */

insert  into `reouverture`(`id`,`code_valeur`,`designation_fr`,`designation_en`,`designation_pt`,`date_emission`,`taux_interet`,`encours_emission`,`unite_volume`,`montant_sollicite`,`unite_montant`,`devise`,`date_echeance`,`date_valeur`,`operateur`,`date_operation`,`emission_id`) values 
(1,'Technician','Shoes','invoice','Rwanda Franc','2021-08-20',92,84176,'Milles',30714,'Millions','Euro','2021-08-20','2021-08-20','Division Plastic','2021-08-21 10:46:56',NULL),
(2,'Reduced','maximized Kiribati index','software Grocery','repurpose payment Technician','2021-08-19',61,97138,'Milliards',99112,'Millions','USD','2021-08-19','2021-08-20','synthesizing challenge deploy','2021-08-21 04:29:17',NULL),
(3,'SMTP scalable','Savings Account projection payment','Cambridgeshire','utilize','2021-08-20',47,44882,'Milles',6300,'Milliards','FCFA','2021-08-19','2021-08-19','hack indexing payment','2021-08-21 09:04:30',NULL),
(4,'Up-sized turquoise','Tasty Central Investor','Pa\'anga Bike','Chicken Decentralized','2021-08-19',98,9514,'Milliards',29898,'Milles','Euro','2021-08-19','2021-08-20','Checking Account Sausages Berkshire','2021-08-21 01:18:43',NULL),
(5,'enable','Pitcairn Islands redundant','New Hampshire Awesome','Gloves','2021-08-19',94,18943,'Milles',98573,'Millions','FCFA','2021-08-19','2021-08-20','holistic Lempira Holy See (Vatican City State)','2021-08-20 20:42:01',NULL),
(6,'moratorium','RSS open system','open-source maroon','SDD Clothing turquoise','2021-08-19',46,43132,'Milliards',74456,'Milles','FCFA','2021-08-20','2021-08-20','Handcrafted Metal Pizza compress','2021-08-20 17:18:25',NULL),
(7,'Cambridgeshire Borders','New York','matrix Home Loan Account','Unbranded Metal Towels evolve','2021-08-20',27,47270,'Milles',48552,'Millions','USD','2021-08-19','2021-08-19','salmon','2021-08-20 22:36:10',NULL),
(8,'Investment Account virtual HDD','quantifying','Sports Somali Shilling unleash','Lead Mountain','2021-08-20',88,40431,'Millions',60126,'Milles','Euro','2021-08-19','2021-08-20','synthesizing override','2021-08-20 21:05:08',NULL),
(9,'Web Azerbaijanian Manat','Soft','Soap','olive Tasty','2021-08-20',83,30086,'Millions',60697,'Milles','USD','2021-08-19','2021-08-19','transmit','2021-08-21 07:42:33',NULL),
(10,'payment maximize','invoice Dobra','drive','Executive Bedfordshire','2021-08-20',33,43785,'Milliards',72020,'Milles','Euro','2021-08-20','2021-08-20','Lao People\'s Democratic Republic Curve','2021-08-20 17:15:20',NULL);

/*Table structure for table `resultat` */

DROP TABLE IF EXISTS `resultat`;

CREATE TABLE `resultat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nbre_svt_total` int(11) DEFAULT NULL,
  `nbre_svt_soumis` int(11) DEFAULT NULL,
  `montant_tresor` double NOT NULL,
  `unite_tresor` varchar(255) DEFAULT NULL,
  `montant_soumis` double NOT NULL,
  `unite_soumis` varchar(255) DEFAULT NULL,
  `montant_servi` double NOT NULL,
  `nbre_titre_total` bigint(20) DEFAULT NULL,
  `nbre_titre_soumis` bigint(20) DEFAULT NULL,
  `taux_min_propose` float DEFAULT NULL,
  `taux_max_propose` float DEFAULT NULL,
  `taux_limite` float DEFAULT NULL,
  `taux_interet_moyen` float DEFAULT NULL,
  `taux_rendement_moyen` float DEFAULT NULL,
  `taux_couverture` float DEFAULT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  `resultat_id` bigint(20) DEFAULT NULL,
  `rachat_id` bigint(20) DEFAULT NULL,
  `onc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_resultat_emission_id` (`emission_id`),
  KEY `fk_resultat_resultat_id` (`resultat_id`),
  KEY `fk_resultat_rachat_id` (`rachat_id`),
  KEY `fk_resultat_onc_id` (`onc_id`),
  CONSTRAINT `fk_resultat_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`),
  CONSTRAINT `fk_resultat_onc_id` FOREIGN KEY (`onc_id`) REFERENCES `onc` (`id`),
  CONSTRAINT `fk_resultat_rachat_id` FOREIGN KEY (`rachat_id`) REFERENCES `rachat` (`id`),
  CONSTRAINT `fk_resultat_resultat_id` FOREIGN KEY (`resultat_id`) REFERENCES `reouverture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Resultat';

/*Data for the table `resultat` */

insert  into `resultat`(`id`,`nbre_svt_total`,`nbre_svt_soumis`,`montant_tresor`,`unite_tresor`,`montant_soumis`,`unite_soumis`,`montant_servi`,`nbre_titre_total`,`nbre_titre_soumis`,`taux_min_propose`,`taux_max_propose`,`taux_limite`,`taux_interet_moyen`,`taux_rendement_moyen`,`taux_couverture`,`emission_id`,`resultat_id`,`rachat_id`,`onc_id`) values 
(1,85852,97181,2260,'Milliards',40871,'Millions',46407,29395,45738,51,97,31,78,78,34,NULL,NULL,NULL,NULL),
(2,49860,9608,40369,'Milliards',15862,'Millions',94989,7357,86567,61,38,47,51,93,14,NULL,NULL,NULL,NULL),
(3,73923,5357,5698,'Milles',56834,'Milles',73051,51839,93615,10,7,58,6,37,4,NULL,NULL,NULL,NULL),
(4,50626,8098,89373,'Milliards',92725,'Milles',29981,10506,73965,3,23,74,18,22,80,NULL,NULL,NULL,NULL),
(5,94092,66114,74987,'Millions',22137,'Millions',70095,69447,3848,32,29,87,23,53,52,NULL,NULL,NULL,NULL),
(6,15709,77666,98475,'Milles',42102,'Milles',53963,7159,11648,78,75,20,63,83,35,NULL,NULL,NULL,NULL),
(7,78045,61088,81912,'Milliards',5952,'Millions',40384,39931,5654,13,92,56,76,47,62,NULL,NULL,NULL,NULL),
(8,19478,15505,18810,'Milles',63989,'Millions',16372,83220,22298,21,26,11,24,70,57,NULL,NULL,NULL,NULL),
(9,34101,72898,30339,'Milliards',24244,'Millions',76292,67026,42563,53,20,31,79,90,94,NULL,NULL,NULL,NULL),
(10,63600,83253,26001,'Milliards',96612,'Millions',79600,48532,64176,61,1,81,5,9,80,NULL,NULL,NULL,NULL);

/*Table structure for table `soumission` */

DROP TABLE IF EXISTS `soumission`;

CREATE TABLE `soumission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `num_anonymat` varchar(255) NOT NULL,
  `date_soumission` datetime NOT NULL,
  `nbre_soumission` int(11) DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `emission_id` bigint(20) DEFAULT NULL,
  `reouverture_id` bigint(20) DEFAULT NULL,
  `rachat_id` bigint(20) DEFAULT NULL,
  `onc_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_soumission_emission_id` (`emission_id`),
  KEY `fk_soumission_reouverture_id` (`reouverture_id`),
  KEY `fk_soumission_rachat_id` (`rachat_id`),
  KEY `fk_soumission_onc_id` (`onc_id`),
  CONSTRAINT `fk_soumission_emission_id` FOREIGN KEY (`emission_id`) REFERENCES `emission` (`id`),
  CONSTRAINT `fk_soumission_onc_id` FOREIGN KEY (`onc_id`) REFERENCES `onc` (`id`),
  CONSTRAINT `fk_soumission_rachat_id` FOREIGN KEY (`rachat_id`) REFERENCES `rachat` (`id`),
  CONSTRAINT `fk_soumission_reouverture_id` FOREIGN KEY (`reouverture_id`) REFERENCES `reouverture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Soumission';

/*Data for the table `soumission` */

insert  into `soumission`(`id`,`num_anonymat`,`date_soumission`,`nbre_soumission`,`operateur`,`date_operation`,`emission_id`,`reouverture_id`,`rachat_id`,`onc_id`) values 
(1,'copying Awesome Granite Tuna open-source','2021-08-20 14:53:09',52938,'Beauty Incredible Granite Pizza','2021-08-20 15:59:43',NULL,NULL,NULL,NULL),
(2,'Games Regional Buckinghamshire','2021-08-21 07:29:52',6260,'Parkways challenge EXE','2021-08-21 06:12:08',NULL,NULL,NULL,NULL),
(3,'Borders','2021-08-20 16:16:24',20071,'application Grocery Nicaragua','2021-08-20 17:23:40',NULL,NULL,NULL,NULL),
(4,'Rhode Island Auto Loan Account','2021-08-21 12:41:18',51318,'calculating','2021-08-20 21:58:35',NULL,NULL,NULL,NULL),
(5,'Credit Card Account technologies','2021-08-21 01:08:26',1207,'South Georgia and the South Sandwich Islands orchestrate','2021-08-21 03:45:37',NULL,NULL,NULL,NULL),
(6,'strategy e-tailers Incredible Concrete Tuna','2021-08-20 15:21:28',759,'redundant scalable leverage','2021-08-21 05:39:07',NULL,NULL,NULL,NULL),
(7,'invoice','2021-08-21 03:38:01',56909,'Personal Loan Account','2021-08-21 06:29:52',NULL,NULL,NULL,NULL),
(8,'Towels Shoes schemas','2021-08-20 18:06:43',95980,'PCI Palau Table','2021-08-20 14:13:56',NULL,NULL,NULL,NULL),
(9,'Tools responsive Polarised','2021-08-20 18:59:36',27929,'bypassing','2021-08-21 01:50:31',NULL,NULL,NULL,NULL),
(10,'Streamlined','2021-08-21 12:46:18',65671,'Granite users Producer','2021-08-20 21:43:42',NULL,NULL,NULL,NULL);

/*Table structure for table `svt` */

DROP TABLE IF EXISTS `svt`;

CREATE TABLE `svt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abreviation_fr` varchar(255) DEFAULT NULL,
  `abreviation_en` varchar(255) DEFAULT NULL,
  `abreviation_pt` varchar(255) DEFAULT NULL,
  `designation_fr` varchar(255) NOT NULL,
  `designation_en` varchar(255) DEFAULT NULL,
  `etat` varchar(255) DEFAULT NULL,
  `operateur` varchar(255) NOT NULL,
  `date_operation` datetime NOT NULL,
  `pays_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_svt_pays_id` (`pays_id`),
  CONSTRAINT `fk_svt_pays_id` FOREIGN KEY (`pays_id`) REFERENCES `pays` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='Entite Svt';

/*Data for the table `svt` */

insert  into `svt`(`id`,`abreviation_fr`,`abreviation_en`,`abreviation_pt`,`designation_fr`,`designation_en`,`etat`,`operateur`,`date_operation`,`pays_id`) values 
(1,'Balboa US Dollar','UIC-Franc Small','Tenge','Health Pennsylvania Security','Implementation THX program','Agree','Principal','2021-08-20 19:47:06',NULL),
(2,'calculating compressing Architect','Tuna Sleek','exploit experiences','Money Market Account out-of-the-box','eco-centric','Suspendu','Corporate','2021-08-21 07:34:41',NULL),
(3,'Communications Investment Account Licensed Steel Ball','Implementation Metal online','Steel overriding','Sleek Flats','plum back up implementation','Agree','Checking Account','2021-08-21 03:46:52',NULL),
(4,'digital','SSL Research','Generic Frozen Pizza Ohio','Buckinghamshire Berkshire','Multi-channelled Concrete','Suspendu','auxiliary Small','2021-08-20 16:20:35',NULL),
(5,'invoice','bandwidth-monitored 6th generation Sausages','Industrial e-services','Outdoors','zero tolerance Bike','Agree','Wisconsin Customer Buckinghamshire','2021-08-21 02:01:29',NULL),
(6,'Streamlined New Israeli Sheqel','Automotive','connecting District Optimization','web-enabled driver','hacking aggregate Functionality','Suspendu','Investment Account Avon','2021-08-20 15:39:39',NULL),
(7,'complexity','Gorgeous white','Buckinghamshire scalable Concrete','enhance Som analyzer','overriding Ball well-modulated','Agree','connect SQL','2021-08-21 02:05:27',NULL),
(8,'Grove','action-items','override','bandwidth','Concrete','Agree','primary generating Outdoors','2021-08-21 13:25:02',NULL),
(9,'driver','Cordoba Oro Fantastic Wooden Computer Assistant','Jamaica','Representative Seychelles','Cheese Intelligent Branch','Suspendu','Chair','2021-08-21 00:57:50',NULL),
(10,'Tactics deposit','AI bypassing Quality','Hat Intelligent Steel Fish white','Small','Cambridgeshire Computers SMTP','Agree','deposit Tasty','2021-08-21 11:56:40',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
