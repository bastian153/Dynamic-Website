<!DOCTYPE html>
<html>

<?php
 require_once('dbconnection.php');

  $stmt = $conn->prepare("SELECT * FROM orders WHERE "
                . $_GET['isbn'] . " = isbn13");
  $stmt->execute();

 $row = $stmt->fetch(PDO::FETCH_ASSOC);


  echo '<head>';
  echo '<title>Noble &amp Barnes: Confirmation </title>';
  echo '<link rel="stylesheet" type="text/css" href="css/stylesheet.css">';
  echo '<meta charset="UTF-8">';
  echo '</head>';
?>

  <body>
    <header>
      <h1>Noble &amp Barnes</h1>

    </header>
      <?php
       echo $row['first_name'];
       echo $row['last_name'];

      ?>
</body>

</html>
