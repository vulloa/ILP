<?php
session_start();

$StatusMessage = "";

if(!isset($_SESSION['username']))	{
	header("Location: login.php");	
} else	{
	$firstname = $_SESSION['firstname'];
	require 'db_connection.php';
}


require('db_connection.php');

function SubjectDropDown()	{
	#create the drop-down list of subjects to filter with
	global $dbConn;
	
	#DISTINCT ensures that drop down does not have duplicate values
	$sql = "SELECT DISTINCT Subject FROM catalog ORDER BY Subject";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	return $stmt->fetchAll();		
}

function DisplaySchedule()	{
	#display Schedule with values from schedule and catalog tables
	global $dbConn;
	
	#using an alias s and c for each table makes writing large statements easier, easier to read
	#match up CourseID on both tables so that it does not print a repetitive list
	$sql = "SELECT * FROM catalog as c INNER JOIN schedule as s WHERE c.CourseID = s.CourseID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	return $stmt->fetchAll();
}

function filterSchedule()	{
	#filter based on drop-down value selected
	#GET method, subject will display in URL
	global $dbConn;
	# isset function is in the table in the HTML
	$Subject = $_GET['Subject'];
	
	$sql = "SELECT * FROM catalog as c INNER JOIN schedule as s WHERE c.CourseID = s.CourseID AND c.Subject LIKE CONCAT(:Subject,'%')";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':Subject'=>$Subject));
	return $stmt->fetchAll();
		
}

function getInstructor($Ins)	{
	#get instructor name based on instructor ID in the table
	global $dbConn;
	$InsID = $Ins;
	
	$sql = "SELECT FirstName,LastName FROM team_project_admin WHERE ID=:ID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':ID'=>$InsID));
	return $stmt->fetch();
	
}

if(isset($_POST['register']))	{
	$ID = $_SESSION['id'];
	$SectionID = $_POST['register'];
	
	$sql = "INSERT INTO enrollment (StudentID,SectionID) VALUES (:id,:sectionID)";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':id'=>$ID,':sectionID'=>$SectionID));
	
	if(!$stmt)	{
		$StatusMessage = "Registration Unsuccessful";
	} else	{
		$StatusMessage = "Succcess!";
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
        <link href="css\teamProjectCSS.css" rel="stylesheet" type="text/css">
	</head>

<body>
<div id="wrapper">
<div id="header">
	<h1>Register for Classes</h1>
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
<form method="get" id="CatalogFilter">
	<select name="Subject">
    	<option value="-1">Select Subject</option>
        <?php
		$dropdown = SubjectDropDown();
		foreach($dropdown as $s)	{
			echo "<option value=" . $s['Subject'] . ">" . $s['Subject'] . "</option>";	
		}
		?>
    </select>
    <br />
    <input type="submit" value="Filter" />
</form>
<form method="GET">
	<input type="submit" value="View All" name="ViewAll" />
</form>
<table id="catalogTable">
<tr>
	<th colspan='6'>Schedule of Classes</th>
</tr>	
<tr>
	<td />
	<td>Section</td>
    <td>Course</td>
    <td>Instructor</td>
    <td>Days</td>
    <td>Time</td>
</tr>
<form method="POST" id="register">
<?php
	#changes what is returned to the $classes variable based on filter or no filter
	if(isset($_GET['Subject']))	{
		$classes = filterSchedule();
	}	else	{
		$classes = DisplaySchedule();
	}
		foreach($classes as $s)	{
			echo "<tr>";
			echo "<td><input type='radio' name='register' value='" . $s['SectionID'] . "'></td>";
			echo "<td>" . $s['SectionID'] . "</td>";
			echo "<td>" . $s['CourseID'] . "</td>";
			$teacher = getInstructor($s['InstructorID']);
			echo "<td>" . $teacher['FirstName'] . " " . $teacher['LastName'] . "</td>"; 
			echo "<td>" . $s['Days'] . "</td>";
			echo "<td>" . $s['Time'] . "</td>";
			echo "</tr>";	
		} #END foreach

?>
<tr>
	<td colspan="6"><input type="submit" value="Register" /></td>
</tr>
</form>
</table>

</div>
</div>
</body>
</html>