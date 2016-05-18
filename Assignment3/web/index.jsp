<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Web.Products" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noble &amp Barnes</title>
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
    </head>
    <body>
        <header>
            <h1>Noble &amp Barnes</h1>
            <nav>
                <ul>
                    <a href=""><li class="col-6">Products</li></a>
                    <a href="./about.html"><li class="col-6">About</li></a>
                </ul>
            </nav>
        </header>
        <%
        Object obj = request.getSession().getAttribute("view");
        out.println("<h1>" + obj + "</h1>");
        %>
        
        <div id="products">
            <jsp:include page="/Products" />
        </div>
    </body>
</html>
