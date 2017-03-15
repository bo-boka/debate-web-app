DROP DATABASE IF EXISTS TestUserDB;
CREATE DATABASE TestUserDB;

USE TestUserDB;

CREATE TABLE IF NOT EXISTS `users` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(25) NOT NULL,
	`password` varchar(20) NOT NULL,
    `first_name` varchar(20) NOT NULL,
    `last_name` varchar(20) NOT NULL,
    `email` varchar(30) NOT NULL,
    `wins` int(11),
    `ties` int(11),
    `engages` int(11),
	`enabled` tinyint(1) NOT NULL,
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `username` (`username`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
INSERT INTO `users` (`user_id`, `username`, `password`, `first_name`, `last_name`, `email`, `wins`, `ties`, `engages`, `enabled`) 
VALUES (1, 'debatinNotHatin', 'password', 'Ally', 'Smith', 'asmith@debator.com', 47, 10, 60, 1),
	(2, '2truDebator', 'password', 'Bob', 'Gualla', 'email@sendit.com', 10, 3, 40, 1),
    (3, 'cheesinForTheWeekend', 'password', 'Lana', 'Brown', 'email@sendin.com', 13, 3, 30, 1),
    (4, 'mdb8r', 'password', 'Arielle', 'Cho', 'thisemail@gmail.com', 13, 10, 25, 1),
    (5, 'snowOwl22', 'password', 'Gertrude', 'Hammerquist', 'hammyq@gmail.com', 4, 3, 8, 1),
    (6, 'sawadeeka', 'password', 'Juan', 'Jimenez', 'jimenez@gmail.com', 5, 4, 10, 1),
	(7, 'smoothDeb', 'password', 'Neena', 'Gupta', 'neenag@gmail.com', 2, 2, 7, 1);
 
 
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(25) NOT NULL,
 `authority` varchar(20) NOT NULL,
 UNIQUE KEY `username` (`username`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
 
INSERT INTO `authorities` (`username`, `authority`) 
VALUES ('debatinNotHatin', 'ROLE_ADMIN'),
	('2truDebator', 'ROLE_USER'),
	('cheesinForTheWeekend', 'ROLE_USER'),
	('mdb8r', 'ROLE_USER'),
	('snowOwl22', 'ROLE_USER'),
	('sawadeeka', 'ROLE_USER'),
	('smoothDeb', 'ROLE_ADMIN');


CREATE TABLE IF NOT EXISTS debates(
	`debate_id` int(11) NOT NULL AUTO_INCREMENT,
    `resolution` varchar(150) NOT NULL,
    `content` text,
    `status_id` int(2),
    `affirmativeUser_id` int(11),
    `negativeUser_id` int(11),
    `proVotes` int(11),
    `conVotes` int(11),
    `category_id` int(11),
    `date` date,
    `published` boolean,
    PRIMARY KEY (`debate_id`)
);

INSERT INTO debates (`debate_id`, `resolution`, `content`, `status_id`, `affirmativeUser_id`, `negativeUser_id`, `proVotes`, `conVotes`, `category_id`, `date`, `published`) 
VALUES 
	(1, 'DRY is good.', 'none necessary', 1, 1, null, null, null, 1, '2017-02-21', true),
	(2, 'Islands are being eaten by the sea.', 'anthropogenic global warming', 1, 2, null, null, null, 2, '2017-02-22', true),
    (3, 'Elliot Smith is better than Bob Dylan.', 'sd;alejfjkskjs', 2, 1, 3, null, null, 4, '2017-01-18', true),
    (4, 'Going vegan is substantially better for the environment.', 'sleirtysodldfksnd', 1, 3, null, null, null, 3, '2016-11-15', false),
    (5, 'Capitalism is actually pretty inefficient.', 'dlsfeisrusanc,cmxjsjks.', 2, 2, 3, null, null, 1, '2016-04-11', true);

CREATE TABLE IF NOT EXISTS `deb_statuses` (
	`status_id` int(2) NOT NULL,
    `status` varchar(10) UNIQUE,
    PRIMARY KEY (`status_id`)
);

INSERT INTO `deb_statuses` (`status_id`, `status`)
VALUES (1, 'intro'),
	(2, 'live'),
	(3, 'proWon'),
    (4, 'conWon'),
    (5, 'wash');

CREATE TABLE IF NOT EXISTS `rebuttals`(
	`rebuttal_id` int(11) NOT NULL AUTO_INCREMENT,
    `content` text NOT NULL,
    `user_id` int(11),
    `debate_id` int(11) NOT NULL,
    `type_id` int(2),
    `date` date,
    `position` boolean,
	PRIMARY KEY (`rebuttal_id`)
);

INSERT INTO `rebuttals` (`rebuttal_id`, `content`, `user_id`, `debate_id`, `type_id`, `date`, `position`) 
VALUES (1, 'You''re wrong, he''s great', 3, 3, 1, '2017-02-22', false),
    (2, 'I''m not wrong. He sings pretty.', 1, 3, 2, '2017-02-20', true),
    (3, 'dkaj;dklsoieioarur', 3, 5, 1, '2016-4-26', false);

CREATE TABLE IF NOT EXISTS `reb_types` (
	`type_id` int(2) NOT NULL,
    `type` varchar(13) UNIQUE,
    PRIMARY KEY (`type_id`)
);

INSERT INTO `reb_types` (`type_id`, `type`)
VALUES (1, 'challenge'),
	(2, 'refutation'),
    (3, 'clarification'),
    (4, 'closing');
  
CREATE TABLE IF NOT EXISTS `categories` (
	`category_id` int(11) NOT NULL AUTO_INCREMENT,
    `category` varchar(25) UNIQUE,
    PRIMARY KEY (`category_id`)
);

INSERT INTO `categories` (`category_id`, `category`)
VALUES (1, 'politics'),
	(2, 'philosophy'),
    (3, 'music'),
    (4, 'recreation'),
    (5, 'ethics'),
    (6, 'marijuana'),
    (7, 'cats'),
    (8, 'books'),
    (9, 'veganism'),
    (10, 'economics'),
    (11, 'science'),
    (12, 'art'),
    (13, 'society'),
    (14, 'history');

CREATE TABLE IF NOT EXISTS `deb_sources` (
	`debate_id` int(11) NOT NULL,
    `source` varchar(200),
    KEY (`debate_id`)
);

INSERT INTO `deb_sources` (`debate_id`, `source`)
VALUES (1, 'http://www.truefacts.com'),
	(1, 'http://sciencestuff.com'),
    (2, 'http://sciencestuff.com'),
    (2, 'http://learnsupm.com');


CREATE TABLE IF NOT EXISTS `reb_sources` (
	`rebuttal_id` int(11) NOT NULL,
    `source` varchar(200),
    KEY (`rebuttal_id`)
);

INSERT INTO `reb_sources` (`rebuttal_id`, `source`)
VALUES (1, 'http://www.citingthings.com'),
	(1, 'http://truethings.com'),
    (2, 'http://peerreview.com'),
    (2, 'http://yayscience.com');
 
 
ALTER TABLE `authorities`
	ADD CONSTRAINT `authorities_users_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
    
ALTER TABLE `debates`
	ADD CONSTRAINT `debates_users_fk` FOREIGN KEY (`affirmativeUser_id`) REFERENCES `users` (`user_id`);
    
ALTER TABLE `debates`
	ADD CONSTRAINT `debates_categories_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`);

ALTER TABLE `debates`
	ADD CONSTRAINT `debates_statuses_fk` FOREIGN KEY (`status_id`) REFERENCES `deb_statuses` (`status_id`);

ALTER TABLE `rebuttals`
	ADD CONSTRAINT `rebuttals_debates_fk` FOREIGN KEY (`debate_id`) REFERENCES `debates` (`debate_id`);

ALTER TABLE `rebuttals`
	ADD CONSTRAINT `rebuttals_types_fk` FOREIGN KEY (`type_id`) REFERENCES `reb_types` (`type_id`);

/*ALTER TABLE `deb_sources`
	ADD CONSTRAINT `deb_sources_debates_fk` FOREIGN KEY (`debate_id`) REFERENCES `debates` (`debate_id`);

ALTER TABLE `reb_sources`
	ADD CONSTRAINT `reb_sources_debates_fk` FOREIGN KEY (`rebuttal_id`) REFERENCES `rebuttals` (`rebuttal_id`);
*/
