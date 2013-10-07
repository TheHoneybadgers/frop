<?php 
	include 'db_helper.php';

#TODO handle stripping the pic out if not approved
	function listOrgs() {
		$dbQuery = sprintf("SELECT `ORG_ID`, `LETTERS`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `ADDRESS`, `FOURSQUARE`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL` FROM `ORGS`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getOrg($id) {
		$dbQuery = sprintf("SELECT `ORG_ID`, `LETTERS`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `ADDRESS`, `FOURSQUARE`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL` FROM `ORGS` WHERE `ORG_ID` = '%d'", mysql_real_escape_string($id));
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
# TODO finish below
	function addOrg() {
		$dbQuery = sprintf("SELECT `ORG_ID`, `LETTERS`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `ADDRESS`, `FOURSQUARE`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL` FROM `ORGS`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
?>
