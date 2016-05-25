<?php

#	link database file with database connection information
require "db_connection.php";

#	create a 1 time admin table

$sql = "CREATE TABLE nfl_admin (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,firstname varchar (50), lastname varchar (50), username varchar (50) NOT NULL, password varchar(50) NOT NULL)";

$stmt = $dbConn->prepare($sql);
$stmt->execute();

$sql="INSERT INTO nfl_admin (firstname,lastname,username,password) VALUES (:firstname,:lastname,:username,:password)";
$stmt = $dbConn->prepare($sql);
$stmt -> execute ( array (":firstname" => "Vanessa", ":lastname" => "Ulloa", ":username" => "ullo4940", ":password" => hash('sha1', 'cst336')));

echo "Your admin table is created!";

?>