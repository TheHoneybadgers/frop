<?php 
	include 'db_helper.php';
	
	function listBadges() {
		$dbQuery = sprintf("SELECT `BADGE_ID`, `NAME`, `GOAL`, `GOAL_UNITS`, `DESCRIPTION`, `PRE_REQ`, `CUSTOM_PIC_URL`, `ADDED_DATE` FROM `BADGES`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
?>
