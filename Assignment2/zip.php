<?php
	require_once('dbconnection.php');

  	if(isset($_REQUEST["q"]) && !empty($_REQUEST["q"])){
	  	try {
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
