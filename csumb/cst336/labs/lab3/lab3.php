<?php

$error1 = "";
$error2 = "";
$inches = 0;
$cms = 0;
$conv = 0;
$result = "";
$resultunit = "";

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
					$resutlunit = "cms";
					break;
				case 'YD':
					$conv = (1/36);
					$resultunit = "yds";
					break;
				case 'FT': 
					$conv = (1/12);
					$resultunit = "ft";
					break;
			}
			$result = $in * $conv;
			$result .= " " . $resultunit;
		} else	{
			$error2 = "Please select a unit!";
		}
		
	} else	{
		$error2 = "Please enter a valid number!";
	}
}

?>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">

		<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame
		Remove this if you use the .htaccess -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>Vanessa Ulloa CST336 LAB 3</title>
		<meta name="description" content="">
		<meta name="author" content="Vanessa">

		<meta name="viewport" content="width=device-width; initial-scale=1.0">

		<!-- Replace favicon.ico & apple-touch-icon.png in the root of your domain and delete these references -->
		<link rel="shortcut icon" href="/favicon.ico">
		<link rel="apple-touch-icon" href="/apple-touch-icon.png">
		<link href="css/lab3CSS.css" rel="stylesheet" type="text/css">

	</head>

	<body>
	<div id="wrapper">
    <table>
    	<tr>
        <form method="get">
        	<td class="col1"><span>Enter cms:</span></td> 
            <td class="col2"><input type="text" name="CMSuserInput" /></td>
            <td class="col3"><input type="submit" value="Convert to Inches" class="SubmitButton" /></td>
        </form>
        </tr>
        <form method="get">
        <tr>
        	<td class="col1"><span>Inches:</span></td>
            <td class="col2" colspan="2">
            <? if ( $inches <> '' )	{
				echo "$inches Inches";
			} else {
				echo $error1;
			}
			?>
            </td>
        </tr>
        <tr><td colspan="3"><br /><br /><br /></td></tr>
        <tr>
        	<td class="col1"><span>Enter inches:</span></td> 
            <td class="col2"><input type="text" name="INuserInput" /></td>
            <td />
        </tr>
        <tr>
        	<td class="col1"><span>Convert to: </span></td>
            <td class="col2">
            	<input type="radio" name="unit" value="CM" /> <span>cms</span>
                <input type="radio" name="unit" value="YD" /> <span>yards</span>
                <input type="radio" name="unit" value="FT" /> <span>feet</span>
            </td>
            <td class="col3"><input type="submit" value="Convert !" class="SubmitButton" /></td>
        </tr>
        <tr>
			<td class="col1"><span>Result: </span></td>
			<td class="col2" colspan="2"><?= $result ?><?= $error2 ?></td>
        </tr>
        </form>
    </table>

    </div>
	</body>
</html>
