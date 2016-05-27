<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Confirmation Page</title>
  <link type="text/css" rel="stylesheet" href="css/stylesheet.css";
  <meta charset="UTF-8">
</head>

<body>
  <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost/inf124grp08"
        user="inf124grp08"  password="@e8hUjaB"/>

  <sql:query dataSource="${snapshot}" var="result">
    SELECT * from orders WHERE id = <%=request.getParameter("id")%>;
  </sql:query>


  <header>
    <h1>Noble &amp Barnes</h1>
    <nav>
      <ul>
        <a href="index.html"><li class="col-6">Products</li></a>
        <a href="about.html"><li class="col-6">About</li></a>
      </ul>
    </nav>
  </header>

  <div class="col-12 details" align="center">
    <div class="col-12">
        <h2>Confirmation Order ID#: <%=request.getParameter("id")%> </h2>
    </div>
    <div class="col-5 order-detail">
        <p>First Name: <c:out value="${result.first_name}"/></p>
        <p>Last Name: <c:out value="${result.last_name}"/></p>
        <p>Address: <c:out value="${result.address}"/></p>
        <p>State: <c:out value="${result.state}"/></p>
        <p>City: <c:out value="${result.city}"/></p>
        <p>Zip Code: <c:out value="${result.zp_code}"/></p>
        <p>ISBN: <c:out value="${result.isbn13}"/></p>
        <p>Quantity: <c:out value="${result.quantity}"/></p>
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
