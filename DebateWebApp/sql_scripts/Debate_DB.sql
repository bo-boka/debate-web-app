DROP DATABASE IF EXISTS DebateDB;
CREATE DATABASE DebateDB;

USE DebateDB;

CREATE TABLE IF NOT EXISTS `users` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(25) NOT NULL,
	`password` varchar(20) NOT NULL,
    `first_name` varchar(20) NOT NULL,
    `last_name` varchar(20) NOT NULL,
    `email` varchar(30) NOT NULL,
    `wins` int(11),
    `ties` int(11),
    `losses` int(11),
	`enabled` tinyint(1) NOT NULL,
	PRIMARY KEY (`user_id`),
	UNIQUE KEY `username` (`username`)
);
 
INSERT INTO `users` (`user_id`, `username`, `password`, `first_name`, `last_name`, `email`, `wins`, `ties`, `losses`, `enabled`) 
VALUES (1, 'debatinNotHatin', 'password', 'Ally', 'Smith', 'asmith@debator.com', 47, 10, 20, 1),
	(2, 'X2truDebatorX', 'password', 'Bob', 'Gualla', 'email@sendit.com', 10, 3, 10, 1),
    (3, 'cheesinForTheWeekend', 'password', 'Lana', 'Brown', 'email@sendin.com', 13, 7, 3, 1),
    (4, 'mdb8r', 'password', 'Arielle', 'Cho', 'thisemail@gmail.com', 13, 10, 25, 1),
    (5, 'snowOwl22', 'password', 'Gertrude', 'Hammerquist', 'hammyq@gmail.com', 14, 3, 8, 1),
	(6, 'sawadeeka', 'password', 'Juan', 'Jimenez', 'jimenez@gmail.com', 5, 4, 10, 1),
	(7, 'SmoothDeb', 'password', 'Neena', 'Gupta', 'neenag@gmail.com', 12, 2, 7, 1),
    (8, 'CoolAidComrade', 'password', 'Jenson', 'Hearthrow', 'jedeez@gmail.com', 11, 3, 0, 1),
    (9, 'KilGore', 'password', 'Polly', 'Kilgore', 'dlsjfd@jl.com', 30, 12, 10, 1),
    (10, 'trash_monster', 'password', 'Sal', 'Trevnal', 'kjdf@gmail.com', 9, 4, 13, 1),
    (11, 'alaDDin', 'password', 'Trevor', 'OMalley', 'djlk@gmail.com', 3, 0, 1, 1),
    (12, 'xXbreakdoWnz4lifeXx', 'password', 'Fran', 'Snape', 'lkf@gmail.com', 50, 6, 8, 1),
    (13, 'synthWave_Rider', 'password', 'Loni', 'Bramer', 'jld@gmail.com', 70, 10, 20, 1);
 
 
CREATE TABLE IF NOT EXISTS `authorities` (
 `username` varchar(25) NOT NULL,
 `authority` varchar(20) NOT NULL,
 UNIQUE KEY `username` (`username`)
);
 
INSERT INTO `authorities` (`username`, `authority`) 
VALUES ('debatinNotHatin', 'ROLE_ADMIN'),
	('X2truDebatorX', 'ROLE_USER'),
	('cheesinForTheWeekend', 'ROLE_USER'),
	('mdb8r', 'ROLE_USER'),
	('snowOwl22', 'ROLE_USER'),
	('sawadeeka', 'ROLE_USER'),
    ('CoolAidComrade', 'ROLE_USER'),
    ('KilGore', 'ROLE_USER'),
    ('trash_monster', 'ROLE_USER'),
    ('alaDDin', 'ROLE_USER'),
    ('xXbreakdoWnz4lifeXx', 'ROLE_USER'),
    ('synthWave_Rider', 'ROLE_USER'),
	('SmoothDeb', 'ROLE_ADMIN');


CREATE TABLE IF NOT EXISTS debates(
	`debate_id` int(11) NOT NULL AUTO_INCREMENT,
    `resolution` varchar(110) NOT NULL UNIQUE,
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
	(1, 'Americans should have free healthcare like all the other major developed countries.', 'x', 1, 1, null, null, null, 1, '2017-02-21', true),
	(2, 'Islands are being eaten by the sea and humans have an obigation to stop it.', 'anthropogenic global warming', 1, 2, null, null, null, 5, '2017-02-22', true),
    (3, 'Elliot Smith is better than Bob Dylan.', 'x', 2, 1, 3, null, null, 3, '2017-01-18', true),
    (4, 'Decentralizing government would solve a lot of problems.', 'x', 2, 7, 12, null, null, 1, '2017-01-05', 1),
    (5, 'Using a synth drum machine in grindcore is cheating.', 'x', 3, 12, 6, 0, 1, 3, '2017-03-25', 1),
    (6, 'Spaces are better than tabs.', 'x', 3, 13, 4, 7, 7, 14, '2017-03-10', 1),
    (7, 'The agricultural revolution was the worst thing to happen to the humanity.', 'x', 2, 8, 10, null, null, 16, '2017-02-17', 1),
    (8, 'John Steinbeck is one of the best authors in American literature.', 'x', 2, 11, 13, null, null, 8, '2017-01-08', 1),
    (9, 'The Replican and Democratic party platforms were basically switched over time.', 'x', 1, 9, null, null, null, 15, '2017-02-20', 1),
    (10, 'Cats shouldn''t be declawed.', 'x', 1, 1, null, null, null, 7, '2017-03-10', 1),
    (11, 'No Man''s Sky was a disappointing game.', 'x', 3, 4, 6, 8, 9, 4, '2017-03-02', 1),
    (12, 'Peter Singer is one of the most influential ethical philosophers of our time.', 'x', 1, 13, null, null, null, 5, '2017-02-28', 1),
    (13, 'Narnia exists.', 'x', 4, 3, 5, 10, 7, 8, '2017-01-11', 1),
    (14, 'AIs are going to take over and kill us all.', 'x', 5, 8, 2, 6, 10, 6, '2016-12-27', 1),
    (15, 'Libertarian Socialism is a branch of Anarchy.', 'x', 1, 3, null, null, null, 1, '2016-12-19', 1),
    (20, 'Going vegan is substantially better for the environment.', 'x', 1, 3, null, null, null, 9, '2016-11-15', false),
    (21, 'Capitalism can be reformed; Abolishing it isn''t necessary.', 'x', 2, 2, 3, null, null, 10, '2016-04-11', true);

CREATE TABLE IF NOT EXISTS `deb_statuses` (
	`status_id` int(2) NOT NULL,
    `status` varchar(10) UNIQUE,
    PRIMARY KEY (`status_id`)
);

INSERT INTO `deb_statuses` (`status_id`, `status`)
VALUES (1, 'intro'),
	(2, 'live'),
    (3, 'voting'),
	(4, 'proWon'),
    (5, 'conWon'),
    (6, 'wash');
    
CREATE TABLE IF NOT EXISTS `rebuttals`(
	`rebuttal_id` int(11) NOT NULL AUTO_INCREMENT,
    `content` text NOT NULL,
    `user_id` int(11),
    `debate_id` int(11) NOT NULL,
    `date` date,
    `position` boolean,
	PRIMARY KEY (`rebuttal_id`)
    
);

INSERT INTO `rebuttals` (`rebuttal_id`, `content`, `user_id`, `debate_id`, `date`, `position`) 
VALUES (1, 'You''re wrong, he''s great', 3, 3, '2017-02-22', false),
    (2, 'I''m not wrong. He sings pretty.', 1, 3, '2017-02-20', true),
    (3, 'x', 3, 21, '2016-4-26', false),
    (4, 'x', 12, 4, '2017-01-06', false),
    (5, 'x', 7, 4, '2017-01-06', true),
    (6, 'x', 12, 4, '2017-01-07', false),
    (7, 'x', 6, 5, '2017-03-25', false),
    (8, 'x', 12, 5, '2017-03-26', true),
    (9, 'x', 6, 5, '2017-03-26', false),
    (10, 'x', 12, 5, '2017-03-26', true),
    (11, 'x', 6, 5, '2017-03-26', false),
    (12, 'x', 4, 6, '2017-03-10', false),
    (13, 'x', 13, 6, '2017-03-10', true),
    (14, 'x', 4, 6, '2017-03-11', false),
    (15, 'x', 13, 6, '2017-03-11', true),
    (16, 'x', 4, 6, '2017-03-11', false),
    (17, 'x', 10, 7, '2017-02-17', false),
    (18, 'x', 8, 7, '2017-02-17', true),
    (19, 'x', 10, 7, '2017-02-19', false),
    (20, 'x', 13, 8, '2017-01-08', false),
    (21, 'x', 11, 8, '2017-01-08', true),
    (22, 'x', 13, 8, '2017-01-09', false),
    (23, 'x', 11, 8, '2017-01-09', true),
    (24, 'x', 6, 11, '2017-03-02', false),
    (25, 'x', 4, 11, '2017-03-02', true),
    (26, 'x', 6, 11, '2017-03-04', false),
    (27, 'x', 4, 11, '2017-03-04', true),
    (28, 'x', 6, 11, '2017-03-04', false),
    (29, 'x', 5, 13, '2017-01-11', false),
    (30, 'x', 3, 13, '2017-01-11', true),
    (31, 'x', 5, 13, '2017-01-13', false),
    (32, 'x', 3, 13, '2017-01-13', true),
    (33, 'x', 5, 13, '2017-01-13', false),
    (34, 'x', 2, 14, '2016-12-27', false),
    (35, 'x', 8, 14, '2016-12-27', true),
    (36, 'x', 2, 14, '2016-12-28', false),
    (37, 'x', 8, 14, '2016-12-28', true),
    (38, 'x', 2, 14, '2016-12-30', false);


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
    (6, 'technology'),
    (7, 'cats'),
    (8, 'books'),
    (9, 'veganism'),
    (10, 'economics'),
    (11, 'science'),
    (12, 'art'),
    (13, 'society'),
    (14, 'programming'),
    (15, 'history'),
    (16, 'anthropology'),
    (17, 'sports');

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

/*ALTER TABLE `rebuttals`
	ADD CONSTRAINT `rebuttals_types_fk` FOREIGN KEY (`type_id`) REFERENCES `reb_types` (`type_id`);

ALTER TABLE `deb_sources`
	ADD CONSTRAINT `deb_sources_debates_fk` FOREIGN KEY (`debate_id`) REFERENCES `debates` (`debate_id`);

ALTER TABLE `reb_sources`
	ADD CONSTRAINT `reb_sources_debates_fk` FOREIGN KEY (`rebuttal_id`) REFERENCES `rebuttals` (`rebuttal_id`);
*/