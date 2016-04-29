<?php
	$servername = "localhost";
  	$username = "root";
  	$password = "";

  	if(isset($_REQUEST["q"]) && !empty($_REQUEST["q"])){
	  	try {
	  		$conn = new PDO("mysql:host=" . $servername . ";dbname=nobleandbarnes;", $username, $password);
	  		$stmt = $conn->prepare("SELECT DISTINCT city FROM zips WHERE city LIKE" . "'" . $_REQUEST["q"] . "%'
                                ORDER BY city");
	  		$stmt->execute();
	  		
	  		$return_arr = array();

	  		while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	  			array_push($return_arr,$row['city']);
	  		}

	  		echo json_encode($return_arr);

	  	} catch(PDOException $e){
	  		print "Error occured!" . "<br>" . $e->getMessage();
	  		exit(-1);
	  	}
  	}
?>
