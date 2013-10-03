
--
-- Database: `CONTRIB_honeybadgers`
--

-- --------------------------------------------------------

--
-- Table structure for table `BADGES`
--

CREATE TABLE IF NOT EXISTS `BADGES` (
  `BADGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `GOAL` int(11) NOT NULL,
  `GOAL_UNITS` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(160) DEFAULT NULL,
  `PRE_REQ` int(11) DEFAULT NULL,
  `CUSTOM_PIC_URL` varchar(160) DEFAULT NULL,
  `PIC_APPROVED_BY` int(11) DEFAULT NULL,
  `ADDED_BY` int(11) DEFAULT NULL,
  `ADDED_DATE` timestamp NULL DEFAULT NULL,
  `DATE_CHANGED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BADGE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Table structure for table `EVENTS`
--

CREATE TABLE IF NOT EXISTS `EVENTS` (
  `EVENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE` date NOT NULL,
  `ORG_ID` int(11) NOT NULL,
  `TITLE` varchar(75) NOT NULL,
  `FOURSQUARE` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(150) DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `APPROVED` tinyint(1) DEFAULT NULL,
  `APPROVED_BY` int(11) DEFAULT NULL,
  `APPROVED_DATE` timestamp NULL DEFAULT NULL,
  `SUMMARY` text,
  `TYPE` varchar(30) DEFAULT NULL,
  `SPECIAL_NOTES` varchar(150) DEFAULT NULL,
  `ALCOHOL` tinyint(1) DEFAULT NULL,
  `CREATED_DATE` timestamp NULL DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `DELETED` tinyint(1) DEFAULT NULL,
  `DELETED_DATE` timestamp NULL DEFAULT NULL,
  `DELETED_BY` int(11) DEFAULT NULL,
  `DATE_CHANGED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Table structure for table `GOV_ORGS`
--

CREATE TABLE IF NOT EXISTS `GOV_ORGS` (
  `GOV_ORG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(160) NOT NULL,
  `ACRONYM` varchar(10) NOT NULL,
  PRIMARY KEY (`GOV_ORG_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Table structure for table `ORGS`
--

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
  `BLURB` text CHARACTER SET utf8 COLLATE utf8_bin,
  `FOURSQUARE` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(150) DEFAULT NULL,
  `HOMEPAGE_URL` varchar(100) DEFAULT NULL,
  `CUSTOM_PIC_URL` varchar(100) DEFAULT NULL,
  `PIC_APPROVED` tinyint(1) DEFAULT NULL,
  `PIC_APPROVED_BY` int(11) DEFAULT NULL,
  `DATE_CHANGED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ORG_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Table structure for table `USERS`
--

CREATE TABLE IF NOT EXISTS `USERS` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GTID` varchar(30) NOT NULL,
  `GOV_ORG_ID` int(11) NOT NULL,
  `PERMS` varchar(160) NOT NULL,
  `ADDED_BY` int(11) DEFAULT NULL,
  `ADDED_DATE` int(11) DEFAULT NULL,
  `ORG_ID` int(11) DEFAULT NULL,
  `SUSPENDED` tinyint(1) DEFAULT NULL,
  `SUSPENDED_DATE` timestamp NULL DEFAULT NULL,
  `SUSPENDED_BY` int(11) DEFAULT NULL,
  `SUSPENDED_REASON` varchar(160) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

---------
-- Dumping test data into tables
---------

--
-- badges
--

INSERT INTO `BADGES` (`BADGE_ID`, `NAME`, `GOAL`, `GOAL_UNITS`, `DESCRIPTION`, `PRE_REQ`, `CUSTOM_PIC_URL`, `ADDED_DATE`) VALUES
(1, 'Pledge', 5, 'Event Check-ins', 'Join the ranks of the pledges by attending 5 events put on by any organization.', '', 'http://bit.ly/18rs2bI', '2013-10-28T10:39:23'),
(2, 'Frosh', 20, 'Event Check-ins', 'Attain frosh status by attending at least 20 events put on by any organization.', 1, 'http://bit.ly/18rs2bI', '2013-10-18T09:49:25'),
(3, 'Al-Be Regular', 5, 'Al-Be Event Check-ins', 'Check-in at 5 events put on by Al-Be.', '', 'http://bit.ly/18rs2bI', '2013-10-24T13:29:26') ;

--
-- events
--

INSERT INTO `EVENTS` (`EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE` ,`SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`) VALUES
(1, '2013-10-31', 1, 'Al-Be\'s Halloween Bash', '522f836711d26c041fd70a09', 'Event Space, Theme Park, and Park in Bay Lake|Bay Lake, FL', '2013-10-31T21:00:00', '2013-11-01T03:30:00', '2013-10-28T10:39:23', 'Come out to the Al-Be Hallow\'s Eve celebration! There will be drinks for those of age, and fun for all around. Bring your best costume and hang out with the gents from Al-Be!', 'Party', 'Halloween Costume Party', 1),
(3, '2013-11-02', '2', '4-Be\'s Halloween Carousal', '522f836711d26c041fd70a09', 'Event Space, Theme Park, and Park in Bay Lake|Bay Lake, FL', '2013-11-02T22:00:00', '2013-11-03T04:00:00', '2013-10-30T16:32:11', 'Come party it up with 4-Be\'s annual Halloween Costume Party. Enter the judging for a chance to show off your costume and win prizes!', 'Party', 'Halloween Costume Party', 0) ;

--
-- gov_orgs
--

INSERT INTO `GOV_ORGS` (`GOV_ORG_ID`, `NAME`, `ACRONYM`) VALUES
(1, 'Interfraternity Council', 'IFC'),
(2, 'Collegiate Panhellenic Council', 'CPC'),
(3, 'National Pan-Hellenic Council', 'NPHC'),
(4, 'Multicultural Greek Council', 'MGC') ;


--
-- orgs
--

INSERT INTO `ORGS` (`ORG_ID`, `LETTERS`, `GOV_ORG_ID`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `ADDRESS`, `FOURSQUARE`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL`) VALUES
(1, 'Alpha Alpha', 1, 'Alpha Beta', 'Al-Be', 'Fraternity','Social', '1801', '1886', 'Alpha Alpha has long been known as a fraternity with a rich history of involvement and character. The handsome men of Al-Be strive to be the best men they can be in and out of the classroom.', '66 5th St NW|Atlanta, GA 30308', '4adc6aabf964a5202f2c21e3', 'http://bit.ly/7hNh', 'http://bit.ly/16ECqQ8'),
(2, 'Beta Beta', 1, 'Beta Beta', '4-Be', 'Fraternity', 'Social', '1869', '1901', 'Beta Beta chapter of Beta Beta is one of the strongest chapters on campus, as well as in the country. 4-Be has cultivated the spirit of being the next best thing; whether it\'s performing in academics, or being the 2nd runner up in every homecoming event for 15 straight years. 4-Be is the place to be if you yearn to be surrounded by above average individuals.', '428 Ponce de Leon Avenue NW|Atlanta, GA 30308-2015', '4c0d7c65d64c0f47f4cc265d', 'http://bit.ly/17aRau', 'http://bit.ly/1715Nq7') ;
