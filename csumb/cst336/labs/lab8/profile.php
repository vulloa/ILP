<?php

/*	STEP 2	*/
$status_message = "";
#define ('SITE_ROOT', realpath(dirname(__FILE__)));

session_start();

if( !isset($_SESSION['username']))	{
	if(isset($_POST['loginForm']))	{
		require ("../../db_connection.php");
		
		$sql = "SELECT * FROM lab9_user WHERE username = :username AND password = :password";
		$stmt = $dbConn->prepare($sql);
		$stmt->execute(array(":username"=>$_POST['username'],":password"=>sha1($_POST['password'])));
		$record = $stmt->fetch();
		
		if(!empty($record))	{
			$_SESSION['username'] = $record['username'];
			$_SESSION['profilePic'] = $record['profilePic'];
			#echo "<h3>Welcome " . $record['username'] . "!</h3>";
			$status_message = "<h3>Welcome! " . $record['firstname'] . " " . $record['lastname'] . "!</h3>";
			#echo "<h3>Welcome! " . $record['firstname'] . " " . $record['lastname'] . "!</h3>";
			
			if(!file_exists($record['username']))	{
				mkdir($record['username']);	
				#mkdir("uploadedFiles/" . $record['username']);	
			}
				
		}	else	{
			
			$error = "Wrong username/password";
			header("Location:Login.html");
		}
	}
}

/*	STEP 3	*/
if(isset($_FILES['fileName']))	{

	$_SESSION['profilePic'] = $_FILES['fileName']['name'];
	move_uploaded_file($_FILES['fileName']['tmp_name'], $_SESSION['username'] . "/" . $_FILES['fileName']['name']);
	#move_uploaded_file($_FILES['fileName']['tmp_name'], SITE_ROOT . $_SESSION['username'] . "/" . $_FILES['fileName']['name']);
	#move_uploaded_file($_FILES['fileName']['tmp_name'],"uploadedFiles/" . $_FILES['fileName']['name']);
	
	#Syntax move_uploaded_file ( string $filename , string $destination )
	
}


?>
<!--BEGIN HTML -->

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">

	<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
	Remove this if you use the .htaccess -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<title>Vanessa CST336 LAB 8</title>
	<meta name="description" content="">
	<meta name="author" content="Vanessa Ulloa">

	<meta name="viewport" content="width=device-width; initial-scale=1.0">

	<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
	<link rel="shortcut icon" href="/favicon.ico">
	<link rel="apple-touch-icon" href="/apple-touch-icon.png">
    <link rel="stylesheet" type="text/css" href="css/lab8CSS.css">
</head>

<body>
<div id="wrapper">
<div id="header">
	<h1>Profile</h1>
</div>    
<?=$status_message ?>    

<div id="lab">
<?php

if(empty($_SESSION['profilePic']))	{
	
	echo "<img src='unknown.jpg' width='196' height='216'/><br />";
	
}	else	{

	echo "<h3>Welcome " . $_SESSION['username'] . "!</h2>";
	echo "<img class='profilepic' src='" . $_SESSION['username'] . "/" . $_SESSION['profilePic'] . "'><br />";
	
}

?>

<!--	STEP 1	-->
<form method="post" enctype="multipart/form-data">
	<br />
    Select File to update profile Picture:
    
    <input type="file" name="fileName" />
    <br />
    <br />
    <br />
    <input type="submit" name="loginForm">

</form>

</div>
</div>
</body>
</html>
