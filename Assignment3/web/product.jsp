<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Web.ProductDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noble &amp Barnes: Product Details</title>
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
    </head>
    <body>
        <header>
            <h1>Noble &amp Barnes</h1>
            <nav>
                <ul>
                    <a href="./index.jsp"><li class="col-6">Products</li></a>
                    <a href="./about.html"><li class="col-6">About</li></a>
                </ul>
            </nav>
        </header>
        
        <%
        Object obj = request.getSession().getAttribute("object");
        out.print(obj);
        %>
        <div class="col-12 description">
             <div class="col-5">
                 <jsp:include page="ProductDetails"/>
    </body>
</html>
