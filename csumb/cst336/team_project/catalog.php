<?php
session_start();

if(!isset($_SESSION['username']))	{
	header("Location: login.php");	
} else	{
	$firstname = $_SESSION['firstname'];
	require 'db_connection.php';
}


require 'db_connection.php';

function SubjectDropDown()	{
	#create the drop-down list of subjects to filter with
	#replace catalog with catalog table
	global $dbConn;
	
	#DISTINCT ensures that drop down does not have duplicate values
	$sql = "SELECT DISTINCT Subject FROM catalog ORDER BY Subject";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	return $stmt->fetchAll();		
}

function DisplayCatalog()	{
	#Display catalog values from the table
	global $dbConn;
	
	$sql = "SELECT * FROM catalog ORDER BY CourseID";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	return $stmt->fetchAll();
}

function filterCatalog()	{
	#filter based on dropw-down value selected
	#GET value will display in the URL
	global $dbConn;
	#isset function is in the HTML of the page
	$Subject = $_GET['Subject'];
	
	$sql = "SELECT * FROM catalog WHERE Subject LIKE CONCAT(:Subject,'%')";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(':Subject'=>$Subject));
	return $stmt->fetchAll();
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
	<h1>Course Catalog</h1>
    <div id="menubar">
    <a href="index.php">Home</a>	|	<a href="catalog.php">Catalog</a>	|	<a href="schedule.php">Class Schedule</a>	|	<a href="studentregister.php">Register for Classes</a>	 |	<a href="logout.php">Login/Logout</a>		
    </div>
    <div id="SubMenu">
    <a href="viewschedule.php">View Schedule</a>	|	<a href="change_password.php">Change Password</a>
    </div>
</div>
<div id="mainbody">
<div id="welcome">Welcome <?=$firstname ?></div>
<form method="get" id="CatalogFilter">
	<select name="Subject">
    	<option value="-1">Select Subject</option>
        <?php
		$catalog = SubjectDropDown();
		foreach($catalog as $cat)	{
			echo "<option value=" . $cat['Subject'] . ">" . $cat['Subject'] . "</option>";	
		}
		?>
    </select>
    <input type="submit" value="Filter by Subject" />
</form>
<table id="catalogTable">
<tr>
	<th colspan='3'>Catalog</th>
</tr>	
<tr>
	<td colspan="2">Course</td>
    <td>Units</td>
</tr>
<?php
	#$catalog = getCatalogValues();
	if(isset($_GET['Subject']))	{
		$catalog = filterCatalog();
	} else {
		$catalog = DisplayCatalog();	
	} ?>

<?php	
	foreach($catalog as $cat)	{
		echo "<tr>";
		?>
        <form method="GET" action="ViewCourseDesc.php">
        <input type="hidden" name="ClassID" value="<?=$cat['ClassID'] ?>" />
        <td><input type="submit" name="ViewInfo" value="View Info" /></td>
        </form>
        <?
		echo "<td>" . $cat['CourseID'] . "</td>";
		echo "<td>" . $cat['Units'] . "</td>";
		echo "</tr>";	
	}
?>

</table>

</div>
</div>
</body>
</html>