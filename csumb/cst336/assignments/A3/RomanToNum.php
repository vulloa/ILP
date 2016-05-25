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

	function RomtoNum($rom)	{
		$rom = trim($rom);
		$outputNum[0] = 0;
		$result = 0;
		$V = array(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1, 0);
		$N = array("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I", "00");
		$r = 0;
		
		$RomanValues = str_split($rom,1);
		$RomanValues = array_reverse($RomanValues);	
		
		for ($i = 0 ; $i < count($RomanValues) ; $i++)	{
			#echo $i . ", " . $RomanValues[$i] . "<br />";
			
			for ($t = 0 ; $t < count($N)-2 ; $t++)	{
				if ($RomanValues[$i] ==	$N[$t])	{
					$outputNum[$i] = $V[$t];
					echo $RomanValues[$i] . ", " . $N[$t] . ", " . $outputNum[$i] . "<br />";
				}
			}
		}
		
		for($r = 0 ; $r < count($outputNum) ; $r++)	{
			if($r == 0)	{
				$result += $outputNum[$r];
			} elseif ($outputNum[$r] >= $outputNum[$r-1])	{
				$result += $outputNum[$r];	
			} else	{
				$result -= $outputNum[$r];
			}
		}
		
		return $result;
	}
	

		if(isset($_GET['UserRom']))	{
			$UserRom = $_GET['UserRom'];
			$UserRom = trim($UserRom);
			
			if(is_numeric($UserRom))	{
				$UserResult = "Please Enter a valid ROman Numeral";	
			}	elseif($UserRom = '')	{	
				$UserResult = 0;
			}	else	{
				$UserResult = "<span>" . RomtoNum($UserRom) . "</span>";
			}
		}

    ?>
	<body>
	<div id="wrapper">
    <div id="header">
		<h1>Roman Numeral Conversion</h1>
    </div>
    <form id="NumToRoman" method="GET" action="RomanToNum.php">
    	<h3>Roman Numeral to Number</h3><br />
		Enter a Roman Numeral: <input type="text" name="UserRom" /> <br /><br />
        <input type="submit" value="Continue" class="SubmitButton" /><br /><br />
       <? echo $UserResult; ?>
        <br /><br /><br />
        <a href="index.php">Start Over</a>
    </form>
    </div>
	</body>
</html>