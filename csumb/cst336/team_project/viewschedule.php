<?php
session_start();
$StatusMessage = "";

if(!isset($_SESSION['username']))	{
	header("Location: login.php");	
} else	{
	$firstname = $_SESSION['firstname'];
	require 'db_connection.php';
}

function ViewSchedule()	{
	global $dbConn;
	$ID = $_SESSION['id'];
	
	$sql = "SELECT * FROM enrollment as e, catalog as c, schedule as s WHERE e.StudentID = :id AND c.CourseID=s.CourseID AND s.SectionID=e.SectionID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':id'=>$ID));
	return $stmt->fetchAll();
}

function get_units(){
	global $dbConn;
	$ID = $_SESSION['id'];
	
	$sql = "SELECT SUM(Units) as totalU FROM enrollment as e, catalog as c, schedule as s WHERE e.StudentID = :id AND c.CourseID=s.CourseID AND s.SectionID=e.SectionID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':id'=>$ID));
	return $stmt->fetch();
}

function total_classes(){
	global $dbConn;
	$ID = $_SESSION['id'];
	
	$sql = "SELECT COUNT(SectionID) as totalC FROM enrollment WHERE StudentId =:id";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':id'=>$ID));
	return $stmt->fetch();
}

function getInstructor($Ins)	{
	#get instructor name based on instructor ID in the table
	global $dbConn;
	$InsID = $Ins;
	
	$sql = "SELECT FirstName,LastName FROM team_project_admin WHERE ID=:ID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':ID'=>$InsID));
	$total = $stmt->fetch();
	return $total;
	
}

if(isset($_GET['register']))	{
	global $dbConn;
	$ID = $_SESSION['id'];
	$SectionID = $_GET['register'];
	
	$sql = "DELETE FROM enrollment WHERE SectionID = :sectionid";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':sectionid'=>$SectionID));
	
	if(!$stmt)	{
		$StatusMessage = "Drop Unsuccessful";
	}	else	{
		$StatusMessage = "Schedule Updated";
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

		<title>T7 Team Project</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
        <link href="css/teamProjectCSS.css" rel="stylesheet" type="text/css">
	</head>

<body>
<div id="wrapper">
<div id="header">
	<h1>View Schedule</h1>
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
<form id="schedule" method="GET">
<table id="ViewScheduleTable">
<tr>
	<td colspan="8">Schedule</td>
</tr>
<tr>
	<td />
	<td>Section</td>
    <td>Course</td>
    <td>Teacher</td>
    <td>Units</td>
    <td>Days</td>
    <td>Time</td>
</tr>
<tr>
<?php
	$Schedule = ViewSchedule();
	if(!empty($Schedule))	{
		foreach($Schedule as $s)	{
			echo "<tr>";
			echo "<td><input type='radio' name='register' value='" . $s['SectionID'] . "'></td>";
			echo "<td>" . $s['SectionID'] . "</td>";
			echo "<td>" . $s['CourseID'] . "</td>";
			$teacher = getInstructor($s['InstructorID']);
			echo "<td>" . $teacher['FirstName'] . " " . $teacher['LastName'] . "</td>"; 
			echo "<td>" . $s['Units'] . "</td>";
			echo "<td>" . $s['Days'] . "</td>";
			echo "<td>" . $s['Time'] . "</td>";
			echo "</tr>";
		} ?>
        	<td colspan="8"><input type="submit" value="Drop" /></td>
       <? echo "</tr>";
		}	else	{ ?>
			<tr><td colspan="8">No Classes Registered</td></tr>
 <?php } ?>
</tr>
</table>
</form>
<br/><br/>
<tabel id ="Total_Units_Table">
<?php
	$Schedule = ViewSchedule();
	$Units = get_units();
	if(!empty($Schedule)){
		echo "<tr>";
		echo "<td>" . "Total Units " . "</td>";
		echo "<td>" . $Units['totalU'] . "</td>";
		echo "</tr>";
	}
?>		
</tabel>
<br/><br/>
<table id ="Total_Classes_Table">
<?php
	$Classes = total_classes();
	$Schedule = ViewSchedule();
	if(!empty($Schedule)){
		echo "<tr>";
		echo "<td>" . "Total Classes " . "</td>";
		echo "<td>" . $Classes['totalC'] . "</td>";
		echo "</tr>";
	}
?>	
	
</table>
			

</div>
</div>
</body>
</html>