<?php
include 'db_credentials.php';
	
$connection = mysql_connect(
	$db_host,
	$db_username,
	$db_password
);

if(!$connection){
	die("Error connecting to the database.<br/><br/>" . 		
	mysql_error());
}

$db_select = mysql_select_db($db_database);
if(!$db_select){die("Error with db select.<br/><br/>".mysql_error());}

function getDBResultsArray($dbQuery){
	$dbResults=mysql_query($dbQuery);

	if(!$dbResults){
		$GLOBALS["_PLATFORM"]->sandboxHeader("HTTP/1.1 500 Internal Server Error");
		die();
	}
	
	$resultsArray = array();
	if(mysql_num_rows($dbResults) > 0){
		while($row = mysql_fetch_assoc($dbResults)){
			$resultsArray[] = $row;
		}	
	}else{
		$GLOBALS["_PLATFORM"]->sandboxHeader('HTTP/1.1 404 Not Found');
		die();
	}
	
	return $resultsArray;
}

function getDBResultRecord($dbQuery){
	$dbResults=mysql_query($dbQuery);

	if(!$dbResults){
		$GLOBALS["_PLATFORM"]->sandboxHeader("HTTP/1.1 500 Internal Server Error");
		die();
	}

	if(mysql_num_rows($dbResults) != 1){
		$GLOBALS["_PLATFORM"]->sandboxHeader('HTTP/1.1 404 Not Found');
		die();
	}
	return mysql_fetch_assoc($dbResults);
}

function getDBResultAffected($dbQuery){
	$dbResults=mysql_query($dbQuery);
	if($dbResults){
		return array('rowsAffected'=>mysql_affected_rows());
	}else{
		$GLOBALS["_PLATFORM"]->sandboxHeader('HTTP/1.1 500 Internal Server Error');
		die(mysql_error());
	}
}

function getDBResultInserted($dbQuery,$id){
	$dbResults=mysql_query($dbQuery);
	if($dbResults){
		return array($id=>mysql_insert_id());
	}else{
		$GLOBALS["_PLATFORM"]->sandboxHeader('HTTP/1.1 500 Internal Server Error');
		die(mysql_error());
	}
}
?>
