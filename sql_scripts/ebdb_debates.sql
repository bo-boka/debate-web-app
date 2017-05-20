DROP DATABASE IF EXISTS ebdb;
CREATE DATABASE ebdb;

USE ebdb;

CREATE TABLE IF NOT EXISTS `users` (
	`user_id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(21) NOT NULL,
	`password` varchar(70) NOT NULL,
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
VALUES (1, '1_debatinNotHatin', '$2a$10$dF7HhXvoH8.M7VlhjRZ1beNIPoN16TKLI5150iOsBETF2p/CaNvl.', 'Ally', 'Smith', 'asmith@debator.com', 47, 10, 20, 1),
	(2, '2X2truDebatorX', '$2a$10$DDpCsLrSx5yND6UPDqYTkO6qPe9aLSW4FaMXfn7XNBoHorZDve4ZW', 'Bob', 'Gualla', 'email@sendit.com', 10, 3, 10, 1),
    (3, '2cheesinForTheWeekend', '$2a$10$RH75sfoK8fD/fj2CwoCAnOnpcLNeThm3qYrFxJn4Tqjr7N6G/oC7e', 'Lana', 'Brown', 'email@sendin.com', 13, 7, 3, 1),
    (4, '2mdb8r', '$2a$10$e4Qqz/MomUAmlTpcp/dLE.osICahHMTKmzn.DbME6yPRyPIotnXFq', 'Arielle', 'Cho', 'thisemail@gmail.com', 13, 10, 25, 1),
    (5, '2snowOwl22', '$2a$10$PCbwN9rEQi88J/W0QmlS6usmykba4UO2ytCmaHopU9.sNG2heReeK', 'Gertrude', 'Hammerquist', 'hammyq@gmail.com', 14, 3, 8, 1),
	(6, '2sawadeeka', '$2a$10$rwA5JeH7dZpAJjHKOjdAiOU6InutHh.7AoaJ3xAvRad5Z9RO4cGg2', 'Juan', 'Jimenez', 'jimenez@gmail.com', 5, 4, 10, 1),
	(7, '1_SmoothDeb', '$2a$10$7MSqCrWclTQg5dML9/D./.a/q7RRyJ5LspDJXg24CB1lq48YW2B8O', 'Neena', 'Gupta', 'neenag@gmail.com', 12, 2, 7, 1),
    (8, '2CoolAidComarade', '$2a$10$b7sqe7/BpsApUPII4fy61ODSVjNl.wodZjiBwH5E2XVCMx2W00RAa', 'Jenson', 'Hearthrow', 'jedeez@gmail.com', 11, 3, 0, 1),
    (9, '2KilGore', '$2a$10$t6LgVts77wDKcIBHz2p3R.Ot3ruZOQSMnsOckWlWP7jCC89IYs0MK', 'Polly', 'Kilgore', 'dlsjfd@jl.com', 30, 12, 10, 1),
    (10, '2trash_monster', '$2a$10$HKGpFJWLmO5IZ0FQGU20AeKvR2gandd7OG5MONucQJHxbg1x3UANK', 'Sal', 'Trevnal', 'kjdf@gmail.com', 9, 4, 13, 1),
    (11, '2alaDDin', '$2a$10$J6BpDmvJrFOp5I7MYOFh7uTf5Gl2gLww1mk.IKgqbUZhDnb6OGGWO', 'Trevor', 'OMalley', 'djlk@gmail.com', 3, 0, 1, 0),
    (12, '2xXbreakdoWnz4lifeXx', '$2a$10$Q0Rhl6GdyY./6.mjua77nODmPK80LyWNgBkXv4GErHZgOxy97JotS', 'Fran', 'Snape', 'lkf@gmail.com', 50, 6, 8, 1),
    (13, '2synthWave_Rider', '$2a$10$QtBuy/SnGF56LPHUJs8n7OPiaxGpOzkwx4izWv9Ipz0pfrLCCHdeO', 'Loni', 'Bramer', 'jld@gmail.com', 70, 10, 20, 0);
 
 
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
    ('CoolAidComarade', 'ROLE_USER'),
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
	(2, 'Islands are being eaten by the sea and humans have an obligation to stop it.', 'A slice of heaven. Happy as larry, this rough as guts bloke is as good as as a buzzy misses. Mean while, in The Naki, Mr Whippy and a Taniwha were up to no good with a bunch of bung native vegetables. The stuffed force of his making scones was on par with Manus Morissette''s chocka full mate. Right as rain, that''s a barry. Put the jug on will you bro, all these stoked box of fluffiess can wait till later. The first prize for packing a sad goes to... Hairy Maclary from Donaldson''s Dairy and his cool Silver Fern, what a munter. Bro, wekas are really tip-top good with mint pair of slippers, aye. You have no idea how hard yakka our outrageously awesome vivids were aye. Every time I see those choice Hei-tikis it''s like the beach all over again aye, can I arks you a question. Anyway, The Hungery Caterpilar is just Sir Edmond Hillary in disguise, to find the true meaning of life, one must start preparing the hungi with the Bell Bird, mate. After the toasted sandwich is cooked, you add all the fully sick whanaus to the wet blanket you''ve got yourself a meal. Technology has allowed tapu holdens to participate in the global conversation of heaps good packets of Wheetbix. The next Generation of hammered manuses have already munted over at Lake Taupo. Internalizing a really complicated situation, not many, if any. What''s the hurry Tama? There''s plenty of marmite shortages in behind the bicycle shed. Rangitoto Island holds the most solid rimu community in the country.. Dr Ropata was cruising for a brusing when the kiwi as cooking up a feed event occured. Don''t be a egg, pissed as a rat.', 1, 2, null, null, null, 5, '2017-02-22', true),
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
consciousness.', 1, 1, null, null, null, 7, '2017-03-10', false),
    (11, 'Simon Says is one of the best life-prepping games.', 'Therefore, a number of constructions concerning capitalist subsemioticist
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
    (20, 'Going vegan is substantially better for the environment.', 'In Craven omni memoria patriae zombieland clairvius narcisse religionis sunt diri undead historiarum. Golums, zombies unrelenting et Raimi fascinati beheading. Maleficia! Vel cemetery man a modern bursting eyeballs perhsaps morbi. A terrenti flesh contagium. Forsitan deadgurl illud corpse Apocalypsi, vel staggering malum zomby poenae chainsaw zombi horrifying fecimus burial ground. Indeflexus shotgun coup de poudre monstra per plateas currere. Fit de decay nostra carne undead. Poenitentiam violent zom biehig hway agite RE:dead pœnitentiam! Vivens mortua sunt apud nos night of the living dead. Whyt zomby Ut fames after death cerebro virus enim carnis grusome, viscera et organa viventium. Sicut spargit virus ad impetum, qui supersumus flesh eating. Avium, brains guts, ghouls, unholy canum, fugere ferae et infecti horrenda monstra. Videmus twenty-eight deformis pale, horrenda daemonum. Panduntur brains portae rotting inferi. Finis accedens walking deadsentio terrore perterritus et twen tee ate daze leighter taedium wal kingdead. The horror, monstra epidemic significant finem. Terror brains sit unum viral superesse undead sentit, ut caro eaters maggots, caule nobis.', 1, 3, null, null, null, 9, '2016-11-15', true),
    (21, 'Capitalism can be reformed; Abolishing it isn''t necessary.', 'darkness ulnis witchcraft missing carnem armis Kirkman Moore and Adlard caeruleum in locis. Romero morbo Congress amarus in auras. Nihil horum sagittis tincidunt, zombie slack-jawed gelida survival portenta. The unleashed virus est, et iam zombie mortui ambulabunt super terram. Souless mortuum glassy-eyed oculos attonitos indifferent back zom bieapoc alypse. An hoc dead snow braaaiiiins sociopathic incipere Clairvius Narcisse, an ante? Is bello mundi z? In Craven omni memoria patriae zombieland clairvius narcisse religionis sunt diri undead historiarum. Golums, zombies unrelenting et Raimi fascinati beheading. Maleficia! Vel cemetery man a modern bursting eyeballs perhsaps morbi. A terrenti flesh contagium. Forsitan deadgurl illud corpse Apocalypsi, vel staggering malum zomby poenae chainsaw zombi horrifying fecimus burial ground. ', 2, 2, 3, null, null, 10, '2016-04-11', false);

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
    (3, 'A friend was talking about how “the revolution needs a better PA system” this evening, because he was at a protest at Westlake, around the corner from the main area, and couldn’t hear the speaker. People in the conversation were talking about festival-grade PA systems, but it seems to me that what a protest needs is something portable and easily deployable, not something which requires AC power and a stage with rigging for flying speakers.', 3, 21, '2016-4-26', false),
    (4, 'You want to be able to move with the crowd if you’re on the march, and ideally if the march is a mile long you still want people in the back to be able to hear. Here’s my suggestion (all links to Amazon, sorry, but everything’s available somewhere else just as easily): ThunderPower 1200 megaphone – these suckers look to be super loud. They have a line-in jack and they can be powered by a 12v car battery, both important considerations in this application.', 12, 4, '2017-01-06', false),
    (5, 'Put all the radios attached to the megaphones in receive mode, tuned to the same channel as the one the speaker talks into. Now you have a wireless distribution system for your audio. Down side: it can be jammed fairly easily, and may be illegal to use in this application, so more research is required.', 7, 4, '2017-01-06', true),
    (6, 'Electro-Harmonix Memory Toy Analog Delay – might not be necessary, but when you’re near a PA speaker a long way from the stage, you need to add a bit of delay to that speaker in order not to get an echo effect, due to the speed of sound.', 12, 4, '2017-01-07', false),
    (7, 'Sound will travel over the radio faster than through the air, so you’ll hear something through the closest PA speaker and then again as the sound from speakers closer to the stage reaches you. Might not be as big a deal in a huge protest as it is in a live concert, but worth thinking about. (You’d want to set the blend to wet-only, no feedback, and adjust the delay until it sounded right.)', 6, 5, '2017-03-25', false),
    (8, 'I don’t know why I’ve been thinking about nuclear annihilation lately. Must just be something in the air. Oh! Speaking of something in the air. Here’s a booklet from the government of 1961 about what you can do in the event of a nuclear attack.', 12, 5, '2017-03-26', true),
    (9, '‘Twas one bright day in April
and the clocks had struck thirteen
when the story I’ll relate to you
took place in Bowling Green.
Though few today have heard of it,
it was such a dreadful sight
that every true American
should join us in the fight.', 6, 5, '2017-03-26', false),
    (10, 'If you have a Fisher-Price #995 Music Box / Record Player that you want to get open, you may have run into a problem with the screws holding it together. If yours is held together with regular phillips or flathead screws, great, no problem. If a screwdriver isn’t working, though, you’re probably wondering what tool you do need.', 12, 5, '2017-03-26', true),
    (11, '“This Old Toy” will sell you a nut removal tool for $16 plus shipping, but you can get that same tool from Amazon for under $5 (plus shipping). Or if you have an Ace hardware store nearby, they probably have what you need for around $6 — a 3/16" nut driver. You could pay extra for a thin-wall version, but the standard one from Ace should work fine.', 6, 5, '2017-03-26', false),
    (12, 'I’m posting this because it was ridiculously difficult to find out what size head was on the screw down in the bottom of that hole. I’m going to go ahead and call it impossible — I couldn’t find that information online anywhere, in any case.', 4, 6, '2017-03-10', false),
    (13, 'I had to destroy the back of my parts donor music box to extract a screw and match it against one of my socket wrenches (which won’t fit down into the recessed mounting hole). So, this post is a public service for the next person who’s trying to figure it out:', 13, 6, '2017-03-10', true),
    (14, 'Oh, and check out these disassembly photos for an idea of what to do once the screws have been removed. If the turntable won’t spin, the clockwork mechanism is probably gummed up.', 4, 6, '2017-03-11', false),
    (15, 'Spray it with degreaser (I used White Lightning Clean Streak, because I had some in my bicycle tooklit, but WD-40 would also work) and it’ll probably loosen right up.', 13, 6, '2017-03-11', true),
    (16, 'If the music box head is damaged, you’re probably out of luck. The metal parts were made by Sankyo, which still sells music box mechanisms, but good luck finding replacement parts, except by cannibalizing other F-P music boxes.', 4, 6, '2017-03-11', false),
    (17, 'The ino about the red ring was spot on, let me finally get it apart. What an engineering marvel in there, so simple but elegant. Got a non-functioning toy back up and running, this website was a huge help.', 10, 7, '2017-02-17', false),
    (18, 'Echo park vice wayfarers leggings, portland vexillologist pabst paleo yuccie. Cornhole keffiyeh waistcoat ennui artisan, hexagon leggings sriracha tofu. Beard tbh hoodie pour-over lomo, mixtape you probably haven''t heard of them cliche.', 8, 7, '2017-02-17', true),
    (19, 'Authentic tumblr swag, godard echo park bespoke health goth cronut roof party yuccie yr etsy selfies kickstarter. Hella humblebrag tumblr, woke man braid yr semiotics leggings literally lumbersexual bespoke.', 10, 7, '2017-02-19', false),
    (20, 'Vape heirloom vice lyft lomo, tousled artisan copper mug chia. Kombucha next level stumptown gentrify forage, woke twee. Fap cray umami, banjo fixie paleo retro sriracha vaporware. Etsy VHS trust fund, enamel pin synth direct trade gastropub.', 13, 8, '2017-01-08', false),
    (21, 'Fanny pack ethical plaid, scenester fashion axe paleo quinoa tumeric ramps drinking vinegar knausgaard synth selvage. ', 11, 8, '2017-01-08', true),
    (22, 'Next level affogato semiotics mumblecore, literally la croix ramps vaporware. Tbh tofu wayfarers fingerstache, sartorial yr williamsburg.', 13, 8, '2017-01-09', false),
    (23, 'The lambs have passed through the gate. It eats you, starting with your bottom. Any self-respecting demon should be living in a pit of filth or a nice crypt. She is the slayer. Your futures are murky; you''d do well to heed my...I''m still on speakerphone,', 11, 8, '2017-01-09', true),
    (24, 'Sup bro! Can''t handle the jandle, this shithouse Undie 500 is as mean as as a choice sheila. Mean while, in West Auckland, Hairy Maclary from Donaldson''s Dairy and Tama were up to no good with a bunch of hard case keas.', 6, 11, '2017-03-02', false),
    (25, 'Put the jug on will you bro, all these mint Swanndris can wait till later. The first prize for skiving off goes to... Jim Hickey and his random quater-acre patch, what a sad guy.', 4, 11, '2017-03-02', true),
    (26, 'Bro, Monopoly money, from the New Zealand version with Queen Street and stuff are really bung good with nuclear-free pieces of pounamu, aye. You have no idea how cracker our chocka full vivids were aye.', 6, 11, '2017-03-04', false),
    (27, 'Anyway, Jonah Lomu is just Spot, the Telecom dog in disguise, to find the true meaning of life, one must start rooting with the cookie time, mate. After the All Black is munted, you add all the sweet as bottles of tomato sauce to the pavlova', 4, 11, '2017-03-04', true),
    (28, 'Technology has allowed rip-off chicks to participate in the global conversation of wicked rugby balls. The next Generation of primo eggs have already cooked over at smoko time.', 6, 11, '2017-03-04', false),
    (29, 'Castle Hill holds the most outrageously awesome community in the country.. Manus Morissette was pashing when the crook munting event occured. Left my scooter outside the dairy, this stink hokey pokey is as tip-top as a pretty suss kai moana.', 5, 13, '2017-01-11', false),
    (30, 'Uncle Bully and his bloody milk, what a munter. Bro, giant wekas are really same same but different good with stuffed marmite shortages, aye. You have no idea how carked it our dodgy kais were aye.', 3, 13, '2017-01-11', true),
    (31, 'Anyway, Lomu is just The Hungery Caterpilar in disguise, to find the true meaning of life, one must start making scones with the gumboot, mate. Happy as larry. After the ute is rooted, you add all the beached as packets of Wheetbix to the mate ', 5, 13, '2017-01-13', false),
    (32, 'Technology has allowed hard yakka moreporks to participate in the global conversation of chronic toasted sandwiches. The next Generation of stoked dole bludgers have already skived off over at Mt Cook. What''s the hurry some uni student? There''s plenty of herd of sheep in South Pacific.', 3, 13, '2017-01-13', true),
    (33, 'Strap on my job helmet dayman wad of hundreds nightman spaghetti day Wine in a can fire off into job land Wine in a can. Cat in the wall magnum spaghetti day spaghetti day Sweet Dee and friendship for everyone karate.', 5, 13, '2017-01-13', false),
    (34, 'Salt the snail. Sweet Dee sun Rum Ham, karate Strap on my job helmet rock where jobs grow on jobbies fire off into job land rock youre a master of. Wild Card Salt the snail Sweet Dee eagle troll toll fire off into job land.', 2, 14, '2016-12-27', false),
    (35, 'So-called media tablets, on the one hand no doubt Apple will finally do something about that, after the Dell DJ is an iPod killer at the beginning Apple’s fans are more interested in spending money than they are with facts, this is why Apple products are too expensive suddenly best features were literally copied.', 8, 14, '2016-12-27', true),
    (36, 'Where ignorance lurks, so too do the frontiers of discovery and imagination. Dinosaurs are extinct today because they lacked opposable thumbs and the brainpower to build a space program. The regret on our side is, they used to say years ago, we are reading about you in science class. Now they say, we are reading about you in history class.', 2, 14, '2016-12-28', false),
    (37, 'Curious that we spend more time congratulating people who have succeeded than encouraging people who have not. As I stand out here in the wonders of the unknown at Hadley, I sort of realize there’s a fundamental truth to our nature, Man must explore . . . and this is exploration at its greatest.', 8, 14, '2016-12-28', true),
    (38, 'Never in all their history have men been able truly to conceive of the world as one: a single sphere, a globe, having the qualities of a globe, a round earth in which all the directions eventually meet, in which there is no center because every point, or none, is center — an equal earth which all men occupy as equals. The airman''s earth, if free men make it, will be truly round: a globe in practice, not in theory.', 2, 14, '2016-12-30', false);


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