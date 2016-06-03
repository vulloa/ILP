<?php
#	php file for database connection

#  MYWINDOWSHOSTING host details
$host = "MYSQL5011.myWindowsHosting.com";
$dbname = "db_9b4669_csumb";
$username = "9b4669_csumb";
$password = "csumb2016";

#	establish database connection using a try/catch so errors can be displayed

try	{
	$dbConn = new PDO("mysql:host=$host;dbname=$dbname",$username,$password);
}  catch (PDOException $e)	{
	die($e->getmessage());
}



?>