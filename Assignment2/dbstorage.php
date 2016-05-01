<?php
  try {
  require_once('dbconnection.php');

  $isbn = $_POST["isbn_input"];
  $quantity = $_POST["quan_input"];
  $firstName = $_POST["firstN"];
  $lastName = $_POST["lastN"];
  $address = $_POST["addr"];
  $city = $_POST["city"];
  $state = $_POST["state"];
  $zipCode = $_POST["zipcode"];

  $stmt = $conn->prepare("INSERT INTO orders
                          (first_name, last_name, address, state, city, zp_code, isbn13, quantity)
                          VALUES
                          ('$firstName', '$lastName', '$address', '$state', '$city', '$zipCode', '$isbn', '$quantity')"); // Comment this to remove randomness
  $stmt->execute();
} catch(PDOException $e){
  echo "Response: Error";
  die();
}
  echo $conn->lastInsertId();
  $conn = null;
?>
