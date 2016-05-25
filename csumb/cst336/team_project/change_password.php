<?php
session_start();
$StatusMessage = "";

if(!isset($_SESSION['username']))	{
	header("Location: login.php");	
} else	{
	$firstname = $_SESSION['firstname'];
	require 'db_connection.php';
}

require 'db_connection.php';

   if(isset($_POST['password'])){
	$sql = "UPDATE team_project_admin
		SET password = :New_password
		WHERE username = :username
		AND password = :password";
	$stmt = $dbConn -> prepare($sql);
	$stmt -> execute(array(":username" => $_SESSION['username'], ":password" => hash('sha1', $_POST['password']), ":New_password" => hash('sha1', $_POST['New_password'])));

#echo " Your password has been updated";
	$StatusMessage = "Your password has been updated";
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

		<title>T7 Team Project</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
        <link href="css/teamProjectCSS.css" rel="stylesheet" type="text/css">
	</head>
<div id="wrapper">
<div id="header">
	<h1>Change Password</h1>
    <div id="menubar">
    <a href="index.php">Home</a>	|	<a href="catalog.php">Catalog</a>	|	<a href="schedule.php">Class Schedule</a>	|	<a href="studentregister.php">Register for Classes</a>	 |	<a href="logout.php">Login/Logout</a>		
    </div>
    <div id="SubMenu">
    <a href="viewschedule.php">View Schedule</a>	|	<a href="change_password.php">Change Password</a>
    </div>
</div>
<div id="mainbody">
<div id="welcome">Welcome <?=$firstname ?></div>
<div id="status"><?=$StatusMessage?></div>
<form method = "post">
<table>
<!--	<tr>
	<td>Username:</td><td><input type="text" name="username"></td>
	</tr>	-->
    <tr>
	<td>Current Password:</td><td> <input type="password" name="password"></td>
	</tr><tr>
	<td>New Password:</td><td><input type="password" name="New_password"></td>
	</tr><tr>
	<td><input type="submit" name = "click" value="submit"></td>
</table>
</form>

</div>
</div>
</body>
</html>