--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `individual` bit(1) NOT NULL,
  `name` varchar(32) NOT NULL,
  `place` varchar(128) NOT NULL,
  `situation` smallint(6) NOT NULL,
  `time` datetime NOT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKal25hg2t6xld4qm3iidly93m4` (`type_id`),
  CONSTRAINT `FKal25hg2t6xld4qm3iidly93m4` FOREIGN KEY (`type_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `competition_team_competitions`
--

DROP TABLE IF EXISTS `competition_team_competitions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_team_competitions` (
  `competition_id` int(11) NOT NULL,
  `team_competitions_id` int(11) NOT NULL,
  PRIMARY KEY (`competition_id`,`team_competitions_id`),
  UNIQUE KEY `UK_kuymsu1ubvghk8hpxo37xt8hm` (`team_competitions_id`),
  CONSTRAINT `FK340ttg3x7c2ssbgouendtbtvq` FOREIGN KEY (`team_competitions_id`) REFERENCES `team_competition` (`id`),
  CONSTRAINT `FK5274rdror2bqpq6jvlnva8emm` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delegation`
--

DROP TABLE IF EXISTS `delegation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delegation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `continent` smallint(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `individual`
--

DROP TABLE IF EXISTS `individual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individual` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(32) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `sex` bit(1) NOT NULL,
  `delegation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK17ikniy6sll8vwqxhp0lsvvim` (`delegation_id`),
  CONSTRAINT `FK17ikniy6sll8vwqxhp0lsvvim` FOREIGN KEY (`delegation_id`) REFERENCES `delegation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `individual_competition`
--

DROP TABLE IF EXISTS `individual_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individual_competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rank` smallint(6) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `competition_id` int(11) DEFAULT NULL,
  `individual_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdk2xtg0allh2jmtsejwgmyqnb` (`competition_id`),
  KEY `FK5yfco885ex4mewktc3kgrbvei` (`individual_id`),
  CONSTRAINT `FK5yfco885ex4mewktc3kgrbvei` FOREIGN KEY (`individual_id`) REFERENCES `individual` (`id`),
  CONSTRAINT `FKdk2xtg0allh2jmtsejwgmyqnb` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `individual_teams`
--

DROP TABLE IF EXISTS `individual_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `individual_teams` (
  `individuals_id` int(11) NOT NULL,
  `teams_id` int(11) NOT NULL,
  PRIMARY KEY (`individuals_id`,`teams_id`),
  KEY `FKnhsjhm5hd8ao8g8em3uc6iqoy` (`teams_id`),
  CONSTRAINT `FKnhsjhm5hd8ao8g8em3uc6iqoy` FOREIGN KEY (`teams_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKp32oxkoh19u5ov5dymk7uas5n` FOREIGN KEY (`individuals_id`) REFERENCES `individual` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `type_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `sex` char(1) NOT NULL,
  `delegation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnhe34dmat7g4y458xdid77upx` (`delegation_id`),
  CONSTRAINT `FKnhe34dmat7g4y458xdid77upx` FOREIGN KEY (`delegation_id`) REFERENCES `delegation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `team_competition`
--

DROP TABLE IF EXISTS `team_competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rank` smallint(6) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `competition_id` int(11) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKegrcyt175igj8599hpv5hk5b` (`competition_id`),
  KEY `FKk9fuvx4optv4p5tlmnw3lof5a` (`team_id`),
  CONSTRAINT `FKegrcyt175igj8599hpv5hk5b` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`),
  CONSTRAINT `FKk9fuvx4optv4p5tlmnw3lof5a` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;