<?php
	include 'db_helper.php';
	
	function listEvents() {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS`");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getEvent($id) {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE `EVENT_ID` =  '%s'", mysql_real_escape_string($id));
		$result=getDBResultRecord($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getThisWeek() {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE `DATE` > CURDATE() AND `DATE` < CURDATE() + 7");
		$result=getDBResultRecord($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
/* TODO: Fix function

	function addEvent($title, $date, $org_id, $foursquare, $address, $start_time, $end_time, $summary, $type, $special_notes, $alcohol) {
		$dbQuery = sprintf("INSERT INTO EVENTS (`TITLE`, `DATE`, `ORG_ID`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
			mysql_real_escape_string($_POST[$title])
			mysql_real_escape_string($_POST[$date])
			mysql_real_escape_string($_POST[$org_id])
			mysql_real_escape_string($_POST[$foursquare])
			mysql_real_escape_string($_POST[$address])
			mysql_real_escape_string($_POST[$start_time])
			mysql_real_escape_string($_POST[$end_time])
			mysql_real_escape_string($_POST[$summary])
			mysql_real_escape_string($_POST[$type])
			mysql_real_escape_string($_POST[$special_notes])
			mysql_real_escape_string($_POST[$alcohol]));
	
		$result = getDBResultInserted($dbQuery,'`EVENT_ID`');
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
*/

/*	
	function updateEvent($id,$event) {
		$dbQuery = sprintf("UPDATE events SET event = '%s' WHERE id = '%s'",
			mysql_real_escape_string($events),
			mysql_real_escape_string($id));
		
		$result = getDBResultAffected($dbQuery);
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
*/

?>
