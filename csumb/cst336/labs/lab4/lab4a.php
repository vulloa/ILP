<?php

//	default
#$host = "localhost";
#$dbname = "ullo4940";
#$username = "ullo4940";
#$password = "vanessa336";
//	temp database until CSUMB hosting DB lets me log in
#$host = "veulloa.fatcowmysql.com";
#$dbname = "lab4";
#$username = "veucst336";
#$password = "vanessa336";

$host = "MYSQL5011.myWindowsHosting.com";
$dbname = "db_9b4669_csumb";
$username = "9b4669_csumb";
$password = "csumb2016";

#$host = "localhost";
#$dbname = "lab4";
#$username = "root";
#$password = "vanessa336";

#   LOCALHOST DETAILS (work laptop)
#$host = "localhost";
#$dbname = "csumb_server";
#$username = "root";
#$password = "team11";


//	establishes database connection
//$dbConn = new PDO("mysql:host=$host;dbname=$dbname",$username,$password);

//	try/catch for debugging if I can't connection to the database
try{
	$dbConn = new PDO("mysql:host=$host;dbname=$dbname",$username,$password);
} catch(PDOException $e)	{
	die($e->getmessage());
}
  
//	shows errors when connecting to database
//$dbConn->setAttribute(PDO::ATRR_ERRMODE,PDO::ERRMODE_EXCEPTION);

	
/**	Getting all team names and their stadium id	**/
$sql = "SELECT teamName, stadiumId 
        FROM nfl_team 
        ORDER BY teamName ASC";

//	prepares a statement for execution and returns a statement object
$stmt = $dbConn->prepare($sql);	
//	execture the prepared statement
$stmt->execute();
//	store the obtained data into an array variable
$teamNames = $stmt->fetchAll();

/**	Getting stadiuminfo based on stadium Id	**/

if (isset ($_GET['stadiumId'])) {
   $stadiumId = $_GET['stadiumId'];
   $sql = "SELECT * 
           FROM nfl_stadium 
           WHERE stadiumId = :stadiumId ";
        
   $stmt = $dbConn -> prepare($sql);
   $stmt -> execute( array (':stadiumId' => $stadiumId));
   $stadiumInfo = $stmt->fetch();
   
   # get variable from the form
   $SID = $_GET['stadiumId'];
   # generate the query statement, looks for the form variable in the table
   $s2 = "SELECT teamName FROM nfl_team WHERE stadiumId = :SID";
   # connects to teh database
   $s2 = $dbConn->prepare($s2);
   # runs the query in the database and returns the value into the variable
   $s2->execute(array(':SID'=> $SID));
   # variable will display value 
   $SID = $s2->fetch();
   
}

/**  STEP 2   **/
//	Top 5 statems with the largest combined number of seats in NFL stadiums

function largestCombinedCapacity()	{
	//	it uses the variables previously declared
	global $dbConn, $stmt;
	$sql = "SELECT state, SUM(capacity) combinedCapacity 
            FROM nfl_stadium GROUP BY state 
            ORDER BY combinedCapacity DESC LIMIT 5";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	
	return $stmt->fetchAll();
}

/**  STEP 5   **/
//	NFL teams and their home stadiums
function teamsAndStadiums()	{
	//	it uses the variables delcared previously
	global $dbConn, $stmt;
	$sql = "SELECT teamName, stadiumName, state 
            FROM nfl_team t JOIN nfl_stadium s ON t.stadiumId = s.stadiumId 
            ORDER BY teamname";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	
	return $stmt->fetchAll();
	
}

?>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>Vanessa Ulloa CST336 LAB 4A</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<link href="css/lab4css.css" rel="stylesheet" type="text/css">

	</head>

	<body>
    <div id="wrapper">
        <div id="header">
	        <h2>NFL Teams & Stadiums Queries</h2>
        </div>
    <div id="PartOne">        
	<form>
		<select name="stadiumId">
			<option value="-1"> - Select Team -</option>
			<?php
				foreach ($teamNames as $team)	{
					echo "<option value=" . $team['stadiumId'] . ">" . $team['teamName'] . "</option>";
				}
			?>
		</select>
		<input type="submit" value="Get Stadium Info!" class="SubmitButton" />
	</form>
	
	<?php

	if(isset($stadiumInfo) && !empty($stadiumInfo))	{
        echo "<Br />";
        //  display what the user just selected since the drop down clears itself on reload
        echo "<span>" . $SID['teamName'] . "</span><br />";
		echo $stadiumInfo['stadiumName'] . "<br />";
		echo $stadiumInfo['street'] . "<br />";
		echo $stadiumInfo['city'] . ", " . $stadiumInfo['state'] . " " . $stadiumInfo['zip'] . "<br />";	
	}
	
	?>	
    </div>
    
    <div id="PartTwo">
    <!-----	STEP 1 -->
    <br />
    <h3>Top 5 states with the largest combined number of seats in NFL stadiums</h3>
    <br />
    
    <!-----	STEP 3	-->
    <?php
	
	$records = largestCombinedCapacity();
	foreach ($records as $record)	{
		echo $record['state'] . " - " . $record['combinedCapacity'] . "<br >";	
	}
	
	?>
    </div>
    
    <div id="PartThree">
    <!-----	STEP 4	-->
    <br />
    <h3>List of all teams and their home stadium</h3>
    <br />
    
    <!-----	STEP 6	-->
    <?php
	
	$records = teamsAndStadiums();
	foreach ($records as $record)	{
		echo $record['teamName'] . " - " . $record['state'] . "<br />";	
	}
	
	//	end close db connection
	$dbConn = null;
	
	
	?>    
    </div>
    </div>
	</body>
	</html>
