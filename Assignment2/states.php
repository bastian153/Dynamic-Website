<?php
	$servername = "localhost";
  	$username = "root";
  	$password = "";

  	try {
  		$conn = new PDO("mysql:host=" . $servername . ";dbname=nobleandbarnes;", $username, $password);
  		$stmt = $conn->prepare("SELECT DISTINCT state FROM zips");
  		$stmt->execute();
  		$result = $stmt->fetchAll(PDO::FETCH_ASSOC);
  		$json = json_encode($result);
  		echo $json;
  	} catch(PDOException $e){
  		print "Error Occured!" . "<br>" . $e->getMessage();
  		exit(-1);
  	}
?>
