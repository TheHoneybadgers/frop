<?php
	include 'db_helper.php';
	
	function listEvents() {
		$dbQuery = sprintf("SELECT event_id,date,org_id,title FROM events");
		$result = getDBResultsArray($dbQuery);
		header("Content-type: application/json");
		echo json_encode($result);
	}
	
	function getEvent($id) {
		$dbQuery = sprintf("SELECT event_id,date,org_id,title FROM events WHERE event_id = '%s'",
			mysql_real_escape_string($id));
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
