<%@page import="Web.ShoppingCart"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                    <a href="Products"><li class="col-4">Products</li></a>
                    <a href=""><li class="col-4">About</li></a>
                    <a href="Checkout"><li class="col-4">Checkout ( <span id="itemsInCart"><% 
                       LinkedList<ShoppingCart> cart = (LinkedList<ShoppingCart>)session.getAttribute("cart");
                       if(cart == null)
                        out.println(0);
                       else
                        out.println(cart.size());%></span>) </li></a>
                </ul>
            </nav>
        </header>
                
        <h1 style="font-family: sans-serif;
            margin: 30px;">Cart is empty! Shop our products and return here to checkout!</h1>
                
                
        <footer>
            <h3>Support #: 1-800-555-1632</h3>
            <h3>Support Email: <a href="mailto:support@noble.com">support@noble.com</a></h3>
            <h3>Noble &amp Barnes &copy 2016</h3>
        </footer>
    </body>
</html>
