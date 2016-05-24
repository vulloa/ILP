<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>Vanessa Ulloa CST336 LAB 2</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		
		<!--CSS CODE HERE-->
		<link href="css/A2.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
	<?php
//	take today's date and convert to roman numerals
	//	variables
		$TodaysDate = date("m.d.Y");
		$TodaysMonth = idate("m");
		$TodaysDay = idate("d");
		$TodaysYear = idate("Y");
		
		$TodaysTime = date("H:i:s");
		$TodaysHour = idate("H");
		$TodaysMin = idate("i");
		$TodaysSec = idate("s");
	
	//	form
	echo "<table id=\"a2table\">";

	echo "<tr><td class=\"todaysdate\" colspan=\"3\">Today's Date is <span>" . $TodaysDate . "</span></td></tr>";
	echo "<tr><td colspan=\"3\" class=\"instr\">This assignment will take today's date and convert it to Roman Numerals.</td></tr>";
	
	//conversion
	echo "<tr>";
	
	echo NumToRoman($TodaysMonth);
	echo NumToRoman($TodaysDay);
	echo NumToRoman($TodaysYear);	
	
	echo "</tr>";
	echo "</table>";	
	
	echo "<br /><br /><br /><br /><br />";
	
	//	table 2
	
	echo "<table id='a2table2'>";
		echo "<tr><td class='todaysdate' colspan='3'>The Current Time is <span>" . $TodaysTime . "</span></td></tr>";
		echo "<tr><td colspan='3' class='instr'>The current time will be converted to Roman Numerals</td></tr>";
		
		echo "<tr>";
		echo NumToRoman($TodaysHour);
		echo NumToRoman($TodaysMin);
		echo NumToRoman($TodaysSec);
		echo "</tr>";
	echo "</table>";
	
	echo "<br /><br /><br /><br /><br />";
	
	echo "<table><tr><td class='instr2'>Refresh the page to see the Roman Numerals Update!</td></tr></table>" ;
	
	//	functions
	function NumToRoman($num)	{
		$RomanValue = "";
		$EvenFlag = false;
		$origNum = $num;
		$V = array(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1, 0);
		$N = array("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I", "00");	
		
		for($i = 0 ; $i < 13 ; $i++)	{
			//echo $i . ": " . $num . " , " . $V[$i] . "<br />";
			if($num % 2 == 0)	{
				$EvenFlag = true;
			}
			while ( $num >= $V[$i])	{
				$num -= $V[$i];
				$RomanValue .= $N[$i];
			}
		}

		echo "<td class=\"";
			if ($EvenFlag == TRUE)	{
				echo "RomanValueEven";
			} elseif($origNum % 10 == 0) {
				echo "RomanValueZero";
				$RomanValue .= "00";
			}else {
				echo "RomanValueOdd";
			}
		echo "\">" . $RomanValue . "</td>";
	}
				
	?>
	</body>
	</html>