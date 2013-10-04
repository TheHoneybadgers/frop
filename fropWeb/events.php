<?php
	include 'db_helper.php';
	
	function listEvents() {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getEvent($id) {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE EVENT_ID = '%s'",
			mysql_real_escape_string($event));
		$result=getDBResultRecord($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}

/*	
	function addEvent($event) {
		$dbQuery = sprintf("INSERT INTO events (event) VALUES ('%s')",
			mysql_real_escape_string($event));
	
		$result = getDBResultInserted($dbQuery,'personId');
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function updateEvent($id,$event) {
		$dbQuery = sprintf("UPDATE events SET event = '%s' WHERE id = '%s'",
			mysql_real_escape_string($event),
			mysql_real_escape_string($id));
		
		$result = getDBResultAffected($dbQuery);
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
*/

?>
