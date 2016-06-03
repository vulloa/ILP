<?php
#   this page contains the database connection details

#  MYWINDOWSHOSTING host details
$host = "MYSQL5011.myWindowsHosting.com";
$dbname = "db_9b4669_csumb";
$username = "9b4669_csumb";
$password = "csumb2016";

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