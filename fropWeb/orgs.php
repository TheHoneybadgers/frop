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
  
	function addOrg($orgLetters, $orgGovOrgId, $orgChapter, $orgNickname, $orgType, $orgFocus, $orgYearFounded, $orgYearChapterFounded, $orgBlurb, $orgFoursquare, $orgAddress, $orgHomepageUrl, $orgCustomPicUrl) {
		$dbQuery = sprintf("INSERT INTO ORGS (`LETTERS`, `GOV_ORG_ID`, `CHAPTER`, `NICKNAME`, `TYPE`, `FOCUS`, `YEAR_FOUNDED`, `YEAR_CHAPTER_FOUNDED`, `BLURB`, `FOURSQUARE`, `ADDRESS`, `HOMEPAGE_URL`, `CUSTOM_PIC_URL`, `PIC_APPROVED`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s', '%s', '%s', '%s', '%s', '%s')", mysql_real_escape_string($orgLetters), mysql_real_escape_string($orgGovOrgId), mysql_real_escape_string($orgChapter), mysql_real_escape_string($orgNickname), mysql_real_escape_string($orgType), mysql_real_escape_string($orgFocus), mysql_real_escape_string($orgYearFounded), mysql_real_escape_string($orgYearChapterFounded), mysql_real_escape_string($orgBlurb), mysql_real_escape_string($orgFoursquare), mysql_real_escape_string($orgAddress), mysql_real_escape_string($orgHomepageUrl), mysql_real_escape_string($orgCustomPicUrl), "false");
		
		echo "Query " . $dbQuery . "</br>";
		$result = getDBResultInserted($dbQuery,'ORG_ID');
		
		header("Content-type: application/json");
		echo json_encode($result) . "</br>";
	}
?>
