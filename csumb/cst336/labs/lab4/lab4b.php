<?php

#link to db connection file
require('db_connection.php');

#functions

function getStadiums()	{
	global $dbConn;
	
	$sql="SELECT stadiumId, stadiumName FROM nfl_stadium ORDER BY stadiumName";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	return $stmt->fetchAll();	
}

function getTeamNames()	{
	global $dbConn;
	
	$sql = "SELECT teamId, teamName FROM nfl_team ORDER BY teamName";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute();
	return $stmt->fetchAll();
}

#	checks if delete button was clicked
if (isset($_POST['delete']))	{
	$sql = "DELETE FROM nfl_stadium WHERE stadiumId = :stadiumId";
	$stmt = $dbConn->prepare($sql);
	# statement for deletion, commented to keep records intact
	#$stmt->execute(array(":stadiumId"=>$_POST['stadiumId']));
	echo "<script type=\"text/javascript\">";
	echo "alert(\"Stadium Deleted!\")";
	echo "</script>";
}

#	checks if add Match button was clicked
if (isset($_POST['addMatch']))	{
	$sql = "INSERT INTO nfl_match (team1_id, team2_id, date) VALUES (:team1_id,:team2_id,:date)";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(":team1_id" => $_POST['team1'],":team2_id" => $_POST['team2'],":date" => $_POST['date']));
	
	$matchId = $dbConn->lastInsertId();
	
	$sql = "INSERT INTO nfl_recap (matchId,recap) VALUES (:matchId,:recap)";
	$stmt = $dbConn->prepare($sql);
	$stmt->execute(array(":matchId" => $matchId, ":recap" => $_POST['recap']));
	
	echo "<script type=\"text/javascript\">";
	echo "alert(\"Record Added!\")";
	echo "</script>";
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

		<title>Vanessa Ulloa CST336 LAB 4B</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<link href="css/lab4CSS.css" rel="stylesheet" type="text/css">
        
        <!-- CONFIRM DELETE SCRIPT -->
        <script>
		
		function confirmDelete(stadiumName)	{
			var remove = confirm("Are you sure you want to delete " + stadiumName + "?");
			
			if(!remove)	{
				// remove == false
				event.preventDefault();
			}
				
		}
		
		</script>
        <style>
		form 	{
			display: inline;
		}
		</style>

	</head>

<body>
<div id="wrapper">
	<div id="header">
    	<h2>NFL Matches</h2>
	</div>
<div id="lab">    
    <span>Select Team 1:</span>
<form method="post">
	<select name="team1">
    <?php
		$teamNames = getTeamNames();
		foreach ($teamNames as $team)	{
			echo "<option value=" . $team['teamId'] . ">" . $team['teamName'] . "</option>";	
		}
	?>
    </select><br />
    
    <span>Select Team 2:</span>
	<select name="team2">
    <?php
		$teamNames = getTeamNames();
		foreach ($teamNames as $team)	{
			echo "<option value=" . $team['teamId'] . ">" . $team['teamName'] . "</option>";	
		}
	?>
    </select><br />
    
    <span>Date</span>: <input type="date" name="date"><br /><br />
    
    <textarea name="recap" rows="15" cols="60" placeholder="Enter Match Recap"></textarea><br />
    <input type="Submit" name="addMatch" class="SubmitButton" />
    
</form>
<br /><br />
<h3>NFL Stadiums</h3>
<?php

$stadiumList = getStadiums();

foreach($stadiumList as $stadium)	{?>

	<?=$stadium['stadiumName']?>
    <form method="POST" action="updateStadium.php">
    	<input type="hidden" name="stadiumId" value="<?=$stadium['stadiumId']?>" />
        <input type="submit" name="update" value="Update" class="SubmitButton" />
    </form>
    <form method="post" onsubmit="confirmDelete('<?=$stadium['stadiumName']?>')">
    	<input type="hidden" name="stadiumId" value="<?=$stadium['stadiumId']?>" />
        <input type="submit" name="delete" value="Delete" class="SubmitButton" />
    </form>
    <br />
    
<?php
} 
?>
</div>    
</div>		
</body>
</html>
