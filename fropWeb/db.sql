
--
-- Database: `CONTRIB_honeybadgers`
--

-- --------------------------------------------------------

--
-- Table Structure
--


CREATE TABLE IF NOT EXISTS `BADGES` (
  `BADGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `GOAL` int(11) NOT NULL,
  `GOAL_UNITS` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(160) DEFAULT NULL,
  `PRE_REQ` int(11) DEFAULT NULL,
  `CUSTOM_PIC_URL` varchar(160) DEFAULT NULL,
  `PIC_APPROVED` tinyint(1) DEFAULT NULL,
  `PIC_APPROVED_BY` int(11) DEFAULT NULL,
  `ADDED_BY` int(11) DEFAULT NULL,
  `ADDED_DATE` timestamp NULL DEFAULT NULL,
  `DATE_CHANGED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BADGE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `EVENTS` (
  `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `ORG_ID` int(11) NOT NULL,
  `TITLE` varchar(75) NOT NULL,
  `FOURSQUARE` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(160) DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `APPROVED` tinyint(1) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DATE` timestamp NULL DEFAULT NULL,
  `SUMMARY` text,
  `TYPE` varchar(30) DEFAULT NULL,
  `SPECIAL_NOTES` varchar(160) DEFAULT NULL,
  `ALCOHOL` tinyint(1) DEFAULT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `DELETED` tinyint(1) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` int(11) DEFAULT NULL,
  `DATE_CHANGED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `GOV_ORGS` (
  `GOV_ORG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(160) NOT NULL,
  `ACRONYM` varchar(10) NOT NULL,
  PRIMARY KEY (`GOV_ORG_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `ORGS` (
  `ORG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LETTERS` varchar(30) NOT NULL,
  `GOV_ORG_ID` int(11) NOT NULL,
  `CHAPTER` varchar(30) DEFAULT NULL,
  `NICKNAME` varchar(30) DEFAULT NULL,
  `TYPE` enum('FRATERNITY','SORORITY') DEFAULT NULL,
  `FOCUS` enum('SOCIAL','SERVICE','HONOR') DEFAULT NULL,
  `YEAR_FOUNDED` varchar(4) DEFAULT NULL,
  `YEAR_CHAPTER_FOUNDED` varchar(4) NOT NULL,
  `BLURB` text,
  `FOURSQUARE` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(160) DEFAULT NULL,
  `HOMEPAGE_URL` varchar(160) DEFAULT NULL,
  `CUSTOM_PIC_URL` varchar(160) DEFAULT NULL,
  `PIC_APPROVED` tinyint(1) DEFAULT NULL,
  `PIC_APPROVED_BY` int(11) DEFAULT NULL,
  `DATE_CHANGED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ORG_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `USERS` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GTID` varchar(30) NOT NULL,
  `GOV_ORG_ID` int(11) NOT NULL,
  `PERMS` tinyint(4) NOT NULL,
  `ADDED_BY` int(11) DEFAULT NULL,
  `ADDED_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ORG_ID` int(11) DEFAULT NULL,
  `SUSPENDED` tinyint(1) DEFAULT NULL,
  `SUSPENDED_DATE` timestamp NULL DEFAULT NULL,
  `SUSPENDED_BY` int(11) DEFAULT NULL,
  `SUSPENDED_REASON` varchar(160) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `FIELDS` (
  `TABLE` varchar(20) NULL,
  `FIELD_NAME` varchar(20) NOT NULL,
  `RAW_TYPE` varchar(20) NOT NULL,
  `EXPECTED_VALUE` text NOT NULL,
  `ALLOWED_NULL` varchar(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_bin;


---------
-- Dumping test data into tables
---------


INSERT IGNORE INTO `BADGES` (`BADGE_ID`, `NAME`, `GOAL`, `GOAL_UNITS`, `DESCRIPTION`, `PRE_REQ`, `CUSTOM_PIC_URL`, `ADDED_DATE`) VALUES
(1, 'Pledge', 5, 'Event Check-ins', 'Join the ranks of the pledges by attending 5 events put on by any organization.', '', 'http://bit.ly/18rs2bI', '2013-10-28T10:39:23'),
(2, 'Frosh', 20, 'Event Check-ins', 'Attain frosh status by attending at least 20 events put on by any organization.', 1, 'http://bit.ly/18rs2bI', '2013-10-18T09:49:25'),
(3, 'Al-Be Regular', 5, 'Al-Be Event Check-ins', 'Check-in at 5 events put on by Al-Be.', '', 'http://bit.ly/18rs2bI', '2013-10-24T13:29:26') ;


INSERT IGNORE INTO `EVENTS` (`EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE` ,`SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`) VALUES
(1, '2013-10-31', 1, 'Al-Be\'s Halloween Bash', '522f836711d26c041fd70a09', 'Event Space, Theme Park, and Park in Bay Lake|Bay Lake, FL', '2013-10-31T21:00:00', '2013-11-01T03:30:00', '2013-10-28T10:39:23', 'Come out to the Al-Be Hallow\'s Eve celebration! There will be drinks for those of age, and fun for all around. Bring your best costume and hang out with the gents from Al-Be!', 'Party', 'Halloween Costume Party', 1),
(3, '2013-11-02', '2', '4-Be\'s Halloween Carousal', '522f836711d26c041fd70a09', 'Event Space, Theme Park, and Park in Bay Lake|Bay Lake, FL', '2013-11-02T22:00:00', '2013-11-03T04:00:00', '2013-10-30T16:32:11', 'Come party it up with 4-Be\'s annual Halloween Costume Party. Enter the judging for a chance to show off your costume and win prizes!', 'Party', 'Halloween Costume Party', 0) ;


INSERT IGNORE INTO `GOV_ORGS` (`GOV_ORG_ID`, `NAME`, `ACRONYM`) VALUES
(1, 'Interfraternity Council', 'IFC'),
(2, 'Collegiate Panhellenic Council', 'CPC'),
(3, 'National Pan-Hellenic Council', 'NPHC'),
(4, 'Multicultural Greek Council', 'MGC') ;


INSERT IGNORE INTO `ORGS` (`ORG_ID`, `LETTERS`, `GOV_ORG_ID`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `ADDRESS`, `FOURSQUARE`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL`) VALUES
(1, 'Alpha Alpha', 1, 'Alpha Beta', 'Al-Be', 'Fraternity','Social', '1801', '1886', 'Alpha Alpha has long been known as a fraternity with a rich history of involvement and character. The handsome men of Al-Be strive to be the best men they can be in and out of the classroom.', '66 5th St NW|Atlanta, GA 30308', '4adc6aabf964a5202f2c21e3', 'http://bit.ly/7hNh', 'http://bit.ly/16ECqQ8'),
(2, 'Beta Beta', 1, 'Beta Beta', '4-Be', 'Fraternity', 'Social', '1869', '1901', 'Beta Beta chapter of Beta Beta is one of the strongest chapters on campus, as well as in the country. 4-Be has cultivated the spirit of being the next best thing; whether it\'s performing in academics, or being the 2nd runner up in every homecoming event for 15 straight years. 4-Be is the place to be if you yearn to be surrounded by above average individuals.', '428 Ponce de Leon Avenue NW|Atlanta, GA 30308-2015', '4c0d7c65d64c0f47f4cc265d', 'http://bit.ly/17aRau', 'http://bit.ly/1715Nq7') ;


INSERT IGNORE INTO `USERS` (`GTID`, `GOV_ORG_ID`, `PERMS`, `ORG_ID`, `SUSPENDED`) VALUES
('govtest1', 1, 1, 2, 0),
('test1', 1, 1, 2, 0),
('test2', 1, 1, 2, 0) ;


INSERT IGNORE INTO `FIELDS` (`TABLE`, `FIELD_NAME`, `RAW_TYPE`, `EXPECTED_VALUE`, `ALLOWED_NULL`) VALUES
('BADGES', 'BADGE_ID', 'int(11)', 'Auto-incremented (by database) primary key value', 'No'),
('BADGES', 'NAME', 'varchar(30)', 'Short name for the Badge. Can be descriptive, fun, or both', 'No'),
('BADGES', 'GOAL', 'int(11)', 'This is the amount of {something} that you have to have, do, or complete to earn the badge. For example, 5 {check-ins} means you have to check in 5 times to earn the badge', 'No'),
('BADGES', 'GOAL_UNITS', 'varchar(30)', 'This is the item that is done {x} number of times in order to earn the badge. For example, {5} Check-ins means that you have to check-in to complete this badge', 'No'),
('BADGES', 'DESCRIPTION', 'varchar(160)', 'This is an optional area for more detail about the badge. This can include information as to what exactly is required by the badge (if more information is needed), why users might want to do the badge, and if/why the badge has PRE_REQs', 'Yes'),
('BADGES', 'PRE_REQ', 'int(11)', 'A BADGE_ID that is required to be completed before this badge', 'Yes'),
('BADGES', 'CUSTOM_PIC_URL', 'varchar(160)', 'URL to a custom picture. Subject to approval', 'Yes'),
('BADGES', 'PIC_APPROVED', 'tinyint(1)', 'Whether custom picture has been approved', 'Yes'),
('BADGES', 'PIC_APPROVED_BY', 'int(11)', 'USER_ID of approver', 'Yes'),
('BADGES', 'ADDED_BY', 'int(11)', 'USER_ID of badge creator', 'Yes'),
('BADGES', 'ADDED_DATE', 'timestamp', 'Date of badge creation', 'Yes'),
('BADGES', 'DATE_CHANGED', 'timestamp', 'Date of most recent change to badge. This is updated automatically by the database', 'Yes'),
  
('EVENTS', 'EVENT_ID', 'int(11)', 'Unique ID autogenerated by the database', 'No'),
('EVENTS', 'DATE', 'date', 'The date the event will take place', 'No'),
('EVENTS', 'ORG_ID', 'int(11)', 'The organization that is hosting the event. This will be auto-filled based on the user creating the event', 'No'),
('EVENTS', 'TITLE', 'varchar(75)', 'Title (hopefully a descriptive one) of the event', 'No'),
('EVENTS', 'FOURSQUARE', 'varchar(50)', 'Foursquare EVENT_ID to enable check-ins', 'Yes'),
('EVENTS', 'ADDRESS', 'varchar(160)', 'Actual address of the event with line breaks denoted by the \'|\' character. eg 123 Test Circle|Atlanta, GA', 'Yes'),
('EVENTS', 'START_TIME', 'datetime', 'Start time of the event. To reduce confusion, use YYYY-MM-DDTHH:MM:SS where T is literally a \'T\'. eg 2013-11-02T22:00:00', 'Yes'),
('EVENTS', 'END_TIME', 'datetime', 'Start time of the event. To reduce confusion, use YYYY-MM-DDTHH:MM:SS where T is literally a \'T\'. eg 2013-11-02T22:00:00', 'Yes'),
('EVENTS', 'APPROVED', 'tinyint(1)', 'Whether the event has been approved by the organization\'s governing organization or not. The resetting of this should be happen and be handled by an application when a user updates an event', 'Yes'),
('EVENTS', 'APPROVED_BY', 'int(11)', 'USER_ID of approver', 'Yes'),
('EVENTS', 'APPROVED_DATE', 'timestamp', 'Timestamp of approval date', 'Yes'),
('EVENTS', 'SUMMARY', 'text', 'Longer summary of the party, date, and details about the event can be included here', 'Yes'),
('EVENTS', 'TYPE', 'varchar(30)', 'Type of event. eg Party, Philanthropy, Chapter', 'Yes'),
('EVENTS', 'SPECIAL_NOTES', 'varchar(30)', 'Theme. eg Halloween Dance Party', 'Yes'),
('EVENTS', 'ALCOHOL', 'tinyint(1)', 'Whether the event will allow or serve alcohol, 1 for yes, 0 for no', 'Yes'),
('EVENTS', 'CREATED_DATE', 'timestamp', 'Creation date of event', 'Yes'),
('EVENTS', 'CREATED_BY', 'int(11)', 'USER_ID of event creator', 'Yes'),
('EVENTS', 'DELETED', 'tinyint(1)', 'Allows for soft-deleting of records (for easy recovery). 1 for deleted, 0 for not deleted', 'Yes'),
('EVENTS', 'DELETED_DATE', 'timestamp', 'Date deletion occured', 'Yes'),
('EVENTS', 'DELETED_BY', 'int(11)', 'USER_ID that initiated the delete', 'Yes'),
('EVENTS', 'DATE_CHANGED', 'timestamp', 'Date of most recent change to event. This is updated automatically by the database', 'Yes'),

('GOV_ORGS', 'GOV_ORG_ID', 'int(11)', 'Unique ID autogenerated by the database', 'No'),
('GOV_ORGS', 'NAME', 'varchar(160)', 'Name of the Governing Organization', 'No'),
('GOV_ORGS', 'ACRONYM', 'varchar(10)', 'Acronym of the Governing Organization. eg IFC', 'No'),

('ORGS', 'ORG_ID', 'int(11)', 'Unique ID autogenerated by the database', 'No'),
('ORGS', 'LETTERS', 'varchar(30)', 'Identifying Greek Letters of organization spelled out and separated by spaces. eg Alpha Alpha Alpha', 'Yes'),
('ORGS', 'GOV_ORG_ID', 'int(11)', 'GOV_ORG_ID of governing organization', 'No'),
('ORGS', 'CHAPTER', 'varchar(30)', 'Identifying Greek Letters of organization\'s chapter spelled out and separated by spaces. eg Beta Beta', 'Yes'),
('ORGS', 'NICKNAME', 'varchar(30)', 'Chapter\'s campus nickname. eg Kappa Sig', 'Yes'),
('ORGS', 'TYPE', 'enum', 'Type of organization, legal values are FRATERNITY and SORORITY', 'Yes'),
('ORGS', 'FOCUS', 'enum', 'Main focus of organization, legal values are SOCIAL, SERVICE, and HONOR', 'Yes'),
('ORGS', 'YEAR_FOUNDED', 'varchar(4)', 'Year the chapter\'s parent organization was founded', 'Yes'),
('ORGS', 'YEAR_CHAPTER_FOUNDED', 'varchar(4)', 'Year chapter was founded', 'Yes'),
('ORGS', 'BLURB', 'text', 'General information about organization', 'Yes'),
('ORGS', 'FOURSQUARE', 'varchar(160)', 'Foursquare VENUE_ID for organization. Can be used to autopopulate location when creating an event', 'Yes'),
('ORGS', 'ADDRESS', 'varchar(160)', 'Actual address for organization with line breaks denoted by the \'|\' character. eg 123 Test Circle|Atlanta, GA', 'Yes'),
('ORGS', 'HOMEPAGE_URL', 'varchar(160)', 'URL for organization\'s website', 'Yes'),
('ORGS', 'CUSTOM_PIC_URL', 'varchar(160)', 'URL for organization\'s custom picture', 'Yes'),
('ORGS', 'PIC_APPROVED', 'tinyint(1)', 'Whether organization\'s custom picture has been approved', 'Yes'),
('ORGS', 'PIC_APPROVED_BY', 'int(11)', 'USER_ID of approver', 'Yes'),
('ORGS', 'DATE_CHANGED', 'timestamp', 'Date of most recent change to event. This is updated automatically by the database', 'Yes'),

('USERS', 'USER_ID', 'int(11)', 'This is an autoincremented field for use as a unique id in database', 'No'),
('USERS', 'GTID', 'varchar(30)', 'This is the user\'s GTID', 'No'),
('USERS', 'GOV_ORG_ID', 'int(11)', 'The id of the governing org this user represents', 'No'),
('USERS', 'PERMS', 'tinyint(4)', '0 - Anonymous superuser, 1 - (Governing Org Social Chair) - Can approve/disapprove items (events, pics, etc) within events/orgs associated with user\'s GOV_ORG_ID, can add/suspend users of same or lower PERM and associated with user\'s GOV_ORG_ID, can create badges, 2 - Organization\'s Social Chair - Can submit events for approval, can submit changes for approval, can submit badges for approval', 'No'),
('USERS', 'ORG_ID', 'int(11)', 'Org that the user is associated with', 'No'),
('USERS', 'ADDED_BY', 'int(11)', 'USER_ID of user who added this row, not required, but encouraged for traceability', 'Yes'),
('USERS', 'ADDED_DATE', 'timestamp', 'Date the user was added, DEFAULT when undefined is CURRENT_TIMESTAMP', 'Yes'),
('USERS', 'SUSPENDED', 'tinyint(1)', 'Whether user is allowed to submit any changes. Default is null, and allows users to submit. 0 also allows users to submit. 1 denotes a suspended user, who should not be able to submit any changes', 'Yes'),
('USERS', 'SUSPENDED_DATE', 'timestamp', 'Timestamp of when the user was submitted. It is suggested to do this on the application backend with CURTIME() function', 'Yes'),
('USERS', 'SUSPENDED_BY', 'int(11)', 'USER_ID that suspended current user', 'Yes'),
('USERS', 'SUSPENDED_REASON', 'varchar(160)', 'Short reason for suspension, should be not null when suspending a user, and enforced in the application backend', 'Yes'),

('FIELDS', 'TABLE', 'varchar(20)', 'Table containing the column', 'No'),
('FIELDS', 'FIELD_NAME', 'varchar(20)', 'Column name', 'No'),
('FIELDS', 'RAW_TYPE', 'varchar(20)', 'MySQL column type', 'No'),
('FIELDS', 'EXPECTED_VALUE', 'text', 'Description of what is expected in column', 'No'),
('FIELDS', 'ALLOWED_NULL', 'varchar(4)', 'Whether column is allowed to be null', 'No') ;
