<?php
#	php file for database connection

#	csumb default

$host="localhost";
$dbname="ullo4940";
$username="ullo4940";
$password="vanessa336";

/**
#	localhost (xampp)
$host="localhost";
$dbname="lab4";
$username="root";
$password="vanessa336";
**/
#	external sql source (fatcow or mywindowshosting)
/**
$host="localhost";
$dbname="";
$username="";
$password="";
**/

#	establish database connection using a try/catch so errors can be displayed

try	{
	$dbConn = new PDO("mysql:host=$host;dbname=$dbname",$username,$password);
}  catch (PDOException $e)	{
	die($e->getmessage());
}



?>