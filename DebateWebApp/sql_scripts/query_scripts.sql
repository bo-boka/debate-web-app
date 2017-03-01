USE DebateDB;

SELECT * FROM debates;

/*all published debates w/o rebuttals for rowmapper*/
SELECT debates.debate_id AS id, resolution, content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, date, published FROM debates
	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id
	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id
    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id
    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id
    WHERE debates.published ORDER BY debates.date DESC;
 
 /*all pub and unpub debates w/o rebs for rowmapper*/
SELECT debates.debate_id AS id, resolution, content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, date, published FROM debates
	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id
	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id
    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id
    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id
    ORDER BY debates.date DESC;
    
SELECT * FROM rebuttals;
/*list of rebuttals for debates for rowmapper*/
SELECT rebuttal_id AS id, content, `users`.username, debate_id, `reb_types`.type, date, position FROM rebuttals
	LEFT OUTER JOIN `users` ON rebuttals.user_id = `users`.user_id
    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id
    ORDER BY rebuttals.date DESC;
    
/*get all pub debates w/ rebuttals using result set extractor*/
SELECT * FROM debates;

SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates
	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id
	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id
    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id
    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id
    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id
    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id
    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id
    WHERE debates.published ORDER BY debates.date DESC;
	
/*get a single debate w. result set extractor*/
SELECT debates.debate_id AS id, resolution, debates.content AS deb_content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, debates.date AS deb_date, published, rebuttal_id, rebuttals.content AS reb_content, rebU.username AS rebUser, `reb_types`.type, rebuttals.date AS reb_date, position FROM debates
	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id
	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id
    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id
    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id
    LEFT OUTER JOIN `rebuttals` ON debates.debate_id = `rebuttals`.debate_id
    LEFT OUTER JOIN `users` AS rebU ON rebuttals.user_id = rebU.user_id
    LEFT OUTER JOIN `reb_types` ON rebuttals.type_id = `reb_types`.type_id
    WHERE debates.published AND debates.debate_id = 3;

/*add debate*/
INSERT INTO debates (resolution, content, status_id, affirmativeUser_id, category_id, date, published)
	VALUES ('this is a res', 'blah blah content', 1, 1, 1, '2015-11-11', true);

/*get user_id from username to add a debate*/
SELECT user_id FROM users WHERE username = 'cheesinForTheWeekend';

/*get category_id to add debate*/
SELECT category_id FROM categories WHERE category = 'philosophy';

/*get all categories to load into dropdown*/
SELECT category FROM categories;

