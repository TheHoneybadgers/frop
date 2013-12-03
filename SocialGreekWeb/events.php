<?php
	include 'db_helper.php';
	
	function listEvents($approved) {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE `APPROVED` = '%s'",
			mysql_real_escape_string($approved)
		);
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function listCurrentEvents($approved) { // TODO why do we have this and get this week, important to have both?
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE `DATE` > CURDATE() AND `APPROVED` = '%s'",
			mysql_real_escape_string($approved)
		);
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getEvent($id) {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE `EVENT_ID` =  '%s'",
			mysql_real_escape_string($id)
		);
		$result=getDBResultRecord($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getThisWeek($approved) {
		$dbQuery = sprintf("SELECT `EVENT_ID`, `DATE`, `ORG_ID`, `TITLE`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `APPROVED_DATE`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`,`DATE_CHANGED` FROM `EVENTS` WHERE `DATE` > CURDATE() AND `DATE` < CURDATE() + 7",
			mysql_real_escape_string($approved)
		);
		$result=getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
  
	function addEvent($title, $date, $org_id, $foursquare, $address, $start_time, $end_time, $summary, $type, $special_notes, $alcohol) {
		$dbQuery = sprintf("INSERT INTO EVENTS (`TITLE`, `DATE`, `ORG_ID`, `FOURSQUARE`, `ADDRESS`, `START_TIME`, `END_TIME`, `SUMMARY`, `TYPE`, `SPECIAL_NOTES`, `ALCOHOL`, `CREATED_DATE`, `CREATED_BY`, `DELETED`, `APPROVED`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
			mysql_real_escape_string($title),
			mysql_real_escape_string($date),
			mysql_real_escape_string($org_id),
			mysql_real_escape_string($foursquare),
			mysql_real_escape_string($address),
			mysql_real_escape_string($start_time),
			mysql_real_escape_string($end_time),
			mysql_real_escape_string($summary),
			mysql_real_escape_string($type),
			mysql_real_escape_string($special_notes),
			mysql_real_escape_string($alcohol),
			mysql_real_escape_string("CURRENT_TIMESTAMP()"),
			mysql_real_escape_string($_USER['uid']),
			mysql_real_escape_string("false"),
			mysql_real_escape_string("0")
		);
	
		$result = getDBResultInserted($dbQuery,'`EVENT_ID`');
		header("Content-type: application/json");
		echo json_encode($result);
	}

	// TODO decide what to be able to update
	// If update, set approved to 0 (awaiting approval value)
	function updateEvent($id,$event) {
		$dbQuery = sprintf("UPDATE EVENTS SET event = '%s' WHERE id = '%s'",
			mysql_real_escape_string($events),
			mysql_real_escape_string($id));
		
		$result = getDBResultAffected($dbQuery);
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function approveEvent($event_id, $approval_level) {
		$dbQuery = sprintf("UPDATE `EVENTS` SET `APPROVED` = '%s', `APPROVED_BY` = '%s', `APPROVED_DATE` = CURRENT_TIMESTAMP() WHERE `EVENT_ID` = '%s'",
			mysql_real_escape_string($approval_level),
			mysql_real_escape_string($_USER['uid']),
			mysql_real_escape_string($event_id));
		
		$result = getDBResultAffected($dbQuery);
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function disapproveEvent($event_id, $approval_level) {
		$dbQuery = sprintf("UPDATE `EVENTS` SET `APPROVED` = '%s', `APPROVED_BY` = '%s', `APPROVED_DATE` = CURRENT_TIMESTAMP() WHERE `EVENT_ID` = '%s'",
			mysql_real_escape_string($approval_level),
			mysql_real_escape_string($_USER['uid']),
			mysql_real_escape_string($event_id));
		
		$result = getDBResultAffected($dbQuery);
		
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
?>
