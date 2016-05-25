<?php
session_start();

if(!isset($_SESSION['username']))	{
	header("Location: login.php");	
} else	{
	$firstname = $_SESSION['firstname'];
	require 'db_connection.php';
}

function getCourseDesc($ClassID)	{
	global $dbConn;
	
	$sql = "SELECT * FROM catalog as c JOIN schedule as s WHERE c.ClassID = :class AND c.CourseID=s.CourseID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(":class"=>$ClassID));
	return $stmt->fetch();
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
        <link href="css\teamProjectCSS.css" rel="stylesheet" type="text/css">
	</head>

<body>
<div id="wrapper">
<div id="header">
	<h1>Course Details</h1>
    <div id="menubar">
    <a href="index.php">Home</a>	|	<a href="catalog.php">Catalog</a>	|	<a href="schedule.php">Class Schedule</a>	|	<a href="studentregister.php">Register for Classes</a>	 |	<a href="logout.php">Login/Logout</a>		
    </div>
    <div id="SubMenu">
    <a href="viewschedule.php">View Schedule</a>	|	<a href="change_password.php">Change Password</a>
    </div>
</div>
<div id="mainbody">
<div id="welcome">Welcome <?=$firstname ?></div>
<? 
	if(isset($_GET['ClassID']))	{
		$c = getCourseDesc($_GET['ClassID']); 
	?>

<table id="CourseDescDetailsTable">
<tr>
	<td><?=$c['Subject']; ?></td>
</tr>
<tr>
	<td><?=$c['CourseID']; ?></td>
</tr>
<tr>
	<td><?=$c['Units']; ?></td>
</tr>
<tr>
	<td><?=$c['CourseDesc']; ?></td> <!--	needs to be added to table	-->
</tr>
<tr>
	<td><a href="catalog.php">Go back to Catalog</a></td>
</tr>
</table>

<? } ?>
</div>
</div>
</body>
</html>