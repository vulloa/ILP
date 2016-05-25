<?php
//	the PHP code for lab 3, seperated for a cleaner looking file.

echo "VANESSA WAS HERE";

$error1 = "";
$error2 = "";
$inches = 0;
$cms = 0;
$conv = 0;
$result = 0;

if(isset ($_GET['CMSuserInput']))	{
	$cm = $_GET['CMSuserInput'];
	
	if (is_numeric($cm))	{
		$inches = $cm * 0.393701;
	} else	{
		$error1 = "Please enter a valid number!";
	}
}

if (isset ($_GET['INuserInput']))	{
	$in = $_GET['INuserInput'];
	
	if(is_numeric($in))	{
		
		if(isset($_GET['unit']))	{
			$unit = $_GET['unit'];
			
			switch($unit)	{
				case 'CMS':
					$conv = 2.54;
					break;
				case 'YD':
					$conv = (1/36);
					break;
				case 'FT': 
					$conv = (1/12);
					break;
			}
			$result = $in * $conv;
		} else	{
			$error2 = "Please select a unit!";
		}
		
	} else	{
		$error2 = "Please enter a valid number!";
	}
}

?>