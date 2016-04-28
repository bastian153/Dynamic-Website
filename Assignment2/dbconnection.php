<?php

  // Connecting to local Apache and MySQL Datbase
  $servername = "localhost";
  $dbname = "nobleandbarnes";
  $username = "root";
  $password = "";


  /*
  $servername = "sylvester-mccoy-v3.ics.uci.edu";
  $dbname = "inf124grp08";
  $username = "inf124grp08";
  $password = "@e8hUjaB";
  */

  try {

    $conn = new PDO("mysql:host=". $servername . ";dbname=" . $dbname . ";",
                    $username, $password);


    /*
    $conn = new PDO('mysql:host=sylvester-mccoy-v3.ics.uci.edu; dbname=inf124grp08;',
                      'inf124grp08', '@e8hUjaB');
    */

  } catch(PDOException $e){
    echo  "Error Occured!<br>" . $e->getMessage();
    exit(-1);
  }

?>
