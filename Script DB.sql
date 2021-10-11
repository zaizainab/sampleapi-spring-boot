CREATE TABLE `files` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` blob NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `genre` varchar(45) DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_updated_by` varchar(45) DEFAULT NULL,
  `last_updated_date` datetime DEFAULT NULL,
  `moviescol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `bookscol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (3,'Home Alone','Home Alone (stylized as HOME ALONe) is a 1990 American comedy film directed by Chris Columbus and written by John Hughes.','Family','admin',NULL,'admin',NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (4,'Sinkhole','Sinkhole is a 2021 South Korean disaster comedy film, directed by Kim Ji-hoon and starring Cha Seung-won, Kim Sung-kyun, Lee Kwang-soo and Kim Hye-jun. It is about an average man\'s house, that took 11 years to obtain, but going down a sinkhole 500 meters underground in a minute.','Disaster','admin',NULL,'admin',NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (6,'Sherlock Holmes: A Game of Shadows','Sherlock Holmes: A Game of Shadows is a 2011 period mystery action film directed by Guy Ritchie and produced by Joel Silver, Lionel Wigram, Susan Downey, and Dan Lin.[4] It is the sequel to the 2009 film Sherlock Holmes and features the Sherlock Holmes and Dr. John Watson characters created by Sir Arthur Conan Doyle.','Thriller','admin',NULL,'admin',NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (7,'Miracle in Cell No. 7','Miracle in Cell No. 7 is a 2013 South Korean comedy-drama film starring Ryu Seung-ryong, Kal So-won and Park Shin-hye. The film is about a mentally challenged man wrongfully imprisoned for murder, who builds friendships with the hardened criminals in his cell, who in return help him see his daughter again by smuggling her into the prison.','Family','admin',NULL,'admin',NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (8,'string2','string2 desc','string','string',NULL,'string2',NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (9,'Test movie 20210930_03:19','Test description 20210930_03:19','Test Genre','Test User','2021-09-30 03:19:27','Test User','2021-09-30 03:19:27',NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (10,'Test movie 20210930_03:19','Test description 20210930_03:19','Test Genre','Test User','2021-09-30 03:19:32','Test User','2021-09-30 03:19:32',NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (11,'Test movie 20210930_03:19','Test description 20210930_03:19','Test Genre','Test User','2021-09-30 03:19:37','Test User','2021-09-30 03:19:37',NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (15,'Home Alone 2','desc2','Family','upload user','2021-10-04 06:51:34',NULL,NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (16,'Sinkhole 2','desc2','Disaster','upload user','2021-10-04 06:51:37',NULL,NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (17,'Midnight','desc2','Thriller','upload user','2021-10-04 06:51:44',NULL,NULL,NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (18,'Escape Room',NULL,'Thriller','redis','2021-10-06 09:27:53','redis','2021-10-06 09:27:53',NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (19,'Escape Room 2','string','Thriller','redis','2021-10-06 10:03:39','redis','2021-10-06 10:03:39',NULL);
INSERT INTO `` (`id`,`name`,`description`,`genre`,`created_by`,`created_date`,`last_updated_by`,`last_updated_date`,`moviescol`) VALUES (20,'Escape Room 3','string','Thriller','redis','2021-10-06 10:04:10','redis','2021-10-06 10:04:10',NULL);


