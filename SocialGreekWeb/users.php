<?php 
	include 'db_helper.php';
	
	function getUser($id) {
		$dbQuery = sprintf("SELECT * FROM `USERS` WHERE USER_ID = '%s' OR GTID = '%s'", 
			mysql_real_escape_string($id), 
			mysql_real_escape_string($id)
		);
		//echo "Query " . $dbQuery . "</br>";
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getUserPerm($id) {
		$dbQuery = sprintf("SELECT `PERMS` FROM `USERS` WHERE USER_ID = '%s' OR GTID = '%s'", 
			mysql_real_escape_string($id), 
			mysql_real_escape_string($id)
		);
		echo $dbQuery;
//		$result = ResultsArray($dbQuery);
// 		header("Content-type: application/json");
// 		echo json_encode($result);
	}
	
	function addUser($userGTID, $userGovOrgId, $userPerms, $userOrgId) {
		$dbQuery = sprintf("INSERT INTO `USERS` (`GTID`, `GOV_ORG_ID`, `PERMS`, `ORG_ID`, `ADDED_BY`, `ADDED_DATE`, `SUSPENDED`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
			mysql_real_escape_string($userGTID), 
			mysql_real_escape_string($userGovOrgId), 
			mysql_real_escape_string($userPerms), 
			mysql_real_escape_string($userOrgId), 
			mysql_real_escape_string($_USER['uid']), 
			"CURRENT_TIMESTAMP()", 
			"false"
		);
		
		//echo "Query " . $dbQuery . "</br>";
		$result = getDBResultInserted($dbQuery,'USER_ID');
		
		header("Content-type: application/json");
		echo json_encode($result); //. "</br>";
	}
	
	function suspendUser($userGTID, $userSuspendedReason) {
		$dbQuery = sprintf("UPDATE `USERS` SET `SUSPENDED_REASON` = '%s', `SUSPENDED` = 'true', `SUSPENDED_DATE` = CURTIME(), `SUSPENDED_BY` = '%s' WHERE GTID = '%s'", 
			mysql_real_escape_string($userSuspendedReason), 
			mysql_real_escape_string($_USER['uid']), 
			mysql_real_escape_string($userGTID)
		);
		
		//echo "Query " . $dbQuery . "</br>";
		$result = getDBResultAffected($dbQuery,'USER_ID');
		
		header("Content-type: application/json");
		echo json_encode($result);
	
	}
?>
