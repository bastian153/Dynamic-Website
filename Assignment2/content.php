<?php
  require_once('dbconnection.php');

  // Get all prodcut information from database
  $stmt = $conn->prepare("SELECT bookName, name, isbn13, genre, price,
                 cover FROM book, author WHERE authorID = id
                 ORDER BY RAND()"); // Comment this to remove randomness
  $stmt->execute();

  // Get total number of products available
  $total = $conn->prepare("SELECT COUNT(*) AS total FROM book");
  $total->execute();
  $total = $total->fetch(PDO::FETCH_ASSOC);
  $total = $total['total'];

  echo '<table class="col-12">';
  $count = 0;
  while($count < $total){
    echo '<tr>';

    for($i = 0; $i < 3 && $count < $total; $i++){
      $row = $stmt->fetch(PDO::FETCH_ASSOC);
      writeTableCell($row);
      $count = $count + 1;
    }

    echo '</tr>';
  }
  echo '</table>';




  function writeTableCell($row){
    echo '<td class="col-4">';
    echo '<a href="products.php?isbn=' . $row['isbn13'] . '">';
    echo '<img src="data:image/jpeg;base64,' .
          base64_encode($row['cover']) . '"/>';
    echo '<p>' . $row['bookName'] . '</p>';
    echo '<p>Author: ' . $row['name'] . '</p>';
    echo '<p>Genre: ' . $row['genre'] . '</p>';
    echo '<p>ISBN-13: ' . substr($row['isbn13'], 0, 3) .
          '-' . substr($row['isbn13'], 3) . '</p>';
    echo '<p>Price: $' . $row['price'] . '</p>';
    echo '</td>';
  }
?>
