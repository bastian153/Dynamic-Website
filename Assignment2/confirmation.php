<!DOCTYPE html>
<?php
  require_once('dbconnection.php');

  // Get the Order record
  $orderStmt = $conn->prepare("SELECT * FROM orders, tax WHERE "
                . $_GET['order'] . " = id and orders.state = tax.state");
  $orderStmt->execute();
  $order = $orderStmt->fetch(PDO::FETCH_ASSOC);

  // Get the Book record
  $bookStmt = $conn->prepare("SELECT * FROM book WHERE "
                . $order['isbn13'] . " = isbn13");
  $bookStmt->execute();
  $book = $bookStmt->fetch(PDO::FETCH_ASSOC);
?>

<html>
<head>
  <title>Confirmation Page</title>
  <link type="text/css" rel="stylesheet" href="css/stylesheet.css";
  <meta charset="UTF-8">
</head>

<body>
  <header>
    <h1>Noble &amp Barnes</h1>
    <nav>
      <ul>
        <a href="index.html"><li class="col-6">Products</li></a>
        <a href="about.html"><li class="col-6">About</li></a>
      </ul>
    </nav>
  </header>

  <div class="col-12 details">
    <div class="col-12">
      <h2>Confirmation Order ID#: <?php echo $order['id'];?> </h2>
    </div>
    <div class="col-5 order-detail">
    <?php
      echo '<img src="data:image/jpeg;base64,' . base64_encode($book['cover']) . '"/>';
      echo '<h3>' . $book['bookName'] . '</h3>';
      echo '<p> Item Subtotal: $' . $book['price'] . '</p>';
      echo '<p> Quantity: ' . $order['quantity'] . '</p>';
      echo '<p> State Tax(' . $order['state'] . '): ' . $order['rate'] . '%</p>';
      echo '<p> Total (Before shipping): $';
      $total = $book['price'] * $order['quantity'];
      $total = round($total + ($total * ($order['rate']/100)), 2);
      echo $total . '</p>';
    ?>
    </div>
    <div class="col-6 shipping-detail">
      <h3>Shipping Information</h3>
      <?php
        echo '<p>Name: ' . $order['first_name'] . ' ' . $order['last_name'] . '</p>';
        echo '<p>Address: ' . $order['address'] . '</p>';
        echo '<p>City: ' . $order['city'] . ', ' . $order['state'] . ' ' .
              $order['zp_code'] . '</p>';
      ?>
    </div>
  </div>

  <footer>
    <h3>Support #: 1-800-555-1632</h3>
    <h3>Support Email: <a href="mailto:support@noble.com">
          support@noble.com</a></h3>
    <h3>Noble &amp Bares &copy 2016</h3>
  </footer>

</body>
</html>
