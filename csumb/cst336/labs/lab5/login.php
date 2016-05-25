<?php

session_start();

$LoginError = "";

if(isset($_POST['username']))	{
	global $LoginError;
	require('db_connection.php');
	
	$sql = "SELECT * FROM nfl_admin WHERE username = :username AND password = :password";
	$stmt = $dbConn->prepare($sql);
	$stmt -> execute(array(":username" => $_POST['username'], ":password" => hash("sha1", $_POST['password'])));
	$record = $stmt->fetch();
	
	if(empty($record))	{
		#echo "Wrong username/password!";	
		$LoginError = "Wrong Username/password !";
	} else	{
		$_SESSION['username'] = $record['username'];
		$_SESSION['name'] = $record['firstname']. " " . $record['lastname'];
		header("Location:index.php");	
	}
		
}

?>
<!--	BEGIN HTML	-->

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>Vanessa Ulloa CST336 LAB 5</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
        <link href="css/lab5CSS.css" rel="stylesheet" type="text/css">
        <style>
			form	{
				display: inline;
			}
		</style>
	</head>

<body>
	<div id="wrapper">
    <div id="header">
    	<h1>Login</h1>
	</div>
	<div id="login">
	<form method="POST">
    	Username: <input type="text" name="username" /><br />
        <p></p>
        Password: <input type="password" name="password" /><br />
        <p></p>
        <input type="submit" value="Login" />
        <p></p>
    </form>
    <p>
    <? echo "<span>" . $LoginError . "</span>"; ?>
    </p>
    <p>
    Username: su5196<Br />
    Password: secret
    </p>
    </div>
    </div>
</body>
</html>
