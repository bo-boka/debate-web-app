USE DebateDB;

SELECT * FROM debates;

/*all published debates*/
SELECT debates.debate_id AS id, resolution, content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, date, published FROM debates
	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id
	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id
    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id
    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id
    WHERE debates.published ORDER BY debates.date DESC;
 
 /*all pub and unpub debates*/
SELECT debates.debate_id AS id, resolution, content, deb_statuses.status, affU.username AS affirmativeUser, negU.username AS negativeUser, proVotes, conVotes, categories.category, date, published FROM debates
	LEFT OUTER JOIN `deb_statuses` ON debates.status_id = `deb_statuses`.status_id
	LEFT OUTER JOIN `users` AS affU ON debates.affirmativeUser_id = affU.user_id
    LEFT OUTER JOIN `users` AS negU ON debates.negativeUser_id = negU.user_id
    LEFT OUTER JOIN `categories` ON debates.category_id = categories.category_id
    ORDER BY debates.date DESC;
 
	
