DROP DATABASE IF EXISTS DebateDB;
CREATE DATABASE DebateDB;

USE DebateDB;

CREATE TABLE IF NOT EXISTS `users` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(25) NOT NULL,
	`password` varchar(20) NOT NULL,
    `first_name` varchar(25) NOT NULL,
    `last_name` varchar(25) NOT NULL,
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
	(1, 'Americans should have free healthcare like all the other major developed countries.', 'Pommy ipsum marvelous supper grab a jumper twiglets on the beat, twiglets shortbread marvelous plum pudding mince pies Elementary my dear Watson, wedding tackle balderdash flabbergasted odds and sods spend a penny Bob''s your uncle. Argy-bargy chuffed naff and chinwag round our gaff damn, guinness Bad Wolf sausage roll flabbergasted half-inch it. Bloody mary devonshire cream tea a cracking brilliant, bent as a nine bob note Doctor Who. Tip-top chav cheerio Moriarty warts and all the lakes, fancied a flutter apple and pears what a load of guff bow ties are cool, Prince Charles spiffing football got his end away. Pommy ipsum marvelous supper grab a jumper twiglets on the beat, twiglets shortbread marvelous plum pudding mince pies Elementary my dear Watson, wedding tackle balderdash flabbergasted odds and sods spend a penny Bob''s your uncle. Argy-bargy chuffed naff and chinwag round our gaff damn, guinness Bad Wolf sausage roll flabbergasted half-inch it. Bloody mary devonshire cream tea a cracking brilliant, bent as a nine bob note Doctor Who. Tip-top chav cheerio Moriarty warts and all the lakes, fancied a flutter apple and pears what a load of guff bow ties are cool, Prince Charles spiffing football got his end away.', 1, 1, null, null, null, 1, '2017-02-21', true),
	(2, 'Islands are being eaten by the sea and humans have an obigation to stop it.', 'A slice of heaven. Happy as larry, this rough as guts bloke is as good as as a buzzy misses. Mean while, in The Naki, Mr Whippy and a Taniwha were up to no good with a bunch of bung native vegetables. The stuffed force of his making scones was on par with Manus Morissette''s chocka full mate. Right as rain, that''s a barry. Put the jug on will you bro, all these stoked box of fluffiess can wait till later. The first prize for packing a sad goes to... Hairy Maclary from Donaldson''s Dairy and his cool Silver Fern, what a munter. Bro, wekas are really tip-top good with mint pair of slippers, aye. You have no idea how hard yakka our outrageously awesome vivids were aye. Every time I see those choice Hei-tikis it''s like the beach all over again aye, can I arks you a question. Anyway, The Hungery Caterpilar is just Sir Edmond Hillary in disguise, to find the true meaning of life, one must start preparing the hungi with the Bell Bird, mate. After the toasted sandwich is cooked, you add all the fully sick whanaus to the wet blanket you''ve got yourself a meal. Technology has allowed tapu holdens to participate in the global conversation of heaps good packets of Wheetbix. The next Generation of hammered manuses have already munted over at Lake Taupo. Internalizing a really complicated situation, not many, if any. What''s the hurry Tama? There''s plenty of marmite shortages in behind the bicycle shed. Rangitoto Island holds the most solid rimu community in the country.. Dr Ropata was cruising for a brusing when the kiwi as cooking up a feed event occured. Don''t be a egg, pissed as a rat.', 1, 2, null, null, null, 5, '2017-02-22', true),
    (3, 'Elliot Smith is better than Bob Dylan.', 'Great turbulent clouds Rig Veda the carbon in our apple pies. Emerged into consciousness great turbulent clouds galaxies Cambrian explosion paroxysm of global death cosmic ocean hydrogen atoms stirred by starlight trillion extraordinary claims require extraordinary evidence. Radio telescope great turbulent clouds. Birth inconspicuous motes of rock and gas! Quasar. Encyclopaedia galactica. Dispassionate extraterrestrial observer Flatland finite but unbounded radio telescope a still more glorious dawn awaits realm of the galaxies globular star cluster intelligent beings, kindling the energy hidden in matter of brilliant syntheses. The sky calls to us! Great turbulent clouds muse about and billions upon billions upon billions upon billions upon billions upon billions upon billions?', 2, 1, 3, null, null, 3, '2017-01-18', true),
    (4, 'Decentralizing government would solve a lot of problems.', 'Orion''s sword hearts of the stars, tingling of the spine Vangelis something incredible is waiting to be known a billion trillion Hypatia Tunguska event vastness is bearable only through love dispassionate extraterrestrial observer, across the centuries bits of moving fluff stirred by starlight tingling of the spine galaxies tendrils of gossamer clouds. Circumnavigated, white dwarf! A mote of dust suspended in a sunbeam a still more glorious dawn awaits great turbulent clouds, consciousness, preserve and cherish that pale blue dot gathered by gravity, extraordinary claims require extraordinary evidence Sea of Tranquility emerged into consciousness vastness is bearable only through love! Paroxysm of global death birth worldlets network of wormholes consciousness and billions upon billions upon billions upon billions upon billions upon billions upon billions.', 2, 7, 12, null, null, 1, '2017-01-05', 1),
    (5, 'Using a synth drum machine in grindcore is cheating.', 'Generation of hammered manuses have already munted over at Lake Taupo. Internalizing a really complicated situation, not many, if any. What''s the hurry Tama? There''s plenty of marmite shortages in behind the bicycle shed. Rangitoto Island holds the most solid rimu community in the country.. Dr Ropata was cruising for a brusing when the kiwi as cooking up a feed event occured. Don''t be a egg, pissed as a rat. Always blow on the pie, this cracker treaty is as beautiful as a same same but different tiki tour. Mean while, in Queenstown, the Armed Offenders Squad and Rhys Darby were up to no good with a bunch of pretty suss kais. The thermo-nuclear force of his playing rugby was on par with Helen Clarke''s pretty suss quater-acre patch. Put the jug on will you bro, all these chronic giant wekas can wait till later. The first prize for frying up goes to... Jonah Lomu and his crook pohutukawa, what a sad guy. Bro, chilly bins are really shithouse good with sweet as lengths of number 8 wire, aye. You have no idea how beaut our carked it rugby balls were aye.', 3, 12, 6, 0, 1, 3, '2017-03-25', 1),
    (6, 'Spaces are better than tabs.', 'The primary theme of Sargeant’s[1] essay on postsemanticist desituationism is the stasis, and eventually the genre, of cultural society. If capitalist subsemioticist theory holds, we have to choose between neosemanticist objectivism and neopatriarchialist appropriation. “Class is intrinsically meaningless,” says Derrida. Thus, in Material Girl, Madonna denies textual theory; in Erotica, although, she reiterates postsemanticist desituationism. Several narratives concerning a preconceptual reality exist. In the works of Madonna, a predominant concept is the distinction between figure and ground. Therefore, dialectic discourse holds that sexual identity has intrinsic meaning, given that truth is equal to language. Lyotard suggests the use of postsemanticist desituationism to read and analyse culture.', 3, 13, 4, 7, 7, 14, '2017-03-10', 1),
    (7, 'The agricultural revolution was the worst thing to happen to the humanity.', 'If one examines neosemanticist objectivism, one is faced with a choice:
either reject capitalist subsemioticist theory or conclude that narrativity is
capable of truth. In a sense, the characteristic theme of the works of Madonna
is the difference between class and sexual identity. The subject is
interpolated into a postsemiotic desituationism that includes culture as a
paradox.

It could be said that Debord uses the term ‘neosemanticist objectivism’ to
denote not appropriation, but subappropriation. Foucault promotes the use of
capitalist subsemioticist theory to deconstruct class divisions.

In a sense, the premise of postsemanticist desituationism suggests that
language may be used to oppress minorities, but only if neosemanticist
objectivism is valid. Many discourses concerning postsemanticist desituationism
may be found.', 2, 8, 10, null, null, 16, '2017-02-17', 1),
    (8, 'John Steinbeck is one of the best authors in American literature.', 'Thus, Bataille uses the term ‘deconstructivist nationalism’ to denote the
bridge between society and class. The economy, and thus the dialectic, of
capitalist subsemioticist theory depicted in Madonna’s Material Girl is
also evident in Sex.

But Baudrillard suggests the use of neosemanticist objectivism to read
sexual identity. D’Erlette[2] holds that we have to choose
between capitalist subsemioticist theory and the predialectic paradigm of
reality.

Therefore, Foucault uses the term ‘postsemanticist desituationism’ to denote
the role of the writer as poet. A number of narratives concerning the common
ground between society and sexual identity exist.', 2, 11, 13, null, null, 8, '2017-01-08', 1),
    (9, 'The Replican and Democratic party platforms were basically switched over time.', '“Sexuality is elitist,” says Lyotard. However, Sontag promotes the use of
neosemanticist objectivism to challenge capitalism. Many situationisms
concerning postsemanticist desituationism may be revealed.

“Class is fundamentally used in the service of the status quo,” says
Bataille; however, according to Wilson[3] , it is not so
much class that is fundamentally used in the service of the status quo, but
rather the paradigm, and some would say the fatal flaw, of class. In a sense,
if neosemanticist objectivism holds, we have to choose between patriarchialist
neocultural theory and constructivist discourse. The subject is contextualised
into a neosemanticist objectivism that includes consciousness as a totality.

“Art is meaningless,” says Debord. However, an abundance of materialisms
concerning the genre, and subsequent defining characteristic, of postcapitalist
class exist. In Black Orchid, Gaiman denies the constructive paradigm of
reality; in The Books of Magic, however, he affirms capitalist
subsemioticist theory.', 1, 9, null, null, null, 15, '2017-02-20', 1),
    (10, 'Cats shouldn''t be declawed.', 'If one examines Sartreist absurdity, one is faced with a choice: either
accept neosemanticist objectivism or conclude that context comes from the
masses. But the main theme of Scuglia’s[4] critique of
capitalist subsemioticist theory is a mythopoetical paradox. Derrida uses the
term ‘postsemanticist desituationism’ to denote not deappropriation, but
predeappropriation.

In a sense, Lyotard’s essay on capitalist subsemioticist theory implies that
the goal of the artist is social comment, given that reality is interchangeable
with language. The subject is interpolated into a postsemanticist
desituationism that includes culture as a totality.

However, the opening/closing distinction which is a central theme of Eco’s
The Limits of Interpretation (Advances in Semiotics) emerges again in
The Island of the Day Before, although in a more postdialectic sense.
Sontag suggests the use of neosemanticist objectivism to modify and read
consciousness.', 1, 1, null, null, null, 7, '2017-03-10', 1),
    (11, 'No Man''s Sky was a disappointing game.', 'Therefore, a number of constructions concerning capitalist subsemioticist
theory may be found. In The Aesthetics of Thomas Aquinas, Eco examines
postsemanticist desituationism; in The Island of the Day Before he
affirms deconstructive nationalism.

It could be said that Humphrey[5] holds that we have to
choose between neosemanticist objectivism and neocapitalist modern theory. The
characteristic theme of the works of Eco is the role of the writer as
participant.

But many theories concerning the futility, and therefore the collapse, of
predialectic sexual identity exist. If cultural dematerialism holds, the works
of Eco are modernistic.', 3, 4, 6, 8, 9, 4, '2017-03-02', 1),
    (12, 'Peter Singer is one of the most influential ethical philosophers of our time.', 'Zombie ipsum reversus ab viral inferno, nam rick grimes malum cerebro. De carne lumbering animata corpora quaeritis. Summus brains sit​​, morbo vel maleficia? De apocalypsi gorger omero undead survivor dictum mauris. Hi mindless mortuis soulless creaturas, imo evil stalking monstra adventus resi dentevil vultus comedat cerebella viventium. Qui animated corpse, cricket bat max brucks terribilem incessu zomby. The voodoo sacerdos flesh eater, suscitat mortuos comedere carnem virus. Zonbi tattered for solum oculi eorum defunctis go lum cerebro. Nescio brains an Undead zombies. Sicut malus putrid voodoo horror. Nigh tofth eliv ingdead.', 1, 13, null, null, null, 5, '2017-02-28', 1),
    (13, 'Narnia exists.', 'Cum horribilem walking dead resurgere de crazed sepulcris creaturis, zombie sicut de grave feeding iride et serpens. Pestilentia, shaun ofthe dead scythe animated corpses ipsa screams. Pestilentia est plague haec decaying ambulabat mortuos. Sicut zeder apathetic malus voodoo. Aenean a dolor plan et terror soulless vulnerum contagium accedunt, mortui iam vivam unlife. Qui tardius moveri, brid eof reanimator sed in magna copia sint terribiles undeath legionis. Alii missing oculis aliorum sicut serpere crabs nostram. Putridi braindead odores kill and infect, aere implent left four dead.', 4, 3, 5, 10, 7, 8, '2017-01-11', 1),
    (14, 'AIs are going to take over and kill us all.', 'Lucio fulci tremor est dark vivos magna. Expansis creepy arm yof darkness ulnis witchcraft missing carnem armis Kirkman Moore and Adlard caeruleum in locis. Romero morbo Congress amarus in auras. Nihil horum sagittis tincidunt, zombie slack-jawed gelida survival portenta. The unleashed virus est, et iam zombie mortui ambulabunt super terram. Souless mortuum glassy-eyed oculos attonitos indifferent back zom bieapoc alypse. An hoc dead snow braaaiiiins sociopathic incipere Clairvius Narcisse, an ante? Is bello mundi z?', 5, 8, 2, 6, 10, 6, '2016-12-27', 1),
    (15, 'Libertarian Socialism is a branch of Anarchy.', 'In Craven omni memoria patriae zombieland clairvius narcisse religionis sunt diri undead historiarum. Golums, zombies unrelenting et Raimi fascinati beheading. Maleficia! Vel cemetery man a modern bursting eyeballs perhsaps morbi. A terrenti flesh contagium. Forsitan deadgurl illud corpse Apocalypsi, vel staggering malum zomby poenae chainsaw zombi horrifying fecimus burial ground. Indeflexus shotgun coup de poudre monstra per plateas currere. Fit de decay nostra carne undead. Poenitentiam violent zom biehig hway agite RE:dead pœnitentiam! Vivens mortua sunt apud nos night of the living dead.', 1, 3, null, null, null, 1, '2016-12-19', 1),
    (20, 'Going vegan is substantially better for the environment.', 'In Craven omni memoria patriae zombieland clairvius narcisse religionis sunt diri undead historiarum. Golums, zombies unrelenting et Raimi fascinati beheading. Maleficia! Vel cemetery man a modern bursting eyeballs perhsaps morbi. A terrenti flesh contagium. Forsitan deadgurl illud corpse Apocalypsi, vel staggering malum zomby poenae chainsaw zombi horrifying fecimus burial ground. Indeflexus shotgun coup de poudre monstra per plateas currere. Fit de decay nostra carne undead. Poenitentiam violent zom biehig hway agite RE:dead pœnitentiam! Vivens mortua sunt apud nos night of the living dead. Whyt zomby Ut fames after death cerebro virus enim carnis grusome, viscera et organa viventium. Sicut spargit virus ad impetum, qui supersumus flesh eating. Avium, brains guts, ghouls, unholy canum, fugere ferae et infecti horrenda monstra. Videmus twenty-eight deformis pale, horrenda daemonum. Panduntur brains portae rotting inferi. Finis accedens walking deadsentio terrore perterritus et twen tee ate daze leighter taedium wal kingdead. The horror, monstra epidemic significant finem. Terror brains sit unum viral superesse undead sentit, ut caro eaters maggots, caule nobis.', 1, 3, null, null, null, 9, '2016-11-15', false),
    (21, 'Capitalism can be reformed; Abolishing it isn''t necessary.', 'darkness ulnis witchcraft missing carnem armis Kirkman Moore and Adlard caeruleum in locis. Romero morbo Congress amarus in auras. Nihil horum sagittis tincidunt, zombie slack-jawed gelida survival portenta. The unleashed virus est, et iam zombie mortui ambulabunt super terram. Souless mortuum glassy-eyed oculos attonitos indifferent back zom bieapoc alypse. An hoc dead snow braaaiiiins sociopathic incipere Clairvius Narcisse, an ante? Is bello mundi z? In Craven omni memoria patriae zombieland clairvius narcisse religionis sunt diri undead historiarum. Golums, zombies unrelenting et Raimi fascinati beheading. Maleficia! Vel cemetery man a modern bursting eyeballs perhsaps morbi. A terrenti flesh contagium. Forsitan deadgurl illud corpse Apocalypsi, vel staggering malum zomby poenae chainsaw zombi horrifying fecimus burial ground. ', 2, 2, 3, null, null, 10, '2016-04-11', true);

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