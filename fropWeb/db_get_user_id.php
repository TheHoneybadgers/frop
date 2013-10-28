<?php
	include 'db_helper.php';
	
	function getUserID($user) {
		$dbQuery = sprintf("SELECT `USER_ID`, `GOV_ORG_ID`, `PERMS` FROM `USERS` WHERE `GTID` = '%d'", mysql_real_escape_string($user));
		$result = getDBResultsRecord($dbQuery);
		return $result;
	}
?>
