<?php
	$servername = "localhost";
  	$username = "root";
  	$password = "";

  	if(isset($_REQUEST["q"]) && !empty($_REQUEST["q"])){
	  	try {
	  		$conn = new PDO("mysql:host=" . $servername . ";dbname=nobleandbarnes;", $username, $password);
	  		$stmt = $conn->prepare("SELECT zip FROM zips WHERE zip LIKE" . "'" . $_REQUEST["q"] . "%'");
	  		$stmt->execute();
	  		
	  		$return_arr = array();

	  		while($row = $stmt->fetch(PDO::FETCH_ASSOC)){
	  			array_push($return_arr,$row['zip']);
	  		}

	  		echo json_encode($return_arr);

	  	} catch(PDOException $e){
	  		print "Error Occured!" . "<br>" . $e->getMessage();
	  		exit(-1);
	  	}
  	}
?>
