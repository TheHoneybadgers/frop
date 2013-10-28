<?php 
	include 'db_helper.php';
	
	function listBadges() {
		$dbQuery = sprintf("SELECT `BADGE_ID`, `NAME`, `GOAL`, `GOAL_UNITS`, `DESCRIPTION`, `PRE_REQ`, `CUSTOM_PIC_URL`, `ADDED_DATE` FROM `BADGES`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getBadge($id) {
		$dbQuery = sprintf("SELECT `NAME`, `GOAL`, `GOAL_UNITS`, `DESCRIPTION`, `PRE_REQ`, `CUSTOM_PIC_URL`, `ADDED_DATE` FROM `BADGES` WHERE `BADGE_ID` = '%s'", mysql_real_escape_string($id));
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function addBadge($badgeName, $badgeGoal, $badgeGoalUnits, $badgeDescription, $badgePreReq, $badgeCustomPicUrl) {
		$dbQuery = sprintf("INSERT INTO BADGES (`NAME`, `GOAL`, `GOAL_UNITS`, `DESCRIPTION`, `PRE_REQ`, `CUSTOM_PIC_URL`, `ADDED_DATE`, `ADDED_BY`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", mysql_real_escape_string($badgeName), mysql_real_escape_string($badgeGoal), mysql_real_escape_string($badgeGoalUnits),mysql_real_escape_string($badgeDescription), mysql_real_escape_string($badgePreReq), mysql_real_escape_string($badgeCustomPicUrl), "CURRENT_TIMESTAMP()", $_USER['uid']);
		//echo "Query " . $dbQuery . "</br>";
		$result = getDBResultInserted($dbQuery,'BADGE_ID');
		header("Content-type: application/json");
		echo json_encode($result) . "</br>";
	}
?>
