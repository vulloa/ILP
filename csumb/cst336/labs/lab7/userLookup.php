<?php

require('../../db_connection.php');

if(isset($_POST['username']))	{

	$sql = "SELECT username FROM lab9_user WHER username = :username";
	
	$stmt = $dbConn->prepare($sl);
	$stmt->execute(array(":username" => $_POST['username']));
	$record = $stmt->fetch();
	
	$output = array();
	
	if(empty($record))	{
		echo "{\"exists\":\"true\"}";
		$output["exists"] = "false";
	}	else	{
		echo "Username already taken";
        $output["exists"] = "true";
	}
	echo json_encode($output);
}

?>