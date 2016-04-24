<!DOCTYPE html>
<html>

<?php
  $servername = "localhost";
  $username = "root";
  $password = "";

  try {
    $conn = new PDO("mysql:host=". $servername . ";dbname=nobleandbarnes",
                    $username, $password);
    $stmt = $conn->prepare("SELECT * FROM book, author WHERE "
                   . $_GET['isbn'] . " = isbn13 AND authorID = id");
    $stmt->execute();

    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  } catch(PDOException $e){
    echo $sql . "<br>" . $e->getMessage();
    exit(-1);
  }


  echo '<head>';
  echo '<title>Noble &amp Barnes: ' . $row['bookName'] . ' </title>';
  echo '<link rel="stylesheet" type="text/css" href="css/stylesheet.css">';
  echo '<meta charset="UTF-8">';
  echo '</head>';
?>

  <body>
    <header>
      <h1>Noble &amp Barnes</h1>
      <nav>
        <ul>
          <a href="../index.html"><li class="col-6">Products</li></a>
          <a href="../about.html"><li class="col-6">About</li></a>
        </ul>
      </nav>
    </header>


  <div class="col-12 description">
    <div class="col-5">
    <?php
      echo '<img src="data:image/jpeg;base64,' .
            base64_encode($row['cover']) . '"/>';
      echo '<h2>' . $row['bookName'] . '</h2>';
      echo '<p>Author: ' . $row['name'] . '</p>';
      echo '<p>ISBN-10: ' . $row['isbn10'] . '</p>';
      echo '<p>ISBN-13: ' . substr($row['isbn13'], 0, 3) .
            '-' . substr($row['isbn13'], 3) . '</p>';
      echo '<p>Genre: ' . $row['genre'] . '</p>';
      echo '<p>Price: $' . $row['price'] . '</p>';
    ?>
    </div>
    <div class="col-6 summary">
      <h1>Description</h1>
      <?php
        echo $row['summary'];
      ?>
    <input type="button" onclick="location.href='../form.html'"
        class="submission" value="Order" />
    </div>
  </div>

  <div id="author" class="col-12">
    <h1>About the Author</h1>
    <?php
      echo $row['description'];
    ?>
  </div>

  <footer>
    <h3>Support #: 1-800-555-1632</h3>
    <h3>Support Email: <a href="mailto:support@noble.com">
          support@noble.com</a></h3>
    <h3>Noble &amp Bares &copy 2016</h3>
  </footer>
</body>
</html>
