<?php
session_start();

#db connection
require 'db_connection.php';

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
	<style>
		h1 {
			text-align:center;
		}
		#wrapper {
			width:900px;
			margin:0px auto;
			border:5px black solid;
			padding: 10px;
		}
		p {
			text-align: center;
		}
	</style>
<body>
<div id="wrapper">
<div id="header" >
	<h1>Welcome to the USS Enterprise Community College </h1>
    <div id="menubar">
    <a href="index.php">Home</a>	|	<a href="catalog.php">Catalog</a>	|	<a href="schedule.php">Class Schedule</a>	|	<a href="studentregister.php">Register for Classes</a>	 |	<a href="logout.php">Login/Logout</a>		
    </div>
    <div id="SubMenu">
    <a href="viewschedule.php">View Schedule</a>	|	<a href="change_password.php">Change Password</a>
    </div>
</div>
<div id="mainbody">
<p>Welcome to the USS Enterprise Community College!<br><br> As you are currently stuck on board the USS Enterpise for the next five years while we travel through the final frontier, exploring
strange new worlds, seeking out new life and new civilizations, and boldly go where no man has gone before, you might as well make the best use of your down time and educate yourself!
</p>
<p>If you can survive these grueling courses, you will be meet the requirements to apply to Star Fleet Academy.</p>
<p>Please use the menu above to start your education!</p>
</div>
</div>
</body>
</html>