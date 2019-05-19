/*
 * Database for Videogames, their developers, producers, publisher, community following, as well
 * as the lead design and lead developer assigned to each game.
 *
 * @author Wesley Stahl
 * @version 5/26/2017
 */

create Database VideoGames;
use VideoGames;

create table Producer (
	producerID Integer identity primary key,
	producer_name char(20),
	producer_description char(250),
	producer_Location char(20),
	constraint producerID_PK Primary Key (producerID),
);

create table Developer (
	developerID Integer identity primary key,
	producerID Integer,
	developerName char(30),
	developer_description char(250),
	developer_location char(20),
	constraint developerID_PK Primary Key (producerID,developerID),
	Foreign Key (producerID) References Producer(producerID),
);

create table Game (
	gameKey Integer identity primary key,
	developerID integer,
	producerID integer,
	game_title char(50),
	game_description char(250),
	game_Rating char(5),
	game_popularity Integer,
  game_price Double,
	game_type char(10),
	game_genre char(10),
	constraint gameKey_PK Primary Key (producerID,developerID,gameKey),
	Foreign Key (producerID) References Producer(producerID),
	Foreign Key (developerID) References Developer(developerID),
);

create table LeadDeveloper (
	developID Integer not NULL,
	developer_name char(30),
	developer_info char(100),
	developer_experience char(200),
	constraint developID_PK Primary Key (developID),
);

create table LeadDesign (
	designerID Integer not NULL,
	designer_name char(30),
	designer_info char(100),
	designer_experience char(200),
	constraint designerID_PK Primary Key (designerID),
);

create table Community (
	communityTag Integer,
	community_size Integer,
	community_description char(250),
	community_work char(250),
	community_Discussion_boards char(250),
	constraint communityTag_PK Primary Key (communityTag),
);

create table Community_Member (
	memberTag Integer identity primary key,
	memberID char(50),
	member_description char(250),
	constraint memberTag_PK Primary Key (memberTag),
);

create table Console (
  consoleID Integer identity primary key,
	consoleName char(20) not NULL,
	constraint consoleID_PK Primary Key (consoleID),
);

create table Game_Developed (
	gameKey Integer not NULL,
	developID Integer not NULL,
	designerID Integer not NULL,
	communityTag Integer not NULL,
	consoleID char(20) not NULL,
	constraint game_deloped_PK Primary Key (gameKey, developID,designerID,communityTag,consoleID),
	Foreign Key (gameKey) REFERENCES Game(gameKey),
	Foreign Key (developID) REFERENCES LeadDeveloper(developID),
	Foreign Key (designerID) REFERENCES LeadDesign(designerID), 
	Foreign Key (communityTag) REFERENCES Community(communityTag),
	Foreign Key (consoleID) REFERENCES Console(consoleID),
);

create table Community_Sect (
	communityID Integer identity primary key,
	communityTag Integer,
	community_Involvement char(250),
	constraint communityID_PK Primary Key (communityTag,communityID),
	Foreign Key (communityTag) References Community(communityTag),
);

create table Publisher (
	publisherID Integer identity Primary key,
	publisherName char(50),
	constraint publisherID_PK Primary Key (publisherID),
);