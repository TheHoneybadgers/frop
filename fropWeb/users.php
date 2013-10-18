<?php 
	include 'db_helper.php';
	
	function getUser($id) {
		$dbQuery = sprintf("SELECT * FROM `USERS` WHERE USER_ID = '%s' OR GTID = '%s'", mysql_real_escape_string($id), mysql_real_escape_string($id));
		//echo "Query " . $dbQuery . "</br>";
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}

	function addUser($userGITD, $userGovOrgId, $userPerms, $userOrgId) {
		$dbQuery = sprintf("INSERT INTO `USERS` (`GTID`, `GOV_ORG_ID`, `PERMS`, `ORG_ID`) VALUES ('%s', '%s', '%s', '%s')", mysql_real_escape_string($userGITD), mysql_real_escape_string($userGovOrgId), mysql_real_escape_string($userPerms), mysql_real_escape_string($userOrgId));
		//echo "Query " . $dbQuery . "</br>";
		$result = getDBResultInserted($dbQuery,'USER_ID');
		header("Content-type: application/json");
		echo json_encode($result) . "</br>";
	}
?>
