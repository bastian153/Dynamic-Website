<?php
	$servername = "localhost";
  	$username = "root";
  	$password = "password";

  	if(isset($_GET["state_id"]) && !empty($_GET["state_id"])){
	  	try {
	  		$conn = new PDO("mysql:host=" . $servername . ";dbname=nobleandbarnes", $username, $password);
	  		$stmt = $conn->prepare("SELECT DISTINCT city FROM zips WHERE state=" . "'" . $_GET["state_id"] . "'");
	  		$stmt->execute();
	  		$result = $stmt->fetchAll(PDO::FETCH_ASSOC);
	  		$json = json_encode($result);
	  		echo $json;
	  	} catch(PDOException $e){
	  		http_response_code(404);
	  		print $sql . "<br>" . $e->getMessage();
	  		exit(-1);
	  	}
  	}
?>