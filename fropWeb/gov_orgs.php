<?php 
	include 'db_helper.php';
	
	function listGovOrgs() {
		$dbQuery = sprintf("SELECT `GOV_ORG_ID`, `NAME`, `ACRONYM` FROM `GOV_ORGS`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getGovOrg($id) {
		$dbQuery = sprintf("SELECT `NAME`, `ACRONYM` FROM `GOV_ORGS` WHERE `GOV_ORG_ID` = '%s'", mysql_real_escape_string($id));
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
#TODO pic approved rules
	function getOrgs($id) {
		$dbQuery = sprintf("SELECT `ORG_ID`, `LETTERS`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `ADDRESS`, `FOURSQUARE`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL` FROM `ORGS` WHERE GOV_ORG_ID = '%s'", mysql_real_escape_string($id));
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}

## TODO make this addGovOrg()	
#	function getGovOrg() {
#		$dbQuery = sprintf("SELECT `GOV_ORG_ID`, `NAME`, `ACRONYM` FROM `GOV_ORGS` WHERE `GOV_ORG_ID` = '%s'", mysql_real_escape_string($gov_orgs));
#		$result = getDBResultsArray($dbQuery);
#		header("Content-type: application/json");
#		echo json_encode($result);
#	}
?>
