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
		<style type="text/css">
			.style1	{
				/* For odd Numbers */
				background-color: #660000;
				color: #ccc;
			} 
			.style2	{
				/*	For even numbers */
				background-color: #ccc;
				color: #000;
			}
			table	{
				border: 1px solid #000;
				margin: 0 auto;
				border-collapse:collapse;
			}
			td	{
				padding: 10px;
				border: black 1px solid;
				text-align:center;
			}
			h3	{
				text-align:center;
				color: #000;
				font-family:Segoe, "Segoe UI", "DejaVu Sans", "Trebuchet MS", Verdana, sans-serif;
			}
		</style>
		
	</head>

	<body>
		<div>
		<!--PHP Code starts here-->
		
		<?php
			//variables
			$cols=10;
			$even=0;
			$odd=0;
			
			echo "<table>";
				for ($i=0 ; $i < 10 ; $i++)	{
					echo "<tr>"; 
						for($j=0 ; $j<$cols ; $j++)	{
							$randrumber=rand(1,100);
							
							if($randrumber % 2 == 0)	{
								echo "<td class=\"style1\">";
								$even += 1;
							}	else {
								echo "<td class=\"style2\">";
								$odd += 1;
							}
							echo $randrumber . "</td>";
						}
					echo "</tr>";
				}
			echo "</table>";
			echo "<h3>There are $odd odd numbers and $even even numbers. </br></h3>";
			echo "<h3>There are $odd % odd numbers and $even % even numbers. </br></h3>";
		?>
		</div>
	</body>
</html>
