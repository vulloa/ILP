<?php
#   this page contains the database connection details

/**
#   CSUMB host default values
$host = "localhost";
$dbname = "ullo4940";
$username = "ullo4940";
$password = "vanessa336";
**/

#  MYWINDOWSHOSTING host details
$host = "MYSQL5015.myWindowsHosting.com";
$dbname = "db_9b4669_cst336";
$username = "9b4669_cst336";
$password = "vanessa336";

/**
#   LOCALHOST DETAILS (personal computer)
$host = 'localhost';
$dbname = 'cst336';
$username = 'root';
$password = 'vanessa';
**/
/**
#   the way that we were shown
    #   establishes a new database connection
    $dbConn = new PDO("mysql:host=$host;dbname=$dbname",$username,$password);
    
    #   shows errors when connecting to the database
    $dbConn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
**/

#   connect using try catch -- this helps show a more specific error message

    try {
        $dbConn = new PDO("mysql:host=$host;dbname=$dbname",$username,$password);
    }   catch(PDOException $e)  {
        die($e->getmessage());
    }



?>