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
	    
	<body>
	<div id="wrapper">
    <div id="header">
		<h1>Roman Numeral Conversion</h1>
    </div>
    <form id="StepOne" method="GET" action="steptwo.php" >
    	<h3>What kind of conversion are we doing today?</h3>
        <input type="radio" name="ConvType" value="NumToRom" />Number to Roman Numerals<br />
        <input type="radio" name="ConvType" value="RomtoNum" />Roman Numerals to Number<br />
        <input type="submit" value="Continue" class="SubmitButton" /><br />
    </form>
    </div>
	</body>
</html>