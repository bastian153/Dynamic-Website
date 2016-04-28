<?php
	$servername = "localhost";
  	$username = "root";
  	$password = "";

  	if(isset($_GET["city_id"]) && !empty($_GET["city_id"])){
	  	try {
	  		$conn = new PDO("mysql:host=" . $servername . ";dbname=nobleandbarnes;", $username, $password);
	  		$stmt = $conn->prepare("SELECT zip FROM zips WHERE city=" . "'" . $_GET["city_id"] . "'");
	  		$stmt->execute();
	  		$result = $stmt->fetchAll(PDO::FETCH_ASSOC);
	  		$json = json_encode($result);
	  		echo $json;
	  	} catch(PDOException $e){
	  		print "Error Occured!" . "<br>" . $e->getMessage();
	  		exit(-1);
	  	}
  	}
?>
