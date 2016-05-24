<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>Vanessa Ulloa CST336 Lab 3</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<link href="css/a3css.css" rel="stylesheet" type="text/css">        
	</head>
	<?php
	
	$UserResult = "";
	$UserNum = 0;
	
    function NumToRoman($num)	{
		$V = array(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1, 0);
		$N = array("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I", "00");	
		$RomanValue = "";
		
		for($i = 0 ; $i < count($V)-1 ; $i++)	{
			while ( $num >= $V[$i])	{
				$num -= $V[$i];
				$RomanValue .= $N[$i];
			}
		}
		
		return $RomanValue;
		
	}//end NumToRoman()
		
		if(isset($_GET['UserNum']))	{
			$UserNum = $_GET['UserNum'];
			$UserNum = trim($UserNum);
			
			if($UserNum < 1 || $UserNum > 4999)	{
				$UserResult = "Please Enter a number between 1 and 4999.";	
			}	elseif (!is_numeric($UserNum))	{
				$UserResult = "Please Enter a Number.";
			}	else	{
				$UserResult = "<span>" . NumToRoman($UserNum) . "</span>";
			}

		}	else	{
			$UserResult = "Please enter a value! (1-4999)";
		}
	
	
	
    ?>
	<body>
	<div id="wrapper">
    <div id="header">
		<h1>Roman Numeral Conversion</h1>
    </div>
    <form id="NumToRoman" method="GET" action="NumToRoman.php">
    	<h3>Number to Roman Numeral</h3><br />
		Enter a Number: <input type="text" name="UserNum" /> <br /><br />
        <input type="submit" value="Continue" class="SubmitButton" /><br /><br />
        <? echo $UserResult; ?>
        <br /><br /><br />
        <a href="index.php">Start Over</a>
    </form>
    </div>
	</body>
</html>
