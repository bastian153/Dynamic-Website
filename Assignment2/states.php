<?php
    require_once('dbconnection.php');

  	try {
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
