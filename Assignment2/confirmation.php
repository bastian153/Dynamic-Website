<!DOCTYPE html>
<html>

<?php
  require_once('dbconnection.php');

  // Get the Order record
  $orderStmt = $conn->prepare("SELECT * FROM orders WHERE "
                . $_GET['order'] . " = id");
  $orderStmt->execute();
  $order = $orderStmt->fetch(PDO::FETCH_ASSOC);

  // Get the Book record 
  $bookStmt = $conn->prepare("SELECT * FROM book WHERE "
                . $order['isbn13'] . " = isbn13");
  $bookStmt->execute();
  $book = $bookStmt->fetch(PDO::FETCH_ASSOC);



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

    <footer>
      <h3>Support #: 1-800-555-1632</h3>
      <h3>Support Email: <a href="mailto:support@noble.com">
            support@noble.com</a></h3>
      <h3>Noble &amp Bares &copy 2016</h3>
    </footer>

  </body>
</html>
